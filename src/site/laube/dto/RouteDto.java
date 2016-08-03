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
public class RouteDto extends CompanyDto {

	/**
	 * to manage the log.<br>
	 */
	private static final long serialVersionUID = 8811788410134049514L;

	/**
	 * to manage the route code.<br>
	 */
	private String routeCode;

	/**
	 * to manage the route name.<br>
	 */
	private String routeName;

	/**
	 * to manage the route type.<br>
	 */
	private int routeType;
}
