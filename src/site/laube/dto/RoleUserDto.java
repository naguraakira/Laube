package site.laube.dto;

import site.laube.utility.LaubeUtility;

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

public final class RoleUserDto extends RoleDto {

	/**
	 * to manage the log.<br>
	 */
	private static final long serialVersionUID = 6539232413121478335L;

	/**
	 * 部門コードを管理します。<br>
	 */
	private String unitCode;

	/**
	 * 部門名を管理します。<br>
	 */
	private String unitName;

	/**
	 * 社員番号を管理します。<br>
	 */
	private String userCode;

	/**
	 * 社員名を管理します。<br>
	 */
	private String userName;

	/**
	 * 部門コードを設定します。
	 * @param unitCode 部門コード
	 */
	public final void setUnitCode(final String unitCode) {
		this.unitCode = unitCode;
	}

	/**
	 * 部門コードを取得します。<br>
	 * @return 部門コード
	 */
	public final String getUnitCode() {
		return this.unitCode;
	}

	/**
	 * 部門名を設定します。<br>
	 * @param unitName 部門名
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 部門名を取得します。<br>
	 *
	 * @return 部門名
	 */
	public String getUnitName() {
		return this.unitName;
	}

	/**
	 * 社員番号を設定します。<br>
	 * @param userCode 社員番号
	 */
	public final void setUserCode(final String userCode) {
		this.userCode = userCode;
	}

	/**
	 * 社員番号を取得します。<br>
	 * @return 社員番号
	 */
	public final String getUserCode() {
		return this.userCode;
	}

	/**
	 * 社員名を設定します。<br>
	 * @param userName 社員名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 社員名を取得します。<br>
	 *
	 * @return 社員名
	 */
	public String getUserName() {
		return this.userName;
	}

	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
