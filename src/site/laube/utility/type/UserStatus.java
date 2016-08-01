package site.laube.utility.type;

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

public enum UserStatus {

	/**
	 * Authorizer_Untreated
	 */
	Authorizer_Untreated(0),

	/**
	 * Arrival
	 */
	Arrival(1),

	/**
	 * Hold
	 */
	Hold(2),

	/**
	 * Authorizer_AnticipatingApproval
	 */
	Authorizer_AnticipatingApproval(3),

	/**
	 * Authorizer_HikeApproval
	 */
	Authorizer_HikeApproval(4),

	/**
	 * Authorizer_AutomaticApproval
	 */
	Authorizer_AutomaticApproval(5),

	/**
	 * Authorizer_Denial
	 */
	Authorizer_Denial(6),

	/**
	 * Authorizer_Approval
	 */
	Authorizer_Approval(7),

	/**
	 * Authorizer_Confirmation
	 */
	Authorizer_Confirmation(8);

	private final int value;
	UserStatus(int value) {
		this.value = value;
	}

	/**
	 * change to int<br>
	 * @return result
	 */
	public int toInt() {
		return this.value;
	}
}
