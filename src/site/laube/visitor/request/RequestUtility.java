package site.laube.visitor.request;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.RequestSystemAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.dto.ActivityObjectDto;
import site.laube.exception.LaubeException;
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

public class RequestUtility {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RequestUtility.class);

	/**
	 * check activity status.<br>
	 * @param activityObjectDtoList activity Object Dto List
	 * @return boolean
	 */
	protected static final boolean checkActivityStatus(final List<ActivityObjectDto> activityObjectDtoList) {

		log.info("[workflowEngine] " + "checkActivityStatus start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "activityObjectDtoList:" + activityObjectDtoList);

		boolean result = true;

		if (LaubeUtility.isEmpty(activityObjectDtoList)) {
			log.info("[workflowEngine] " + "checkActivityStatus end");
			return false;
		}

		for(ActivityObjectDto activityObjectDto : activityObjectDtoList) {
			if (LaubeUtility.isEmpty(activityObjectDto)) {
				log.info("[workflowEngine] " + "checkActivityStatus end");
				return false;
			}

			if (SpecifiedValue.Authorizer_Untreated == activityObjectDto.getActivityStatus()) {
				log.info("[workflowEngine] " + "checkActivityStatus end");
				return false;
			}

			if (SpecifiedValue.Arrival == activityObjectDto.getActivityStatus()) {
				if (SpecifiedValue.FunctionConfirmation != activityObjectDto.getFunction()) {
					log.info("[workflowEngine] " + "checkActivityStatus end");
					return false;
				}
			}

			if (SpecifiedValue.Hold == activityObjectDto.getActivityStatus()) {
				log.info("[workflowEngine] " + "checkActivityStatus end");
				return false;
			}
		}
		log.info("[workflowEngine] " + "checkActivityStatus end");
		return result;
	}

	/**
	 * check of essential items.
	 * @param requestSystemAcceptor request System Acceptor
	 * @return result
	 */
	protected static final boolean isEmpty(final RequestSystemAcceptor requestSystemAcceptor){

		log.info("[workflowEngine] " + "isNull start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "requestSystemAcceptor:" + requestSystemAcceptor);

		if (LaubeUtility.isEmpty(requestSystemAcceptor)) {
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getCompanyCode())) {
			log.info("[workflowEngine] " + "isNull end");
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getApplicationFormCode())) {
			log.info("[workflowEngine] " + "isNull end");
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getApplyDate())) {
			log.info("[workflowEngine] " + "isNull end");
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getApplyCompanyCode())) {
			log.info("[workflowEngine] " + "isNull end");
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getApplyUnitCode())) {
			log.info("[workflowEngine] " + "isNull end");
			return true;
		}

		if (LaubeUtility.isBlank(requestSystemAcceptor.getApplyUserCode())) {
			log.info("[workflowEngine] " + "isNull end");
			return true;
		}

		log.info("[workflowEngine] " + "isNull end");
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
	protected static final List<ActivityObjectDto> copyToActivityDto(
			final String companyCode,
			final String applyUserCode,
			final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptor
			) throws LaubeException {

		log.info("[workflowEngine] " + "copyToActivityDto start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "companyCode:" + companyCode);
		log.info("[workflowEngine] " + "applyUserCode:" + applyUserCode);
		log.info("[workflowEngine] " + "approvalRouteInformationAcceptor:" + approvalRouteInformationAcceptor);

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
				activityObjectDto.setActivityStatus(SpecifiedValue.Authorizer_Untreated);
				activityObjectDto.setApprovalComment(null);
				activityObjectDto.setCreateUserId(applyUserCode);
				activityObjectDto.setUpdateUserId(applyUserCode);
				activityObjectDtos.add(activityObjectDto);
			}
		}
		log.info("[workflowEngine] " + "copyToActivityDto end");
		return activityObjectDtos;
	}

	/**
	 * check of routes.
	 * @param approvalRouteInformationAcceptors
	 * @return result
	 */
	protected static final boolean checkRoute(final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors) {

		log.info("[workflowEngine] " + "checkRoute start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "approvalRouteInformationAcceptors:" + approvalRouteInformationAcceptors);

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
					log.debug("[workflowEngine] " + "keywords:" + keywords);
					if (!checkNextParty(approvalRouteInformationAcceptors, route.getNextPartyCode(), keywords)) {
						log.error("[workflowEngine] " + "error next party");
						log.info("[workflowEngine] " + "checkRoute end");
						return false;
					}else{
						log.debug("[workflowEngine] " + "initialize keywords");
						keywords = new ArrayList<String>();
					}
				}
			}
		}

		log.debug("[workflowEngine] " + "checkRoute end");
		return result;
	}

	/**
	 * check of item.
	 * @param approvalRouteInformationAcceptors
	 * @param target
	 * @return result
	 */
	@SuppressWarnings({ "nls", "static-method" })
	private static final boolean checkItem(final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors, final ApprovalRouteInformationAcceptor target) {

		log.info("[workflowEngine] " + "checkItem start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "approvalRouteInformationAcceptors:" + approvalRouteInformationAcceptors);
		log.info("[workflowEngine] " + "target:" + target);

		if ((LaubeUtility.isEmpty(approvalRouteInformationAcceptors))||(LaubeUtility.isEmpty(target))) {
			return false;
		}

		// check party code
		if (LaubeUtility.isBlank(target.getPartyCode())) {
			log.error("[workflowEngine] " + "error party code");
			log.info("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of party code connector
		boolean isUnspecified        = target.getPartyCodeConnector() == SpecifiedValue.Unspecified;
		boolean isLogicalSum         = target.getPartyCodeConnector() == SpecifiedValue.LogicalSum;
		boolean isLogicalProduct     = target.getPartyCodeConnector() == SpecifiedValue.LogicalProduct;
		final boolean isPartyCodeConnector = isUnspecified || isLogicalSum || isLogicalProduct;

		if (!isPartyCodeConnector) {
			log.error("[workflowEngine] " + "error party code connector");
			log.info("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of route type
		final boolean isIndividualRoute = target.getRouteType() == SpecifiedValue.IndividualRoute;
		final boolean isCommonRoute     = target.getRouteType() == SpecifiedValue.CommonRoute;
		final boolean isSpecial         = target.getRouteType() == SpecifiedValue.Special;
		final boolean isRouteType       = isIndividualRoute || isCommonRoute || isSpecial;

		if (!isRouteType) {
			log.error("[workflowEngine] " + "error route type");
			log.info("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of approval company code
		if (LaubeUtility.isBlank(target.getApprovalCompanyCode())) {
			log.error("[workflowEngine] " + "error approval company code");
			log.info("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of approval unit code
		if (LaubeUtility.isBlank(target.getApprovalUnitCode())) {
			log.error("[workflowEngine] " + "error approval unit code");
			log.info("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of approval user code
		if (LaubeUtility.isBlank(target.getApprovalUserCode())) {
			log.error("[workflowEngine] " + "error approval user code");
			log.info("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of deputy approval
		final boolean isDeputyApprovalCompanyCode = !LaubeUtility.isBlank(target.getDeputyApprovalCompanyCode());
		final boolean isDeputyApprovalUnitCode    = !LaubeUtility.isBlank(target.getDeputyApprovalUnitCode());
		final boolean isDeputyApprovalUserCode    = !LaubeUtility.isBlank(target.getDeputyApprovalUserCode());
		final boolean isDeputy = isDeputyApprovalCompanyCode && isDeputyApprovalUnitCode && isDeputyApprovalUserCode;
		final boolean isNotDeputy = !isDeputyApprovalCompanyCode && !isDeputyApprovalUnitCode && !isDeputyApprovalUserCode;
		if (!((isDeputy)||(isNotDeputy))) {
			log.error("[workflowEngine] " + "error deputy approval");
			log.info("[workflowEngine] " + "checkItem end");
			return false;
		}
		if (isDeputy) {
			if (LaubeUtility.isBlank(target.getDeputyApprovalComment())){
				log.error("[workflowEngine] " + "error deputy comment");
				log.info("[workflowEngine] " + "checkItem end");
				return false;
			}
		}
		if (isNotDeputy) {
			if (!LaubeUtility.isBlank(target.getDeputyApprovalComment())){
				log.error("[workflowEngine] " + "error deputy comment");
				log.info("[workflowEngine] " + "checkItem end");
				return false;
			}
		}

		// check of function
		final boolean isExamination           = target.getFunction() == SpecifiedValue.Examination;
		final boolean isFunctionConfirmation  = target.getFunction() == SpecifiedValue.FunctionConfirmation;
		final boolean isFunction       = isExamination || isFunctionConfirmation;
		if (!isFunction) {
			log.error("[workflowEngine] " + "error function");
			log.info("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check next party code
		if (LaubeUtility.isBlank(target.getNextPartyCode())) {
			log.error("[workflowEngine] " + "error next party code");
			log.info("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of party transit code
		if (LaubeUtility.isBlank(target.getPartyTransitCode())){
			log.error("[workflowEngine] " + "error party transit code");
			log.info("[workflowEngine] " + "checkItem end");
			return false;
		}

		// check of party code connector
		isUnspecified        = target.getPartyTransitCodeConnector() == SpecifiedValue.Unspecified;
		isLogicalSum         = target.getPartyTransitCodeConnector() == SpecifiedValue.LogicalSum;
		isLogicalProduct     = target.getPartyTransitCodeConnector() == SpecifiedValue.LogicalProduct;
		final boolean isPartyTransitCodeConnector = isUnspecified || isLogicalSum || isLogicalProduct;
		if (!isPartyTransitCodeConnector) {
			log.error("[workflowEngine] " + "error party code connector");
			log.info("[workflowEngine] " + "checkItem end");
			return false;
		}

		return true;
	}

	/**
	 * check of next party.(recursive processing)
	 * @param approvalRouteInformationAcceptors
	 * @param nextPartyCode next party code
	 * @param keywords
	 * @return result
	 */
	@SuppressWarnings({ "nls", "static-method" })
	private static final boolean checkNextParty(final List<ApprovalRouteInformationAcceptor> approvalRouteInformationAcceptors, final String nextPartyCode, ArrayList<String> keywords) {

		log.info("[workflowEngine] " + "checkNextParty start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "approvalRouteInformationAcceptors:" + approvalRouteInformationAcceptors);
		log.info("[workflowEngine] " + "nextPartyCode: " + nextPartyCode);
		log.info("[workflowEngine] " + "keywords:" + keywords);

		if ((LaubeUtility.isEmpty(approvalRouteInformationAcceptors))||(LaubeUtility.isBlank(nextPartyCode))||(LaubeUtility.isEmpty(keywords))) {
			return true;
		}

		for(ApprovalRouteInformationAcceptor route : approvalRouteInformationAcceptors) {
			// discover the next party code
			if (nextPartyCode.equals(route.getPartyCode())) {
				if (route.getNextPartyCode().equals(SpecifiedValue.END)) {
					log.debug("[workflowEngine] " + "success to find the final party");
					log.info("[workflowEngine] " + "checkNextParty end");
					return true;
				}
				// if the next party code is known of the party error
				for(String keyword : keywords) {
					if ((route.getPartyCode() + route.getNextPartyCode()).equals(keyword)) {
						log.error("[workflowEngine] " + "already add party code. party code is [" + route.getPartyCode() + route.getNextPartyCode() + "]");
						log.info("[workflowEngine] " + "checkNextParty end");
						return false;
					}
				}
				keywords.add(route.getPartyCode() + route.getNextPartyCode());

				boolean result = checkNextParty(approvalRouteInformationAcceptors, route.getNextPartyCode(), keywords);
				if (!result) {
					log.error("[workflowEngine] " + "error checkNextParty");
					log.info("[workflowEngine] " + "checkNextParty end");
					return false;
				}else{
					keywords = new ArrayList<String>();
				}
			}
		}
		log.info("[workflowEngine] " + "checkNextParty end");
		return true;
	}
}
