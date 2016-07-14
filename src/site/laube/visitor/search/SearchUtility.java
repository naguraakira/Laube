package site.laube.visitor.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.search.RouteSearchAcceptor;
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

public class SearchUtility {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(SearchUtility.class);

	/**
	 * check of essential items.
	 */
	@SuppressWarnings({ "nls", "static-method" })
	public static final boolean isEmpty(final RouteSearchAcceptor routeSearchAcceptor){

		log.info("[workflowEngine] " + "isEmpty start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "routeSearchAcceptor:" + routeSearchAcceptor);

		if (LaubeUtility.isEmpty(routeSearchAcceptor)) {
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getCompanyCode())) {
			log.debug("[workflowEngine] " + "companyCode : null");
			log.info("[workflowEngine] " + "isEmpty end");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplicationFormCode())) {
			log.debug("[workflowEngine] " + "applicationFormCode : null");
			log.info("[workflowEngine] " + "isEmpty end");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplyCompanyCode())) {
			log.debug("[workflowEngine] " + "applyCompanyCode : null");
			log.info("[workflowEngine] " + "isEmpty end");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplyUnitCode())) {
			log.debug("[workflowEngine] " + "applyUnitCode : null");
			log.info("[workflowEngine] " + "isEmpty end");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplyUserCode())) {
			log.debug("[workflowEngine] " + "applyUserCode : null");
			log.info("[workflowEngine] " + "isEmpty end");
			return true;
		}

		log.info("[workflowEngine] " + "isEmpty end");
		return false;
	}
}
