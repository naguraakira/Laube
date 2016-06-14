package site.laube.modelinterface;

import site.laube.dto.ApplicationFormDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * Model class is a class for manipulating the database.<br>
 * To manage the application master model interface.<br>
 * When you use this framework be sure to implement this interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface ApplicationFormModelInterface {

	/**
     * delete the application form master (company unit)<br>
     * remove the application-specific activity master and all of the application form master.
     * @param companyCode company code
     * @return result
     * @throws LaubeException
     */
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
     * delete the application classification master (application code unit)<br>
     * remove the application classification by Activision Byi master to match the application classification master.
     * @param companyCode Company code
     * @param applicationFormCode Application code
     * @return result
     * @throws LaubeException
     */
    public abstract ResultDto delete(final String companyCode, final String applicationFormCode) throws LaubeException;

	/**
	 * register the application form master.<br>
	 * @param applicationFormDto Application form master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto insert(final ApplicationFormDto applicationFormDto) throws LaubeException;

	/**
	 * to update the application form master.<br>
	 * @param applicationFormDto Application form master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto update(final ApplicationFormDto applicationFormDto) throws LaubeException;

	/**
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @return Application form master
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	public abstract ResultDto find(final String companyCode) throws LaubeException;

	/**
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @param applicationFormCode Application code
	 * @return Application form master
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	public abstract ResultDto findByApplicationFormCode(final String companyCode, final String applicationFormCode) throws LaubeException;

	/**
	 * search the application form master.<br>
	 * @param companyCode Company code
	 * @param applicationFormCode Application code
	 * @return Application form master
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	public abstract ResultDto findByApplicationClassificationCode(final String companyCode, final String applicationClassificationCode) throws LaubeException;
}