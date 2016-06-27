package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.ActivityObjectDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.ActivityObjectModelInterface;
import site.laube.utility.LaubeUtility;
import site.laube.utility.SpecifiedValue;

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

public final class ActivityObjectModel extends LaubeModel implements ActivityObjectModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ActivityObjectModel.class);

	/**
	 * register the activity object.<br>
	 * @param ActivityObjectDtos activity object
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public ResultDto insert(final List<ActivityObjectDto> ActivityObjectDtos) throws LaubeException {

		log.debug("[workflowEngine] " + "insert Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[ActivityObjectDtos]: " + ActivityObjectDtos);

		ResultDto resultDto = new ResultDto();

		if (ActivityObjectDtos == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		for (ActivityObjectDto ActivityObjectDto : ActivityObjectDtos) {
			resultDto = insert(ActivityObjectDto);
			if (!resultDto.isSuccess()){
				return resultDto;
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		return resultDto;
	}

	/**
	 * add the activity object.<br>
	 * @param activityObjectDto activity object
	 * @return ResultDto
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	@Override
	public final ResultDto insert(final ActivityObjectDto activityObjectDto) throws LaubeException {

		log.debug("[workflowEngine] " + "insert Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[activityObjectDto]: " + activityObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (activityObjectDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			final String companyCode = activityObjectDto.getCompanyCode();
			final int applicationNumber = activityObjectDto.getApplicationNumber();
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
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_activity_object( ");
			sql.append("company_code,");
			sql.append("application_number,");
			sql.append("activity_object_code,");
			sql.append("party_code,");
			sql.append("party_code_connector,");
			sql.append("route_type,");
			sql.append("approval_company_code,");
			sql.append("approval_unit_code,");
			sql.append("approval_user_code,");
			sql.append("deputy_approval_company_code,");
			sql.append("deputy_approval_user_code,");
			sql.append("deputy_approval_comment,");
			sql.append("function,");
			sql.append("next_party_code,");
			sql.append("party_transit_code,");
			sql.append("party_transit_code_connector,");
			sql.append("reaching_date,");
			sql.append("process_date,");
			sql.append("activity_status,");
			sql.append("approval_comment,");
			sql.append("create_date_time,");
			sql.append("create_user_id,");
			sql.append("update_date_time,");
			sql.append("update_user_id) ");
			sql.append("VALUES(");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("? ");
			sql.append(");");

			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString   ( 1, companyCode);
			this.preparedStatement.setInt      ( 2, applicationNumber);
			this.preparedStatement.setInt      ( 3, activityObjectCode);
			this.preparedStatement.setString   ( 4, partyCode);
			this.preparedStatement.setInt      ( 5, partyCodeConnector);
			this.preparedStatement.setInt      ( 6, routeType);
			this.preparedStatement.setString   ( 7, approvalCompanyCode);
			this.preparedStatement.setString   ( 8, approvalUnitCode);
			this.preparedStatement.setString   ( 9, approvalUserCode);
			this.preparedStatement.setString   (10, deputyApprovalCompanyCode);
			this.preparedStatement.setString   (11, deputyApprovalUserCode);
			this.preparedStatement.setString   (12, deputyApprovalComment);
			this.preparedStatement.setInt      (13, function);
			this.preparedStatement.setString   (14, nextPartyCode);
			this.preparedStatement.setString   (15, partyTransitCode);
			this.preparedStatement.setInt      (16, partyTransitCodeConnector);
			this.preparedStatement.setTimestamp(17, reachingDate);
			this.preparedStatement.setTimestamp(18, processDate);
			this.preparedStatement.setInt      (19, activityStatus);
			this.preparedStatement.setString   (20, approvalComment);
			this.preparedStatement.setString   (21, createUserID);
			this.preparedStatement.setString   (22, updateUserID);
			this.preparedStatement.executeUpdate();
			resultDto.setResultData(applicationNumber);

		} catch (SQLException se) {
			throw new LaubeException(se);

		} finally {

			try {

				if (this.resultSet != null) {
					this.resultSet.close();
					this.resultSet = null;
				}

				if (this.preparedStatement != null) {
					this.preparedStatement.close();
					this.preparedStatement = null;
				}

			} catch (Exception e) {
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		return resultDto;
	}

	/**
	 * update the activity object.<br>
	 * @param activityObjectDto activity object Dto
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto update(final ActivityObjectDto activityObjectDto) throws LaubeException {

		log.debug("[workflowEngine] " + "update Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[activityObjectDto]: " + activityObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (activityObjectDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "update End");
			return resultDto;
		}

		try {
			final String deputyApprovalCompanyCode = activityObjectDto.getDeputyApprovalCompanyCode();
			final String deputyApprovalUserCode = activityObjectDto.getDeputyApprovalUserCode();
			final String deputyApprovalComment = activityObjectDto.getDeputyApprovalComment();
			final int    activityStatus = activityObjectDto.getActivityStatus();
			final String approvalComment = activityObjectDto.getApprovalComment();
			final String companyCode = activityObjectDto.getCompanyCode();
			final int applicationNumber = activityObjectDto.getApplicationNumber();
			final String approvalCompanyCode = activityObjectDto.getApprovalCompanyCode();
			final String approvalUserCode = activityObjectDto.getApprovalUserCode();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE ");
			sql.append("wkf_activity_object ");
			sql.append("SET ");
			sql.append("deputy_approval_company_code = ?, ");
			sql.append("deputy_approval_user_code = ?, ");
			sql.append("deputy_approval_comment = ?, ");
			sql.append("process_date = CURRENT_TIMESTAMP(0), ");
			sql.append("activity_status = ?, ");
			sql.append("approval_comment = ?, ");
			sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
			sql.append("update_user_id = ? ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("application_number = ? AND ");
			sql.append("approval_company_code = ? AND ");
			sql.append("approval_user_code = ?;");

			log.debug("[workflowEngine] " + "SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString( 1, deputyApprovalCompanyCode);
			this.preparedStatement.setString( 2, deputyApprovalUserCode);
			this.preparedStatement.setString( 3, deputyApprovalComment);
			this.preparedStatement.setInt   ( 4, activityStatus);
			this.preparedStatement.setString( 5, approvalComment);
			this.preparedStatement.setString( 6, approvalUserCode);
			this.preparedStatement.setString( 7, companyCode);
			this.preparedStatement.setInt   ( 8, applicationNumber);
			this.preparedStatement.setString( 9, approvalCompanyCode);
			this.preparedStatement.setString(10, approvalUserCode);
			this.preparedStatement.executeUpdate();
			resultDto.setResultData(applicationNumber);

		} catch (SQLException se) {
			log.error("[workflowEngine] " + "update End");
			throw new LaubeException(se);

		} finally {

			try {
				if (this.resultSet != null) {
					this.resultSet.close();
					this.resultSet = null;
				}
				if (this.preparedStatement != null) {
					this.preparedStatement.close();
					this.preparedStatement = null;
				}
			} catch (Exception e) {
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "update End");
		return resultDto;
	}

	/**
	 * update the activity object status.<br>
	 * @param activityObjectDto activity object Dto
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto updateByArrival(final ActivityObjectDto activityObjectDto) throws LaubeException {

		log.debug("[workflowEngine] " + "updateByArrival Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[activityObjectDto]: " + activityObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (activityObjectDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "updateByArrival End");
			return resultDto;
		}

		try {
			final int    activityStatus = SpecifiedValue.Arrival;
			final String companyCode = activityObjectDto.getCompanyCode();
			final int applicationNumber = activityObjectDto.getApplicationNumber();
			final String approvalCompanyCode = activityObjectDto.getApprovalCompanyCode();
			final String approvalUserCode = activityObjectDto.getApprovalUserCode();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE ");
			sql.append("wkf_activity_object ");
			sql.append("SET ");
			sql.append("reaching_date = CURRENT_TIMESTAMP(0), ");
			sql.append("activity_status = ?, ");
			sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
			sql.append("update_user_id = ? ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("application_number = ? AND ");
			sql.append("approval_company_code = ? AND ");
			sql.append("approval_user_code = ?;");

			log.debug("[workflowEngine] " + "SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setInt   ( 1, activityStatus);
			this.preparedStatement.setString( 2, approvalUserCode);
			this.preparedStatement.setString( 3, companyCode);
			this.preparedStatement.setInt   ( 4, applicationNumber);
			this.preparedStatement.setString( 5, approvalCompanyCode);
			this.preparedStatement.setString( 6, approvalUserCode);
			this.preparedStatement.executeUpdate();
			resultDto.setResultData(applicationNumber);

		} catch (SQLException se) {
			log.error("[workflowEngine] " + "updateByArrival End");
			throw new LaubeException(se);

		} finally {

			try {
				if (this.resultSet != null) {
					this.resultSet.close();
					this.resultSet = null;
				}
				if (this.preparedStatement != null) {
					this.preparedStatement.close();
					this.preparedStatement = null;
				}
			} catch (Exception e) {
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "updateByArrival End");
		return resultDto;
	}

	/**
	 * update the activity object status.<br>
	 * @param activityObjectDto activity object Dto
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto updateByAuthorizerApproval(final ActivityObjectDto activityObjectDto) throws LaubeException {

		log.debug("[workflowEngine] " + "updateByAuthorizerApproval Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[activityObjectDto]: " + activityObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (activityObjectDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "updateByAuthorizerApproval End");
			return resultDto;
		}

		try {
			final String deputyApprovalCompanyCode = activityObjectDto.getDeputyApprovalCompanyCode();
			final String deputyApprovalUserCode = activityObjectDto.getDeputyApprovalUserCode();
			final String deputyApprovalComment = activityObjectDto.getDeputyApprovalComment();
			final int    activityStatus = SpecifiedValue.Authorizer_Approval;
			final String approvalComment = activityObjectDto.getApprovalComment();
			final String companyCode = activityObjectDto.getCompanyCode();
			final int applicationNumber = activityObjectDto.getApplicationNumber();
			final String approvalCompanyCode = activityObjectDto.getApprovalCompanyCode();
			final String approvalUserCode = activityObjectDto.getApprovalUserCode();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE ");
			sql.append("wkf_activity_object ");
			sql.append("SET ");
			sql.append("deputy_approval_company_code = ?, ");
			sql.append("deputy_approval_user_code = ?, ");
			sql.append("deputy_approval_comment = ?, ");
			sql.append("process_date = CURRENT_TIMESTAMP(0), ");
			sql.append("activity_status = ?, ");
			sql.append("approval_comment = ?, ");
			sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
			sql.append("update_user_id = ? ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("application_number = ? AND ");
			sql.append("approval_company_code = ? AND ");
			sql.append("approval_user_code = ?;");

			log.debug("[workflowEngine] " + "SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString( 1, deputyApprovalCompanyCode);
			this.preparedStatement.setString( 2, deputyApprovalUserCode);
			this.preparedStatement.setString( 3, deputyApprovalComment);
			this.preparedStatement.setInt   ( 4, activityStatus);
			this.preparedStatement.setString( 5, approvalComment);
			this.preparedStatement.setString( 6, approvalUserCode);
			this.preparedStatement.setString( 7, companyCode);
			this.preparedStatement.setInt   ( 8, applicationNumber);
			this.preparedStatement.setString( 9, approvalCompanyCode);
			this.preparedStatement.setString(10, approvalUserCode);
			this.preparedStatement.executeUpdate();
			resultDto.setResultData(applicationNumber);

		} catch (SQLException se) {
			log.error("[workflowEngine] " + "updateByAuthorizerApproval End");
			throw new LaubeException(se);

		} finally {

			try {
				if (this.resultSet != null) {
					this.resultSet.close();
					this.resultSet = null;
				}
				if (this.preparedStatement != null) {
					this.preparedStatement.close();
					this.preparedStatement = null;
				}
			} catch (Exception e) {
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "updateByAuthorizerApproval End");
		return resultDto;
	}

	/**
	 * delete the activity object.<br>
	 * @param applicationNumber application number
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto delete(final String companyCode, final int applicationNumber) throws LaubeException {

		ResultDto resultDto = new ResultDto();
		try {

			String sql = "DELETE FROM wkf_activity_object WHERE company_code = ? AND application_number = ?;";
			this.preparedStatement = connection.prepareStatement(sql);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setInt   (2, applicationNumber);
			this.preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new LaubeException(e);

		} finally {
			try {
				if (this.resultSet != null) {
					this.resultSet.close();
					this.resultSet = null;
				}

				if (this.preparedStatement != null) {
					this.preparedStatement.close();
					this.preparedStatement = null;
				}

			} catch (Exception e) {
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
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
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto find(final String companyCode, final int applicationNumber, final String approvalCompanyCode, final String approvalUnitCode, final String approvalUserCode, final int applovalUserStatus) throws LaubeException {

		log.debug("[workflowEngine] " + "find Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: "  + companyCode);
		log.debug("[workflowEngine] " + "[applicationNumber]: "  + applicationNumber);
		log.debug("[workflowEngine] " + "[approvalCompanyCode]: "    + approvalCompanyCode);
		log.debug("[workflowEngine] " + "[approvalUnitCode]: "    + approvalUnitCode);
		log.debug("[workflowEngine] " + "[approvalUserCode]: "    + approvalUserCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(applicationNumber == 0)||(LaubeUtility.isBlank(approvalCompanyCode))||(LaubeUtility.isBlank(approvalUnitCode))||(LaubeUtility.isBlank(approvalUserCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("application_number, ");
			sql.append("activity_object_code, ");
			sql.append("party_code, ");
			sql.append("party_code_connector, ");
			sql.append("route_type, ");
			sql.append("approval_company_code, ");
			sql.append("approval_company_name, ");
			sql.append("approval_unit_code, ");
			sql.append("approval_unit_name, ");
			sql.append("approval_user_code, ");
			sql.append("approval_user_name, ");
			sql.append("deputy_approval_company_code, ");
			sql.append("deputy_approval_company_name, ");
			sql.append("deputy_approval_user_code, ");
			sql.append("deputy_approval_user_name, ");
			sql.append("deputy_approval_comment, ");
			sql.append("function, ");
			sql.append("next_party_code, ");
			sql.append("party_transit_code, ");
			sql.append("party_transit_code_connector, ");
			sql.append("reaching_date, ");
			sql.append("process_date, ");
			sql.append("activity_status, ");
			sql.append("approval_comment, ");
			sql.append("create_date_time, ");
			sql.append("create_user_id, ");
			sql.append("update_date_time, ");
			sql.append("update_user_id ");
			sql.append("FROM ");
			sql.append("wkf_view_activity_object");
			sql.append(" ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND application_number = ? AND approval_company_code = ? AND approval_unit_code = ? AND approval_user_code = ? AND activity_status = ?");
			sql.append(";");

			log.debug("[workflowEngine] SQL:" + sql.toString());

			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setInt   (2, applicationNumber);
			this.preparedStatement.setString(3, approvalCompanyCode);
			this.preparedStatement.setString(4, approvalUnitCode);
			this.preparedStatement.setString(5, approvalUserCode);
			this.preparedStatement.setInt   (6, applovalUserStatus);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[applicationNumber]: " + applicationNumber);
				log.error("[workflowEngine] " + "[approvalUserCode]: " + approvalUserCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "find End");
				return resultDto;
			}
			ArrayList<LaubeDto> result = conversion(this.resultSet, new ActivityObjectDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(result);
			log.debug("[workflowEngine] " + "find End");
			return resultDto;

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "find End");
			throw new LaubeException(e);

		} finally {
			try {
				if (this.resultSet != null) {
					this.resultSet.close();
					this.resultSet = null;
				}
				if (this.preparedStatement != null) {
					this.preparedStatement.close();
					this.preparedStatement = null;
				}

			} catch (SQLException e) {
				log.error("[workflowEngine] " + "[SQLException] " + e);
				log.error("[workflowEngine] " + "find End");
				throw new LaubeException(e);
			}
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
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	@Override
	public final ResultDto findByArrival(final String companyCode, final int applicationNumber, final String approvalCompanyCode, final String approvalUnitCode, final String approvalUserCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findByArrival Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: "  + companyCode);
		log.debug("[workflowEngine] " + "[applicationNumber]: "  + applicationNumber);
		log.debug("[workflowEngine] " + "[approvalCompanyCode]: "    + approvalCompanyCode);
		log.debug("[workflowEngine] " + "[approvalUnitCode]: "    + approvalUnitCode);
		log.debug("[workflowEngine] " + "[approvalUserCode]: "    + approvalUserCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(applicationNumber == 0)||(LaubeUtility.isBlank(approvalUnitCode))||(LaubeUtility.isBlank(approvalUserCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		final int applovalUserStatus = SpecifiedValue.Arrival;

		try {

			resultDto = this.find(companyCode, applicationNumber, approvalCompanyCode,  approvalUnitCode, approvalUserCode, applovalUserStatus);

			if (!resultDto.isSuccess()){
				return resultDto;
			}

			log.debug("[workflowEngine] " + "findByArrival End");
			return resultDto;

		} catch (Exception e) {
			log.error("[workflowEngine] " + "[Exception] " + e);
			log.error("[workflowEngine] " + "findByArrival End");
			throw new LaubeException(e);
		}
	}
}
