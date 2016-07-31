package site.laube.visitor.approval;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.laube.acceptor.ApprovalSystemAcceptor;
import site.laube.acceptor.approval.ApprovalAcceptor;
import site.laube.database.DbConnectManager;
import site.laube.dto.ActivityObjectDto;
import site.laube.dto.AppendedDto;
import site.laube.dto.DeputyApprovelDto;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.model.ActivityObjectModel;
import site.laube.model.AppendedModel;
import site.laube.model.LaubeModel;
import site.laube.modelinterface.ActivityObjectModelInterface;
import site.laube.modelinterface.AppendedModelInterface;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
import site.laube.utility.LaubeProperties;
import site.laube.utility.LaubeUtility;
import site.laube.visitor.ApprovalSystemVisitor;
import site.laube.visitor.VisitorUtility;

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
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(ApprovalVisitor.class);

	/**
	 * Do the application work.<br>
	 * @param approvalSystemAcceptor approval system acceptor
	 * @return ResultDto
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "nls", "unchecked" })
	public ResultDto visit(final ApprovalSystemAcceptor approvalSystemAcceptor) throws LaubeException {

		log.traceStart("visit",approvalSystemAcceptor);

		// create a return information.
		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(approvalSystemAcceptor)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.traceEnd("visit");
			return resultDto;
		}

		// be sure to set this when you reuse a single connection.
		if (!LaubeUtility.isEmpty(approvalSystemAcceptor.getConnection())){
			DbConnectManager.setConnection(approvalSystemAcceptor.getConnection());
		}

		boolean isAutoCommit = false;
		if (LaubeProperties.TRUE.equals(LaubeProperties.getValue("isAutoCommit"))){
			isAutoCommit = true;
		}else{
			isAutoCommit = false;
		}

		final ApprovalAcceptor approvalAcceptor = (ApprovalAcceptor)approvalSystemAcceptor;

		try{
			if (ApprovalUtility.isEmpty(approvalAcceptor)) {
				resultDto.setStatus(false);
				resultDto.setMessageId("E0001");
				log.crush("visit", resultDto.toString());
				return resultDto;
			}

			final long applicationNumber = approvalAcceptor.getApplicationNumber();
			final String companyCode = approvalAcceptor.getCompanyCode();
			String approvalCompanyCode = approvalAcceptor.getApprovalCompanyCode();
			String approvalUnitCode = approvalAcceptor.getApprovalUnitCode();
			String approvalUserCode = approvalAcceptor.getApprovalUserCode();
			final String comment = approvalAcceptor.getComment();
			final List<ApprovalSystemAcceptor.AppendFile> appendFileList = approvalAcceptor.getAppendFileList();

			log.message("visit", "find the activity object.");
			ActivityObjectModelInterface activityObjectModelInterface = new ActivityObjectModel();
			resultDto = activityObjectModelInterface.findByArrival(companyCode, applicationNumber, approvalCompanyCode, approvalUnitCode, approvalUserCode);

			if (LaubeUtility.isEmpty(resultDto)) {
				resultDto = new ResultDto();
				resultDto.setStatus(false);
				resultDto.setMessageId("E1006");
				log.crush("visit", "[resultDto] " + resultDto.toString());
				return resultDto;
			}

			ArrayList<LaubeDto> activityObjectDtos = (ArrayList<LaubeDto>)resultDto.getResultData();
			if (activityObjectDtos.size() == 1) {
				// it gets the approval record.
				ActivityObjectDto activityObjectDto = (ActivityObjectDto)activityObjectDtos.get(0);
				activityObjectDto.setApprovalComment(comment);
				log.message("visit", "update the activity object.");
				resultDto = activityObjectModelInterface.updateByAuthorizerApproval(activityObjectDto);

				if (LaubeUtility.isEmpty(resultDto)) {
					LaubeModel.connection.rollback();
					resultDto = new ResultDto();
					resultDto.setStatus(false);
					resultDto.setMessageId("E1005");
					log.crush("visit", "[resultDto] " + resultDto.toString());
					return resultDto;
				}
			}else{
				log.message("visit", "find the deputy approval master.");
				resultDto = VisitorUtility.getDeputyApprovalList(approvalCompanyCode, approvalUserCode);

				if (LaubeUtility.isEmpty(resultDto)) {
					LaubeModel.connection.rollback();
					log.crush("visit", "[resultDto] " + resultDto.toString());
					return resultDto;
				}

				ArrayList<DeputyApprovelDto> deputyApprovelDtos = null;

				if (resultDto.getResultData() instanceof ArrayList){
					deputyApprovelDtos = (ArrayList<DeputyApprovelDto>)resultDto.getResultData();
				}else{
					LaubeModel.connection.rollback();
					log.crush("visit", "[resultDto] " + resultDto.toString());
					return resultDto;
				}

				for (DeputyApprovelDto deputyApprovelDto : deputyApprovelDtos) {

					log.message("visit", "find the activity object.");
					activityObjectModelInterface = new ActivityObjectModel();
					resultDto = activityObjectModelInterface.findByArrival(companyCode, applicationNumber, deputyApprovelDto.getCompanyCode(), deputyApprovelDto.getUnitCode(), deputyApprovelDto.getUserCode());

					if (LaubeUtility.isEmpty(resultDto)) {
						LaubeModel.connection.rollback();
						log.crush("visit", "[resultDto] " + resultDto.toString());
						return resultDto;
					}

					if (resultDto.getResultData() instanceof ArrayList){
						activityObjectDtos = (ArrayList<LaubeDto>)resultDto.getResultData();
					}else{
						LaubeModel.connection.rollback();
						log.crush("visit", "[resultDto] " + resultDto.toString());
						return resultDto;
					}

					if (activityObjectDtos.size() == 1) {
						// it gets the approval record.
						ActivityObjectDto activityObjectDto = (ActivityObjectDto)activityObjectDtos.get(0);
						activityObjectDto.setApprovalComment(comment);
						activityObjectDto.setDeputyApprovalCompanyCode(deputyApprovelDto.getCompanyCode());
						activityObjectDto.setDeputyApprovalUserCode(deputyApprovelDto.getDeputyApproverlUserCode());
						activityObjectDto.setDeputyApprovalComment(deputyApprovelDto.getDeputyContents());
						log.message("visit", "update the activity object.");
						resultDto = activityObjectModelInterface.updateByAuthorizerApproval(activityObjectDto);

						if (LaubeUtility.isEmpty(resultDto)) {
							LaubeModel.connection.rollback();
							log.crush("visit", "[resultDto] " + resultDto.toString());
							return resultDto;
						}

						approvalCompanyCode = deputyApprovelDto.getCompanyCode();
						approvalUnitCode = deputyApprovelDto.getDeputyApproverlUnitCode();
						approvalUserCode = deputyApprovelDto.getDeputyApproverlUserCode();
						break;
					}
				}
			}

			log.message("visit", "insert the appended object.");
			if (!LaubeUtility.isEmpty(appendFileList)){
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

					if (LaubeUtility.isEmpty(resultDto)) {
						LaubeModel.connection.rollback();
						log.crush("visit", "[resultDto] " + resultDto.toString());
						return resultDto;
					}
				}
			}
			log.message("visit", "circulated to the next approver.");
			resultDto = VisitorUtility.circulatedNextApprover(companyCode, applicationNumber);

			if (LaubeUtility.isEmpty(resultDto)) {
				LaubeModel.connection.rollback();
				log.crush("visit", "[resultDto] " + resultDto.toString());
				return resultDto;
			}

			if (isAutoCommit){
				LaubeModel.connection.commit();
			}else{
				resultDto.setConnection(LaubeModel.connection);
			}

			resultDto.setStatus(true);
			resultDto.setMessageId("N0001");
			resultDto.setResultData(applicationNumber);
			return resultDto;

		}catch(final Exception e){
			log.traceEnd("visit", resultDto);
			throw new LaubeException("visit", e);

		}finally{
			try {
				if (isAutoCommit){
					if (!LaubeUtility.isEmpty(LaubeModel.connection)){
						LaubeModel.connection.close();
					}
				}
				log.traceEnd("visit", resultDto);
			} catch (final SQLException e) {
				log.traceEnd("visit", resultDto);
				throw new LaubeException("visit", e);
			}
		}
	}
}
