package site.laube.visitor.approval;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.ApprovalSystemAcceptor;
import site.laube.acceptor.approval.ApprovalAcceptor;
import site.laube.dto.ActivityObjectDto;
import site.laube.dto.AppendedDto;
import site.laube.dto.DeputyApprovelDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.model.ActivityObjectModel;
import site.laube.model.AppendedModel;
import site.laube.model.DeputyApprovelModel;
import site.laube.model.LaubeModel;
import site.laube.modelinterface.ActivityObjectModelInterface;
import site.laube.modelinterface.AppendedModelInterface;
import site.laube.modelinterface.DeputyApprovelModelInterface;
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

public class ApprovalVisitor extends ApprovalSystemVisitor {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApprovalVisitor.class);

	/**
	 * Do the application work.<br>
	 * @param approvalSystemAcceptor approval system acceptor
	 * @return ResultDto
	 * @exception LaubeException
	 */
	@SuppressWarnings("unchecked")
	public ResultDto visit(final ApprovalSystemAcceptor approvalSystemAcceptor) throws LaubeException {

		log.debug("[workflowEngine] " + "visit start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] "  + "approvalSystemAcceptor:" + approvalSystemAcceptor);

		ApprovalAcceptor approvalAcceptor = (ApprovalAcceptor)approvalSystemAcceptor;

		try{
			// create a return information.
			ResultDto resultDto = new ResultDto();

			// checks of the parameters
			if (approvalAcceptor == null) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0001");
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			boolean result = isNull(approvalAcceptor);

			if (result) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0001");
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			final int applicationNumber = approvalAcceptor.getApplicationNumber();
//			final String approvalDate = approvalAcceptor.getApprovalDate();
			final String companyCode = approvalAcceptor.getCompanyCode();
			String approvalCompanyCode = approvalAcceptor.getApprovalCompanyCode();
			String approvalUnitCode = approvalAcceptor.getApprovalUnitCode();
			String approvalUserCode = approvalAcceptor.getApprovalUserCode();
			final String comment = approvalAcceptor.getComment();
			final List<ApprovalSystemAcceptor.AppendFile> appendFileList = approvalAcceptor.getAppendFileList();

			log.debug("[workflowEngine] " + "find the activity object.");
			ActivityObjectModelInterface activityObjectModelInterface = new ActivityObjectModel();
			resultDto = activityObjectModelInterface.findByArrival(companyCode, applicationNumber, approvalCompanyCode, approvalUnitCode, approvalUserCode);

			if ((resultDto == null)||(!resultDto.isSuccess())||(resultDto.getResultData() == null)) {
				resultDto = new ResultDto();
				resultDto.setStatus(false);
				resultDto.setMessageId("E1006");
				log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
				log.error("[workflowEngine] " + "visit end");
				return resultDto;
			}

			ArrayList<LaubeDto> activityObjectDtos = (ArrayList<LaubeDto>)resultDto.getResultData();
			if (activityObjectDtos.size() == 1) {
				// it gets the approval record.
				ActivityObjectDto activityObjectDto = (ActivityObjectDto)activityObjectDtos.get(0);
				activityObjectDto.setApprovalComment(comment);
				log.debug("[workflowEngine] " + "update the activity object.");
				resultDto = activityObjectModelInterface.updateByAuthorizerApproval(activityObjectDto);

				if ((resultDto == null)||(!resultDto.isSuccess())) {
					resultDto = new ResultDto();
					resultDto.setStatus(false);
					resultDto.setMessageId("E1005");
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}
			}else{
				log.debug("[workflowEngine] " + "find the deputy approval master.");
				final DeputyApprovelModelInterface deputyApprovelModelInterface = new DeputyApprovelModel();
				resultDto = deputyApprovelModelInterface.find(companyCode, approvalUserCode);

				if ((resultDto == null)||(!resultDto.isSuccess())) {
					resultDto = new ResultDto();
					resultDto.setStatus(false);
					resultDto.setMessageId("E1014");
					log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
					log.error("[workflowEngine] " + "visit end");
					return resultDto;
				}

				ArrayList<DeputyApprovelDto> deputyApprovelDtos = (ArrayList<DeputyApprovelDto>)resultDto.getResultData();

				for (DeputyApprovelDto deputyApprovelDto : deputyApprovelDtos) {

					log.debug("[workflowEngine] " + "find the activity object.");
					activityObjectModelInterface = new ActivityObjectModel();
					resultDto = activityObjectModelInterface.findByArrival(companyCode, applicationNumber, deputyApprovelDto.getCompanyCode(), deputyApprovelDto.getUnitCode(), deputyApprovelDto.getUserCode());

					if ((resultDto == null)||(!resultDto.isSuccess())||(resultDto.getResultData() == null)) {
						resultDto = new ResultDto();
						resultDto.setStatus(false);
						resultDto.setMessageId("E1005");
						log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
						log.error("[workflowEngine] " + "visit end");
						return resultDto;
					}

					activityObjectDtos = (ArrayList<LaubeDto>)resultDto.getResultData();
					if (activityObjectDtos.size() == 1) {
						// it gets the approval record.
						ActivityObjectDto activityObjectDto = (ActivityObjectDto)activityObjectDtos.get(0);
						activityObjectDto.setApprovalComment(comment);
						activityObjectDto.setDeputyApprovalCompanyCode(deputyApprovelDto.getCompanyCode());
						activityObjectDto.setDeputyApprovalUserCode(deputyApprovelDto.getDeputyApproverlUserCode());
						activityObjectDto.setDeputyApprovalComment(deputyApprovelDto.getDeputyContents());
						log.debug("[workflowEngine] " + "update the activity object.");
						resultDto = activityObjectModelInterface.updateByAuthorizerApproval(activityObjectDto);

						if ((resultDto == null)||(!resultDto.isSuccess())) {
							resultDto = new ResultDto();
							resultDto.setStatus(false);
							resultDto.setMessageId("E1005");
							log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
							log.error("[workflowEngine] " + "visit end");
							return resultDto;
						}else{
							approvalCompanyCode = deputyApprovelDto.getCompanyCode();
							approvalUnitCode = deputyApprovelDto.getDeputyApproverlUnitCode();
							approvalUserCode = deputyApprovelDto.getDeputyApproverlUserCode();
							break;
						}
					}
				}
			}

			log.debug("[workflowEngine] " + "insert the appended object.");
			if ((appendFileList == null)||(appendFileList.size() == 0)) {
			}else{
				for (ApprovalSystemAcceptor.AppendFile appendFile : appendFileList) {

					AppendedDto appendedDto = new AppendedDto();
					appendedDto.setCompanyCode(companyCode);
					appendedDto.setApplicationNumber(applicationNumber);
					appendedDto.setApprovalCompanyCode(approvalCompanyCode);
					appendedDto.setApprovalUnitCode(approvalUnitCode);
					appendedDto.setApprovalUserCode(approvalUserCode);
					appendedDto.setApprovalPath(appendFile.getPath());

					final AppendedModelInterface appendedModelInterface = new AppendedModel();
					resultDto = appendedModelInterface.insert(appendedDto);

					if ((resultDto == null)||(!resultDto.isSuccess())) {
						resultDto = new ResultDto();
						resultDto.setStatus(false);
						resultDto.setMessageId("E1005");
						log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
						log.error("[workflowEngine] " + "visit end");
						return resultDto;
					}
				}
			}

			log.debug("次の承認者を到着に更新");








			log.debug("最終承認の場合、承認済に更新");




			LaubeModel.connection.commit();

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(applicationNumber);
			log.debug("[workflowEngine] " + "applicationNumber is " + applicationNumber);
			log.debug("[workflowEngine] " + "visit end");
			return resultDto;

		}catch(Exception e){
			log.error("[workflowEngine] " + "visit end");
			throw new LaubeException(e);

		}finally{
			try {
				LaubeModel.connection.close();
				log.error("[workflowEngine] " + "visit end");
			} catch (SQLException e) {
				log.error("[workflowEngine] " + "visit end");
				throw new LaubeException(e);
			}
		}
	}

	/**
	 * check of essential items.
	 */
	@SuppressWarnings({ "nls", "static-method" })
	private final boolean isNull(final ApprovalAcceptor approvalAcceptor){

		log.debug("[workflowEngine] " + "isNull start");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] " + "approvalAcceptor:" + approvalAcceptor);

		if (LaubeUtility.isBlank(approvalAcceptor.getApprovalCompanyCode())) {
			log.debug("[workflowEngine] " + "approvalCompanyCode : null");
			log.debug("[workflowEngine] " + "LaubeApplyVisitor.isNull() end [return]:true");
			return true;
		}

		if (LaubeUtility.isBlank(approvalAcceptor.getApprovalDate())) {
			log.debug("[workflowEngine] " + "approvalDate : null");
			log.debug("[workflowEngine] " + "LaubeApplyVisitor.isNull() end [return]:true");
			return true;
		}

		if (LaubeUtility.isBlank(approvalAcceptor.getApprovalUserCode())) {
			log.debug("[workflowEngine] " + "approvalUserCode : null");
			log.debug("[workflowEngine] " + "LaubeApplyVisitor.isNull() end [return]:true");
			return true;
		}

		if (approvalAcceptor.getApplicationNumber() == 0) {
			log.debug("[workflowEngine] " + "applicationNumber : null");
			log.debug("[workflowEngine] " + "LaubeApplyVisitor.isNull() end [return]:true");
			return true;
		}

		log.debug("[workflowEngine] " + "isNull end");
		return false;
	}
}
