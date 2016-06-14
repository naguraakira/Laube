package site.laube.modelinterface;

import site.laube.dto.ResultDto;
import site.laube.dto.RouteDto;
import site.laube.exception.LaubeException;

/**
 * ModelInterface is the class of the order to ensure that there is no reliance on the control class even if the change is DataBase.<br>
 * to manage the route master model interface.<br>
 * when you use this framework be sure to implement this interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface RouteModelInterface {

	/**
	 * removal of the route master (company units)<br>
	 * remove all of the route master.<br>
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException
	 */
 	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * removal of the route master (route units)<br>
	 * remove all of the route master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto delete(final String companyCode, final String routeCode) throws LaubeException;

	/**
	 * register the route master.<br>
	 * @param routeDto route master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto insert(final RouteDto routeDto) throws LaubeException;

	/**
	 * update the route master.<br>
	 * @param routeDto route master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto update(final RouteDto routeDto) throws LaubeException;

	/**
	 * spent check of the route master<br>
	 * if the route is already being used in the application form by the root master, and then return true.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return result
	 * @exception LaubeException
	 */
	@SuppressWarnings("static-method")
	public abstract boolean isOccupied(final String companyCode, final String routeCode) throws LaubeException;

	/**
	 * search the route master.<br>
	 * @param companyCode company code
	 * @return route master
	 * @exception LaubeException
	 */
	public abstract ResultDto find(final String companyCode) throws LaubeException;

	/**
	 * search the route master.<br>
	 * @param companyCode company code
	 * @param routeCode route code
	 * @return route master
	 * @exception LaubeException
	 */
	public abstract ResultDto find(final String companyCode, final String routeCode) throws LaubeException;

}