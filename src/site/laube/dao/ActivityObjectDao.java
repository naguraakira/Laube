package site.laube.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import lombok.val;
import site.laube.daointerface.ActivityObjectDaoInterface;
import site.laube.dto.ActivityObjectDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
import site.laube.utility.LaubeUtility;
import site.laube.utility.type.UserStatus;

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

public final class ActivityObjectDao extends LaubeDao implements ActivityObjectDaoInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(ActivityObjectDao.class);

	/**
	 * register the activity object.<br>
	 * @param activityObjectDtos activity object
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public ResultDto insert(final List<ActivityObjectDto> activityObjectDtos) throws LaubeException {

		log.traceStart("insert", activityObjectDtos);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(activityObjectDtos)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
			return resultDto;
		}

		for (ActivityObjectDto ActivityObjectDto : activityObjectDtos) {
			resultDto = insert(ActivityObjectDto);
			if (!resultDto.isSuccess()){
				log.traceEnd("insert");
				return resultDto;
			}
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("insert");
		return resultDto;
	}

	/**
	 * add the activity object.<br>
	 * @param activityObjectDto activity object
	 * @return ResultDto
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "nls", "boxing" })
	@Override
	public final ResultDto insert(final ActivityObjectDto activityObjectDto) throws LaubeException {

		log.traceStart("insert", activityObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(activityObjectDto)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
			return resultDto;
		}

		try {
			final String companyCode = activityObjectDto.getCompanyCode();
			final int activityObjectCode = activityObjectDto.getActivityObjectCode();
			final String partyCode = activityObjectDto.getPartyCode();
			final int partyCodeConnector = activityObjectDto.getPartyCodeConnector();
			final int routeType = activityObjectDto.getRouteType();
			final String approvalCompanyCode = activityObjectDto.getApprovalCompanyCode();
			final String approvalUnitCode = activityObjectDto.getApprovalUnitCode();
			final String approvalUserCode = activityObjectDto.getApprovalUserCode();
			final String deputyApprovalCompanyCode = activityObjectDto.getDeputyApprovalCompanyCode();
			final String deputyApprovalUserCode = activityObjectDto.getDeputyApprovalUserCode();
			final String deputyApprovalComment = activityObjectDto.getDeputyApprovalComment();
			final int function = activityObjectDto.getFunction();
			final String nextPartyCode = activityObjectDto.getNextPartyCode();
			final String partyTransitCode = activityObjectDto.getPartyTransitCode();
			final int partyTransitCodeConnector = activityObjectDto.getPartyTransitCodeConnector();
			final Timestamp reachingDate = activityObjectDto.getReachingDate();
			final Timestamp processDate = activityObjectDto.getProcessDate();
			final int activityStatus = activityObjectDto.getActivityStatus();
			final String approvalComment = activityObjectDto.getApprovalComment();
			final String createUserID = activityObjectDto.getCreateUserId();
			final String updateUserID = activityObjectDto.getUpdateUserId();

			// cretate sql
			final StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_activity_object(");
			sql.append(" company_code");
			sql.append(",activity_object_code");
			sql.append(",party_code");
			sql.append(",party_code_connector");
			sql.append(",route_type");
			sql.append(",approval_company_code");
			sql.append(",approval_unit_code");
			sql.append(",approval_user_code");
			sql.append(",deputy_approval_company_code");
			sql.append(",deputy_approval_user_code");
			sql.append(",deputy_approval_comment");
			sql.append(",function");
			sql.append(",next_party_code");
			sql.append(",party_transit_code");
			sql.append(",party_transit_code_connector");
			sql.append(",reaching_date");
			sql.append(",process_date");
			sql.append(",activity_status");
			sql.append(",approval_comment");
			sql.append(",create_date_time");
			sql.append(",create_user_id");
			sql.append(",update_date_time");
			sql.append(",update_user_id");
			sql.append(")");
			sql.append(" VALUES(");
			sql.append(" ?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",CURRENT_TIMESTAMP(0)");
			sql.append(",?");
			sql.append(",CURRENT_TIMESTAMP(0)");
			sql.append(",?");
			sql.append(")");
			sql.append(";");

			log.message("insert", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString   ( 1, companyCode);
			this.preparedStatement.setInt      ( 2, activityObjectCode);
			this.preparedStatement.setString   ( 3, partyCode);
			this.preparedStatement.setInt      ( 4, partyCodeConnector);
			this.preparedStatement.setInt      ( 5, routeType);
			this.preparedStatement.setString   ( 6, approvalCompanyCode);
			this.preparedStatement.setString   ( 7, approvalUnitCode);
			this.preparedStatement.setString   ( 8, approvalUserCode);
			this.preparedStatement.setString   ( 9, deputyApprovalCompanyCode);
			this.preparedStatement.setString   (10, deputyApprovalUserCode);
			this.preparedStatement.setString   (11, deputyApprovalComment);
			this.preparedStatement.setInt      (12, function);
			this.preparedStatement.setString   (13, nextPartyCode);
			this.preparedStatement.setString   (14, partyTransitCode);
			this.preparedStatement.setInt      (15, partyTransitCodeConnector);
			this.preparedStatement.setTimestamp(16, reachingDate);
			this.preparedStatement.setTimestamp(17, processDate);
			this.preparedStatement.setInt      (18, activityStatus);
			this.preparedStatement.setString   (19, approvalComment);
			this.preparedStatement.setString   (20, createUserID);
			this.preparedStatement.setString   (21, updateUserID);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException se) {
			throw new LaubeException("insert", se);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("insert", e);
			}
			log.traceEnd("insert");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("insert");
		return resultDto;
	}

	/**
	 * update the activity object.<br>
	 * @param activityObjectDto activity object Dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto update(final ActivityObjectDto activityObjectDto) throws LaubeException {

		log.traceStart("update", activityObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(activityObjectDto)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("update");
			return resultDto;
		}

		try {
			final String deputyApprovalCompanyCode = activityObjectDto.getDeputyApprovalCompanyCode();
			final String deputyApprovalUserCode = activityObjectDto.getDeputyApprovalUserCode();
			final String deputyApprovalComment = activityObjectDto.getDeputyApprovalComment();
			final int    activityStatus = activityObjectDto.getActivityStatus();
			final String approvalComment = activityObjectDto.getApprovalComment();
			final String companyCode = activityObjectDto.getCompanyCode();
			final long applicationNumber = activityObjectDto.getApplicationNumber();
			final String approvalCompanyCode = activityObjectDto.getApprovalCompanyCode();
			final String approvalUserCode = activityObjectDto.getApprovalUserCode();

			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_activity_object");
			sql.append(" SET");
			sql.append(" deputy_approval_company_code = ?");
			sql.append(",deputy_approval_user_code = ?");
			sql.append(",deputy_approval_comment = ?");
			sql.append(",process_date = CURRENT_TIMESTAMP(0)");
			sql.append(",activity_status = ?");
			sql.append(",approval_comment = ?");
			sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(",update_user_id = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_number = ?");
			sql.append(" and approval_company_code = ?");
			sql.append(" and approval_user_code = ?");
			sql.append(";");

			log.message("update","[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString( 1, deputyApprovalCompanyCode);
			this.preparedStatement.setString( 2, deputyApprovalUserCode);
			this.preparedStatement.setString( 3, deputyApprovalComment);
			this.preparedStatement.setInt   ( 4, activityStatus);
			this.preparedStatement.setString( 5, approvalComment);
			this.preparedStatement.setString( 6, approvalUserCode);
			this.preparedStatement.setString( 7, companyCode);
			this.preparedStatement.setLong  ( 8, applicationNumber);
			this.preparedStatement.setString( 9, approvalCompanyCode);
			this.preparedStatement.setString(10, approvalUserCode);
			this.preparedStatement.executeUpdate();
			resultDto.setResultData(applicationNumber);

		} catch (final SQLException se) {
			throw new LaubeException("update", se);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("update", e);
			}
			log.traceEnd("update");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("update");
		return resultDto;
	}

	/**
	 * update the activity object status.<br>
	 * @param activityObjectDto activity object Dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto updateByArrival(final ActivityObjectDto activityObjectDto) throws LaubeException {

		log.traceStart("updateByArrival", activityObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(activityObjectDto)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("updateByArrival");
			return resultDto;
		}

		try {
			final int    activityStatus = UserStatus.Arrival.toInt();
			final String companyCode = activityObjectDto.getCompanyCode();
			final long applicationNumber = activityObjectDto.getApplicationNumber();
			final String approvalCompanyCode = activityObjectDto.getApprovalCompanyCode();
			final String approvalUserCode = activityObjectDto.getApprovalUserCode();

			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_activity_object");
			sql.append(" SET");
			sql.append(" reaching_date = CURRENT_TIMESTAMP(0)");
			sql.append(",activity_status = ?");
			sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(",update_user_id = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_number = ?");
			sql.append(" and approval_company_code = ?");
			sql.append(" and approval_user_code = ?");
			sql.append(";");

			log.message("updateByArrival", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setInt   ( 1, activityStatus);
			this.preparedStatement.setString( 2, approvalUserCode);
			this.preparedStatement.setString( 3, companyCode);
			this.preparedStatement.setLong  ( 4, applicationNumber);
			this.preparedStatement.setString( 5, approvalCompanyCode);
			this.preparedStatement.setString( 6, approvalUserCode);
			this.preparedStatement.executeUpdate();
			resultDto.setResultData(applicationNumber);

		} catch (final SQLException se) {
			throw new LaubeException("updateByArrival", se);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("update", e);
			}
			log.traceEnd("updateByArrival");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("updateByArrival");
		return resultDto;
	}

	/**
	 * update the activity object status.<br>
	 * @param activityObjectDto activity object Dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto updateByAuthorizerApproval(final ActivityObjectDto activityObjectDto) throws LaubeException {

		log.traceStart("updateByAuthorizerApproval", activityObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(activityObjectDto)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("updateByAuthorizerApproval");
			return resultDto;
		}

		try {
			final String deputyApprovalCompanyCode = activityObjectDto.getDeputyApprovalCompanyCode();
			final String deputyApprovalUserCode = activityObjectDto.getDeputyApprovalUserCode();
			final String deputyApprovalComment = activityObjectDto.getDeputyApprovalComment();
			final int    activityStatus = UserStatus.Authorizer_Approval.toInt();
			final String approvalComment = activityObjectDto.getApprovalComment();
			final String companyCode = activityObjectDto.getCompanyCode();
			final long applicationNumber = activityObjectDto.getApplicationNumber();
			final String approvalCompanyCode = activityObjectDto.getApprovalCompanyCode();
			final String approvalUserCode = activityObjectDto.getApprovalUserCode();

			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_activity_object");
			sql.append(" SET");
			sql.append(" deputy_approval_company_code = ?");
			sql.append(",deputy_approval_user_code = ?");
			sql.append(",deputy_approval_comment = ?");
			sql.append(",process_date = CURRENT_TIMESTAMP(0)");
			sql.append(",activity_status = ?");
			sql.append(",approval_comment = ?");
			sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(",update_user_id = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_number = ?");
			sql.append(" and approval_company_code = ?");
			sql.append(" and approval_user_code = ?");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString( 1, deputyApprovalCompanyCode);
			this.preparedStatement.setString( 2, deputyApprovalUserCode);
			this.preparedStatement.setString( 3, deputyApprovalComment);
			this.preparedStatement.setInt   ( 4, activityStatus);
			this.preparedStatement.setString( 5, approvalComment);
			this.preparedStatement.setString( 6, approvalUserCode);
			this.preparedStatement.setString( 7, companyCode);
			this.preparedStatement.setLong  ( 8, applicationNumber);
			this.preparedStatement.setString( 9, approvalCompanyCode);
			this.preparedStatement.setString(10, approvalUserCode);
			this.preparedStatement.executeUpdate();
			resultDto.setResultData(applicationNumber);

		} catch (final SQLException se) {
			throw new LaubeException("updateByAuthorizerApproval", se);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("updateByAuthorizerApproval", e);
			}
			log.traceEnd("updateByAuthorizerApproval");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("updateByAuthorizerApproval");
		return resultDto;
	}

	/**
	 * delete the activity object.<br>
	 * @param applicationNumber application number
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode, final long applicationNumber) throws LaubeException {

		log.traceStart("delete", companyCode, applicationNumber);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isEmpty(applicationNumber))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final String sql = "DELETE FROM wkf_activity_object WHERE company_code = ? and application_number = ?;";

			log.message("delete", "[SQL] " + sql);
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setLong  (2, applicationNumber);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			throw new LaubeException("delete", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("delete", e);
			}
			log.traceEnd("delete");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("delete");
		return resultDto;
	}

	/**
	 * find the activity object by arrival.<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @param approvalCompanyCode approval company code
	 * @param approvalUnitCode approval unit code
	 * @param approvalUserCode approval userCode code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode, final long applicationNumber, final String approvalCompanyCode, final String approvalUnitCode, final String approvalUserCode, final int applovalUserStatus) throws LaubeException {

		log.traceStart("find", companyCode, applicationNumber, approvalCompanyCode, approvalUnitCode, approvalUserCode, applovalUserStatus);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isEmpty(applicationNumber))||(LaubeUtility.isBlank(approvalCompanyCode))||(LaubeUtility.isBlank(approvalUnitCode))||(LaubeUtility.isBlank(approvalUserCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("find");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",application_number");
			sql.append(",activity_object_code");
			sql.append(",party_code");
			sql.append(",party_code_connector");
			sql.append(",route_type");
			sql.append(",approval_company_code");
			sql.append(",approval_company_name");
			sql.append(",approval_unit_code");
			sql.append(",approval_unit_name");
			sql.append(",approval_user_code");
			sql.append(",approval_user_name");
			sql.append(",deputy_approval_company_code");
			sql.append(",deputy_approval_company_name");
			sql.append(",deputy_approval_user_code");
			sql.append(",deputy_approval_user_name");
			sql.append(",deputy_approval_comment");
			sql.append(",function");
			sql.append(",next_party_code");
			sql.append(",party_transit_code");
			sql.append(",party_transit_code_connector");
			sql.append(",reaching_date");
			sql.append(",process_date");
			sql.append(",activity_status");
			sql.append(",approval_comment");
			sql.append(",create_date_time");
			sql.append(",create_user_id");
			sql.append(",update_date_time");
			sql.append(",update_user_id");
			sql.append(" FROM wkf_view_activity_object");
			sql.append(" WHERE");
			sql.append(" company_code = ? and application_number = ? and approval_company_code = ? and approval_unit_code = ? and approval_user_code = ? and activity_status = ?");
			sql.append(";");

			log.message("find", "[SQL] " + sql.toString());

			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setLong  (2, applicationNumber);
			this.preparedStatement.setString(3, approvalCompanyCode);
			this.preparedStatement.setString(4, approvalUnitCode);
			this.preparedStatement.setString(5, approvalUserCode);
			this.preparedStatement.setInt   (6, applovalUserStatus);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("find", "the record was not found. Please investigate the cause by referring to the following.");
				log.message("find", "[SQL]");
				log.message("find", sql.toString());
				log.message("find", " ");
				log.message("find", "[Extraction condition]");
				log.message("find", "[companyCode]: " + companyCode);
				log.message("find", "[applicationNumber]: " + applicationNumber);
				log.message("find", "[approvalUserCode]: " + approvalUserCode);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				log.info("[workflowEngine] " + "find end");
				return resultDto;
			}
			final val result = conversion(this.resultSet, new ActivityObjectDto());

			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(result);
			return resultDto;

		} catch (final SQLException e) {
			throw new LaubeException("find", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("find", e);
			}
			log.traceEnd("find");
		}
	}

	/**
	 * find the activity object by arrival.<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @param approvalCompanyCode approval company code
	 * @param approvalUnitCode approval unit code
	 * @param approvalUserCode approval userCode code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "nls", "boxing" })
	@Override
	public final ResultDto findByArrival(final String companyCode, final long applicationNumber, final String approvalCompanyCode, final String approvalUnitCode, final String approvalUserCode) throws LaubeException {

		log.traceStart("findByArrival", companyCode, applicationNumber, approvalCompanyCode, approvalUnitCode, approvalUserCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isEmpty(applicationNumber))||(LaubeUtility.isBlank(approvalUnitCode))||(LaubeUtility.isBlank(approvalUserCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findByArrival");
			return resultDto;
		}

		final int applovalUserStatus = UserStatus.Arrival.toInt();

		try {

			resultDto = this.find(companyCode, applicationNumber, approvalCompanyCode,  approvalUnitCode, approvalUserCode, applovalUserStatus);

			if (!resultDto.isSuccess()){
				log.info("[workflowEngine] " + "findByArrival end");
				return resultDto;
			}

			log.info("[workflowEngine] " + "findByArrival end");
			return resultDto;

		} catch (final Exception e) {
			throw new LaubeException("findByArrival",e);

		} finally {
			log.traceEnd("findByArrival");
		}
	}
}
