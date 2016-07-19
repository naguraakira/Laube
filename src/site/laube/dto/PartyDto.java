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

public class PartyDto extends PartyTransitDto {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = -2693497943935038382L;

	/**
	 * to manage the party code.<br>
	 */
	private String partyCode;

	/**
	 * to manage the partyCodeConnector.<br>
	 */
	private int partyCodeConnector;

	/**
	 * set the party code.<br>
	 * @param partyCode party code
	 */
	public final void setPartyCode(final String partyCode) {
		this.partyCode = partyCode;
	}

	/**
	 * get the party code.<br>
	 * @return party code
	 */
	public final String getPartyCode() {
		return this.partyCode;
	}

	/**
	 * set the partyCodeConnector.<br>
	 * @param partyCodeConnector party Code Connector
	 */
	public final void setPartyCodeConnector(final int partyCodeConnector) {
		this.partyCodeConnector = partyCodeConnector;
	}

	/**
	 * get the partyCodeConnector.<br>
	 * @return partyCodeConnector
	 */
	public final int getPartyCodeConnector() {
		return this.partyCodeConnector;
	}
}
