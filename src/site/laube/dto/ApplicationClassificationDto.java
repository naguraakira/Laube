package site.laube.dto;

import site.laube.dto.temporary.CompanyDto;
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

public class ApplicationClassificationDto extends CompanyDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = 5380706709408812768L;

	/**
	 * to manage the application classification code. <br>
	 */
	private String applicationClassificationCode = null;

	/**
	 * to manage the application classification name. <br>
	 */
	private String applicationClassificationName = null;

	/**
	 * to manage the sort order. <br>
	 */
	private int sortOrder;

	/**
	 * to manage the management unit code. <br>
	 * Employees of the same department and the management department will be able to proxy application the application
	 * of all of the employees.<br>
	 */
	private String managementUnitCode = null;

	/**
	 * to manage the management unit name. <br>
	 */
	private String managementUnitName = null;

	/**
	 * set the application classification code.<br>
	 * @param applicationClassificationCode application classification code
	 */
	public final void setApplicationClassificationCode(final String applicationClassificationCode) {
		this.applicationClassificationCode = applicationClassificationCode;
	}

	/**
	 * get the application classification code.<br>
	 * @return application classification code
	 */
	public final String getApplicationClassificationCode() {
		return this.applicationClassificationCode;
	}

	/**
	 * set the application classification name.<br>
	 * @param applicationClassificationName application classification name
	 */
	public final void setApplicationClassificationName(final String applicationClassificationName) {
		this.applicationClassificationName = applicationClassificationName;
	}

	/**
	 * get the application classification name.<br>
	 * @return application classification name
	 */
	public final String getApplicationClassificationName() {
		return this.applicationClassificationName;
	}

	/**
	 * set the sort order.<br>
	 * @param sortOrder sort order
	 */
	public final void setSortOrder(final int sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * get the sort order.<br>
	 * @return sort order
	 */
	public final int getSortOrder() {
		return this.sortOrder;
	}

	/**
	 * set the management unit code.<br>
	 * @param managementUnitCode management unit code
	 */
	public final void setManagementUnitCode(final String managementUnitCode) {
		this.managementUnitCode = managementUnitCode;
	}

	/**
	 * get the management unit code.<br>
	 * @return management unit code
	 */
	public final String getManagementUnitCode() {
		return this.managementUnitCode;
	}

	/**
	 * set the management unit name.<br>
	 * @param managementUnitName management unit name
	 */
	public final void setManagementUnitName(final String managementUnitName) {
		this.managementUnitName = managementUnitName;
	}

	/**
	 * get the management unit name.<br>
	 * @return management unit name
	 */
	public final String getManagementUnitName() {
		return this.managementUnitName;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
