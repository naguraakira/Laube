package site.laube.dto;

import java.io.Serializable;
import java.sql.Connection;

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

public final class ResultDto implements Serializable {

	private static final long serialVersionUID = 2824047883202335433L;
	private Connection connection = null;
	private boolean status;
	private String messageId = null;
	private String message = null;
	private Object resultData;

	/**
	 * constractor
	 */
	@SuppressWarnings("nls")
	public ResultDto(){
		setStatus(true);
		this.messageId = "N0001";
		setMessage(this.messageId);
	}

	/**
	 * set the Connection.<br>
	 * @param connection Connection
	 */
	public final void setConnection(Connection connection){
		this.connection = connection;
	}

	/**
	 * get the Connection.<br>
	 * @return Connection
	 */
	public final Connection getConnection(){
		return this.connection;
	}

	/**
	 * set the status.<br>
	 * @param status status
	 */
	public final void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * get the status.<br>
	 *
	 * @return status
	 */
	public final boolean isSuccess() {
		return this.status;
	}

	/**
	 * get the message id.<br>
	 *
	 * @return message id
	 */
	public final String getMessageId() {
		return this.messageId;
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
	 * get the message.<br>
	 *
	 * @return message
	 */
	public final String getMessage() {
		return this.message;
	}

	/**
	 * set the message.<br>
	 * @param messageId message id
	 */
	private final void setMessage(final String messageId) {
		this.message = LaubeProperties.getValue(messageId);
	}

	/**
	 * get the result data.<br>
	 *
	 * @return result data
	 */
	public final Object getResultData() {
		return this.resultData;
	}

	/**
	 * set the result data.<br>
	 * @param resultData result data
	 */
	public final void setResultData(final Object resultData) {
		this.resultData = resultData;
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
