package site.laube.dto.temporary;

import lombok.Getter;
import lombok.Setter;
import site.laube.dto.LaubeDto;
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
@Getter
@Setter
public class CompanyDto extends LaubeDto {

	/**
	 * To manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 4575225513879036593L;

	/**
	 * To manage the company code.<br>
	 */
	private String companyCode;

	/**
	 * To manage the company name.<br>
	 */
	private String companyName;

	/**
	 * All the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
