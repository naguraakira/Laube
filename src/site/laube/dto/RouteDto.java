package site.laube.dto;

import site.laube.dto.temporary.CompanyDto;

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

public class RouteDto extends CompanyDto {

	/**
	 * to manage the log.<br>
	 */
	private static final long serialVersionUID = 8811788410134049514L;

	/**
	 * to manage the route code.<br>
	 */
	private String routeCode;

	/**
	 * to manage the route name.<br>
	 */
	private String routeName;

	/**
	 * to manage the route type.<br>
	 */
	private int routeType;

	/**
	 * set the route code.<br>
	 * @param routeCode route code
	 */
	public final void setRouteCode(final String routeCode) {
		this.routeCode = routeCode;
	}

	/**
	 * get the route code.<br>
	 * @return route code
	 */
	public final String getRouteCode() {
		return this.routeCode;
	}

	/**
	 * set the route name.<br>
	 * @param routeName route name
	 */
	public final void setRouteName(final String routeName) {
		this.routeName = routeName;
	}

	/**
	 * get the route name.<br>
	 * @return route name
	 */
	public final String getRouteName() {
		return this.routeName;
	}

	/**
	 * set the route type.<br>
	 * @param routeType route type
	 */
	public final void setRouteType(int routeType) {
		this.routeType = routeType;
	}

	/**
	 * get the route type.<br>
	 * @return route type
	 */
	public final int getRouteType() {
		return routeType;
	}
}
