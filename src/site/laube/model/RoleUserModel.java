package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.dto.RoleUserDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.RoleUserModelInterface;
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

public final class RoleUserModel extends LaubeModel implements RoleUserModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RoleUserModel.class);

	/**
	 * deleting a role by the employee master (role / employee number units)<br>
	 * remove the role by the employee master.<br>
	 * @param companyCode company code
	 * @param roleCode    role code
	 * @param userCode    user code
	 * @return result
	 * @throws LaubeException
	 */
	@Override
    public final ResultDto delete(final String companyCode, final String roleCode, final String userCode) throws LaubeException {

		log.debug("[workflowEngine] " + "delete Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[roleCode]: " + roleCode);
		log.debug("[workflowEngine] " + "[userCode]: " + userCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(roleCode))||(LaubeUtility.isBlank(userCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "delete End");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_role_user ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("role_code = ? AND ");
			sql.append("user_code = ? ");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, roleCode);
			this.preparedStatement.setString(3, userCode);
			this.preparedStatement.executeUpdate();

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "delete Start");
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
				log.error("[workflowEngine] " + "delete Start");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "delete Start");
		return resultDto;
    }

	/**
	 * register the role by the employee master.<br>
	 * @param roleUserDto role user dto
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto insert(final RoleUserDto roleUserDto) throws LaubeException {

		log.debug("[workflowEngine] " + "insert Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[roleUserDto]: " + roleUserDto);

		ResultDto resultDto = new ResultDto();

		if (roleUserDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "insert End");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_role_user ");
			sql.append("(");
			sql.append("company_code,");
			sql.append("role_code,");
			sql.append("unit_code,");
			sql.append("user_code,");
			sql.append("create_date_time,");
			sql.append("create_user_id,");
			sql.append("update_date_time,");
			sql.append("update_user_id) ");
			sql.append("VALUES(");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?);");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString( 1, roleUserDto.getCompanyCode());
			this.preparedStatement.setString( 2, roleUserDto.getRoleCode());
			this.preparedStatement.setString( 3, roleUserDto.getUnitCode());
			this.preparedStatement.setString( 4, roleUserDto.getUserCode());
			this.preparedStatement.setString( 5, roleUserDto.getCreateUserId());
			this.preparedStatement.setString( 6, roleUserDto.getUpdateUserId());
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
		log.debug("[workflowEngine] " + "insert End" + "[return value]:true");
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		return resultDto;
	}

	/**
	 * update the role by the employee master.<br>
	 * @param roleUserDto role user dto
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto update(final RoleUserDto roleUserDto) throws LaubeException {

		log.debug("[workflowEngine] " + "update Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[roleUserDto]: " + roleUserDto);

		ResultDto resultDto = new ResultDto();

		if (roleUserDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "update End");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_role_user ");
			sql.append("SET ");
			sql.append("unit_code = ?, ");
			sql.append("user_code = ?, ");
			sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
			sql.append("update_user_id = ?;");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("role_code = ?;");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString( 1, roleUserDto.getUnitCode());
			this.preparedStatement.setString( 2, roleUserDto.getUserCode());
			this.preparedStatement.setString( 3, roleUserDto.getUpdateUserId());
			this.preparedStatement.setString( 4, roleUserDto.getCompanyCode());
			this.preparedStatement.setString( 5, roleUserDto.getRoleCode());
			this.preparedStatement.executeUpdate();

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
	 * search only single item to the appropriate record.<br>
	 * @param companyCode company code
	 * @param roleCode    role code
	 * @param userCode    user code
	 * @return role user master
	 * @exception LaubeException
	 */
	@Override
	public final ResultDto find(final String companyCode, final String roleCode, final String userCode) throws LaubeException {

		log.debug("[workflowEngine] " + "find Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[roleCode]: " + roleCode);
		log.debug("[workflowEngine] " + "[userCode]: " + userCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(roleCode))||(LaubeUtility.isBlank(userCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "delete End");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("role_code, ");
			sql.append("role_name, ");
			sql.append("unit_code, ");
			sql.append("unit_name, ");
			sql.append("user_code, ");
			sql.append("user_name  ");
			sql.append("FROM ");
			sql.append("wkf_view_role_user");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("role_code = ? AND ");
			sql.append("user_code = ?");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL]");
			log.debug("[workflowEngine] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, roleCode);
			this.preparedStatement.setString(3, userCode);
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
				log.error("[workflowEngine] " + "find End");
				return null;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new RoleUserDto());

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
	 * employees will find the role that belongs.<br>
	 * @param companyCode company code
	 * @param userCode    user code
	 * @return role user master
	 * @exception LaubeException
	 */
	@Override
	public final ResultDto findByUserCode(final String companyCode, final String userCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findByUserCode Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[userCode]: " + userCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(userCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "findByUserCode End");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("role_code, ");
			sql.append("role_name, ");
			sql.append("unit_code, ");
			sql.append("unit_name, ");
			sql.append("user_code, ");
			sql.append("user_name  ");
			sql.append("FROM ");
			sql.append("wkf_view_user_role");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("user_code = ?");
			sql.append("ORDER BY role_code");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL]");
			log.debug("[workflowEngine] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, userCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "The record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[userCode]: " + userCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "findByUserCode Start");
				return null;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new RoleUserDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.debug("[workflowEngine] " + "findByUserCode Start");
			return resultDto;

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "findByUserCode Start");
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
	}

	/**
	 * search for all employees that belong to the role.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return role user master
	 * @exception LaubeException
	 */
	@Override
	public final ResultDto findByRoleCode(final String companyCode, final String roleCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findByRoleCode Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[roleCode]: " + roleCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(roleCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "findByRoleCode End");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("role_code, ");
			sql.append("role_name, ");
			sql.append("unit_code, ");
			sql.append("unit_name, ");
			sql.append("user_code, ");
			sql.append("user_name  ");
			sql.append("FROM ");
			sql.append("wkf_view_role_user");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("role_code = ?");
			sql.append("ORDER BY user_code");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL]");
			log.debug("[workflowEngine] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, roleCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "The record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[roleCode]: " + roleCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "findByRoleCode End");
				return null;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new RoleUserDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.debug("[workflowEngine] " + "findByRoleCode End");
			return resultDto;

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "findByRoleCode End");
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
				log.error("[workflowEngine] " + "findByRoleCode End");
				throw new LaubeException(e);
			}
		}
	}
}
