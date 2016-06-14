package site.laube.database;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.exception.LaubeException;
import site.laube.utility.LaubeProperties;


/**
 * to manage the connection control class.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
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
