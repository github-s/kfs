/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kfs.web.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.kfs.KFSKeyConstants;
import org.kuali.kfs.bo.ElectronicPaymentClaim;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.service.ElectronicPaymentClaimingDocument;
import org.kuali.kfs.service.ElectronicPaymentClaimingService;

public class ElectronicFundTransferForm extends KualiForm {
    private List<ElectronicPaymentClaim> claims;
    private List<ElectronicPaymentClaimingDocument> availableClaimingDocuments;
    private String chosenElectronicPaymentClaimingDocumentCode;
    private String hasDocumentation;
    
    public ElectronicFundTransferForm() {
        claims = new ArrayList<ElectronicPaymentClaim>();
    }
    
    /**
     * Gets the availableClaimingDocuments attribute. 
     * @return Returns the availableClaimingDocuments.
     */
    public List<ElectronicPaymentClaimingDocument> getAvailableClaimingDocuments() {
        return availableClaimingDocuments;
    }
    /**
     * Sets the availableClaimingDocuments attribute value.
     * @param availableClaimingDocuments The availableClaimingDocuments to set.
     */
    public void setAvailableClaimingDocuments(List<ElectronicPaymentClaimingDocument> availableClaimingDocuments) {
        this.availableClaimingDocuments = availableClaimingDocuments;
    }
    /**
     * Gets the chosenElectronicPaymentClaimingDocumentCode attribute. 
     * @return Returns the chosenElectronicPaymentClaimingDocumentCode.
     */
    public String getChosenElectronicPaymentClaimingDocumentCode() {
        return chosenElectronicPaymentClaimingDocumentCode;
    }
    /**
     * Sets the chosenElectronicPaymentClaimingDocumentCode attribute value.
     * @param chosenElectronicPaymentClaimingDocumentCode The chosenElectronicPaymentClaimingDocumentCode to set.
     */
    public void setChosenElectronicPaymentClaimingDocumentCode(String chosenElectronicPaymentClaimingDocumentCode) {
        this.chosenElectronicPaymentClaimingDocumentCode = chosenElectronicPaymentClaimingDocumentCode;
    }
    /**
     * Gets the claims attribute. 
     * @return Returns the claims.
     */
    public List<ElectronicPaymentClaim> getClaims() {
        return claims;
    }
    /**
     * Returns the claim at the specified index in the list of claims.
     * @param i index of the claim to return
     * @return the claim at the index
     */
    public ElectronicPaymentClaim getClaim(int i) {
        while (claims.size() <= i) {
            claims.add(new ElectronicPaymentClaim());
        }
        return claims.get(i);
    }
    /**
     * Puts an ElectronicPaymentClaim record in the claims array at a specified point
     * @param claim the claim to add
     * @param i the index in the list to add the record at
     */
    public void setClaim(ElectronicPaymentClaim claim, int i) {
        while (claims.size() <= i) {
            claims.add(new ElectronicPaymentClaim());
        }
        claims.add(i, claim);
    }
    /**
     * Sets the claims attribute value.
     * @param claims The claims to set.
     */
    public void setClaims(List<ElectronicPaymentClaim> claims) {
        this.claims = claims;
    }

    /**
     * Gets the hasDocumentation attribute. 
     * @return Returns the hasDocumentation.
     */
    public String getHasDocumentation() {
        return hasDocumentation;
    }

    /**
     * Sets the hasDocumentation attribute value.
     * @param hasDocumentation The hasDocumentation to set.
     */
    public void setHasDocumentation(String hasDocumentation) {
        this.hasDocumentation = hasDocumentation;
    }
    
    /**
     * Returns a boolean whether the user has stated that documentation exists for the claims about to be made or not
     * @return true if has documentation, false otherwise
     */
    public boolean isProperlyDocumented() {
        return StringUtils.isNotBlank(this.hasDocumentation) && this.hasDocumentation.equals("Yep");
    }
    
    /**
     * Returns whether the current user has administrative powers for Electronic Funds Transfer or not
     * @return true if administrative powers exist, false otherwise
     */
    public boolean isAllowElectronicFundsTransferAdministration() {
        return SpringContext.getBean(ElectronicPaymentClaimingService.class).isElectronicPaymentAdministrator(GlobalVariables.getUserSession().getUniversalUser());
    }
    
    /**
     * @return the key to the EFT documentation message
     */
    public String getDocumentationMessageKey() {
        return KFSKeyConstants.ElectronicPaymentClaim.MESSAGE_EFT_CLAIMING_DOCUMENTATION;
    }
}
