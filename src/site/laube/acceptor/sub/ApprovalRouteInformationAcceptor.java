package site.laube.acceptor.sub;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
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
public final class ApprovalRouteInformationAcceptor implements Serializable {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 2111275563007100295L;

	/**
	 * to manage the party code.<br>
	 */
	private String partyCode;

	/**
	 * to manage the party Code Connector.<br>
	 */
	private int partyCodeConnector;

	/**
	 * to manage the route Type.<br>
	 */
	private int routeType;

	/**
	 * to manage the approval company code.<br>
	 */
	private String approvalCompanyCode = null;

	/**
	 * to manage the approval unit code.<br>
	 */
	private String approvalUnitCode = null;

	/**
	 * to manage the approval user code.<br>
	 */
	private String approvalUserCode = null;

	/**
	 * to manage the deputy approval company code.<br>
	 */
	private String deputyApprovalCompanyCode = null;

	/**
	 * to manage the deputy approval unit code.<br>
	 */
	private String deputyApprovalUnitCode = null;

	/**
	 * to manage the deputy approval user code.<br>
	 */
	private String deputyApprovalUserCode = null;

	/**
	 * to manage the function.<br>
	 */
	private int function = 0;

	/**
	 * to manage the delete flag.<br>
	 */
	private boolean deleteFlag = false;

	/**
	 * to manage the deputy approval comment.<br>
	 */
	private String deputyApprovalComment = null;

	/**
	 * to manage the next party code.<br>
	 */
	private String nextPartyCode;

	/**
	 * to manage the party transit code.<br>
	 */
	private String partyTransitCode;

	/**
	 * to manage the party transit connector.<br>
	 */
	private int partyTransitCodeConnector;

	/**
	 * All the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
