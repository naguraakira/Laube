package site.laube.visitor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import site.laube.model.DeputyApprovelModel;
import site.laube.model.RoleUserModel;
import site.laube.model.UnitModel;
import site.laube.model.UserModel;
import site.laube.modelinterface.ActivityModelInterface;
import site.laube.modelinterface.ApplicationClassificationModelInterface;
import site.laube.modelinterface.ApplicationFormModelInterface;
import site.laube.modelinterface.ApplicationFormRouteModelInterface;
import site.laube.modelinterface.BossModelInterface;
import site.laube.modelinterface.CompanyModelInterface;
import site.laube.modelinterface.DeputyApprovelModelInterface;
import site.laube.modelinterface.RoleUserModelInterface;
import site.laube.modelinterface.UnitModelInterface;
import site.laube.modelinterface.UserModelInterface;
import site.laube.utility.LaubeProperties;
import site.laube.utility.LaubeUtility;
import site.laube.utility.SpecifiedValue;
import site.laube.utility.type.RouteType;

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

public final class VisitorUtility {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RequestSystemVisitor.class);

	/**
	 * commit.<br>
	 * @param connection connection
	 * @return result
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	public static final boolean commit(Connection connection) throws LaubeException {

		log.info("[workflowEngine] " + "commit start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "connection:" + connection);

		if ("true".equals(LaubeProperties.getInstance().getValue("isAutoCommit"))){
			final String errMessage = "auto commit mode now.you can not call this method.";
			log.info("[workflowEngine] " + "commit end");
			throw new LaubeException(errMessage);
		}

		if (LaubeUtility.isEmpty(connection)){
			final String errMessage = "connection is null.";
			log.info("[workflowEngine] " + "commit end");
			throw new LaubeException(errMessage);
		}

		try {
			connection.commit();
			connection.close();
			log.info("[workflowEngine] " + "commit end");
			return true;
		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "commit end");
			return false;
		}
	}

	/**
	 * rollbeck.<br>
	 * @param connection connection
	 * @return result
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	public static final boolean rollback(Connection connection) throws LaubeException {

		log.info("[workflowEngine] " + "rollback start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "connection:" + connection);

		if ("true".equals(LaubeProperties.getInstance().getValue("isAutoCommit"))){
			final String errMessage = "auto commit mode now.you can not call this method.";
			log.info("[workflowEngine] " + "rollback end");
			throw new LaubeException(errMessage);
		}

		if (LaubeUtility.isEmpty(connection)){
			final String errMessage = "connection is null.";
			log.info("[workflowEngine] " + "rollback end");
			throw new LaubeException(errMessage);
		}

		try {
			connection.rollback();
			connection.close();
			log.info("[workflowEngine] " + "rollback end");
			return true;
		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "rollback end");
			return false;
		}
	}

	/**
	 * find the company master.<br>
	 * @param companyCode company code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("unchecked")
	public static final ResultDto findCompany(final String companyCode) throws LaubeException {

		log.info("[workflowEngine] " + "findCompany start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "companyCode:" + companyCode);

		ResultDto resultDto  = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "findCompany end");
			return resultDto;
		}

		final CompanyModelInterface companyModelInterface = new CompanyModel();
		resultDto = companyModelInterface.find(companyCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.info("[workflowEngine] " + "findCompany end");
			return resultDto;
		}

		ArrayList<LaubeDto> companyDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		CompanyDto companyDto = (CompanyDto)companyDtos.get(0);
		resultDto.setResultData(companyDto);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "findCompany end");
		return resultDto;
	}

	/**
	 * find the unit master.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("unchecked")
	public static final ResultDto findUnit(final String companyCode, final String unitCode) throws LaubeException {

		log.info("[workflowEngine] " + "findUnit start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "unitCode:" + unitCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(unitCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "findUnit end");
			return resultDto;
		}

		final UnitModelInterface unitModelInterface = new UnitModel();
		resultDto = unitModelInterface.find(companyCode, unitCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.info("[workflowEngine] " + "findUnit end");
			return resultDto;
		}

		ArrayList<LaubeDto> unitDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		UnitDto applyUnitDto = (UnitDto)unitDtos.get(0);
		resultDto.setResultData(applyUnitDto);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "findUnit end");
		return resultDto;
	}

	/**
	 * find the user master.<br>
	 * @param companyCode ompany code
	 * @param userCode user code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("unchecked")
	public static final ResultDto findUser(final String companyCode, final String userCode) throws LaubeException {

		log.info("[workflowEngine] " + "findUser start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "companyCode:" + companyCode);
		log.info("[workflowEngine] " + "userCode:" + userCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(userCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "findUser end");
			return resultDto;
		}

		final UserModelInterface userModelInterface = new UserModel();
		resultDto = userModelInterface.find(companyCode, userCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.info("[workflowEngine] " + "findUser end");
			return resultDto;
		}

		ArrayList<LaubeDto> userDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		UserDto userDto = (UserDto)userDtos.get(0);
		resultDto.setResultData(userDto);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "findUser end");
		return resultDto;
	}

	/**
	 * find the application form master.<br>
	 * @param companyCode ompany code
	 * @param applicationFormCode application form Code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("unchecked")
	public static final ResultDto findApplicationForm(final String companyCode, final String applicationFormCode) throws LaubeException {

		log.info("[workflowEngine] " + "findApplicationForm start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "companyCode:" + companyCode);
		log.info("[workflowEngine] " + "applicationFormCode:" + applicationFormCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "findApplicationForm end");
			return resultDto;
		}

		final ApplicationFormModelInterface applicationFormModelInterface = new ApplicationFormModel();
		resultDto = applicationFormModelInterface.findByApplicationFormCode(companyCode, applicationFormCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.info("[workflowEngine] " + "findApplicationForm end");
			return resultDto;
		}

		ArrayList<LaubeDto> applicationFormDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		ApplicationFormDto applicationFormDto = (ApplicationFormDto)applicationFormDtos.get(0);
		resultDto.setResultData(applicationFormDto);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "findApplicationForm end");
		return resultDto;
	}

	/**
	 * find the application form route master.<br>
	 * @param companyCode ompany code
	 * @param applicationFormCode application form Code
	 * @param unitCode unit Code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("unchecked")
	public static final ResultDto findApplicationFormRoute(final String companyCode, final String applicationFormCode, final String unitCode) throws LaubeException {

		log.info("[workflowEngine] " + "findApplicationFormRoute start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "companyCode:" + companyCode);
		log.info("[workflowEngine] " + "applicationFormCode:" + applicationFormCode);
		log.info("[workflowEngine] " + "unitCode:" + unitCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))||(LaubeUtility.isBlank(unitCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "findApplicationFormRoute end");
			return resultDto;
		}

		final ApplicationFormRouteModelInterface applicationFormRouteModelInterface = new ApplicationFormRouteModel();
		resultDto = applicationFormRouteModelInterface.find(companyCode, applicationFormCode, unitCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.info("[workflowEngine] " + "findApplicationFormRoute end");
			return resultDto;
		}

		ArrayList<LaubeDto> applicationFormRouteDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		ApplicationFormRouteDto applicationFormRouteDto = (ApplicationFormRouteDto)applicationFormRouteDtos.get(0);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		resultDto.setResultData(applicationFormRouteDto);
		log.info("[workflowEngine] " + "findApplicationFormRoute end");
		return resultDto;
	}

	/**
	 * find the application classification master.<br>
	 * @param companyCode ompany code
	 * @param applicationClassificationCode application classification code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("unchecked")
	public static final ResultDto findApplicationClassification(final String companyCode, final String applicationClassificationCode) throws LaubeException {

		log.info("[workflowEngine] " + "findApplicationClassification start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "companyCode:" + companyCode);
		log.info("[workflowEngine] " + "applicationClassificationCode:" + applicationClassificationCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationClassificationCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "findApplicationClassification end");
			return resultDto;
		}

		final ApplicationClassificationModelInterface applicationClassificationModelInterfaces = new ApplicationClassificationModel();
		resultDto = applicationClassificationModelInterfaces.findByApplicationClassificationCode(companyCode, applicationClassificationCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.info("[workflowEngine] " + "findApplicationClassification end");
			return resultDto;
		}

		ArrayList<LaubeDto> ApplicationClassificationDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		ApplicationClassificationDto applicationClassificationDto = (ApplicationClassificationDto)ApplicationClassificationDtos.get(0);
		resultDto.setResultData(applicationClassificationDto);
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "findApplicationClassification end");
		return resultDto;
	}

	/**
	 * find the route master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @param routeType route type
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("unchecked")
	public static final ResultDto findRoute(final String companyCode, final String routeCode, final RouteType routeType) throws LaubeException {

		log.info("[workflowEngine] " + "findRoute start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "companyCode:" + companyCode);
		log.info("[workflowEngine] " + "routeCode:" + routeCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(routeCode))||(LaubeUtility.isEmpty(routeType))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "findRoute end");
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
				if (LaubeUtility.isEmpty(resultDto)) {
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
		log.info("[workflowEngine] " + "findRoute end");
		return resultDto;
	}

	/**
	 * find the route master.<br>
	 * @param applyCompanyCode apply company code
	 * @param applyUnitCode apply unit code
	 * @param applyUserCode apply user code
	 * @param applicationFormCode application form code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("unchecked")
	public static final ResultDto findRoute(
			final String applyCompanyCode,
			final String applyUnitCode,
			final String applyUserCode,
			final String applicationFormCode) throws LaubeException {

		log.info("[workflowEngine] " + "findRoute start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "applyCompanyCode:" + applyCompanyCode);
		log.info("[workflowEngine] " + "applyUnitCode:" + applyUnitCode);
		log.info("[workflowEngine] " + "applyUserCode:" + applyUserCode);
		log.info("[workflowEngine] " + "applicationFormCode:" + applicationFormCode);

		final BossModelInterface bossModelInterface = new BossModel();
		ResultDto resultDto = bossModelInterface.findByChainOfResposibility(applyCompanyCode, applyUnitCode, applyUserCode, applicationFormCode);
		ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = new ArrayList<ApprovalRouteInformationAcceptor>();
		if (LaubeUtility.isEmpty(resultDto)) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.info("[workflowEngine] " + "findRoute end");
			return resultDto;
		}

		if (resultDto.getMessageId().equals("N0002")) {
			return resultDto;
		}

		ArrayList<BossDto> bossDtos = (ArrayList<BossDto>)resultDto.getResultData();

		if ((LaubeUtility.isEmpty(bossDtos))||(bossDtos.size() < 1)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E1002");
			log.info("[workflowEngine] " + "findRoute end");
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
		log.info("[workflowEngine] " + "findRoute end");
		return resultDto;
	}

	/**
	 * <br>
	 * @param companyCode company code
	 * @param approvalUserCode approval user code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	public static final ResultDto getDeputyApprovalList(final String companyCode, final String approvalUserCode) throws LaubeException {

		log.info("[workflowEngine] " + "getDeputyApprovalList start.");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] "  + "companyCode:" + companyCode);
		log.info("[workflowEngine] "  + "approvalUserCode:" + approvalUserCode);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(companyCode)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "visit end");
			return resultDto;
		}

		if (LaubeUtility.isEmpty(approvalUserCode)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "visit end");
			return resultDto;
		}

		final DeputyApprovelModelInterface deputyApprovelModelInterface = new DeputyApprovelModel();
		resultDto = deputyApprovelModelInterface.find(companyCode, approvalUserCode);

		if (!resultDto.isSuccess()) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.info("[workflowEngine] " + "visit end");
			return resultDto;
		}
		return resultDto;
	}

	/**
	 * update the next approver in waiting for approval. In addition, in the case was the final approval who will update the application object table to the approved.<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	public static final ResultDto circulatedNextApprover(final String companyCode, final long applicationNumber) throws LaubeException {

		log.info("[workflowEngine] " + "circulatedNextApprover start.");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] "  + "companyCode:" + companyCode);
		log.info("[workflowEngine] "  + "applicationNumber:" + applicationNumber);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(companyCode)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "visit end");
			return resultDto;
		}

		if (LaubeUtility.isEmpty(applicationNumber)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "visit end");
			return resultDto;
		}














		if (!resultDto.isSuccess()) {
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E1999");
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.info("[workflowEngine] " + "visit end");
			return resultDto;
		}









		log.info("[workflowEngine] " + "circulatedNextApprover end.");
		return resultDto;
	}
}
