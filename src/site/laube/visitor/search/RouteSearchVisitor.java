package site.laube.visitor.search;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.SearchSystemAcceptor;
import site.laube.acceptor.search.RouteSearchAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.dto.ApplicationClassificationDto;
import site.laube.dto.ApplicationFormDto;
import site.laube.dto.ApplicationFormRouteDto;
import site.laube.dto.ResultDto;
import site.laube.dto.temporary.CompanyDto;
import site.laube.dto.temporary.UnitDto;
import site.laube.dto.temporary.UserDto;
import site.laube.exception.LaubeException;
import site.laube.model.LaubeModel;
import site.laube.utility.LaubeProperties;
import site.laube.utility.LaubeUtility;
import site.laube.utility.SpecifiedValue;
import site.laube.utility.type.RouteType;
import site.laube.visitor.SearchSystemVisitor;
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

public class RouteSearchVisitor extends SearchSystemVisitor {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RouteSearchVisitor.class);

	/**
	 * Do the application work.<br>
	 * @param searchSystemAcceptor searchs system acceptor
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("unchecked")
	public ResultDto visit(final SearchSystemAcceptor searchSystemAcceptor) throws LaubeException {

		log.info("[workflowEngine] " + "visit start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "searchSystemAcceptor:" + searchSystemAcceptor);

		boolean isAutoCommit = false;
		if ("true".equals(LaubeProperties.getInstance().getValue("isAutoCommit"))){
			isAutoCommit = true;
		}else{
			isAutoCommit = false;
		}

		// create a return information.
		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(searchSystemAcceptor)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "visit end");
			return resultDto;
		}

		final RouteSearchAcceptor routeSearchAcceptor = (RouteSearchAcceptor)searchSystemAcceptor;

		try{
			boolean isNull = SearchUtility.isEmpty(routeSearchAcceptor);

			if (isNull) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0001");
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			final String companyCode = routeSearchAcceptor.getCompanyCode();
			final String applicationFormCode = routeSearchAcceptor.getApplicationFormCode();
			final String applyCompanyCode = routeSearchAcceptor.getApplyCompanyCode();
			final String applyUnitCode = routeSearchAcceptor.getApplyUnitCode();
			final String applyUserCode = routeSearchAcceptor.getApplyUserCode();
			final String deputyApplyCompanyCode = routeSearchAcceptor.getDeputyApplyCompanyCode();
			final String deputyApplyUnitCode = routeSearchAcceptor.getDeputyApplyUnitCode();
			final String deputyApplyUserCode = routeSearchAcceptor.getDeputyApplyUserCode();

			log.debug("[workflowEngine] " + "find the company master.");
			resultDto = VisitorUtility.findCompany(companyCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			CompanyDto companyDto = (CompanyDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the application form master.");
			ApplicationFormDto applicationFormDto = null;
			resultDto = VisitorUtility.findApplicationForm(companyCode, applicationFormCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			applicationFormDto = (ApplicationFormDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the application form route master.");
			resultDto = VisitorUtility.findApplicationFormRoute(applyCompanyCode, applicationFormCode, applyUnitCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			ApplicationFormRouteDto applicationFormRouteDto = (ApplicationFormRouteDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the application Classification master.");
			resultDto = VisitorUtility.findApplicationClassification(companyCode, applicationFormDto.getApplicationClassificationCode());
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			ApplicationClassificationDto applicationClassificationDto = (ApplicationClassificationDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the company master.");
			resultDto = VisitorUtility.findCompany(applyCompanyCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			CompanyDto applyCompanyDto = (CompanyDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the unit master.");
			resultDto = VisitorUtility.findUnit(applyCompanyCode, applyUnitCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			UnitDto applyUnitDto = (UnitDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the user master.");
			resultDto = VisitorUtility.findUser(applyCompanyCode, applyUserCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.info("[workflowEngine] " + "visit end");
				return resultDto;
			}

			UserDto applyUserDto = (UserDto)resultDto.getResultData();

			final boolean isDeputyApplyCompanyCode = (LaubeUtility.isBlank(deputyApplyCompanyCode));
			final boolean isDeputyApplyUnitCode    = (LaubeUtility.isBlank(deputyApplyUnitCode));
			final boolean isDeputyApplyUserCode    = (LaubeUtility.isBlank(deputyApplyUserCode));

			final boolean isNotDeputy = isDeputyApplyCompanyCode || isDeputyApplyUnitCode || isDeputyApplyUserCode;

			// deputy apply check
			CompanyDto deputyApplyCompanyDto = null;
			UnitDto deputyApplyUnitDto = null;
			UserDto deputyApplyUserDto = null;

			if (!isNotDeputy) {

				log.debug("[workflowEngine] " + "find the company master.");
				deputyApplyCompanyDto = null;
				resultDto = VisitorUtility.findCompany(deputyApplyCompanyCode);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}

				deputyApplyCompanyDto = (CompanyDto)resultDto.getResultData();

				log.debug("[workflowEngine] " + "find the unit master.");
				deputyApplyUnitDto = null;
				resultDto = VisitorUtility.findUnit(deputyApplyCompanyCode, deputyApplyUnitCode);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}

				deputyApplyUnitDto = (UnitDto)resultDto.getResultData();

				log.debug("[workflowEngine] " + "find the user master.");
				deputyApplyUserDto = null;
				resultDto = VisitorUtility.findUser(deputyApplyCompanyCode, deputyApplyUserCode);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}

				deputyApplyUserDto = (UserDto)resultDto.getResultData();
			}

			// set return information
			routeSearchAcceptor.setScreenMode(SpecifiedValue.ApplyMode);
			routeSearchAcceptor.setCompanyCode(companyDto.getCompanyCode());
			routeSearchAcceptor.setCompanyName(companyDto.getCompanyName());
			routeSearchAcceptor.setApplicationFormCode(applicationFormDto.getApplicationFormCode());
			routeSearchAcceptor.setApplicationFormName(applicationFormDto.getApplicationFormName());
			routeSearchAcceptor.setApplicationClassificationCode(applicationClassificationDto.getApplicationClassificationCode());
			routeSearchAcceptor.setApplicationClassificationName(applicationClassificationDto.getApplicationClassificationName());
			routeSearchAcceptor.setApplyCompanyCode(applyCompanyDto.getCompanyCode());
			routeSearchAcceptor.setApplyCompanyName(applyCompanyDto.getCompanyName());
			routeSearchAcceptor.setApplyUnitCode(applyUnitDto.getUnitCode());
			routeSearchAcceptor.setApplyUnitName(applyUnitDto.getUnitName());
			routeSearchAcceptor.setApplyUserCode(applyUserDto.getUserCode());
			routeSearchAcceptor.setApplyUserName(applyUserDto.getUserName());

			// deputy apply user
			if (!isNotDeputy) {
				routeSearchAcceptor.setDeputyApplyCompanyCode(deputyApplyCompanyDto.getCompanyCode());
				routeSearchAcceptor.setDeputyApplyCompanyName(deputyApplyCompanyDto.getCompanyName());
				routeSearchAcceptor.setDeputyApplyUnitCode(deputyApplyUnitDto.getUnitCode());
				routeSearchAcceptor.setDeputyApplyUnitName(deputyApplyUnitDto.getUnitName());
				routeSearchAcceptor.setDeputyApplyUserCode(deputyApplyUserDto.getUserCode());
				routeSearchAcceptor.setDeputyApplyUserName(deputyApplyUserDto.getUserName());
			}else{
				routeSearchAcceptor.setDeputyApplyCompanyName(null);
				routeSearchAcceptor.setDeputyApplyUnitName(null);
				routeSearchAcceptor.setDeputyApplyUserName(null);
			}

			String individualRouteCode = null;
			String commonRouteCode = null;

			if (!LaubeUtility.isEmpty(applicationFormRouteDto)) {
				log.debug("[workflowEngine] " + "find the application form route master.");
				individualRouteCode = applicationFormRouteDto.getIndividualRouteCode();
				commonRouteCode     = applicationFormRouteDto.getCommonRouteCode();
			}

			log.debug("[workflowEngine] " + "routeFlag:" + applicationFormDto.getRouteFlag());
			log.debug("[workflowEngine] " + "individualRouteCode:" + individualRouteCode);
			log.debug("[workflowEngine] " + "commonRouteCode:" + commonRouteCode);

			if ((SpecifiedValue.IndividualRouteFlag == applicationFormDto.getRouteFlag()) && (!LaubeUtility.isBlank(individualRouteCode))) {
				log.debug("[workflowEngine] " + "find the individual route master.");
				resultDto = VisitorUtility.findRoute(applyCompanyCode, individualRouteCode, RouteType.IndividualRoute);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}

				ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = (ArrayList<ApprovalRouteInformationAcceptor>)resultDto.getResultData();;
				if ((approvalRouteInformationAcceptors == null)||(approvalRouteInformationAcceptors.size() < 1)) {
					resultDto.setStatus(false);
					resultDto.setMessageId("E1002");
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}
				routeSearchAcceptor.setIndividualRoutes(approvalRouteInformationAcceptors);
			}

			if (SpecifiedValue.BossRouteFlag == applicationFormDto.getRouteFlag()) {
				log.debug("[workflowEngine] " + "find the boss route master.");
				resultDto = VisitorUtility.findRoute(applyCompanyCode, applyUnitCode, applyUserCode, applicationFormCode);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}

				if (resultDto.getMessageId().equals("N0002")) {
					log.debug("[workflowEngine] " + "find the special route master.");
					resultDto = VisitorUtility.findRoute(applyCompanyCode,applicationClassificationDto.getApplicationClassificationCode(), RouteType.SpecialRoute);
					if (LaubeUtility.isEmpty(resultDto)) {
						log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
						log.info("[workflowEngine] " + "visit end");
						return resultDto;
					}

					ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = (ArrayList<ApprovalRouteInformationAcceptor>)resultDto.getResultData();;
					if ((LaubeUtility.isEmpty(approvalRouteInformationAcceptors))||(approvalRouteInformationAcceptors.size() < 1)) {
						resultDto.setStatus(false);
						resultDto.setMessageId("E1002");
						log.info("[workflowEngine] " + "visit end");
						return resultDto;
					}
					routeSearchAcceptor.setIndividualRoutes(approvalRouteInformationAcceptors);

				}else{
					ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = (ArrayList<ApprovalRouteInformationAcceptor>)resultDto.getResultData();;
					if ((LaubeUtility.isEmpty(approvalRouteInformationAcceptors))||(approvalRouteInformationAcceptors.size() < 1)) {
						resultDto.setStatus(false);
						resultDto.setMessageId("E1002");
						log.info("[workflowEngine] " + "visit end");
						return resultDto;
					}
					routeSearchAcceptor.setIndividualRoutes(approvalRouteInformationAcceptors);
				}
			}

			String applyUser = null;

			if (isNotDeputy){
				applyUser = routeSearchAcceptor.getApplyUserCode();
			}else{
				applyUser = routeSearchAcceptor.getDeputyApplyUserCode();
			}

			if (applicationFormDto.isSkipApplyUser()){
				// if the applicant is on the individual route, and then removed from the route.
				SearchUtility.updateIndividualRoute(applyUser, routeSearchAcceptor);

				if (SearchUtility.isAllSkip(routeSearchAcceptor.getIndividualRoutes())){
					resultDto = VisitorUtility.findRoute(applyCompanyCode, applicationClassificationDto.getApplicationClassificationCode(), RouteType.SpecialRoute);
					if (LaubeUtility.isEmpty(resultDto)) {
						log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
						log.info("[workflowEngine] " + "visit end");
						return resultDto;
					}

					if (resultDto.isSuccess()) {
						if (LaubeUtility.isEmpty(resultDto.getResultData())) {
							routeSearchAcceptor.setIndividualRoutes(null);
						}else{
							Object o = resultDto.getResultData();
							ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = (ArrayList<ApprovalRouteInformationAcceptor>)o;
							routeSearchAcceptor.setIndividualRoutes(approvalRouteInformationAcceptors);
							// if the applicant is on the individual route, and then removed from the route.
							SearchUtility.updateIndividualRoute(applyUser, routeSearchAcceptor);
						}
					}else{
						log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
						log.info("[workflowEngine] " + "visit end");
						return resultDto;
					}
				}
			}

			if (!LaubeUtility.isBlank(commonRouteCode)) {
				log.debug("[workflowEngine] " + "find the common route master.");
				resultDto = VisitorUtility.findRoute(applyCompanyCode, commonRouteCode, RouteType.CommonRoute);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}

				ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = (ArrayList<ApprovalRouteInformationAcceptor>)resultDto.getResultData();;
				if ((LaubeUtility.isEmpty(approvalRouteInformationAcceptors))||(approvalRouteInformationAcceptors.size() < 1)) {
					resultDto.setStatus(false);
					resultDto.setMessageId("E1002");
					log.info("[workflowEngine] " + "visit end");
					return resultDto;
				}
				routeSearchAcceptor.setCommonRoutes(approvalRouteInformationAcceptors);
			}

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(routeSearchAcceptor);
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
			} catch (final SQLException e) {
				log.info("[workflowEngine] " + "visit end");
				throw new LaubeException(e);
			}
		}
	}
}
