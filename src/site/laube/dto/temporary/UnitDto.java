package site.laube.dto.temporary;

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

public class UnitDto extends CompanyDto {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 4575225513879036593L;

	/**
	 * to manage the unit code.<br>
	 */
	private String unitCode;

	/**
	 * to manage the department name.<br>
	 */
	private String unitName;

	/**
	 * to manage the manager code.<br>
	 */
	private String managerCode;

	/**
	 * to manage the manager name.<br>
	 */
	private String managerName;

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
	public final void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * get the unit name.<br>
	 *
	 * @return unit name
	 */
	public final String getUnitName() {
		return this.unitName;
	}

	/**
	 * set the manager code.<br>
	 * @param managerCode manager code
	 */
	public final void setManagerCode(final String managerCode) {
		this.managerCode = managerCode;
	}

	/**
	 * get the manager code.<br>
	 * @return manager code
	 */
	public final String getManagerCode() {
		return this.managerCode;
	}

	/**
	 * set the manager name.<br>
	 * @param managerName manager name
	 */
	public final void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	/**
	 * get the manager name.<br>
	 *
	 * @return manager name
	 */
	public final String getManagerName() {
		return this.managerName;
	}
}
