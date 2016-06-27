package site.laube.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class LaubeException extends Exception {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(LaubeException.class);

	/**
	 * sirial version
	 */
	private static final long serialVersionUID = 2314117173345596443L;

	/**
	 * constractor
	 * @param e exception
	 */
	public LaubeException(Exception e) {
		super(e);
		log.error("[workflowEngine] " + "Exception occured.");
		log.error("[workflowEngine] " + "[argument]");
		log.error("[workflowEngine] " + "e:" + e);
	}

	/**
	 * constractor
	 * @param message
	 */
	public LaubeException(String message) {
		super(message);
		log.error("[workflowEngine] " + "Exception occured.");
		log.error("[workflowEngine] " + "[argument]");
		log.error("[workflowEngine] " + "message:" + message);
	}

	/**
	 * constractor<br>
	 *
	 * The message of the exception "message ID +": "+ Message" to set
	 * @param messageID
	 * @param message
	 */
	public LaubeException(String messageID, String message) {
		super(messageID + ":" + message);
		log.error("[workflowEngine] " + "Exception occured.");
		log.error("[workflowEngine] " + "[argument]");
		log.error("[workflowEngine] " + "messageID:" + messageID);
		log.error("[workflowEngine] " + "message:" + message);
	}

}
