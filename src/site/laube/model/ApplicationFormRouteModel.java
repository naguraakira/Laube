package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import site.laube.dto.ApplicationFormRouteDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.ApplicationFormRouteModelInterface;
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

public final class ApplicationFormRouteModel extends LaubeModel implements ApplicationFormRouteModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(ApplicationFormRouteModel.class);

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param applicationFormCode application form code
	 * @param unitCode unit code
	 * @return Application-specific root master entity
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode, final String applicationFormCode, final String unitCode) throws LaubeException {

		log.traceStart("find", companyCode, applicationFormCode, unitCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationFormCode))||(LaubeUtility.isBlank(unitCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("find");
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

			log.message("find", "[SQL] " + sql.toString());
			closePreparedStatement();
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

				log.message("find", "[SQL] " + sql.toString());
				closePreparedStatement();
				this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
				this.preparedStatement.setString(1, companyCode);
				this.preparedStatement.setString(2, applicationFormCode);

				this.resultSet = this.preparedStatement.executeQuery();

				if (!this.resultSet.first()) {
					log.message("find","the record was not found. Please investigate the cause by referring to the following.");
					log.message("find","[SQL]");
					log.message("find",sql.toString());
					log.message("find","");
					log.message("find","[Extraction condition]");
					log.message("find","[companyCode]: " + companyCode);
					log.message("find","[applicationFormCode]: " + applicationFormCode);
					log.message("find","[unitCode]: " + unitCode);
					resultDto.setStatus(false);
					resultDto.setMessageId("E1003");
					return null;
				}
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormRouteDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			return resultDto;

		} catch (final SQLException e) {
			throw new LaubeException("find",e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("find",e);
			}
			log.traceEnd("find");
		}
	}

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param individualRouteCode individual route code
	 * @return Application-specific root master entity
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public final ResultDto findByIndividualRouteCode(final String companyCode, final String individualRouteCode) throws LaubeException {

		log.traceStart("findByIndividualRouteCode", companyCode, individualRouteCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(individualRouteCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findByIndividualRouteCode");
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

			log.message("findByIndividualRouteCode","[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, individualRouteCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("findByIndividualRouteCode","The record was not found. Please investigate the cause by referring to the following.");
				log.message("findByIndividualRouteCode","[SQL]");
				log.message("findByIndividualRouteCode",sql.toString());
				log.message("findByIndividualRouteCode","");
				log.message("findByIndividualRouteCode","[Extraction condition]");
				log.message("findByIndividualRouteCode","[companyCode]: " + companyCode);
				log.message("findByIndividualRouteCode","[individualRouteCode]: " + individualRouteCode);
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
			throw new LaubeException("findByIndividualRouteCode",e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("findByIndividualRouteCode",e);
			}
			log.traceEnd("findByIndividualRouteCode");
		}
	}

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param commonRouteCode common route code
	 * @return Application-specific root master entity
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public final ResultDto findByCommonRouteCode(final String companyCode, final String commonRouteCode) throws LaubeException {

		log.traceStart("findByCommonRouteCode", companyCode, commonRouteCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(commonRouteCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findByCommonRouteCode");
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

			log.message("findByCommonRouteCode","[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, commonRouteCode);

			closePreparedStatement();
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("findByCommonRouteCode","the record was not found. Please investigate the cause by referring to the following.");
				log.message("findByCommonRouteCode","[SQL]");
				log.message("findByCommonRouteCode",sql.toString());
				log.message("findByCommonRouteCode","");
				log.message("findByCommonRouteCode","[Extraction condition]");
				log.message("findByCommonRouteCode","[companyCode]: " + companyCode);
				log.message("findByCommonRouteCode","[commonRouteCode]: " + commonRouteCode);
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
			throw new LaubeException("findByCommonRouteCode",e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("findByIndividualRouteCode",e);
			}
			log.traceEnd("findByCommonRouteCode");
		}
	}
}
