package site.laube.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.val;
import site.laube.daointerface.ActivityDaoInterface;
import site.laube.dto.ActivityDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
import site.laube.utility.LaubeUtility;
import site.laube.utility.type.RouteType;

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

public abstract class ActivityDao extends LaubeDao implements ActivityDaoInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = new LaubeLogger(LaubeLoggerFactory.getLogger(ActivityDao.class));

	/**
	 * it returns the route model class.<br>
	 * @param routeType route type
	 * @return model object
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public static ActivityDao factory(final RouteType routeType) throws LaubeException {

		log.traceStart("factory", routeType);

		switch(routeType) {

		case IndividualRoute:
			log.traceEnd("factory");
			return new IndividualActivityDao();

		case CommonRoute:
			log.traceEnd("factory");
			return new CommonActivityDao();

		case SpecialRoute:
			log.traceEnd("factory");
			return new SpecialActivityDao();

		default:
			throw new LaubeException("factory", "[WKF] Route type is incorrect. Please check it.[routeType] " + routeType);
		}
	}

	/**
	 * delete query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected abstract String deleteQuery();

	/**
	 * delete by route query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected abstract String deleteByRouteQuery();

	/**
	 * insert query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected abstract String insertQuery();

	/**
	 * update query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected abstract String updateQuery();

	/**
	 * find by route query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected abstract String findByRouteQuery();

	/**
	 * find by activity query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected abstract String findByActivityQuery();

	/**
	 * removal of the common activity master (company units)<br>
	 * remove all of the common activity master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
    @SuppressWarnings("nls")
	public final ResultDto delete(final String companyCode) throws LaubeException {

		log.traceStart("delete", companyCode);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final String sql = deleteQuery();

			log.message("delete", "[SQL] " + sql);
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql);
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
	 * removal of the common activity master (route units)<br>
	 * remove all of the common activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
    public final ResultDto delete(final String companyCode, final String routeCode) throws LaubeException {

		log.traceStart("delete", companyCode, routeCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(routeCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final String sql = deleteByRouteQuery();

			log.message("delete", "[SQL] " + sql);
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, routeCode);
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
	 * register the activityd dto.<br>
	 * @param activityDto activityd dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto insert(final ActivityDto activityDto) throws LaubeException {

		log.traceStart("insert", activityDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(activityDto)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
			return resultDto;
		}

		try {
			final String sql = insertQuery();

			log.message("insert", "[SQL] " + sql);
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql);

			this.preparedStatement.setString( 1, activityDto.getCompanyCode());
			this.preparedStatement.setString( 2, activityDto.getRouteCode());
			this.preparedStatement.setInt   ( 3, activityDto.getActivityCode());
			this.preparedStatement.setString( 4, activityDto.getApprovalCompanyCode());
			this.preparedStatement.setString( 5, activityDto.getApprovalRoleCode());
			this.preparedStatement.setString( 6, activityDto.getApprovalUnitCode());
			this.preparedStatement.setString( 7, activityDto.getApprovalUserCode());
			this.preparedStatement.setInt   ( 8, activityDto.getFunction());
			this.preparedStatement.setString( 9, activityDto.getPartyCode());
			this.preparedStatement.setInt   (10, activityDto.getPartyCodeConnector());
			this.preparedStatement.setString(11, activityDto.getNextPartyCode());
			this.preparedStatement.setString(12, activityDto.getPartyTransitCode());
			this.preparedStatement.setInt   (13, activityDto.getPartyTransitCodeConnector());
			this.preparedStatement.setString(14, activityDto.getBranchItemName());
			this.preparedStatement.setInt   (15, activityDto.getBranchItem());
			this.preparedStatement.setInt   (16, activityDto.getComparisonOperator());
			this.preparedStatement.setString(17, activityDto.getCreateUserId());
			this.preparedStatement.setString(18, activityDto.getUpdateUserId());
			this.preparedStatement.executeUpdate();

		} catch (SQLException e) {
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
	 * update the activityd dto.<br>
	 * @param activityDto activity dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto update(final ActivityDto activityDto) throws LaubeException {

		log.traceStart("update", activityDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(activityDto)) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("update");
			return resultDto;
		}

		try {
			final String sql = updateQuery();

			log.message("update", "[SQL] " + sql);
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql);

			this.preparedStatement.setString( 1, activityDto.getApprovalCompanyCode());
			this.preparedStatement.setString( 2, activityDto.getApprovalRoleCode());
			this.preparedStatement.setString( 3, activityDto.getApprovalUnitCode());
			this.preparedStatement.setString( 4, activityDto.getApprovalUserCode());
			this.preparedStatement.setInt   ( 5, activityDto.getFunction());
			this.preparedStatement.setString( 6, activityDto.getPartyCode());
			this.preparedStatement.setInt   ( 7, activityDto.getPartyCodeConnector());
			this.preparedStatement.setString( 8, activityDto.getNextPartyCode());
			this.preparedStatement.setString( 9, activityDto.getPartyTransitCode());
			this.preparedStatement.setInt   (10, activityDto.getPartyTransitCodeConnector());
			this.preparedStatement.setString(11, activityDto.getBranchItemName());
			this.preparedStatement.setInt   (12, activityDto.getBranchItem());
			this.preparedStatement.setInt   (13, activityDto.getComparisonOperator());
			this.preparedStatement.setString(14, activityDto.getUpdateUserId());
			this.preparedStatement.setString(15, activityDto.getCompanyCode());
			this.preparedStatement.setString(16, activityDto.getRouteCode());
			this.preparedStatement.setInt   (17, activityDto.getActivityCode());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				log.traceEnd("update");
				return resultDto;
			}

		} catch (final SQLException e) {
			throw new LaubeException("update", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("find", e);
			}
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("update");
		return resultDto;
	}

	/**
	 * company code, route code to find the activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return activity master
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
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
			final String sql = findByRouteQuery();
			log.message("find", "[SQL] " + sql);
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
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
				log.message("find", "[individual_route_code]: " + routeCode);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}
			final val result = conversion(this.resultSet, new ActivityDto());

			resultDto.setSuccess(true);
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

	/**
	 * company code, route code, the activity code, to find the activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @param activityCode activity code
	 * @return activity master
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode, final String routeCode, final String activityCode) throws LaubeException {

		log.traceStart("find", companyCode, routeCode, activityCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(routeCode))||(LaubeUtility.isBlank(activityCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("find");
			return resultDto;
		}

		try {
			final String sql = findByActivityQuery();

			log.message("find","[SQL] " + sql);
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, routeCode);
			this.preparedStatement.setString(3, activityCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("find","the record was not found. Please investigate the cause by referring to the following.");
				log.message("find","[SQL]");
				log.message("find",sql.toString());
				log.message("find","");
				log.message("find","[Extraction condition]");
				log.message("find","[companyCode]: " + companyCode);
				log.message("find","[route_code]: " + routeCode);
				log.message("find","[activityCode]: " + activityCode);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}
			final val result = conversion(this.resultSet, new ActivityDto());

			resultDto.setSuccess(true);
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