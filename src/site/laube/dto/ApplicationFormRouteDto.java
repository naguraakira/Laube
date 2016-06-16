package site.laube.dto;

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

public final class ApplicationFormRouteDto extends ApplicationFormDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = -8336258002631442544L;

	/**
	 * to manage the unit code. <br>
	 */
	private String unitCode;

	/**
	 * to manage the unit name. <br>
	 */
	private String unitName;

	/**
	 * to manage the individual route code. <br>
	 */
	private String individualRouteCode;

	/**
	 * to manage the individual route name. <br>
	 */
	private String individualRouteName;

	/**
	 * to manage the common route code. <br>
	 */
	private String commonRouteCode;

	/**
	 * to manage the common route name. <br>
	 */
	private String commonRouteName;

	/**
	 * set the unit code.<br>
	 * @param unitCode unit code
	 */
	public final void setUnitCode(final String unitCode) {
		this.unitCode = unitCode;
	}

	/**
	 * get the unit code.<br>
	 * @return unit code
	 */
	public final String getUnitCode() {
		return this.unitCode;
	}

	/**
	 * set the unit name.<br>
	 * @param unitName unit name
	 */
	public final void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * get the unit name.<br>
	 * @return unit name
	 */
	public final String getUnitName() {
		return this.unitName;
	}

	/**
	 * set the individual route code.<br>
	 * @param individualRouteCode individual route code
	 */
	public final void setIndividualRouteCode(final String individualRouteCode) {
		this.individualRouteCode = individualRouteCode;
	}

	/**
	 * get the individual route code.<br>
	 * @return individual route code
	 */
	public final String getIndividualRouteCode() {
		return this.individualRouteCode;
	}

	/**
	 * set the individual route name.<br>
	 * @param individualRouteName individual route name
	 */
	public final void setIndividualRouteName(final String individualRouteName) {
		this.individualRouteName = individualRouteName;
	}

	/**
	 * get the individual route name.<br>
	 * @return individual route name
	 */
	public final String getIndividualRouteName() {
		return this.individualRouteName;
	}

	/**
	 * set the common route code.<br>
	 * @param commonRouteCode common route code
	 */
	public final void setCommonRouteCode(final String commonRouteCode) {
		this.commonRouteCode = commonRouteCode;
	}

	/**
	 * get the common route code.<br>
	 * @return common route code
	 */
	public final String getCommonRouteCode() {
		return this.commonRouteCode;
	}

	/**
	 * set the common route name.<br>
	 * @param commonRouteName common route name
	 */
	public void setCommonRouteName(String commonRouteName) {
		this.commonRouteName = commonRouteName;
	}

	/**
	 * get the common route name.<br>
	 * @return common route name
	 */
	public String getCommonRouteName() {
		return this.commonRouteName;
	}

	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
