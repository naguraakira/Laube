package site.laube.dto;

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
public final class ApplicationFormRouteDto extends ApplicationFormDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = -8336258002631442544L;

	/**
	 * to manage the unit code. <br>
	 */
	private String unitCode;

	/**
	 * to manage the unit name. <br>
	 */
	private String unitName;

	/**
	 * to manage the individual route code. <br>
	 */
	private String individualRouteCode;

	/**
	 * to manage the individual route name. <br>
	 */
	private String individualRouteName;

	/**
	 * to manage the common route code. <br>
	 */
	private String commonRouteCode;

	/**
	 * to manage the common route name. <br>
	 */
	private String commonRouteName;
}
