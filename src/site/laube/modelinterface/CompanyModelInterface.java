package site.laube.modelinterface;

import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * Model class is a class for manipulating the database.<br>
 * To manage the company master model interface.<br>
 * When you use this framework be sure to implement this interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface CompanyModelInterface {

	/**
	 * Search the company master.<br>
	 * @param companyCode company code
	 * @return company master
	 * @exception LaubeException
	 */
	@SuppressWarnings("nls")
	public abstract ResultDto find(final String companyCode) throws LaubeException;
}