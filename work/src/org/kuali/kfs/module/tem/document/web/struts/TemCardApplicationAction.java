/*
 * Copyright 2012 The Kuali Foundation.
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
package org.kuali.kfs.module.tem.document.web.struts;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.module.tem.TemKeyConstants;
import org.kuali.kfs.module.tem.TemWorkflowConstants;
import org.kuali.kfs.module.tem.businessobject.TEMProfile;
import org.kuali.kfs.module.tem.document.CardApplicationDocument;
import org.kuali.kfs.module.tem.document.TemCTSCardApplicationDocument;
import org.kuali.kfs.module.tem.document.TemCorporateCardApplicationDocument;
import org.kuali.kfs.module.tem.document.service.TravelDocumentService;
import org.kuali.kfs.module.tem.document.web.bean.TravelMvcWrapperBean;
import org.kuali.kfs.module.tem.service.TemProfileService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.util.RiceConstants;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.exception.AuthorizationException;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.web.format.DateFormatter;
import org.kuali.rice.kns.web.struts.action.KualiTransactionalDocumentActionBase;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class TemCardApplicationAction extends KualiTransactionalDocumentActionBase {
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.docHandler(mapping, form, request, response);
        
        
        Person currentUser = GlobalVariables.getUserSession().getPerson();
        TemCardApplicationForm applicationForm = (TemCardApplicationForm)form;
        TEMProfile profile = null;
        
        CardApplicationDocument document = (CardApplicationDocument) applicationForm.getDocument();
        String command = applicationForm.getCommand();
        if (!StringUtils.equals(KEWConstants.INITIATE_COMMAND,command)) {
            profile = document.getTemProfile();
            //If not the user's profile, check if they are the FO or Travel Manager.
            if (!currentUser.getPrincipalId().equals(profile.getPrincipalId())){
                boolean canView = false;
                if (applicationForm.isTravelManager()){
                    canView = true;
                }
                else if (applicationForm.isFiscalOfficer()){
                    canView = true;
                }
                
                if (!canView){
                    throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), "view",this.getClass().getSimpleName());
                }
                
            }
        }
        else{
            profile = SpringContext.getBean(TemProfileService.class).findTemProfileByPrincipalId(currentUser.getPrincipalId());
            if (profile == null){
                applicationForm.setEmptyProfile(true);
                return mapping.findForward("cardApplicationError");
            }
            else{
                if (StringUtils.isEmpty(profile.getDefaultAccount())){
                    applicationForm.setEmptyAccount(true);
                    return mapping.findForward("cardApplicationError");
                }
            }
            
            //Find any pre-existing applications
            Map<String,Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("initiatorWorkflowId", currentUser.getPrincipalId());
            List<DocumentRouteHeaderValue> documentRouteHeaderValueList = (List<DocumentRouteHeaderValue>) getBusinessObjectService().findMatching(DocumentRouteHeaderValue.class, fieldValues);
            String type = "Corporate Card Application";
            if (document instanceof TemCTSCardApplicationDocument){
                type = "CTS Card Application";
            }
            
            for (DocumentRouteHeaderValue value : documentRouteHeaderValueList){
                if (value.getDocTitle() != null && value.getDocTitle().contains(type)){
                    if (value.getDocRouteStatus().equals(KEWConstants.ROUTE_HEADER_FINAL_CD)
                            || value.getDocRouteStatus().equals(KEWConstants.ROUTE_HEADER_APPROVED_CD)
                            || value.getDocRouteStatus().equals(KEWConstants.ROUTE_HEADER_PROCESSED_CD)
                            || value.getDocRouteStatus().equals(KEWConstants.ROUTE_HEADER_ENROUTE_CD)){
                        applicationForm.setMultipleApplications(true);
                        return mapping.findForward("cardApplicationError");
                    }
                }
            }
            
            
            document.setTemProfile(profile);
            document.setTemProfileId(profile.getProfileId());
            profile.getTravelerTypeCode(); 
            applicationForm.setInitiator(true);
        }
        
        return forward;
    }

    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#approve(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TemCardApplicationForm applicationForm = (TemCardApplicationForm)form;
        CardApplicationDocument document = (CardApplicationDocument) applicationForm.getDocument();
        if (document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getAppDocStatus().equals(TemWorkflowConstants.RouteNodeNames.FISCAL_OFFICER_REVIEW)){
            if (document instanceof TemCorporateCardApplicationDocument){
                if (!((TemCorporateCardApplicationDocument)document).isDepartmentHeadAgreement()){
                    GlobalVariables.getMessageMap().putError("departmentHeadAgreement", TemKeyConstants.ERROR_TEM_CARD_APP_NO_AGREEMENT, "Department Head");
                }
            }
            
        }
        Person currentUser = GlobalVariables.getUserSession().getPerson();
        if (SpringContext.getBean(TravelDocumentService.class).isTravelManager(currentUser)
                && document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getAppDocStatus().equals(TemWorkflowConstants.RouteNodeNames.APPLIED_TO_BANK)){
            document.sendAcknowledgement();
            document.approvedByBank();
        }
        return super.approve(mapping, form, request, response);
    }

    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#disapprove(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward disapprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TemCardApplicationForm applicationForm = (TemCardApplicationForm)form;
        CardApplicationDocument document = (CardApplicationDocument) applicationForm.getDocument();
        Person currentUser = GlobalVariables.getUserSession().getPerson();
        if (SpringContext.getBean(TravelDocumentService.class).isTravelManager(currentUser)
                && document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getAppDocStatus().equals(TemWorkflowConstants.RouteNodeNames.APPLIED_TO_BANK)){
            document.sendAcknowledgement();
        }
        return super.disapprove(mapping, form, request, response);
    }

    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#route(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TemCardApplicationForm applicationForm = (TemCardApplicationForm)form;
        CardApplicationDocument document = (CardApplicationDocument) applicationForm.getDocument();

        if (applicationForm.getDocument().getDocumentHeader().getWorkflowDocument().stateIsInitiated()){
            if (!document.isUserAgreement()){
                GlobalVariables.getMessageMap().putError("userAgreement", TemKeyConstants.ERROR_TEM_CARD_APP_NO_AGREEMENT, "User");
            }
            if (StringUtils.isEmpty(document.getDocumentHeader().getDocumentDescription())){
                GlobalVariables.getMessageMap().putError("document.documentHeader.documentDescription", RiceKeyConstants.ERROR_REQUIRED, "Description");
            }
        }
        
        return super.route(mapping, form, request, response);
    }

    public ActionForward applyToBank(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TemCardApplicationForm applicationForm = (TemCardApplicationForm)form;
        CardApplicationDocument document = (CardApplicationDocument) applicationForm.getDocument();
        Person currentUser = GlobalVariables.getUserSession().getPerson();
        if (!SpringContext.getBean(TravelDocumentService.class).isTravelManager(currentUser)){
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), "Apply To Bank",this.getClass().getSimpleName());
        }
        document.applyToBank();
        document.getDocumentHeader().getWorkflowDocument().getRouteHeader().setAppDocStatus(TemWorkflowConstants.RouteNodeNames.APPLIED_TO_BANK);
        document.saveAppDocStatus();
        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }
    
    
    public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TemCardApplicationForm applicationForm = (TemCardApplicationForm)form;
        CardApplicationDocument document = (CardApplicationDocument) applicationForm.getDocument();
        Person currentUser = GlobalVariables.getUserSession().getPerson();
        if (!SpringContext.getBean(TravelDocumentService.class).isTravelManager(currentUser)){
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), "Submit",this.getClass().getSimpleName());
        }
        document.getDocumentHeader().getWorkflowDocument().getRouteHeader().setAppDocStatus(TemWorkflowConstants.RouteNodeNames.APPLY_TO_BANK);
        document.saveAppDocStatus();
        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }
    
    protected KualiConfigurationService getKualiConfigurationService() {
        return SpringContext.getBean(KualiConfigurationService.class);
    }
    
    protected DocumentService getDocumentService() {
        return SpringContext.getBean(DocumentService.class);
    }
}
