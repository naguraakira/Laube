package site.laube.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import site.laube.daointerface.ApplicationClassificationDaoInterface;
import site.laube.dto.ApplicationClassificationDto;
import site.laube.dto.ApplicationFormRouteDto;
import site.laube.dto.LaubeDto;
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

public final class ApplicationClassificationDao extends LaubeDao implements ApplicationClassificationDaoInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(ApplicationClassificationDao.class);

	/**
     * delete the application classification master (company unit)<br>
     * remove all of the application classification master and the applicant by category activity master.
     * @param companyCode company code
     * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
     */
	@SuppressWarnings("nls")
	@Override
    public final ResultDto delete(final String companyCode) throws LaubeException {

		log.traceStart("delete", companyCode);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_application_classification");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(";");

			log.message("delete", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			throw new LaubeException("delete",e);

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
     * delete the application classification master (application code unit)<br>
     * remove the application classification by activity master to match the application classification master.
     * @param companyCode company code
     * @param applicationClassificationCode Application classification code
     * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
     */
	@SuppressWarnings("nls")
	@Override
    public final ResultDto delete(final String companyCode, final String applicationClassificationCode) throws LaubeException {

		log.traceStart("delete", companyCode, applicationClassificationCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationClassificationCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_application_classification");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_classification_code = ?");
			sql.append(";");

			log.message("delete","[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, applicationClassificationCode);
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
	 * register the application classification master.<br>
	 * @param applicationClassificationDto Application classification master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto insert(final ApplicationClassificationDto applicationClassificationDto) throws LaubeException {

		log.traceStart("insert", applicationClassificationDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(applicationClassificationDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_application_classification");
			sql.append("(");
			sql.append(" company_code");
			sql.append(",application_classification_code");
			sql.append(",application_classification_name");
			sql.append(",sort_order");
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
			sql.append(",CURRENT_TIMESTAMP(0)");
			sql.append(",?");
			sql.append(",CURRENT_TIMESTAMP(0)");
			sql.append(",?");
			sql.append(")");
			sql.append(";");

			log.message("insert","[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString(  1, applicationClassificationDto.getCompanyCode());
			this.preparedStatement.setString(  2, applicationClassificationDto.getApplicationClassificationCode());
			this.preparedStatement.setString(  3, applicationClassificationDto.getApplicationClassificationName());
			this.preparedStatement.setInt   (  4, applicationClassificationDto.getSortOrder());
			this.preparedStatement.setString(  5, applicationClassificationDto.getCreateUserId());
			this.preparedStatement.setString(  6, applicationClassificationDto.getUpdateUserId());
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			throw new LaubeException("insert", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("insert",  e);
			}
			log.traceEnd("insert");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("insert");
		return resultDto;
	}

	/**
	 * to update the application classification master.<br>
	 * @param applicationClassificationDto Application classification master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto update(final ApplicationClassificationDto applicationClassificationDto) throws LaubeException {

		log.traceStart("update", applicationClassificationDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(applicationClassificationDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("update");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_application_classification");
			sql.append(" SET");
			sql.append(" application_classification_name = ?");
			sql.append(",sort_order = ?");
			sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(",update_user_id = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_classification_code = ?");
			sql.append(";");

			log.message("update", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString(  1, applicationClassificationDto.getApplicationClassificationName());
			this.preparedStatement.setInt   (  2, applicationClassificationDto.getSortOrder());
			this.preparedStatement.setString(  3, applicationClassificationDto.getUpdateUserId());
			this.preparedStatement.setString(  4, applicationClassificationDto.getCompanyCode());
			this.preparedStatement.setString(  5, applicationClassificationDto.getApplicationClassificationCode());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				log.message("update", "It failed to update the application classification master.");
				log.message("update", "applicationClassificationName:" + applicationClassificationDto.getApplicationClassificationName());
				log.message("update", "sortOrder:" + applicationClassificationDto.getSortOrder());
				log.message("update", "updateUserId:" + applicationClassificationDto.getUpdateUserId());
				log.message("update", "companyCode:" + applicationClassificationDto.getCompanyCode());
				log.message("update", "gapplicationClassificationCode:" + applicationClassificationDto.getApplicationClassificationCode());
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
	 * @param companyCode company code
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
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",application_classification_code");
			sql.append(",application_classification_name");
			sql.append(" FROM wkf_view_application_classification");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(";");

			log.message("find","[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("find","The record was not found. Please investigate the cause by referring to the following.");
				log.message("find","[SQL]");
				log.message("find",sql.toString());
				log.message("find","");
				log.message("find","[Extraction condition]");
				log.message("find","[companyCode]: " + companyCode);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationClassificationDto());

			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.traceEnd("find");
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
	 * @param companyCode company code
	 * @param applicationClassificationCode Application classification code
	 * @return Application form master
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
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",application_classification_code");
			sql.append(",application_classification_name");
			sql.append(" FROM wkf_view_application_classification");
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
				log.message("findByApplicationClassificationCode","The record was not found. Please investigate the cause by referring to the following.");
				log.message("findByApplicationClassificationCode","[SQL]");
				log.message("findByApplicationClassificationCode",sql.toString());
				log.message("findByApplicationClassificationCode","");
				log.message("findByApplicationClassificationCode","[Extraction condition]");
				log.message("findByApplicationClassificationCode","[companyCode]: " + companyCode);
				log.message("findByApplicationClassificationCode","[applicationClassificationCode]: " + applicationClassificationCode);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationClassificationDto());

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

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param specialRouteCode special route code
	 * @return Application-specific root master entity
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public final ResultDto findBySpecialRouteCode(final String companyCode, final String specialRouteCode) throws LaubeException {

		log.traceStart("findBySpecialRouteCode", companyCode, specialRouteCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(specialRouteCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findBySpecialRouteCode");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",application_classification_code");
			sql.append(",application_classification_name");
			sql.append(",unit_code");
			sql.append(",unit_name");
			sql.append(",individual_route_code");
			sql.append(",individual_route_name");
			sql.append(",common_route_code");
			sql.append(",common_route_name");
			sql.append(" FROM wkf_view_application_classification_Activity");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and common_route_code = ?");
			sql.append(";");

			log.message("findBySpecialRouteCode", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, specialRouteCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("findBySpecialRouteCode", "The record was not found. Please investigate the cause by referring to the following.");
				log.message("findBySpecialRouteCode", "[SQL]");
				log.message("findBySpecialRouteCode", sql.toString());
				log.message("findBySpecialRouteCode", "");
				log.message("findBySpecialRouteCode", "[Extraction condition]");
				log.message("findBySpecialRouteCode", "[companyCode]: " + companyCode);
				log.message("findBySpecialRouteCode", "[specialRouteCode]: " + specialRouteCode);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return null;
			}

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormRouteDto());

			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			return resultDto;

		} catch (final SQLException e) {
			throw new LaubeException("findBySpecialRouteCode", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("findBySpecialRouteCode", e);
			}
			log.traceEnd("findBySpecialRouteCode");
		}
	}
}
