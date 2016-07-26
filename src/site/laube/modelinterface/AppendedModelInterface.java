package site.laube.modelinterface;

import site.laube.dto.AppendedDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

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

public interface AppendedModelInterface {

	/**
	 * delete the appended object<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	*/
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * delete the appended object(user unit)<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto delete(final String companyCode, final int applicationNumber) throws LaubeException;

	/**
	 * register the appended object.<br>
	 * @param appendedDto appended object
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto insert(final AppendedDto appendedDto) throws LaubeException;

	/**
	 * To update the appended object.<br>
	 * @param appendedDto appended object
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto update(final AppendedDto appendedDto) throws LaubeException;

	/**
	 * Search the appended object.<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto find(final String companyCode, final int applicationNumber) throws LaubeException;
}