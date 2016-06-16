package site.laube.dto;

import java.sql.Timestamp;

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

public class ActivityObjectDto extends ApplicationObjectDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = -4918780328972650732L;

	/**
	 * to manage the activity object code. <br>
	 */
	private int activityObjectCode;

	/**
	 * to manage the party code.<br>
	 */
	private String partyCode;

	/**
	 * to manage the party Code Connector.<br>
	 */
	private int partyCodeConnector;

	/**
	 * to manage the route type. <br>
	 * 1:Individual route<br>
	 * 2:Common route<br>
	 * 3:Special route<br>
	 */
	private int routeType;

	/**
	 * to manage the approval company code.<br>
	 */
	private String approvalCompanyCode = null;

	/**
	 * to manage the company name of the approver.<br>
	 */
	private String approvalCompanyName;

	/**
	 * to manage the approval unit code.<br>
	 */
	private String approvalUnitCode = null;

	/**
	 * to manage the unit name of the approver.<br>
	 */
	private String approvalUnitName = null;

	/**
	 * to manage the approval user code.<br>
	 */
	private String approvalUserCode = null;

	/**
	 * to manage the employee name of the approver.<br>
	 */
	private String approvalUserName = null;

	/**
	 * to manage the company code of the agency's approval.<br>
	 */
	private String deputyApprovalCompanyCode = null;

	/**
	 * to manage the company name of the agency's approval.<br>
	 */
	private String deputyApprovalCompanyName = null;

	/**
	 * to manage the employee number of the agency's approval.<br>
	 */
	private String deputyApprovalUserCode = null;

	/**
	 * to manage the employee name of the agency's approval.<br>
	 */
	private String deputyApprovalUserName = null;

	/**
	 * to manage the agency approved reason.<br>
	 */
	private String deputyApprovalComment = null;

	/**
	 * to manage the function.<br>
	 */
	private int function = 0;

	/**
	 * to manage the approval function name.<br>
	 */
	private String functionName;

	/**
	 * to manage the next party code.<br>
	 */
	private String nextPartyCode;

	/**
	 * to manage the party transit code.<br>
	 */
	private String partyTansitCode;

	/**
	 * to manage the party transit code.<br>
	 */
	private int partyTansitCodeConnector;

	/**
	 * to manage the application of the date of arrival.<br>
	 */
	private Timestamp reachingDate = null;

	/**
	 * to manage the application of the processing day.<br>
	 */
	private Timestamp processDate = null;

	/**
	 * to manage the approval status code of the approver.<br>
	 */
	private int activityStatus;

	/**
	 * to manage the approval status name of the approver.<br>
	 */
	private String activityStatusName = null;

	/**
	 * to manage the approval comment.<br>
	 */
	private String approvalComment = null;

	/**
	 * set the activity object code.<br>
	 * @param activityObjectCode activity object code
	 */
	public final void setActivityObjectCode(final int activityObjectCode) {
		this.activityObjectCode = activityObjectCode;
	}

	/**
	 * get the activity object code.<br>
	 * @return activity object cod
	 */
	public final int getActivityObjectCode() {
		return this.activityObjectCode;
	}

	/**
	 * set the party code.<br>
	 * @param partyCode party code
	 */
	public final void setPartyCode(final String partyCode) {
		this.partyCode = partyCode;
	}

	/**
	 * get the party code.<br>
	 * @return party code
	 */
	public final String getPartyCode() {
		return this.partyCode;
	}

	/**
	 * set the partyCodeConnector.<br>
	 * @param partyCodeConnector party Code Connector
	 */
	public final void setPartyCodeConnector(final int partyCodeConnector) {
		this.partyCodeConnector = partyCodeConnector;
	}

	/**
	 * get the partyCodeConnector.<br>
	 * @return partyCodeConnector
	 */
	public final int getPartyCodeConnector() {
		return this.partyCodeConnector;
	}

	/**
	 * set the route type.<br>
	 * @param routeType route type
	 */
	public void setRouteType(int routeType) {
		this.routeType = routeType;
	}

	/**
	 * get the route type.<br>
	 * @return route type
	 */
	public int getRouteType() {
		return routeType;
	}

	/**
	 * set the company code of the approver.<br>
	 * @param approvalCompanyCode company code of the approver
	 */
	public final void setApprovalCompanyCode(final String approvalCompanyCode) {
		this.approvalCompanyCode = approvalCompanyCode;
	}

	/**
	 * get the company code of the approver.<br>
	 * @return company code of the approver
	 */
	public final String getApprovalCompanyCode() {
		return this.approvalCompanyCode;
	}

	/**
	 * set the company name of the approver.<br>
	 * @param approvalCompanyName company name of the approver
	 */
	public void setApprovalCompanyName(String approvalCompanyName) {
		this.approvalCompanyName = approvalCompanyName;
	}

	/**
	 * get the company name of the approver.<br>
	 * @return company name of the approver
	 */
	public String getApprovalCompanyName() {
		return this.approvalCompanyName;
	}

	/**
	 * set the unit code of the approver.<br>
	 * @param approvalUnitCode unit code of the approver
	 */
	public final void setApprovalUnitCode(final String approvalUnitCode) {
		this.approvalUnitCode = approvalUnitCode;
	}

	/**
	 * get the unit code of the approver.<br>
	 * @return unit code of the approver
	 */
	public final String getApprovalUnitCode() {
		return this.approvalUnitCode;
	}

	/**
	 * set the unit name of the approver.<br>
	 * @param approvalUnitName unit name of the approver
	 */
	public void setApprovalUnitName(String approvalUnitName) {
		this.approvalUnitName = approvalUnitName;
	}

	/**
	 * get the unit name of the approver.<br>
	 * @return unit name of the approver
	 */
	public String getApprovalUnitName() {
		return this.approvalUnitName;
	}

	/**
	 * set the employee number of the approver.<br>
	 * @param approvalUserCode employee number of the approver
	 */
	public final void setApprovalUserCode(final String approvalUserCode) {
		this.approvalUserCode = approvalUserCode;
	}

	/**
	 * get the employee number of the approver.<br>
	 * @return employee number of the approver
	 */
	public final String getApprovalUserCode() {
		return this.approvalUserCode;
	}

	/**
	 * set the employee name of the approver.<br>
	 * @param approvalUserName employee name of the approver
	 */
	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	/**
	 * get the employee name of the approver.<br>
	 * @return employee name of the approver
	 */
	public String getApprovalUserName() {
		return this.approvalUserName;
	}

	/**
	 * set the company code of the agency's approval.<br>
	 * @param deputyApprovalCompanyCode deputy approval company code
	 */
	public final void setDeputyApprovalCompanyCode(final String deputyApprovalCompanyCode) {
		this.deputyApprovalCompanyCode = deputyApprovalCompanyCode;
	}

	/**
	 * get the company code of the agency's approval.<br>
	 * @return deputy approval company code
	 */
	public final String getDeputyApprovalCompanyCode() {
		return this.deputyApprovalCompanyCode;
	}

	/**
	 * set the company name of the proxy approver.<br>
	 * @param deputyApprovalCompanyName deputy approval company name
	 */
	public final void setDeputyApprovalCompanyName(final String deputyApprovalCompanyName) {
		this.deputyApprovalCompanyName = deputyApprovalCompanyName;
	}

	/**
	 * get the company name of the proxy approver.<br>
	 * @return deputy approval company name
	 */
	public final String getDeputyApprovalCompanyName() {
		return this.deputyApprovalCompanyName;
	}

	/**
	 * set the employee number of the proxy approver<br>
	 * @param deputyApprovalUserCode deputy approval user code
	 */
	public final void setDeputyApprovalUserCode(final String deputyApprovalUserCode) {
		this.deputyApprovalUserCode = deputyApprovalUserCode;
	}

	/**
	 * get the employee number of the proxy approver<br>
	 * @return deputy approval user code
	 */
	public final String getDeputyApprovalUserCode() {
		return this.deputyApprovalUserCode;
	}

	/**
	 * set the employee name of the proxy approver<br>
	 * @param deputyApprovalUserName deputy approval user name
	 */
	public void setDeputyApprovalUserName(String deputyApprovalUserName) {
		this.deputyApprovalUserName = deputyApprovalUserName;
	}

	/**
	 * get the employee name of the proxy approver<br>
	 * @return deputy approval user name
	 */
	public String getDeputyApprovalUserName() {
		return this.deputyApprovalUserName;
	}

	/**
	 * set the proxy approval reason.<br>
	 * @param deputyApprovalComment deputy approval comment
	 */
	public void setDeputyApprovalComment(String deputyApprovalComment) {
		this.deputyApprovalComment = deputyApprovalComment;
	}

	/**
	 * get the proxy approval reason.<br>
	 * @return deputy comment
	 */
	public String getDeputyApprovalComment() {
		return this.deputyApprovalComment;
	}

	/**
	 * set the approval function.<br>
	 * @param function approval function
	 */
	public final void setFunction(final int function) {
		this.function = function;
	}

	/**
	 * get the approval function.<br>
	 * @return approval function
	 */
	public final int getFunction() {
		return this.function;
	}

	/**
	 * set the approval function name.<br>
	 * @param functionName function name
	 */
	public final void setFunctionName(final String functionName) {
		this.functionName = functionName;
	}

	/**
	 * get the approval function name.<br>
	 * @return function name
	 */
	public final String getFunctionName() {
		return this.functionName;
	}

	/**
	 * set the next party code.<br>
	 * @param nextPartyCode next party code
	 */
	public final void setNextPartyCode(final String nextPartyCode) {
		this.nextPartyCode = nextPartyCode;
	}

	/**
	 * get the next party code.<br>
	 * @return next party code
	 */
	public final String getNextPartyCode() {
		return this.nextPartyCode;
	}

	/**
	 * set the party transit code.<br>
	 * @param partyTransitCode party transit code
	 */
	public final void setPartyTransitCode(final String partyTransitCode) {
		this.partyTansitCode = partyTransitCode;
	}

	/**
	 * get the party transit code.<br>
	 * @return party transit code
	 */
	public final String getPartyTransitCode() {
		return this.partyTansitCode;
	}

	/**
	 * set the party Transit code connector.<br>
	 * @param partyTansitCodeConnector party Transit code connector
	 */
	public final void setPartyTransitCodeConnector(final int partyTansitCodeConnector) {
		this.partyTansitCodeConnector = partyTansitCodeConnector;
	}

	/**
	 * get the party Transit Code Connector.<br>
	 * @return partyTransitCodeConnector
	 */
	public final int getPartyTransitCodeConnector() {
		return this.partyTansitCodeConnector;
	}

	/**
	 * set the reaching date.<br>
	 * @param reachingDate reaching date
	 */
	public void setReachingDate(Timestamp reachingDate) {
		this.reachingDate = reachingDate;
	}

	/**
	 * get the reaching date.<br>
	 * @return reaching date
	 */
	public Timestamp getReachingDate() {
		return this.reachingDate;
	}

	/**
	 * set the application form the date of arrival.<br>
	 * @param processDate process date
	 */
	public void setProcessDate(Timestamp processDate) {
		this.processDate = processDate;
	}

	/**
	 * get the application of the processing day.<br>
	 * @return application of the processing day
	 */
	public Timestamp getProcessDate() {
		return this.processDate;
	}

	/**
	 * set the approval status of the approver.<br>
	 * @param activityStatus activity status
	 */
	public final void setActivityStatus(final int activityStatus) {
		this.activityStatus = activityStatus;
	}

	/**
	 * get the approval status of the approver.<br>
	 * @return activity status
	 */
	public final int getActivityStatus() {
		return this.activityStatus;
	}

	/**
	 * set the approval status name of the approver.<br>
	 * @param activityStatusName activity status name
	 */
	public void setActivityStatusName(String activityStatusName) {
		this.activityStatusName = activityStatusName;
	}
	/**
	 * get the approval status name of the approver.<br>
	 * @return activity status name
	 */
	public String getActivityStatusName() {
		return this.activityStatusName;
	}


	/**
	 * set the approval comment.<br>
	 * @param approvalComment approval comment
	 */
	public final void setApprovalComment(final String approvalComment) {
		this.approvalComment = approvalComment;
	}

	/**
	 * get the approval comment.<br>
	 * @return approval comment
	 */
	public final String getApprovalComment() {
		return this.approvalComment;
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
