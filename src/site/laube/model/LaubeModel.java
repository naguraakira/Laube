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

		log.debug("[workflowEngine] " + "getConnection Start");

		try {
			if ((connection == null) || (connection.isClosed())) {
				connection = DbConnectManager.getConnection();
				statement = connection.createStatement();
			}

		} catch (Exception e) {
			log.error("[workflowEngine] " + e);
			e.printStackTrace();
		}

		log.debug("[workflowEngine] " + "getConnection End" + "[return]:" + connection);
		return connection;
	}

	/**
	 * commit<br>
	 *
	 * @throws LaubeException
	 */
	public void commit() throws LaubeException {

		try {
			connection.commit();
		} catch(Exception e){
			log.error("[workflowEngine] " + e);
			e.printStackTrace();
		}
	}

	/**
	 * set ResultSet to Dto.<br>
	 *
	 * @param resultSet ResultSet
	 * @param workflowDto before data
	 * @return after data
	 * @throws LaubeException
	 */
	public ArrayList<LaubeDto> conversion(final ResultSet resultSet, LaubeDto workflowDto) throws LaubeException {

		log.debug("[workflowEngine] " + "conversion Start");

		ArrayList<LaubeDto> result = new ArrayList<LaubeDto>();

		try {

			do {
				LaubeDto updateWorkflowDto = resultSetToDto(resultSet, workflowDto);

				if (updateWorkflowDto == null){
					break;
				}

				log.debug("[workflowEngine] " + "[updateWorkflowDto] " + updateWorkflowDto);
				result.add(updateWorkflowDto);

			} while (resultSet.next());

		} catch (Exception e) {
			log.error("[workflowEngine] " + e);
			throw new LaubeException(e);
		}

		log.debug("[workflowEngine] " + "conversion End" + "[return]:" + result);
		return result;
	}

	/**
	 * set ResultSet to Dto.<br>
	 *
	 * @param resultSet ResultSet
	 * @param workflowDto before data
	 * @return after data
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "static-method", "nls", "boxing" })
	public LaubeDto resultSetToDto(final ResultSet resultSet, final LaubeDto wWorkflowDto) throws LaubeException {

		log.debug("[workflowEngine] " + "resultSetToDto Start");

		LaubeDto laubeDto = wWorkflowDto;

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
				} else {
					method = laubeDto.getClass().getMethod(setter, new Class[] { String.class });
					method.invoke(laubeDto, resultSet.getString(name));
				}
			}
		} catch (Exception e) {
			log.error("[workflowEngine] " + e);
			throw new LaubeException(e);

		}
		log.debug("[workflowEngine] " + "resultSetToDto End" + "[return]:" + laubeDto);
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

		log.debug("[workflowEngine] " + "checkRequiredItem Start");

		String getter = null;

		try {
			if (items == null) {
				return true;
			}
			for (String item : items) {
				getter = "get" + item.substring(0, 1).toUpperCase() + item.substring(1);
				Method method =  workflowDto.getClass().getMethod(getter, new Class[]{});
				Object[] param = null;
				Object obj = method.invoke(workflowDto, param);

				if (obj == null) {
					log.error("[workflowEngine] " + "nothing item");
					resultDto.setStatus(false);
					resultDto.setMessageId("E0019");
					log.debug("[workflowEngine] " + "checkRequiredItem End" + "[return]:false");
					return false;
				}

			}
		} catch (Exception e) {
			log.error("[workflowEngine] " + e);
			throw new LaubeException(e);
		}

		log.debug("[workflowEngine] " + "checkRequiredItem End" + "[return]:true");
		return true;
	}

}
