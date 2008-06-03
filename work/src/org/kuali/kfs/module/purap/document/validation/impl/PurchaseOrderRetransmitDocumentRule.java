/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.module.purap.rules;

import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.exceptions.UserNotFoundException;
import org.kuali.core.exceptions.ValidationException;
import org.kuali.core.rule.event.ApproveDocumentEvent;
import org.kuali.core.rules.TransactionalDocumentRuleBase;
import org.kuali.core.service.UniversalUserService;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.service.ParameterService;
import org.kuali.kfs.service.impl.ParameterConstants;
import org.kuali.module.purap.PurapParameterConstants;
import org.kuali.module.purap.document.PurchaseOrderDocument;
import org.kuali.module.purap.service.PurchaseOrderService;

/**
 * Rules for Purchase Order Retransmit document creation. Should not extend <code>PurchaseOrderDocumentRule</code>, since it does
 * not allow the purchase order to be edited, nor should it create GL entries.
 */
public class PurchaseOrderRetransmitDocumentRule extends TransactionalDocumentRuleBase {

    /**
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean isValid = true;
        PurchaseOrderDocument porDocument = (PurchaseOrderDocument) document;
        return isValid &= processValidation(porDocument);
    }

    /**
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean isValid = true;
        PurchaseOrderDocument porDocument = (PurchaseOrderDocument) document;
        return isValid &= processValidation(porDocument);
    }

    /**
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.rule.event.ApproveDocumentEvent)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(ApproveDocumentEvent approveEvent) {
        boolean isValid = true;
        PurchaseOrderDocument porDocument = (PurchaseOrderDocument) approveEvent.getDocument();
        return isValid;
    }

    /**
     * Central method to control the processing of rule checks. Checks that the purchase order document is not null, and that the
     * user is in the purchasing workgroup.
     * 
     * @param document A PurchaseOrderDocument.
     * @return True if the document passes all the validations.
     */
    private boolean processValidation(PurchaseOrderDocument document) {
        boolean valid = true;

        // Check that the PO is not null.
        if (ObjectUtils.isNull(document)) {
            throw new ValidationException("Purchase Order Retransmit document was null on validation.");
        }
        else {
            if (!document.getPurchaseOrderAutomaticIndicator()) {
                // Check that the user is in purchasing workgroup.
                valid &= SpringContext.getBean(PurchaseOrderService.class).isPurchasingUser(document, "retransmit");
            }
        }

        return valid;
    }
}
