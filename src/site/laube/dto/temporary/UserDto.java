package site.laube.dto.temporary;

import site.laube.utility.LaubeUtility;

/**
 * Dto class is the message class for the delivery of the framework and database.<br>
 * Super Class of DTO class that holds the unit code / employee number.<br>
 * Minimum of items on the employee master managed by Laube only does not hold.
 * This is the bare minimum of information needed by Laube.
 * If you want to use the Laube by linking to other systems,
 * you need to replace the employee master for Laube in the employee master of the relevant system.
 * If you want to replace, modify the framework is not necessary,
 * only replace the left join in the view table you are using.
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public class UserDto extends UnitDto {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 4575225513879036593L;

	/**
	 * to manage the employee number.<br>
	 */
	private String userCode;

	/**
	 * to manage the employee name.<br>
	 */
	private String userName;

	/**
	 * set the employee number.<br>
	 * @param userCode employee number
	 */
	public final void setUserCode(final String userCode) {
		this.userCode = userCode;
	}

	/**
	 * get the employee number.<br>
	 * @return employee number
	 */
	public final String getUserCode() {
		return this.userCode;
	}

	/**
	 * set the employee name.<br>
	 * @param userName employee name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * get the employee name.<br>
	 *
	 * @return employee name
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * All the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
