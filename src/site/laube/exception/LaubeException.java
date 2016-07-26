package site.laube.exception;

import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;

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

public final class LaubeException extends Exception {

	/**
	 * To manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(LaubeException.class);

	/**
	 * sirial version
	 */
	private static final long serialVersionUID = 2314117173345596443L;

	/**
	 * constractor<br>
	 * @param traceName trace name
	 * @param contents contents
	 */
	public LaubeException(final String traceName, final String contents) {
		super(contents);
		log.crush(traceName, contents);
	}

	/**
	 * constractor<br>
	 * @param traceName trace name
	 * @param e exception
	 */
	public LaubeException(final String traceName, final Exception e) {
		super(e);
		log.crush(traceName, e);
	}

	/**
	 * constractor<br>
	 * @param traceName trace name
	 * @param messageId message id
	 * @param message message
	 */
	public LaubeException(final String traceName, final String messageId, final String message) {
		super(message);
		log.crush(traceName, messageId, message);
	}
}
