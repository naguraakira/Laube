package site.laube.model;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.database.DbConnectManager;
import site.laube.dto.LaubeDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
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

public class LaubeModel {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(LaubeModel.class);

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
	 * to manage the properties.<br>
	 */
	protected LaubeProperties properties = LaubeProperties.getInstance();

	/**
	 * constractor<br>
	 * create connection<br>
	 */
	public LaubeModel() {
		getConnection();
	}

	/**
	 * get the connection.<br>
	 *
	 * @return connection
	 */
	public final Connection getConnection() {

		log.info("[workflowEngine] " + "getConnection start");

		try {
			if ((LaubeUtility.isEmpty(connection)) || (connection.isClosed())) {
				connection = DbConnectManager.getConnection();
				statement = connection.createStatement();
			}

		} catch (final Exception e) {
			log.info("[workflowEngine] " + "getConnection end");
			e.printStackTrace();
		}

		log.info("[workflowEngine] " + "getConnection end");
		return connection;
	}

	/**
	 * commit<br>
	 *
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	public void commit() throws LaubeException {

		log.info("[workflowEngine] " + "commit start");

		try {
			connection.commit();
			log.info("[workflowEngine] " + "commit end");
		} catch(final Exception e){
			log.info("[workflowEngine] " + "commit end");
			e.printStackTrace();
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
	public ArrayList<LaubeDto> conversion(final ResultSet resultSet, LaubeDto workflowDto) throws LaubeException {

		log.info("[workflowEngine] " + "conversion start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[resultSet]: " + resultSet);
		log.info("[workflowEngine] " + "[workflowDto]: " + workflowDto);

		final ArrayList<LaubeDto> result = new ArrayList<LaubeDto>();

		try {

			do {
				LaubeDto updateWorkflowDto = resultSetToDto(resultSet, workflowDto);

				if (LaubeUtility.isEmpty(updateWorkflowDto)){
					break;
				}

				log.debug("[workflowEngine] " + "[updateWorkflowDto] " + updateWorkflowDto);
				result.add(updateWorkflowDto);

			} while (resultSet.next());

		} catch (final Exception e) {
			log.info("[workflowEngine] " + "conversion end");
			throw new LaubeException(e);
		}

		log.info("[workflowEngine] " + "conversion end");
		return result;
	}

	/**
	 * set ResultSet to Dto.<br>
	 *
	 * @param resultSet result set
	 * @param workflowDto workflow dto
	 * @return after data
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings({ "static-method", "nls", "boxing" })
	public LaubeDto resultSetToDto(final ResultSet resultSet, final LaubeDto workflowDto) throws LaubeException {

		log.info("[workflowEngine] " + "resultSetToDto start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[resultSet]: " + resultSet);
		log.info("[workflowEngine] " + "[workflowDto]: " + workflowDto);

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

				log.debug("[workflowEngine] setter = " + setter);

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
		} catch (final Exception e) {
			log.info("[workflowEngine] " + "resultSetToDto end");
			throw new LaubeException(e);

		}
		log.info("[workflowEngine] " + "resultSetToDto end");
		return laubeDto;
	}

	/**
	 * check Item.<br>
	 * @param wFResult result
	 * @param LaubeDto dto
	 * @param items item
	 * @return result
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-method", "nls" })
	protected boolean checkRequiredItem(ResultDto resultDto, LaubeDto workflowDto, String[] items) throws LaubeException {

		log.info("[workflowEngine] " + "checkRequiredItem start");
		log.info("[workflowEngine] " + "[argument]");
		log.info("[workflowEngine] " + "[resultDto]: " + resultDto);
		log.info("[workflowEngine] " + "[workflowDto]: " + workflowDto);
		log.info("[workflowEngine] " + "[items]: " + items);

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
					resultDto.setStatus(false);
					resultDto.setMessageId("E0019");
					log.info("[workflowEngine] " + "checkRequiredItem end" );
					return false;
				}

			}
		} catch (final Exception e) {
			log.info("[workflowEngine] " + "checkRequiredItem end" );
			throw new LaubeException(e);
		}

		log.info("[workflowEngine] " + "checkRequiredItem end" );
		return true;
	}

}
