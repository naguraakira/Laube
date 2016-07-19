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
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto find(final String companyCode, final String applicationFormCode, final String unitCode) throws LaubeException {

		log.info("[workflowEngine] " + "find start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[applicationFormCode]: " + applicationFormCode);
		log.info("[workflowEngine] " + "[unitCode]: " + unitCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))||(LaubeUtility.isBlank(unitCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "find end");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",application_form_code");
			sql.append(",application_form_name");
			sql.append(",application_classification_code");
			sql.append(",application_classification_name");
			sql.append(",unit_code");
			sql.append(",unit_name");
			sql.append(",individual_route_code");
			sql.append(",individual_route_name");
			sql.append(",common_route_code");
			sql.append(",common_route_name");
			sql.append(" FROM wkf_view_application_form_route");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and application_form_code = ?");
			sql.append(" and unit_code = ?");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, applicationFormCode);
			this.preparedStatement.setString(3, unitCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				sql = new StringBuffer();
				sql.append("SELECT");
				sql.append(" company_code");
				sql.append(",company_name");
				sql.append(",application_form_code");
				sql.append(",application_form_name");
				sql.append(",application_classification_code");
				sql.append(",application_classification_name");
				sql.append(",unit_code");
				sql.append(",unit_name");
				sql.append(",individual_route_code");
				sql.append(",individual_route_name");
				sql.append(",common_route_code");
				sql.append(",common_route_name");
				sql.append(" FROM wkf_view_application_form_route");
				sql.append(" WHERE");
				sql.append(" company_code = ?");
				sql.append(" and application_form_code = ?");
				sql.append(" and unit_code IS NULL");
				sql.append(";");

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
					log.info("[workflowEngine] " + "find end");
					return null;
				}
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormRouteDto());

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
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param individualRouteCode individual route code
	 * @return Application-specific root master entity
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public final ResultDto findByIndividualRouteCode(final String companyCode, final String individualRouteCode) throws LaubeException {

		log.info("[workflowEngine] " + "findByIndividualRouteCode start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[individualRouteCode]: " + individualRouteCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(individualRouteCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "findByIndividualRouteCode end");
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
			sql.append(",unit_code");
			sql.append(",unit_name");
			sql.append(",individual_route_code");
			sql.append(",individual_route_name");
			sql.append(",common_route_code");
			sql.append(",common_route_name");
			sql.append(" FROM wkf_view_application_form_route");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and individual_route_code = ?");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
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
				log.info("[workflowEngine] " + "findByIndividualRouteCode end");
				return null;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormRouteDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.info("[workflowEngine] " + "findByIndividualRouteCode end");
			return resultDto;

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "findByIndividualRouteCode end");
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
				log.info("[workflowEngine] " + "findByIndividualRouteCode end");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param commonRouteCode common route code
	 * @return Application-specific root master entity
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public final ResultDto findByCommonRouteCode(final String companyCode, final String commonRouteCode) throws LaubeException {

		log.info("[workflowEngine] " + "findByCommonRouteCode start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[commonRouteCode]: " + commonRouteCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(commonRouteCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "findByCommonRouteCode end");
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
			sql.append(",unit_code");
			sql.append(",unit_name");
			sql.append(",individual_route_code");
			sql.append(",individual_route_name");
			sql.append(",common_route_code");
			sql.append(",common_route_name");
			sql.append(" FROM wkf_view_application_form_route");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and common_route_code = ?");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
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
				log.info("[workflowEngine] " + "findByCommonRouteCode end");
				return null;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormRouteDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.info("[workflowEngine] " + "findByCommonRouteCode end");
			return resultDto;

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "findByCommonRouteCode end");
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
				log.info("[workflowEngine] " + "findByCommonRouteCode end");
				throw new LaubeException(e);
			}
		}
	}
}
