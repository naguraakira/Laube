package site.laube.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.val;
import site.laube.dto.DeputyApprovelDto;
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

public final class DeputyApprovelDao extends LaubeDao implements site.laube.daointerface.DeputyApprovelDaoInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = new LaubeLogger(LaubeLoggerFactory.getLogger(DeputyApprovelDao.class));

	/**
	 * delete the deputy approval master.<br>
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
			sql.append(" FROM wkf_deputy_approvel ");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(";");

			log.message("delete", "[SQL]" + sql.toString());
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
	 * delete the deputy approval master.<br>
	 * @param companyCode company code
	 * @param userCode user code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode, final String userCode) throws LaubeException {

		log.traceStart("delete", companyCode, userCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank((userCode)))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_deputy_approvel");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and user_code = ?");
			sql.append(";");

			log.message("delete", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, userCode);
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
	 * insert the deputy approval master.<br>
	 * @param deputyApprovelDto deputy approvel dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto insert(final DeputyApprovelDto deputyApprovelDto) throws LaubeException {

		log.traceStart("insert", deputyApprovelDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(deputyApprovelDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_deputy_approvel");
			sql.append("(");
			sql.append(" company_code");
			sql.append(",user_code");
			sql.append(",unit_code");
			sql.append(",deputy_approverl_company_code");
			sql.append(",deputy_approverl_unit_code");
			sql.append(",deputy_approverl_user_code");
			sql.append(",deputy_contents");
			sql.append(",create_date_time");
			sql.append(",create_user_id");
			sql.append(",update_date_time");
			sql.append(",update_user_id)");
			sql.append(" VALUES");
			sql.append("(");
			sql.append(" ?");
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

			this.preparedStatement.setString(  1, deputyApprovelDto.getCompanyCode());
			this.preparedStatement.setString(  2, deputyApprovelDto.getUserCode());
			this.preparedStatement.setString(  3, deputyApprovelDto.getUnitCode());
			this.preparedStatement.setString(  4, deputyApprovelDto.getDeputyApproverlCompanyCode());
			this.preparedStatement.setString(  5, deputyApprovelDto.getDeputyApproverlUnitCode());
			this.preparedStatement.setString(  6, deputyApprovelDto.getDeputyApproverlUserCode());
			this.preparedStatement.setString(  7, deputyApprovelDto.getDeputyContents());
			this.preparedStatement.setString(  8, deputyApprovelDto.getUserCode());
			this.preparedStatement.setString(  9, deputyApprovelDto.getUserCode());
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
	 * update the deputy approval master.<br>
	 * @param deputyApprovelDto deputy approvel dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto update(final DeputyApprovelDto deputyApprovelDto) throws LaubeException {

		log.traceStart("update", deputyApprovelDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(deputyApprovelDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("update");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_deputy_approvel");
			sql.append(" SET");
			sql.append(" deputy_approverl_company_code = ?");
			sql.append(",deputy_approverl_unit_code = ?");
			sql.append(",deputy_approverl_user_code = ?");
			sql.append(",deputy_contents = ?");
			sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(",update_user_id = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and user_code = ?");
			sql.append(" and unit_code = ?");
			sql.append(";");

			log.message("update", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString(  1, deputyApprovelDto.getDeputyApproverlCompanyCode());
			this.preparedStatement.setString(  2, deputyApprovelDto.getDeputyApproverlUnitCode());
			this.preparedStatement.setString(  3, deputyApprovelDto.getDeputyApproverlUserCode());
			this.preparedStatement.setString(  4, deputyApprovelDto.getDeputyContents());
			this.preparedStatement.setString(  5, deputyApprovelDto.getUserCode());
			this.preparedStatement.setString(  6, deputyApprovelDto.getCompanyCode());
			this.preparedStatement.setString(  7, deputyApprovelDto.getUserCode());
			this.preparedStatement.setString(  8, deputyApprovelDto.getUnitCode());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				log.message("update", "It failed to update the application form master.");
				log.message("update", "deputyApproverlUserCode:" + deputyApprovelDto.getDeputyApproverlUserCode());
				log.message("update", "deputyContents:" + deputyApprovelDto.getDeputyContents());
				log.message("update", "userCode:" + deputyApprovelDto.getUserCode());
				log.message("update", "companyCode:" + deputyApprovelDto.getCompanyCode());
				log.message("update", "userCode:" + deputyApprovelDto.getUserCode());
				log.message("update", "unitCode:" + deputyApprovelDto.getUnitCode());
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
	 * search the deputy approval master.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @param userCode user code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	 public final ResultDto find(final String companyCode, final String unitCode, final String userCode) throws LaubeException {

		log.traceStart("find", companyCode, unitCode, userCode);

		final ResultDto resultDto = new ResultDto();

		try {

			if ((!LaubeUtility.isEmpty(companyCode))||(!LaubeUtility.isEmpty(userCode))) {
				resultDto.setSuccess(false);
				resultDto.setMessageId("E0001");
				log.traceEnd("find");
				return resultDto;
			}

			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",user_code");
			sql.append(",user_name");
			sql.append(",unit_code");
			sql.append(",unit_name");
			sql.append(",deputy_approverl_company_code");
			sql.append(",deputy_approverl_company_name");
			sql.append(",deputy_approverl_unit_code");
			sql.append(",deputy_approverl_unit_name");
			sql.append(",deputy_approverl_user_code");
			sql.append(",deputy_approverl_user_name");
			sql.append(",deputy_contents");
			sql.append(",create_date_time");
			sql.append(",create_user_id");
			sql.append(",update_date_time");
			sql.append(",update_user_id");
			sql.append(" FROM wkf_view_deputy_approvel");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and user_code = ?");
			sql.append(" and unit_code = ?");
			sql.append(" ORDER BY company_cde, unit_code, user_code");
			sql.append(";");

			log.message("find", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("find", "the record was not found. Please investigate the cause by referring to the following.");
				log.message("find", "[SQL]");
				log.message("find", sql.toString());
				log.message("find", "");
				log.message("find", "[Extraction condition]");
				log.message("find", "[companyCode]: " + companyCode);
				log.message("find", "[unitCode]: " + unitCode);
				log.message("find", "[userCode]: " + userCode);
				resultDto.setSuccess(true);
				resultDto.setMessageId("N0001");
				return resultDto;
			}

			final val resultData = conversion(this.resultSet, new DeputyApprovelDto());

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
	 * search the deputy approval master.<br>
	 * @param companyCode company code
	 * @param deputyApproverlUserCode deputy approverl user code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	 public final ResultDto find(final String companyCode, final String deputyApproverlUserCode) throws LaubeException {

		log.traceStart("find", companyCode, deputyApproverlUserCode);

		final ResultDto resultDto = new ResultDto();

		try {
			if ((!LaubeUtility.isEmpty(companyCode))||(!LaubeUtility.isEmpty(deputyApproverlUserCode))) {
				resultDto.setSuccess(false);
				resultDto.setMessageId("E0001");
				log.traceEnd("find");
				return resultDto;
			}

			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",user_code");
			sql.append(",user_name");
			sql.append(",unit_code");
			sql.append(",unit_name");
			sql.append(",deputy_approverl_company_code");
			sql.append(",deputy_approverl_company_name");
			sql.append(",deputy_approverl_unit_code");
			sql.append(",deputy_approverl_unit_name");
			sql.append(",deputy_approverl_user_code");
			sql.append(",deputy_approverl_user_name");
			sql.append(",deputy_contents");
			sql.append(",create_date_time");
			sql.append(",create_user_id");
			sql.append(",update_date_time");
			sql.append(",update_user_id");
			sql.append(" FROM wkf_view_deputy_approvel");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and deputy_approverl_user_code = ?");
			sql.append(" ORDER BY company_cde, unit_code, user_code");
			sql.append(";");

			log.message("find", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("find", "the record was not found. Please investigate the cause by referring to the following.");
				log.message("find", "[SQL]");
				log.message("find", sql.toString());
				log.message("find", "");
				log.message("find", "[Extraction condition]");
				log.message("find", "[companyCode]: " + companyCode);
				log.message("find", "[deputyApproverlUserCode]: " + deputyApproverlUserCode);
				resultDto.setSuccess(true);
				resultDto.setMessageId("N0001");
				return resultDto;
			}

			final val resultData = conversion(this.resultSet, new DeputyApprovelDto());

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
}
