package site.laube.modelinterface;

import site.laube.dto.ResultDto;
import site.laube.dto.RoleUserDto;
import site.laube.exception.LaubeException;

/**
 * ModelInterface is the class of the order to ensure that there is no reliance on the control class even if the change is DataBase.<br>
 * To manage the employees by role model master interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface RoleUserModelInterface {

	/**
	 * deleting a role by the employee master (role / employee number units)<br>
	 * remove the role by the employee master.<br>
	 * @param companyCode company code
	 * @param roleCode    role code
	 * @param userCode    user code
	 * @return result
	 * @throws LaubeException
	 */
    public abstract ResultDto delete(final String companyCode, final String roleCode, final String userCode) throws LaubeException;

	/**
	 * register the role by the employee master.<br>
	 * @param userRoleDto user role Master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto insert(final RoleUserDto roleUserDto) throws LaubeException;

	/**
	 * update the role by the employee master.<br>
	 * @param userRoleDto user role master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto update(final RoleUserDto roleUserDto) throws LaubeException;

	/**
	 * search only single item to the appropriate record.<br>
	 * @param companyCode company code
	 * @param roleCode    role code
	 * @param userCode    user code
	 * @return role user master
	 * @exception LaubeException
	 */
	public abstract ResultDto find(final String companyCode, final String roleName, final String userCode) throws LaubeException;

	/**
	 * employees will find the role that belongs.<br>
	 * @param companyCode company code
	 * @param userCode    user code
	 * @return role user master
	 * @exception LaubeException
	 */
	public abstract ResultDto findByUserCode(final String companyCode, final String userCode) throws LaubeException;

	/**
	 * search for all employees that belong to the role.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return role user master
	 * @exception LaubeException
	 */
	public abstract ResultDto findByRoleCode(final String companyCode, final String roleCode) throws LaubeException;
}
