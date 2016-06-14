package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.BossDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.BossModelInterface;
import site.laube.utility.LaubeUtility;

/**
 * Model class is a class for manipulating the database.<br>
 * The class that manages the boss master Model.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public final class BossModel extends LaubeModel implements BossModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(BossModel.class);

	/**
	 * delete the boss master<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto delete(final String companyCode) throws LaubeException {

		log.debug("[workflowEngine] " + "delete Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			// deletion processing (company unit)
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_boss ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
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
		log.debug("[workflowEngine] " + "delete End" + "[return value]:true");
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		return resultDto;
	}

	/**
	 * delete the boss master(user unit)<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @param userCode user code
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto delete(final String companyCode, final String userCode) throws LaubeException {

		log.debug("[workflowEngine] " + "delete Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[userCode]: " + userCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(userCode))){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_boss ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("user_code = ? ");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, userCode);
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
		log.debug("[workflowEngine] " + "delete End" + "[return value]:true");
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		return resultDto;
    }

	/**
	 * register the boss master.<br>
	 * @param applicationFormDto Application form master
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto insert(final BossDto bossDto) throws LaubeException {

		log.debug("[workflowEngine] " + "insert Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[bossDto]: " + bossDto);

		ResultDto resultDto = new ResultDto();

		if (bossDto == null) {
			log.error("[workflowEngine] " + "insert End" + "[return value]:false");
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_boss ");
			sql.append("(");
			sql.append("company_code,");
			sql.append("unit_code,");
			sql.append("user_code,");
			sql.append("application_form_code,");
			sql.append("boss_company_code,");
			sql.append("boss_unit_code,");
			sql.append("boss_user_code,");
			sql.append("create_date_time,");
			sql.append("create_user_id,");
			sql.append("update_date_time,");
			sql.append("update_user_id) ");
			sql.append("VALUES(");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?,");
			sql.append("CURRENT_TIMESTAMP(0),");
			sql.append("?);");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
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
	 * To update the boss master.<br>
	 * @param bosseDto boss master
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto update(final BossDto bosseDto) throws LaubeException {

		log.debug("[workflowEngine] " + "update Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[bosseDto]: " + bosseDto);

		ResultDto resultDto = new ResultDto();

		if (bosseDto == null) {
			log.error("[workflowEngine] " + "update End" + "[return value]:false");
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_boss ");
			sql.append("SET ");
			sql.append("boss_company_code = ?, ");
			sql.append("boss_unit_code = ?, ");
			sql.append("boss_user_code = ?, ");
			sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
			sql.append("update_userID = ? ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("unit_code = ? AND ");
			sql.append("user_code = ? AND ");
			sql.append("application_form_code = ?;");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString( 1, bosseDto.getBossCompanyCode());
			this.preparedStatement.setString( 2, bosseDto.getBossUnitCode());
			this.preparedStatement.setString( 3, bosseDto.getBossUserCode());
			this.preparedStatement.setString( 4, bosseDto.getUpdateUserId());
			this.preparedStatement.setString( 5, bosseDto.getCompanyCode());
			this.preparedStatement.setString( 6, bosseDto.getUnitCode());
			this.preparedStatement.setString( 7, bosseDto.getUserCode());
			this.preparedStatement.setString( 8, bosseDto.getApplicationFormCode());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				log.error("[workflowEngine] It failed to update the boss master.");
				log.error("[workflowEngine] bossCompanyCode:" + bosseDto.getBossCompanyCode());
				log.error("[workflowEngine] bossUnitCode:" + bosseDto.getBossUnitCode());
				log.error("[workflowEngine] bossUserCode:" + bosseDto.getBossUserCode());
				log.error("[workflowEngine] updateUserId:" + bosseDto.getUpdateUserId());
				log.error("[workflowEngine] companyCode:" + bosseDto.getCompanyCode());
				log.error("[workflowEngine] unitCode:" + bosseDto.getUnitCode());
				log.error("[workflowEngine] userCode:" + bosseDto.getUserCode());
				log.error("[workflowEngine] applicationFormCode:" + bosseDto.getApplicationFormCode());
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				return resultDto;
			}

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
		log.debug("[workflowEngine] " + "update End" + "[return value]:true");
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		return resultDto;
	}

	/**
	 * return all of the boss set to the highest.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @param userCode user code
	 * @param applicationFormCode application form code
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto findByChainOfResposibility(String companyCode, String unitCode, String userCode, String applicationFormCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findByChainOfResposibility Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[unitCode]: " + unitCode);
		log.debug("[workflowEngine] " + "[userCode]: " + userCode);
		log.debug("[workflowEngine] " + "[applicationFormCode]: " + applicationFormCode);

		ResultDto resultDto = new ResultDto();
		ArrayList<BossDto> list = new ArrayList<BossDto>();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(unitCode))||(LaubeUtility.isBlank(userCode))) {
			log.error("[workflowEngine] " + "update End" + "[return value]:false");
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			boolean hasRecord = false;
			while(true) {
				ResultDto r = find(companyCode, unitCode, userCode, applicationFormCode);

				if (r == null){
					resultDto.setStatus(true);
					resultDto.setMessageId("N0001");
					resultDto.setResultData(null);
					log.debug("[workflowEngine]" + "findByChainOfResposibility End");
					return resultDto;
				}

				if (!r.isSuccess()) {
					return r;
				}

				ArrayList<BossDto> bossDtos = (ArrayList<BossDto>)(r.getResultData());

				if (bossDtos == null){
					resultDto.setStatus(true);
					resultDto.setMessageId("N0001");
					resultDto.setResultData(null);
					log.debug("[workflowEngine]" + "bossDto os null.");
					log.debug("[workflowEngine]" + "findByChainOfResposibility End");
					return resultDto;

				}else{
					BossDto bossDto = bossDtos.get(0);
					if (LaubeUtility.isBlank(bossDto.getBossCompanyCode()) || LaubeUtility.isBlank(bossDto.getBossUnitCode()) || LaubeUtility.isBlank(bossDto.getBossUserCode())){

						if (hasRecord) {
							resultDto.setMessageId("N0001");
						}else{
							resultDto.setMessageId("N0002");
						}
						resultDto.setStatus(true);
						resultDto.setResultData(list);
						log.debug("[workflowEngine]" + "top-level employee." + list.toString());
						log.debug("[workflowEngine]" + "findByChainOfResposibility End");
						return resultDto;
					}

					hasRecord = true;

					companyCode = bossDto.getBossCompanyCode();
					unitCode    = bossDto.getBossUnitCode();
					userCode    = bossDto.getBossUserCode();
					applicationFormCode = null;

					log.debug("[workflowEngine]" + "check if the same boss has appeared twice, to release because an infinite loop");
					int index = list.size();
					for (int i = 0; i < index; i++){
						BossDto doubleCheck = (BossDto)list.get(i);
						if ((doubleCheck.getUnitCode().equals(unitCode))&&(doubleCheck.getUserCode().equals(userCode))){
							log.debug("[workflowEngine]" + "unitCode:" + doubleCheck.getBossUnitCode());
							log.debug("[workflowEngine]" + "userCode:" + doubleCheck.getBossUserCode());
							log.debug("[workflowEngine]" + "because the same boss has appeared twice, to exit from the loop.");
							resultDto.setStatus(false);
							resultDto.setMessageId("E1004");
							log.debug("[workflowEngine]" + "findByChainOfResposibility End");
							return resultDto;
						}
					}
					log.debug("[workflowEngine]" + "[add]" + bossDto);
					list.add(bossDto);
				}
			}

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
				log.error("[workflowEngine]" + "findByChainOfResposibility End");
				throw new LaubeException(e);
			}
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
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode, final String unitCode, final String userCode, final String applicationFormCode) throws LaubeException {

		log.debug("[workflowEngine] " + "find Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[unitCode]: " + unitCode);
		log.debug("[workflowEngine] " + "[userCode]: " + userCode);
		log.debug("[workflowEngine] " + "[applicationFormCode]: " + applicationFormCode);

		ResultDto resultDto = new ResultDto();

		try {
			if (LaubeUtility.isEmpty(companyCode) || LaubeUtility.isEmpty(unitCode) || LaubeUtility.isEmpty(userCode)){
				log.error("[workflowEngine] " + "find End");
				throw new LaubeException("company Code / department code / employee number is a required field. Be sure to set.");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name,");
			sql.append("unit_code, ");
			sql.append("unit_name,");
			sql.append("user_code,");
			sql.append("user_name,");
			sql.append("application_form_code,");
			sql.append("application_form_name, ");
			sql.append("boss_company_code,");
			sql.append("boss_company_name,");
			sql.append("boss_unit_code,");
			sql.append("boss_unit_name,");
			sql.append("boss_user_code,");
			sql.append("boss_user_name ");
			sql.append("from wkf_view_boss as A ");

			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("unit_code = ? AND ");
			sql.append("user_Code = ? AND ");
			sql.append("application_form_code = ? ");
			sql.append("ORDER BY ");
			sql.append("unit_code ASC;");

			log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, unitCode);
			this.preparedStatement.setString(3, userCode);
			this.preparedStatement.setString(4, applicationFormCode);
			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				sql = new StringBuffer();
				sql.append("SELECT ");
				sql.append("company_code, ");
				sql.append("company_name,");
				sql.append("unit_code, ");
				sql.append("unit_name,");
				sql.append("user_code,");
				sql.append("user_name,");
				sql.append("application_form_code,");
				sql.append("application_form_name, ");
				sql.append("boss_company_code,");
				sql.append("boss_company_name,");
				sql.append("boss_unit_code,");
				sql.append("boss_unit_name,");
				sql.append("boss_user_code,");
				sql.append("boss_user_name ");
				sql.append("from wkf_view_boss as A ");

				sql.append("WHERE ");
				sql.append("company_code = ? AND ");
				sql.append("unit_code = ? AND ");
				sql.append("user_Code = ? AND ");
				sql.append("application_form_code IS NULL ");
				sql.append("ORDER BY ");
				sql.append("unit_code ASC;");

				log.debug("[workflowEngine] " + "[SQL]" + sql.toString());
				this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
				this.preparedStatement.setString(1, companyCode);
				this.preparedStatement.setString(2, unitCode);
				this.preparedStatement.setString(3, userCode);
				this.resultSet = this.preparedStatement.executeQuery();

				if (!this.resultSet.first()) {
					log.error("[workflowEngine] " + "The record was not found. Please investigate the cause by referring to the following.");
					log.error("[workflowEngine] " + "[SQL]");
					log.error("[workflowEngine] " + sql.toString());
					log.error("[workflowEngine] " + "");
					log.error("[workflowEngine] " + "[Extraction condition]");
					log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
					log.error("[workflowEngine] " + "[unitCode]: " + unitCode);
					log.error("[workflowEngine] " + "[userCode]: " + userCode);
					log.error("[workflowEngine] " + "[applicationFormCode]: " + applicationFormCode);
					resultDto.setStatus(false);
					resultDto.setMessageId("E1003");
					log.error("[workflowEngine] " + "find End");
					return resultDto;
				}
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new BossDto());
			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);

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
		log.debug("[workflowEngine] " + "find End");
		return resultDto;
	}
}