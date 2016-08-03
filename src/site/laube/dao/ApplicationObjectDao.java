package site.laube.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import lombok.val;
import site.laube.daointerface.ApplicationObjectDaoInterface;
import site.laube.dto.ApplicationObjectDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
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

public final class ApplicationObjectDao extends LaubeDao implements ApplicationObjectDaoInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(ApplicationObjectDao.class);

	/**
	 * search the application object.<br>
	 * @param companyCode Company code
	 * @param applicationNumber application Number
	 * @return LaubeResult
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public ResultDto find(final String companyCode, final long applicationNumber) throws LaubeException {

		log.traceStart("find", companyCode, applicationNumber);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("find");
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
			sql.append(" and application_number = ?");
			sql.append(";");

			log.message("find","[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setLong  (2, applicationNumber);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("find","the record was not found. Please investigate the cause by referring to the following.");
				log.message("find","[SQL]");
				log.message("find",sql.toString());
				log.message("find","");
				log.message("find","[Extraction condition]");
				log.message("find","[companyCode]: " + companyCode);
				log.message("find","[applicationNumber]: " + applicationNumber);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}

			final val resultData = conversion(this.resultSet, new ApplicationObjectDto());

			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
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
	 * tegister the application object.<br>
	 * @param applicationObjectDto application object Dto
	 * @return rsult
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public final ResultDto insert(final ApplicationObjectDto applicationObjectDto) throws LaubeException {

		log.traceStart("insert", applicationObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(applicationObjectDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
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

			log.message("insert","[SQL] " + sql.toString());
			closePreparedStatement();
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
			throw new LaubeException("insert", pe);

		} catch (final SQLException se) {
			throw new LaubeException("insert", se);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("find", e);
			}
			log.traceEnd("insert");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("insert");
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

		log.traceStart("update", applicationObjectDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(applicationObjectDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("update");
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

			log.message("update", "[SQL] " + sql.toString());
			closePreparedStatement();
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
			throw new LaubeException("update", pe);

		} catch (final SQLException se) {
			throw new LaubeException("update", se);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("find", e);
			}
			log.traceEnd("update");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("update");
		return resultDto;
	}
}
