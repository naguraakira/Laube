package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import site.laube.dto.BossDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.BossModelInterface;
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

public final class BossModel extends LaubeModel implements BossModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(BossModel.class);

	/**
	 * delete the boss master<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @return ResultDto
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode) throws LaubeException {

		log.traceStart("delete", companyCode);

		final ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			// deletion processing (company unit)
			final  StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_boss");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(";");

			log.message("delete","[SQL]" + sql.toString());
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
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("delete");
		return resultDto;
	}

	/**
	 * delete the boss master(user unit)<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @param userCode user code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto delete(final String companyCode, final String userCode) throws LaubeException {

		log.traceStart("delete", companyCode, userCode);

		final ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(userCode))){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("delete");
			return resultDto;
		}

		try {
			final  StringBuffer sql = new StringBuffer();
			sql.append("DELETE");
			sql.append(" FROM wkf_boss");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and user_code = ? ");
			sql.append(";");

			log.message("delete", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, userCode);
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
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("delete");
		return resultDto;
    }

	/**
	 * register the boss master.<br>
	 * @param bossDto boss dto
	 * @return ResultDto
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto insert(final BossDto bossDto) throws LaubeException {

		log.traceStart("insert", bossDto);

		final  ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(bossDto)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("insert");
			return resultDto;
		}

		try {
			final  StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_boss");
			sql.append("(");
			sql.append(" company_code");
			sql.append(",unit_code");
			sql.append(",user_code");
			sql.append(",application_form_code");
			sql.append(",boss_company_code");
			sql.append(",boss_unit_code");
			sql.append(",boss_user_code");
			sql.append(",create_date_time");
			sql.append(",create_user_id");
			sql.append(",update_date_time");
			sql.append(",update_user_id");
			sql.append(")");
			sql.append(" VALUES(");
			sql.append(" ?");
			sql.append(",?");
			sql.append(",?");
			sql.append(",?");
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

			this.preparedStatement.setString( 1, bossDto.getCompanyCode());
			this.preparedStatement.setString( 2, bossDto.getUnitCode());
			this.preparedStatement.setString( 3, bossDto.getUserCode());
			this.preparedStatement.setString( 4, bossDto.getApplicationFormCode());
			this.preparedStatement.setString( 5, bossDto.getBossCompanyCode());
			this.preparedStatement.setString( 6, bossDto.getBossUnitCode());
			this.preparedStatement.setString( 7, bossDto.getBossUserCode());
			this.preparedStatement.setString( 8, bossDto.getCreateUserId());
			this.preparedStatement.setString( 9, bossDto.getUpdateUserId());
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
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("insert");
		return resultDto;
	}

	/**
	 * To update the boss master.<br>
	 * @param bosseDto boss master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto update(final BossDto bossDto) throws LaubeException {

		log.traceStart("update", bossDto);

		final  ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(bossDto)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("update");
			return resultDto;
		}

		try {
			final  StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_boss");
			sql.append(" SET");
			sql.append(" boss_company_code = ?");
			sql.append(",boss_unit_code = ?");
			sql.append(",boss_user_code = ?");
			sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
			sql.append(",update_userID = ?");
			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and unit_code = ?");
			sql.append(" and user_code = ?");
			sql.append(" and application_form_code = ?");
			sql.append(";");

			log.message("update", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString( 1, bossDto.getBossCompanyCode());
			this.preparedStatement.setString( 2, bossDto.getBossUnitCode());
			this.preparedStatement.setString( 3, bossDto.getBossUserCode());
			this.preparedStatement.setString( 4, bossDto.getUpdateUserId());
			this.preparedStatement.setString( 5, bossDto.getCompanyCode());
			this.preparedStatement.setString( 6, bossDto.getUnitCode());
			this.preparedStatement.setString( 7, bossDto.getUserCode());
			this.preparedStatement.setString( 8, bossDto.getApplicationFormCode());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				log.message("update", "It failed to update the boss master.");
				log.message("update", "bossCompanyCode:" + bossDto.getBossCompanyCode());
				log.message("update", "bossUnitCode:" + bossDto.getBossUnitCode());
				log.message("update", "bossUserCode:" + bossDto.getBossUserCode());
				log.message("update", "updateUserId:" + bossDto.getUpdateUserId());
				log.message("update", "companyCode:" + bossDto.getCompanyCode());
				log.message("update", "unitCode:" + bossDto.getUnitCode());
				log.message("update", "userCode:" + bossDto.getUserCode());
				log.message("update", "applicationFormCode:" + bossDto.getApplicationFormCode());
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}

		} catch (final SQLException e) {
			throw new LaubeException("update", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("update", e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.traceEnd("update");
		return resultDto;
	}

	/**
	 * return all of the boss set to the highest.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @param userCode user code
	 * @param applicationFormCode application form code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto findByChainOfResposibility(String companyCode, String unitCode, String userCode, String applicationFormCode) throws LaubeException {

		log.traceStart("findByChainOfResposibility", companyCode, unitCode, userCode, applicationFormCode);

		final  ResultDto resultDto = new ResultDto();
		final  ArrayList<BossDto> list = new ArrayList<BossDto>();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findByChainOfResposibility");
			return resultDto;
		}

		if (LaubeUtility.isBlank(unitCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findByChainOfResposibility");
			return resultDto;
		}

		if (LaubeUtility.isBlank(userCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("findByChainOfResposibility");
			return resultDto;
		}

		String wCompanyCode = companyCode;
		String wUnitCode = unitCode;
		String wUserCode = userCode;
		String wApplicationFormCode = applicationFormCode;

		try {
			boolean hasRecord = false;
			while(true) {
				final  ResultDto r = find(wCompanyCode, wUnitCode, wUserCode, wApplicationFormCode);

				if (LaubeUtility.isEmpty(r)){
					resultDto.setStatus(true);
					resultDto.setMessageId("N0001");
					resultDto.setResultData(null);
					log.traceEnd("findByChainOfResposibility");
					return resultDto;
				}

				if (!r.isSuccess()) {
					return r;
				}

				ArrayList<BossDto> bossDtos = null;
				if (r.getResultData() instanceof ArrayList){
					bossDtos = LaubeUtility.automaticCast(r.getResultData());
				}

				if (LaubeUtility.isEmpty(bossDtos)){
					resultDto.setStatus(true);
					resultDto.setMessageId("N0001");
					resultDto.setResultData(null);
					log.traceEnd("findByChainOfResposibility");
					return resultDto;

				}else{
					final  BossDto bossDto = bossDtos.get(0);
					if (LaubeUtility.isBlank(bossDto.getBossCompanyCode()) || LaubeUtility.isBlank(bossDto.getBossUnitCode()) || LaubeUtility.isBlank(bossDto.getBossUserCode())){

						if (hasRecord) {
							resultDto.setMessageId("N0001");
						}else{
							resultDto.setMessageId("N0002");
						}
						resultDto.setStatus(true);
						resultDto.setResultData(list);
						log.traceEnd("findByChainOfResposibility");
						return resultDto;
					}

					hasRecord = true;

					wCompanyCode = bossDto.getBossCompanyCode();
					wUnitCode    = bossDto.getBossUnitCode();
					wUserCode    = bossDto.getBossUserCode();
					wApplicationFormCode = null;

					log.message("findByChainOfResposibility", "check if the same boss has appeared twice, to release because an infinite loop");
					int index = list.size();
					for (int i = 0; i < index; i++){
						BossDto doubleCheck = (BossDto)list.get(i);
						if ((doubleCheck.getUnitCode().equals(unitCode))&&(doubleCheck.getUserCode().equals(userCode))){
							resultDto.setStatus(false);
							resultDto.setMessageId("E1004");
							return resultDto;
						}
					}
					list.add(bossDto);
				}
			}

		} catch (Exception e) {
			throw new LaubeException("findByChainOfResposibility", e);
		}
	}

	/**
	 * Search the boss master.<br>
	 * Boss of the settings can be set to the [department] [application] unit or [department] unit.<br>
	 * If both are set, and returns the setting of the [department] [application] unit.<br>
	 * If the application code is null, return the setting boss is set to [department] unit.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @param userCode user code
	 * @param applicationFormCode application form code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode, final String unitCode, final String userCode, final String applicationFormCode) throws LaubeException {

		log.traceStart("find", companyCode, unitCode, userCode, applicationFormCode);

		final ResultDto resultDto = new ResultDto();

		try {
			if (LaubeUtility.isEmpty(companyCode) || LaubeUtility.isEmpty(unitCode) || LaubeUtility.isEmpty(userCode)){
				throw new LaubeException("find", "company Code / department code / employee number is a required field. Be sure to set.");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" company_code");
			sql.append(",company_name");
			sql.append(",unit_code");
			sql.append(",unit_name");
			sql.append(",user_code");
			sql.append(",user_name");
			sql.append(",application_form_code");
			sql.append(",application_form_name");
			sql.append(",boss_company_code");
			sql.append(",boss_company_name");
			sql.append(",boss_unit_code");
			sql.append(",boss_unit_name");
			sql.append(",boss_user_code");
			sql.append(",boss_user_name");
			sql.append(" from wkf_view_boss as A");

			sql.append(" WHERE");
			sql.append(" company_code = ?");
			sql.append(" and unit_code = ?");
			sql.append(" and user_Code = ?");
			sql.append(" and application_form_code = ?");
			sql.append(" ORDER BY");
			sql.append(" unit_code ASC");
			sql.append(";");

			log.message("find", "[SQL] " + sql.toString());
			closePreparedStatement();
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, unitCode);
			this.preparedStatement.setString(3, userCode);
			this.preparedStatement.setString(4, applicationFormCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				sql = new StringBuffer();
				sql.append("SELECT");
				sql.append(" company_code");
				sql.append(",company_name");
				sql.append(",unit_code");
				sql.append(",unit_name");
				sql.append(",user_code");
				sql.append(",user_name");
				sql.append(",application_form_code");
				sql.append(",application_form_name");
				sql.append(",boss_company_code");
				sql.append(",boss_company_name");
				sql.append(",boss_unit_code");
				sql.append(",boss_unit_name");
				sql.append(",boss_user_code");
				sql.append(",boss_user_name");
				sql.append(" from wkf_view_boss as A");
				sql.append(" WHERE");
				sql.append(" company_code = ?");
				sql.append(" and unit_code = ?");
				sql.append(" and user_Code = ?");
				sql.append(" and application_form_code IS NULL");
				sql.append(" ORDER BY");
				sql.append(" unit_code ASC");
				sql.append(";");

				log.message("find","[SQL] " + sql.toString());
				closePreparedStatement();
				this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
				this.preparedStatement.setString(1, companyCode);
				this.preparedStatement.setString(2, unitCode);
				this.preparedStatement.setString(3, userCode);
				this.resultSet = this.preparedStatement.executeQuery();

				if (!this.resultSet.first()) {
					log.message("find","The record was not found. Please investigate the cause by referring to the following.");
					log.message("find","[SQL]");
					log.message("find",sql.toString());
					log.message("find","");
					log.message("find","[Extraction condition]");
					log.message("find","[companyCode]: " + companyCode);
					log.message("find","[unitCode]: " + unitCode);
					log.message("find","[userCode]: " + userCode);
					log.message("find","[applicationFormCode]: " + applicationFormCode);
					resultDto.setStatus(false);
					resultDto.setMessageId("E1003");
					return resultDto;
				}
			}

			final ArrayList<LaubeDto> resultData = conversion(this.resultSet, new BossDto());
			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);

		} catch (final SQLException e) {
			throw new LaubeException("find", e);

		} finally {
			try {
				closePreparedStatement();
			} catch (final Exception e) {
				throw new LaubeException("find", e);
			}
		}
		log.traceEnd("find");
		return resultDto;
	}
}