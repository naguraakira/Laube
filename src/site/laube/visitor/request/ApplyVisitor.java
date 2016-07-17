package site.laube.visitor.request;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.RequestSystemAcceptor;
import site.laube.acceptor.request.ApplyAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.dto.ActivityObjectDto;
import site.laube.dto.ApplicationFormDto;
import site.laube.dto.ApplicationObjectDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.model.ActivityObjectModel;
import site.laube.model.ApplicationObjectModel;
import site.laube.model.LaubeModel;
import site.laube.modelinterface.ActivityObjectModelInterface;
import site.laube.modelinterface.ApplicationObjectModelInterface;
import site.laube.utility.LaubeProperties;
import site.laube.utility.LaubeUtility;
import site.laube.utility.SpecifiedValue;
import site.laube.visitor.RequestSystemVisitor;
import site.laube.visitor.VisitorUtility;

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

public class ApplyVisitor extends RequestSystemVisitor {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApplyVisitor.class);

	/**
	 * Do the application work.<br>
	 * @param requestSystemAcceptor requests system acceptor
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	public ResultDto visit(final RequestSystemAcceptor requestSystemAcceptor) throws LaubeException {

		log.info("[workflowEngine] " + "visit start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "requestSystemAcceptor:" + requestSystemAcceptor);

		boolean isAutoCommit = false;
		if ("true".equals(LaubeProperties.getInstance().getValue("isAutoCommit"))){
			isAutoCommit = true;
		}else{
			isAutoCommit = false;
		}

		// create a return information.
		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(requestSystemAcceptor)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "visit end");
			return resultDto;
		}

		final ApplyAcceptor applyAcceptor = (ApplyAcceptor)requestSystemAcceptor;

		try{
			if (RequestUtility.isEmpty(applyAcceptor)) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0001");
				log.error("[workflowEngine] " + "[resultDto] " + resultDto.toString());
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			final String companyCode = applyAcceptor.getCompanyCode();
			long applicationNumber = applyAcceptor.getApplicationNumber();
			final long reapplicationNumber = applyAcceptor.getReapplicationNumber();
			final String applicationFormCode = applyAcceptor.getApplicationFormCode();
			final String applyDate = applyAcceptor.getApplyDate();
			final String applyCompanyCode = applyAcceptor.getApplyCompanyCode();
			final String applyUnitCode = applyAcceptor.getApplyUnitCode();
			final String applyUserCode = applyAcceptor.getApplyUserCode();
			final String deputyApplyCompanyCode = applyAcceptor.getDeputyApplyCompanyCode();
			final String deputyApplyUnitCode = applyAcceptor.getDeputyApplyUnitCode();
			final String deputyApplyUserCode = applyAcceptor.getDeputyApplyUserCode();
			final int applicationStatus = SpecifiedValue.UnderExamination;

			log.debug("[workflowEngine] " + "Find the application form master.");
			VisitorUtility.findApplicationForm(applyCompanyCode, applicationFormCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}
			ApplicationFormDto applicationFormDto  = (ApplicationFormDto)resultDto.getResultData();

			List<ApprovalRouteInformationAcceptor> individualRoutes = applyAcceptor.getIndividualRoutes();
			List<ApprovalRouteInformationAcceptor> commonRoutes = applyAcceptor.getCommonRoutes();

			final boolean isIndividualRoutes = (LaubeUtility.isEmpty(individualRoutes))||(individualRoutes.size() == 0);
			final boolean isCommonRoutes = (LaubeUtility.isEmpty(commonRoutes))||(commonRoutes.size() == 0);

			final boolean check1 = (SpecifiedValue.NoIndividualRouteFlag == applicationFormDto.getRouteFlag()) && (!isIndividualRoutes);
			final boolean check2 = (SpecifiedValue.IndividualRouteFlag == applicationFormDto.getRouteFlag()) && (isIndividualRoutes);
			final boolean check3 = isIndividualRoutes && isCommonRoutes;

			if (check1) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0002");
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			if (check2) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0003");
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			if (check3) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0007");
				log.info("[workflowEngine] " + "visit end");
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
			boolean isCheck = RequestUtility.checkRoute(approvalRouteInformationAcceptor);

			if (!isCheck) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0009");
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			final ApplicationObjectModelInterface applicationObjectModelInterface = new ApplicationObjectModel();

			if (applicationNumber != 0) {
				resultDto = applicationObjectModelInterface.find(applyAcceptor.getCompanyCode(), applyAcceptor.getApplicationNumber());
			}

			Object object = resultDto.getResultData();
			ArrayList<LaubeDto> applicationObjectDtos = null;

			if (object instanceof ArrayList){
				applicationObjectDtos = (ArrayList<LaubeDto>)object;
			}

			boolean isDraft = false;
			if ((applicationNumber == 0)||(LaubeUtility.isEmpty(applicationObjectDtos))) {
				isDraft = false;

			}else{

				if (SpecifiedValue.Draft == ((ApplicationObjectDto)applicationObjectDtos.get(0)).getApplicationStatus()) {
					isDraft = true;

				}else{
					resultDto.setStatus(false);
					resultDto.setMessageId("E0010");
					log.info("[workflowEngine] " + "visit end");
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

			// create Activity Object
			List<ActivityObjectDto> activityObjectDtoList = RequestUtility.copyToActivityDto(companyCode, applyUserCode, approvalRouteInformationAcceptor);

			// change the first approver of status
			activityObjectDtoList = changeTheFirstApproverOfStatus(activityObjectDtoList);

			ActivityObjectModelInterface activityObjectModelInterface = new ActivityObjectModel();

			if (isDraft){
				resultDto = activityObjectModelInterface.delete(companyCode, applicationNumber);
				if (!resultDto.isSuccess()) {
					LaubeModel.connection.rollback();
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}
				resultDto = activityObjectModelInterface.insert(activityObjectDtoList);
				if (!resultDto.isSuccess()) {
					LaubeModel.connection.rollback();
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}
			}else{
				resultDto = activityObjectModelInterface.insert(activityObjectDtoList);
				LaubeModel.connection.rollback();
				if (!resultDto.isSuccess()) {
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}
			}

			// check activity status
			boolean isComplete = RequestUtility.checkActivityStatus(activityObjectDtoList);

			if (isComplete){
				applicationObjectDto.setApplicationStatus(SpecifiedValue.Approved);
				resultDto = applicationObjectModelInterface.update(applicationObjectDto);
			}

			if (!resultDto.isSuccess()) {
				LaubeModel.connection.rollback();
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			if (isAutoCommit){
				LaubeModel.connection.commit();
			}else{
				resultDto.setConnection(LaubeModel.connection);
			}

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(applicationNumber);
			log.debug("[workflowEngine] " + "applicationNumber is " + applicationNumber);
			log.info("[workflowEngine] " + "visit end");
			return resultDto;

		}catch(final Exception e){
			log.info("[workflowEngine] " + "visit end");
			throw new LaubeException(e);

		}finally{
			try {
				if (isAutoCommit){
					if (!LaubeUtility.isEmpty(LaubeModel.connection)){
						LaubeModel.connection.close();
					}
				}
				log.info("[workflowEngine] " + "visit end");
			} catch (final SQLException e) {
				log.info("[workflowEngine] " + "visit end");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * change the first approver of status.<br>
	 * @param activityObjectDtoList
	 * @return List<ActivityObjectDto>
	 * @exception LaubeException
	 */
	private final List<ActivityObjectDto> changeTheFirstApproverOfStatus(List<ActivityObjectDto> activityObjectDtoList) {

		log.info("[workflowEngine] " + "changeTheFirstApproverOfStatus start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "activityObjectDtoList:" + activityObjectDtoList);

		boolean result = true;


		if (LaubeUtility.isEmpty(activityObjectDtoList)) {
			log.info("[workflowEngine] " + "changeTheFirstApproverOfStatus end");
			return activityObjectDtoList;
		}

		for(ActivityObjectDto activityObjectDto : activityObjectDtoList) {

			if (activityObjectDto != null){
				// find party of start
				if (isFirstParty(activityObjectDtoList, activityObjectDto)) {
					result = setArrivalStatus(activityObjectDtoList, activityObjectDto.getPartyCode());
				}
			}
			if (!result) {
				break;
			}
		}

		log.info("[workflowEngine] " + "changeTheFirstApproverOfStatus end");
		return activityObjectDtoList;
	}

	/**
	 * set arrival status.
	 * @param activityObjectDtoList
	 * @param target
	 * @return result
	 */
	private final boolean isFirstParty(List<ActivityObjectDto> activityObjectDtoList, final ActivityObjectDto target){

		log.info("[workflowEngine] " + "isFirstParty start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "activityObjectDtoList:" + activityObjectDtoList);
		log.info("[workflowEngine] " + "target:" + target);

		boolean result = true;

		if ((LaubeUtility.isEmpty(activityObjectDtoList))||(LaubeUtility.isEmpty(target))) {
			log.info("[workflowEngine] " + "isFirstParty end");
			return false;
		}

		for(ActivityObjectDto activityObjectDto : activityObjectDtoList) {

			if (!LaubeUtility.isEmpty(activityObjectDto)) {
				if (activityObjectDto.getNextPartyCode().equals(target.getPartyCode())) {
					result = false;
				}
			}
		}
		log.info("[workflowEngine] " + "isFirstParty end");
		return result;
	}

	/**
	 * set arrival status.
	 * @param activityObjectDtoList
	 * @param partyCode
	 * @return result
	 */
	private final boolean setArrivalStatus(List<ActivityObjectDto> activityObjectDtoList, final String partyCode){

		log.info("[workflowEngine] " + "setArrivalStatus start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "activityObjectDtoList:" + activityObjectDtoList);
		log.info("[workflowEngine] " + "partyCode:" + partyCode);

		boolean result = true;

		if ((LaubeUtility.isEmpty(activityObjectDtoList))||(LaubeUtility.isBlank(partyCode))) {
			log.info("[workflowEngine] " + "setArrivalStatus end");
			return false;
		}

		for(ActivityObjectDto activityObjectDto : activityObjectDtoList) {
			if (LaubeUtility.isEmpty(activityObjectDto)){
				log.info("[workflowEngine] " + "setArrivalStatus end");
				return false;
			}
			if (partyCode.equals(activityObjectDto.getPartyCode())) {
				activityObjectDto.setActivityStatus(SpecifiedValue.Arrival);
			}
		}
		log.info("[workflowEngine] " + "setArrivalStatus end");
		return result;
	}
}
