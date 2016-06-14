package site.laube.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.ActivityDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.dto.RouteDto;
import site.laube.exception.LaubeException;
import site.laube.modelinterface.RouteModelInterface;
import site.laube.utility.LaubeUtility;

/**
 * Model class is a class for manipulating the database.<br>
 * the class that manages the special route master Model.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public final class SpecialRouteModel extends RouteModel implements RouteModelInterface {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(SpecialRouteModel.class);

	/**
	 * removal of the special route master (company units)<br>
	 * remove all of the special route master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto delete(final String companyCode) throws LaubeException {
		throw new LaubeException("special route master the same for the applicant classification master, please if you want to delete is running the deletion of the application classification master model.");
	}

	/**
	 * removal of the special route master (route units)<br>
	 * remove all of the route master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto delete(final String companyCode, final String routeCode) throws LaubeException {
		throw new LaubeException("special route master the same for the applicant classification master, please if you want to delete is running the deletion of the application classification master model.");
	}

	/**
	 * register the special route master.<br>
	 * @param routeDto route master
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto insert(final RouteDto routeDto) throws LaubeException {
		throw new LaubeException("special route master the same for the applicant classification master, please if you want to delete is running the deletion of the application classification master model.");
	}

	/**
	 * update the special route master.<br>
	 * @param routeDto route master
	 * @return result
	 * @throws LaubeException
	 */
	@Override
	public final ResultDto update(final RouteDto routeDto) throws LaubeException {
		throw new LaubeException("special route master the same for the applicant classification master, please if you want to delete is running the deletion of the application classification master model.");
	}

	/**
	 * spent check of the special route master<br>
	 * if the route is already being used in the application form by the root master, and then return true.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @exception LaubeException
	 */
	@Override
	public final boolean isOccupied(final String companyCode, final String routeCode) throws LaubeException {

		log.debug("[workflowEngine] " + "isOccupied Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);
		log.debug("[workflowEngine] " + "[routeCode]: " + routeCode);

		try {
			ApplicationClassificationModel applicationClassificationModel = new ApplicationClassificationModel();
			ResultDto  resultDto = applicationClassificationModel.findBySpecialRouteCode(companyCode, routeCode);

			if (resultDto == null){
				log.error("[workflowEngine] " + "the record was not found. Please investigate the cause by referring to the following.");
				log.error("[workflowEngine] " + "isOccupied End");
				throw new LaubeException("the record was not found. Please investigate the cause by referring to the following.");
			}else{
				if (resultDto.getResultData() == null) {
					log.error("[workflowEngine] " + "isOccupied End");
					return false;
				}else{
					log.error("[workflowEngine] " + "isOccupied End");
					return true;
				}
			}

		} catch (Exception e) {
			log.error("[workflowEngine] " + "[Exception] " + e);
			log.error("[workflowEngine] " + "isOccupied End");
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
				log.error("[workflowEngine] " + "isOccupied End");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * search the special route master.<br>
	 * @param companyCode company code
	 * @return route master
	 * @exception LaubeException
	 */
	@Override
	public final ResultDto find(final String companyCode) throws LaubeException {

		log.debug("[workflowEngine] " + "find Start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "[companyCode]: " + companyCode);

		ResultDto resultDto = new ResultDto();

		if ((LaubeUtility.isBlank(companyCode))) {
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			return resultDto;
		}

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("route_code, ");
			sql.append("route_name ");
			sql.append("FROM ");
			sql.append("wkf_view_special_route");
			sql.append(" ");
			sql.append("WHERE ");
			sql.append("company_code\" = ? ");
			sql.append("ORDER BY route_code");
			sql.append(";");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
			this.preparedStatement.setString(1, companyCode);
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

			ArrayList<LaubeDto> resultData = conversion(this.resultSet, new RouteDto());

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
	 * search the special route master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return route master
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
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("company_code, ");
			sql.append("company_name, ");
			sql.append("route_code, ");
			sql.append("route_name ");
			sql.append("FROM ");
			sql.append("wkf_view_special_route");
			sql.append(" ");
			sql.append("WHERE ");
			sql.append("company_code = ? AND ");
			sql.append("route_code = ? ");
			sql.append("ORDER BY \"route_code\"");
			sql.append(";");

			log.debug("[workflowEngine] SQL:" + sql.toString());
			this.preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_UPDATABLE);
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
				log.error("[workflowEngine] " + "[special_route_code]: " + routeCode);
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
