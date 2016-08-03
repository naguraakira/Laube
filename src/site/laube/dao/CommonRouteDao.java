package site.laube.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.val;
import site.laube.daointerface.RouteDaoInterface;
import site.laube.dto.ResultDto;
import site.laube.dto.RouteDto;
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

public final class CommonRouteDao extends RouteDao implements RouteDaoInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(CommonRouteDao.class);

	/**
	 * removal of the common route master (company units)<br>
	 * remove all of the common route master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode) throws LaubeException {

		log.traceStart("delete", companyCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_common_route");
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
				throw new LaubeException("delete",e);
			}
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("delete");
		return resultDto;
    }

	/**
	 * removal of the common route master (route units)<br>
	 * remove all of the route master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode, final String routeCode) throws LaubeException {

		log.traceStart("delete", companyCode, routeCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||((LaubeUtility.isBlank(routeCode)))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_common_route");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and individual_route_code = ?");
			sql.append(";");

			log.message("delete", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, routeCode);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			throw new LaubeException("delete",e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("delete",e);
			}
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("delete");
		return resultDto;
	}

	/**
	 * register the common route master.<br>
	 * @param routeDto route master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	 	public final ResultDto insert(final RouteDto routeDto) throws LaubeException {

		log.traceStart("insert", routeDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(routeDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_common_route");
			sql.append("(");
			sql.append(" company_code");
			sql.append(",individual_route_code");
			sql.append(",individual_route_name");
			sql.append(",create_date_time");
			sql.append(",create_user_id");
			sql.append(",update_date_time");
			sql.append(",update_user_id");
			sql.append(")");
			sql.append(" VALUES(");
			sql.append(" ?");
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

			this.preparedStatement.setString( 1, routeDto.getCompanyCode());
			this.preparedStatement.setString( 2, routeDto.getRouteCode());
			this.preparedStatement.setString( 3, routeDto.getRouteName());
			this.preparedStatement.setString( 4, routeDto.getCreateUserId());
			this.preparedStatement.setString( 5, routeDto.getUpdateUserId());
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			throw new LaubeException("insert", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("delete", e);
			}
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("insert");
		return resultDto;
	}

	/**
	 * update the common route master.<br>
	 * @param routeDto route master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto update(final RouteDto routeDto) throws LaubeException {

		log.traceStart("update", routeDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(routeDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("update");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_common_route");
			sql.append(" SET");
			sql.append(" individual_route_name = ?");
			sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(",update_user_id = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and individual_route_code = ?");
			sql.append(";");

			log.message("update", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString( 1, routeDto.getRouteName());
			this.preparedStatement.setString( 2, routeDto.getUpdateUserId());
			this.preparedStatement.setString( 3, routeDto.getCompanyCode());
			this.preparedStatement.setString( 4, routeDto.getRouteCode());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				log.message("update", "it failed to update the application form master.");
				log.message("update", "routeName:" + routeDto.getRouteName());
				log.message("update", "updateUserId:" + routeDto.getUpdateUserId());
				log.message("update", "companyCode:" + routeDto.getCompanyCode());
				log.message("update", "grouteCode:" + routeDto.getRouteCode());
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
				throw new LaubeException("delete", e);
			}
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("update");
		return resultDto;
	}

	/**
	 * spent check of the common route master<br>
	 * if the route is already being used in the application form by the root master, and then return true.<br>
	 * @param companyCode company code
	 * @param commonRouteCode common route code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final boolean isOccupied(final String companyCode, final String commonRouteCode) throws LaubeException {

		log.traceStart("isOccupied", companyCode, commonRouteCode);

		if ((LaubeUtility.isBlank(companyCode))||((LaubeUtility.isBlank(commonRouteCode)))) {
			log.traceEnd("isOccupied");
			return false;
		}

		try {
			ApplicationFormRouteDao applicationFormRouteModel = new ApplicationFormRouteDao();
			final ResultDto resultDto = applicationFormRouteModel.findByCommonRouteCode(companyCode, commonRouteCode);

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
			throw new LaubeException("isOccupied",e);

		} finally {
			log.traceEnd("isOccupied");
		}
	}

	/**
	 * search the common route master.<br>
	 * @param companyCode company code
	 * @return route master
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
			sql.append(",route_code");
			sql.append(",route_name");
			sql.append(" FROM wkf_view_common_route");
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
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}

			final val resultData = conversion(this.resultSet, new RouteDto());

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
	 * search the common route master.<br>
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
			sql.append(",route_code");
			sql.append(",route_name");
			sql.append(" FROM wkf_view_common_route");
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
				log.message("find", "[routeCode]: " + routeCode);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return null;
			}

			final val resultData = conversion(this.resultSet, new RouteDto());

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
