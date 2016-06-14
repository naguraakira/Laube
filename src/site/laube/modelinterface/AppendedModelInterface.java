package site.laube.modelinterface;

import site.laube.dto.AppendedDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * Model class is a class for manipulating the database.<br>
 * To manage the appended model interface.<br>
 * When you use this framework be sure to implement this interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface AppendedModelInterface {

	/**
	 * delete the appended object<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException
	*/
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * delete the appended object(user unit)<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto delete(final String companyCode, final int applicationNumber) throws LaubeException;

	/**
	 * register the appended object.<br>
	 * @param appendedDto appended object
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto insert(final AppendedDto appendedDto) throws LaubeException;

	/**
	 * To update the appended object.<br>
	 * @param appendedDto appended object
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto update(final AppendedDto appendedDto) throws LaubeException;

	/**
	 * Search the appended object.<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @return result
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	public abstract ResultDto find(final String companyCode, final int applicationNumber) throws LaubeException;
}