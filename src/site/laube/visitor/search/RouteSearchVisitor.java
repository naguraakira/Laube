package site.laube.visitor.search;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.SearchSystemAcceptor;
import site.laube.acceptor.search.RouteSearchAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.dto.ActivityDto;
import site.laube.dto.ApplicationClassificationDto;
import site.laube.dto.ApplicationFormDto;
import site.laube.dto.ApplicationFormRouteDto;
import site.laube.dto.BossDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.dto.RoleUserDto;
import site.laube.dto.temporary.CompanyDto;
import site.laube.dto.temporary.UnitDto;
import site.laube.dto.temporary.UserDto;
import site.laube.exception.LaubeException;
import site.laube.model.ActivityModel;
import site.laube.model.ApplicationClassificationModel;
import site.laube.model.ApplicationFormModel;
import site.laube.model.ApplicationFormRouteModel;
import site.laube.model.BossModel;
import site.laube.model.CompanyModel;
import site.laube.model.LaubeModel;
import site.laube.model.RoleUserModel;
import site.laube.model.UnitModel;
import site.laube.model.UserModel;
import site.laube.modelinterface.ActivityModelInterface;
import site.laube.modelinterface.ApplicationClassificationModelInterface;
import site.laube.modelinterface.ApplicationFormModelInterface;
import site.laube.modelinterface.ApplicationFormRouteModelInterface;
import site.laube.modelinterface.BossModelInterface;
import site.laube.modelinterface.CompanyModelInterface;
import site.laube.modelinterface.RoleUserModelInterface;
import site.laube.modelinterface.UnitModelInterface;
import site.laube.modelinterface.UserModelInterface;
import site.laube.utility.LaubeUtility;
import site.laube.utility.SpecifiedValue;
import site.laube.utility.type.RouteType;
import site.laube.visitor.SearchSystemVisitor;

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
	 * @exception LaubeException
	 */
	@SuppressWarnings("unchecked")
	public ResultDto visit(final SearchSystemAcceptor searchSystemAcceptor) throws LaubeException {

		log.debug("[workflowEngine] " + "visit start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "searchSystemAcceptor:" + searchSystemAcceptor);

		// create a return information.
		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(searchSystemAcceptor)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "visit end");
			return resultDto;
		}

		final RouteSearchAcceptor routeSearchAcceptor = (RouteSearchAcceptor)searchSystemAcceptor;

		try{
			boolean isNull = isNull(routeSearchAcceptor);

			if (isNull) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0001");
				log.error("[workflowEngine] " + "visit end");
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
			resultDto = findCompany(companyCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			CompanyDto companyDto = (CompanyDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the application form master.");
			ApplicationFormDto applicationFormDto = null;
			resultDto = findApplicationForm(companyCode, applicationFormCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			applicationFormDto = (ApplicationFormDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the application form route master.");
			resultDto = findApplicationFormRoute(applyCompanyCode, applicationFormCode, applyUnitCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			ApplicationFormRouteDto applicationFormRouteDto = (ApplicationFormRouteDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the application Classification master.");
			resultDto = findApplicationClassification(companyCode, applicationFormDto.getApplicationClassificationCode());
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			ApplicationClassificationDto applicationClassificationDto = (ApplicationClassificationDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the company master.");
			resultDto = findCompany(applyCompanyCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			CompanyDto applyCompanyDto = (CompanyDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the unit master.");
			resultDto = findUnit(applyCompanyCode, applyUnitCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			UnitDto applyUnitDto = (UnitDto)resultDto.getResultData();

			log.debug("[workflowEngine] " + "find the user master.");
			resultDto = findUser(applyCompanyCode, applyUserCode);
			if (LaubeUtility.isEmpty(resultDto)) {
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			UserDto applyUserDto = (UserDto)resultDto.getResultData();

			final boolean isDeputyApplyCompanyCode = (deputyApplyCompanyCode == null);
			final boolean isDeputyApplyUnitCode = (deputyApplyUnitCode == null);
			final boolean isDeputyApplyUserCode = (deputyApplyUserCode == null);

			final boolean isDeputy = isDeputyApplyCompanyCode || isDeputyApplyUnitCode || isDeputyApplyUserCode;

			// deputy apply check
			CompanyDto deputyApplyCompanyDto = null;
			UnitDto deputyApplyUnitDto = null;
			UserDto deputyApplyUserDto = null;

			if (!isDeputy) {

				log.debug("[workflowEngine] " + "find the company master.");
				deputyApplyCompanyDto = null;
				resultDto = findCompany(deputyApplyCompanyCode);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}

				deputyApplyCompanyDto = (CompanyDto)resultDto.getResultData();

				log.debug("[workflowEngine] " + "find the unit master.");
				deputyApplyUnitDto = null;
				resultDto = findUnit(deputyApplyCompanyCode, deputyApplyUnitCode);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}

				deputyApplyUnitDto = (UnitDto)resultDto.getResultData();

				log.debug("[workflowEngine] " + "find the user master.");
				deputyApplyUserDto = null;
				resultDto = findUser(deputyApplyCompanyCode, deputyApplyUserCode);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.error("[workflowEngine] " + "visit end");
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
			if (!isDeputy) {
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

			if (applicationFormRouteDto != null) {
				log.debug("[workflowEngine] " + "find the application form route master.");
				individualRouteCode = applicationFormRouteDto.getIndividualRouteCode();
				commonRouteCode     = applicationFormRouteDto.getCommonRouteCode();
			}

			log.debug("[workflowEngine] " + "routeFlag:" + applicationFormDto.getRouteFlag());
			log.debug("[workflowEngine] " + "individualRouteCode:" + individualRouteCode);
			log.debug("[workflowEngine] " + "commonRouteCode:" + commonRouteCode);

			if ((applicationFormDto.getRouteFlag() == SpecifiedValue.IndividualRouteFlag) && (individualRouteCode != null)) {
				log.debug("[workflowEngine] " + "find the individual route master.");
				resultDto = findRoute(applyCompanyCode, individualRouteCode, RouteType.IndividualRoute);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}

				ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = (ArrayList<ApprovalRouteInformationAcceptor>)resultDto.getResultData();;
				if ((approvalRouteInformationAcceptors == null)||(approvalRouteInformationAcceptors.size() < 1)) {
					resultDto.setStatus(false);
					resultDto.setMessageId("E1002");
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}
				routeSearchAcceptor.setIndividualRoutes(approvalRouteInformationAcceptors);
			}

			if (applicationFormDto.getRouteFlag() == SpecifiedValue.BossRouteFlag) {
				log.debug("[workflowEngine] " + "find the boss route master.");
				resultDto = findRoute(applyCompanyCode, applyUnitCode, applyUserCode, applicationFormCode);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}

				if (resultDto.getMessageId().equals("N0002")) {
					log.debug("[workflowEngine] " + "find the special route master.");
					resultDto = findRoute(applyCompanyCode,applicationClassificationDto.getApplicationClassificationCode(), RouteType.SpecialRoute);
					if (LaubeUtility.isEmpty(resultDto)) {
						log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
						log.error("[workflowEngine] " + "visit end");
						return resultDto;
					}

					ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = (ArrayList<ApprovalRouteInformationAcceptor>)resultDto.getResultData();;
					if ((approvalRouteInformationAcceptors == null)||(approvalRouteInformationAcceptors.size() < 1)) {
						resultDto.setStatus(false);
						resultDto.setMessageId("E1002");
						log.error("[workflowEngine] " + "visit end");
						return resultDto;
					}
					routeSearchAcceptor.setIndividualRoutes(approvalRouteInformationAcceptors);

				}else{
					ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = (ArrayList<ApprovalRouteInformationAcceptor>)resultDto.getResultData();;
					if ((approvalRouteInformationAcceptors == null)||(approvalRouteInformationAcceptors.size() < 1)) {
						resultDto.setStatus(false);
						resultDto.setMessageId("E1002");
						log.error("[workflowEngine] " + "visit end");
						return resultDto;
					}
					routeSearchAcceptor.setIndividualRoutes(approvalRouteInformationAcceptors);
				}
			}

			if (commonRouteCode != null) {
				log.debug("[workflowEngine] " + "find the common route master.");
				resultDto = findRoute(applyCompanyCode, commonRouteCode, RouteType.CommonRoute);
				if (LaubeUtility.isEmpty(resultDto)) {
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}

				ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = (ArrayList<ApprovalRouteInformationAcceptor>)resultDto.getResultData();;
				if ((approvalRouteInformationAcceptors == null)||(approvalRouteInformationAcceptors.size() < 1)) {
					resultDto.setStatus(false);
					resultDto.setMessageId("E1002");
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}
				routeSearchAcceptor.setCommonRoutes(approvalRouteInformationAcceptors);
			}

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(routeSearchAcceptor);
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
	 * check of essential items.
	 */
	@SuppressWarnings({ "nls", "static-method" })
	private final boolean isNull(final RouteSearchAcceptor routeSearchAcceptor){

		log.debug("[workflowEngine] " + "isNull start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "routeSearchAcceptor:" + routeSearchAcceptor);

		if (LaubeUtility.isEmpty(routeSearchAcceptor)) {
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getCompanyCode())) {
			log.debug("[workflowEngine] " + "companyCode : null");
			log.debug("[workflowEngine] " + "isNull end");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplicationFormCode())) {
			log.debug("[workflowEngine] " + "applicationFormCode : null");
			log.debug("[workflowEngine] " + "isNull end");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplyCompanyCode())) {
			log.debug("[workflowEngine] " + "applyCompanyCode : null");
			log.debug("[workflowEngine] " + "isNull end");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplyUnitCode())) {
			log.debug("[workflowEngine] " + "applyUnitCode : null");
			log.debug("[workflowEngine] " + "isNull end");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplyUserCode())) {
			log.debug("[workflowEngine] " + "applyUserCode : null");
			log.debug("[workflowEngine] " + "isNull end");
			return true;
		}

		log.debug("[workflowEngine] " + "isNull end");
		return false;
	}

	/**
	 * find the company master.<br>
	 * @param laubeRequestAcceptor
	 * @return ResultDto
	 * @exception LaubeException
	 */
	@SuppressWarnings("unchecked")
	private final ResultDto findCompany(final String companyCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findCompany start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "companyCode:" + companyCode);

		ResultDto resultDto  = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.debug("[workflowEngine] " + "findRoute end");
			return resultDto;
		}

		final CompanyModelInterface companyModelInterface = new CompanyModel();
		resultDto = companyModelInterface.find(companyCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.error("[workflowEngine] " + "findCompany end");
			return resultDto;
		}

		ArrayList<LaubeDto> companyDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		CompanyDto companyDto = (CompanyDto)companyDtos.get(0);
		resultDto.setResultData(companyDto);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "findCompany end");
		return resultDto;
	}

	/**
	 * find the unit master.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @return ResultDto
	 * @exception LaubeException
	 */
	@SuppressWarnings("unchecked")
	private final ResultDto findUnit(final String companyCode, final String unitCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findUnit start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "unitCode:" + unitCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(unitCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.debug("[workflowEngine] " + "findRoute end");
			return resultDto;
		}

		final UnitModelInterface unitModelInterface = new UnitModel();
		esultDto = unitModelInterface.find(companyCode, unitCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.error("[workflowEngine] " + "findUnit end");
			return resultDto;
		}

		ArrayList<LaubeDto> unitDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		UnitDto applyUnitDto = (UnitDto)unitDtos.get(0);
		resultDto.setResultData(applyUnitDto);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "findUnit end");
		return resultDto;
	}

	/**
	 * find the user master.<br>
	 * @param companyCode ompany code
	 * @param userCode user code
	 * @return ResultDto
	 * @exception LaubeException
	 */
	@SuppressWarnings("unchecked")
	private final ResultDto findUser(final String companyCode, final String userCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findUser start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "companyCode:" + companyCode);
		log.debug("[workflowEngine] " + "userCode:" + userCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(userCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.debug("[workflowEngine] " + "findRoute end");
			return resultDto;
		}

		final UserModelInterface userModelInterface = new UserModel();
		resultDto = userModelInterface.find(companyCode, userCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.error("[workflowEngine] " + "findUser end");
			return resultDto;
		}

		ArrayList<LaubeDto> userDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		UserDto userDto = (UserDto)userDtos.get(0);
		resultDto.setResultData(userDto);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "findUser end");
		return resultDto;
	}

	/**
	 * find the application form master.<br>
	 * @param companyCode ompany code
	 * @param applicationFormCode application form Code
	 * @return ResultDto
	 * @exception LaubeException
	 */
	@SuppressWarnings("unchecked")
	private final ResultDto findApplicationForm(final String companyCode, final String applicationFormCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findApplicationForm start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "companyCode:" + companyCode);
		log.debug("[workflowEngine] " + "applicationFormCode:" + applicationFormCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.debug("[workflowEngine] " + "findRoute end");
			return resultDto;
		}

		final ApplicationFormModelInterface applicationFormModelInterface = new ApplicationFormModel();
		resultDto = applicationFormModelInterface.findByApplicationFormCode(companyCode, applicationFormCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.error("[workflowEngine] " + "findApplicationForm end");
			return resultDto;
		}

		ArrayList<LaubeDto> applicationFormDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		ApplicationFormDto applicationFormDto = (ApplicationFormDto)applicationFormDtos.get(0);
		resultDto.setResultData(applicationFormDto);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "findApplicationForm end");
		return resultDto;
	}

	/**
	 * find the application form route master.<br>
	 * @param companyCode ompany code
	 * @param applicationFormCode application form Code
	 * @param unitCode unit Code
	 * @return ResultDto
	 * @exception LaubeException
	 */
	@SuppressWarnings("unchecked")
	private final ResultDto findApplicationFormRoute(final String companyCode, final String applicationFormCode, final String unitCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findApplicationFormRoute start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "companyCode:" + companyCode);
		log.debug("[workflowEngine] " + "applicationFormCode:" + applicationFormCode);
		log.debug("[workflowEngine] " + "unitCode:" + unitCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))||(LaubeUtility.isBlank(unitCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.debug("[workflowEngine] " + "findRoute end");
			return resultDto;
		}

		final ApplicationFormRouteModelInterface applicationFormRouteModelInterface = new ApplicationFormRouteModel();
		resultDto = applicationFormRouteModelInterface.find(companyCode, applicationFormCode, unitCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.error("[workflowEngine] " + "findApplicationFormRoute end");
			return resultDto;
		}

		ArrayList<LaubeDto> applicationFormRouteDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		ApplicationFormRouteDto applicationFormRouteDto = (ApplicationFormRouteDto)applicationFormRouteDtos.get(0);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		resultDto.setResultData(applicationFormRouteDto);
		log.debug("[workflowEngine] " + "findApplicationFormRoute end");
		return resultDto;
	}

	/**
	 * find the application classification master.<br>
	 * @param companyCode ompany code
	 * @param applicationClassificationCode application classification code
	 * @return ResultDto
	 * @exception LaubeException
	 */
	@SuppressWarnings("unchecked")
	private final ResultDto findApplicationClassification(final String companyCode, final String applicationClassificationCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findApplicationClassification start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "companyCode:" + companyCode);
		log.debug("[workflowEngine] " + "applicationClassificationCode:" + applicationClassificationCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationClassificationCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.debug("[workflowEngine] " + "findRoute end");
			return resultDto;
		}

		final ApplicationClassificationModelInterface applicationClassificationModelInterfaces = new ApplicationClassificationModel();
		resultDto = applicationClassificationModelInterfaces.findByApplicationClassificationCode(companyCode, applicationClassificationCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.error("[workflowEngine] " + "findApplicationClassification end");
			return resultDto;
		}

		ArrayList<LaubeDto> ApplicationClassificationDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		ApplicationClassificationDto applicationClassificationDto = (ApplicationClassificationDto)ApplicationClassificationDtos.get(0);
		resultDto.setResultData(applicationClassificationDto);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.error("[workflowEngine] " + "findApplicationClassification end");
		return resultDto;
	}

	/**
	 * find the route master.<br>
	 * @param companyCode ompany code
	 * @param individualRouteCode individual route code
	 * @return ResultDto
	 * @exception LaubeException
	 */
	@SuppressWarnings("unchecked")
	private final ResultDto findRoute(final String companyCode, final String routeCode, final RouteType routeType) throws LaubeException {

		log.debug("[workflowEngine] " + "findRoute start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "companyCode:" + companyCode);
		log.debug("[workflowEngine] " + "routeCode:" + routeCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(routeCode))||(LaubeUtility.isEmpty(routeType))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.debug("[workflowEngine] " + "findRoute end");
			return resultDto;
		}

		ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = new ArrayList<ApprovalRouteInformationAcceptor>();

		final ActivityModelInterface activityModelInterface = ActivityModel.factory(routeType);
		resultDto = activityModelInterface.find(companyCode, routeCode);
		ArrayList<ActivityDto> activityDtos = (ArrayList<ActivityDto>)(resultDto.getResultData());

		final RoleUserModelInterface RoleUserModelInterface = new RoleUserModel();

		for (ActivityDto activityDto : activityDtos) {

			String roleCode = activityDto.getApprovalRoleCode();

			if (!LaubeUtility.isBlank(roleCode)) {

				resultDto = RoleUserModelInterface.findByRoleCode(companyCode, roleCode);
				if (resultDto == null) {
					continue;
				}
				ArrayList<RoleUserDto> roleUserDtos = (ArrayList<RoleUserDto>)resultDto.getResultData();

				for (RoleUserDto roleUserDto : roleUserDtos) {
					ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor = new ApprovalRouteInformationAcceptor();
					approvalRouteInformationAcceptor.setApprovalCompanyCode(roleUserDto.getCompanyCode());
					approvalRouteInformationAcceptor.setApprovalUnitCode(roleUserDto.getUnitCode());
					approvalRouteInformationAcceptor.setApprovalUserCode(roleUserDto.getUserCode());
					approvalRouteInformationAcceptor.setDeputyApprovalComment(null);
					approvalRouteInformationAcceptor.setDeputyApprovalCompanyCode(null);
					approvalRouteInformationAcceptor.setDeputyApprovalUnitCode(null);
					approvalRouteInformationAcceptor.setDeputyApprovalUserCode(null);
					approvalRouteInformationAcceptor.setFunction(activityDto.getFunction());
					approvalRouteInformationAcceptor.setNextPartyCode(activityDto.getNextPartyCode());
					approvalRouteInformationAcceptor.setPartyCode(activityDto.getPartyCode());
					approvalRouteInformationAcceptor.setPartyCodeConnector(activityDto.getPartyCodeConnector());
					approvalRouteInformationAcceptor.setPartyTransitCode(activityDto.getPartyTransitCode());
					approvalRouteInformationAcceptor.setPartyTransitCodeConnector(activityDto.getPartyTransitCodeConnector());
					approvalRouteInformationAcceptor.setRouteType(routeType);
					approvalRouteInformationAcceptors.add(approvalRouteInformationAcceptor);
				}

			} else {
				ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor = new ApprovalRouteInformationAcceptor();
				approvalRouteInformationAcceptor.setApprovalCompanyCode(activityDto.getApprovalCompanyCode());
				approvalRouteInformationAcceptor.setApprovalUnitCode(activityDto.getApprovalUnitCode());
				approvalRouteInformationAcceptor.setApprovalUserCode(activityDto.getApprovalUserCode());
				approvalRouteInformationAcceptor.setDeputyApprovalComment(null);
				approvalRouteInformationAcceptor.setDeputyApprovalCompanyCode(null);
				approvalRouteInformationAcceptor.setDeputyApprovalUnitCode(null);
				approvalRouteInformationAcceptor.setDeputyApprovalUserCode(null);
				approvalRouteInformationAcceptor.setFunction(activityDto.getFunction());
				approvalRouteInformationAcceptor.setNextPartyCode(activityDto.getNextPartyCode());
				approvalRouteInformationAcceptor.setPartyCode(activityDto.getPartyCode());
				approvalRouteInformationAcceptor.setPartyCodeConnector(activityDto.getPartyCodeConnector());
				approvalRouteInformationAcceptor.setPartyTransitCode(activityDto.getPartyTransitCode());
				approvalRouteInformationAcceptor.setPartyTransitCodeConnector(activityDto.getPartyTransitCodeConnector());
				approvalRouteInformationAcceptor.setRouteType(routeType);
				approvalRouteInformationAcceptors.add(approvalRouteInformationAcceptor);
			}
		}
		resultDto.setResultData(approvalRouteInformationAcceptors);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "findRoute end");
		return resultDto;
	}

	/**
	 * find the route master.<br>
	 * @param applyCompanyCode apply company code
	 * @param applyUnitCode apply unit code
	 * @param applyUserCode apply user code
	 * @param applicationFormCode application form code
	 * @param bossCompanyCode boss company code
	 * @param bossUnitCode boss unit code
	 * @param bossUserCode boss user code
	 * @param finalApproverFlag final approver flag
	 * @return ResultDto
	 * @exception LaubeException
	 */
	@SuppressWarnings("unchecked")
	private final ResultDto findRoute(
			final String applyCompanyCode,
			final String applyUnitCode,
			final String applyUserCode,
			final String applicationFormCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findRoute start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "applyCompanyCode:" + applyCompanyCode);
		log.debug("[workflowEngine] " + "applyUnitCode:" + applyUnitCode);
		log.debug("[workflowEngine] " + "applyUserCode:" + applyUserCode);
		log.debug("[workflowEngine] " + "applicationFormCode:" + applicationFormCode);

		final BossModelInterface bossModelInterface = new BossModel();
		ResultDto resultDto = bossModelInterface.findByChainOfResposibility(applyCompanyCode, applyUnitCode, applyUserCode, applicationFormCode);
		ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = new ArrayList<ApprovalRouteInformationAcceptor>();
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.error("[workflowEngine] " + "findRoute end");
			return resultDto;
		}

		if (resultDto.getMessageId().equals("N0002")) {
			return resultDto;
		}

		ArrayList<BossDto> bossDtos = (ArrayList<BossDto>)resultDto.getResultData();

		if ((bossDtos == null)||(bossDtos.size() < 1)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E1002");
			log.error("[workflowEngine] " + "findRoute end");
			return resultDto;
		}

		int partyCodeIndex = 0;
		int partyTransitCodeIndex = 0;

		for (BossDto bossDto : bossDtos) {
			ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor = new ApprovalRouteInformationAcceptor();

			// add initial route
			approvalRouteInformationAcceptor = new ApprovalRouteInformationAcceptor();
			approvalRouteInformationAcceptor.setApprovalCompanyCode(bossDto.getBossCompanyCode());
			approvalRouteInformationAcceptor.setApprovalUnitCode(bossDto.getBossUnitCode());
			approvalRouteInformationAcceptor.setApprovalUserCode(bossDto.getBossUserCode());
			approvalRouteInformationAcceptor.setDeputyApprovalComment(null);
			approvalRouteInformationAcceptor.setDeputyApprovalCompanyCode(null);
			approvalRouteInformationAcceptor.setDeputyApprovalUnitCode(null);
			approvalRouteInformationAcceptor.setDeputyApprovalUserCode(null);
			approvalRouteInformationAcceptor.setFunction(SpecifiedValue.Examination);
			approvalRouteInformationAcceptor.setPartyCode("AP" + String.format("%1$08d", (++partyCodeIndex)));
			approvalRouteInformationAcceptor.setPartyCodeConnector(SpecifiedValue.Unspecified);
			if (bossDtos.size() == partyCodeIndex) {
				approvalRouteInformationAcceptor.setNextPartyCode(SpecifiedValue.END);
			}else{
				approvalRouteInformationAcceptor.setNextPartyCode("AP" + String.format("%1$08d", (partyCodeIndex + 1)));
			}
			approvalRouteInformationAcceptor.setPartyTransitCode("AT" + String.format("%1$08d", (++partyTransitCodeIndex)));
			approvalRouteInformationAcceptor.setPartyTransitCodeConnector(SpecifiedValue.Unspecified);
			approvalRouteInformationAcceptor.setRouteType(SpecifiedValue.IndividualRoute);
			approvalRouteInformationAcceptors.add(approvalRouteInformationAcceptor);
		}
		resultDto.setResultData(approvalRouteInformationAcceptors);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "findRoute end");
		return resultDto;
	}
}
