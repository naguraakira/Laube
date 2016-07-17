package site.laube.acceptor.sub;

import java.io.Serializable;

import site.laube.utility.LaubeUtility;
import site.laube.utility.SpecifiedValue;
import site.laube.utility.type.RouteType;

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
	private String partyTansitCode;

	/**
	 * to manage the party transit connector.<br>
	 */
	private int partyTansitCodeConnector;

	/**
	 * set the party code.<br>
	 * @param partyCode party code
	 */
	public final void setPartyCode(final String partyCode) {
		this.partyCode = partyCode;
	}

	/**
	 * get the party code.<br>
	 * @return party code
	 */
	public final String getPartyCode() {
		return this.partyCode;
	}

	/**
	 * set the partyCodeConnector.<br>
	 * @param partyCodeConnector party Code Connector
	 */
	public final void setPartyCodeConnector(final int partyCodeConnector) {
		this.partyCodeConnector = partyCodeConnector;
	}

	/**
	 * get the partyCodeConnector.<br>
	 * @return partyCodeConnector
	 */
	public final int getPartyCodeConnector() {
		return this.partyCodeConnector;
	}

	/**
	 * get the route Type.<br>
	 * @return routeType route Type
	 */
	public final int getRouteType() {
		return this.routeType;
	}

	/**
	 * set the route Type.<br>
	 * @param routeType route type
	 */
	public final void setRouteType(final int routeType) {
		this.routeType = routeType;
	}

	/**
	 * set the route Type.<br>
	 * @param routeType route type
	 */
	public final void setRouteType(final RouteType routeType) {

		if (routeType == RouteType.IndividualRoute) {
			this.routeType = SpecifiedValue.IndividualRoute;
		}

		if (routeType == RouteType.CommonRoute) {
			this.routeType = SpecifiedValue.CommonRoute;
		}

		if (routeType == RouteType.SpecialRoute) {
			this.routeType = SpecifiedValue.Special;
		}
	}

	/**
	 * get the approval Company Code.<br>
	 * @return approvalCompanyCode approval Company Code
	 */
	public final String getApprovalCompanyCode() {
		return this.approvalCompanyCode;
	}

	/**
	 * set the approval Company Code.<br>
	 * @param approvalCompanyCode approval Company Code
	 */
	public final void setApprovalCompanyCode(final String approvalCompanyCode) {
		this.approvalCompanyCode = approvalCompanyCode;
	}

	/**
	 * get the approval unit Code.<br>
	 * @return approvalUnitCode approval unit Code
	 */
	public final String getApprovalUnitCode() {
		return this.approvalUnitCode;
	}

	/**
	 * set the approval unit Code.<br>
	 * @param approvalUnitCode approval unit Code
	 */
	public final void setApprovalUnitCode(final String approvalUnitCode) {
		this.approvalUnitCode = approvalUnitCode;
	}

	/**
	 * get the approval User Code.<br>
	 * @return approvalUserCode approval User Code
	 */
	public final String getApprovalUserCode() {
		return this.approvalUserCode;
	}

	/**
	 * set the approval User Code.<br>
	 * @param approvalUserCode approval User Code
	 */
	public final void setApprovalUserCode(final String approvalUserCode) {
		this.approvalUserCode = approvalUserCode;
	}

	/**
	 * get the deputy approval Company Code.<br>
	 * @return deputy approvalCompanyCode
	 */
	public final String getDeputyApprovalCompanyCode() {
		return this.deputyApprovalCompanyCode;
	}

	/**
	 * set the deputy approval Company Code.<br>
	 * @param deputyApprovalCompanyCode deputy approval Company Code
	 */
	public final void setDeputyApprovalCompanyCode(final String deputyApprovalCompanyCode) {
		this.deputyApprovalCompanyCode = deputyApprovalCompanyCode;
	}

	/**
	 * get the deputy approval unit Code.<br>
	 * @return deputy approval unit Code
	 */
	public final String getDeputyApprovalUnitCode() {
		return this.deputyApprovalUnitCode;
	}

	/**
	 * set the deputy approval unit Code.<br>
	 * @param deputyApprovalUnitCode deputy approval unit Code
	 */
	public final void setDeputyApprovalUnitCode(final String deputyApprovalUnitCode) {
		this.deputyApprovalUnitCode = deputyApprovalUnitCode;
	}

	/**
	 * get the deputy approval User Code.<br>
	 * @return deputy Approval User Code
	 */
	public final String getDeputyApprovalUserCode() {
		return this.deputyApprovalUserCode;
	}

	/**
	 * set the deputy approval User Code.<br>
	 * @param deputyApprovalUserCode deputy Approval User Code
	 */
	public final void setDeputyApprovalUserCode(final String deputyApprovalUserCode) {
		this.deputyApprovalUserCode = deputyApprovalUserCode;
	}

	/**
	 * get the delete flag.<br>
	 * @return flag
	 */
	public final boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	/**
	 * set the delete flag.<br>
	 * @param deleteFlag delete flag
	 */
	public final void setDeleteFlag(final boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * get the deputy approval Comment.<br>
	 * @return deputy Approval Comment
	 */
	public final String getDeputyApprovalComment() {
		return this.deputyApprovalComment;
	}

	/**
	 * set the deputy approval Comment.<br>
	 * @param deputyApprovalComment deputy Approval Comment
	 */
	public final void setDeputyApprovalComment(final String deputyApprovalComment) {
		this.deputyApprovalComment = deputyApprovalComment;
	}

	/**
	 * get the function.<br>
	 * @return function
	 */
	public final int getFunction() {
		return this.function;
	}

	/**
	 * set the function.<br>
	 * @param function function
	 */
	public final void setFunction(final int function) {
		this.function = function;
	}

	/**
	 * set the next party code.<br>
	 * @param nextPartyCode next party code
	 */
	public final void setNextPartyCode(final String nextPartyCode) {
		this.nextPartyCode = nextPartyCode;
	}

	/**
	 * get the next party code.<br>
	 * @return next party code
	 */
	public final String getNextPartyCode() {
		return this.nextPartyCode;
	}

	/**
	 * set the party transit code.<br>
	 * @param partyTransitCode party transit code
	 */
	public final void setPartyTransitCode(final String partyTransitCode) {
		this.partyTansitCode = partyTransitCode;
	}

	/**
	 * get the party transit code.<br>
	 * @return party transit code
	 */
	public final String getPartyTransitCode() {
		return this.partyTansitCode;
	}

	/**
	 * set the party Transit code connector.<br>
	 * @param partyTansitCodeConnector party transit code connector
	 */
	public final void setPartyTransitCodeConnector(final int partyTansitCodeConnector) {
		this.partyTansitCodeConnector = partyTansitCodeConnector;
	}

	/**
	 * get the party Transit Code Connector.<br>
	 * @return partyTransitCodeConnector
	 */
	public final int getPartyTransitCodeConnector() {
		return this.partyTansitCodeConnector;
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
