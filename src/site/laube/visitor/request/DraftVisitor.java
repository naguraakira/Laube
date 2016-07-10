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
	public ResultDto visit(final RequestSystemAcceptor requestSystemAcceptor) throws LaubeException {

		log.info("[workflowEngine] " + "visit start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "requestSystemAcceptor:" + requestSystemAcceptor);

		// create a return information.
		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(requestSystemAcceptor)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "visit end");
			return resultDto;
		}

		final DraftAcceptor draftAcceptor = (DraftAcceptor)requestSystemAcceptor;

		try{

			boolean isNull = RequestUtility.isNull(draftAcceptor);

			if (isNull) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0001");
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			final String companyCode = draftAcceptor.getCompanyCode();
			long applicationNumber = draftAcceptor.getApplicationNumber();
			final long reapplicationNumber = draftAcceptor.getReapplicationNumber();
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
				log.info("[workflowEngine] " + "visit end");
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
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.info("[workflowEngine] " + "ApplyVisitor.visit() end");
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

			ActivityObjectModelInterface activityObjectModelInterface = new ActivityObjectModel();

			if (isDraft){
				resultDto = activityObjectModelInterface.delete(companyCode, applicationNumber);
				if (!resultDto.isSuccess()) {
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}
				resultDto = activityObjectModelInterface.insert(activityObjectDtoList);
				if (!resultDto.isSuccess()) {
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}
			}else{
				resultDto = activityObjectModelInterface.insert(activityObjectDtoList);
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
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			LaubeModel.connection.commit();

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
				LaubeModel.connection.close();
			} catch (final SQLException e) {
				log.info("[workflowEngine] " + "visit end");
				throw new LaubeException(e);
			}
		}
	}
}
