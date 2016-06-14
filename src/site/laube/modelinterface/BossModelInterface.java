package site.laube.modelinterface;

import site.laube.dto.BossDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * Model class is a class for manipulating the database.<br>
 * To manage the boss model interface.<br>
 * When you use this framework be sure to implement this interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface BossModelInterface {

	/**
	 * delete the boss master<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @return result
	 * @throws LaubeException
	*/
	public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * delete the boss master(user unit)<br>
	 * remove the application-specific all of the boss master.
	 * @param companyCode company code
	 * @param userCode user code
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto delete(final String companyCode, final String userCode) throws LaubeException;

	/**
	 * register the boss master.<br>
	 * @param applicationFormDto Application form master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto insert(final BossDto bossDto) throws LaubeException;

	/**
	 * To update the boss master.<br>
	 * @param bosseDto boss master
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto update(final BossDto bosseDto) throws LaubeException;

	/**
	 * return all of the boss set to the highest.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @param userCode user code
	 * @param applicationFormCode application form code
	 * @return result
	 * @throws LaubeException
	 */
	public abstract ResultDto findByChainOfResposibility(final String companyCode, final String unitCode, final String userCode, final String applicationFormCode) throws LaubeException;

	/**
	 * Search the boss master.<br>
	 * Boss of the settings can be set to the [department] [application] unit or [department] unit.<br>
	 * If both are set, and returns the setting of the [department] [application] unit.<br>
	 * If the application code is null, return the setting boss is set to [department] unit.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @param userCode user code
	 * @param applicationFormCode application form code
	 * @return result
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	public abstract ResultDto find(final String companyCode, final String unitCode, final String userCode, final String applicationFormCode) throws LaubeException;
}