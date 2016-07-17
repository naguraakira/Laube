package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.dto.RoleDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.RoleModelInterface;
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

public final class RoleModel extends LaubeModel implements RoleModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RoleModel.class);

	/**
	 * delete role master (company units)<br>
	 * remove all of the role the master and the role by the employee master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto delete(final String companyCode) throws LaubeException {

		log.info("[workflowEngine] " + "delete start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);

		final ResultDto resultDto = new ResultDto();

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_role ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");

			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "delete end");
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
				log.info("[workflowEngine] " + "delete end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "delete end");
		return resultDto;
    }

	/**
	 * delete role master (role units)<br>
	 * remove all of the role the master and the role by the employee master.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto delete(final String companyCode, final String roleCode) throws LaubeException {

		log.info("[workflowEngine] " + "delete start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[roleCode]: " + roleCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(roleCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "delete end");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_role ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("role_code = ? ");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, roleCode);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "delete end");
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
				log.info("[workflowEngine] " + "delete end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "delete end");
		return resultDto;
    }

	/**
	 * register the role master.<br>
	 * @param roleDto role master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto insert(final RoleDto roleDto) throws LaubeException {

		log.info("[workflowEngine] " + "insert start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[roleDto]: " + roleDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(roleDto)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "insert end");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_role ");
			sql.append("(");
			sql.append("company_code,");
			sql.append("role_code,");
			sql.append("role_name,");
			sql.append("create_date_time,");
			sql.append("create_user_id,");
			sql.append("update_date_time,");
			sql.append("update_user_id) ");
			sql.append("VALUES(");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?);");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString( 1, roleDto.getCompanyCode());
			this.preparedStatement.setString( 2, roleDto.getRoleCode());
			this.preparedStatement.setString( 3, roleDto.getRoleName());
			this.preparedStatement.setString( 4, roleDto.getCreateUserId());
			this.preparedStatement.setString( 5, roleDto.getUpdateUserId());
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "insert end");
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
				log.info("[workflowEngine] " + "insert end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "insert end");
		return resultDto;
	}

	/**
	 * update the role master.<br>
	 * @param roleDto role master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto update(final RoleDto roleDto) throws LaubeException {

		log.info("[workflowEngine] " + "update start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[roleDto]: " + roleDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(roleDto)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.info("[workflowEngine] " + "update end");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_role ");
			sql.append("SET ");
			sql.append("role_name = ?, ");
			sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
			sql.append("update_user_id = ? ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("role_code = ?;");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString( 1, roleDto.getRoleName());
			this.preparedStatement.setString( 2, roleDto.getUpdateUserId());
			this.preparedStatement.setString( 3, roleDto.getCompanyCode());
			this.preparedStatement.setString( 4, roleDto.getRoleCode());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				log.error("[workflowEngine] It failed to update the application form master.");
				log.error("[workflowEngine] roleName:" + roleDto.getRoleName());
				log.error("[workflowEngine] updateUserId:" + roleDto.getUpdateUserId());
				log.error("[workflowEngine] companyCode:" + roleDto.getCompanyCode());
				log.error("[workflowEngine] roleCode:" + roleDto.getRoleCode());
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.info("[workflowEngine] " + "update end");
				return resultDto;
			}

		} catch (final SQLException e) {
			log.info("[workflowEngine] " + "update end");
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
				log.info("[workflowEngine] " + "update end");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.info("[workflowEngine] " + "update end");
		return resultDto;
	}

	/**
	 * the appropriate role code checks in use.<br>
	 * role code and returns true if the is not role by employees master and string.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return use check
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final boolean isOccupied(final String companyCode, final String roleCode) throws LaubeException {

		log.info("[workflowEngine] " + "isOccupied start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[roleCode]: " + roleCode);

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(roleCode))) {
			log.info("[workflowEngine] " + "isOccupied end");
			return false;
		}

		final RoleUserModel roleUserModel = new RoleUserModel();
		final ResultDto resultDto = roleUserModel.findByRoleCode(companyCode, roleCode);

		try {
			if (LaubeUtility.isEmpty(resultDto)){
				log.info("[workflowEngine] " + "isOccupied end");
				return false;
			}else{
				if (LaubeUtility.isEmpty(resultDto.getResultData())){
					log.info("[workflowEngine] " + "isOccupied end");
					return false;
				}else{
					log.info("[workflowEngine] " + "isOccupied end");
					return true;
				}
			}

		} catch (final Exception e) {
			log.info("[workflowEngine] " + "isOccupied end");
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
				log.info("[workflowEngine] " + "isOccupied end");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * search for the role a master at the company code.<br>
	 * @param companyCode company code
	 * @return role master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto find(final String companyCode) throws LaubeException {

		log.info("[workflowEngine] " + "find start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);

		final ResultDto resultDto = new ResultDto();

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("role_code, ");
			sql.append("role_name ");
			sql.append("FROM ");
			sql.append("wkf_view_role ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");
			sql.append("ORDER BY role_code");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.debug("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.debug("[workflowEngine] " + "[SQL]");
				log.debug("[workflowEngine] " + sql.toString());
				log.debug("[workflowEngine] " + "");
				log.debug("[workflowEngine] " + "[Extraction condition]");
				log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
				resultDto.setStatus(true);
				resultDto.setMessageId("N0001");
				log.info("[workflowEngine] " + "find end");
				return resultDto;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new RoleDto());

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
	 * perform a full match search of role master.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return role master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto findByRoleCode(final String companyCode, final String roleCode) throws LaubeException {

		log.info("[workflowEngine] " + "findByRoleCode start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[roleCode]: " + roleCode);

		final ResultDto resultDto = new ResultDto();

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("role_code, ");
			sql.append("role_name ");
			sql.append("FROM ");
			sql.append("wkf_view_role ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");
			sql.append("AND role_code = ?");
			sql.append("ORDER BY role_code");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, roleCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.debug("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.debug("[workflowEngine] " + "[SQL]");
				log.debug("[workflowEngine] " + sql.toString());
				log.debug("[workflowEngine] " + "");
				log.debug("[workflowEngine] " + "[Extraction condition]");
				log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.debug("[workflowEngine] " + "[roleCode]: " + roleCode);
				resultDto.setStatus(true);
				resultDto.setMessageId("N0001");
				log.info("[workflowEngine] " + "find end");
				return resultDto;
			}

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new RoleDto());

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
	 * do the fuzzy search of role master.<br>
	 * @param companyCode company code
	 * @param roleName role name
	 * @return role master list
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@Override
	public final ResultDto findByRoleName(final String companyCode, final String roleName) throws LaubeException {

		log.info("[workflowEngine] " + "findByRoleName start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.info("[workflowEngine] " + "[roleName]: " + roleName);

		final ResultDto resultDto = new ResultDto();

		try {
			boolean isSettingYes = false;

			if (LaubeUtility.isNotEmpty(roleName)){
				isSettingYes = true;
			}

			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("role_code, ");
			sql.append("role_name ");
			sql.append("FROM ");
			sql.append("wkf_view_role ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");

			if (isSettingYes){
				sql.append("AND role_name LIKE ?");
			}

			sql.append("ORDER BY company_cde, role_code");
			sql.append(";");

			log.debug("[workflowEngine] " + "[SQL] " + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);

			if (isSettingYes){
				this.preparedStatement.setString(2, "%" + roleName + "%");
			}

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.debug("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.debug("[workflowEngine] " + "[SQL]");
				log.debug("[workflowEngine] " + sql.toString());
				log.debug("[workflowEngine] " + "");
				log.debug("[workflowEngine] " + "[Extraction condition]");
				log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.debug("[workflowEngine] " + "[roleName]: " + roleName);
				resultDto.setStatus(true);
				resultDto.setMessageId("N0001");
				log.info("[workflowEngine] " + "find end");
				return resultDto;
			}

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new RoleDto());

			log.debug("[workflowEngine] " + "find end" + "[return value]:" + resultData);
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
}
