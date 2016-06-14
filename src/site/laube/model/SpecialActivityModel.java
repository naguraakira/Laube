package site.laube.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.exception.LaubeException;

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

public final class SpecialActivityModel extends ActivityModel {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(SpecialActivityModel.class);

	/**
	 * delete query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected String deleteQuery(){

		log.debug("[workflowEngine] " + "deleteQuery Start");

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("wkf_view_application_classification_activity ");
		sql.append("WHERE ");
		sql.append("company_code = ? ");

		log.debug("[workflowEngine] SQL:" + sql.toString());
		log.debug("[workflowEngine] " + "deleteQuery End");
		return sql.toString();
	}

	/**
	 * delete by route query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected String deleteByRouteQuery(){

		log.debug("[workflowEngine] " + "deleteByRouteQuery Start");

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("wkf_application_classification_activity ");
		sql.append("WHERE ");
		sql.append("company_code = ? AND ");
		sql.append("application_classification_code = ? ");

		log.debug("[workflowEngine] SQL:" + sql.toString());
		log.debug("[workflowEngine] " + "deleteByRouteQuery End");
		return sql.toString();
	}

	/**
	 * insert query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected String insertQuery(){

		log.debug("[workflowEngine] " + "insertQuery Start");

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO wkf_application_classification_activity ");
		sql.append("(");
		sql.append("company_code,");
		sql.append("application_classification_code,");
		sql.append("activity_code,");
		sql.append("approval_company_code,");
		sql.append("approval_role_code,");
		sql.append("approval_unit_code,");
		sql.append("approval_user_code,");
		sql.append("function,");
		sql.append("party_code,");
		sql.append("party_code_connector,");
		sql.append("next_party_code,");
		sql.append("party_transit_code,");
		sql.append("party_transit_code_connector,");
		sql.append("branch_item_name,");
		sql.append("branch_item,");
		sql.append("comparison_operator,");
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
		sql.append("?,");
		sql.append("?,");
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

		log.debug("[workflowEngine] SQL:" + sql.toString());
		log.debug("[workflowEngine] " + "insertQuery End");
		return sql.toString();
	}

	/**
	 * update query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected String updateQuery(){

		log.debug("[workflowEngine] " + "updateQuery Start");

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE wkf_application_classification_activity ");
		sql.append("SET ");
		sql.append("approval_company_code = ?, ");
		sql.append("approval_role_code = ?, ");
		sql.append("approval_unit_code = ?, ");
		sql.append("approval_user_code = ?, ");
		sql.append("function = ?, ");
		sql.append("party_code = ?, ");
		sql.append("party_code_connector = ?, ");
		sql.append("next_party_code = ?, ");
		sql.append("party_transit_code = ?, ");
		sql.append("party_transit_code_connector = ?, ");
		sql.append("branch_item_name = ?, ");
		sql.append("branch_item = ?, ");
		sql.append("comparison_operator = ?, ");
		sql.append("update_date_time = CURRENT_TIMESTAMP(0), ");
		sql.append("update_user_id = ? ");
		sql.append("WHERE ");
		sql.append("company_code = ? AND ");
		sql.append("application_classification_code = ? AND ");
		sql.append("activity_code = ?;");

		log.debug("[workflowEngine] SQL:" + sql.toString());
		log.debug("[workflowEngine] " + "updateQuery End");
		return sql.toString();
	}

	/**
	 * find by route query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected String findByRouteQuery(){

		log.debug("[workflowEngine] " + "findByRouteQuery Start");

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("company_code, ");
		sql.append("company_name, ");
		sql.append("route_code, ");
		sql.append("route_name, ");
		sql.append("activity_code, ");
		sql.append("approval_company_code, ");
		sql.append("approval_company_name, ");
		sql.append("approval_role_code, ");
		sql.append("approval_role_name, ");
		sql.append("approval_unit_code, ");
		sql.append("approval_unit_name, ");
		sql.append("approval_user_code, ");
		sql.append("approval_user_name, ");
		sql.append("function, ");
		sql.append("party_code, ");
		sql.append("party_code_connector, ");
		sql.append("next_party_code, ");
		sql.append("party_transit_code, ");
		sql.append("party_transit_code_connector, ");
		sql.append("branch_item_name, ");
		sql.append("branch_item, ");
		sql.append("comparison_operator, ");
		sql.append("create_user_id, ");
		sql.append("update_user_id ");
		sql.append("FROM ");
		sql.append("wkf_view_special_activity");
		sql.append(" ");
		sql.append("WHERE ");
		sql.append("company_code = ? AND route_code = ?");
		sql.append(";");

		log.debug("[workflowEngine] SQL:" + sql.toString());
		log.debug("[workflowEngine] " + "findByRouteQuery End");
		return sql.toString();
	}

	/**
	 * find by activity query<br>
	 * @return result
	 * @throws LaubeException
	 */
	protected String findByActivityQuery(){

		log.debug("[workflowEngine] " + "findByActivityQuery Start");

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("company_code, ");
		sql.append("company_name, ");
		sql.append("route_code, ");
		sql.append("route_name, ");
		sql.append("activity_code, ");
		sql.append("approval_company_code, ");
		sql.append("approval_company_name, ");
		sql.append("approval_role_code, ");
		sql.append("approval_role_name, ");
		sql.append("approval_unit_code, ");
		sql.append("approval_unit_name, ");
		sql.append("approval_user_code, ");
		sql.append("approval_user_name, ");
		sql.append("function, ");
		sql.append("party_code, ");
		sql.append("party_code_connector, ");
		sql.append("next_party_code, ");
		sql.append("party_transit_code, ");
		sql.append("party_transit_code_connector, ");
		sql.append("branch_item_name, ");
		sql.append("branch_item, ");
		sql.append("comparison_operator, ");
		sql.append("create_user_id, ");
		sql.append("update_user_id ");
		sql.append("FROM ");
		sql.append("wkf_view_special_activity");
		sql.append(" ");
		sql.append("WHERE ");
		sql.append("company_code = ? AND route_code = ? AND activity_code = ?");
		sql.append(";");

		log.debug("[workflowEngine] SQL:" + sql.toString());
		log.debug("[workflowEngine] " + "findByActivityQuery End");
		return sql.toString();
	}
}