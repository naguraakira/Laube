package site.laube.visitor.request;

import java.util.ArrayList;
import java.util.List;

import site.laube.acceptor.RequestSystemAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.dto.ActivityObjectDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
import site.laube.utility.LaubeUtility;
import site.laube.utility.SpecifiedValue;
import site.laube.utility.type.ApprovalFunction;
import site.laube.utility.type.Connector;
import site.laube.utility.type.RouteType;
import site.laube.utility.type.UserStatus;

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

public class RequestUtility {

	/**
	 * To manage the log object.<br>
	 */
	private static LaubeLogger log = new LaubeLogger(LaubeLoggerFactory.getLogger(RequestUtility.class));

	/**
	 * check activity status.<br>
	 * @param activityObjectDtoList activity Object Dto List
	 * @return boolean
	 */
	@SuppressWarnings("nls")
	protected static final boolean checkActivityStatus(final List<ActivityObjectDto> activityObjectDtoList) {

		log.traceStart("checkActivityStatus", activityObjectDtoList);

		boolean result = true;

		if (LaubeUtility.isEmpty(activityObjectDtoList)) {
			log.traceEnd("checkActivityStatus");
			return false;
		}

		for(ActivityObjectDto activityObjectDto : activityObjectDtoList) {
			if (LaubeUtility.isEmpty(activityObjectDto)) {
				log.traceEnd("checkActivityStatus");
				return false;
			}

			if (UserStatus.Authorizer_Untreated.toInt() == activityObjectDto.getActivityStatus()) {
				log.traceEnd("checkActivityStatus");
				return false;
			}

			if (UserStatus.Arrival.toInt() == activityObjectDto.getActivityStatus()) {
				if (ApprovalFunction.FunctionConfirmation.toInt() != activityObjectDto.getFunction()) {
					log.traceEnd("checkActivityStatus");
					return false;
				}
			}

			if (UserStatus.Hold.toInt() == activityObjectDto.getActivityStatus()) {
				log.traceEnd("checkActivityStatus");
				return false;
			}
		}
		log.traceEnd("checkActivityStatus");
		return result;
	}

	/**
	 * check of essential items.
	 * @param requestSystemAcceptor request System Acceptor
	 * @return result
	 */
	@SuppressWarnings("nls")
	protected static final boolean isEmpty(final RequestSystemAcceptor requestSystemAcceptor){

		log.traceStart("isEmpty", requestSystemAcceptor);

		if (LaubeUtility.isEmpty(requestSystemAcceptor)) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getCompanyCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getApplicationFormCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getApplyDate())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getApplyCompanyCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getApplyUnitCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getApplyUserCode())) {
			log.traceEnd("isEmpty");
			return true;
		}

		log.traceEnd("isEmpty");
		return false;
	}

	/**
	 * copy the value of the Acceptor in Dto.<br>
	 * @param companyCode company Code
	 * @param applyUserCode apply User Code
	 * @param applicationNumber application Number
	 * @param approvalRouteInformationAcceptor approval Route Information Acceptor
	 * @return List<ActivityObjectDto> result
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	protected static final List<ActivityObjectDto> copyToActivityDto(
			final String companyCode,
			final String applyUserCode,
			final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptor
			) throws LaubeException {

		log.traceStart("copyToActivityDto", companyCode, applyUserCode, approvalRouteInformationAcceptor);

		List<ActivityObjectDto> activityObjectDtos = new ArrayList<ActivityObjectDto>();

		int row = 0;

		if (!LaubeUtility.isEmpty(approvalRouteInformationAcceptor)){
			for(ApprovalRouteInformationAcceptor route : approvalRouteInformationAcceptor) {
				ActivityObjectDto activityObjectDto = new ActivityObjectDto();
				activityObjectDto.setCompanyCode(companyCode);
				int activityObjectCode = ++row;
				activityObjectDto.setActivityObjectCode(activityObjectCode);
				activityObjectDto.setPartyCode(route.getPartyCode());
				activityObjectDto.setPartyCodeConnector(route.getPartyCodeConnector());
				activityObjectDto.setRouteType(route.getRouteType());
				activityObjectDto.setApprovalCompanyCode(route.getApprovalCompanyCode());
				activityObjectDto.setApprovalUnitCode(route.getApprovalUnitCode());
				activityObjectDto.setApprovalUserCode(route.getApprovalUserCode());
				activityObjectDto.setDeputyApprovalCompanyCode(route.getDeputyApprovalCompanyCode());
				activityObjectDto.setDeputyApprovalUserCode(route.getDeputyApprovalUserCode());
				activityObjectDto.setDeputyApprovalComment(route.getDeputyApprovalComment());
				activityObjectDto.setFunction(route.getFunction());
				activityObjectDto.setNextPartyCode(route.getNextPartyCode());
				activityObjectDto.setPartyTransitCode(route.getPartyTransitCode());
				activityObjectDto.setPartyTransitCodeConnector(route.getPartyTransitCodeConnector());
				activityObjectDto.setReachingDate(null);
				activityObjectDto.setProcessDate(null);
				activityObjectDto.setActivityStatus(UserStatus.Authorizer_Untreated.toInt());
				activityObjectDto.setApprovalComment(null);
				activityObjectDto.setCreateUserId(applyUserCode);
				activityObjectDto.setUpdateUserId(applyUserCode);
				activityObjectDtos.add(activityObjectDto);
			}
		}
		log.traceEnd("copyToActivityDto");
		return activityObjectDtos;
	}

	/**
	 * check of routes.
	 * @param approvalRouteInformationAcceptors
	 * @return result
	 */
	@SuppressWarnings("nls")
	protected static final boolean checkRoute(final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors) {

		log.traceStart("checkRoute", approvalRouteInformationAcceptors);

		boolean result = true;
		if (!LaubeUtility.isEmpty(approvalRouteInformationAcceptors)){
			for(ApprovalRouteInformationAcceptor route : approvalRouteInformationAcceptors) {
				if (!LaubeUtility.isEmpty(route)) {
					result = checkItem(approvalRouteInformationAcceptors, route);
					if (!result) {
						break;
					}
				}
			}
		}

		// check chain
		if (!LaubeUtility.isEmpty(approvalRouteInformationAcceptors)){

			ArrayList<String> keywords = new ArrayList<String>();

			for(ApprovalRouteInformationAcceptor route : approvalRouteInformationAcceptors) {
				if (!LaubeUtility.isEmpty(route)) {
					keywords.add(route.getPartyCode() + route.getNextPartyCode());
					log.message("checkRoute","keywords:" + keywords);
					if (!checkNextParty(approvalRouteInformationAcceptors, route.getNextPartyCode(), keywords)) {
						log.crush("checkRoute","error next party");
						log.traceEnd("checkRoute");
						return false;
					}else{
						log.message("checkRoute","initialize keywords");
						keywords = new ArrayList<String>();
					}
				}
			}
		}

		log.traceEnd("checkRoute");
		return result;
	}

	/**
	 * check of item.
	 * @param approvalRouteInformationAcceptors
	 * @param target
	 * @return result
	 */
	@SuppressWarnings({ "nls" })
	private static final boolean checkItem(final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors, final ApprovalRouteInformationAcceptor target) {

		log.traceStart("checkItem", approvalRouteInformationAcceptors, target);

		if ((LaubeUtility.isEmpty(approvalRouteInformationAcceptors))||(LaubeUtility.isEmpty(target))) {
			log.traceEnd("checkItem");
			return false;
		}

		// check party code
		if (LaubeUtility.isBlank(target.getPartyCode())) {
			log.crush("checkItem","error party code");
			log.traceEnd("checkItem");
			return false;
		}

		// check of party code connector
		boolean isUnspecified        = target.getPartyCodeConnector() == Connector.Unspecified.toInt();
		boolean isLogicalSum         = target.getPartyCodeConnector() == Connector.LogicalSum.toInt();
		boolean isLogicalProduct     = target.getPartyCodeConnector() == Connector.LogicalProduct.toInt();
		final boolean isPartyCodeConnector = isUnspecified || isLogicalSum || isLogicalProduct;

		if (!isPartyCodeConnector) {
			log.crush("checkItem","error party code connector");
			log.traceEnd("checkItem");
			return false;
		}

		// check of route type
		final boolean isIndividualRoute = target.getRouteType() == RouteType.IndividualRoute.toInt();
		final boolean isCommonRoute     = target.getRouteType() == RouteType.CommonRoute.toInt();
		final boolean isSpecial         = target.getRouteType() == RouteType.SpecialRoute.toInt();
		final boolean isRouteType       = isIndividualRoute || isCommonRoute || isSpecial;

		if (!isRouteType) {
			log.crush("checkItem","error route type");
			log.traceEnd("checkItem");
			return false;
		}

		// check of approval company code
		if (LaubeUtility.isBlank(target.getApprovalCompanyCode())) {
			log.crush("checkItem","error approval company code");
			log.traceEnd("checkItem");
			return false;
		}

		// check of approval unit code
		if (LaubeUtility.isBlank(target.getApprovalUnitCode())) {
			log.crush("checkItem","error approval unit code");
			log.traceEnd("checkItem");
			return false;
		}

		// check of approval user code
		if (LaubeUtility.isBlank(target.getApprovalUserCode())) {
			log.crush("checkItem","error approval user code");
			log.traceEnd("checkItem");
			return false;
		}

		// check of deputy approval
		final boolean isDeputyApprovalCompanyCode = !LaubeUtility.isBlank(target.getDeputyApprovalCompanyCode());
		final boolean isDeputyApprovalUnitCode    = !LaubeUtility.isBlank(target.getDeputyApprovalUnitCode());
		final boolean isDeputyApprovalUserCode    = !LaubeUtility.isBlank(target.getDeputyApprovalUserCode());
		final boolean isDeputy = isDeputyApprovalCompanyCode && isDeputyApprovalUnitCode && isDeputyApprovalUserCode;
		final boolean isNotDeputy = !isDeputyApprovalCompanyCode && !isDeputyApprovalUnitCode && !isDeputyApprovalUserCode;
		if (!((isDeputy)||(isNotDeputy))) {
			log.crush("checkItem","error deputy approval");
			log.traceEnd("checkItem");
			return false;
		}
		if (isDeputy) {
			if (LaubeUtility.isBlank(target.getDeputyApprovalComment())){
				log.crush("checkItem","error deputy comment");
				log.traceEnd("checkItem");
				return false;
			}
		}
		if (isNotDeputy) {
			if (!LaubeUtility.isBlank(target.getDeputyApprovalComment())){
				log.crush("checkItem","error deputy comment");
				log.traceEnd("checkItem");
				return false;
			}
		}

		// check of function
		final boolean isExamination           = target.getFunction() == ApprovalFunction.Examination.toInt();
		final boolean isFunctionConfirmation  = target.getFunction() == ApprovalFunction.FunctionConfirmation.toInt();
		final boolean isFunction       = isExamination || isFunctionConfirmation;
		if (!isFunction) {
			log.crush("checkItem","error function");
			log.traceEnd("checkItem");
			return false;
		}

		// check next party code
		if (LaubeUtility.isBlank(target.getNextPartyCode())) {
			log.crush("checkItem","error next party code");
			log.traceEnd("checkItem");
			return false;
		}

		// check of party transit code
		if (LaubeUtility.isBlank(target.getPartyTransitCode())){
			log.crush("checkItem","error party transit code");
			log.traceEnd("checkItem");
			return false;
		}

		// check of party code connector
		isUnspecified        = target.getPartyTransitCodeConnector() == Connector.Unspecified.toInt();
		isLogicalSum         = target.getPartyTransitCodeConnector() == Connector.LogicalSum.toInt();
		isLogicalProduct     = target.getPartyTransitCodeConnector() == Connector.LogicalProduct.toInt();
		final boolean isPartyTransitCodeConnector = isUnspecified || isLogicalSum || isLogicalProduct;
		if (!isPartyTransitCodeConnector) {
			log.crush("checkItem","error party code connector");
			log.traceEnd("checkItem");
			return false;
		}
		log.traceEnd("checkItem");
		return true;
	}

	/**
	 * check of next party.(recursive processing)
	 * @param approvalRouteInformationAcceptors
	 * @param nextPartyCode next party code
	 * @param keywords partyCode + nextpartyCode
	 * @return result
	 */
	@SuppressWarnings("nls")
	private static final boolean checkNextParty(final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors, final String nextPartyCode, ArrayList<String> keywords) {

		log.traceStart("checkNextParty", approvalRouteInformationAcceptors, nextPartyCode, keywords);

		if ((LaubeUtility.isEmpty(approvalRouteInformationAcceptors))||(LaubeUtility.isBlank(nextPartyCode))||(LaubeUtility.isEmpty(keywords))) {
			log.traceEnd("checkNextParty");
			return true;
		}

		for(ApprovalRouteInformationAcceptor route : approvalRouteInformationAcceptors) {
			// discover the next party code
			if (nextPartyCode.equals(route.getPartyCode())) {
				if (route.getNextPartyCode().equals(SpecifiedValue.END)) {
					log.message("checkNextParty", "success to find the final party");
					log.traceEnd("checkNextParty");
					return true;
				}
				// if the next party code is known of the party error
				for(String keyword : keywords) {
					if ((route.getPartyCode() + route.getNextPartyCode()).equals(keyword)) {
						log.crush("checkNextParty", "already add party code. party code is [" + route.getPartyCode() + route.getNextPartyCode() + "]");
						log.traceEnd("checkNextParty");
						return false;
					}
				}
				keywords.add(route.getPartyCode() + route.getNextPartyCode());

				boolean result = checkNextParty(approvalRouteInformationAcceptors, route.getNextPartyCode(), keywords);
				if (!result) {
					log.crush("checkNextParty", "error checkNextParty");
					log.traceEnd("checkNextParty");
					return false;
				}else{
					keywords.clear();
				}
			}
		}
		log.traceEnd("checkNextParty");
		return true;
	}
}
