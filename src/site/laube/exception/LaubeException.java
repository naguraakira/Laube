package site.laube.exception;

/**
 * Dto class is the message class for the delivery of the framework and the database.<br>
 * The class that manages the exception.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public class LaubeException extends Exception {

	/**
	 * sirial version
	 */
	private static final long serialVersionUID = 2314117173345596443L;

	/**
	 * constractor
	 * @param exception
	 */
	public LaubeException(Exception e) {
		super(e);
	}

	/**
	 * constractor
	 * @param message
	 */
	public LaubeException(String message) {
		super(message);
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
	}

}
