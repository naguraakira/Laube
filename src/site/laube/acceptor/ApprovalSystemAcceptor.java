package site.laube.acceptor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
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
public abstract class ApprovalSystemAcceptor extends LaubeAcceptor {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApprovalSystemVisitor.class);

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
	 * set the company code.<br>
	 * @param companyCode company code
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
	 * set the application number of approve.<br>
	 * @param applicationNumber application number of approve
	 */
	public final void setApplicationNumber(final long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * get the application number of approve.<br>
	 * @return application number of approve
	 */
	public final long getApplicationNumber() {
		return this.applicationNumber;
	}

	/**
	 * set the date of approval.<br>
	 * @param approvalDate date of approval
	 */
	public final void setApprovalDate(final String approvalDate) {
		this.approvalDate = approvalDate;
	}

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
	 * set the company code of approval.<br>
	 * @param approvalCompanyCode company code of approval
	 */
	public final void setApprovalCompanyCode(final String approvalCompanyCode) {
		this.approvalCompanyCode = approvalCompanyCode;
	}

	/**
	 * get the company code of approval.<br>
	 * @return company code of approval
	 */
	public final String getApprovalCompanyCode() {
		return this.approvalCompanyCode;
	}

	/**
	 * set the compunitany code of approval.<br>
	 * @param approvalUnitCode unit code of approval
	 */
	public final void setApprovalUnitCode(final String approvalUnitCode) {
		this.approvalUnitCode = approvalUnitCode;
	}

	/**
	 * get the unit code of approval.<br>
	 * @return unit code of approval
	 */
	public final String getApprovalUnitCode() {
		return this.approvalUnitCode;
	}

	/**
	 * set the employee number of approval.<br>
	 * @param approvalUserCode employee number of approval
	 */
	public final void setApprovalUserCode(final String approvalUserCode) {
		this.approvalUserCode = approvalUserCode;
	}

	/**
	 * get the employee number of approval.<br>
	 * @return employee number of approval
	 */
	public final String getApprovalUserCode() {
		return this.approvalUserCode;
	}

	/**
	 * set the comment of approval.<br>
	 * @param comment comment of approval
	 */
	public final void setComment(final String comment) {
		this.comment = comment;
	}

	/**
	 * get the comment of approval.<br>
	 * @return comment of approval
	 */
	public final String getComment() {
		return this.comment;
	}

	/**
	 * set the attachments of approval.<br>
	 * @param appendFileList append file list
	 */
	public void setAppendFileList(List<AppendFile> appendFileList) {
		this.appendFileList = appendFileList;
	}

	/**
	 * get the attachments of approval.<br>
	 * @return appendedList attachments of approval
	 */
	public List<AppendFile> getAppendFileList() {
		return this.appendFileList;
	}

	/**
	 * Inner class for managing the attachment<br>
	 */
	public class AppendFile {

		/**
		 * to manage the approval number of attachments.<br>
		 */
		private int approvalNumber;

		/**
		 * to manage the path of attachments.<br>
		 */
		private String path = null;

		/**
		 * set the approval number of attachments.<br>
		 * @param approvalNumber approval number
		 */
		public final void setApprovalNumber(final int approvalNumber) {
			this.approvalNumber = approvalNumber;
		}

		/**
		 * get the approval number of attachments.<br>
		 * @return approval number number of attachments
		 */
		public final int getApprovalNumber() {
			return this.approvalNumber;
		}

		/**
		 * set the path of attachments.<br>
		 * @param path path of attachments.
		 */
		public final void setPath(final String path) {
			this.path = path;
		}

		/**
		 * get the path of attachments.<br>
		 * @return path path of attachments.
		 */
		public final String getPath() {
			return this.path;
		}
	}

	/**
	 * accept
	 * @param approvalSystemVisitor approval system visitor
	 * @return ResultDto
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public final ResultDto accept(final ApprovalSystemVisitor approvalSystemVisitor) throws LaubeException{

		ResultDto resultDto = approvalSystemVisitor.visit(this);
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

