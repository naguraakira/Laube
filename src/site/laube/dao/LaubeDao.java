package site.laube.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import site.laube.database.DbConnectManager;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
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

public class LaubeDao {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = new LaubeLogger(LaubeLoggerFactory.getLogger(LaubeDao.class));

	/**
	 * to manage the connection.<br>
	 */
	public static Connection connection = null;

	/**
	 * to manage the resultSet.<br>
	 */
	protected ResultSet resultSet = null;

	/**
	 * to manage the preparedStatement.<br>
	 */
	protected PreparedStatement preparedStatement = null;

	/**
	 * to manage the statement.<br>
	 */
	protected Statement statement = null;

	/**
	 * constractor<br>
	 * create connection<br>
	 */
	public LaubeDao() {
		getConnection();
	}

	/**
	 * get the connection.<br>
	 *
	 * @return connection
	 */
	@SuppressWarnings("nls")
	public final Connection getConnection() {

		log.traceStart("getConnection");

		try {
			if ((LaubeUtility.isEmpty(connection)) || (connection.isClosed())) {
				connection = DbConnectManager.getConnection();
				this.statement = connection.createStatement();
			}

		} catch (final Exception e) {
			log.crush("getConnection", e);

		} finally {
			log.traceEnd("getConnection");
		}
		return connection;
	}

	/**
	 * close preparedStatement.<br>
	 */
	@SuppressWarnings("nls")
	protected final void closePreparedStatement() {

		log.traceStart("closePreparedStatement");

		try {
			closeResultSet();
			if ((!LaubeUtility.isEmpty(this.preparedStatement)) && (!this.preparedStatement.isClosed())) {
				this.preparedStatement.close();
				this.preparedStatement = null;
			}
		} catch (final Exception e) {
			log.crush("closePreparedStatement", e);
		}
		log.traceEnd("closePreparedStatement");
	}

	/**
	 * close resultSet.<br>
	 */
	@SuppressWarnings("nls")
	private final void closeResultSet() {

		log.traceStart("closeResultSet");

		try {
			if ((!LaubeUtility.isEmpty(this.resultSet)) && (!this.resultSet.isClosed())) {
				this.resultSet.close();
				this.resultSet = null;
			}
		} catch (final Exception e) {
			log.crush("closeResultSet", e);
		}
		log.traceEnd("closeResultSet");
	}

	/**
	 * commit<br>
	 *
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public final static void commit() throws LaubeException {

		log.traceStart("commit");

		try {
			connection.commit();

		} catch (final Exception e) {
			log.crush("commit", e);

		} finally {
			log.traceEnd("commit");
		}
	}

	/**
	 * set ResultSet to Dto.<br>
	 *
	 * @param resultSet ResultSet
	 * @param workflowDto before data
	 * @return after data
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public final static ArrayList<LaubeDto> conversion(final ResultSet resultSet, LaubeDto workflowDto) throws LaubeException {

		log.traceStart("conversion", resultSet, workflowDto);

		final ArrayList<LaubeDto> result = new ArrayList<LaubeDto>();

		try {
			do {
				LaubeDto updateWorkflowDto = resultSetToDto(resultSet, workflowDto);
				if (LaubeUtility.isEmpty(updateWorkflowDto)){
					break;
				}
				result.add(updateWorkflowDto);
			} while (resultSet.next());
			return result;

		} catch (final Exception e) {
			throw new LaubeException("conversion", e);
		} finally {
			log.traceEnd("conversion");
		}
	}

	/**
	 * set ResultSet to Dto.<br>
	 *
	 * @param resultSet result set
	 * @param workflowDto workflow dto
	 * @return after data
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public final static LaubeDto resultSetToDto(final ResultSet resultSet, final LaubeDto workflowDto) throws LaubeException {

		log.traceStart("resultSetToDto", resultSet, workflowDto);

		LaubeDto laubeDto = workflowDto;

		try {
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			ArrayList<String> itemList = new ArrayList<String>();

			for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
				itemList.add(resultSetMetaData.getColumnName(i + 1));
			}
			Method method = null;

			laubeDto = laubeDto.getClass().newInstance();
			int count = 0;
			for (String name : itemList) {
				count++;
				String camelName = LaubeUtility.snakeToCamel(name);
				String setter = "set" + camelName.substring(0, 1).toUpperCase() + camelName.substring(1);

				int type = resultSetMetaData.getColumnType(count);
				if (Types.INTEGER == type) {
					method = laubeDto.getClass().getMethod(setter, new Class[] { int.class });
					method.invoke(laubeDto, resultSet.getInt(name));
				} else if (Types.TIMESTAMP == type) {
					method = laubeDto.getClass().getMethod(setter, new Class[] { Timestamp.class });
					method.invoke(laubeDto, resultSet.getTimestamp(name));
				} else if (Types.BIT == type) {
					method = laubeDto.getClass().getMethod(setter, new Class[] { boolean.class });
					method.invoke(laubeDto, resultSet.getBoolean(name));
				} else if (Types.BOOLEAN == type) {
					method = laubeDto.getClass().getMethod(setter, new Class[] { boolean.class });
					method.invoke(laubeDto, resultSet.getBoolean(name));
				} else if (Types.BIGINT == type) {
					method = laubeDto.getClass().getMethod(setter, new Class[] { long.class });
					method.invoke(laubeDto, resultSet.getLong(name));
				} else {
					method = laubeDto.getClass().getMethod(setter, new Class[] { String.class });
					method.invoke(laubeDto, resultSet.getString(name));
				}
			}
			return laubeDto;

		} catch (final Exception e) {
			throw new LaubeException("resultSetToDto", e);
		} finally {
			log.traceEnd("resultSetToDto");
		}
	}

	/**
	 * check Item.<br>
	 * @param wFResult result
	 * @param LaubeDto dto
	 * @param items item
	 * @return result
	 * @throws Exception
	 */
	@SuppressWarnings({ "nls" })
	protected final static boolean checkRequiredItem(ResultDto resultDto, LaubeDto workflowDto, String[] items) throws LaubeException {

		log.traceStart("checkRequiredItem", resultDto, workflowDto, items);

		String getter = null;

		try {
			if (LaubeUtility.isEmpty(items)){
				return true;
			}
			for (String item : items) {
				getter = "get" + item.substring(0, 1).toUpperCase() + item.substring(1);
				final Method method =  workflowDto.getClass().getMethod(getter, new Class[]{});
				final Object[] param = null;
				final Object obj = method.invoke(workflowDto, param);

				if (LaubeUtility.isEmpty(obj)){
					resultDto.setSuccess(false);
					resultDto.setMessageId("E0019");
					return false;
				}
			}
			return true;

		} catch (final Exception e) {
			throw new LaubeException("checkRequiredItem", e);
		} finally {
			log.traceEnd("checkRequiredItem");
		}
	}

}
