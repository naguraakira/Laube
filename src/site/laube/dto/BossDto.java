package site.laube.dto;

import site.laube.dto.temporary.UserDto;
import site.laube.utility.LaubeUtility;

/**
 * Dto class is the message class for the delivery of the framework and the database.<br>
 * The class that manages the boss master.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public class BossDto extends UserDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = -5545503360639439566L;

	/**
	 * to manage the application code.<br>
	 */
	private String applicationFormCode;

	/**
	 * to manage the application form name.<br>
	 */
	private String applicationFormName;

	/**
	 * to manage the company code of the immediate supervisor.<br>
	 */
	private String bossCompanyCode;

	/**
	 * to manage the company name of the immediate supervisor.<br>
	 */
	private String bossCompanyName;

	/**
	 * to manage the department code of the immediate supervisor.<br>
	 */
	private String bossUnitCode;

	/**
	 * to manage the department name of the immediate supervisor.<br>
	 */
	private String bossUnitName;

	/**
	 * to manage the employee number of the immediate supervisor.<br>
	 */
	private String bossUserCode;

	/**
	 * to manage the employee name of immediate supervisor.<br>
	 */
	private String bossUserName;

	/**
	 * set the application code.<br>
	 * @param applicationFormCode application form code
	 */
	public void setApplicationFormCode(String applicationFormCode) {
		this.applicationFormCode = applicationFormCode;
	}

	/**
	 * get the application code.<br>
	 * @return application form code
	 */
	public String getApplicationFormCode() {
		return this.applicationFormCode;
	}

	/**
	 * set the application form name.<br>
	 * @param applicationFormName application form name
	 */
	public final void setApplicationFormName(final String applicationFormName) {
		this.applicationFormName = applicationFormName;
	}

	/**
	 * get the application form name.<br>
	 * @return application form name
	 */
	public final String getApplicationFormName() {
		return this.applicationFormName;
	}

	/**
	 * set the company code of the immediate supervisor.<br>
	 * @param bossCompanyCode boss company code
	 */
	public void setBossCompanyCode(String bossCompanyCode) {
		this.bossCompanyCode = bossCompanyCode;
	}

	/**
	 * get the company code of the immediate supervisor.<br>
	 * @return boss company code
	 */
	public String getBossCompanyCode() {
		return bossCompanyCode;
	}

	/**
	 * set the company name of the immediate supervisor.<br>
	 * @param bossCompanyName boss company name
	 */
	public void setBossCompanyName(String bossCompanyName) {
		this.bossCompanyName = bossCompanyName;
	}

	/**
	 * get the company name of the immediate supervisor.<br>
	 * @return boss company name
	 */
	public String getBossCompanyName() {
		return bossCompanyName;
	}

	/**
	 * set the department code of the immediate supervisor.<br>
	 * @param bossUnitCode boss unit code
	 */
	public void setBossUnitCode(String bossUnitCode) {
		this.bossUnitCode = bossUnitCode;
	}

	/**
	 * get the department code of the immediate supervisor.<br>
	 * @return boss unit code
	 */
	public String getBossUnitCode() {
		return bossUnitCode;
	}

	/**
	 * set the department name of the immediate supervisor.<br>
	 * @param bossUnitName boss unit name
	 */
	public void setBossUnitName(String bossUnitName) {
		this.bossUnitName = bossUnitName;
	}

	/**
	 * get the department name of the immediate supervisor.<br>
	 * @return boss unit name
	 */
	public String getBossUnitName() {
		return bossUnitName;
	}

	/**
	 * set the employee number of the immediate supervisor.<br>
	 * @param bossUserCode boss user code
	 */
	public void setBossUserCode(String bossUserCode) {
		this.bossUserCode = bossUserCode;
	}

	/**
	 * get the employee number of the immediate supervisor.<br>
	 * @return boss user code
	 */
	public String getBossUserCode() {
		return bossUserCode;
	}

	/**
	 * set the employee name of the immediate supervisor.<br>
	 * @param bossUserName boss user name
	 */
	public void setBossUserName(String bossUserName) {
		this.bossUserName = bossUserName;
	}

	/**
	 * get the employee name of the immediate supervisor.<br>
	 * @return boss user name
	 */
	public String getBossUserName() {
		return bossUserName;
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
