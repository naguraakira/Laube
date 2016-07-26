package site.laube.modelinterface;

import site.laube.dto.ApplicationFormDto;
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

public interface ApplicationFormModelInterface {

	/**
     * delete the application form master (company unit)<br>
     * remove the application-specific activity master and all of the application form master.
     * @param companyCode company code
     * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
     */
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
     * delete the application classification master (application code unit)<br>
     * remove the application classification by Activision Byi master to match the application classification master.
     * @param companyCode Company code
     * @param applicationFormCode Application code
     * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
     */
    public abstract ResultDto delete(final String companyCode, final String applicationFormCode) throws LaubeException;

	/**
	 * register the application form master.<br>
	 * @param applicationFormDto Application form master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto insert(final ApplicationFormDto applicationFormDto) throws LaubeException;

	/**
	 * to update the application form master.<br>
	 * @param applicationFormDto Application form master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto update(final ApplicationFormDto applicationFormDto) throws LaubeException;

	/**
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @return Application form master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto find(final String companyCode) throws LaubeException;

	/**
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @param applicationFormCode Application code
	 * @return Application form master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto findByApplicationFormCode(final String companyCode, final String applicationFormCode) throws LaubeException;

	/**
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @param applicationClassificationCode application classification code
	 * @return ResultDto
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto findByApplicationClassificationCode(final String companyCode, final String applicationClassificationCode) throws LaubeException;
}