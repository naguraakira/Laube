package site.laube.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import org.seasar.doma.Id;

import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
public class LaubeDto implements Serializable {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = 4575225513879036593L;

	/**
	 * to manage the sarogate key. <br>
	 */
	@Id
	private int id;

	/**
	 * to manage the create date time. <br>
	 */
	private Timestamp createDateTime;

	/**
	 * to manage the create user id. <br>
	 */
	private String createUserId;

	/**
	 * to manage the update date time. <br>
	 */
	private Timestamp updateDateTime;

	/**
	 * to manage the update user id. <br>
	 */
	private String updateUserId;

	/**
	 * all the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
