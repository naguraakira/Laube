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

public enum Connector {

	/**
	 * Unspecified / 未定義
	 */
	Unspecified(0),

	/**
	 * LogicalSum / 論理和
	 */
	LogicalSum(1),

	/**
	 * LogicalProduct / 論理積
	 */
	LogicalProduct(2),

	/**
	 * Majority / 多数
	 */
	Majority(3);

	private final int value;
	Connector(int value) {
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
