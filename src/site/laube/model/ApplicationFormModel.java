package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.ApplicationFormDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.ApplicationFormModelInterface;
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

public final class ApplicationFormModel extends LaubeModel implements ApplicationFormModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApplicationFormModel.class);

	/**
	 * delete the application form master (company unit)<br>
	 * remove the application-specific activity master and all of the application form master.
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto delete(final String companyCode) throws LaubeException {

		log.debug("[workflowEngine] " + "delete start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_application_form ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.executeUpdate();

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "delete end");
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
				log.error("[workflowEngine] " + "delete end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "delete end");
		return resultDto;
    }

	/**
	 * delete the application classification master (application code unit)<br>
	 * remove the application classification by Activision Byi master to match the application classification master.
	 * @param companyCode Company code
	 * @param applicationFormCode Application code
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto delete(final String companyCode, final String applicationFormCode) throws LaubeException {

		log.debug("[workflowEngine] " + "delete start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[applicationFormCode]: " + applicationFormCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_application_form ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("application_form_code = ? ");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, applicationFormCode);
			this.preparedStatement.executeUpdate();

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "delete end");
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
				log.error("[workflowEngine] " + "delete end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "delete end");
		return resultDto;
    }

	/**
	 * register the application form master.<br>
	 * @param applicationFormDto Application form master
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto insert(final ApplicationFormDto applicationFormDto) throws LaubeException {

		log.debug("[workflowEngine] " + "insert start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[applicationFormDto]: " + applicationFormDto);

		ResultDto resultDto = new ResultDto();

		if (applicationFormDto == null) {
			log.error("[workflowEngine] " + "insert end" + "[return value]:false");
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_application_form ");
			sql.append("(");
			sql.append("company_code,");
			sql.append("application_form_code,");
			sql.append("application_form_name,");
			sql.append("application_classification_code,");
			sql.append("auto_approval_flag,");
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
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?);");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString(  1, applicationFormDto.getCompanyCode());
			this.preparedStatement.setString(  2, applicationFormDto.getApplicationFormCode());
			this.preparedStatement.setString(  3, applicationFormDto.getApplicationFormName());
			this.preparedStatement.setString(  4, applicationFormDto.getApplicationClassificationCode());
			this.preparedStatement.setBoolean( 5, applicationFormDto.isAutoApprovalFlag());
			this.preparedStatement.setString(  6, applicationFormDto.getCreateUserId());
			this.preparedStatement.setString(  7, applicationFormDto.getUpdateUserId());
			this.preparedStatement.executeUpdate();

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "insert end");
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
				log.error("[workflowEngine] " + "insert end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.error("[workflowEngine] " + "insert end");
		return resultDto;
	}

	/**
	 * to update the application form master.<br>
	 * @param applicationFormDto Application form master
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto update(final ApplicationFormDto applicationFormDto) throws LaubeException {

		log.debug("[workflowEngine] " + "update start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[applicationFormDto]: " + applicationFormDto);

		ResultDto resultDto = new ResultDto();

		if (applicationFormDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "update end");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_application_form ");
			sql.append("SET ");
			sql.append("application_form_name = ?, ");
			sql.append("application_classification_code = ?, ");
			sql.append("auto_approval_flag = ?, ");
			sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
			sql.append("update_user_id = ? ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("application_form_code = ?;");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString(  1, applicationFormDto.getApplicationFormName());
			this.preparedStatement.setString(  2, applicationFormDto.getApplicationClassificationCode());
			this.preparedStatement.setBoolean( 3, applicationFormDto.isAutoApprovalFlag());
			this.preparedStatement.setString(  4, applicationFormDto.getUpdateUserId());
			this.preparedStatement.setString(  5, applicationFormDto.getCompanyCode());
			this.preparedStatement.setString(  6, applicationFormDto.getApplicationFormCode());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				log.error("[workflowEngine] It failed to update the application form master.");
				log.error("[workflowEngine] applicationFormName:" + applicationFormDto.getApplicationFormName());
				log.error("[workflowEngine] applicationClassificationCode:" + applicationFormDto.getApplicationClassificationCode());
				log.error("[workflowEngine] autoApprovalFlag:" + applicationFormDto.isAutoApprovalFlag());
				log.error("[workflowEngine] companyCode:" + applicationFormDto.getCompanyCode());
				log.error("[workflowEngine] applicationFormCode:" + applicationFormDto.getApplicationFormCode());
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "update end");
				return resultDto;
			}

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "update end");
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
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "update end");
		return resultDto;
	}

	/**
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @return Application form master
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode) throws LaubeException {

		log.debug("[workflowEngine] " + "find start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "find end");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("A.\"company_code\", ");
			sql.append("A.\"company_name\", ");
			sql.append("A.\"application_form_code\", ");
			sql.append("A.\"application_form_name\", ");
			sql.append("A.\"application_classification_code\", ");
			sql.append("A.\"application_classification_name\", ");
			sql.append("A.\"auto_approval_flag\" ");
			sql.append("FROM \"wkf_view_application_form\" ");
			sql.append("WHERE ");
			sql.append("\"company_code\" = ?;");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "find end");
				return null;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.debug("[workflowEngine] " + "find end");
			return resultDto;

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "find end");
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
				log.error("[workflowEngine] " + "find end");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @param applicationFormCode Application code
	 * @return Application form master
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto findByApplicationFormCode(final String companyCode, final String applicationFormCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findByApplicationFormCode start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[applicationFormCode]: " + applicationFormCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.debug("[workflowEngine] " + "findByApplicationFormCode end");
			return resultDto;

		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("application_form_code, ");
			sql.append("application_form_name, ");
			sql.append("application_classification_code, ");
			sql.append("application_classification_name, ");
			sql.append("auto_approval_flag, ");
			sql.append("pulling_flag, ");
			sql.append("route_flag ");
			sql.append("FROM wkf_view_application_form ");
			sql.append("WHERE ");
			sql.append("company_code = ?");
			sql.append(" AND application_form_code = ?;");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, applicationFormCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[applicationFormCode]: " + applicationFormCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "findByApplicationFormCode end");
				return resultDto;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.debug("[workflowEngine] " + "findByApplicationFormCode end");
			return resultDto;

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "findByApplicationFormCode end");
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
				log.error("[workflowEngine] " + "findByApplicationFormCode end");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @param applicationClassificationCode application classification code
	 * @return ResultDto
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto findByApplicationClassificationCode(final String companyCode, final String applicationClassificationCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findByApplicationClassificationCode start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[applicationClassificationCode]: " + applicationClassificationCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationClassificationCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("A.company_code, ");
			sql.append("A.company_name, ");
			sql.append("A.application_form_code, ");
			sql.append("A.application_form_name, ");
			sql.append("A.application_classification_code, ");
			sql.append("A.application_classification_name, ");
			sql.append("A.auto_approval_flag ");
			sql.append("FROM wkf_view_application_form ");
			sql.append("WHERE ");
			sql.append("company_code = ?");
			sql.append(" AND application_classification_code = ?;");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, applicationClassificationCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[applicationClassificationCode]: " + applicationClassificationCode);
				log.error("[workflowEngine] " + "findByApplicationClassificationCode end" + "[return value]:null");
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "findByApplicationClassificationCode end");
				return resultDto;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.debug("[workflowEngine] " + "findByApplicationClassificationCode end");
			return resultDto;

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "findByApplicationClassificationCode end");
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
				log.error("[workflowEngine] " + "findByApplicationClassificationCode end");
				throw new LaubeException(e);
			}
		}
	}
}
