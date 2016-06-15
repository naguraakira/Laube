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

public final class RoleUserDto extends RoleDto {

	/**
	 * to manage the log.<br>
	 */
	private static final long serialVersionUID = 6539232413121478335L;

	/**
	 * to manage the unit code.<br>
	 */
	private String unitCode;

	/**
	 * to manage the unit name.<br>
	 */
	private String unitName;

	/**
	 * to manage the user code.<br>
	 */
	private String userCode;

	/**
	 * to manage the user name.<br>
	 */
	private String userName;

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
	 * @param unitCode unit name
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * get the unit name.<br>
	 * @return unit name
	 */
	public String getUnitName() {
		return this.unitName;
	}

	/**
	 * set the user code.<br>
	 * @param userCode user code
	 */
	public final void setUserCode(final String userCode) {
		this.userCode = userCode;
	}

	/**
	 * get the user code.<br>
	 * @return user code
	 */
	public final String getUserCode() {
		return this.userCode;
	}

	/**
	 * set the user name.<br>
	 * @param userCode user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * get the user name.<br>
	 * @return user name
	 */
	public String getUserName() {
		return this.userName;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
