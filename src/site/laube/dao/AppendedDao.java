package site.laube.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.val;
import site.laube.daointerface.AppendedDaoInterface;
import site.laube.dto.AppendedDto;
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

public final class AppendedDao extends LaubeDao implements AppendedDaoInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(AppendedDao.class);

	/**
	 * delete the appended object<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	*/
	@SuppressWarnings("nls")
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
			// deletion processing (company unit)
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_appended");
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
			log.traceEnd("delete");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("delete");
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
	@SuppressWarnings("nls")
	public final ResultDto delete(final String companyCode, final int applicationNumber) throws LaubeException {

		log.traceStart("delete", companyCode, applicationNumber);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(applicationNumber == 0)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_appended");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and applicationNumber = ?");
			sql.append(";");

			log.message("delete", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setInt   (2, applicationNumber);
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
	 * register the boss master.<br>
	 * @param appendedDto appended dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto insert(final AppendedDto appendedDto) throws LaubeException {

		log.traceStart("insert", appendedDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(appendedDto)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_appended");
			sql.append("(");
			sql.append(" company_code");
			sql.append(",application_number");
			sql.append(",approval_company_code");
			sql.append(",approval_unit_code");
			sql.append(",approval_user_code");
			sql.append(",approval_path");
			sql.append(",approval_date");
			sql.append(",create_date_time");
			sql.append(",create_user_id");
			sql.append(",update_date_time");
			sql.append(",update_user_id");
			sql.append(")");
			sql.append(" VALUES");
			sql.append("(");
			sql.append(" ?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",CURRENT_TIMESTAMP(0)");
			sql.append(",CURRENT_TIMESTAMP(0)");
			sql.append(",?");
			sql.append(",CURRENT_TIMESTAMP(0)");
			sql.append(",?");
			sql.append(")");
			sql.append(";");

			log.message("insert", "[SQL] " + sql.toString());
			closePreparedStatement();
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
	 * To update the appended.<br>
	 * @param appendedDto appended dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto update(final AppendedDto appendedDto) throws LaubeException {

		log.traceStart("update", appendedDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(appendedDto)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("update");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_appended");
			sql.append(" SET");
			sql.append(" company_code = ?");
			sql.append(",application_number = ?");
			sql.append(",approval_number = ?");
			sql.append(",approval_company_code = ?");
			sql.append(",approval_unit_code = ?");
			sql.append(",approval_user_code = ?");
			sql.append(",approval_path = ?");
			sql.append(",approval_date = ?");
			sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(",update_user_id = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_number = ?");
			sql.append(" and approval_number = ?");
			sql.append(";");

			log.message("update", "[SQL] " + sql.toString());
			closePreparedStatement();
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
				log.message("update", "It failed to update the boss master.");
				log.message("update", "companyCode:" + appendedDto.getCompanyCode());
				log.message("update", "applicationNumber:" + appendedDto.getApplicationNumber());
				log.message("update", "approvalNumber:" + appendedDto.getApprovalNumber());
				log.message("update", "approvalCompanyCode:" + appendedDto.getApprovalCompanyCode());
				log.message("update", "approvalUnitCode:" + appendedDto.getApprovalUnitCode());
				log.message("update", "approvalUserCode:" + appendedDto.getApprovalUserCode());
				log.message("update", "approvalPath:" + appendedDto.getApprovalPath());
				log.message("update", "approvalDate:" + appendedDto.getApprovalDate());
				log.message("update", "updateUserId:" + appendedDto.getUpdateUserId());
				log.message("update", "ompanyCode:" + appendedDto.getCompanyCode());
				log.message("update", "applicationNumber:" + appendedDto.getApplicationNumber());
				log.message("update", "approvalNumber:" + appendedDto.getApprovalNumber());
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
				throw new LaubeException("insert", e);
			}
			log.traceEnd("update");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("update");
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

		log.traceStart("find", companyCode, applicationNumber);

		final ResultDto resultDto = new ResultDto();

		try {
			if (LaubeUtility.isEmpty(companyCode) || applicationNumber == 0){
				throw new LaubeException("find", "company Code / applicationNumber is a required field. Be sure to set.");
			}
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",application_number,");
			sql.append(",approval_number");
			sql.append(",approval_company_code");
			sql.append(",approval_unit_code");
			sql.append(",approval_user_code");
			sql.append(",approval_path");
			sql.append(",approval_date");
			sql.append(",create_date_time");
			sql.append(",create_user_id");
			sql.append(",update_date_time");
			sql.append(",update_user_id");
			sql.append(" from wkf_view_Appended as A");

			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and applicationNumber = ?");
			sql.append(" ORDER BY");
			sql.append(" approval_number ASC");
			sql.append(";");

			log.message("find", "[SQL] " + sql.toString());
			closePreparedStatement();
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
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				log.info("[workflowEngine] " + "find end");
				return resultDto;
			}

			final val resultData = conversion(this.resultSet, new AppendedDto());
			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);

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
		log.traceEnd("find");
		return resultDto;
	}
}