package site.laube.acceptor.search;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import site.laube.acceptor.SearchSystemAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;

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
@Getter
@Setter
public final class RouteSearchAcceptor extends SearchSystemAcceptor {

	/**
	 * to manage the screen mode<br>
	 */
	private int screenMode;

	/**
	 * to manage the state of the company name<br>
	 */
	private String companyName;

	/**
	 * to manage the state of the application form name<br>
	 */
	private String applicationFormName;

	/**
	 * to manage the application classification code<br>
	 */
	private String applicationClassificationCode = null;

	/**
	 * to manage the application classification name<br>
	 */
	private String applicationClassificationName = null;

	/**
	 * to manage the apply company name of request.<br>
	 */
	private String applyCompanyName;

	/**
	 * to manage the apply unit name of request.<br>
	 */
	private String applyUnitName;

	/**
	 * to manage the apply user name. of request.<br>
	 */
	private String applyUserName;

	/**
	 * to manage the deputy apply company name of request.<br>
	 */
	private String deputyApplyCompanyName;

	/**
	 * to manage the deputy apply unit name of request.<br>
	 */
	private String deputyApplyUnitName;

	/**
	 * to manage the deputy apply user name. of request.<br>
	 */
	private String deputyApplyUserName;

	/**
	 * to manage the individual Routes.<br>
	 */
	private List<ApprovalRouteInformationAcceptor> individualRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();

	/**
	 * to manage the common Routes.<br>
	 */
	private List<ApprovalRouteInformationAcceptor> commonRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();
}
