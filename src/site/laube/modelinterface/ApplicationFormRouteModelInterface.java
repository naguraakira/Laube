package site.laube.modelinterface;

import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * ModelInterface is the class of the order to ensure that there is no reliance on the control class even if the change is DataBase.<br>
 * Manage application-specific root master model interface.<br>
 * When you use this framework be sure to implement this interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface ApplicationFormRouteModelInterface {

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param applicationFormCode application form code
	 * @param unitCode unit code
	 * @return Application-specific root master entity
	 * @exception LaubeException
	 */
	public abstract ResultDto find(final String companyCode, final String applicationFormCode, final String unitCode) throws LaubeException;

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param individualRouteCode individual route code
	 * @return Application-specific root master entity
	 * @exception LaubeException
	 */
	public abstract ResultDto findByIndividualRouteCode(final String companyCode, final String individualRouteCode) throws LaubeException;

	/**
	 * search of the application form by the root master.<br>
	 * @param companyCode company code
	 * @param commonRouteCode common route code
	 * @return Application-specific root master entity
	 * @exception LaubeException
	 */
	public abstract ResultDto findByCommonRouteCode(final String companyCode, final String commonRouteCode) throws LaubeException;
}