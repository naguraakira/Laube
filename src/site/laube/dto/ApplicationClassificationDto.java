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
public class ApplicationClassificationDto extends CompanyDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = 5380706709408812768L;

	/**
	 * to manage the application classification code. <br>
	 */
	private String applicationClassificationCode = null;

	/**
	 * to manage the application classification name. <br>
	 */
	private String applicationClassificationName = null;

	/**
	 * to manage the sort order. <br>
	 */
	private int sortOrder;

	/**
	 * to manage the management unit code. <br>
	 * Employees of the same department and the management department will be able to proxy application the application
	 * of all of the employees.<br>
	 */
	private String managementUnitCode = null;

	/**
	 * to manage the management unit name. <br>
	 */
	private String managementUnitName = null;
}
