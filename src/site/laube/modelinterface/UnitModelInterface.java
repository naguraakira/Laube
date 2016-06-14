package site.laube.modelinterface;

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

public interface UnitModelInterface {

	/**
	 * search the unit master.<br>
	 * @param companyCode Company code
	 * @param unitCode unit code
	 * @return unit master
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	public abstract ResultDto find(final String companyCode, final String unitCode) throws LaubeException;
}