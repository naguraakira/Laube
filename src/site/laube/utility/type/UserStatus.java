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
	 * Authorizer_Untreated / 未処理
	 */
	Authorizer_Untreated(0),

	/**
	 * Arrival / 到着
	 */
	Arrival(1),

	/**
	 * Hold / 保留
	 */
	Hold(2),

	/**
	 * Authorizer_AnticipatingApproval / 先取り承認
	 */
	Authorizer_AnticipatingApproval(3),

	/**
	 * Authorizer_HikeApproval / 引き上げ承認
	 */
	Authorizer_HikeApproval(4),

	/**
	 * Authorizer_AutomaticApproval / 自動承認
	 */
	Authorizer_AutomaticApproval(5),

	/**
	 * Authorizer_Denial / 否認
	 */
	Authorizer_Denial(6),

	/**
	 * Authorizer_Approval / 承認
	 */
	Authorizer_Approval(7),

	/**
	 * Authorizer_Confirmation / 確認
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
