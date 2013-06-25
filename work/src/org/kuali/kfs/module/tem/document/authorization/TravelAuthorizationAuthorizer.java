/*
 * Copyright 2011 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kfs.module.tem.document.authorization;

import java.util.Map;

import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.businessobject.TemSourceAccountingLine;
import org.kuali.kfs.module.tem.document.TravelAuthorizationDocument;
import org.kuali.kfs.module.tem.document.TravelDocument;
import org.kuali.kfs.sys.identity.KfsKimAttributes;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

public class TravelAuthorizationAuthorizer extends TravelArrangeableAuthorizer {

    /**
     *  check permission to close
     *
     * @param taDoc
     * @param user
     * @return
     */
    public boolean canClose(final TravelDocument taDoc, final Person user) {
        return getActionPermission(taDoc, user, TemConstants.Permission.CLOSE_TA);
    }

    /**
     * Check permission for amend
     *
     * @param taDoc
     * @param user
     * @return
     */
    public boolean canAmend(final TravelDocument taDoc, final Person user) {
        return getActionPermission(taDoc, user, TemConstants.Permission.AMEND_TA);
    }

    /**
     *
     * @param travelDocument
     * @param user
     * @return
     */
    public boolean canHold(TravelAuthorizationDocument travelDocument, Person user) {
        return getActionPermission(travelDocument, user, TemConstants.Permission.HOLD_TA);
    }

    /**
     *
     * @param travelDocument
     * @param user
     * @return
     */
    public boolean canRemoveHold(final TravelAuthorizationDocument travelDocument, final Person user) {
        return getActionPermission(travelDocument, user, TemConstants.Permission.REMOVE_HOLD_TA);
    }

    /**
     *
     * @param travelDocument
     * @param user
     * @return
     */
    public boolean canCancel(final TravelAuthorizationDocument travelDocument, final Person user) {
        return getActionPermission(travelDocument, user, TemConstants.Permission.CANCEL_TA);
    }

    /**
     *
     * @param travelDocument
     * @param user
     * @return
     */
    public boolean hideButtons(final TravelAuthorizationDocument travelDocument, final Person user) {
        return getActionPermission(travelDocument, user, TemConstants.Permission.HIDE_BUTTONS);
    }

    /**
     * Initiator is not allow to copy document
     *
     * @param travelDocument
     * @param user
     * @return
     */
    public boolean canCopy(TravelAuthorizationDocument travelDocument, Person user) {
        if(ObjectUtils.isNull(user)) {
            return false;
        }
        //if user is initiator or the arrange, do not allow to copy
        return getTravelService().isUserInitiatorOrArranger(travelDocument, user);
    }

    /**
     * If the current user is a fiscal officer on any accounting line on the document, add that qualification
     * @see org.kuali.kfs.module.tem.document.authorization.TravelArrangeableAuthorizer#addRoleQualification(java.lang.Object, java.util.Map)
     */
    @Override
    protected void addRoleQualification(Object dataObject, Map<String, String> qualification) {
        if (dataObject instanceof TravelAuthorizationDocument) {
            addAccountQualification((TravelAuthorizationDocument)dataObject, qualification);
        }
        super.addRoleQualification(dataObject, qualification);
    }

    /**
     * Goes through the given List of accounting lines and fines one line where the current user is the fiscal officer; it uses that line to put chart of accounts
     * code and account number qualifications into the given Map of attributes for role qualification
     * @param accountingLines a List of AccountingLines
     * @param attributes a Map of role qualification attributes
     */
    protected void addAccountQualification(TravelAuthorizationDocument authorizationDoc, Map<String, String> attributes) {
        final Person currentUser = GlobalVariables.getUserSession().getPerson();
        boolean foundQualification = false;
        int count = 0;
        while (!foundQualification && !ObjectUtils.isNull(authorizationDoc.getSourceAccountingLines()) && count < authorizationDoc.getSourceAccountingLines().size()) {
            final TemSourceAccountingLine accountingLine = (TemSourceAccountingLine)authorizationDoc.getSourceAccountingLines().get(count);
            foundQualification = addAccountQualificationForLine(accountingLine, attributes, currentUser);
            count += 1;
        }
        count = 0;
        while (!foundQualification && authorizationDoc.shouldProcessAdvanceForDocument() && !ObjectUtils.isNull(authorizationDoc.getAdvanceAccountingLines()) && count < authorizationDoc.getAdvanceAccountingLines().size()) {
            final TemSourceAccountingLine accountingLine = authorizationDoc.getAdvanceAccountingLine(count);
            foundQualification = addAccountQualificationForLine(accountingLine, attributes, currentUser);
            count += 1;
        }
    }

    /**
     * If the given user is the fiscal officer of the account on the given accounting line, then add that account as a qualification
     * @param line the accounting line to check
     * @param attributes the role qualification to fill
     * @param currentUser the currently logged in user, whom we are doing permission checks for
     * @return true if qualifications were added based on the line, false otherwise
     */
    protected boolean addAccountQualificationForLine(TemSourceAccountingLine line, Map<String, String> attributes, Person currentUser) {
        if (ObjectUtils.isNull(line.getAccount())) {
            line.refreshReferenceObject("account");
        }
        if (!ObjectUtils.isNull(line.getAccount()) && currentUser.getPrincipalId().equalsIgnoreCase(line.getAccount().getAccountFiscalOfficerSystemIdentifier())) {
            attributes.put(KfsKimAttributes.CHART_OF_ACCOUNTS_CODE, line.getChartOfAccountsCode());
            attributes.put(KfsKimAttributes.ACCOUNT_NUMBER, line.getAccountNumber());
            return true;
        }
        return false;
    }

    /**
     *
     * @param travelDocument
     * @param user
     * @param action
     * @param canInitiatorAct
     * @return
     */
    protected boolean getActionPermission(final TravelDocument travelDocument, final Person user, final String permission){
        final String nameSpaceCode = TemConstants.PARAM_NAMESPACE;

        //Return true if they have the correct permissions or they are the initiator and the initiator can perform this action.
        return isAuthorized(travelDocument, nameSpaceCode, permission, user.getPrincipalId());
    }

}