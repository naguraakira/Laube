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

public class ApplicationObjectDto extends CompanyDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = -3758242472539403511L;

	/**
	 * to manage the application number. <br>
	 */
	private long applicationNumber;

	/**
	 * to manage the reapplication number. <br>
	 */
	private long reapplicationNumber;

	/**
	 * to manage the application classification code. <br>
	 */
	private String applicationClassificationCode;

	/**
	 * to manage the application classification name. <br>
	 */
	private String applicationClassificationName;

	/**
	 * to manage the application form code. <br>
	 */
	private String applicationFormCode;

	/**
	 * to manage the application form name. <br>
	 */
	private String applicationFormName;

	/**
	 * to manage the apply company code. <br>
	 */
	private String applyCompanyCode;

	/**
	 * to manage the apply company name. <br>
	 */
	private String applyCompanyName;

	/**
	 * to manage the apply unit code. <br>
	 */
	private String applyUnitCode;

	/**
	 * to manage the apply unit name. <br>
	 */
	private String applyUnitName;

	/**
	 * to manage the apply user code. <br>
	 */
	private String applyUserCode;

	/**
	 * to manage the apply user name. <br>
	 */
	private String applyUserName;

	/**
	 * to manage the deputy apply company code. <br>
	 */
	private String deputyApplyCompanyCode;

	/**
	 * to manage the deputy apply company name. <br>
	 */
	private String deputyApplyCompanyName;

	/**
	 * to manage the deputy apply unit code. <br>
	 */
	private String deputyApplyUnitCode;

	/**
	 * to manage the deputy apply unit name. <br>
	 */
	private String deputyApplyUnitName;

	/**
	 * to manage the deputy apply user code. <br>
	 */
	private String deputyApplyUserCode;

	/**
	 * to manage the deputy apply user name. <br>
	 */
	private String deputyApplyUserName;

	/**
	 * to manage the apply date. <br>
	 */
	private String applyDate;

	/**
	 * to manage the application status. <br>
	 */
	private int applicationStatus;

	/**
	 * to manage the application status name. <br>
	 */
	private String applicationStatusName;

	/**
	 * set the applicationn number.<br>
	 * @param applicationNumber applicationn number
	 */
	public final void setApplicationNumber(final long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * get the applicationn number.<br>
	 * @return applicationn number
	 */
	public final long getApplicationNumber() {
		return this.applicationNumber;
	}

	/**
	 * set the reapplicationn number.<br>
	 * @param reapplicationNumber reapplicationn number
	 */
	public final void setReapplicationNumber(final long reapplicationNumber) {
		this.reapplicationNumber = reapplicationNumber;
	}

	/**
	 * get the reapplicationn number.<br>
	 * @return reapplicationn number
	 */
	public final long getReapplicationNumber() {
		return this.reapplicationNumber;
	}

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
	 * set the application form code.<br>
	 * @param applicationFormCode application form code
	 */
	public final void setApplicationFormCode(final String applicationFormCode) {
		this.applicationFormCode = applicationFormCode;
	}

	/**
	 * get the application form code.<br>
	 * @return application form code
	 */
	public final String getApplicationFormCode() {
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
	 * set the apply company code.<br>
	 * @param applyCompanyCode apply company code
	 */
	public final void setApplyCompanyCode(final String applyCompanyCode) {
		this.applyCompanyCode = applyCompanyCode;
	}

	/**
	 * get the apply company code.<br>
	 * @return apply company code
	 */
	public final String getApplyCompanyCode() {
		return this.applyCompanyCode;
	}

	/**
	 * set the apply company name.<br>
	 * @param applyCompanyName apply company name
	 */
	public final void setApplyCompanyName(final String applyCompanyName) {
		this.applyCompanyName = applyCompanyName;
	}

	/**
	 * get the apply company name.<br>
	 * @return apply company name
	 */
	public final String getApplyCompanyName() {
		return this.applyCompanyName;
	}

	/**
	 * set the apply unit code.<br>
	 * @param applyUnitCode apply unit code
	 */
	public final void setApplyUnitCode(final String applyUnitCode) {
		this.applyUnitCode = applyUnitCode;
	}

	/**
	 * get the apply unit code.<br>
	 * @return apply unit code
	 */
	public final String getApplyUnitCode() {
		return this.applyUnitCode;
	}

	/**
	 * set the apply unit name.<br>
	 * @param applyUnitName apply unit name
	 */
	public final void setApplyUnitName(final String applyUnitName) {
		this.applyUnitName = applyUnitName;
	}

	/**
	 * get the apply unit name.<br>
	 * @return apply unit name
	 */
	public final String getApplyUnitName() {
		return this.applyUnitName;
	}

	/**
	 * set the apply user code.<br>
	 * @param applyUserCode apply user code
	 */
	public final void setApplyUserCode(final String applyUserCode) {
		this.applyUserCode = applyUserCode;
	}

	/**
	 * get the apply user code.<br>
	 * @return apply user code
	 */
	public final String getApplyUserCode() {
		return this.applyUserCode;
	}

	/**
	 * set the apply user name.<br>
	 * @param applyUserName apply user name
	 */
	public final void setApplyUserName(final String applyUserName) {
		this.applyUserName = applyUserName;
	}

	/**
	 * get the apply user name.<br>
	 * @return apply user name
	 */
	public final String getApplyUserName() {
		return this.applyUserName;
	}

	/**
	 * set the deputy apply company code.<br>
	 * @param deputyApplyCompanyCode deputy apply company code
	 */
	public final void setDeputyApplyCompanyCode(final String deputyApplyCompanyCode) {
		this.deputyApplyCompanyCode = deputyApplyCompanyCode;
	}

	/**
	 * get the deputy apply company code.<br>
	 * @return deputy apply company code
	 */
	public final String getDeputyApplyCompanyCode() {
		return this.deputyApplyCompanyCode;
	}

	/**
	 * set the deputy apply company name.<br>
	 * @param deputyApplyCompanyName deputy apply company name
	 */
	public final void setDeputyApplyCompanyName(final String deputyApplyCompanyName) {
		this.deputyApplyCompanyName = deputyApplyCompanyName;
	}

	/**
	 * get the deputy apply company name.<br>
	 * @return deputy apply company name
	 */
	public final String getDeputyApplyCompanyName() {
		return this.deputyApplyCompanyName;
	}

	/**
	 * set the deputy apply unit code.<br>
	 * @param deputyApplyUnitCode deputy apply unit code
	 */
	public final void setDeputyApplyUnitCode(final String deputyApplyUnitCode) {
		this.deputyApplyUnitCode = deputyApplyUnitCode;
	}

	/**
	 * get the deputy apply unit code.<br>
	 * @return deputy apply unit code
	 */
	public final String getDeputyApplyUnitCode() {
		return this.deputyApplyUnitCode;
	}

	/**
	 * set the deputy apply unit name.<br>
	 * @param deputyApplyUnitName deputy apply unit name
	 */
	public final void setDeputyApplyUnitName(final String deputyApplyUnitName) {
		this.deputyApplyUnitName = deputyApplyUnitName;
	}

	/**
	 * get the deputy apply unit name.<br>
	 * @return deputy apply unit name
	 */
	public final String getDeputyApplyUnitName() {
		return this.deputyApplyUnitName;
	}

	/**
	 * set the deputy apply user code.<br>
	 * @param deputyApplyUserCode deputy apply user code
	 */
	public final void setDeputyApplyUserCode(final String deputyApplyUserCode) {
		this.deputyApplyUserCode = deputyApplyUserCode;
	}

	/**
	 * get the deputy apply user code.<br>
	 * @return deputy apply user code
	 */
	public final String getDeputyApplyUserCode() {
		return this.deputyApplyUserCode;
	}

	/**
	 * get the deputy apply user name.<br>
	 * @return deputy apply user name
	 */
	public final String getDeputyApplyUserName() {
		return this.deputyApplyUserName;
	}

	/**
	 * set the deputy apply user name.<br>
	 * @param deputyApplyUserName deputy apply user name
	 */
	public final void setDeputyApplyUserName(final String deputyApplyUserName) {
		this.deputyApplyUserName = deputyApplyUserName;
	}

	/**
	 * set the apply date.<br>
	 * @param applyDate apply date
	 */
	public final void setApplyDate(final String applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * get the apply date.<br>
	 * @return apply date
	 */
	public final String getApplyDate() {

		if (!LaubeUtility.isEmpty(this.applyDate)){
			this.applyDate = this.applyDate.trim().replace('-', '/');
		}
		return this.applyDate;
	}

	/**
	 * set the application status.<br>
	 * @param applicationStatus application status
	 */
	public final void setApplicationStatus(final int applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	/**
	 * get the application status.<br>
	 * @return application status
	 */
	public final int getApplicationStatus() {
		return this.applicationStatus;
	}

	/**
	 * set the application status name.<br>
	 * @param applicationStatusName application status name
	 */
	public void setApplicationStatusName(String applicationStatusName) {
		this.applicationStatusName = applicationStatusName;
	}

	/**
	 * get the application status name.<br>
	 * @return application status name
	 */
	public String getApplicationStatusName() {
		return this.applicationStatusName;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
