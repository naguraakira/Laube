package site.laube.modelinterface;

import site.laube.dto.ApplicationClassificationDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * ModelInterface is the class of the order to ensure that there is no reliance on the control class even if the change is DataBase.<br>
 * To manage the application classification master model interface.<br>
 * When you use this framework be sure to implement this interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface ApplicationClassificationModelInterface {

	/**
	 * delete the application classification master (company unit)<br>
	 * Remove all of the application classification master and the applicant by category activity master.
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * delete the application classification master (application code unit)<br>
	 * Remove the application classification by activity master to match the application classification master.
	 * @param companyCode company code
	 * @param applicationClassificationCode Application classification code
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto delete(final String companyCode, final String applicationClassificationCode) throws LaubeException;

	/**
	 * register the application classification master.<br>
	 * @param applicationClassificationDto Application classification master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto insert(final ApplicationClassificationDto applicationClassificationDto) throws LaubeException;

	/**
	 * to update the application classification master.<br>
	 * @param applicationClassificationDto Application classification master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto update(final ApplicationClassificationDto applicationClassificationDto) throws LaubeException;

	/**
	 * search the application form master.<br>
	 * @param companyCode company code
	 * @return Application form master
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	public abstract ResultDto find(final String companyCode) throws LaubeException;

	/**
	 * search the application form master.<br>
	 * @param companyCode company code
	 * @param applicationClassificationCode Application classification code
	 * @return Application form master
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	public abstract ResultDto findByApplicationClassificationCode(final String companyCode, final String applicationClassificationCode) throws LaubeException;
}