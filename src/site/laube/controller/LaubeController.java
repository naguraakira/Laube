package site.laube.controller;

import java.io.Serializable;

import site.laube.acceptor.ApprovalSystemAcceptor;
import site.laube.acceptor.RequestSystemAcceptor;
import site.laube.acceptor.SearchSystemAcceptor;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
import site.laube.visitor.ApprovalSystemVisitor;
import site.laube.visitor.RequestSystemVisitor;
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

public final class LaubeController implements Serializable {

	/**
	 * to manage the log.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(LaubeController.class);

	/**
	 * Make the application-based processing.<br>
	 * @param searchSystemAcceptor search system acceptor
	 * @param searchSystemVisitor search system visitor
	 * @return ResultDto
	 */
	@SuppressWarnings("nls")
	public final static ResultDto doSearch(final SearchSystemAcceptor searchSystemAcceptor,SearchSystemVisitor searchSystemVisitor) {

		log.traceStart("doSearch", searchSystemAcceptor, searchSystemVisitor);

		ResultDto resultDto = null;

		try {
			resultDto = searchSystemAcceptor.accept(searchSystemVisitor);

		}catch(final LaubeException e){
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.traceEnd("doSearch");
			return resultDto;

		}catch(final Exception e){
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.traceEnd("doSearch");
			return resultDto;
		}
		log.traceEnd("doSearch");
		return resultDto;
	}

	/**
	 * Make the application-based processing.<br>
	 * @param requestSystemAcceptor request system acceptor
	 * @param requestSystemVisitor request system visitor
	 * @return ResultDto
	 */
	@SuppressWarnings("nls")
	public final static ResultDto doRequest(final RequestSystemAcceptor requestSystemAcceptor,RequestSystemVisitor requestSystemVisitor) {

		log.traceStart("doRequest", requestSystemAcceptor, requestSystemVisitor);

		ResultDto resultDto = null;

		try {
			resultDto = requestSystemAcceptor.accept(requestSystemVisitor);

		}catch(final LaubeException e){
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.traceEnd("doRequest");
			return resultDto;

		}catch(final Exception e){
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.traceEnd("doRequest");
			return resultDto;
		}
		log.traceEnd("doRequest");
		return resultDto;
	}

	/**
	 * make the process of approval.<br>
	 * @param approvalAcceptor approval acceptor
	 * @param approvalSystemVisitor approval system visitor
	 * @return ResultDto
	 */
	@SuppressWarnings("nls")
	public final static ResultDto doApproval(final ApprovalSystemAcceptor approvalAcceptor,ApprovalSystemVisitor approvalSystemVisitor) {

		log.traceStart("doApproval", approvalAcceptor, approvalSystemVisitor);

		ResultDto resultDto = null;

		try {
			resultDto = approvalAcceptor.accept(approvalSystemVisitor);

		}catch(final LaubeException e){
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.traceEnd("doApproval");
			return resultDto;

		}catch(final Exception e){
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.traceEnd("doApproval");
			return resultDto;
		}
		log.traceEnd("doApproval");
		return resultDto;
	}
}
