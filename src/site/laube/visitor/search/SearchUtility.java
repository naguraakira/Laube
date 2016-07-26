package site.laube.visitor.search;

import java.util.List;

import site.laube.acceptor.search.RouteSearchAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
import site.laube.utility.LaubeUtility;
import site.laube.utility.SpecifiedValue;

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
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(SearchUtility.class);

	/**
	 * check of essential items.
	 * @param routeSearchAcceptor route search acceptor
	 * @return result
	 */
	@SuppressWarnings({ "nls" })
	public static final boolean isEmpty(final RouteSearchAcceptor routeSearchAcceptor){

		log.traceStart("isEmpty", routeSearchAcceptor);

		if (LaubeUtility.isEmpty(routeSearchAcceptor)) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getCompanyCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplicationFormCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplyCompanyCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplyUnitCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(routeSearchAcceptor.getApplyUserCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		log.traceEnd("isEmpty");
		return false;
	}

	/**
	 * if the applicant is included in the approval route, to exclude from the individual route.<br>
	 * @param applyUserCode apply user code
	 * @param routeSearchAcceptor individual route(updates in this method)
	 */
	@SuppressWarnings("nls")
	protected static final void updateIndividualRoute(final String applyUserCode, final RouteSearchAcceptor routeSearchAcceptor) throws LaubeException {

		log.traceStart("updateIndividualRoute", applyUserCode, routeSearchAcceptor);

		if (LaubeUtility.isEmpty(routeSearchAcceptor)){
			log.traceEnd("updateIndividualRoute");
			return;
		}

		if (LaubeUtility.isEmpty(routeSearchAcceptor.getIndividualRoutes())){
			log.traceEnd("updateIndividualRoute");
			return;
		}

		if (LaubeUtility.isBlank(applyUserCode)){
			log.traceEnd("updateIndividualRoute");
			return;
		}

		for (ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor : routeSearchAcceptor.getIndividualRoutes()) {
			if (!LaubeUtility.isEmpty(approvalRouteInformationAcceptor)){
				if (applyUserCode.equals(approvalRouteInformationAcceptor.getApprovalUserCode())){
					approvalRouteInformationAcceptor.setFunction(SpecifiedValue.Skip);
				}
			}
		}
		log.traceEnd("updateIndividualRoute");
	}

	/**
	 * <br>
	 * @param applyUserCode apply user code
	 * @param routeSearchAcceptor individual route(updates in this method)
	 */
	@SuppressWarnings("nls")
	protected static final boolean isAllSkip(final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptorList) throws LaubeException {

		log.traceStart("isAllSkip", approvalRouteInformationAcceptorList);

		boolean result = true;

		if (LaubeUtility.isEmpty(approvalRouteInformationAcceptorList)){
			log.traceEnd("isAllSkip");
			return result;
		}

		for (ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor : approvalRouteInformationAcceptorList) {
			if (!LaubeUtility.isEmpty(approvalRouteInformationAcceptor)){
				if (SpecifiedValue.Skip != approvalRouteInformationAcceptor.getFunction()){
					result = false;
				}
			}
		}
		log.traceEnd("isAllSkip");
		return result;
	}
}
