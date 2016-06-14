package site.laube.dto;

import site.laube.dto.temporary.UserDto;
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

public class BossDto extends UserDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = -5545503360639439566L;

	/**
	 * to manage the application code.<br>
	 */
	private String applicationFormCode;

	/**
	 * to manage the application form name.<br>
	 */
	private String applicationFormName;

	/**
	 * to manage the company code of the immediate supervisor.<br>
	 */
	private String bossCompanyCode;

	/**
	 * to manage the company name of the immediate supervisor.<br>
	 */
	private String bossCompanyName;

	/**
	 * to manage the department code of the immediate supervisor.<br>
	 */
	private String bossUnitCode;

	/**
	 * to manage the department name of the immediate supervisor.<br>
	 */
	private String bossUnitName;

	/**
	 * to manage the employee number of the immediate supervisor.<br>
	 */
	private String bossUserCode;

	/**
	 * to manage the employee name of immediate supervisor.<br>
	 */
	private String bossUserName;

	/**
	 * set the application code.<br>
	 * @param applicationFormCode application form code
	 */
	public void setApplicationFormCode(String applicationFormCode) {
		this.applicationFormCode = applicationFormCode;
	}

	/**
	 * get the application code.<br>
	 * @return application form code
	 */
	public String getApplicationFormCode() {
		return this.applicationFormCode;
	}

	/**
	 * set the application form name.<br>
	 * @param applicationFormName application form name
	 */
	public final void setApplicationFormName(final String applicationFormName) {
		this.applicationFormName = applicationFormName;
	}

	/**
	 * get the application form name.<br>
	 * @return application form name
	 */
	public final String getApplicationFormName() {
		return this.applicationFormName;
	}

	/**
	 * set the company code of the immediate supervisor.<br>
	 * @param bossCompanyCode boss company code
	 */
	public void setBossCompanyCode(String bossCompanyCode) {
		this.bossCompanyCode = bossCompanyCode;
	}

	/**
	 * get the company code of the immediate supervisor.<br>
	 * @return boss company code
	 */
	public String getBossCompanyCode() {
		return bossCompanyCode;
	}

	/**
	 * set the company name of the immediate supervisor.<br>
	 * @param bossCompanyName boss company name
	 */
	public void setBossCompanyName(String bossCompanyName) {
		this.bossCompanyName = bossCompanyName;
	}

	/**
	 * get the company name of the immediate supervisor.<br>
	 * @return boss company name
	 */
	public String getBossCompanyName() {
		return bossCompanyName;
	}

	/**
	 * set the department code of the immediate supervisor.<br>
	 * @param bossUnitCode boss unit code
	 */
	public void setBossUnitCode(String bossUnitCode) {
		this.bossUnitCode = bossUnitCode;
	}

	/**
	 * get the department code of the immediate supervisor.<br>
	 * @return boss unit code
	 */
	public String getBossUnitCode() {
		return bossUnitCode;
	}

	/**
	 * set the department name of the immediate supervisor.<br>
	 * @param bossUnitName boss unit name
	 */
	public void setBossUnitName(String bossUnitName) {
		this.bossUnitName = bossUnitName;
	}

	/**
	 * get the department name of the immediate supervisor.<br>
	 * @return boss unit name
	 */
	public String getBossUnitName() {
		return bossUnitName;
	}

	/**
	 * set the employee number of the immediate supervisor.<br>
	 * @param bossUserCode boss user code
	 */
	public void setBossUserCode(String bossUserCode) {
		this.bossUserCode = bossUserCode;
	}

	/**
	 * get the employee number of the immediate supervisor.<br>
	 * @return boss user code
	 */
	public String getBossUserCode() {
		return bossUserCode;
	}

	/**
	 * set the employee name of the immediate supervisor.<br>
	 * @param bossUserName boss user name
	 */
	public void setBossUserName(String bossUserName) {
		this.bossUserName = bossUserName;
	}

	/**
	 * get the employee name of the immediate supervisor.<br>
	 * @return boss user name
	 */
	public String getBossUserName() {
		return bossUserName;
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
