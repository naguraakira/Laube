package site.laube.acceptor.search;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.SearchSystemAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
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

public final class RouteSearchAcceptor extends SearchSystemAcceptor {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RouteSearchAcceptor.class);

	/**
	 * to manage the screen mode<br>
	 */
	private int screenMode;

	/**
	 * to manage the state of the company name<br>
	 */
	private String companyName;

	/**
	 * to manage the state of the application form name<br>
	 */
	private String applicationFormName;

	/**
	 * to manage the application classification code<br>
	 */
	private String applicationClassificationCode = null;

	/**
	 * to manage the application classification name<br>
	 */
	private String applicationClassificationName = null;

	/**
	 * to manage the apply company name of request.<br>
	 */
	private String applyCompanyName;

	/**
	 * to manage the apply unit name of request.<br>
	 */
	private String applyUnitName;

	/**
	 * to manage the apply user name. of request.<br>
	 */
	private String applyUserName;

	/**
	 * to manage the deputy apply company name of request.<br>
	 */
	private String deputyApplyCompanyName;

	/**
	 * to manage the deputy apply unit name of request.<br>
	 */
	private String deputyApplyUnitName;

	/**
	 * to manage the deputy apply user name. of request.<br>
	 */
	private String deputyApplyUserName;

	/**
	 * to manage the individual Routes.<br>
	 */
	private List<ApprovalRouteInformationAcceptor> individualRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();

	/**
	 * to manage the common Routes.<br>
	 */
	private List<ApprovalRouteInformationAcceptor> commonRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();

	/**
	 * set the screen code.<br>
	 * @param screenMode screen mode
	 */
	public final void setScreenMode(final int screenMode) {
		this.screenMode = screenMode;
	}

	/**
	 * get the screen code.<br>
	 *
	 * @return screen mode.
	 */
	public final  int getScreenMode() {
		return this.screenMode;
	}

	/**
	 * set the company name.<br>
	 * @param companyName company name
	 */
	public final void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}

	/**
	 * get the company name.<br>
	 *
	 * @return company name.
	 */
	public final  String getCompanyName() {
		return this.companyName;
	}

	/**
	 * set the application form name.<br>
	 * @param applicationFormName application form name
	 */
	public final void setApplicationFormName(final String applicationFormName) {
		this.applicationFormName = applicationFormName;
	}

	/**
	 * get the application form name<br>
	 *
	 * @return application form name
	 */
	public final String getApplicationFormName() {
		return this.applicationFormName;
	}

	/**
	 * set the application classification code.<br>
	 * @param applicationClassificationCode application classification code
	 */
	public final void setApplicationClassificationCode(final String applicationClassificationCode) {
		this.applicationClassificationCode = applicationClassificationCode;
	}

	/**
	 * get the application classification code.<br>
	 * @return application classification code
	 */
	public final String getApplicationClassificationCode() {
		return this.applicationClassificationCode;
	}

	/**
	 * set the application classification name.<br>
	 * @param applicationClassificationName application classification name
	 */
	public final void setApplicationClassificationName(final String applicationClassificationName) {
		this.applicationClassificationName = applicationClassificationName;
	}

	/**
	 * get the application classification name.<br>
	 * @return application classification name
	 */
	public final String getApplicationClassificationName() {
		return this.applicationClassificationName;
	}

	/**
	 * set the apply company name of request.<br>
	 * @param applyCompanyName apply company name of request
	 */
	public final void setApplyCompanyName(final String applyCompanyName) {
		this.applyCompanyName = applyCompanyName;
	}

	/**
	 * get the apply company name of request.<br>
	 * @return apply company name of request
	 */
	public final String getApplyCompanyName() {
		return this.applyCompanyName;
	}

	/**
	 * set the apply unit name of request.<br>
	 * @param applyUnitName apply unit name of request
	 */
	public final void setApplyUnitName(final String applyUnitName) {
		this.applyUnitName = applyUnitName;
	}

	/**
	 * get the apply unit name of request.<br>
	 * @return apply unit name of request
	 */
	public final String getApplyUnitName() {
		return this.applyUnitName;
	}

	/**
	 * set the apply user name of request.<br>
	 * @param applyUserName apply user name of request
	 */
	public final void setApplyUserName(final String applyUserName) {
		this.applyUserName = applyUserName;
	}

	/**
	 * get the apply user name of request.<br>
	 * @return apply user name of request
	 */
	public final String getApplyUserName() {
		return this.applyUserName;
	}

	/**
	 * set the deputy apply company name of request.<br>
	 * @param deputyApplyCompanyName deputy apply company name of request
	 */
	public final void setDeputyApplyCompanyName(final String deputyApplyCompanyName) {
		this.deputyApplyCompanyName = deputyApplyCompanyName;
	}

	/**
	 * get the deputy apply company name of request.<br>
	 * @return deputy apply company name of request
	 */
	public final String getDeputyApplyCompanyName() {
		return this.deputyApplyCompanyName;
	}

	/**
	 * set the deputy apply unit name of request.<br>
	 * @param deputyApplyUnitName deputy apply unit name of request
	 */
	public final void setDeputyApplyUnitName(final String deputyApplyUnitName) {
		this.deputyApplyUnitName = deputyApplyUnitName;
	}

	/**
	 * get the deputy apply unit name of request.<br>
	 * @return deputy apply unit name of request
	 */
	public final String getDeputyApplyUnitName() {
		return this.deputyApplyUnitName;
	}

	/**
	 * set the deputy apply user name of request.<br>
	 * @param deputyApplyUserName apply user name of request
	 */
	public final void setDeputyApplyUserName(final String deputyApplyUserName) {
		this.deputyApplyUserName = deputyApplyUserName;
	}

	/**
	 * get the deputy apply user name of request.<br>
	 * @return deputy apply user name of request
	 */
	public final String getDeputyApplyUserName() {
		return this.deputyApplyUserName;
	}

	/**
	 * get the Individual Routes<br>
	 * @return Individual Routes
	 */
	public List<ApprovalRouteInformationAcceptor> getIndividualRoutes() {
		return this.individualRoutes;
	}

	/**
	 * set the Individual Routes.<br>
	 * @param individualRoutes Individual Routes
	 */
	public void setIndividualRoutes(List<ApprovalRouteInformationAcceptor> individualRoutes) {
		this.individualRoutes = individualRoutes;
	}

	/**
	 * get the Common Routes.<br>
	 * @return common Routes
	 */
	public List<ApprovalRouteInformationAcceptor> getCommonRoutes() {
		return this.commonRoutes;
	}

	/**
	 * set the Common Routes.<br>
	 * @param commonRoutes Common Routes
	 */
	public void setCommonRoutes(List<ApprovalRouteInformationAcceptor> commonRoutes) {
		this.commonRoutes = commonRoutes;
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
