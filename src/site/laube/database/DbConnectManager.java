package site.laube.database;

import java.sql.Connection;
import java.sql.DriverManager;

import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
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

public final class DbConnectManager {

	/**
	 * to manage the log.<br>
	 */
	private static LaubeLogger log = new LaubeLogger(LaubeLoggerFactory.getLogger(DbConnectManager.class));

	/**
	 * connection<br>
	 */
	private static Connection connection = null;

	/**
	 * It sets the generated connection object.<br>
	 * The resulting connection object will generate only one instance.<br>
	 * @return Connection object
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public static void setConnection(Connection con) throws LaubeException {
		connection = con;
	}

	/**
	 * It gets the generated connection object.<br>
	 * The resulting connection object will generate only one instance.<br>
	 * @return Connection object
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public static Connection getConnection() throws LaubeException {

		log.traceStart("getConnection"); //$NON-NLS-1$

		try {
			if ((LaubeUtility.isEmpty(connection)) || (connection.isClosed())) {
				String dbDriver = LaubeProperties.getValue("db.driver"); //$NON-NLS-1$
				String url      = LaubeProperties.getValue("db.url"); //$NON-NLS-1$
				String userName = LaubeProperties.getValue("db.user"); //$NON-NLS-1$
				String password = LaubeProperties.getValue("db.password"); //$NON-NLS-1$

				try {
					Class.forName (dbDriver);
					connection = DriverManager.getConnection(url, userName, password);
					connection.setAutoCommit(false);
				} catch (Exception e) {
					throw new LaubeException("getConnection", e); //$NON-NLS-1$
				}
			}
		} catch (final Exception e) {
			throw new LaubeException("getConnection", e); //$NON-NLS-1$
		}
		log.traceEnd("getConnection"); //$NON-NLS-1$
		return connection;
	}
}
