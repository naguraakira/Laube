package site.laube.visitor.request;

import java.util.ArrayList;
import java.util.List;

import site.laube.acceptor.RequestSystemAcceptor;
import site.laube.acceptor.request.ApplyAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.dao.ActivityObjectDao;
import site.laube.dao.ApplicationObjectDao;
import site.laube.dao.LaubeDao;
import site.laube.daointerface.ActivityObjectDaoInterface;
import site.laube.daointerface.ApplicationObjectDaoInterface;
import site.laube.dto.ActivityObjectDto;
import site.laube.dto.ApplicationFormDto;
import site.laube.dto.ApplicationObjectDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
import site.laube.utility.LaubeProperties;
import site.laube.utility.LaubeUtility;
import site.laube.utility.type.RouteFlag;
import site.laube.utility.type.Status;
import site.laube.utility.type.UserStatus;
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
	private static LaubeLogger log = new LaubeLogger(LaubeLoggerFactory.getLogger(ApplyVisitor.class));

	/**
	 * Do the application work.<br>
	 * @param requestSystemAcceptor requests system acceptor
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public ResultDto visit(final RequestSystemAcceptor requestSystemAcceptor) throws LaubeException {

		log.traceStart("visit",requestSystemAcceptor);

		// create a return information.
		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(requestSystemAcceptor)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		boolean isAutoCommit = false;
		if (LaubeProperties.TRUE.equals(LaubeProperties.getValue("isAutoCommit"))){
			isAutoCommit = true;
		}else{
			isAutoCommit = false;
		}

		final ApplyAcceptor applyAcceptor = (ApplyAcceptor)requestSystemAcceptor;

		try{
			if (RequestUtility.isEmpty(applyAcceptor)) {
				resultDto.setSuccess(false);
				resultDto.setMessageId("E0001");
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
			final int applicationStatus = Status.UnderExamination.toInt();

			log.message("visit","Find the application form master.");
			resultDto = VisitorUtility.findApplicationForm(applyCompanyCode, applicationFormCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				resultDto.setSuccess(false);
				resultDto.setMessageId("E0001");
				return resultDto;
			}
			ApplicationFormDto applicationFormDto  = (ApplicationFormDto)resultDto.getResultData();

			List<ApprovalRouteInformationAcceptor> individualRoutes = applyAcceptor.getIndividualRoutes();
			List<ApprovalRouteInformationAcceptor> commonRoutes = applyAcceptor.getCommonRoutes();

			final boolean isIndividualRoutes = (LaubeUtility.isEmpty(individualRoutes))||(individualRoutes.size() == 0);
			final boolean isCommonRoutes = (LaubeUtility.isEmpty(commonRoutes))||(commonRoutes.size() == 0);

			final boolean check1 = (RouteFlag.NoIndividualRouteFlag.toInt() == applicationFormDto.getRouteFlag()) && (!isIndividualRoutes);
			final boolean check2 = (RouteFlag.IndividualRouteFlag.toInt() == applicationFormDto.getRouteFlag()) && (isIndividualRoutes);
			final boolean check3 = isIndividualRoutes && isCommonRoutes;

			if (check1) {
				resultDto.setSuccess(false);
				resultDto.setMessageId("E0002");
				return resultDto;
			}

			if (check2) {
				resultDto.setSuccess(false);
				resultDto.setMessageId("E0003");
				return resultDto;
			}

			if (check3) {
				resultDto.setSuccess(false);
				resultDto.setMessageId("E0007");
				return resultDto;
			}

			// Store individualRoute and commonRoutes in the same variable
			List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptor = new ArrayList<ApprovalRouteInformationAcceptor>();

			if (!LaubeUtility.isEmpty(individualRoutes)){
				for(ApprovalRouteInformationAcceptor individualRoute : individualRoutes) {
					approvalRouteInformationAcceptor.add(individualRoute);
				}
			}
			if (!LaubeUtility.isEmpty(commonRoutes)){
				for(ApprovalRouteInformationAcceptor commonRoute : commonRoutes) {
					approvalRouteInformationAcceptor.add(commonRoute);
				}
			}

			// Whether or not there is inconsistency in the route information check
			boolean isCheck = RequestUtility.checkRoute(approvalRouteInformationAcceptor);

			if (!isCheck) {
				resultDto.setSuccess(false);
				resultDto.setMessageId("E0009");
				return resultDto;
			}

			final ApplicationObjectDaoInterface applicationObjectModelInterface = new ApplicationObjectDao();

			if (applicationNumber != 0) {
				resultDto = applicationObjectModelInterface.find(applyAcceptor.getCompanyCode(), applyAcceptor.getApplicationNumber());
			}

			Object object = resultDto.getResultData();
			ArrayList<LaubeDto> applicationObjectDtos = null;

			if (object instanceof ArrayList){
				applicationObjectDtos = LaubeUtility.automaticCast(object);
			}

			boolean isDraft = false;
			if ((applicationNumber == 0)||(LaubeUtility.isEmpty(applicationObjectDtos))) {
				isDraft = false;

			}else{

				if (((ApplicationObjectDto)applicationObjectDtos.get(0)).getApplicationStatus() == Status.Draft.toInt()) {
					isDraft = true;

				}else{
					resultDto.setSuccess(false);
					resultDto.setMessageId("E0010");
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

			ActivityObjectDaoInterface activityObjectModelInterface = new ActivityObjectDao();

			if (isDraft){
				resultDto = activityObjectModelInterface.delete(companyCode, applicationNumber);
				if (!resultDto.isSuccess()) {
					LaubeDao.rollback();
					return resultDto;
				}
				resultDto = activityObjectModelInterface.insert(activityObjectDtoList);
				if (!resultDto.isSuccess()) {
					LaubeDao.rollback();
					return resultDto;
				}
			}else{
				resultDto = activityObjectModelInterface.insert(activityObjectDtoList);
				if (!resultDto.isSuccess()) {
					LaubeDao.rollback();
					return resultDto;
				}
			}

			// check activity status
			boolean isComplete = RequestUtility.checkActivityStatus(activityObjectDtoList);

			if (isComplete){
				applicationObjectDto.setApplicationStatus(Status.Approved.toInt());
				resultDto = applicationObjectModelInterface.update(applicationObjectDto);
			}

			if (!resultDto.isSuccess()) {
				LaubeDao.rollback();
				return resultDto;
			}

			if (isAutoCommit){
				LaubeDao.commit();
			}else{
				resultDto.setConnection(LaubeDao.connection);
			}

			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(applicationNumber);
			return resultDto;

		}catch(final Exception e){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E1999");
			throw new LaubeException("visit",e);

		}finally{
			try {
				if (isAutoCommit){
					if (!LaubeUtility.isEmpty(LaubeDao.connection)){
						LaubeDao.close();
					}
				}
				log.traceEnd("visit",resultDto);
			} catch (final Exception e) {
				log.traceEnd("visit",e);
				throw new LaubeException("visit",e);
			}
		}
	}

	/**
	 * change the first approver of status.<br>
	 * @param activityObjectDtoList
	 * @return List<ActivityObjectDto>
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	private final static List<ActivityObjectDto> changeTheFirstApproverOfStatus(List<ActivityObjectDto> activityObjectDtoList) {

		log.traceStart("changeTheFirstApproverOfStatus", activityObjectDtoList);

		boolean result = true;


		if (LaubeUtility.isEmpty(activityObjectDtoList)) {
			log.traceEnd("changeTheFirstApproverOfStatus");
			return activityObjectDtoList;
		}

		for(ActivityObjectDto activityObjectDto : activityObjectDtoList) {

			if (!LaubeUtility.isEmpty(activityObjectDto)){
				// find party of start
				if (isFirstParty(activityObjectDtoList, activityObjectDto)) {
					result = setArrivalStatus(activityObjectDtoList, activityObjectDto.getPartyCode());
				}
			}
			if (!result) {
				break;
			}
		}

		log.traceEnd("changeTheFirstApproverOfStatus");
		return activityObjectDtoList;
	}

	/**
	 * set arrival status.
	 * @param activityObjectDtoList
	 * @param target
	 * @return result
	 */
	@SuppressWarnings("nls")
	private final static boolean isFirstParty(List<ActivityObjectDto> activityObjectDtoList, final ActivityObjectDto target){

		log.traceStart("isFirstParty", activityObjectDtoList, target);

		boolean result = true;

		if ((LaubeUtility.isEmpty(activityObjectDtoList))||(LaubeUtility.isEmpty(target))) {
			log.traceEnd("isFirstParty");
			return false;
		}

		for(ActivityObjectDto activityObjectDto : activityObjectDtoList) {

			if (!LaubeUtility.isEmpty(activityObjectDto)) {
				if (activityObjectDto.getNextPartyCode().equals(target.getPartyCode())) {
					result = false;
				}
			}
		}
		log.traceEnd("isFirstParty");
		return result;
	}

	/**
	 * set arrival status.
	 * @param activityObjectDtoList
	 * @param partyCode
	 * @return result
	 */
	@SuppressWarnings("nls")
	private final static boolean setArrivalStatus(List<ActivityObjectDto> activityObjectDtoList, final String partyCode){

		log.traceStart("setArrivalStatus", activityObjectDtoList, partyCode);

		boolean result = true;

		if ((LaubeUtility.isEmpty(activityObjectDtoList))||(LaubeUtility.isBlank(partyCode))) {
			log.traceEnd("setArrivalStatus");
			return false;
		}

		for(ActivityObjectDto activityObjectDto : activityObjectDtoList) {
			if (LaubeUtility.isEmpty(activityObjectDto)){
				log.traceEnd("setArrivalStatus");
				return false;
			}
			if (partyCode.equals(activityObjectDto.getPartyCode())) {
				activityObjectDto.setActivityStatus(UserStatus.Arrival.toInt());
			}
		}
		log.traceEnd("setArrivalStatus");
		return result;
	}
}
