package site.laube.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.utility.LaubeProperties;
import site.laube.utility.LaubeUtility;

/**
 * Dto class is the rsult.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public final class ResultDto implements Serializable {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static Logger log = LoggerFactory.getLogger(ResultDto.class);

	/**
	 * normal message
	 */
	@SuppressWarnings("nls")
	public static final String MSG_N_N0001 = "N0001";

	private static final long serialVersionUID = 2824047883202335433L;
	private boolean status;
	private String messageId = null;
	private String message = null;
	private Object resultData;

	/**
	 * constractor
	 */
	public ResultDto(){

		setStatus(true);
		this.messageId = MSG_N_N0001;
		setMessage(this.messageId);
	}

	/**
	 * set the status.<br>
	 * @param status status
	 */
	public final void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * get the status.<br>
	 *
	 * @return status
	 */
	public final boolean isSuccess() {
		return this.status;
	}

	/**
	 * get the message id.<br>
	 *
	 * @return message id
	 */
	public final String getMessageId() {
		return this.messageId;
	}

	/**
	 * set the message id.<br>
	 * @param messageId message id
	 */
	public final void setMessageId(final String messageId) {

		this.messageId = messageId;
		setMessage(messageId);

	}

	/**
	 * get the message.<br>
	 *
	 * @return message
	 */
	public final String getMessage() {
		return this.message;
	}

	/**
	 * set the message.<br>
	 * @param messageId message id
	 */
	private final void setMessage(final String messageId) {
		this.message = LaubeProperties.getInstance().getValue(messageId);
	}

	/**
	 * get the result data.<br>
	 *
	 * @return result data
	 */
	public final Object getResultData() {
		return this.resultData;
	}

	/**
	 * set the result data.<br>
	 * @param resultData result data
	 */
	public final void setResultData(final Object resultData) {
		this.resultData = resultData;
	}

	/**
	 * all the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
