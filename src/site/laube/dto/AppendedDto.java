package site.laube.dto;

import java.sql.Timestamp;

import site.laube.utility.LaubeUtility;

/**
 * Dto class is the message class for the delivery of the framework and the database.<br>
 * The class that manages the appended master.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public final class AppendedDto extends ApplicationObjectDto {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 1749200060270412825L;

	/**
	 * to manage the number of attachments.<br>
	 */
	private int approvalNumber;

	/**
	 * to manage the company code of the approver.<br>
	 */
	private String approvalCompanyCode;

	/**
	 * to manage the company name of the approver.<br>
	 */
	private String approvalCompanyName;

	/**
	 * to manage the unit code of the approver.<br>
	 */
	private String approvalUnitCode = null;

	/**
	 * to manage the unit name of the approver.<br>
	 */
	private String approvalUnitName = null;

	/**
	 * to manage the employee number of the approver.<br>
	 */
	private String approvalUserCode = null;

	/**
	 * to manage the employee name of the approver.<br>
	 */
	private String approvalUserName = null;

	/**
	 * to manage the path to the attached file.<br>
	 */
	private String approvalPath = null;

	/**
	 * to manage the attached file attachments date.<br>
	 */
	private Timestamp approvalDate = null;

	/**
	 * get the number of attachments.<br>
	 * @return approvalNumber number of attachments
	 */
	public final int getApprovalNumber() {
		return this.approvalNumber;
	}

	/**
	 * set the number of attachments.<br>
	 * @param approvalNumber number of attachments
	 */
	public final void setApprovalNumber(final int approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	/**
	 * set the company code of the approver.<br>
	 * @param approvalCompanyCode company code of the approver
	 */
	public final void setApprovalCompanyCode(final String approvalCompanyCode) {
		this.approvalCompanyCode = approvalCompanyCode;
	}

	/**
	 * get the company code of the approver.<br>
	 * @return company code of the approver.
	 */
	public final String getApprovalCompanyCode() {
		return this.approvalCompanyCode;
	}

	/**
	 * set the company name of the approver.<br>
	 * @param approvalCompanyName company name of the approver
	 */
	public void setApprovalCompanyName(String approvalCompanyName) {
		this.approvalCompanyName = approvalCompanyName;
	}

	/**
	 * get the company name of the approver.<br>
	 * @return company name of the approver
	 */
	public String getApprovalCompanyName() {
		return this.approvalCompanyName;
	}

	/**
	 * set the unit code of the approver.<br>
	 * @param approvalUnitCode unit code of the approver
	 */
	public final void setApprovalUnitCode(final String approvalUnitCode) {
		this.approvalUnitCode = approvalUnitCode;
	}

	/**
	 * get the unit code of the approver.<br>
	 * @return unit code of the approver
	 */
	public final String getApprovalUnitCode() {
		return this.approvalUnitCode;
	}

	/**
	 * set the unit name of the approver.<br>
	 * @param approvalUnitName unit name of the approver
	 */
	public void setApprovalUnitName(String approvalUnitName) {
		this.approvalUnitName = approvalUnitName;
	}

	/**
	 * get the unit name of the approver.<br>
	 * @return unit name of the approver
	 */
	public String getApprovalUnitName() {
		return this.approvalUnitName;
	}

	/**
	 * set the employee number of the approver.<br>
	 * @param approvalUserCode employee number of the approver
	 */
	public final void setApprovalUserCode(final String approvalUserCode) {
		this.approvalUserCode = approvalUserCode;
	}

	/**
	 * get the employee number of the approver.<br>
	 * @return employee number of the approver
	 */
	public final String getApprovalUserCode() {
		return this.approvalUserCode;
	}

	/**
	 * set the employee name of the approver.<br>
	 * @param approvalUserName employee name of the approver
	 */
	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	/**
	 * get the employee name of the approver.<br>
	 * @return employee name of the approver
	 */
	public String getApprovalUserName() {
		return this.approvalUserName;
	}

	/**
	 * set the path to the attached file.<br>
	 * @return approvalPath path to the attached file
	 */
	public final String getApprovalPath() {
		return this.approvalPath;
	}

	/**
	 * get the path to the attached file.<br>
	 * @param approvalPath path to the attached file
	 */
	public final void setApprovalPath(final String approvalPath) {
		this.approvalPath = approvalPath;
	}

	/**
	 * set the attached file attachments date.<br>
	 * @return approvalDate attached file attachments date
	 */
	public final void setApprovalDate(Timestamp approvalDate) {
		this.approvalDate = approvalDate;
	}

	/**
	 * get the attached file attachments date.<br>
	 * @return approvalDate attached file attachments date
	 */
	public final Timestamp getApprovalDate() {
		return this.approvalDate;
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
