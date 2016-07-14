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

public class ApplicationFormDto extends CompanyDto {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 8712217230468992871L;

	/**
	 * to manage the application code.<br>
	 */
	private String applicationFormCode;

	/**
	 * to manage the application form.<br>
	 */
	private String applicationFormName;

	/**
	 * to manage the application classification code.<br>
	 */
	private String applicationClassificationCode = null;

	/**
	 * to manage the application classification name.<br>
	 */
	private String applicationClassificationName = null;

	/**
	 * to manage the automatic skip apply user.<br>
	 * If this flag is true, erase about user from individual route.
	 */
	private boolean skipApplyUser;

	/**
	 * to manage the automatic approval flag.<br>
	 * If this flag is true, automatically update to the [approval]
	 * when you arrive employees applicant was once approved for the same employee number again.
	 */
	private boolean autoApprovalFlag;

	/**
	 * to manage the hike approval flag.<br>
	 * If this flag is true, you can be approved even without me meet the lower approval.
	 */
	private boolean pullingFlag;

	/**
	 * to manage the individual route segment. <br>
	 * 1:individual route<br>
	 * 2:boss route<br>
	 * 9:No individual route<br>
	 */
	private int routeFlag;

	/**
	 * set the application code.<br>
	 * @param applicationFormCode application code
	 */
	public final void setApplicationFormCode(final String applicationFormCode) {
		this.applicationFormCode = applicationFormCode;
	}

	/**
	 * get the application code.<br>
	 * @return application code
	 */
	public final String getApplicationFormCode() {
		return this.applicationFormCode;
	}

	/**
	 * set the application name.<br>
	 * @param applicationFormName application name
	 */
	public final void setApplicationFormName(final String applicationFormName) {
		this.applicationFormName = applicationFormName;
	}

	/**
	 * get the application name.<br>
	 * @return application name
	 */
	public final String getApplicationFormName() {
		return this.applicationFormName;
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
	 * Set the skip apply user.<br>
	 * @param skipApplyUser skip apply user
	 */
	public final void setSkipApplyUser(final boolean skipApplyUser) {
		this.skipApplyUser = skipApplyUser;
	}

	/**
	 * Get the skip apply user.<br>
	 * @return skip apply user
	 */
	public final boolean isSkipApplyUser() {
		return this.skipApplyUser;
	}

	/**
	 * Set the automatic approval flag.<br>
	 * @param autoApprovalFlag automatic approval flag
	 */
	public final void setAutoApprovalFlag(final boolean autoApprovalFlag) {
		this.autoApprovalFlag = autoApprovalFlag;
	}

	/**
	 * Get the automatic approval flag.<br>
	 * @return automatic approval flag
	 */
	public final boolean isAutoApprovalFlag() {
		return this.autoApprovalFlag;
	}

	/**
	 * Set the pulling flag.<br>
	 * @param pullingFlag pulling flag
	 */
	public final void setPullingFlag(final boolean pullingFlag) {
		this.pullingFlag = pullingFlag;
	}

	/**
	 * Get the pulling flag.<br>
	 * @return pulling flag
	 */
	public final boolean isPpullingFlag() {
		return this.pullingFlag;
	}

	/**
	 * Set the individual route segment.<br>
	 * @param routeFlag individual route segment
	 */
	public final void setRouteFlag(final int routeFlag) {
		this.routeFlag = routeFlag;
	}

	/**
	 * Get the individual route segment.<br>
	 * 1:individual route<br>
	 * 2:boss route<br>
	 * 9:No individual route<br>
	 * @return individual route segment
	 */
	public final int getRouteFlag() {
		return this.routeFlag;
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