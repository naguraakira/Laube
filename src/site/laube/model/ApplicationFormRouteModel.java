package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.ApplicationFormRouteDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.ApplicationFormRouteModelInterface;
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

public final class ApplicationFormRouteModel extends LaubeModel implements ApplicationFormRouteModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApplicationFormRouteModel.class);

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param applicationFormCode application form code
	 * @param unitCode unit code
	 * @return Application-specific root master entity
	 * @exception LaubeException
	 */
	@Override
	public final ResultDto find(final String companyCode, final String applicationFormCode, final String unitCode) throws LaubeException {

		log.debug("[workflowEngine] " + "find Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[applicationFormCode]: " + applicationFormCode);
		log.debug("[workflowEngine] " + "[unitCode]: " + unitCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))||(LaubeUtility.isBlank(unitCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "find Start");
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
			sql.append("unit_code, ");
			sql.append("unit_name, ");
			sql.append("individual_route_code, ");
			sql.append("individual_route_name, ");
			sql.append("common_route_code, ");
			sql.append("common_route_name ");
			sql.append("FROM wkf_view_application_form_route ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");
			sql.append("and application_form_code = ? ");
			sql.append("and unit_code = ?;");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, applicationFormCode);
			this.preparedStatement.setString(3, unitCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.debug("[workflowEngine] " + "受け取った部門コードのデータが存在しなかった為、部門コードがNULLのレコードを検索");
				sql = new StringBuffer();
				sql.append("SELECT ");
				sql.append("company_code, ");
				sql.append("company_name, ");
				sql.append("application_form_code, ");
				sql.append("application_form_name, ");
				sql.append("application_classification_code, ");
				sql.append("application_classification_name, ");
				sql.append("unit_code, ");
				sql.append("unit_name, ");
				sql.append("individual_route_code, ");
				sql.append("individual_route_name, ");
				sql.append("common_route_code, ");
				sql.append("common_route_name ");
				sql.append("FROM wkf_view_application_form_route ");
				sql.append("WHERE ");
				sql.append("company_code = ? ");
				sql.append("and application_form_code = ? ");
				sql.append("and unit_code IS NULL;");

				log.debug("[workflowEngine] SQL:" + sql.toString());
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
					log.error("[workflowEngine] " + "[unitCode]: " + unitCode);
					resultDto.setStatus(false);
					resultDto.setMessageId("E1003");
					log.error("[workflowEngine] " + "find End");
					return null;
				}
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormRouteDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.debug("[workflowEngine] " + "find End");
			return resultDto;

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "find End");
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
				log.error("[workflowEngine] " + "find End");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param individualRouteCode individual route code
	 * @return Application-specific root master entity
	 * @exception LaubeException
	 */
	public final ResultDto findByIndividualRouteCode(final String companyCode, final String individualRouteCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findByIndividualRouteCode Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[individualRouteCode]: " + individualRouteCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(individualRouteCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "find Start");
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
			sql.append("unit_code, ");
			sql.append("unit_name, ");
			sql.append("individual_route_code, ");
			sql.append("individual_route_name, ");
			sql.append("common_route_code, ");
			sql.append("common_route_name ");
			sql.append("FROM wkf_view_application_form_route ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");
			sql.append("and individual_route_code = ?;");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, individualRouteCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "The record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[individualRouteCode]: " + individualRouteCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "findByIndividualRouteCode End");
				return null;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormRouteDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.debug("[workflowEngine] " + "findByIndividualRouteCode End");
			return resultDto;

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "findByIndividualRouteCode End");
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
				log.error("[workflowEngine] " + "findByIndividualRouteCode End");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param commonRouteCode common route code
	 * @return Application-specific root master entity
	 * @exception LaubeException
	 */
	public final ResultDto findByCommonRouteCode(final String companyCode, final String commonRouteCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findByCommonRouteCode Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[commonRouteCode]: " + commonRouteCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(commonRouteCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "find Start");
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
			sql.append("unit_code, ");
			sql.append("unit_name, ");
			sql.append("individual_route_code, ");
			sql.append("individual_route_name, ");
			sql.append("common_route_code, ");
			sql.append("common_route_name ");
			sql.append("FROM wkf_view_application_form_route ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");
			sql.append("and common_route_code = ?;");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, commonRouteCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[commonRouteCode]: " + commonRouteCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "findByCommonRouteCode End");
				return null;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormRouteDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.debug("[workflowEngine] " + "findByCommonRouteCode End");
			return resultDto;

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "findByCommonRouteCode End");
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
				log.error("[workflowEngine] " + "findByCommonRouteCode End");
				throw new LaubeException(e);
			}
		}
	}
}
