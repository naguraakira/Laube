package site.laube.dto;

import java.io.Serializable;
import java.sql.Connection;

import lombok.Getter;
import lombok.Setter;
import site.laube.utility.LaubeProperties;
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
public final class ResultDto implements Serializable {

	private static final long serialVersionUID = 2824047883202335433L;
	private Connection connection = null;
	private boolean success;
	private String messageId = null;
	private String message = null;
	private Object resultData;

	/**
	 * constractor
	 */
	@SuppressWarnings("nls")
	public ResultDto(){
		setSuccess(true);
		this.messageId = "N0001";
		setMessage(this.messageId);
	}

	/**
	 * set the message id.<br>
	 * @param messageId message id
	 */
	public final void setMessageId(final String messageId) {

		this.messageId = messageId;
		setMessage(messageId);

	}

	/**
	 * set the message.<br>
	 * @param messageId message id
	 */
	private final void setMessage(final String messageId) {
		this.message = LaubeProperties.getValue(messageId);
	}

	/**
	 * all the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
