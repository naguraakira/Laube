package site.laube.dto;

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

	/**
	 * set the party transit code.<br>
	 * @param partyTransitCode party transit code
	 */
	public final void setPartyTransitCode(final String partyTransitCode) {
		this.partyTransitCode = partyTransitCode;
	}

	/**
	 * get the party transit code.<br>
	 *
	 * @return party transit code
	 */
	public final String getPartyTransitCode() {
		return this.partyTransitCode;
	}

	/**
	 * set the party transit code connector.<br>
	 * @param partyTransitCodeConnector party transit code connector
	 */
	public final void setPartyTransitCodeConnector(final int partyTransitCodeConnector) {
		this.partyTransitCodeConnector = partyTransitCodeConnector;
	}

	/**
	 * get the party transit code connector.<br>
	 *
	 * @return party transit code connector
	 */
	public final int getPartyTransitCodeConnector() {
		return this.partyTransitCodeConnector;
	}

	/**
	 * set the conditional branching for the item name.<br>
	 * @param branchItemName branch item name
	 */
	public final void setBranchItemName(final String branchItemName) {
		this.branchItemName = branchItemName;
	}

	/**
	 * get the conditional branching for the item name.<br>
	 * @return branch item name
	 */
	public final String getBranchItemName() {
		return this.branchItemName;
	}

	/**
	 * set the branch item.<br>
	 * @param branchItem branch item
	 */
	public final void setBranchItem(final int branchItem) {
		this.branchItem = branchItem;
	}

	/**
	 * get the branch item.<br>
	 * @return branch item
	 */
	public final int getBranchItem() {
		return this.branchItem;
	}

	/**
	 * set the comparison operators.<br>
	 * @param comparisonOperator comparison operator
	 */
	public final void setComparisonOperator(final int comparisonOperator) {
		this.comparisonOperator = comparisonOperator;
	}

	/**
	 * get the comparison operators.<br>
	 * @return comparison operator
	 */
	public final int getComparisonOperator() {
		return this.comparisonOperator;
	}
}
