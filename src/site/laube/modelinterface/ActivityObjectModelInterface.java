package site.laube.modelinterface;

import java.util.List;

import site.laube.dto.ActivityObjectDto;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * Model class is a class for manipulating the database.<br>
 * To manage the activity Object model interface.<br>
 * When you use this framework be sure to implement this interface.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public interface ActivityObjectModelInterface {

	/**
	 * register the activity object.<br>
	 * @param ActivityObjectDtos activity object dto list
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto insert(final List<ActivityObjectDto> ActivityObjectDtos) throws LaubeException;

	/**
	 * register the activity object.<br>
	 * @param ActivityObjectDto activity object dto
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto insert(final ActivityObjectDto activityObjectDto) throws LaubeException;

	/**
	 * update the activity object.<br>
	 * @param activityObjectDto activity object Dto
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto update(final ActivityObjectDto activityObjectDto) throws LaubeException;

	/**
	 * delete the activity object.<<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto delete(final String companyCode, final int applicationNumber) throws LaubeException;

	/**
	 * find the activity object.<<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @param approvalCompanyCode approval company code
	 * @param approvalUnitCode approval unit code
	 * @param approvalUserCode approval userCode code
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto find(final String companyCode, final int applicationNumber, final String approvalCompanyCode, final String approvalUnitCode, final String approvalUserCode, final int applovalUserStatus) throws LaubeException;

	/**
	 * find the activity object by arrival.<<br>
	 * @param companyCode company code
	 * @param applicationNumber application number
	 * @param approvalCompanyCode approval company code
	 * @param approvalUnitCode approval unit code
	 * @param approvalUserCode approval userCode code
	 * @return result
	 * @throws LaubeException
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public abstract ResultDto findByArrival(final String companyCode, final int applicationNumber, final String approvalCompanyCode, final String approvalUnitCode, final String approvalUserCode) throws LaubeException;
}