package site.laube.dto.temporary;

import site.laube.utility.LaubeUtility;

/**
 * Dto class is the message class for the delivery of the framework and database.<br>
 * SuperClass of DTO class that holds the unit code.<br>
 * Minimum of items on the department master managed by Laube only does not hold.
 * This is the bare minimum of information needed by Laube.
 * If you want to use the Laube by linking to other systems,
 * you need to replace the department master for Laube in the department master of the relevant system.
 * If you want to replace, modify the framework is not necessary,
 * only replace the left join in the view table you are using.
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public class UnitDto extends CompanyDto {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 4575225513879036593L;

	/**
	 * to manage the unit code.<br>
	 */
	private String unitCode;

	/**
	 * to manage the department name.<br>
	 */
	private String unitName;

	/**
	 * to manage the manager code.<br>
	 */
	private String managerCode;

	/**
	 * to manage the manager name.<br>
	 */
	private String managerName;

	/**
	 * set the unit code.<br>
	 * @param unit code
	 */
	public final void setUnitCode(final String unitCode) {
		this.unitCode = unitCode;
	}

	/**
	 * get the unit code.<br>
	 * @return unit code
	 */
	public final String getUnitCode() {
		return this.unitCode;
	}

	/**
	 * set the unit name.<br>
	 * @param unitName unit name
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * get the unit name.<br>
	 *
	 * @return unit name
	 */
	public String getUnitName() {
		return this.unitName;
	}

	/**
	 * set the manager code.<br>
	 * @param manager code
	 */
	public final void setManagerCode(final String managerCode) {
		this.managerCode = managerCode;
	}

	/**
	 * get the manager code.<br>
	 * @return manager code
	 */
	public final String getManagerCode() {
		return this.managerCode;
	}

	/**
	 * set the manager name.<br>
	 * @param managerCode manager name
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	/**
	 * get the manager name.<br>
	 *
	 * @return manager name
	 */
	public String getManagerName() {
		return this.managerName;
	}

	/**
	 * All the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
