package site.laube.modelinterface;

import site.laube.dto.ApplicationClassificationDto;
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

public interface ApplicationClassificationModelInterface {

	/**
	 * delete the application classification master (company unit)<br>
	 * Remove all of the application classification master and the applicant by category activity master.
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * delete the application classification master (application code unit)<br>
	 * Remove the application classification by activity master to match the application classification master.
	 * @param companyCode company code
	 * @param applicationClassificationCode Application classification code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto delete(final String companyCode, final String applicationClassificationCode) throws LaubeException;

	/**
	 * register the application classification master.<br>
	 * @param applicationClassificationDto Application classification master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto insert(final ApplicationClassificationDto applicationClassificationDto) throws LaubeException;

	/**
	 * to update the application classification master.<br>
	 * @param applicationClassificationDto Application classification master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto update(final ApplicationClassificationDto applicationClassificationDto) throws LaubeException;

	/**
	 * search the application form master.<br>
	 * @param companyCode company code
	 * @return Application form master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public abstract ResultDto find(final String companyCode) throws LaubeException;

	/**
	 * search the application form master.<br>
	 * @param companyCode company code
	 * @param applicationClassificationCode Application classification code
	 * @return Application form master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public abstract ResultDto findByApplicationClassificationCode(final String companyCode, final String applicationClassificationCode) throws LaubeException;
}