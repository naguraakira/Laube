package site.laube.daointerface;

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
public interface UserDaoInterface {

	/**
	 * Search the user master.<br>
	 * @param companyCode Company code
	 * @param userCode user code
	 * @return user master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public abstract ResultDto find(final String companyCode, final String userCode) throws LaubeException;
}