package site.laube.utility;

import java.util.ResourceBundle;

/**
 * to manage the property class.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
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
