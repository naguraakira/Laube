package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.ApplicationClassificationDto;
import site.laube.dto.ApplicationFormRouteDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.ApplicationClassificationModelInterface;
import site.laube.utility.LaubeUtility;

/**
 * Model class is a class for manipulating the database.<br>
 * The class that manages the application classification master Model.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public final class ApplicationClassificationModel extends LaubeModel implements ApplicationClassificationModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApplicationClassificationModel.class);

	/**
     * delete the application classification master (company unit)<br>
     * remove all of the application classification master and the applicant by category activity master.
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

		if (LaubeUtility.isBlank(companyCode)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_application_classification ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
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
     * delete the application classification master (application code unit)<br>
     * remove the application classification by activity master to match the application classification master.
     * @param companyCode company code
     * @param applicationClassificationCode Application classification code
     * @return result
     * @throws LaubeException
     */
	@Override
    public final ResultDto delete(final String companyCode, final String applicationClassificationCode) throws LaubeException {

		log.debug("[workflowEngine] " + "delete Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[applicationClassificationCode]: " + applicationClassificationCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationClassificationCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("wkf_application_classification ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("application_classification_code = ? ");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, applicationClassificationCode);
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
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "delete End");
		return resultDto;
    }

	/**
	 * register the application classification master.<br>
	 * @param applicationClassificationDto Application classification master
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto insert(final ApplicationClassificationDto applicationClassificationDto) throws LaubeException {

		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[applicationClassificationDto]: " + applicationClassificationDto);

		ResultDto resultDto = new ResultDto();

		if (applicationClassificationDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.debug("[workflowEngine] " + "insert End");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO wkf_application_classification ");
			sql.append("(");
			sql.append("company_code,");
			sql.append("application_classification_code,");
			sql.append("application_classification_name,");
			sql.append("sort_order,");
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

			this.preparedStatement.setString(  1, applicationClassificationDto.getCompanyCode());
			this.preparedStatement.setString(  2, applicationClassificationDto.getApplicationClassificationCode());
			this.preparedStatement.setString(  3, applicationClassificationDto.getApplicationClassificationName());
			this.preparedStatement.setInt   (  4, applicationClassificationDto.getSortOrder());
			this.preparedStatement.setString(  5, applicationClassificationDto.getCreateUserId());
			this.preparedStatement.setString(  6, applicationClassificationDto.getUpdateUserId());
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
	 * to update the application classification master.<br>
	 * @param applicationClassificationDto Application classification master
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto update(final ApplicationClassificationDto applicationClassificationDto) throws LaubeException {

		log.debug("[workflowEngine] " + "update Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[applicationClassificationDto]: " + applicationClassificationDto);

		ResultDto resultDto = new ResultDto();

		if (applicationClassificationDto == null) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.debug("[workflowEngine] " + "insert End");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE wkf_application_classification ");
			sql.append("SET ");
			sql.append("application_classification_name = ?, ");
			sql.append("sort_order = ?, ");
			sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
			sql.append("update_user_id = ? ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("application_classification_code = ?;");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString());

			this.preparedStatement.setString(  1, applicationClassificationDto.getApplicationClassificationName());
			this.preparedStatement.setInt   (  2, applicationClassificationDto.getSortOrder());
			this.preparedStatement.setString(  3, applicationClassificationDto.getUpdateUserId());
			this.preparedStatement.setString(  4, applicationClassificationDto.getCompanyCode());
			this.preparedStatement.setString(  5, applicationClassificationDto.getApplicationClassificationCode());
			int upCnt = this.preparedStatement.executeUpdate();

			if (upCnt != 1) {
				log.error("[workflowEngine] It failed to update the application classification master.");
				log.error("[workflowEngine] applicationClassificationName:" + applicationClassificationDto.getApplicationClassificationName());
				log.error("[workflowEngine] sortOrder:" + applicationClassificationDto.getSortOrder());
				log.error("[workflowEngine] updateUserId:" + applicationClassificationDto.getUpdateUserId());
				log.error("[workflowEngine] companyCode:" + applicationClassificationDto.getCompanyCode());
				log.error("[workflowEngine] gapplicationClassificationCode:" + applicationClassificationDto.getApplicationClassificationCode());
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "update Start");
				return resultDto;
			}

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "update Start");
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
				log.error("[workflowEngine] " + "update Start");
				throw new LaubeException(e);
			}
		}
		resultDto.setStatus(true);
		resultDto.setMessageId("N0001");
		log.debug("[workflowEngine] " + "update Start");
		return resultDto;
	}

	/**
	 * search the application form master.<br>
	 * @param companyCode company code
	 * @return Application form master
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto find(final String companyCode) throws LaubeException {

		log.debug("[workflowEngine] " + "find Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isBlank(companyCode)) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("application_classification_code, ");
			sql.append("application_classification_name ");
			sql.append("FROM wkf_view_application_classification ");
			sql.append("WHERE ");
			sql.append("company_code = ?;");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "The record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "find End");
				return resultDto;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationClassificationDto());

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
	 * search the application form master.<br>
	 * @param companyCode company code
	 * @param applicationClassificationCode Application classification code
	 * @return Application form master
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	@Override
	public final ResultDto findByApplicationClassificationCode(final String companyCode, final String applicationClassificationCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findByApplicationClassificationCode Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[applicationClassificationCode]: " + applicationClassificationCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(applicationClassificationCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("application_classification_code, ");
			sql.append("application_classification_name ");
			sql.append("FROM wkf_view_application_classification ");
			sql.append("WHERE ");
			sql.append("company_code = ?");
			sql.append(" AND application_classification_code = ?;");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, applicationClassificationCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "The record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[applicationClassificationCode]: " + applicationClassificationCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "findByApplicationClassificationCode End");
				return resultDto;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationClassificationDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.debug("[workflowEngine] " + "findByApplicationClassificationCode End");
			return resultDto;

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "findByApplicationClassificationCode End");
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
				log.error("[workflowEngine] " + "findByApplicationClassificationCode End");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param specialRouteCode special route code
	 * @return Application-specific root master entity
	 * @exception LaubeException
	 */
	public final ResultDto findBySpecialRouteCode(final String companyCode, final String specialRouteCode) throws LaubeException {

		log.debug("[workflowEngine] " + "findBySpecialRouteCode Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[specialRouteCode]: " + specialRouteCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))||(LaubeUtility.isBlank(specialRouteCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "findBySpecialRouteCode End");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("application_classification_code, ");
			sql.append("application_classification_name, ");
			sql.append("unit_code, ");
			sql.append("unit_name, ");
			sql.append("individual_route_code, ");
			sql.append("individual_route_name, ");
			sql.append("common_route_code, ");
			sql.append("common_route_name ");
			sql.append("FROM wkf_view_application_classification_Activity ");
			sql.append("WHERE ");
			sql.append("company_code = ? ");
			sql.append("and common_route_code = ?;");

			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
			this.preparedStatement.setString(2, specialRouteCode);

			this.resultSet = this.preparedStatement.executeQuery();

			if (!this.resultSet.first()) {
				log.error("[workflowEngine] " + "The record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "[SQL]");
				log.error("[workflowEngine] " + sql.toString());
				log.error("[workflowEngine] " + "");
				log.error("[workflowEngine] " + "[Extraction condition]");
				log.error("[workflowEngine] " + "[companyCode]: " + companyCode);
				log.error("[workflowEngine] " + "[specialRouteCode]: " + specialRouteCode);
				resultDto.setStatus(false);
				resultDto.setMessageId("E1003");
				log.error("[workflowEngine] " + "findBySpecialRouteCode End");
				return null;
			}

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new ApplicationFormRouteDto());

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(resultData);
			log.debug("[workflowEngine] " + "findBySpecialRouteCode End");
			return resultDto;

		} catch (SQLException e) {
			log.error("[workflowEngine] " + "[SQLException] " + e);
			log.error("[workflowEngine] " + "findBySpecialRouteCode End");
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
				log.error("[workflowEngine] " + "findBySpecialRouteCode End");
				throw new LaubeException(e);
			}
		}
	}
}
