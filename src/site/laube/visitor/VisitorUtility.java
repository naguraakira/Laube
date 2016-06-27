package site.laube.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.model.DeputyApprovelModel;
import site.laube.modelinterface.DeputyApprovelModelInterface;
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

public final class VisitorUtility {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RequestSystemVisitor.class);

	/**
	 * <br>
	 * @param companyCode company code
	 * @param approvalUserCode approval user code
	 * @return ResultDto
	 * @exception LaubeException
	 */
	public static final ResultDto getDeputyApprovalList(final String companyCode, final String approvalUserCode) throws LaubeException {

		log.debug("[workflowEngine] " + "getDeputyApprovalList start.");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] "  + "companyCode:" + companyCode);
		log.debug("[workflowEngine] "  + "approvalUserCode:" + approvalUserCode);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(companyCode)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "visit end");
			return resultDto;
		}

		if (LaubeUtility.isEmpty(approvalUserCode)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "visit end");
			return resultDto;
		}

		final DeputyApprovelModelInterface deputyApprovelModelInterface = new DeputyApprovelModel();
		resultDto = deputyApprovelModelInterface.find(companyCode, approvalUserCode);

		if (!resultDto.isSuccess()) {
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.error("[workflowEngine] " + "visit end");
			return resultDto;
		}
		return resultDto;
	}

	/**
	 * update the next approver in waiting for approval. In addition, in the case was the final approval who will update the application object table to the approved.<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @return ResultDto
	 * @exception LaubeException
	 */
	public static final ResultDto circulatedNextApprover(final String companyCode, final int applicationNumber) throws LaubeException {

		log.debug("[workflowEngine] " + "circulatedNextApprover start.");
		log.debug("[workflowEngine] " + "[argument]");
		log.debug("[workflowEngine] "  + "companyCode:" + companyCode);
		log.debug("[workflowEngine] "  + "applicationNumber:" + applicationNumber);

		ResultDto resultDto = new ResultDto();

		if (LaubeUtility.isEmpty(companyCode)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "visit end");
			return resultDto;
		}

		if (LaubeUtility.isEmpty(applicationNumber)){
			resultDto.setStatus(false);
			resultDto.setMessageId("E0001");
			log.error("[workflowEngine] " + "visit end");
			return resultDto;
		}














		if (!resultDto.isSuccess()) {
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E1999");
			log.error("[workflowEngine] " + "[resultDto]" + resultDto.toString());
			log.error("[workflowEngine] " + "visit end");
			return resultDto;
		}









		log.debug("[workflowEngine] " + "circulatedNextApprover end.");
		return resultDto;
	}
}
