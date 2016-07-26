package site.laube.modelinterface;

import site.laube.dto.ResultDto;
import site.laube.dto.RouteDto;
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

public interface RouteModelInterface {

	/**
	 * removal of the route master (company units)<br>
	 * remove all of the route master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
 	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * removal of the route master (route units)<br>
	 * remove all of the route master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto delete(final String companyCode, final String routeCode) throws LaubeException;

	/**
	 * register the route master.<br>
	 * @param routeDto route master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto insert(final RouteDto routeDto) throws LaubeException;

	/**
	 * update the route master.<br>
	 * @param routeDto route master
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto update(final RouteDto routeDto) throws LaubeException;

	/**
	 * spent check of the route master<br>
	 * if the route is already being used in the application form by the root master, and then return true.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract boolean isOccupied(final String companyCode, final String routeCode) throws LaubeException;

	/**
	 * search the route master.<br>
	 * @param companyCode company code
	 * @return route master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto find(final String companyCode) throws LaubeException;

	/**
	 * search the route master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return route master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto find(final String companyCode, final String routeCode) throws LaubeException;

}