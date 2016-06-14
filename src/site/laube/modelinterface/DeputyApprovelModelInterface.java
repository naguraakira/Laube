package site.laube.modelinterface;

import site.laube.dto.DeputyApprovelDto;
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
public interface DeputyApprovelModelInterface {

	/**
	 * delete the deputy approval master.<br>
	 * @param companyCode company code
	 * @return result
	 * @exception LaubeException
	 */
    public abstract ResultDto delete(final String companyCode) throws LaubeException;

	/**
	 * delete the deputy approval master.<br>
	 * @param companyCode company code
	 * @param userCode user code
	 * @return result
	 * @exception LaubeException
	 */
    public abstract ResultDto delete(final String companyCode, final String userCode) throws LaubeException;

	/**
	 * insert the deputy approval master.<br>
	 * @param deputyApprovelDto deputy approvel dto
	 * @return result
	 * @exception LaubeException
	 */
	public abstract ResultDto insert(final DeputyApprovelDto deputyApprovelDto) throws LaubeException;

	/**
	 * update the deputy approval master.<br>
	 * @param deputyApprovelDto deputy approvel dto
	 * @return result
	 * @exception LaubeException
	 */
	public abstract ResultDto update(final DeputyApprovelDto deputyApprovelDto) throws LaubeException;

	/**
	 * search the deputy approval master.<br>
	 * @param companyCode company code
	 * @param unitCode unit code
	 * @param userCode user code
	 * @return result
	 * @exception LaubeException
	 */
	public abstract ResultDto find(final String companyCode, final String unitCode, final String userCode) throws LaubeException;

	/**
	 * search the deputy approval master.<br>
	 * @param companyCode company code
	 * @param deputyApproverlUserCode deputy approverl user code
	 * @return result
	 * @exception LaubeException
	 */
	 public abstract ResultDto find(final String companyCode, final String deputyApproverlUserCode) throws LaubeException;
}