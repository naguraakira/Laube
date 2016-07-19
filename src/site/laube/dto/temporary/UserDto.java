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

public class UserDto extends UnitDto {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 4575225513879036593L;

	/**
	 * to manage the employee number.<br>
	 */
	private String userCode;

	/**
	 * to manage the employee name.<br>
	 */
	private String userName;

	/**
	 * set the employee number.<br>
	 * @param userCode employee number
	 */
	public final void setUserCode(final String userCode) {
		this.userCode = userCode;
	}

	/**
	 * get the employee number.<br>
	 * @return employee number
	 */
	public final String getUserCode() {
		return this.userCode;
	}

	/**
	 * set the employee name.<br>
	 * @param userName employee name
	 */
	public final void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * get the employee name.<br>
	 *
	 * @return employee name
	 */
	public final String getUserName() {
		return this.userName;
	}
}
