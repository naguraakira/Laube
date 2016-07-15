package site.laube.modelinterface;

import site.laube.dto.ActivityDto;
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

public interface ActivityModelInterface {

	/**
	 * removal of the common activity master (company units)<br>
	 * remove all of the common activity master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * removal of the common activity master (route units)<br>
	 * remove all of the common activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto delete(final String companyCode, final String routeCode) throws LaubeException;

	/**
	 * register the activity master.<br>
	 * @param activityDto activity dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto insert(final ActivityDto activityDto) throws LaubeException;

	/**
	 * update the activity master.<br>
	 * @param activityDto activity dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto update(final ActivityDto activityDto) throws LaubeException;

	/**
	 * company code, route code, the activity code, to find the activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @param activityCode activity code
	 * @return activity master
	 * @exception LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto find(final String companyCode, final String routeCode, final String activityCode) throws LaubeException;

	/**
	 * company code, route code to find the activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return activity master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto find(final String companyCode, final String routeCode) throws LaubeException;
}