package site.laube.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.val;
import site.laube.daointerface.ApplicationFormDaoInterface;
import site.laube.dto.ApplicationFormDto;
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

public final class ApplicationFormDao extends LaubeDao implements ApplicationFormDaoInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(ApplicationFormDao.class);

	/**
	 * delete the application form master (company unit)<br>
	 * remove the application-specific activity master and all of the application form master.
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode) throws LaubeException {

		log.traceStart("delete", companyCode);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_application_form");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(";");

			log.message("delete","[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			throw new LaubeException("delete", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("delete", e);
			}
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("delete");
		return resultDto;
    }

	/**
	 * delete the application classification master (application code unit)<br>
	 * remove the application classification by Activision Byi master to match the application classification master.
	 * @param companyCode Company code
	 * @param applicationFormCode Application code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode, final String applicationFormCode) throws LaubeException {

		log.traceStart("delete", companyCode, applicationFormCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_application_form");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_form_code = ?");
			sql.append(";");

			log.message("delete", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, applicationFormCode);
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
	 * register the application form master.<br>
	 * @param applicationFormDto Application form master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto insert(final ApplicationFormDto applicationFormDto) throws LaubeException {

		log.traceStart("insert", applicationFormDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(applicationFormDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_application_form");
			sql.append("(");
			sql.append(" company_code");
			sql.append(",application_form_code");
			sql.append(",application_form_name");
			sql.append(",application_classification_code");
			sql.append(",skip_apply_user");
			sql.append(",auto_approval_flag");
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
			sql.append(",CURRENT_TIMESTAMP(0)");
			sql.append(",?");
			sql.append(",CURRENT_TIMESTAMP(0)");
			sql.append(",?");
			sql.append(")");
			sql.append(";");

			log.message("insert", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString(  1, applicationFormDto.getCompanyCode());
			this.preparedStatement.setString(  2, applicationFormDto.getApplicationFormCode());
			this.preparedStatement.setString(  3, applicationFormDto.getApplicationFormName());
			this.preparedStatement.setString(  4, applicationFormDto.getApplicationClassificationCode());
			this.preparedStatement.setBoolean( 5, applicationFormDto.isSkipApplyUser());
			this.preparedStatement.setBoolean( 6, applicationFormDto.isAutoApprovalFlag());
			this.preparedStatement.setString(  7, applicationFormDto.getCreateUserId());
			this.preparedStatement.setString(  8, applicationFormDto.getUpdateUserId());
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			throw new LaubeException("insert", e);

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
	 * to update the application form master.<br>
	 * @param applicationFormDto Application form master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto update(final ApplicationFormDto applicationFormDto) throws LaubeException {

		log.traceStart("update", applicationFormDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(applicationFormDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("update");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_application_form");
			sql.append(" SET");
			sql.append(" application_form_name = ?");
			sql.append(",application_classification_code = ?");
			sql.append(",skip_apply_user = ?");
			sql.append(",auto_approval_flag = ?");
			sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(",update_user_id = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_form_code = ?");
			sql.append(";");

			log.message("update", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString(  1, applicationFormDto.getApplicationFormName());
			this.preparedStatement.setString(  2, applicationFormDto.getApplicationClassificationCode());
			this.preparedStatement.setBoolean( 3, applicationFormDto.isSkipApplyUser());
			this.preparedStatement.setBoolean( 4, applicationFormDto.isAutoApprovalFlag());
			this.preparedStatement.setString(  5, applicationFormDto.getUpdateUserId());
			this.preparedStatement.setString(  6, applicationFormDto.getCompanyCode());
			this.preparedStatement.setString(  7, applicationFormDto.getApplicationFormCode());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				log.message("update", "It failed to update the application form master.");
				log.message("update", "applicationFormName:" + applicationFormDto.getApplicationFormName());
				log.message("update", "applicationClassificationCode:" + applicationFormDto.getApplicationClassificationCode());
				log.message("update", "skipApplyUser:" + applicationFormDto.isSkipApplyUser());
				log.message("update", "autoApprovalFlag:" + applicationFormDto.isAutoApprovalFlag());
				log.message("update", "companyCode:" + applicationFormDto.getCompanyCode());
				log.message("update", "applicationFormCode:" + applicationFormDto.getApplicationFormCode());
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}

		} catch (final SQLException e) {
			throw new LaubeException("update", e);

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
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @return Application form master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode) throws LaubeException {

		log.traceStart("find", companyCode);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("find");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" A.company_code");
			sql.append(",A.company_name");
			sql.append(",A.application_form_code");
			sql.append(",A.application_form_name");
			sql.append(",A.application_classification_code");
			sql.append(",A.application_classification_name");
			sql.append(",A.skip_apply_user");
			sql.append(",A.auto_approval_flag");
			sql.append(" FROM wkf_view_application_form");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(";");

			log.message("find","[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("find","the record was not found. Please investigate the cause by referring to the following.");
				log.message("find","[SQL]");
				log.message("find",sql.toString());
				log.message("find","");
				log.message("find","[Extraction condition]");
				log.message("find","[companyCode]: " + companyCode);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return null;
			}

			final val resultData = conversion(this.resultSet, new ApplicationFormDto());

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
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @param applicationFormCode Application code
	 * @return Application form master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto findByApplicationFormCode(final String companyCode, final String applicationFormCode) throws LaubeException {

		log.traceStart("findByApplicationFormCode", companyCode, applicationFormCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findByApplicationFormCode");
			return resultDto;

		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",application_form_code");
			sql.append(",application_form_name");
			sql.append(",application_classification_code");
			sql.append(",application_classification_name");
			sql.append(",skip_apply_user");
			sql.append(",auto_approval_flag");
			sql.append(",pulling_flag");
			sql.append(",route_flag");
			sql.append(" FROM wkf_view_application_form");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_form_code = ?");
			sql.append(";");

			log.message("findByApplicationFormCode", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, applicationFormCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("findByApplicationFormCode","the record was not found. Please investigate the cause by referring to the following.");
				log.message("findByApplicationFormCode","[SQL]");
				log.message("findByApplicationFormCode",sql.toString());
				log.message("findByApplicationFormCode","");
				log.message("findByApplicationFormCode","[Extraction condition]");
				log.message("findByApplicationFormCode","[companyCode]: " + companyCode);
				log.message("findByApplicationFormCode","[applicationFormCode]: " + applicationFormCode);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}

			final val resultData = conversion(this.resultSet, new ApplicationFormDto());

			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			return resultDto;

		} catch (final SQLException e) {
			throw new LaubeException("findByApplicationFormCode", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("findByApplicationFormCode", e);
			}
			log.traceEnd("findByApplicationFormCode");
		}
	}

	/**
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @param applicationClassificationCode application classification code
	 * @return ResultDto
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto findByApplicationClassificationCode(final String companyCode, final String applicationClassificationCode) throws LaubeException {

		log.traceStart("findByApplicationClassificationCode", companyCode, applicationClassificationCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationClassificationCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findByApplicationClassificationCode");
			return resultDto;
		}
		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" A.company_code");
			sql.append(",A.company_name");
			sql.append(",A.application_form_code");
			sql.append(",A.application_form_name");
			sql.append(",A.application_classification_code");
			sql.append(",A.application_classification_name");
			sql.append(",A.skip_apply_user");
			sql.append(",A.auto_approval_flag");
			sql.append(" FROM wkf_view_application_form");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_classification_code = ?");
			sql.append(";");

			log.message("findByApplicationClassificationCode","[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, applicationClassificationCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("findByApplicationClassificationCode","the record was not found. Please investigate the cause by referring to the following.");
				log.message("findByApplicationClassificationCode","[SQL]");
				log.message("findByApplicationClassificationCode",sql.toString());
				log.message("findByApplicationClassificationCode","");
				log.message("findByApplicationClassificationCode","[Extraction condition]");
				log.message("findByApplicationClassificationCode","[companyCode]: " + companyCode);
				log.message("findByApplicationClassificationCode","[applicationClassificationCode]: " + applicationClassificationCode);
				log.message("findByApplicationClassificationCode","findByApplicationClassificationCode end" + "[return value]:null");
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}

			final val resultData = conversion(this.resultSet, new ApplicationFormDto());

			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			return resultDto;

		} catch (final SQLException e) {
			throw new LaubeException("findByApplicationClassificationCode", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("findByApplicationClassificationCode", e);
			}
			log.traceEnd("findByApplicationClassificationCode");
		}
	}
}
