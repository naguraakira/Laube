package site.laube.modelinterface;

import java.util.List;

import site.laube.dto.ActivityObjectDto;
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

public interface ActivityObjectModelInterface {

	/**
	 * register the activity object.<br>
	 * @param ActivityObjectDtos activity object dto list
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto insert(final List<ActivityObjectDto> ActivityObjectDtos) throws LaubeException;

	/**
	 * register the activity object.<br>
	 * @param activityObjectDto activity object dto
	 * @return ResultDto
	 * @throws LaubeException
	 */
	public abstract ResultDto insert(final ActivityObjectDto activityObjectDto) throws LaubeException;

	/**
	 * update the activity object.<br>
	 * @param activityObjectDto activity object Dto
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto update(final ActivityObjectDto activityObjectDto) throws LaubeException;

	/**
	 * delete the activity object.<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto delete(final String companyCode, final int applicationNumber) throws LaubeException;

	/**
	 * find the activity object.<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @param approvalCompanyCode approval company code
	 * @param approvalUnitCode approval unit code
	 * @param approvalUserCode approval user code
	 * @param applovalUserStatus apploval user status
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto find(final String companyCode, final int applicationNumber, final String approvalCompanyCode, final String approvalUnitCode, final String approvalUserCode, final int applovalUserStatus) throws LaubeException;

	/**
	 * find the activity object by arrival.<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @param approvalCompanyCode approval company code
	 * @param approvalUnitCode approval unit code
	 * @param approvalUserCode approval userCode code
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto findByArrival(final String companyCode, final int applicationNumber, final String approvalCompanyCode, final String approvalUnitCode, final String approvalUserCode) throws LaubeException;
}