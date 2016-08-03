package site.laube.daointerface;

import site.laube.dto.BossDto;
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

public interface BossDaoInterface {

	/**
	 * delete the boss master<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	*/
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * delete the boss master(user unit)<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @param userCode user code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto delete(final String companyCode, final String userCode) throws LaubeException;

	/**
	 * register the boss master.<br>
	 * @param bossDto boss dto
	 * @return ResultDto
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto insert(final BossDto bossDto) throws LaubeException;

	/**
	 * To update the boss master.<br>
	 * @param bosseDto boss master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto update(final BossDto bosseDto) throws LaubeException;

	/**
	 * return all of the boss set to the highest.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @param userCode user code
	 * @param applicationFormCode application form code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto findByChainOfResposibility(final String companyCode, final String unitCode, final String userCode, final String applicationFormCode) throws LaubeException;

	/**
	 * Search the boss master.<br>
	 * Boss of the settings can be set to the [department] [application] unit or [department] unit.<br>
	 * If both are set, and returns the setting of the [department] [application] unit.<br>
	 * If the application code is null, return the setting boss is set to [department] unit.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @param userCode user code
	 * @param applicationFormCode application form code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto find(final String companyCode, final String unitCode, final String userCode, final String applicationFormCode) throws LaubeException;
}