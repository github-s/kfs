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
package org.kuali.kfs.document;

/**
 * A set of methods that KFS Documents which plan to handle ElectronicPaymentClaim claims should implement
 */
public interface ElectronicPaymentClaiming {
    /**
     * A method which is called when a document is disapproved or canceled, which removes the claim
     * the document had on any ElectronicPaymentClaim records.
     */
    public abstract void declaimElectronicPaymentClaims();
}
