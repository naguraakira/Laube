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
public final class AppendedDto extends ApplicationObjectDto {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 1749200060270412825L;

	/**
	 * to manage the number of attachments.<br>
	 */
	private int approvalNumber;

	/**
	 * to manage the company code of the approver.<br>
	 */
	private String approvalCompanyCode;

	/**
	 * to manage the company name of the approver.<br>
	 */
	private String approvalCompanyName;

	/**
	 * to manage the unit code of the approver.<br>
	 */
	private String approvalUnitCode = null;

	/**
	 * to manage the unit name of the approver.<br>
	 */
	private String approvalUnitName = null;

	/**
	 * to manage the employee number of the approver.<br>
	 */
	private String approvalUserCode = null;

	/**
	 * to manage the employee name of the approver.<br>
	 */
	private String approvalUserName = null;

	/**
	 * to manage the path to the attached file.<br>
	 */
	private String approvalPath = null;

	/**
	 * to manage the attached file attachments date.<br>
	 */
	private Timestamp approvalDate = null;
}
