package site.laube.acceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeUtility;
import site.laube.visitor.SearchSystemVisitor;

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

public abstract class SearchSystemAcceptor extends LaubeAcceptor {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(SearchSystemAcceptor.class);

	/**
	 * to manage the state of the company code<br>
	 */
	private String companyCode;

	/**
	 * to manage the application code of request.<br>
	 */
	private String applicationFormCode;

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
	 * set the apply unit code.<br>
	 * @param applyUnitCode apply unit code
	 */
	public final void setApplyUnitCode(final String applyUnitCode) {
		this.applyUnitCode = applyUnitCode;
	}

	/**
	 * get the apply unit code.<br>
	 * @return apply unit code
	 */
	public final String getApplyUnitCode() {
		return this.applyUnitCode;
	}

	/**
	 * set the apply user code.<br>
	 * @param applyUserCode apply user code
	 */
	public final void setApplyUserCode(final String applyUserCode) {
		this.applyUserCode = applyUserCode;
	}

	/**
	 * get the apply user code.<br>
	 * @return apply user code
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
	 * set the deputy apply unit code.<br>
	 * @param deputyApplyUnitCode deputy apply unit code
	 */
	public final void setDeputyApplyUnitCode(final String deputyApplyUnitCode) {
		this.deputyApplyUnitCode = deputyApplyUnitCode;
	}

	/**
	 * get the deputy apply unit code.<br>
	 * @return deputy apply unit code
	 */
	public final String getDeputyApplyUnitCode() {
		return this.deputyApplyUnitCode;
	}

	/**
	 * set the deputy apply user code.<br>
	 * @param deputyApplyUserCode deputy apply user code
	 */
	public final void setDeputyApplyUserCode(final String deputyApplyUserCode) {
		this.deputyApplyUserCode = deputyApplyUserCode;
	}

	/**
	 * get the deputy apply user code.<br>
	 * @return deputy apply user code
	 */
	public final String getDeputyApplyUserCode() {
		return this.deputyApplyUserCode;
	}

	/**
	 * accept
	 * @param searchSystemVisitor  search system visitor
	 * @return ResultDto
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	public final ResultDto accept(final SearchSystemVisitor searchSystemVisitor) throws LaubeException{

		ResultDto resultDto = searchSystemVisitor.visit(this);
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
