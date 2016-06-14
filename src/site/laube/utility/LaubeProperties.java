package site.laube.utility;

import java.util.ResourceBundle;

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

public final class LaubeProperties {

	/**
	 * it manages the properties file name.
	 */
	private static ResourceBundle bundle = ResourceBundle.getBundle("laube");

	/**
	 * it manages the properties object.<br>
	 */
	private static LaubeProperties properties = new LaubeProperties();

	private LaubeProperties() {
		// Singleton
	}

	/**
	 * it gets the instance of the property object.<br>
	 * @return property object
	 */
	public static LaubeProperties getInstance() {
		return properties;
	}

	/**
	 * it gets the value of the property file.<br>
	 * @param key key
	 * @return value
	 */
	public final String getValue(final String key) {
		String value = null;
		value = bundle.getString(key);
		return value;
	}
}
