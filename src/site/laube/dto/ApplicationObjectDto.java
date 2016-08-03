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
}
