package site.laube.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.ApplicationObjectDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.ApplicationObjectModelInterface;
import site.laube.utility.LaubeUtility;

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

public final class ApplicationObjectModel extends LaubeModel implements ApplicationObjectModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApplicationObjectModel.class);

	/**
	 * search the application object.<br>
	 * @param companyCode Company code
	 * @param applicationNumber application Number
	 * @return LaubeResult
	 * @exception LaubeException
	 */
	public ResultDto find(final String companyCode, final int applicationNumber) throws LaubeException {

		log.debug("[workflowEngine] " + "find Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[applicationNumber]: " + applicationNumber);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "find End");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * ");
			sql.append("FROM wkf_view_application_object ");
			sql.append("WHERE ");
			sql.append("company_code = ?");
			sql.append(" AND application_number = ?;");

			log.debug("[workflowEngine] " + "SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setInt   (2, applicationNumber);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[applicationNumber]: " + applicationNumber);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "find End");
				return resultDto;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationObjectDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.debug("[workflowEngine] " + "find End");
			return resultDto;

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
			} catch (SQLException e) {
				log.error("[workflowEngine] " + "find End");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * tegister the application object.<br>
	 * @param applicationObjectDto application object Dto
	 * @return rsult
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public final ResultDto insert(final ApplicationObjectDto applicationObjectDto) throws LaubeException {

		log.debug("[workflowEngine] " + "insert Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[applicationObjectDto]: " + applicationObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (applicationObjectDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "insert End");
			return resultDto;
		}

		try {
			final String companyCode = applicationObjectDto.getCompanyCode();
			final int applicationNumber = getNextApplicationNumber(applicationObjectDto.getCompanyCode());
			final long reApplicationNumber = applicationObjectDto.getReapplicationNumber();
			final String applicationFormCode = applicationObjectDto.getApplicationFormCode();
			Date applyDate = null;
			if (applicationObjectDto.getApplyDate() != null) {
				DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
				java.util.Date date = format.parse(applicationObjectDto.getApplyDate());
				applyDate = new java.sql.Date(date.getTime());
			}
			final String applyCompanyCode = applicationObjectDto.getApplyCompanyCode();
			final String applyUnitCode = applicationObjectDto.getApplyUnitCode();
			final String applyUserCode = applicationObjectDto.getApplyUserCode();
			final String deputyApplyCompanyCode = applicationObjectDto.getDeputyApplyCompanyCode();
			final String deputyApplyUnitCode = applicationObjectDto.getDeputyApplyUnitCode();
			final String deputyApplyUserCode = applicationObjectDto.getDeputyApplyUserCode();
			final int applicationStatus = applicationObjectDto.getApplicationStatus();
			final String createUserID = applicationObjectDto.getCreateUserId();
			final String updateUserID = applicationObjectDto.getUpdateUserId();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_application_object( ");
			sql.append("company_code, ");
			sql.append("application_number, ");
			sql.append("reapplication_number, ");
			sql.append("application_form_code, ");
			sql.append("apply_company_code, ");
			sql.append("apply_unit_code, ");
			sql.append("apply_user_code, ");
			sql.append("deputy_apply_company_code, ");
			sql.append("deputy_apply_unit_code, ");
			sql.append("deputy_apply_user_code, ");
			sql.append("apply_date, ");
			sql.append("application_status, ");
			sql.append("create_date_time, ");
			sql.append("create_user_id, ");
			sql.append("update_date_time, ");
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
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("? ");
			sql.append(");");

			log.debug("[workflowEngine] " + "SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString( 1, companyCode);
			this.preparedStatement.setInt   ( 2, applicationNumber);
			this.preparedStatement.setLong  ( 3, reApplicationNumber);
			this.preparedStatement.setString( 4, applicationFormCode);
			this.preparedStatement.setString( 5, applyCompanyCode);
			this.preparedStatement.setString( 6, applyUnitCode);
			this.preparedStatement.setString( 7, applyUserCode);
			this.preparedStatement.setString( 8, deputyApplyCompanyCode);
			this.preparedStatement.setString( 9, deputyApplyUnitCode);
			this.preparedStatement.setString(10, deputyApplyUserCode);
			this.preparedStatement.setDate  (11, applyDate);
			this.preparedStatement.setInt   (12, applicationStatus);
			this.preparedStatement.setString(13, createUserID);
			this.preparedStatement.setString(14, updateUserID);
			this.preparedStatement.executeUpdate();
			resultDto.setResultData(applicationNumber);

		} catch (ParseException pe) {
			log.error("[workflowEngine] " + "insert End");
			throw new LaubeException(pe);

		} catch (SQLException se) {
			log.error("[workflowEngine] " + "insert End");
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
				log.error("[workflowEngine] " + "insert End");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "insert End");
		return resultDto;
	}

	/**
	 * update the application object.<br>
	 * @param applicationObjectDto application object Dto
	 * @return rsult
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public final ResultDto update(final ApplicationObjectDto applicationObjectDto) throws LaubeException {

		log.debug("[workflowEngine] " + "update Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[applicationObjectDto]: " + applicationObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (applicationObjectDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "update End");
			return resultDto;
		}

		try {
			final String companyCode = applicationObjectDto.getCompanyCode();
			final long applicationNumber = applicationObjectDto.getApplicationNumber();
			java.sql.Date applyDate = null;
			if (applicationObjectDto.getApplyDate() != null) {
				DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
				java.util.Date date = format.parse(applicationObjectDto.getApplyDate());
				applyDate = new java.sql.Date(date.getTime());
			}
			final String applyCompanyCode = applicationObjectDto.getApplyCompanyCode();
			final String applyUnitCode = applicationObjectDto.getApplyUnitCode();
			final String applyUserCode = applicationObjectDto.getApplyUserCode();
			final String deputyApplyCompanyCode = applicationObjectDto.getDeputyApplyCompanyCode();
			final String deputyApplyUnitCode = applicationObjectDto.getDeputyApplyUnitCode();
			final String deputyApplyUserCode = applicationObjectDto.getDeputyApplyUserCode();
			final int applicationStatus = applicationObjectDto.getApplicationStatus();
			final String updateUserId = applicationObjectDto.getUpdateUserId();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE ");
			sql.append("wkf_application_object ");
			sql.append("SET ");
			sql.append("apply_company_code = ?, ");
			sql.append("apply_unit_code = ?, ");
			sql.append("apply_user_code = ?, ");
			sql.append("deputy_apply_company_code = ?, ");
			sql.append("deputy_apply_unit_code = ?, ");
			sql.append("deputy_apply_user_code = ?, ");
			sql.append("apply_date = ?, ");
			sql.append("application_status = ?, ");
			sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
			sql.append("update_user_id = ? ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("application_number = ?;");

			log.debug("[workflowEngine] " + "SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString( 1, applyCompanyCode);
			this.preparedStatement.setString( 2, applyUnitCode);
			this.preparedStatement.setString( 3, applyUserCode);
			this.preparedStatement.setString( 4, deputyApplyCompanyCode);
			this.preparedStatement.setString( 5, deputyApplyUnitCode);
			this.preparedStatement.setString( 6, deputyApplyUserCode);
			this.preparedStatement.setDate  ( 7, applyDate);
			this.preparedStatement.setInt   ( 8, applicationStatus);
			this.preparedStatement.setString( 9, updateUserId);
			this.preparedStatement.setString(10, companyCode);
			this.preparedStatement.setLong  (11, applicationNumber);
			this.preparedStatement.executeUpdate();
			resultDto.setResultData(applicationNumber);

		} catch (ParseException pe) {
			log.error("[workflowEngine] " + "update End");
			throw new LaubeException(pe);

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
	 * it returns the value obtained by counting up the maximum application number.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	private final int getNextApplicationNumber(String  companyCode) throws LaubeException {

		log.debug("[workflowEngine] " + "getNextApplicationNumber Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);

		int result = 0;

		if (LaubeUtility.isBlank(companyCode)) {
			log.error("[workflowEngine] " + "getNextApplicationNumber End");
			throw new LaubeException("companyCode is null");
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("max(application_number) ");
			sql.append("FROM ");
			sql.append("wkf_view_application_object ");
			sql.append("WHERE ");
			sql.append("company_code = ?");

			log.debug("[workflowEngine] " + "SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.resultSet = this.preparedStatement.executeQuery();

			int max = 0;

			if (this.resultSet.first()) {
				max = this.resultSet.getInt("max");
			}
			result = ++max;

		} catch (SQLException se) {
			log.error("[workflowEngine] " + se);
			log.error("[workflowEngine] " + "getNextApplicationNumber End");
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
				log.error("[workflowEngine] " + "getNextApplicationNumber End");
				throw new LaubeException(e);
			}
		}
		log.debug("[workflowEngine] " + "getNextApplicationNumber End");
		return result;
	}
}
