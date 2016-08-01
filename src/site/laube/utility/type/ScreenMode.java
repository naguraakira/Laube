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

public enum ScreenMode {

	/**
	 * ApplyMode
	 */
	ApplyMode(1),

	/**
	 * ApprovalMode
	 */
	ApprovalMode(2),

	/**
	 * ConfirmationMode
	 */
	ConfirmationMode(3),

	/**
	 * SeeMode
	 */
	SeeMode(4);

	private final int value;
	ScreenMode(int value) {
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
