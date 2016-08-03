package site.laube.dto;

import lombok.Getter;
import lombok.Setter;
import site.laube.dto.temporary.CompanyDto;

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
}