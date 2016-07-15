package site.laube.modelinterface;

import site.laube.dto.ResultDto;
import site.laube.dto.RoleUserDto;
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

public interface RoleUserModelInterface {

	/**
	 * deleting a role by the employee master (role / employee number units)<br>
	 * remove the role by the employee master.<br>
	 * @param companyCode company code
	 * @param roleCode    role code
	 * @param userCode    user code
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
    public abstract ResultDto delete(final String companyCode, final String roleCode, final String userCode) throws LaubeException;

	/**
	 * register the role by the employee master.<br>
	 * @param roleUserDto role user dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto insert(final RoleUserDto roleUserDto) throws LaubeException;

	/**
	 * update the role by the employee master.<br>
	 * @param roleUserDto role user dto
	 * @return result
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto update(final RoleUserDto roleUserDto) throws LaubeException;

	/**
	 * search only single item to the appropriate record.<br>
	 * @param companyCode company code
	 * @param roleName    role name
	 * @param userCode    user code
	 * @return role user master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto find(final String companyCode, final String roleName, final String userCode) throws LaubeException;

	/**
	 * employees will find the role that belongs.<br>
	 * @param companyCode company code
	 * @param userCode    user code
	 * @return role user master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto findByUserCode(final String companyCode, final String userCode) throws LaubeException;

	/**
	 * search for all employees that belong to the role.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return role user master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto findByRoleCode(final String companyCode, final String roleCode) throws LaubeException;
}
