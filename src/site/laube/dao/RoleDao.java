package site.laube.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import site.laube.daointerface.RoleDaoInterface;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.dto.RoleDto;
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

public final class RoleDao extends LaubeDao implements RoleDaoInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(RoleDao.class);

	/**
	 * delete role master (company units)<br>
	 * remove all of the role the master and the role by the employee master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode) throws LaubeException {

		log.traceStart("delete", companyCode);

		final ResultDto resultDto = new ResultDto();

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_role");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(";");

			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
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
			log.traceEnd("delete");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("delete");
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
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode, final String roleCode) throws LaubeException {

		log.traceStart("delete", companyCode, roleCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(roleCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_role");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and role_code = ?");
			sql.append(";");

			log.message("delete", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, roleCode);
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			throw new LaubeException("delete", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("delete", e);
			}
			log.traceEnd("delete");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("delete");
		return resultDto;
    }

	/**
	 * register the role master.<br>
	 * @param roleDto role master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto insert(final RoleDto roleDto) throws LaubeException {

		log.traceStart("insert", roleDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(roleDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_role");
			sql.append("(");
			sql.append(" company_code");
			sql.append(",role_code");
			sql.append(",role_name");
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

			this.preparedStatement.setString( 1, roleDto.getCompanyCode());
			this.preparedStatement.setString( 2, roleDto.getRoleCode());
			this.preparedStatement.setString( 3, roleDto.getRoleName());
			this.preparedStatement.setString( 4, roleDto.getCreateUserId());
			this.preparedStatement.setString( 5, roleDto.getUpdateUserId());
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			throw new LaubeException("insert", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("insert", e);
			}
			log.traceEnd("insert");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("insert");
		return resultDto;
	}

	/**
	 * update the role master.<br>
	 * @param roleDto role master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto update(final RoleDto roleDto) throws LaubeException {

		log.traceStart("update", roleDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(roleDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("update");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_role");
			sql.append(" SET");
			sql.append(" role_name = ?");
			sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(",update_user_id = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and role_code = ?");
			sql.append(";");

			log.message("update", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString( 1, roleDto.getRoleName());
			this.preparedStatement.setString( 2, roleDto.getUpdateUserId());
			this.preparedStatement.setString( 3, roleDto.getCompanyCode());
			this.preparedStatement.setString( 4, roleDto.getRoleCode());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				log.message("update", "It failed to update the application form master.");
				log.message("update", "roleName:" + roleDto.getRoleName());
				log.message("update", "updateUserId:" + roleDto.getUpdateUserId());
				log.message("update", "companyCode:" + roleDto.getCompanyCode());
				log.message("update", "roleCode:" + roleDto.getRoleCode());
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}

		} catch (final SQLException e) {
			throw new LaubeException("update",e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("update",e);
			}
			log.traceEnd("update");
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("update");
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
	@SuppressWarnings("nls")
	@Override
	public final boolean isOccupied(final String companyCode, final String roleCode) throws LaubeException {

		log.traceStart("isOccupied", companyCode, roleCode);

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(roleCode))) {
			log.traceEnd("isOccupied");
			return false;
		}

		final RoleUserDao roleUserModel = new RoleUserDao();
		final ResultDto resultDto = roleUserModel.findByRoleCode(companyCode, roleCode);

		try {
			if (LaubeUtility.isEmpty(resultDto)){
				return false;
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
	 * search for the role a master at the company code.<br>
	 * @param companyCode company code
	 * @return role master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode) throws LaubeException {

		log.traceStart("find", companyCode);

		final ResultDto resultDto = new ResultDto();

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",role_code");
			sql.append(",role_name");
			sql.append(" FROM wkf_view_role");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" ORDER BY role_code");
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
				resultDto.setSuccess(true);
				resultDto.setMessageId("N0001");
				return resultDto;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new RoleDto());

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
	 * perform a full match search of role master.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return role master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto findByRoleCode(final String companyCode, final String roleCode) throws LaubeException {

		log.traceStart("findByRoleCode", companyCode, roleCode);

		final ResultDto resultDto = new ResultDto();

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",role_code");
			sql.append(",role_name");
			sql.append(" FROM wkf_view_role");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and role_code = ?");
			sql.append(" ORDER BY role_code");
			sql.append(";");

			log.message("findByRoleCode", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, roleCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("findByRoleCode", "the record was not found. Please investigate the cause by referring to the following.");
				log.message("findByRoleCode", "[SQL]");
				log.message("findByRoleCode", sql.toString());
				log.message("findByRoleCode", "");
				log.message("findByRoleCode", "[Extraction condition]");
				log.message("findByRoleCode", "[companyCode]: " + companyCode);
				log.message("findByRoleCode", "[roleCode]: " + roleCode);
				resultDto.setSuccess(true);
				resultDto.setMessageId("N0001");
				log.info("[workflowEngine] " + "find end");
				return resultDto;
			}

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new RoleDto());

			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			return resultDto;

		} catch (final SQLException e) {
			throw new LaubeException("findByRoleCode",e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("findByRoleCode",e);
			}
			log.traceEnd("findByRoleCode");
		}
	}

	/**
	 * do the fuzzy search of role master.<br>
	 * @param companyCode company code
	 * @param roleName role name
	 * @return role master list
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto findByRoleName(final String companyCode, final String roleName) throws LaubeException {

		log.traceStart("findByRoleName", companyCode, roleName);

		final ResultDto resultDto = new ResultDto();

		try {
			boolean isSettingYes = false;

			if (!LaubeUtility.isEmpty(roleName)){
				isSettingYes = true;
			}

			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",role_code");
			sql.append(",role_name");
			sql.append(" FROM wkf_view_role");
			sql.append(" WHERE");
			sql.append(" company_code = ?");

			if (isSettingYes){
				sql.append(" and role_name LIKE ?");
			}

			sql.append(" ORDER BY company_cde, role_code");
			sql.append(";");

			log.message("findByRoleName", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);

			if (isSettingYes){
				this.preparedStatement.setString(2, "%" + roleName + "%");
			}

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("findByRoleName", "the record was not found. Please investigate the cause by referring to the following.");
				log.message("findByRoleName", "[SQL]");
				log.message("findByRoleName", sql.toString());
				log.message("findByRoleName", "");
				log.message("findByRoleName", "[Extraction condition]");
				log.message("findByRoleName", "[companyCode]: " + companyCode);
				log.message("findByRoleName", "[roleName]: " + roleName);
				resultDto.setSuccess(true);
				resultDto.setMessageId("N0001");
				return resultDto;
			}

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new RoleDto());

			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			return resultDto;

		} catch (final SQLException e) {
			throw new LaubeException("findByRoleName", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("findByRoleName", e);
			}
			log.traceEnd("findByRoleName");
		}
	}
}
