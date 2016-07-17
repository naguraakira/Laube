package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.AppendedDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.AppendedModelInterface;
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

public final class AppendedModel extends LaubeModel implements AppendedModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(AppendedModel.class);

	/**
	 * delete the appended object<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	*/
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
			// deletion processing (company unit)
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_appended ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "delete end");
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
	 * delete the appended object(user unit)<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public final ResultDto delete(final String companyCode, final int applicationNumber) throws LaubeException {

		log.info("[workflowEngine] " + "delete start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[applicationNumber]: " + applicationNumber);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(applicationNumber == 0)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "delete end");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_appended ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("applicationNumber = ? ");

			log.info("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setInt   (2, applicationNumber);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "delete end");
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
	 * register the boss master.<br>
	 * @param appendedDto appended dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto insert(final AppendedDto appendedDto) throws LaubeException {

		log.info("[workflowEngine] " + "insert start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[appendedDto]: " + appendedDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(appendedDto)) {
			log.error("[workflowEngine] " + "insert end" + "[return value]:false");
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "insert end");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_appended ");
			sql.append("(");
			sql.append("company_code,");
			sql.append("application_number,");
			sql.append("approval_company_code,");
			sql.append("approval_unit_code,");
			sql.append("approval_user_code,");
			sql.append("approval_path,");
			sql.append("approval_date,");
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
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?);");

			log.info("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString   ( 1, appendedDto.getCompanyCode());
			this.preparedStatement.setLong     ( 2, appendedDto.getApplicationNumber());
			this.preparedStatement.setString   ( 3, appendedDto.getApprovalCompanyCode());
			this.preparedStatement.setString   ( 4, appendedDto.getApprovalUnitCode());
			this.preparedStatement.setString   ( 5, appendedDto.getApprovalUserCode());
			this.preparedStatement.setString   ( 6, appendedDto.getApprovalPath());
			this.preparedStatement.setString   ( 7, appendedDto.getApprovalUserCode());
			this.preparedStatement.setString   ( 8, appendedDto.getApprovalUserCode());
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "insert end");
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
	 * To update the appended.<br>
	 * @param appendedDto appended dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto update(final AppendedDto appendedDto) throws LaubeException {

		log.info("[workflowEngine] " + "update start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[appendedDto]: " + appendedDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(appendedDto)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "update end");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_appended ");
			sql.append("SET ");
			sql.append("company_code = ?, ");
			sql.append("application_number = ?, ");
			sql.append("approval_number = ?, ");
			sql.append("approval_company_code = ?, ");
			sql.append("approval_unit_code = ?, ");
			sql.append("approval_user_code = ?, ");
			sql.append("approval_path = ?, ");
			sql.append("approval_date = ?, ");
			sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
			sql.append("update_user_id = ? ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("application_number = ? AND ");
			sql.append("approval_number = ?;");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString   ( 1, appendedDto.getCompanyCode());
			this.preparedStatement.setLong     ( 2, appendedDto.getApplicationNumber());
			this.preparedStatement.setInt      ( 3, appendedDto.getApprovalNumber());
			this.preparedStatement.setString   ( 4, appendedDto.getApprovalCompanyCode());
			this.preparedStatement.setString   ( 5, appendedDto.getApprovalUnitCode());
			this.preparedStatement.setString   ( 6, appendedDto.getApprovalUserCode());
			this.preparedStatement.setString   ( 7, appendedDto.getApprovalPath());
			this.preparedStatement.setTimestamp( 8, appendedDto.getApprovalDate());
			this.preparedStatement.setString   ( 9, appendedDto.getUpdateUserId());
			this.preparedStatement.setString   (10, appendedDto.getCompanyCode());
			this.preparedStatement.setLong     (11, appendedDto.getApplicationNumber());
			this.preparedStatement.setInt      (12, appendedDto.getApprovalNumber());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				log.error("[workflowEngine] It failed to update the boss master.");
				log.error("[workflowEngine] companyCode:" + appendedDto.getCompanyCode());
				log.error("[workflowEngine] applicationNumber:" + appendedDto.getApplicationNumber());
				log.error("[workflowEngine] approvalNumber:" + appendedDto.getApprovalNumber());
				log.error("[workflowEngine] approvalCompanyCode:" + appendedDto.getApprovalCompanyCode());
				log.error("[workflowEngine] approvalUnitCode:" + appendedDto.getApprovalUnitCode());
				log.error("[workflowEngine] approvalUserCode:" + appendedDto.getApprovalUserCode());
				log.error("[workflowEngine] approvalPath:" + appendedDto.getApprovalPath());
				log.error("[workflowEngine] approvalDate:" + appendedDto.getApprovalDate());
				log.error("[workflowEngine] updateUserId:" + appendedDto.getUpdateUserId());
				log.error("[workflowEngine] companyCode:" + appendedDto.getCompanyCode());
				log.error("[workflowEngine] applicationNumber:" + appendedDto.getApplicationNumber());
				log.error("[workflowEngine] approvalNumber:" + appendedDto.getApprovalNumber());
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
				if (!LaubeUtility.isEmpty(this.resultSet)){
					this.resultSet.close();
					this.resultSet = null;
				}

				if (!LaubeUtility.isEmpty(this.preparedStatement)){
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
	 * Search the appended object.<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @return ResultDto
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode, final int applicationNumber) throws LaubeException {

		log.info("[workflowEngine] " + "find start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[applicationNumber]: " + applicationNumber);

		final ResultDto resultDto = new ResultDto();

		try {
			if (LaubeUtility.isEmpty(companyCode) || applicationNumber == 0){
				log.info("[workflowEngine] " + "find end");
				throw new LaubeException("company Code / applicationNumber is a required field. Be sure to set.");
			}
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name,");
			sql.append("application_number, ");
			sql.append("approval_number,");
			sql.append("approval_company_code,");
			sql.append("approval_unit_code,");
			sql.append("approval_user_code,");
			sql.append("approval_path, ");
			sql.append("approval_date,");
			sql.append("create_date_time,");
			sql.append("create_user_id,");
			sql.append("update_date_time,");
			sql.append("update_user_id");
			sql.append("from wkf_view_Appended as A ");

			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("applicationNumber = ?;");
			sql.append("ORDER BY ");
			sql.append("approval_number ASC;");

			log.info("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setInt   (2, applicationNumber);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "The record was not found. Please investigate the cause by referring to the following.");
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

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new AppendedDto());
			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);

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
		log.info("[workflowEngine] " + "find end");
		return resultDto;
	}
}