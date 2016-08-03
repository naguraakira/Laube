package site.laube.dto;

import lombok.Getter;
import lombok.Setter;

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
public class PartyTransitDto extends RouteDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = -2693497943935038382L;

	/**
	 * to manage the party transit code.<br>
	 */
	private String partyTransitCode;

	/**
	 * to manage the party transit code connector.<br>
	 */
	private int partyTransitCodeConnector;

	/**
	 * to manage the conditional branching for the item name.<br>
	 */
	private String branchItemName;

	/**
	 * to manage the conditional branching for the item value.<br>
	 */
	private int branchItem;

	/**
	 * to manage the comparison operators.<br>
	 */
	private int comparisonOperator;
}
