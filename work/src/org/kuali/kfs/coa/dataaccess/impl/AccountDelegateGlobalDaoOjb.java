/*
 * Copyright 2009 The Kuali Foundation.
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
package org.kuali.kfs.coa.dataaccess.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kfs.coa.dataaccess.AccountDelegateGlobalDao;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.document.MaintenanceLock;
import org.kuali.rice.kns.util.KNSPropertyConstants;

/**
 * This class...
 */
public class AccountDelegateGlobalDaoOjb extends PlatformAwareDaoBaseOjb implements AccountDelegateGlobalDao {

    public String getLockingDocumentNumber(String lockingRepresentation, String documentNumber) {
        String lockingDocNumber = "";

        lockingRepresentation = convertForLikeCriteria(lockingRepresentation);
        
        // build the query criteria
        Criteria criteria = new Criteria();
        criteria.addLike("lockingRepresentation", lockingRepresentation);

        // if a docHeaderId is specified, then it will be excluded from the
        // locking representation test.
        if (StringUtils.isNotBlank(documentNumber)) {
            criteria.addNotEqualTo(KNSPropertyConstants.DOCUMENT_NUMBER, documentNumber);
        }

        // attempt to retrieve a document based off this criteria
        MaintenanceLock maintenanceLock = (MaintenanceLock) getPersistenceBrokerTemplate().getObjectByQuery(QueryFactory.newQuery(MaintenanceLock.class, criteria));

        // if a document was found, then there's already one out there pending, and
        // we consider it 'locked' and we return the docnumber.
        if (maintenanceLock != null) {
            lockingDocNumber = maintenanceLock.getDocumentNumber();
        }
        return lockingDocNumber;
    }
    
    private String convertForLikeCriteria(String lockingRepresentation) {
        //org.kuali.kfs.coa.businessobject.AccountDelegate!!chartOfAccountsCode^^BL::accountNumber^^1031400::financialDocumentTypeCode^^IB::accountsDelegatePrmrtIndicator^^true
        String[] values = StringUtils.split(lockingRepresentation, "::");
        StringBuilder sb = new StringBuilder();
        for (String val : values) {
            if (val.startsWith("financialDocumentTypeCode")) {
                String[] meh = StringUtils.split(val, "^^");
                meh[1] = "%";
                val = meh[0] +"^^"+meh[1];
                sb.append(val);
                break;
            } else {
                sb.append(val+"::");
            }
        }
        
        return sb.toString();
    }
}
