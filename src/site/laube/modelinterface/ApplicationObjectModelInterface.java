package site.laube.modelinterface;

import site.laube.dto.ApplicationObjectDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * Model class is a class for manipulating the database.<br>
 * To manage the application Object model interface.<br>
 * When you use this framework be sure to implement this interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface ApplicationObjectModelInterface {

	/**
	 * search the application object.<br>
	 * @param companyCode Company code
	 * @param applicationNumber application Number
	 * @return LaubeResult
	 * @exception LaubeException
	 */
	public abstract ResultDto find(final String companyCode, final int applicationNumber) throws LaubeException;

	/**
	 * tegister the application object.<br>
	 * @param applicationObjectDto application object Dto
	 * @return rsult
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto insert(final ApplicationObjectDto applicationObjectDto) throws LaubeException;

	/**
	 * update the application object.<br>
	 * @param applicationObjectDto application object Dto
	 * @return rsult
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto update(final ApplicationObjectDto applicationObjectDto) throws LaubeException;
}