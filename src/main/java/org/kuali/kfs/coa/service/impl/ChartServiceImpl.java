/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 * 
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.coa.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.Organization;
import org.kuali.kfs.coa.service.ChartService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.sys.identity.KfsKimAttributes;
import org.kuali.kfs.sys.service.NonTransactional;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.cache.annotation.Cacheable;

/**
 * This class is the service implementation for the Chart structure. This is the default, Kuali delivered implementation.
 */
@NonTransactional
public class ChartServiceImpl implements ChartService {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ChartServiceImpl.class);

    protected BusinessObjectService businessObjectService;
    protected RoleService roleService;
    protected PersonService personService;
    protected ParameterService parameterService;

    /**
     * @see org.kuali.kfs.coa.service.ChartService#getByPrimaryId(java.lang.String)
     */
    @Override
    @Cacheable(value=Chart.CACHE_NAME,key="'chartOfAccountsCode='+#p0")
    public Chart getByPrimaryId(String chartOfAccountsCode) {
        return businessObjectService.findBySinglePrimaryKey(Chart.class, chartOfAccountsCode);
    }

    /**
     * @see org.kuali.kfs.coa.service.ChartService#getUniversityChart()
     */
    @Override
    @Cacheable(value=Chart.CACHE_NAME,key="'UniversityChart'")
    public Chart getUniversityChart() {
        // 1. find the organization with the type which reports to itself
        String organizationReportsToSelfParameterValue = parameterService.getParameterValueAsString(Organization.class, KFSConstants.ChartApcParms.ORG_MUST_REPORT_TO_SELF_ORG_TYPES);

        Map<String,String> orgCriteria = new HashMap<String, String>(2);
        orgCriteria.put(KFSPropertyConstants.ORGANIZATION_TYPE_CODE, organizationReportsToSelfParameterValue);
        orgCriteria.put(KFSPropertyConstants.ACTIVE, KFSConstants.ACTIVE_INDICATOR);
        Collection<Organization> orgs = businessObjectService.findMatching(Organization.class, orgCriteria);
        if ( orgs != null && !orgs.isEmpty() ) {
            return getByPrimaryId(orgs.iterator().next().getChartOfAccountsCode());
        }

        return null;
    }

    /**
     * @see org.kuali.kfs.coa.service.ChartService#getAllChartCodes()
     */
    @Override
    @Cacheable(value=Chart.CACHE_NAME,key="'AllChartCodes'")
    public List<String> getAllChartCodes() {
        Collection<Chart> charts = businessObjectService.findAllOrderBy(Chart.class, KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, true);
        return getChartCodes(charts);
    }

    /**
     * @see org.kuali.kfs.coa.service.ChartService#getAllActiveChartCodes()
     */
    @Override
    @Cacheable(value=Chart.CACHE_NAME,key="'AllActiveChartCodes'")
    public List<String> getAllActiveChartCodes() {
        return getChartCodes(getAllActiveCharts());
    }

    @Override
    @Cacheable(value=Chart.CACHE_NAME,key="'AllActiveCharts'")
    public Collection<Chart> getAllActiveCharts() {
        return businessObjectService.findMatchingOrderBy(Chart.class,
                Collections.singletonMap(KFSPropertyConstants.ACTIVE, true),
                KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, true);
    }

    /**
     * @see org.kuali.module.chart.service.getReportsToHierarchy()
     */
    @Override
    @Cacheable(value=Chart.CACHE_NAME,key="'ReportsToHierarchy'")
    public Map<String, String> getReportsToHierarchy() {
        Map<String, String> reportsToHierarchy = new HashMap<String, String>();

        for ( String chartCode : getAllChartCodes() ) {
            Chart chart = businessObjectService.findBySinglePrimaryKey(Chart.class, chartCode);

            if (LOG.isDebugEnabled()) {
                LOG.debug("adding " + chart.getChartOfAccountsCode() + "-->" + chart.getReportsToChartOfAccountsCode());
            }
            reportsToHierarchy.put(chart.getChartOfAccountsCode(), chart.getReportsToChartOfAccountsCode());
        }

        return reportsToHierarchy;
    }

    @Override
    @Cacheable(value=Chart.CACHE_NAME,key="'{isParentChart?}'+#p0+'-->'+#p1")
    public boolean isParentChart(String potentialChildChartCode, String potentialParentChartCode) {
        if ((potentialChildChartCode == null) || (potentialParentChartCode == null)) {
            throw new IllegalArgumentException("The isParentChartCode method requires a non-null potentialChildChartCode and potentialParentChartCode");
        }
        Chart thisChart = getByPrimaryId(potentialChildChartCode);
        if ((thisChart == null) || StringUtils.isBlank(thisChart.getChartOfAccountsCode())) {
            throw new IllegalArgumentException("The isParentChartCode method requires a valid potentialChildChartCode");
        }
        if (thisChart.getCode().equals(thisChart.getReportsToChartOfAccountsCode())) {
            return false;
        }
        else if (potentialParentChartCode.equals(thisChart.getReportsToChartOfAccountsCode())) {
            return true;
        }
        else {
            return isParentChart(thisChart.getReportsToChartOfAccountsCode(), potentialParentChartCode);
        }
    }

    @Override
    public Person getChartManager(String chartOfAccountsCode) {
        String chartManagerId = null;
        Person chartManager = null;

        Map<String,String> qualification = new HashMap<String,String>();
        qualification.put(KfsKimAttributes.CHART_OF_ACCOUNTS_CODE, chartOfAccountsCode);

        Collection<String> chartManagerList = getRoleService().getRoleMemberPrincipalIds(KFSConstants.CoreModuleNamespaces.KFS, KFSConstants.SysKimApiConstants.CHART_MANAGER_KIM_ROLE_NAME, qualification);

        if (!chartManagerList.isEmpty()) {
            chartManagerId = chartManagerList.iterator().next();
        }

        if (chartManagerId != null) {
            chartManager = getPersonService().getPerson(chartManagerId);
        }

        return chartManager;
    }

    protected List<String> getChartCodes(Collection<Chart> charts) {
        List<String> chartCodes = new ArrayList<String>(charts.size());
        for (Chart chart : charts) {
            chartCodes.add(chart.getChartOfAccountsCode());
        }
        return chartCodes;
    }

    protected RoleService getRoleService() {
        if ( roleService == null ) {
            roleService = KimApiServiceLocator.getRoleService();
        }
        return roleService;
    }
    protected PersonService getPersonService() {
        if (personService == null) {
            personService = SpringContext.getBean(PersonService.class);
        }
        return personService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
