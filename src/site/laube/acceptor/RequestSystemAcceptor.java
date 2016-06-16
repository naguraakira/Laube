package site.laube.acceptor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeUtility;
import site.laube.visitor.RequestSystemVisitor;

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

public abstract class RequestSystemAcceptor implements LaubeAcceptor {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RequestSystemAcceptor.class);

	/**
	 * to manage the state of the company code<br>
	 */
	private String companyCode;

	/**
	 * to manage the most recent application number.<br>
	 */
	private int reapplicationNumber;

	/**
	 * to manage the application number.<br>
	 */
	private int applicationNumber;

	/**
	 * to manage the application code of request.<br>
	 */
	private String applicationFormCode;

	/**
	 * to manage the date of request.<br>
	 */
	private String applyDate;

	/**
	 * to manage the apply company code of request.<br>
	 */
	private String applyCompanyCode;

	/**
	 * to manage the department code of request.<br>
	 */
	private String applyUnitCode;

	/**
	 * to manage the employee number of request.<br>
	 */
	private String applyUserCode;

	/**
	 * to manage the deputy apply company code of request.<br>
	 */
	private String deputyApplyCompanyCode;

	/**
	 * to manage the Department code of the proxy applicant of request.<br>
	 */
	private String deputyApplyUnitCode;

	/**
	 * to manage the employee number of the proxy applicant of request.<br>
	 */
	private String deputyApplyUserCode;

	/**
	 * to manage the individual Routes.<br>
	 */
	private List<ApprovalRouteInformationAcceptor> individualRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();

	/**
	 * to manage the common Routes.<br>
	 */
	private List<ApprovalRouteInformationAcceptor> commonRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();

	/**
	 * set the company code.<br>
	 * @param companyCode company code
	 */
	public final void setCompanyCode(final String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * get the company code.<br>
	 *
	 * @return company code.
	 */
	public final  String getCompanyCode() {
		return this.companyCode;
	}

	/**
	 * set the application number.<br>
	 * @param applicationNumber application number
	 */
	public final void setApplicationNumber(final int applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * get the application number<br>
	 *
	 * @return application number
	 */
	public final int getApplicationNumber() {
		return this.applicationNumber;
	}

	/**
	 * set the most recent application number.<br>
	 * @param reapplicationNumber reapplicationNumber
	 */
	public final void setReapplicationNumber(final int reapplicationNumber) {
		this.reapplicationNumber = reapplicationNumber;
	}

	/**
	 * get the most recent application number<br>
	 *
	 * @return most recent application number
	 */
	public final int getReapplicationNumber() {
		return this.reapplicationNumber;
	}

	/**
	 * set the application code of request.<br>
	 * @param applicationFormCode application code of request
	 */
	public final void setApplicationFormCode(final String applicationFormCode) {
		this.applicationFormCode = applicationFormCode;
	}

	/**
	 * get the application code of request.<br>
	 *
	 * @return application code of request
	 */
	public final String getApplicationFormCode() {
		return this.applicationFormCode;
	}

	/**
	 * set the date of request.<br>
	 * @param applyDate date of request
	 */
	public final void setApplyDate(final String applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * get the date of request.<br>
	 *
	 * @return date of request
	 */
	public final String getApplyDate() {

		if (this.applyDate != null){
			this.applyDate = this.applyDate.trim().replace('-', '/');
		}
		return this.applyDate;
	}

	/**
	 * set the apply company code of request.<br>
	 * @param applyCompanyCode apply company code of request
	 */
	public final void setApplyCompanyCode(final String applyCompanyCode) {
		this.applyCompanyCode = applyCompanyCode;
	}

	/**
	 * get the apply company code of request.<br>
	 * @return apply company code of request
	 */
	public final String getApplyCompanyCode() {
		return this.applyCompanyCode;
	}

	/**
	 * set the department code of request.<br>
	 * @param applyUnitCode apply unit code
	 */
	public final void setApplyUnitCode(final String applyUnitCode) {
		this.applyUnitCode = applyUnitCode;
	}

	/**
	 * get the department code of request.<br>
	 * @return department code of request
	 */
	public final String getApplyUnitCode() {
		return this.applyUnitCode;
	}

	/**
	 * set the employee number of request.<br>
	 * @param applyUserCode apply user code
	 */
	public final void setApplyUserCode(final String applyUserCode) {
		this.applyUserCode = applyUserCode;
	}

	/**
	 * get the employee number of request.<br>
	 * @return employee number of request
	 */
	public final String getApplyUserCode() {
		return this.applyUserCode;
	}

	/**
	 * set the deputy apply company code of request.<br>
	 * @param deputyApplyCompanyCode deputy apply company code of request
	 */
	public final void setDeputyApplyCompanyCode(final String deputyApplyCompanyCode) {
		this.deputyApplyCompanyCode = deputyApplyCompanyCode;
	}

	/**
	 * get the deputy apply company code of request.<br>
	 * @return deputy apply company code of request
	 */
	public final String getDeputyApplyCompanyCode() {
		return this.deputyApplyCompanyCode;
	}

	/**
	 * set the Department code of the proxy applicant of request.<br>
	 * @param deputyApplyUnitCode deputy apply unit code
	 */
	public final void setDeputyApplyUnitCode(final String deputyApplyUnitCode) {
		this.deputyApplyUnitCode = deputyApplyUnitCode;
	}

	/**
	 * get the Department code of the proxy applicant of request.<br>
	 * @return Department code of the proxy applicant of request
	 */
	public final String getDeputyApplyUnitCode() {
		return this.deputyApplyUnitCode;
	}

	/**
	 * set the employee number of the proxy applicant of request.<br>
	 * @param deputyApplyUserCode deputy apply user code
	 */
	public final void setDeputyApplyUserCode(final String deputyApplyUserCode) {
		this.deputyApplyUserCode = deputyApplyUserCode;
	}

	/**
	 * get the employee number of the proxy applicant of request.<br>
	 * @return employee number of the proxy applicant of request
	 */
	public final String getDeputyApplyUserCode() {
		return this.deputyApplyUserCode;
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
	 * accept
	 * @param requestSystemVisitor  request system visitor
	 * @return ResultDto
	 * @exception LaubeException return the exception
	 */
	public final ResultDto accept(final RequestSystemVisitor requestSystemVisitor) throws LaubeException{

		ResultDto resultDto = requestSystemVisitor.visit(this);
		log.debug("[workflowEngine]" + resultDto.toString());
		return resultDto;
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
