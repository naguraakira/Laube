package site.laube.dto;

import lombok.Getter;
import lombok.Setter;
import site.laube.dto.temporary.UserDto;

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
}
