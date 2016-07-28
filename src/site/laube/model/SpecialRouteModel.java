package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import site.laube.dto.ActivityDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.dto.RouteDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.RouteModelInterface;
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

public final class SpecialRouteModel extends RouteModel implements RouteModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(SpecialRouteModel.class);

	/**
	 * removal of the special route master (company units)<br>
	 * remove all of the special route master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode) throws LaubeException {
		throw new LaubeException("delete", "special route master the same for the applicant classification master, please if you want to delete is running the deletion of the application classification master model.");
	}

	/**
	 * removal of the special route master (route units)<br>
	 * remove all of the route master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode, final String routeCode) throws LaubeException {
		throw new LaubeException("delete", "special route master the same for the applicant classification master, please if you want to delete is running the deletion of the application classification master model.");
	}

	/**
	 * register the special route master.<br>
	 * @param routeDto route master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto insert(final RouteDto routeDto) throws LaubeException {
		throw new LaubeException("insert", "special route master the same for the applicant classification master, please if you want to delete is running the deletion of the application classification master model.");
	}

	/**
	 * update the special route master.<br>
	 * @param routeDto route master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto update(final RouteDto routeDto) throws LaubeException {
		throw new LaubeException("update", "special route master the same for the applicant classification master, please if you want to delete is running the deletion of the application classification master model.");
	}

	/**
	 * spent check of the special route master<br>
	 * if the route is already being used in the application form by the root master, and then return true.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final boolean isOccupied(final String companyCode, final String routeCode) throws LaubeException {

		log.traceStart("isOccupied", companyCode, routeCode);

		try {
			final ApplicationClassificationModel applicationClassificationModel = new ApplicationClassificationModel();
			final ResultDto  resultDto = applicationClassificationModel.findBySpecialRouteCode(companyCode, routeCode);

			if (LaubeUtility.isEmpty(resultDto)){
				throw new LaubeException("isOccupied", "the record was not found. Please investigate the cause by referring to the following.");
			}else{
				if (LaubeUtility.isEmpty(resultDto.getResultData())){
					return false;
				}else{
					return true;
				}
			}

		} catch (final Exception e) {
			throw new LaubeException("isOccupied", e);

		} finally {
			log.traceEnd("isOccupied");
		}
	}

	/**
	 * search the special route master.<br>
	 * @param companyCode company code
	 * @return route master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode) throws LaubeException {

		log.traceStart("find", companyCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("find");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",route_code");
			sql.append(",route_name");
			sql.append(" FROM wkf_view_special_route");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" ORDER BY route_code");
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
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				return null;
			}

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new RouteDto());

			resultDto.setStatus(true);
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
	 * search the special route master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return route master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode, final String routeCode) throws LaubeException {

		log.traceStart("find", companyCode, routeCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(routeCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("find");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",route_code");
			sql.append(",route_name");
			sql.append(" FROM wkf_view_special_route");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and route_code = ?");
			sql.append(" ORDER BY route_code");
			sql.append(";");

			log.message("find", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, routeCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("find", "the record was not found. Please investigate the cause by referring to the following.");
				log.message("find", "[SQL]");
				log.message("find", sql.toString());
				log.message("find", "");
				log.message("find", "[Extraction condition]");
				log.message("find", "[companyCode]: " + companyCode);
				log.message("find", "[special_route_code]: " + routeCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}
			final ArrayList<LaubeDto> result = conversion(this.resultSet, new ActivityDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(result);
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
