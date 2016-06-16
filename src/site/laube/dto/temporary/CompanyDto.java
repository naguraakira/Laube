package site.laube.dto.temporary;

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
	 * set the company code.<br>
	 * @param companyCode Company Code
	 */
	public final void setCompanyCode(final String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * get the company code.<br>
	 * @return company code
	 */
	public final String getCompanyCode() {
		return this.companyCode;
	}

	/**
	 * set the company name.<br>
	 * @param companyName company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * get the company name.<br>
	 *
	 * @return company name
	 */
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * All the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
