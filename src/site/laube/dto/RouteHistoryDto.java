package site.laube.dto;

import site.laube.utility.LaubeUtility;

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

public final class RouteHistoryDto extends ApplicationObjectDto {

	/**
	 * to manage the log.<br>
	 */
	private static final long serialVersionUID = -4751153596644033770L;

	/**
	 * to manage the history number.<br>
	 */
	private int number;

	/**
	 * set the history number.<br>
	 * @param number history number
	 */
	public final void setNumber(final int number) {
		this.number = number;
	}

	/**
	 * get the history number.<br>
	 * @return history number
	 */
	public final int getNumber() {
		return this.number;
	}

	/**
	 * all the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
