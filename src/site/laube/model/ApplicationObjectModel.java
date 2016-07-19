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
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public ResultDto find(final String companyCode, final long applicationNumber) throws LaubeException {

		log.info("[workflowEngine] " + "find start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[applicationNumber]: " + applicationNumber);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "find end");
			return resultDto;
		}

		try {
			final  StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" id");
			sql.append(",company_code");
			sql.append(",company_name");
			sql.append(",application_number");
			sql.append(",reapplication_number");
			sql.append(",application_classification_code");
			sql.append(",application_classification_name");
			sql.append(",application_form_code");
			sql.append(",application_form_name");
			sql.append(",apply_company_code");
			sql.append(",company_name AS apply_company_name");
			sql.append(",apply_unit_code");
			sql.append(",unit_name AS apply_unit_name");
			sql.append(",apply_user_code");
			sql.append(",user_name AS apply_user_name");
			sql.append(",deputy_apply_company_code");
			sql.append(",company_name AS deputy_apply_company_name");
			sql.append(",deputy_apply_unit_code");
			sql.append(",unit_name AS deputy_apply_unit_name");
			sql.append(",deputy_apply_user_code");
			sql.append(",user_name AS deputy_apply_user_name");
			sql.append(",apply_date");
			sql.append(",application_status");
			sql.append(" FROM wkf_view_application_object");
			sql.append(" WHERE ");
			sql.append(" company_code = ?");
			sql.append(" AND application_number = ?");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setLong  (2, applicationNumber);

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
				log.info("[workflowEngine] " + "find end");
				return resultDto;
			}

			final  ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationObjectDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.info("[workflowEngine] " + "find end");
			return resultDto;

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "find end");
			throw new LaubeException(e);

		} finally {
			try {
				if (!LaubeUtility.isEmpty(this.resultSet)){
					this.resultSet.close();
					this.resultSet = null;
				}

				if (!LaubeUtility.isEmpty(this.preparedStatement)){
					this.preparedStatement.close();
					this.preparedStatement = null;
				}

			} catch (final SQLException e) {
				log.info("[workflowEngine] " + "find end");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * tegister the application object.<br>
	 * @param applicationObjectDto application object Dto
	 * @return rsult
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public final ResultDto insert(final ApplicationObjectDto applicationObjectDto) throws LaubeException {

		log.info("[workflowEngine] " + "insert start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[applicationObjectDto]: " + applicationObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(applicationObjectDto)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "insert end");
			return resultDto;
		}

		try {
			final String companyCode = applicationObjectDto.getCompanyCode();
			final long reApplicationNumber = applicationObjectDto.getReapplicationNumber();
			final String applicationFormCode = applicationObjectDto.getApplicationFormCode();
			Date applyDate = null;
			if (!LaubeUtility.isEmpty(applicationObjectDto.getApplyDate())){
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

			final  StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_application_object(");
			sql.append(",company_code");
			sql.append(",reapplication_number");
			sql.append(",application_form_code");
			sql.append(",apply_company_code");
			sql.append(",apply_unit_code");
			sql.append(",apply_user_code");
			sql.append(",deputy_apply_company_code");
			sql.append(",deputy_apply_unit_code");
			sql.append(",deputy_apply_user_code");
			sql.append(",apply_date");
			sql.append(",application_status");
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
			sql.append(",CURRENT_TIMESTAMP(0)");
			sql.append(",?");
			sql.append(",CURRENT_TIMESTAMP(0)");
			sql.append(",?");
			sql.append(")");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString( 1, companyCode);
			this.preparedStatement.setLong  ( 2, reApplicationNumber);
			this.preparedStatement.setString( 3, applicationFormCode);
			this.preparedStatement.setString( 4, applyCompanyCode);
			this.preparedStatement.setString( 5, applyUnitCode);
			this.preparedStatement.setString( 6, applyUserCode);
			this.preparedStatement.setString( 7, deputyApplyCompanyCode);
			this.preparedStatement.setString( 8, deputyApplyUnitCode);
			this.preparedStatement.setString( 9, deputyApplyUserCode);
			this.preparedStatement.setDate  (10, applyDate);
			this.preparedStatement.setInt   (11, applicationStatus);
			this.preparedStatement.setString(12, createUserID);
			this.preparedStatement.setString(13, updateUserID);
			this.preparedStatement.executeUpdate();

		} catch (final ParseException pe) {
			log.info("[workflowEngine] " + "insert end");
			throw new LaubeException(pe);

		} catch (final SQLException se) {
			log.info("[workflowEngine] " + "insert end");
			throw new LaubeException(se);

		} finally {
			try {
				if (!LaubeUtility.isEmpty(this.resultSet)){
					this.resultSet.close();
					this.resultSet = null;
				}

				if (!LaubeUtility.isEmpty(this.preparedStatement)){
					this.preparedStatement.close();
					this.preparedStatement = null;
				}

			} catch (final Exception e) {
				log.info("[workflowEngine] " + "insert end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "insert end");
		return resultDto;
	}

	/**
	 * update the application object.<br>
	 * @param applicationObjectDto application object Dto
	 * @return rsult
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public final ResultDto update(final ApplicationObjectDto applicationObjectDto) throws LaubeException {

		log.info("[workflowEngine] " + "update start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[applicationObjectDto]: " + applicationObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(applicationObjectDto)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "update end");
			return resultDto;
		}

		try {
			final String companyCode = applicationObjectDto.getCompanyCode();
			final long applicationNumber = applicationObjectDto.getApplicationNumber();
			java.sql.Date applyDate = null;
			if (!LaubeUtility.isEmpty(applicationObjectDto.getApplyDate())){
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

			final  StringBuffer sql = new StringBuffer();
			sql.append("UPDATE");
			sql.append(" wkf_application_object");
			sql.append(" SET");
			sql.append(" apply_company_code = ?");
			sql.append(".apply_unit_code = ?");
			sql.append(".apply_user_code = ?");
			sql.append(".deputy_apply_company_code = ?");
			sql.append(".deputy_apply_unit_code = ?");
			sql.append(".deputy_apply_user_code = ?");
			sql.append(".apply_date = ?");
			sql.append(".application_status = ?");
			sql.append(".update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(".update_user_id = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_number = ?");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
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

		} catch (final ParseException pe) {
			log.info("[workflowEngine] " + "update end");
			throw new LaubeException(pe);

		} catch (final SQLException se) {
			log.info("[workflowEngine] " + "update end");
			throw new LaubeException(se);

		} finally {

			try {
				if (!LaubeUtility.isEmpty(this.resultSet)){
					this.resultSet.close();
					this.resultSet = null;
				}

				if (!LaubeUtility.isEmpty(this.preparedStatement)){
					this.preparedStatement.close();
					this.preparedStatement = null;
				}

			} catch (final Exception e) {
				log.info("[workflowEngine] " + "update end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "update end");
		return resultDto;
	}
}
