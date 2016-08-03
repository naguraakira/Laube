package site.laube.acceptor;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
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
@Getter
@Setter
public abstract class RequestSystemAcceptor extends LaubeAcceptor {

	/**
	 * To manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(RequestSystemAcceptor.class);

	/**
	 * to manage the state of the company code<br>
	 */
	private String companyCode;

	/**
	 * to manage the most recent application number.<br>
	 */
	private long reapplicationNumber;

	/**
	 * to manage the application number.<br>
	 */
	private long applicationNumber;

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
	 * get the date of request.<br>
	 *
	 * @return date of request
	 */
	public final String getApplyDate() {

		if (!LaubeUtility.isBlank(this.applyDate)){
			this.applyDate = this.applyDate.trim().replace('-', '/');
		}
		return this.applyDate;
	}

	/**
	 * accept
	 * @param requestSystemVisitor  request system visitor
	 * @return ResultDto
	 * @exception LaubeException return the exception
	 */
	@SuppressWarnings("nls")
	public final ResultDto accept(final RequestSystemVisitor requestSystemVisitor) throws LaubeException{

		log.traceStart("accept", requestSystemVisitor);
		ResultDto resultDto = requestSystemVisitor.visit(this);
		log.traceEnd("accept");
		return resultDto;
	}
}
