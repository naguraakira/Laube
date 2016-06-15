package site.laube.dto;

import site.laube.dto.temporary.UserDto;
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

public final class DeputyApprovelDto extends UserDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = -5313076077453748225L;

	/**
	 * to manage the deputy approverl company code. <br>
	 */
	private String deputyApproverlCompanyCode;

	/**
	 * to manage the deputy approverl unit code. <br>
	 */
	private String deputyApproverlUnitCode;

	/**
	 * to manage the deputy approverl user code. <br>
	 */
	private String deputyApproverlUserCode;

	/**
	 * to manage the deputy approverl user name. <br>
	 */
	private String deputyApproverlUserName;

	/**
	 * to manage the deputy contents. <br>
	 */
	private String deputyContents;

	/**
	 * set the deputy approverl company code.<br>
	 * @param deputyApproverlCompanyCode deputy approverl company code
	 */
	public final void setDeputyApproverlCompanyCode(final String deputyApproverlCompanyCode) {
		this.deputyApproverlCompanyCode = deputyApproverlCompanyCode;
	}

	/**
	 * get the deputy approverl company code.<br>
	 * @return deputy approverl company code
	 */
	public final String getDeputyApproverlCompanyCode() {
		return this.deputyApproverlCompanyCode;
	}

	/**
	 * set the deputy approverl unit code.<br>
	 * @param deputyApproverlUnitCode deputy approverl unit code
	 */
	public final void setDeputyApproverlUnitCode(final String deputyApproverlUnitCode) {
		this.deputyApproverlUnitCode = deputyApproverlUnitCode;
	}

	/**
	 * get the deputy approverl unit code.<br>
	 * @return deputy approverl unit code
	 */
	public final String getDeputyApproverlUnitCode() {
		return this.deputyApproverlUnitCode;
	}

	/**
	 * set the deputy approverl user code.<br>
	 * @param deputyApproverlUnitCode deputy approverl user code
	 */
	public final void setDeputyApproverlUserCode(final String deputyApproverlUserCode) {
		this.deputyApproverlUserCode = deputyApproverlUserCode;
	}

	/**
	 * get the deputy approverl user code.<br>
	 * @return deputy approverl user code
	 */
	public final String getDeputyApproverlUserCode() {
		return this.deputyApproverlUserCode;
	}

	/**
	 * set the deputy approverl user name.<br>
	 * @param deputyApproverlUserName deputy approverl user name
	 */
	public final void setDeputyApproverlUserName(final String deputyApproverlUserName) {
		this.deputyApproverlUserName = deputyApproverlUserName;
	}

	/**
	 * get the deputy approverl user name.<br>
	 * @return deputy approverl user name
	 */
	public final String getDeputyApproverlUserName() {
		return this.deputyApproverlUserName;
	}

	/**
	 * set the deputy approverl contents.<br>
	 * @param deputyApproverlUserName deputy contents
	 */
	public final void setDeputyContents(final String deputyContents) {
		this.deputyContents = deputyContents;
	}

	/**
	 * get the deputy approverl contents.<br>
	 * @return deputy approverl contents
	 */
	public final String getDeputyContents() {
		return this.deputyContents;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
