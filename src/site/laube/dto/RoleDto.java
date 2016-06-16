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

public class RoleDto extends CompanyDto {

	/**
	 * to manage the log.<br>
	 */
	private static final long serialVersionUID = -737624750139517158L;

	/**
	 * to manage role code.<br>
	 */
	private String roleCode;

	/**
	 * to manage role name.<br>
	 */
	private String roleName;

	/**
	 * to manage number of registered employees<br>
	 */
	private int count;

	/**
	 * set the role code.<br>
	 * @param roleCode role code
	 */
	public final void setRoleCode(final String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * get the role code.<br>
	 *
	 * @return role code
	 */
	public final String getRoleCode() {
		return this.roleCode;
	}

	/**
	 * set the role name.<br>
	 * @param roleName role name
	 */
	public final void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	/**
	 * get the role name.<br>
	 *
	 * @return role name
	 */
	public final String getRoleName() {
		return this.roleName;
	}

	/**
	 * get the number of registered employees.<br>
	 *
	 * @return number of registered employees
	 */
	public final int getCount() {
		return this.count;
	}

	/**
	 * all the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
