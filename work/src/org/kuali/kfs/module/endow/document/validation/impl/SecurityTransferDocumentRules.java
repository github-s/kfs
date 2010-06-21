/*
 * Copyright 2010 The Kuali Foundation.
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
package org.kuali.kfs.module.endow.document.validation.impl;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kfs.module.endow.EndowKeyConstants;
import org.kuali.kfs.module.endow.businessobject.EndowmentSourceTransactionLine;
import org.kuali.kfs.module.endow.businessobject.EndowmentTransactionLine;
import org.kuali.kfs.module.endow.document.EndowmentSecurityDetailsDocument;
import org.kuali.kfs.module.endow.document.EndowmentTransactionLinesDocument;
import org.kuali.kfs.module.endow.document.SecurityTransferDocument;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.GlobalVariables;

public class SecurityTransferDocumentRules extends EndowmentTransactionLinesDocumentBaseRules {


    /**
     * @see org.kuali.kfs.module.endow.document.validation.AddTransactionLineRule#processAddTransactionLineRules(org.kuali.kfs.module.endow.document.EndowmentTransactionLinesDocument,
     *      org.kuali.kfs.module.endow.businessobject.EndowmentTransactionLine)
     */
    @Override
    public boolean processAddTransactionLineRules(EndowmentTransactionLinesDocument transLineDocument, EndowmentTransactionLine line) {
        boolean isValid = true;

        SecurityTransferDocument securityTransferDocument = (SecurityTransferDocument) transLineDocument;

        isValid &= validateSecurity(isValid, (SecurityTransferDocument) transLineDocument, true);

        isValid &= validateRegistration(isValid, (SecurityTransferDocument) transLineDocument, true);

        // there can be only one source transaction line
        isValid &= validateOnlyOneSourceTransactionLine(true, securityTransferDocument, line, -1);

        if (isValid) {
            isValid &= super.processAddTransactionLineRules(transLineDocument, line);
        }

        return GlobalVariables.getMessageMap().getErrorCount() == 0;
    }

    /**
     * Validates that one and only one source transaction line can be added.
     * 
     * @param validateForAdd tells whether the validation is for add or not
     * @param endowmentTransactionLinesDocument
     * @return true if valid, false otherwise
     */
    private boolean validateOnlyOneSourceTransactionLine(boolean validateForAdd, EndowmentTransactionLinesDocument endowmentTransactionLinesDocument, EndowmentTransactionLine line, int index) {
        boolean isValid = true;

        if (line instanceof EndowmentSourceTransactionLine) {

            // if we do validation upon adding a new transaction line make sure we don't allow more than one line to be added; if
            // there
            // is already one source transaction line than validation will fail as no more source transaction line can be added
            if (validateForAdd) {
                if (endowmentTransactionLinesDocument.getSourceTransactionLines() != null && endowmentTransactionLinesDocument.getSourceTransactionLines().size() >= 1) {
                    isValid = false;
                    putFieldError(getErrorPrefix(line, index), EndowKeyConstants.EndowmentTransactionDocumentConstants.ERROR_SECURITY_TRANSFER_ONE_AND_ONLY_ONE_SOURCE_TRANS_LINE);
                }
            }
            // if we do validation on save or submit we have to make sure that there is one and only one source transaction line
            else {
                if (endowmentTransactionLinesDocument.getSourceTransactionLines() != null && endowmentTransactionLinesDocument.getSourceTransactionLines().size() != 1) {
                    isValid = false;
                    putFieldError(getErrorPrefix(line, index), EndowKeyConstants.EndowmentTransactionDocumentConstants.ERROR_SECURITY_TRANSFER_ONE_AND_ONLY_ONE_SOURCE_TRANS_LINE);
                }
            }
        }

        return isValid;

    }

    /**
     * Validate Security Transfer Transaction Line.
     * 
     * @param endowmentTransactionLinesDocumentBase
     * @param line
     * @param index
     * @return
     */
    @Override
    protected boolean validateTransactionLine(EndowmentTransactionLinesDocument endowmentTransactionLinesDocument, EndowmentTransactionLine line, int index) {
        boolean isValid = super.validateTransactionLine(endowmentTransactionLinesDocument, line, index);

        if (isValid) {
            // Obtain Prefix for Error fields in UI.
            String ERROR_PREFIX = getErrorPrefix(line, index);

            // Validate Units is Greater then Zero(thus positive) value
            isValid &= validateTransactionUnitsGreaterThanZero(line, ERROR_PREFIX);

            if (line instanceof EndowmentSourceTransactionLine) {
                // Validate if Sufficient Units are Avaiable
                isValid &= checkSufficientUnitsAvaiable(endowmentTransactionLinesDocument, line, ERROR_PREFIX);
            }

            // Check if value of Endowment is being reduced.
            checkWhetherReducePermanentlyRestrictedFund(line, ERROR_PREFIX);
        }

        return GlobalVariables.getMessageMap().getErrorCount() == 0;
    }

    /**
     * @see org.kuali.kfs.module.endow.document.validation.impl.EndowmentTransactionLinesDocumentBaseRules#processCustomSaveDocumentBusinessRules(org.kuali.rice.kns.document.Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean isValid = super.processCustomSaveDocumentBusinessRules(document);
        isValid &= !GlobalVariables.getMessageMap().hasErrors();

        if (isValid) {
            SecurityTransferDocument securityTransferDocument = (SecurityTransferDocument) document;

            // Validate Security
            isValid &= validateSecurity(isValid, securityTransferDocument, true);

            // Validate Registration code.
            isValid &= validateRegistration(isValid, securityTransferDocument, true);

            // Validate atleast one Source Tx was entered.
            if (!transactionLineSizeGreaterThanZero(securityTransferDocument, true))
                return false;

            // Validate atleast one Target Tx was entered.
            if (!transactionLineSizeGreaterThanZero(securityTransferDocument, false))
                return false;

            // Obtaining all the transaction lines for validations
            List<EndowmentTransactionLine> txLines = new ArrayList<EndowmentTransactionLine>();
            txLines.addAll(securityTransferDocument.getSourceTransactionLines());
            txLines.addAll(securityTransferDocument.getTargetTransactionLines());

            // Validate All the Transaction Lines.
            for (int i = 0; i < txLines.size(); i++) {
                EndowmentTransactionLine txLine = txLines.get(i);
                isValid &= validateTransactionLine(securityTransferDocument, txLine, i);
            }

            // Validate the source & target units are equal.
            isValid &= validateSourceTargetUnitsEqual(securityTransferDocument);

            // Validate the source & target amounts are equal.
            isValid &= validateSourceTargetAmountEqual(securityTransferDocument);

        }

        return GlobalVariables.getMessageMap().getErrorCount() == 0;
    }


    /**
     * @see org.kuali.rice.kns.rules.DocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.rice.kns.document.Document)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        return super.processCustomRouteDocumentBusinessRules(document);
    }
    
    /**
     * @see org.kuali.kfs.module.endow.document.validation.impl.EndowmentTransactionalDocumentBaseRule#validateSecurityClassTypeCode(org.kuali.kfs.module.endow.document.EndowmentSecurityDetailsDocument, boolean, java.lang.String)
     */
    @Override
    protected boolean validateSecurityClassTypeCode(EndowmentSecurityDetailsDocument document, boolean isSource, String classCodeType) {
        return true;
    }
}
