package site.laube.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
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
	private String partyTransitCode;

	/**
	 * to manage the party transit code.<br>
	 */
	private int partyTransitCodeConnector;

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
}
