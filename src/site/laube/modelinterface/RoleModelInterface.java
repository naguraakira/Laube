package site.laube.modelinterface;

import site.laube.dto.ResultDto;
import site.laube.dto.RoleDto;
import site.laube.exception.LaubeException;

/**
 * Model class is a class for manipulating the database.<br>
 * To manage the role master model interface.<br>
 * When you use this framework be sure to implement this interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface RoleModelInterface {

	/**
	 * delete role master (company units)<br>
	 * remove all of the role the master and the role by the employee master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * delete role master (role units)<br>
	 * remove all of the role the master and the role by the employee master.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return result
	 * @throws LaubeException
	 */
    public abstract ResultDto delete(final String companyCode, final String roleCode) throws LaubeException;

	/**
	 * register the role master.<br>
	 * @param roleDto role master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto insert(final RoleDto roleDto) throws LaubeException;

	/**
	 * update the role master.<br>
	 * @param roleDto role master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto update(final RoleDto roleDto) throws LaubeException;

	/**
	 * the appropriate role code checks in use.<br>
	 * tole code and returns true if the is not Zui role by employees master and string.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return use check
	 * @exception LaubeException
	 */
	public abstract boolean isOccupied(final String companyCode, final String roleCode) throws LaubeException;

	/**
	 * search for the role a master at the company code.<br>
	 * @param companyCode company code
	 * @return role master
	 * @exception LaubeException
	 */
	public abstract ResultDto find(final String companyCode) throws LaubeException;

	/**
	 * perform a full match search of role master.<br>
	 * @param companyCode company code
	 * @param roleCode role code
	 * @return role master
	 * @exception LaubeException
	 */
	public abstract ResultDto findByRoleCode(final String companyCode, final String roleCode) throws LaubeException;

	/**
	 * do the fuzzy search of role master.<br>
	 * @param companyCode company code
	 * @param roleName role name
	 * @return role master list
	 * @exception LaubeException
	 */
	public abstract ResultDto findByRoleName(final String companyCode, final String roleName) throws LaubeException;}
