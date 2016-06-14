package site.laube.modelinterface;

import site.laube.dto.ActivityDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * ModelInterface is the class of the order to ensure that there is no reliance on the control class even if the change is DataBase.<br>
 * to manage the activities master model interface.<br>
 * when you use this framework be sure to implement this interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface ActivityModelInterface {

	/**
	 * removal of the common activity master (company units)<br>
	 * remove all of the common activity master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * removal of the common activity master (route units)<br>
	 * remove all of the common activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto delete(final String companyCode, final String routeCode) throws LaubeException;

	/**
	 * register the activity master.<br>
	 * @param ActivityDto activity master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto insert(final ActivityDto activityDto) throws LaubeException;

	/**
	 * update the activity master.<br>
	 * @param ActivityDto activity master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto update(final ActivityDto activityDto) throws LaubeException;

	/**
	 * company code, route code, the activity code, to find the activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @param activityCode activity code
	 * @return activity master
	 * @exception LaubeException
	 */
	public abstract ResultDto find(final String companyCode, final String routeCode, final String activityCode) throws LaubeException;

	/**
	 * company code, route code to find the activity master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return activity master
	 * @exception LaubeException
	 */
	public abstract ResultDto find(final String companyCode, final String routeCode) throws LaubeException;
}