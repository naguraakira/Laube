package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.DeputyApprovelDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.DeputyApprovelModelInterface;
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

public final class DeputyApprovelModel extends LaubeModel implements DeputyApprovelModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(DeputyApprovelModel.class);

	/**
	 * delete the deputy approval master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto delete(final String companyCode) throws LaubeException {

		log.info("[workflowEngine] " + "delete start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "delete end");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_deputy_approvel ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "delete end");
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
			} catch (final SQLException e) {
				log.info("[workflowEngine] " + "delete end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "delete end");
		return resultDto;
    }

	/**
	 * delete the deputy approval master.<br>
	 * @param companyCode company code
	 * @param userCode user code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto delete(final String companyCode, final String userCode) throws LaubeException {

		log.info("[workflowEngine] " + "delete start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[userCode]: " + userCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank((userCode)))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "delete end");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_deputy_approvel ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("user_code = ? ");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, userCode);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "delete end");
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
			} catch (final SQLException e) {
				log.info("[workflowEngine] " + "delete end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "delete end");
		return resultDto;
    }

	/**
	 * insert the deputy approval master.<br>
	 * @param deputyApprovelDto deputy approvel dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto insert(final DeputyApprovelDto deputyApprovelDto) throws LaubeException {

		log.info("[workflowEngine] " + "insert start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[deputyApprovelDto]: " + deputyApprovelDto);

		final ResultDto resultDto = new ResultDto();

		if (deputyApprovelDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "insert end");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_deputy_approvel ");
			sql.append("(");
			sql.append("company_code,");
			sql.append("user_code,");
			sql.append("unit_code,");
			sql.append("deputy_approverl_company_code,");
			sql.append("deputy_approverl_unit_code,");
			sql.append("deputy_approverl_user_code,");
			sql.append("deputy_contents,");
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
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?);");

			log.debug("[WKF] " + "[SQL] " + sql.toString());
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
			log.info("[workflowEngine] " + "insert end");
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
			} catch (final SQLException e) {
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
	 * update the deputy approval master.<br>
	 * @param deputyApprovelDto deputy approvel dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto update(final DeputyApprovelDto deputyApprovelDto) throws LaubeException {

		log.info("[workflowEngine] " + "update start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[deputyApprovelDto]: " + deputyApprovelDto);

		final ResultDto resultDto = new ResultDto();

		if (deputyApprovelDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "update end");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_deputy_approvel ");
			sql.append("SET ");
			sql.append("deputy_approverl_company_code = ?, ");
			sql.append("deputy_approverl_unit_code = ?, ");
			sql.append("deputy_approverl_user_code = ?, ");
			sql.append("deputy_contents = ?, ");
			sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
			sql.append("update_user_id = ? ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("user_code = ? AND ");
			sql.append("unit_code = ?;");

			log.debug("[WKF] " + "[SQL] " + sql.toString());
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
				log.error("[workflowEngine] It failed to update the application form master.");
				log.error("[workflowEngine] deputyApproverlUserCode:" + deputyApprovelDto.getDeputyApproverlUserCode());
				log.error("[workflowEngine] deputyContents:" + deputyApprovelDto.getDeputyContents());
				log.error("[workflowEngine] userCode:" + deputyApprovelDto.getUserCode());
				log.error("[workflowEngine] companyCode:" + deputyApprovelDto.getCompanyCode());
				log.error("[workflowEngine] userCode:" + deputyApprovelDto.getUserCode());
				log.error("[workflowEngine] unitCode:" + deputyApprovelDto.getUnitCode());
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.info("[workflowEngine] " + "update end");
				return resultDto;
			}

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "update end");
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
			} catch (final SQLException e) {
				log.info("[workflowEngine] " + "update end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "update end");
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
	@Override
	 public final ResultDto find(final String companyCode, final String unitCode, final String userCode) throws LaubeException {

		log.info("[workflowEngine] " + "find start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[userCode]: " + userCode);

		final ResultDto resultDto = new ResultDto();

		try {

			if ((LaubeUtility.isNotEmpty(companyCode))||(LaubeUtility.isNotEmpty(userCode))) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0001");
				log.info("[workflowEngine] " + "find end");
				return resultDto;
			}

			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("user_code, ");
			sql.append("user_name, ");
			sql.append("unit_code, ");
			sql.append("unit_name, ");
			sql.append("deputy_approverl_company_code, ");
			sql.append("deputy_approverl_company_name, ");
			sql.append("deputy_approverl_unit_code, ");
			sql.append("deputy_approverl_unit_name, ");
			sql.append("deputy_approverl_user_code, ");
			sql.append("deputy_approverl_user_name, ");
			sql.append("deputy_contents, ");
			sql.append("create_date_time, ");
			sql.append("create_user_id, ");
			sql.append("update_date_time, ");
			sql.append("update_user_id ");
			sql.append("FROM ");
			sql.append("wkf_view_deputy_approvel ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("user_code = ? AND ");
			sql.append("unit_code = ? ");
			sql.append("ORDER BY company_cde, unit_code, user_code");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.debug("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.debug("[workflowEngine] " + "[SQL]");
				log.debug("[workflowEngine] " + sql.toString());
				log.debug("[workflowEngine] " + "");
				log.debug("[workflowEngine] " + "[Extraction condition]");
				log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.debug("[workflowEngine] " + "[unitCode]: " + unitCode);
				log.debug("[workflowEngine] " + "[userCode]: " + userCode);
				resultDto.setStatus(true);
				resultDto.setMessageId("N0001");
				log.info("[workflowEngine] " + "find end");
				return resultDto;
			}

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new DeputyApprovelDto());

			log.debug("[workflowEngine] " + "find end" + "[return value]:" + resultData);
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
				if (this.resultSet != null) {
					this.resultSet.close();
					this.resultSet = null;
				}
				if (this.preparedStatement != null) {
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
	 * search the deputy approval master.<br>
	 * @param companyCode company code
	 * @param deputyApproverlUserCode deputy approverl user code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	 public final ResultDto find(final String companyCode, final String deputyApproverlUserCode) throws LaubeException {

		log.info("[workflowEngine] " + "find start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[deputyApproverlUserCode]: " + deputyApproverlUserCode);

		final ResultDto resultDto = new ResultDto();

		try {
			if ((LaubeUtility.isNotEmpty(companyCode))||(LaubeUtility.isNotEmpty(deputyApproverlUserCode))) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0001");
				log.info("[workflowEngine] " + "find end");
				return resultDto;
			}

			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("user_code, ");
			sql.append("user_name, ");
			sql.append("unit_code, ");
			sql.append("unit_name, ");
			sql.append("deputy_approverl_company_code, ");
			sql.append("deputy_approverl_company_name, ");
			sql.append("deputy_approverl_unit_code, ");
			sql.append("deputy_approverl_unit_name, ");
			sql.append("deputy_approverl_user_code, ");
			sql.append("deputy_approverl_user_name, ");
			sql.append("deputy_contents, ");
			sql.append("create_date_time, ");
			sql.append("create_user_id, ");
			sql.append("update_date_time, ");
			sql.append("update_user_id ");
			sql.append("FROM ");
			sql.append("wkf_view_deputy_approvel ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("deputy_approverl_user_code = ? ");
			sql.append("ORDER BY company_cde, unit_code, user_code");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.debug("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.debug("[workflowEngine] " + "[SQL]");
				log.debug("[workflowEngine] " + sql.toString());
				log.debug("[workflowEngine] " + "");
				log.debug("[workflowEngine] " + "[Extraction condition]");
				log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.debug("[workflowEngine] " + "[deputyApproverlUserCode]: " + deputyApproverlUserCode);
				resultDto.setStatus(true);
				resultDto.setMessageId("N0001");
				log.info("[workflowEngine] " + "find end");
				return resultDto;
			}

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new DeputyApprovelDto());

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
				if (this.resultSet != null) {
					this.resultSet.close();
					this.resultSet = null;
				}
				if (this.preparedStatement != null) {
					this.preparedStatement.close();
					this.preparedStatement = null;
				}
			} catch (final SQLException e) {
				log.info("[workflowEngine] " + "find end");
				throw new LaubeException(e);
			}
		}
	}
}
