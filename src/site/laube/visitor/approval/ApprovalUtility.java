package site.laube.visitor.approval;

import site.laube.acceptor.ApprovalSystemAcceptor;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
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

public class ApprovalUtility {

	/**
	 * To manage the log object.<br>
	 */
	private static LaubeLogger log = new LaubeLogger(LaubeLoggerFactory.getLogger(ApprovalUtility.class));

	/**
	 * check of essential items.
	 */
	@SuppressWarnings("nls")
	protected static final boolean isEmpty(final ApprovalSystemAcceptor approvalSystemAcceptor){

		log.traceStart("isEmpty", approvalSystemAcceptor);

		if (LaubeUtility.isEmpty(approvalSystemAcceptor)) {
			return true;
		}

		if (LaubeUtility.isBlank(approvalSystemAcceptor.getApprovalCompanyCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(approvalSystemAcceptor.getApprovalDate())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(approvalSystemAcceptor.getApprovalUserCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (approvalSystemAcceptor.getApplicationNumber() == 0) {
			log.traceEnd("isEmpty");
			return true;
		}

		log.traceEnd("isEmpty");
		return false;
	}
}
