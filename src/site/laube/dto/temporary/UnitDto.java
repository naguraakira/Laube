package site.laube.dto.temporary;

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
public class UnitDto extends CompanyDto {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 4575225513879036593L;

	/**
	 * to manage the unit code.<br>
	 */
	private String unitCode;

	/**
	 * to manage the department name.<br>
	 */
	private String unitName;

	/**
	 * to manage the manager code.<br>
	 */
	private String managerCode;

	/**
	 * to manage the manager name.<br>
	 */
	private String managerName;
}