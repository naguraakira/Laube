package site.laube.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import site.laube.utility.LaubeUtility;

/**
 * Dto class is the message class for the delivery of the framework and the database.<br>
 * The class that manages the all dto.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public class LaubeDto implements Serializable {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = 4575225513879036593L;

	/**
	 * to manage the sarogate key. <br>
	 */
	private int id;

	/**
	 * to manage the create date time. <br>
	 */
	private Timestamp createDateTime;

	/**
	 * to manage the create user id. <br>
	 */
	private String createUserId;

	/**
	 * to manage the update date time. <br>
	 */
	private Timestamp updateDateTime;

	/**
	 * to manage the update user id. <br>
	 */
	private String updateUserId;

	/**
	 * set the sarogate key.<br>
	 * @param id sarogate key
	 */
	public final void setId(int id) {
		this.id = id;
	}

	/**
	 * get the sarogate key.<br>
	 *
	 * @return sarogate key
	 */
	public final int getId() {
		return this.id;
	}

	/**
	 * set the create date time.<br>
	 * @param createDateTime create date time
	 */
	public final void setCreateDateTime(final Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * get the create date time.<br>
	 *
	 * @return create date time
	 */
	public final Timestamp getCreateDateTime() {
		return this.createDateTime;
	}

	/**
	 * set the create user id.<br>
	 * @param createUserId create user id
	 */
	public final void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * get the create user id.<br>
	 *
	 * @return create user id
	 */
	public final String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * set the update date time.<br>
	 * @param updateDateTime update date time
	 */
	public final void setUpdateDateTime(final Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	/**
	 * get the update date time.<br>
	 * @return update date time
	 */
	public final Timestamp getUpdateDateTime() {
		return this.updateDateTime;
	}

	/**
	 * set the update user id.<br>
	 * @param updateUserId update user id
	 */
	public final void setUpdateUserId(final String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * get the update user id.<br>
	 * @return update user id
	 */
	public final String getUpdateUserId() {
		return this.updateUserId;
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
