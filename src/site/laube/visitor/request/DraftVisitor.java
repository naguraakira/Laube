package site.laube.visitor.request;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.RequestSystemAcceptor;
import site.laube.acceptor.request.DraftAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.dto.ActivityObjectDto;
import site.laube.dto.ApplicationFormDto;
import site.laube.dto.ApplicationObjectDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.model.ActivityObjectModel;
import site.laube.model.ApplicationFormModel;
import site.laube.model.ApplicationObjectModel;
import site.laube.model.LaubeModel;
import site.laube.modelinterface.ActivityObjectModelInterface;
import site.laube.modelinterface.ApplicationFormModelInterface;
import site.laube.modelinterface.ApplicationObjectModelInterface;
import site.laube.utility.LaubeUtility;
import site.laube.utility.SpecifiedValue;
import site.laube.visitor.RequestSystemVisitor;

/*
 * Copyright (c) 2016, Ryuta Miki All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class DraftVisitor extends RequestSystemVisitor {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(DraftVisitor.class);

	/**
	 * Do the application work.<br>
	 * @param requestSystemAcceptor request system acceptor
	 * @return ResultDto
	 * @exception LaubeException
	 */
	@SuppressWarnings("unchecked")
	public ResultDto visit(final RequestSystemAcceptor requestSystemAcceptor) throws LaubeException {

		log.debug("[workflowEngine] " + "visit start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "requestSystemAcceptor:" + requestSystemAcceptor);

		// create a return information.
		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(requestSystemAcceptor)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "visit end");
			return resultDto;
		}

		final DraftAcceptor draftAcceptor = (DraftAcceptor)requestSystemAcceptor;

		try{

			boolean isNull = isNull(draftAcceptor);

			if (isNull) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0001");
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			final String companyCode = draftAcceptor.getCompanyCode();
			      int applicationNumber = draftAcceptor.getApplicationNumber();
			final int reapplicationNumber = draftAcceptor.getReapplicationNumber();
			final String applicationFormCode = draftAcceptor.getApplicationFormCode();
			final String applyDate = draftAcceptor.getApplyDate();
			final String applyCompanyCode = draftAcceptor.getApplyCompanyCode();
			final String applyUnitCode = draftAcceptor.getApplyUnitCode();
			final String applyUserCode = draftAcceptor.getApplyUserCode();
			final String deputyApplyCompanyCode = draftAcceptor.getDeputyApplyCompanyCode();
			final String deputyApplyUnitCode = draftAcceptor.getDeputyApplyUnitCode();
			final String deputyApplyUserCode = draftAcceptor.getDeputyApplyUserCode();
			final int applicationStatus = SpecifiedValue.Draft;

			log.debug("[workflowEngine] " + "Find the application form master.");
			final ApplicationFormModelInterface applicationFormModelInterface = new ApplicationFormModel();
			resultDto = applicationFormModelInterface.findByApplicationFormCode(applyCompanyCode, applicationFormCode);

			if (!resultDto.isSuccess()) {
				log.error("[workflowEngine] " + "[resultDto] " + resultDto.toString());
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			ArrayList<LaubeDto> applicationFormDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

			List<ApprovalRouteInformationAcceptor> individualRoutes = draftAcceptor.getIndividualRoutes();
			List<ApprovalRouteInformationAcceptor> commonRoutes = draftAcceptor.getCommonRoutes();

			final boolean isIndividualRoutes = (individualRoutes == null)||(individualRoutes.size() == 0);
			final boolean isCommonRoutes = (commonRoutes == null)||(commonRoutes.size() == 0);

			ApplicationFormDto applicationFormDto = (ApplicationFormDto)applicationFormDtos.get(0);
			final boolean check1 = (applicationFormDto.getRouteFlag() == SpecifiedValue.NoIndividualRouteFlag) && (!isIndividualRoutes);
			final boolean check2 = (applicationFormDto.getRouteFlag() == SpecifiedValue.IndividualRouteFlag) && (isIndividualRoutes);
			final boolean check3 = isIndividualRoutes && isCommonRoutes;

			if (check1) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0002");
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			if (check2) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0003");
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			if (check3) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0007");
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			// Store individualRoute and commonRoutes in the same variable
			List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptor = new ArrayList<ApprovalRouteInformationAcceptor>();

			if (individualRoutes != null){
				for(ApprovalRouteInformationAcceptor individualRoute : individualRoutes) {
					approvalRouteInformationAcceptor.add(individualRoute);
				}
			}
			if (commonRoutes != null){
				for(ApprovalRouteInformationAcceptor commonRoute : commonRoutes) {
					approvalRouteInformationAcceptor.add(commonRoute);
				}
			}

			// Whether or not there is inconsistency in the route information check
			boolean isCheck = checkRoute(approvalRouteInformationAcceptor);

			if (!isCheck) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0009");
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.debug("[workflowEngine] " + "ApplyVisitor.visit() end");
				return resultDto;
			}

			final ApplicationObjectModelInterface applicationObjectModelInterface = new ApplicationObjectModel();

			if (applicationNumber != 0) {
				resultDto = applicationObjectModelInterface.find(draftAcceptor.getCompanyCode(), draftAcceptor.getApplicationNumber());
			}

			Object object = resultDto.getResultData();
			ArrayList<LaubeDto> applicationObjectDtos = (ArrayList<LaubeDto>)object;

			boolean isDraft = false;
			if ((applicationNumber == 0)||(applicationObjectDtos == null)) {
				isDraft = false;

			}else{

				if (((ApplicationObjectDto)applicationObjectDtos.get(0)).getApplicationStatus() == SpecifiedValue.Draft) {
					isDraft = true;

				}else{
					resultDto.setStatus(false);
					resultDto.setMessageId("E0010");
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}
			}

			// create Application Object
			ApplicationObjectDto applicationObjectDto = new ApplicationObjectDto();
			applicationObjectDto.setCompanyCode(companyCode);
			applicationObjectDto.setApplicationNumber(applicationNumber);
			applicationObjectDto.setReapplicationNumber(reapplicationNumber);
			applicationObjectDto.setApplicationFormCode(applicationFormCode);
			applicationObjectDto.setApplyDate(applyDate);
			applicationObjectDto.setApplyCompanyCode(applyCompanyCode);
			applicationObjectDto.setApplyUnitCode(applyUnitCode);
			applicationObjectDto.setApplyUserCode(applyUserCode);
			applicationObjectDto.setDeputyApplyCompanyCode(deputyApplyCompanyCode);
			applicationObjectDto.setDeputyApplyUnitCode(deputyApplyUnitCode);
			applicationObjectDto.setDeputyApplyUserCode(deputyApplyUserCode);
			applicationObjectDto.setApplicationStatus(applicationStatus);
			applicationObjectDto.setCreateUserId(applyUserCode);
			applicationObjectDto.setUpdateUserId(applyUserCode);

			if (isDraft){
				resultDto = applicationObjectModelInterface.update(applicationObjectDto);
			}else{
				resultDto = applicationObjectModelInterface.insert(applicationObjectDto);
			}

			applicationNumber = (int)(resultDto.getResultData());

			// create Activity Object
			List<ActivityObjectDto> activityObjectDtoList = copyToActivityDto(companyCode, applyUserCode, applicationNumber, approvalRouteInformationAcceptor);

			ActivityObjectModelInterface activityObjectModelInterface = new ActivityObjectModel();

			if (isDraft){
				resultDto = activityObjectModelInterface.delete(companyCode, applicationNumber);
				if (!resultDto.isSuccess()) {
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}
				resultDto = activityObjectModelInterface.insert(activityObjectDtoList);
				if (!resultDto.isSuccess()) {
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}
			}else{
				resultDto = activityObjectModelInterface.insert(activityObjectDtoList);
				if (!resultDto.isSuccess()) {
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}
			}

			// check activity status
			boolean isComplete = checkActivityStatus(activityObjectDtoList);

			if (isComplete){
				applicationObjectDto.setApplicationStatus(SpecifiedValue.Approved);
				resultDto = applicationObjectModelInterface.update(applicationObjectDto);
			}

			if (!resultDto.isSuccess()) {
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			LaubeModel.connection.commit();

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(applicationNumber);
			log.debug("[workflowEngine] " + "applicationNumber is " + applicationNumber);
			log.debug("[workflowEngine] " + "visit end");
			return resultDto;

		}catch(Exception e){
			log.error("[workflowEngine] " + "visit end");
			throw new LaubeException(e);

		}finally{
			try {
				LaubeModel.connection.close();
			} catch (SQLException e) {
				log.error("[workflowEngine] " + "visit end");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * check of routes.
	 * @param approvalRouteInformationAcceptors
	 * @return result
	 */
	@SuppressWarnings({ "nls", "static-method" })
	private final boolean checkRoute(final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors){

		log.debug("[workflowEngine] " + "checkRoute start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "approvalRouteInformationAcceptors:" + approvalRouteInformationAcceptors);

		boolean result = true;
		if (!LaubeUtility.isEmpty(approvalRouteInformationAcceptors)){
			for(ApprovalRouteInformationAcceptor route : approvalRouteInformationAcceptors) {
				if (!LaubeUtility.isEmpty(route)) {
					result = checkItem(approvalRouteInformationAcceptors, route);
					if (!result) {
						break;
					}
				}
			}
		}

		// check chain
		if (!LaubeUtility.isEmpty(approvalRouteInformationAcceptors)){

			ArrayList<String> keywords = new ArrayList<String>();

			for(ApprovalRouteInformationAcceptor route : approvalRouteInformationAcceptors) {
				if (!LaubeUtility.isEmpty(route)) {
					keywords.add(route.getPartyCode() + route.getNextPartyCode());
					log.debug("[workflowEngine] " + "keywords:" + keywords);
					if (!checkNextParty(approvalRouteInformationAcceptors, route.getNextPartyCode(), keywords)) {
						log.error("[workflowEngine] " + "error next party");
						log.error("[workflowEngine] " + "checkRoute end");
						return false;
					}else{
						log.debug("[workflowEngine] " + "initialize keywords");
						keywords = new ArrayList<String>();
					}
				}
			}
		}

		log.debug("[workflowEngine] " + "checkRoute end");
		return result;
	}

	/**
	 * check of item.
	 * @param approvalRouteInformationAcceptors
	 * @param target
	 * @return result
	 */
	@SuppressWarnings({ "nls", "static-method" })
	private final boolean checkItem(final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors, final ApprovalRouteInformationAcceptor target){

		log.debug("[workflowEngine] " + "checkItem start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "approvalRouteInformationAcceptors:" + approvalRouteInformationAcceptors);
		log.debug("[workflowEngine] " + "target:" + target);

		// check party code
		if (LaubeUtility.isBlank(target.getPartyCode())) {
			log.error("[workflowEngine] " + "error party code");
			log.error("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of party code connector
		boolean isUnspecified        = target.getPartyCodeConnector() == SpecifiedValue.Unspecified;
		boolean isLogicalSum         = target.getPartyCodeConnector() == SpecifiedValue.LogicalSum;
		boolean isLogicalProduct     = target.getPartyCodeConnector() == SpecifiedValue.LogicalProduct;
		final boolean isPartyCodeConnector = isUnspecified || isLogicalSum || isLogicalProduct;

		if (!isPartyCodeConnector) {
			log.error("[workflowEngine] " + "error party code connector");
			log.error("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of route type
		final boolean isIndividualRoute = target.getRouteType() == SpecifiedValue.IndividualRoute;
		final boolean isCommonRoute     = target.getRouteType() == SpecifiedValue.CommonRoute;
		final boolean isSpecial         = target.getRouteType() == SpecifiedValue.Special;
		final boolean isRouteType       = isIndividualRoute || isCommonRoute || isSpecial;

		if (!isRouteType) {
			log.error("[workflowEngine] " + "error route type");
			log.error("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of approval company code
		if (LaubeUtility.isBlank(target.getApprovalCompanyCode())) {
			log.error("[workflowEngine] " + "error approval company code");
			log.error("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of approval unit code
		if (LaubeUtility.isBlank(target.getApprovalUnitCode())) {
			log.error("[workflowEngine] " + "error approval unit code");
			log.error("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of approval user code
		if (LaubeUtility.isBlank(target.getApprovalUserCode())) {
			log.error("[workflowEngine] " + "error approval user code");
			log.error("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of deputy approval
		final boolean isDeputyApprovalCompanyCode = (target.getDeputyApprovalCompanyCode() != null) && (target.getDeputyApprovalCompanyCode().trim().length() > 0);
		final boolean isDeputyApprovalUnitCode    = (target.getDeputyApprovalUnitCode()    != null) && (target.getDeputyApprovalUnitCode().trim().length()    > 0);
		final boolean isDeputyApprovalUserCode    = (target.getDeputyApprovalUserCode()    != null) && (target.getDeputyApprovalUserCode().trim().length()    > 0);
		final boolean isDeputy = isDeputyApprovalCompanyCode && isDeputyApprovalUnitCode && isDeputyApprovalUserCode;
		final boolean isNotDeputy = !isDeputyApprovalCompanyCode && !isDeputyApprovalUnitCode && !isDeputyApprovalUserCode;
		if (!((isDeputy)||(isNotDeputy))) {
			log.error("[workflowEngine] " + "error deputy approval");
			log.error("[workflowEngine] " + "checkItem end");
			return false;
		}
		if (isDeputy) {
			if ((target.getDeputyApprovalComment() == null)||(target.getDeputyApprovalComment().trim().length() == 0)) {
				log.error("[workflowEngine] " + "error deputy comment");
				log.error("[workflowEngine] " + "checkItem end");
				return false;
			}
		}
		if (isNotDeputy) {
			if (target.getDeputyApprovalComment() != null) {
				log.error("[workflowEngine] " + "error deputy comment");
				log.error("[workflowEngine] " + "checkItem end");
				return false;
			}
		}

		// check of function
		final boolean isExamination           = target.getFunction() == SpecifiedValue.Examination;
		final boolean isFunctionConfirmation  = target.getFunction() == SpecifiedValue.FunctionConfirmation;
		final boolean isFunction       = isExamination || isFunctionConfirmation;
		if (!isFunction) {
			log.error("[workflowEngine] " + "error function");
			log.error("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check next party code
		if (LaubeUtility.isBlank(target.getNextPartyCode())) {
			log.error("[workflowEngine] " + "error next party code");
			log.error("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of party transit code
		if ((target.getPartyTransitCode() == null)||(target.getPartyTransitCode().trim().length() == 0)) {
			log.error("[workflowEngine] " + "error party transit code");
			log.error("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of party code connector
		isUnspecified        = target.getPartyTransitCodeConnector() == SpecifiedValue.Unspecified;
		isLogicalSum         = target.getPartyTransitCodeConnector() == SpecifiedValue.LogicalSum;
		isLogicalProduct     = target.getPartyTransitCodeConnector() == SpecifiedValue.LogicalProduct;
		final boolean isPartyTransitCodeConnector = isUnspecified || isLogicalSum || isLogicalProduct;
		if (!isPartyTransitCodeConnector) {
			log.error("[workflowEngine] " + "error party code connector");
			log.error("[workflowEngine] " + "checkItem end");
			return false;
		}

		return true;
	}

	/**
	 * check of next party.(recursive processing)
	 * @param approvalRouteInformationAcceptors
	 * @param nextPartyCode next party code
	 * @param keywords
	 * @return result
	 */
	@SuppressWarnings({ "nls", "static-method" })
	private final boolean checkNextParty(final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors, final String nextPartyCode, ArrayList<String> keywords){

		log.debug("[workflowEngine] " + "checkNextParty start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "approvalRouteInformationAcceptors:" + approvalRouteInformationAcceptors);
		log.debug("[workflowEngine] " + "nextPartyCode: " + nextPartyCode);
		log.debug("[workflowEngine] " + "keywords:" + keywords);

		for(ApprovalRouteInformationAcceptor route : approvalRouteInformationAcceptors) {
			// discover the next party code
			if (nextPartyCode.equals(route.getPartyCode())) {
				if (route.getNextPartyCode().equals(SpecifiedValue.END)) {
					log.debug("[workflowEngine] " + "success to find the final party");
					log.debug("[workflowEngine] " + "checkNextParty end");
					return true;
				}
				// if the next party code is known of the party error
				for(String keyword : keywords) {
					if ((route.getPartyCode() + route.getNextPartyCode()).equals(keyword)) {
						log.error("[workflowEngine] " + "already add party code. party code is [" + route.getPartyCode() + route.getNextPartyCode() + "]");
						log.error("[workflowEngine] " + "checkNextParty end");
						return false;
					}
				}
				keywords.add(route.getPartyCode() + route.getNextPartyCode());

				boolean result = checkNextParty(approvalRouteInformationAcceptors, route.getNextPartyCode(), keywords);
				if (!result) {
					log.error("[workflowEngine] " + "error checkNextParty");
					log.error("[workflowEngine] " + "checkNextParty end");
					return false;
				}else{
					keywords = new ArrayList<String>();
				}
			}
		}
		log.debug("[workflowEngine] " + "checkNextParty end");
		return true;
	}

	/**
	 * check of essential items.
	 */
	@SuppressWarnings({ "nls", "static-method" })
	private final boolean isNull(final DraftAcceptor draftAcceptor){

		log.debug("[workflowEngine] " + "isNull start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "applyAcceptor:" + draftAcceptor);

		if (LaubeUtility.isBlank(draftAcceptor.getCompanyCode())) {
			log.debug("[workflowEngine] " + "companyCode : null");
			log.debug("[workflowEngine] " + "LaubeApplyVisitor.isNull() end [return]:true");
			return true;
		}

		if (LaubeUtility.isBlank(draftAcceptor.getApplicationFormCode())) {
			log.debug("[workflowEngine] " + "applicationFormCode : null");
			log.debug("[workflowEngine] " + "LaubeApplyVisitor.isNull() end [return]:true");
			return true;
		}

		if (LaubeUtility.isBlank(draftAcceptor.getApplyDate())) {
			log.debug("[workflowEngine] " + "applyDate : null");
			log.debug("[workflowEngine] " + "LaubeApplyVisitor.isNull() end [return]:true");
			return true;
		}

		if (LaubeUtility.isBlank(draftAcceptor.getApplyCompanyCode())) {
			log.debug("[workflowEngine] " + "applyCompanyCode : null");
			log.debug("[workflowEngine] " + "LaubeApplyVisitor.isNull() end [return]:true");
			return true;
		}

		if (LaubeUtility.isBlank(draftAcceptor.getApplyUnitCode())) {
			log.debug("[workflowEngine] " + "applyUnitCode : null");
			log.debug("[workflowEngine] " + "LaubeApplyVisitor.isNull() end [return]:true");
			return true;
		}

		if (LaubeUtility.isBlank(draftAcceptor.getApplyUserCode())) {
			log.debug("[workflowEngine] " + "applyUserCode : null");
			log.debug("[workflowEngine] " + "LaubeApplyVisitor.isNull() end [return]:true");
			return true;
		}

		log.debug("[workflowEngine] " + "isNull end");
		return false;
	}

	/**
	 * copy the value of the Acceptor in Dto.<br>
	 * @param companyCode
	 * @param applyUserCode
	 * @param applicationNumber
	 * @param approvalRouteInformationAcceptor
	 * @return List<ActivityObjectDto>
	 * @exception LaubeException
	 */
	@SuppressWarnings({ "nls", "static-method" })
	private final List<ActivityObjectDto> copyToActivityDto(
			final String companyCode,
			final String applyUserCode,
			final int applicationNumber,
			final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptor
			) throws LaubeException {

		log.debug("[workflowEngine] " + "copyToActivityDto start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "companyCode:" + companyCode);
		log.debug("[workflowEngine] " + "applyUserCode:" + applyUserCode);
		log.debug("[workflowEngine] " + "applicationNumber:" + applicationNumber);
		log.debug("[workflowEngine] " + "approvalRouteInformationAcceptor:" + approvalRouteInformationAcceptor);

		List<ActivityObjectDto> activityObjectDtos = new ArrayList<ActivityObjectDto>();

		int row = 0;

		if (!LaubeUtility.isEmpty(approvalRouteInformationAcceptor)){
			for(ApprovalRouteInformationAcceptor route : approvalRouteInformationAcceptor) {

				ActivityObjectDto activityObjectDto = new ActivityObjectDto();

				activityObjectDto.setCompanyCode(companyCode);
				activityObjectDto.setApplicationNumber(applicationNumber);
				int activityObjectCode = ++row;
				activityObjectDto.setActivityObjectCode(activityObjectCode);
				activityObjectDto.setPartyCode(route.getPartyCode());
				activityObjectDto.setPartyCodeConnector(route.getPartyCodeConnector());
				activityObjectDto.setRouteType(route.getRouteType());
				activityObjectDto.setApprovalCompanyCode(route.getApprovalCompanyCode());
				activityObjectDto.setApprovalUnitCode(route.getApprovalUnitCode());
				activityObjectDto.setApprovalUserCode(route.getApprovalUserCode());
				activityObjectDto.setDeputyApprovalCompanyCode(route.getDeputyApprovalCompanyCode());
				activityObjectDto.setDeputyApprovalUserCode(route.getDeputyApprovalUserCode());
				activityObjectDto.setDeputyApprovalComment(route.getDeputyApprovalComment());
				activityObjectDto.setFunction(route.getFunction());
				activityObjectDto.setNextPartyCode(route.getNextPartyCode());
				activityObjectDto.setPartyTransitCode(route.getPartyTransitCode());
				activityObjectDto.setPartyTransitCodeConnector(route.getPartyTransitCodeConnector());
				activityObjectDto.setReachingDate(null);
				activityObjectDto.setProcessDate(null);
				activityObjectDto.setActivityStatus(SpecifiedValue.Authorizer_Untreated);
				activityObjectDto.setApprovalComment(null);
				activityObjectDto.setCreateUserId(applyUserCode);
				activityObjectDto.setUpdateUserId(applyUserCode);
				activityObjectDtos.add(activityObjectDto);
			}
		}
		return activityObjectDtos;
	}

	/**
	 * check activity status.<br>
	 * @param activityObjectDtoList
	 * @return boolean
	 */
	@SuppressWarnings({ "nls", "static-method" })
	private final boolean checkActivityStatus(final List<ActivityObjectDto> activityObjectDtoList) {

		log.debug("[workflowEngine] " + "checkActivityStatus start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "activityObjectDtoList:" + activityObjectDtoList);

		boolean result = true;

		for(ActivityObjectDto activityObjectDto : activityObjectDtoList) {
			if (activityObjectDto == null) {
				return false;
			}

			if (activityObjectDto.getActivityStatus() == SpecifiedValue.Authorizer_Untreated) {
				return false;
			}

			if (activityObjectDto.getActivityStatus() == SpecifiedValue.Arrival) {
				if (activityObjectDto.getFunction() != SpecifiedValue.FunctionConfirmation) {
					return false;
				}
			}

			if (activityObjectDto.getActivityStatus() == SpecifiedValue.Hold) {
				return false;
			}
		}
		return result;
	}
}
