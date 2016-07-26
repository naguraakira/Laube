package site.laube.model;

import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;

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

public final class IndividualActivityModel extends ActivityModel {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(IndividualActivityModel.class);

	/**
	 * delete query<br>
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings("nls")
	protected String deleteQuery(){

		log.traceStart("deleteQuery");

		final StringBuffer sql = new StringBuffer();
		sql.append("DELETE");
		sql.append(" FROM wkf_view_individual_activity");
		sql.append(" WHERE");
		sql.append(" company_code = ?");
		sql.append(";");

		log.message("deleteQuery", sql.toString());
		log.traceEnd("deleteQuery");
		return sql.toString();
	}

	/**
	 * delete by route query<br>
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings("nls")
	protected String deleteByRouteQuery(){

		log.traceStart("deleteByRouteQuery");

		final StringBuffer sql = new StringBuffer();
		sql.append("DELETE");
		sql.append(" FROM wkf_individual_activity");
		sql.append(" WHERE");
		sql.append(" company_code = ?");
		sql.append(" and individual_route_code = ?");
		sql.append(";");

		log.message("deleteByRouteQuery", sql.toString());
		log.traceEnd("deleteByRouteQuery");
		return sql.toString();
	}

	/**
	 * insert query<br>
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings("nls")
	protected String insertQuery(){

		log.traceStart("insertQuery");

		final StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO wkf_individual_activity");
		sql.append("(");
		sql.append(" company_code");
		sql.append(",individual_route_code");
		sql.append(",activity_code");
		sql.append(",approval_company_code");
		sql.append(",approval_role_code");
		sql.append(",approval_unit_code");
		sql.append(",approval_user_code");
		sql.append(",function");
		sql.append(",party_code");
		sql.append(",party_code_connector");
		sql.append(",next_party_code");
		sql.append(",party_transit_code");
		sql.append(",party_transit_code_connector");
		sql.append(",branch_item_name");
		sql.append(",branch_item");
		sql.append(",comparison_operator");
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
		sql.append(",?");
		sql.append(",?");
		sql.append(",?");
		sql.append(",?");
		sql.append(",?");
		sql.append(",?");
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

		log.message("insertQuery", sql.toString());
		log.traceEnd("insertQuery");
		return sql.toString();
	}

	/**
	 * update query<br>
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings("nls")
	protected String updateQuery(){

		log.traceStart("updateQuery");

		final StringBuffer sql = new StringBuffer();
		sql.append("UPDATE wkf_individual_activity");
		sql.append(" SET");
		sql.append(" approval_company_code = ?");
		sql.append(",approval_role_code = ?");
		sql.append(",approval_unit_code = ?");
		sql.append(",approval_user_code = ?");
		sql.append(",function = ?");
		sql.append(",party_code = ?");
		sql.append(",party_code_connector = ?");
		sql.append(",next_party_code = ?");
		sql.append(",party_transit_code = ?");
		sql.append(",party_transit_code_connector = ?");
		sql.append(",branch_item_name = ?");
		sql.append(",branch_item = ?");
		sql.append(",comparison_operator = ?");
		sql.append(",update_date_time = CURRENT_TIMESTAMP(0)");
		sql.append(",update_user_id = ?");
		sql.append(" WHERE");
		sql.append(" company_code = ?");
		sql.append(" and individual_route_code = ?");
		sql.append(" and activity_code = ?");
		sql.append(";");

		log.message("updateQuery", sql.toString());
		log.traceEnd("updateQuery");
		return sql.toString();
	}

	/**
	 * find by route query<br>
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings("nls")
	protected String findByRouteQuery(){

		log.traceStart("findByRouteQuery");

		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT");
		sql.append(" company_code");
		sql.append(",company_name");
		sql.append(",route_code");
		sql.append(",route_name");
		sql.append(",activity_code");
		sql.append(",approval_company_code");
		sql.append(",approval_company_name");
		sql.append(",approval_role_code");
		sql.append(",approval_role_name");
		sql.append(",approval_unit_code");
		sql.append(",approval_unit_name");
		sql.append(",approval_user_code");
		sql.append(",approval_user_name");
		sql.append(",function");
		sql.append(",party_code");
		sql.append(",party_code_connector");
		sql.append(",next_party_code");
		sql.append(",party_transit_code");
		sql.append(",party_transit_code_connector");
		sql.append(",branch_item_name");
		sql.append(",branch_item");
		sql.append(",comparison_operator");
		sql.append(",create_user_id");
		sql.append(",update_user_id");
		sql.append(" FROM wkf_view_individual_activity");
		sql.append(" WHERE");
		sql.append(" company_code = ?");
		sql.append(" and route_code = ?");
		sql.append(" ORDER BY company_code asc,activity_code asc");
		sql.append(";");

		log.message("findByRouteQuery", sql.toString());
		log.traceEnd("findByRouteQuery");
		return sql.toString();
	}

	/**
	 * find by activity query<br>
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings("nls")
	protected String findByActivityQuery(){

		log.traceStart("findByActivityQuery");

		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT");
		sql.append(" company_code");
		sql.append(",company_name");
		sql.append(",route_code");
		sql.append(",route_name");
		sql.append(",activity_code");
		sql.append(",approval_company_code");
		sql.append(",approval_company_name");
		sql.append(",approval_role_code");
		sql.append(",approval_role_name");
		sql.append(",approval_unit_code");
		sql.append(",approval_unit_name");
		sql.append(",approval_user_code");
		sql.append(",approval_user_name");
		sql.append(",function");
		sql.append(",party_code");
		sql.append(",party_code_connector");
		sql.append(",next_party_code");
		sql.append(",party_transit_code");
		sql.append(",party_transit_code_connector");
		sql.append(",branch_item_name");
		sql.append(",branch_item");
		sql.append(",comparison_operator");
		sql.append(",create_user_id");
		sql.append(",update_user_id");
		sql.append(" FROM wkf_view_individual_activity");
		sql.append(" WHERE");
		sql.append(" company_code = ?");
		sql.append(" and individual_route_code = ?");
		sql.append(" and activity_code = ?");
		sql.append(" ORDER BY company_code asc,route_code asc,activity_code asc");
		sql.append(";");

		log.message("findByActivityQuery", sql.toString());
		log.traceEnd("findByActivityQuery");
		return sql.toString();
	}
}