/*
 * Copyright 2007 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kfs.module.endow.fixture;

import java.sql.Date;

import org.kuali.kfs.module.endow.businessobject.KEMID;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.service.BusinessObjectService;

public enum KemIdFixture {
    // Security Record FIXTURE
    CLOSED_KEMID_RECORD("TESTKEMID", // kemid
            "Test Kemid for Unit test", // shortTitle
            "Long Title for Kemid", // longTitle
            Date.valueOf("2006-02-01"), // dateOpened
            Date.valueOf("2006-02-01"), // dateEstablished
            "046", // typeCode
            "MR", // purposeCode
            "TRST", // responsibleAdminCode
            "NTRAN", // transactionRestrictionCode
            new Integer(1), // cashSweepModelId
            false, // dormantIndicator
            true, // close
            "038B011179", // closedToKEMID
            "T", // closeCode
            "TRU", // incomeRestrictionCode
            "TRU" // principalRestrictionCode
    ),

    OPEN_KEMID_RECORD("TESTKEMID", // kemid
            "Test Kemid for Unit test", // shortTitle
            "Long Title for Kemid", // longTitle
            Date.valueOf("2006-02-01"), // dateOpened
            Date.valueOf("2006-02-01"), // dateEstablished
            "046", // typeCode
            "MR", // purposeCode
            "TRST", // responsibleAdminCode
            "NTRAN", // transactionRestrictionCode
            new Integer(1), // cashSweepModelId
            false, // dormantIndicator
            false, // close
            "", // closedToKEMID
            "", // closeCode
            "TRU", // incomeRestrictionCode
            "TRU" // principalRestrictionCode
    ),

    ALLOW_TRAN_KEMID_RECORD("TESTKEMID", // kemid
            "Test Kemid for Unit test", // shortTitle
            "Long Title for Kemid", // longTitle
            Date.valueOf("2006-02-01"), // dateOpened
            Date.valueOf("2006-02-01"), // dateEstablished
            "046", // typeCode
            "MR", // purposeCode
            "TRST", // responsibleAdminCode
            "NONE", // transactionRestrictionCode
            new Integer(1), // cashSweepModelId
            false, // dormantIndicator
            false, // close
            "", // closedToKEMID
            "", // closeCode
            "TRU", // incomeRestrictionCode
            "TRU" // principalRestrictionCode
    ),

    NO_TRAN_KEMID_RECORD("TESTKEMID", // kemid
            "Test Kemid for Unit test", // shortTitle
            "Long Title for Kemid", // longTitle
            Date.valueOf("2006-02-01"), // dateOpened
            Date.valueOf("2006-02-01"), // dateEstablished
            "046", // typeCode
            "MR", // purposeCode
            "TRST", // responsibleAdminCode
            "NONE", // transactionRestrictionCode
            new Integer(1), // cashSweepModelId
            false, // dormantIndicator
            false, // close
            "", // closedToKEMID
            "", // closeCode
            "TRU", // incomeRestrictionCode
            "TRU" // principalRestrictionCode
    );

    public final String kemid;
    public final String shortTitle;
    public final String longTitle;
    public final Date dateOpened;
    public final Date dateEstablished;
    public final String typeCode;
    public final String purposeCode;
    public final String responsibleAdminCode;
    public final String transactionRestrictionCode;
    public final Integer cashSweepModelId;
    public final boolean dormantIndicator;
    public final boolean close;
    public final String closedToKEMID;
    public final String closeCode;
    public final String incomeRestrictionCode;
    public final String principalRestrictionCode;

    private KemIdFixture(String kemid, String shortTitle, String longTitle, Date dateOpened, Date dateEstablished, String typeCode, String purposeCode, String responsibleAdminCode, String transactionRestrictionCode, Integer cashSweepModelId, boolean dormantIndicator, boolean close, String closedToKEMID, String closeCode, String incomeRestrictionCode, String principalRestrictionCode) {
        this.kemid = kemid;
        this.shortTitle = shortTitle;
        this.longTitle = longTitle;
        this.dateOpened = dateOpened;
        this.dateEstablished = dateEstablished;
        this.typeCode = typeCode;
        this.purposeCode = purposeCode;
        this.responsibleAdminCode = responsibleAdminCode;
        this.transactionRestrictionCode = transactionRestrictionCode;
        this.cashSweepModelId = cashSweepModelId;
        this.dormantIndicator = dormantIndicator;
        this.close = close;
        this.closedToKEMID = closedToKEMID;
        this.closeCode = closeCode;
        this.incomeRestrictionCode = incomeRestrictionCode;
        this.principalRestrictionCode = principalRestrictionCode;
    }

    /**
     * This method creates a KEMID record and saves it to table
     * 
     * @return kemid record
     */
    public KEMID createKemidRecord() {
        KEMID kemidRecord = new KEMID();
        kemidRecord.setKemid(this.kemid);
        kemidRecord.setShortTitle(this.shortTitle);
        kemidRecord.setLongTitle(this.longTitle);
        kemidRecord.setDateOpened(this.dateOpened);
        kemidRecord.setDateEstablished(this.dateEstablished);
        kemidRecord.setTypeCode(this.typeCode);
        kemidRecord.setPurposeCode(this.purposeCode);
        kemidRecord.setResponsibleAdminCode(this.responsibleAdminCode);
        kemidRecord.setTransactionRestrictionCode(this.transactionRestrictionCode);
        kemidRecord.setCashSweepModelId(this.cashSweepModelId);
        kemidRecord.setDormantIndicator(this.dormantIndicator);
        kemidRecord.setClose(this.close);
        kemidRecord.setClosedToKEMID(this.closedToKEMID);
        kemidRecord.setCloseCode(this.closeCode);
        kemidRecord.setIncomeRestrictionCode(this.incomeRestrictionCode);
        kemidRecord.setPrincipalRestrictionCode(this.principalRestrictionCode);

        kemidRecord.refreshNonUpdateableReferences();
        kemidRecord.refreshReferenceObject("typeRestrictionCodeForPrincipalRestrictionCode");

        saveKemidRecord(kemidRecord);
        
        return kemidRecord;
    }
    
    /**
     * This method creates a KEMID record and saves it to table
     * @return kemid record
     */
    public KEMID createKemidRecord(String kemid, String shortTitle, String longTitle,
                                   Date dateOpened, Date dateEstablished, String typeCode,
                                   String purposeCode, String responsibleAdminCode,
                                   String transactionRestrictionCode, Integer cashSweepModelId,
                                   boolean dormantIndicator, boolean close,
                                   String closedToKEMID, String closeCode,
                                   String incomeRestrictionCode, String principalRestrictionCode) {
        KEMID kemidRecord = new KEMID();
        kemidRecord.setKemid(kemid);
        kemidRecord.setShortTitle(shortTitle);
        kemidRecord.setLongTitle(longTitle);
        kemidRecord.setDateOpened(dateOpened);
        kemidRecord.setDateEstablished(dateEstablished);
        kemidRecord.setTypeCode(typeCode);
        kemidRecord.setPurposeCode(purposeCode);
        kemidRecord.setResponsibleAdminCode(responsibleAdminCode);
        kemidRecord.setTransactionRestrictionCode(transactionRestrictionCode);
        kemidRecord.setCashSweepModelId(cashSweepModelId);
        kemidRecord.setDormantIndicator(dormantIndicator);
        kemidRecord.setClose(close);
        kemidRecord.setClosedToKEMID(closedToKEMID);
        kemidRecord.setCloseCode(closeCode);
        kemidRecord.setIncomeRestrictionCode(incomeRestrictionCode);
        kemidRecord.setPrincipalRestrictionCode(principalRestrictionCode);

        kemidRecord.refreshNonUpdateableReferences();
        kemidRecord.refreshReferenceObject("typeRestrictionCodeForPrincipalRestrictionCode");

        saveKemidRecord(kemidRecord);
        
        return kemidRecord;
    }
    
    /**
     * Method to save the business object....
     */
    private void saveKemidRecord(KEMID kemidRecord) {
          BusinessObjectService businessObjectService = SpringContext.getBean(BusinessObjectService.class);
        businessObjectService.save(kemidRecord);
    }
}
