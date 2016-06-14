package site.laube.modelinterface;

import site.laube.dto.ResultDto;
import site.laube.dto.RoleDto;
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

public interface RoleModelInterface {

	/**
	 * delete role master (company units)<br>
	 * remove all of the role the master and the role by the employee master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * delete role master (role units)<br>
	 * remove all of the role the master and the role by the employee master.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return result
	 * @throws LaubeException
	 */
    public abstract ResultDto delete(final String companyCode, final String roleCode) throws LaubeException;

	/**
	 * register the role master.<br>
	 * @param roleDto role master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto insert(final RoleDto roleDto) throws LaubeException;

	/**
	 * update the role master.<br>
	 * @param roleDto role master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto update(final RoleDto roleDto) throws LaubeException;

	/**
	 * the appropriate role code checks in use.<br>
	 * tole code and returns true if the is not Zui role by employees master and string.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return use check
	 * @exception LaubeException
	 */
	public abstract boolean isOccupied(final String companyCode, final String roleCode) throws LaubeException;

	/**
	 * search for the role a master at the company code.<br>
	 * @param companyCode company code
	 * @return role master
	 * @exception LaubeException
	 */
	public abstract ResultDto find(final String companyCode) throws LaubeException;

	/**
	 * perform a full match search of role master.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return role master
	 * @exception LaubeException
	 */
	public abstract ResultDto findByRoleCode(final String companyCode, final String roleCode) throws LaubeException;

	/**
	 * do the fuzzy search of role master.<br>
	 * @param companyCode company code
	 * @param roleName role name
	 * @return role master list
	 * @exception LaubeException
	 */
	public abstract ResultDto findByRoleName(final String companyCode, final String roleName) throws LaubeException;}
