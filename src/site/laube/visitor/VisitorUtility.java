package site.laube.visitor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.dao.ActivityDao;
import site.laube.dao.ApplicationClassificationDao;
import site.laube.dao.ApplicationFormDao;
import site.laube.dao.ApplicationFormRouteDao;
import site.laube.dao.BossDao;
import site.laube.dao.CompanyDao;
import site.laube.dao.DeputyApprovelDao;
import site.laube.dao.RoleUserDao;
import site.laube.dao.UnitDao;
import site.laube.dao.UserDao;
import site.laube.daointerface.ActivityDaoInterface;
import site.laube.daointerface.ApplicationClassificationDaoInterface;
import site.laube.daointerface.ApplicationFormDaoInterface;
import site.laube.daointerface.ApplicationFormRouteDaoInterface;
import site.laube.daointerface.BossDaoInterface;
import site.laube.daointerface.CompanyDaoInterface;
import site.laube.daointerface.DeputyApprovelDaoInterface;
import site.laube.daointerface.RoleUserDaoInterface;
import site.laube.daointerface.UnitDaoInterface;
import site.laube.daointerface.UserDaoInterface;
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
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
import site.laube.utility.LaubeProperties;
import site.laube.utility.LaubeUtility;
import site.laube.utility.SpecifiedValue;
import site.laube.utility.type.ApprovalFunction;
import site.laube.utility.type.Connector;
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
	private static LaubeLogger log = new LaubeLogger(LaubeLoggerFactory.getLogger(VisitorUtility.class));

	/**
	 * commit.<br>
	 * @param connection connection
	 * @return result
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public static final boolean commit(Connection connection) throws LaubeException {

		log.traceStart("commit", connection);

		if ("true".equals(LaubeProperties.getValue("isAutoCommit"))){
			final String errMessage = "auto commit mode now.you can not call this method.";
			throw new LaubeException("commit", errMessage);
		}

		if (LaubeUtility.isEmpty(connection)){
			final String errMessage = "connection is null.";
			throw new LaubeException("commit", errMessage);
		}

		try {
			connection.commit();
			connection.close();
			log.traceEnd("commit");
			return true;
		} catch (final SQLException e) {
			log.traceEnd("commit");
			return false;
		}
	}

	/**
	 * rollbeck.<br>
	 * @param connection connection
	 * @return result
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public static final boolean rollback(Connection connection) throws LaubeException {

		log.traceStart("rollback", connection);

		if ("true".equals(LaubeProperties.getValue("isAutoCommit"))){
			final String errMessage = "auto commit mode now.you can not call this method.";
			throw new LaubeException("rollback", errMessage);
		}

		if (LaubeUtility.isEmpty(connection)){
			final String errMessage = "connection is null.";
			throw new LaubeException("rollback", errMessage);
		}

		try {
			connection.rollback();
			connection.close();
			log.traceEnd("rollback");
			return true;
		} catch (final SQLException e) {
			log.traceEnd("rollback");
			return false;
		}
	}

	/**
	 * find the company master.<br>
	 * @param companyCode company code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "unchecked", "nls" })
	public static final ResultDto findCompany(final String companyCode) throws LaubeException {

		log.traceStart("findCompany", companyCode);

		ResultDto resultDto  = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findCompany");
			return resultDto;
		}

		final CompanyDaoInterface companyModelInterface = new CompanyDao();
		resultDto = companyModelInterface.find(companyCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.traceEnd("findCompany");
			return resultDto;
		}

		ArrayList<LaubeDto> companyDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		CompanyDto companyDto = (CompanyDto)companyDtos.get(0);
		resultDto.setResultData(companyDto);
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("findCompany");
		return resultDto;
	}

	/**
	 * find the unit master.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "unchecked", "nls" })
	public static final ResultDto findUnit(final String companyCode, final String unitCode) throws LaubeException {

		log.traceStart("findUnit", companyCode, unitCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(unitCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findCompany");
			return resultDto;
		}

		final UnitDaoInterface unitModelInterface = new UnitDao();
		resultDto = unitModelInterface.find(companyCode, unitCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.traceEnd("findCompany");
			return resultDto;
		}

		ArrayList<LaubeDto> unitDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		UnitDto applyUnitDto = (UnitDto)unitDtos.get(0);
		resultDto.setResultData(applyUnitDto);
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("findCompany");
		return resultDto;
	}

	/**
	 * find the user master.<br>
	 * @param companyCode ompany code
	 * @param userCode user code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "unchecked", "nls" })
	public static final ResultDto findUser(final String companyCode, final String userCode) throws LaubeException {

		log.traceStart("findUser", companyCode, userCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(userCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findUser");
			return resultDto;
		}

		final UserDaoInterface userModelInterface = new UserDao();
		resultDto = userModelInterface.find(companyCode, userCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.traceEnd("findUser");
			return resultDto;
		}

		ArrayList<LaubeDto> userDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		UserDto userDto = (UserDto)userDtos.get(0);
		resultDto.setResultData(userDto);
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("findUser");
		return resultDto;
	}

	/**
	 * find the application form master.<br>
	 * @param companyCode ompany code
	 * @param applicationFormCode application form Code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "unchecked", "nls" })
	public static final ResultDto findApplicationForm(final String companyCode, final String applicationFormCode) throws LaubeException {

		log.traceStart("findApplicationForm", companyCode, applicationFormCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findApplicationForm");
			return resultDto;
		}

		final ApplicationFormDaoInterface applicationFormModelInterface = new ApplicationFormDao();
		resultDto = applicationFormModelInterface.findByApplicationFormCode(companyCode, applicationFormCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.traceEnd("findApplicationForm");
			return resultDto;
		}

		ArrayList<LaubeDto> applicationFormDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		ApplicationFormDto applicationFormDto = (ApplicationFormDto)applicationFormDtos.get(0);
		resultDto.setResultData(applicationFormDto);
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("findApplicationForm");
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
	@SuppressWarnings({ "unchecked", "nls" })
	public static final ResultDto findApplicationFormRoute(final String companyCode, final String applicationFormCode, final String unitCode) throws LaubeException {

		log.traceStart("findApplicationFormRoute", companyCode, applicationFormCode, unitCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))||(LaubeUtility.isBlank(unitCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findApplicationFormRoute");
			return resultDto;
		}

		final ApplicationFormRouteDaoInterface applicationFormRouteModelInterface = new ApplicationFormRouteDao();
		resultDto = applicationFormRouteModelInterface.find(companyCode, applicationFormCode, unitCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.traceEnd("findApplicationFormRoute");
			return resultDto;
		}

		ArrayList<LaubeDto> applicationFormRouteDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		ApplicationFormRouteDto applicationFormRouteDto = (ApplicationFormRouteDto)applicationFormRouteDtos.get(0);
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		resultDto.setResultData(applicationFormRouteDto);
		log.traceEnd("findApplicationFormRoute");
		return resultDto;
	}

	/**
	 * find the application classification master.<br>
	 * @param companyCode ompany code
	 * @param applicationClassificationCode application classification code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "unchecked", "nls" })
	public static final ResultDto findApplicationClassification(final String companyCode, final String applicationClassificationCode) throws LaubeException {

		log.traceStart("findApplicationClassification", companyCode, applicationClassificationCode);

		ResultDto resultDto  = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationClassificationCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findApplicationClassification");
			return resultDto;
		}

		final ApplicationClassificationDaoInterface applicationClassificationModelInterfaces = new ApplicationClassificationDao();
		resultDto = applicationClassificationModelInterfaces.findByApplicationClassificationCode(companyCode, applicationClassificationCode);
		if (LaubeUtility.isEmpty(resultDto)) {
			log.traceEnd("findApplicationClassification");
			return resultDto;
		}

		ArrayList<LaubeDto> ApplicationClassificationDtos = (ArrayList<LaubeDto>)resultDto.getResultData();;

		ApplicationClassificationDto applicationClassificationDto = (ApplicationClassificationDto)ApplicationClassificationDtos.get(0);
		resultDto.setResultData(applicationClassificationDto);
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("findApplicationClassification");
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
	@SuppressWarnings({ "unchecked", "nls" })
	public static final ResultDto findRoute(final String companyCode, final String routeCode, final RouteType routeType) throws LaubeException {

		log.traceStart("findRoute", companyCode, routeCode, routeType);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(routeCode))||(LaubeUtility.isEmpty(routeType))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findRoute");
			return resultDto;
		}

		ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = new ArrayList<ApprovalRouteInformationAcceptor>();

		final ActivityDaoInterface activityModelInterface = ActivityDao.factory(routeType);
		resultDto = activityModelInterface.find(companyCode, routeCode);
		ArrayList<ActivityDto> activityDtos = (ArrayList<ActivityDto>)(resultDto.getResultData());

		final RoleUserDaoInterface RoleUserModelInterface = new RoleUserDao();

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
					approvalRouteInformationAcceptor.setRouteType(routeType.toInt());
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
				approvalRouteInformationAcceptor.setRouteType(routeType.toInt());
				approvalRouteInformationAcceptors.add(approvalRouteInformationAcceptor);
			}
		}
		resultDto.setResultData(approvalRouteInformationAcceptors);
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("findRoute");
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
	@SuppressWarnings({ "unchecked", "nls" })
	public static final ResultDto findRoute(
			final String applyCompanyCode,
			final String applyUnitCode,
			final String applyUserCode,
			final String applicationFormCode) throws LaubeException {

		log.traceStart("findRoute", applyCompanyCode, applyUnitCode, applyUserCode, applicationFormCode);

		final BossDaoInterface bossModelInterface = new BossDao();
		ResultDto resultDto = bossModelInterface.findByChainOfResposibility(applyCompanyCode, applyUnitCode, applyUserCode, applicationFormCode);
		ArrayList<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors = new ArrayList<ApprovalRouteInformationAcceptor>();
		if (LaubeUtility.isEmpty(resultDto)) {
			log.traceEnd("findRoute");
			return resultDto;
		}

		if (resultDto.getMessageId().equals("N0002")) {
			return resultDto;
		}

		ArrayList<BossDto> bossDtos = (ArrayList<BossDto>)resultDto.getResultData();

		if ((LaubeUtility.isEmpty(bossDtos))||(bossDtos.size() < 1)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E1002");
			log.traceEnd("findRoute");
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
			approvalRouteInformationAcceptor.setFunction(ApprovalFunction.Examination.toInt());
			approvalRouteInformationAcceptor.setPartyCode("AP" + String.format("%1$08d", (++partyCodeIndex)));
			approvalRouteInformationAcceptor.setPartyCodeConnector(Connector.Unspecified.toInt());
			if (bossDtos.size() == partyCodeIndex) {
				approvalRouteInformationAcceptor.setNextPartyCode(SpecifiedValue.END);
			}else{
				approvalRouteInformationAcceptor.setNextPartyCode("AP" + String.format("%1$08d", (partyCodeIndex + 1)));
			}
			approvalRouteInformationAcceptor.setPartyTransitCode("AT" + String.format("%1$08d", (++partyTransitCodeIndex)));
			approvalRouteInformationAcceptor.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			approvalRouteInformationAcceptor.setRouteType(RouteType.IndividualRoute.toInt());
			approvalRouteInformationAcceptors.add(approvalRouteInformationAcceptor);
		}
		resultDto.setResultData(approvalRouteInformationAcceptors);
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("findRoute");
		return resultDto;
	}

	/**
	 * <br>
	 * @param companyCode company code
	 * @param approvalUserCode approval user code
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public static final ResultDto getDeputyApprovalList(final String companyCode, final String approvalUserCode) throws LaubeException {

		log.traceStart("getDeputyApprovalList", companyCode, approvalUserCode);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(companyCode)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("getDeputyApprovalList");
			return resultDto;
		}

		if (LaubeUtility.isEmpty(approvalUserCode)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("getDeputyApprovalList");
			return resultDto;
		}

		final DeputyApprovelDaoInterface deputyApprovelModelInterface = new DeputyApprovelDao();
		resultDto = deputyApprovelModelInterface.find(companyCode, approvalUserCode);

		if (!resultDto.isSuccess()) {
			log.traceEnd("getDeputyApprovalList");
			return resultDto;
		}
		log.traceEnd("getDeputyApprovalList");
		return resultDto;
	}

	/**
	 * update the next approver in waiting for approval. In addition, in the case was the final approval who will update the application object table to the approved.<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public static final ResultDto circulatedNextApprover(final String companyCode, final long applicationNumber) throws LaubeException {

		log.traceStart("circulatedNextApprover", companyCode, applicationNumber);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(companyCode)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("circulatedNextApprover");
			return resultDto;
		}

		if (LaubeUtility.isEmpty(applicationNumber)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("circulatedNextApprover");
			return resultDto;
		}














		if (!resultDto.isSuccess()) {
			resultDto = new ResultDto();
			resultDto.setSuccess(false);
			resultDto.setMessageId("E1999");
			log.traceEnd("circulatedNextApprover");
			return resultDto;
		}









		log.traceEnd("circulatedNextApprover");
		return resultDto;
	}
}
