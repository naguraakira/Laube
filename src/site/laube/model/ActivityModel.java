package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.ActivityDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.ActivityModelInterface;
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

public abstract class ActivityModel extends LaubeModel implements ActivityModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ActivityModel.class);

	/**
	 * it returns the route model class.<br>
	 * @param routeType route type
	 * @return model object
	 * @throws LaubeException
	 */
	public static ActivityModel factory(RouteType routeType) throws LaubeException {

		switch(routeType) {

		case IndividualRoute:
			return new IndividualActivityModel();

		case CommonRoute:
			return new CommonActivityModel();

		case SpecialRoute:
			return new SpecialActivityModel();

		default:
			log.error("[WKF] " + "route type is incorrect. Please check it.[routeType] " + routeType);
			throw new LaubeException("[WKF] Route type is incorrect. Please check it.[routeType] " + routeType);
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
	 * @throws LaubeException
	 */
    public final ResultDto delete(final String companyCode) throws LaubeException {

		log.debug("[workflowEngine] " + "delete Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			String sql = deleteQuery();

			this.preparedStatement = connection.prepareStatement(sql);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.executeUpdate();

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "delete End");
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
				log.error("[workflowEngine] " + "delete End");
				throw new LaubeException(e);
			}
		}
		log.debug("[workflowEngine] " + "delete End");
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		return resultDto;
    }

	/**
	 * removal of the common activity master (route units)<br>
	 * remove all of the common activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @throws LaubeException
	 */
	@Override
    public final ResultDto delete(final String companyCode, final String routeCode) throws LaubeException {

		log.debug("[workflowEngine] " + "delete Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[routeCode]: " + routeCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(routeCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			String sql = deleteByRouteQuery();

			this.preparedStatement = connection.prepareStatement(sql);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, routeCode);
			this.preparedStatement.executeUpdate();

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
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
		log.debug("[workflowEngine] " + "delete End");
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		return resultDto;
   }

	/**
	 * register the activity master.<br>
	 * @param ActivityDto activity master
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto insert(final ActivityDto activityDto) throws LaubeException {

		log.debug("[workflowEngine] " + "insert Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[activityDto]: " + activityDto);

		ResultDto resultDto = new ResultDto();

		if (activityDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			String sql = insertQuery();

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
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "insert End");
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
				log.error("[workflowEngine] " + "insert End");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "insert End");
		return resultDto;
	}

	/**
	 * update the activity master.<br>
	 * @param ActivityDto activity master
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto update(final ActivityDto activityDto) throws LaubeException {

		log.debug("[workflowEngine] " + "update Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[activityDto]: " + activityDto);

		ResultDto resultDto = new ResultDto();

		if (activityDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			String sql = updateQuery();

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
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.debug("[workflowEngine] " + "update End");
				return resultDto;
			}

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "update End");
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
				log.error("[workflowEngine] " + "update End");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "update End");
		return resultDto;
	}

	/**
	 * company code, route code to find the activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return activity master
	 * @exception LaubeException
	 */
	@Override
	public final ResultDto find(final String companyCode, final String routeCode) throws LaubeException {

		log.debug("[workflowEngine] " + "find Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: "  + companyCode);
		log.debug("[workflowEngine] " + "[routeCode]: "    + routeCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(routeCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			String sql = findByRouteQuery();


			this.preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, routeCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[individual_route_code]: " + routeCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "find End");
				return resultDto;
			}
			ArrayList<LaubeDto> result = conversion(this.resultSet, new ActivityDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(result);
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
	 * company code, route code, the activity code, to find the activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @param activityCode activity code
	 * @return activity master
	 * @exception LaubeException
	 */
	@Override
	public final ResultDto find(final String companyCode, final String routeCode, final String activityCode) throws LaubeException {

		log.debug("[workflowEngine] " + "find Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: "  + companyCode);
		log.debug("[workflowEngine] " + "[routeCode]: "    + routeCode);
		log.debug("[workflowEngine] " + "[activityCode]: " + activityCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(routeCode))||(LaubeUtility.isBlank(activityCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			String sql = findByActivityQuery();

			this.preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, routeCode);
			this.preparedStatement.setString(3, activityCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[route_code]: " + routeCode);
				log.error("[workflowEngine] " + "[activityCode]: " + activityCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "find End");
				return resultDto;
			}
			ArrayList<LaubeDto> result = conversion(this.resultSet, new ActivityDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(result);
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
}