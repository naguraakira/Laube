package site.laube.database;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.exception.LaubeException;
import site.laube.utility.LaubeProperties;

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

public final class DbConnectManager {

	/**
	 * to manage the log.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(DbConnectManager.class);

	private static Connection CONNECTION = null;

	/**
	 * It gets the generated connection object.<br>
	 * The resulting connection object will generate only one instance.<br>
	 * @return Connection object
	 * @throws LaubeException
	 */
	public static Connection getConnection() throws LaubeException {

		try {

			if ((CONNECTION == null) || (CONNECTION.isClosed())) {

				LaubeProperties workflowProperties = LaubeProperties.getInstance();
				String dbDriver = workflowProperties.getValue("db.driver");
				String url = workflowProperties.getValue("db.url");
				String userName = workflowProperties.getValue("db.user");
				String password =  workflowProperties.getValue("db.password");

				try {
					Class.forName (dbDriver);
					CONNECTION = DriverManager.getConnection(url, userName, password);
					CONNECTION.setAutoCommit(false);

				} catch (Exception e) {
					log.error("[workflowEngine] " + e);
					throw new LaubeException(e);
				}
			}
		} catch (Exception e) {
			log.error("[workflowEngine] " + e);
			throw new LaubeException(e);
		}
		return CONNECTION;
	}
}
