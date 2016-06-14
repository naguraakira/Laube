package site.laube.dto;

import site.laube.utility.LaubeUtility;

/**
 * Dto class is the message class for the delivery of the framework and the database.<br>
 * The class that manages the activity master.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public final class ActivityDto extends PartyDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = -4918780328972650732L;

	/**
	 * to manage the activity code. <br>
	 */
	private int activityCode;

	/**
	 * to manage the route type. <br>
	 * 1:Individual route<br>
	 * 2:Common route<br>
	 * 3:Special route<br>
	 */
	private int routeType;

	/**
	 * to manage the approval company code.<br>
	 */
	private String approvalCompanyCode = null;

	/**
	 * to manage the company name of the approver.<br>
	 */
	private String approvalCompanyName;

	/**
	 * to manage the approval role code. <br>
	 */
	private String approvalRoleCode;

	/**
	 * to manage the approval role name. <br>
	 */
	private String approvalRoleName;

	/**
	 * to manage the approval unit code.<br>
	 */
	private String approvalUnitCode = null;

	/**
	 * to manage the unit name of the approver.<br>
	 */
	private String approvalUnitName = null;

	/**
	 * to manage the approval user code.<br>
	 */
	private String approvalUserCode = null;

	/**
	 * to manage the employee name of the approver.<br>
	 */
	private String approvalUserName = null;

	/**
	 * to manage the function.<br>
	 */
	private int function = 0;

	/**
	 * to manage the next party code.<br>
	 */
	private String nextPartyCode;

	/**
	 * set the activity code.<br>
	 * @param activityCode activity code
	 */
	public final void setActivityCode(final int activityCode) {
		this.activityCode = activityCode;
	}

	/**
	 * get the activity code.<br>
	 * @return activity code
	 */
	public final int getActivityCode() {
		return this.activityCode;
	}

	/**
	 * set the route type.<br>
	 * @param routeType route type
	 */
	public void setRouteType(int routeType) {
		this.routeType = routeType;
	}

	/**
	 * get the route type.<br>
	 * @return route type
	 */
	public int getRouteType() {
		return routeType;
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
	 * @return company code of the approver
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
	 * set the approval role code.<br>
	 * @param approvalRoleCode approval role code
	 */
	public final void setApprovalRoleCode(final String approvalRoleCode) {
		this.approvalRoleCode = approvalRoleCode;
	}

	/**
	 * get the approval role code.<br>
	 * @return approval role code
	 */
	public final String getApprovalRoleCode() {
		return this.approvalRoleCode;
	}

	/**
	 * set the approval role name.<br>
	 * @param approvalRoleName approval role name
	 */
	public final void setApprovalRoleName(final String approvalRoleName) {
		this.approvalRoleName = approvalRoleName;
	}

	/**
	 * get the approval role name.<br>
	 * @return approval role name
	 */
	public final String getApprovalRoleName() {
		return this.approvalRoleName;
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
	 * set the approval function.<br>
	 * @param function approval function
	 */
	public final void setFunction(final int function) {
		this.function = function;
	}

	/**
	 * get the approval function.<br>
	 * @return approval function
	 */
	public final int getFunction() {
		return this.function;
	}

	/**
	 * set the next party code.<br>
	 * @param nextPartyCode next party code
	 */
	public final void setNextPartyCode(final String nextPartyCode) {
		this.nextPartyCode = nextPartyCode;
	}

	/**
	 * get the next party code.<br>
	 * @return next party code
	 */
	public final String getNextPartyCode() {
		return this.nextPartyCode;
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
