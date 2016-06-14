package site.laube.dto;

import site.laube.dto.temporary.CompanyDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeUtility;

/**
 * Model class is a class for manipulating the database.<br>
 * The class that manages the role master model.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public class RoleDto extends CompanyDto {

	/**
	 * to manage the log.<br>
	 */
	private static final long serialVersionUID = -737624750139517158L;

	/**
	 * to manage role code.<br>
	 */
	private String roleCode;

	/**
	 * to manage role name.<br>
	 */
	private String roleName;

	/**
	 * to manage number of registered employees<br>
	 */
	private int count;

	/**
	 * set the role code.<br>
	 * @param roleCode role code
	 * @throws LaubeException
	 */
	public final void setRoleCode(final String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * get the role code.<br>
	 *
	 * @return role code
	 */
	public final String getRoleCode() {
		return this.roleCode;
	}

	/**
	 * set the role name.<br>
	 * @param roleName role name
	 * @throws LaubeException
	 */
	public final void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	/**
	 * get the role name.<br>
	 *
	 * @return role name
	 */
	public final String getRoleName() {
		return this.roleName;
	}

	/**
	 * get the number of registered employees.<br>
	 *
	 * @return number of registered employees
	 */
	public final int getCount() {
		return this.count;
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
