package site.laube.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.val;
import site.laube.daointerface.RoleUserDaoInterface;
import site.laube.dto.ResultDto;
import site.laube.dto.RoleUserDto;
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

public final class RoleUserDao extends LaubeDao implements RoleUserDaoInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(RoleUserDao.class);

	/**
	 * deleting a role by the employee master (role / employee number units)<br>
	 * remove the role by the employee master.<br>
	 * @param companyCode company code
	 * @param roleCode    role code
	 * @param userCode    user code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
    public final ResultDto delete(final String companyCode, final String roleCode, final String userCode) throws LaubeException {

		log.traceStart("delete", companyCode, roleCode, userCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(roleCode))||(LaubeUtility.isBlank(userCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_role_user");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and role_code = ?");
			sql.append(" and user_code = ?");
			sql.append(";");

			log.message("delete", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, roleCode);
			this.preparedStatement.setString(3, userCode);
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
	 * register the role by the employee master.<br>
	 * @param roleUserDto role user dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto insert(final RoleUserDto roleUserDto) throws LaubeException {

		log.traceStart("insert", roleUserDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(roleUserDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_role_user");
			sql.append("(");
			sql.append(" company_code");
			sql.append(",role_code");
			sql.append(",unit_code");
			sql.append(",user_code");
			sql.append(",create_date_time");
			sql.append(",create_user_id");
			sql.append(",update_date_time");
			sql.append(",update_user_id");
			sql.append(")");
			sql.append(" VALUES");
			sql.append("(");
			sql.append(" ?");
			sql.append(",?");
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

			this.preparedStatement.setString( 1, roleUserDto.getCompanyCode());
			this.preparedStatement.setString( 2, roleUserDto.getRoleCode());
			this.preparedStatement.setString( 3, roleUserDto.getUnitCode());
			this.preparedStatement.setString( 4, roleUserDto.getUserCode());
			this.preparedStatement.setString( 5, roleUserDto.getCreateUserId());
			this.preparedStatement.setString( 6, roleUserDto.getUpdateUserId());
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			throw new LaubeException("insert", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("insert", e);
			}
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("insert");
		return resultDto;
	}

	/**
	 * update the role by the employee master.<br>
	 * @param roleUserDto role user dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto update(final RoleUserDto roleUserDto) throws LaubeException {

		log.traceStart("update", roleUserDto);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(roleUserDto)){
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("update");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_role_user");
			sql.append(" SET");
			sql.append(" unit_code = ?");
			sql.append(",user_code = ?");
			sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(",update_user_id = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and role_code = ?");
			sql.append(";");

			log.message("update", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString( 1, roleUserDto.getUnitCode());
			this.preparedStatement.setString( 2, roleUserDto.getUserCode());
			this.preparedStatement.setString( 3, roleUserDto.getUpdateUserId());
			this.preparedStatement.setString( 4, roleUserDto.getCompanyCode());
			this.preparedStatement.setString( 5, roleUserDto.getRoleCode());
			this.preparedStatement.executeUpdate();

		} catch (final SQLException e) {
			throw new LaubeException("update", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("update", e);
			}
		}
		resultDto.setSuccess(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("update");
		return resultDto;
	}

	/**
	 * search only single item to the appropriate record.<br>
	 * @param companyCode company code
	 * @param roleCode    role code
	 * @param userCode    user code
	 * @return role user master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode, final String roleCode, final String userCode) throws LaubeException {

		log.traceStart("find", companyCode, roleCode, userCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(roleCode))||(LaubeUtility.isBlank(userCode))) {
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
			sql.append(",role_code");
			sql.append(",role_name");
			sql.append(",unit_code");
			sql.append(",unit_name");
			sql.append(",user_code");
			sql.append(",user_name");
			sql.append(" FROM wkf_view_role_user");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and role_code = ?");
			sql.append(" and user_code = ?");
			sql.append(";");

			log.message("find", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, roleCode);
			this.preparedStatement.setString(3, userCode);
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
				return null;
			}

			final val resultData = conversion(this.resultSet, new RoleUserDto());

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
	 * employees will find the role that belongs.<br>
	 * @param companyCode company code
	 * @param userCode    user code
	 * @return role user master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto findByUserCode(final String companyCode, final String userCode) throws LaubeException {

		log.traceStart("findByUserCode", companyCode, userCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(userCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findByUserCode");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",role_code");
			sql.append(",role_name");
			sql.append(",unit_code");
			sql.append(",unit_name");
			sql.append(",user_code");
			sql.append(",user_name");
			sql.append(" FROM wkf_view_user_role");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and user_code = ?");
			sql.append(" ORDER BY role_code");
			sql.append(";");

			log.message("findByUserCode", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, userCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("findByUserCode", "The record was not found. Please investigate the cause by referring to the following.");
				log.message("findByUserCode", "[SQL]");
				log.message("findByUserCode",  sql.toString());
				log.message("findByUserCode", "");
				log.message("findByUserCode", "[Extraction condition]");
				log.message("findByUserCode", "[companyCode]: " + companyCode);
				log.message("findByUserCode", "[userCode]: " + userCode);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				log.info("[workflowEngine] " + "findByUserCode end");
				return null;
			}

			final val resultData = conversion(this.resultSet, new RoleUserDto());

			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			return resultDto;

		} catch (final SQLException e) {
			throw new LaubeException("findByUserCode", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("findByUserCode", e);
			}
			log.traceEnd("findByUserCode");
		}
	}

	/**
	 * search for all employees that belong to the role.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return role user master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto findByRoleCode(final String companyCode, final String roleCode) throws LaubeException {

		log.traceStart("findByRoleCode", companyCode, roleCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(roleCode))) {
			resultDto.setSuccess(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findByRoleCode");
			return resultDto;
		}

		try {
			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",role_code");
			sql.append(",role_name");
			sql.append(",unit_code");
			sql.append(",unit_name");
			sql.append(",user_code");
			sql.append(",user_name");
			sql.append(" FROM wkf_view_role_user");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and role_code = ?");
			sql.append(" ORDER BY user_code");
			sql.append(";");

			log.message("findByRoleCode","[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, roleCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.message("findByRoleCode","The record was not found. Please investigate the cause by referring to the following.");
				log.message("findByRoleCode","[SQL]");
				log.message("findByRoleCode",sql.toString());
				log.message("findByRoleCode","");
				log.message("findByRoleCode","[Extraction condition]");
				log.message("findByRoleCode","[companyCode]: " + companyCode);
				log.message("findByRoleCode","[roleCode]: " + roleCode);
				resultDto.setSuccess(false);
				resultDto.setMessageId("E1003");
				return null;
			}

			final val resultData = conversion(this.resultSet, new RoleUserDto());

			resultDto.setSuccess(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			return resultDto;

		} catch (final SQLException e) {
			throw new LaubeException("findByRoleCode", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("findByRoleCode", e);
			}
			log.traceEnd("findByRoleCode");
		}
	}
}
