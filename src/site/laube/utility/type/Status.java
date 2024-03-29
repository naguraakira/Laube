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

public enum Status {

	/**
	 * Draft / 下書き
	 */
	Draft(0),

	/**
	 * UnderExamination / 審査中
	 */
	UnderExamination(1),

	/**
	 * Remand / 保留
	 */
	Remand(2),

	/**
	 * Pullback / 引き戻し
	 */
	Pullback(3),

	/**
	 * Withdrawal / 差し戻し
	 */
	Withdrawal(4),

	/**
	 * Denial / 否認
	 */
	Denial(5),

	/**
	 * Approved / 承認
	 */
	Approved(6);

	private final int value;
	Status(int value) {
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
