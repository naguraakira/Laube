package site.laube.acceptor;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
import site.laube.utility.LaubeUtility;
import site.laube.visitor.ApprovalSystemVisitor;

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
public abstract class ApprovalSystemAcceptor extends LaubeAcceptor {

	/**
	 * To manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(ApprovalSystemAcceptor.class);

	/**
	 * to manage the company code.<br>
	 */
	private String companyCode;

	/**
	 *  to manage the application number.<br>
	 */
	private long applicationNumber;

	/**
	 * to manage the date of approval.<br>
	 */
	private String approvalDate;

	/**
	 * to manage the company code of approval.<br>
	 */
	private String approvalCompanyCode;

	/**
	 * to manage the unit code of approval.<br>
	 */
	private String approvalUnitCode;

	/**
	 * to manage the employee number of approval.<br>
	 */
	private String approvalUserCode;

	/**
	 * to manage the comment of approval.<br>
	 */
	private String comment;

	/**
	 * to manage the attachments of approval.<br>
	 */
	private List<AppendFile> appendFileList = new ArrayList<AppendFile>();

	/**
	 * get the date of approval.<br>
	 *
	 * @return date of approval
	 */
	public final String getApprovalDate() {

		if (!LaubeUtility.isBlank(this.approvalDate)){
			this.approvalDate = this.approvalDate.trim().replace('-', '/');
		}
		return this.approvalDate;
	}

	/**
	 * Inner class for managing the attachment<br>
	 */
	@Getter
	@Setter
	public final class AppendFile {

		/**
		 * to manage the approval number of attachments.<br>
		 */
		private int approvalNumber;

		/**
		 * to manage the path of attachments.<br>
		 */
		private String path = null;
	}

	/**
	 * accept
	 * @param approvalSystemVisitor approval system visitor
	 * @return ResultDto
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public final ResultDto accept(final ApprovalSystemVisitor approvalSystemVisitor) throws LaubeException{

		log.traceStart("accept", approvalSystemVisitor);
		ResultDto resultDto = approvalSystemVisitor.visit(this);
		log.traceEnd("accept");
		return resultDto;
	}
}