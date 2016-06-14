package site.laube.dto.temporary;

import site.laube.dto.LaubeDto;
import site.laube.utility.LaubeUtility;

/**
 * Dto class is the message class for the delivery of the framework and database.<br>
 * SuperClass of DTO class that holds the company code.<br>
 * Minimum of items on the company master managed by Laube only does not hold.
 * This is the bare minimum of information needed by Laube.
 * If you want to use the Laube by linking to other systems,
 * you need to replace the company Master for Laube in the company master of the relevant system.
 * If you want to replace, modify the framework is not necessary,
 * only replace the left join in the view table you are using.
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 */
public class CompanyDto extends LaubeDto {

	/**
	 * To manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 4575225513879036593L;

	/**
	 * To manage the company code.<br>
	 */
	private String companyCode;

	/**
	 * To manage the company name.<br>
	 */
	private String companyName;

	/**
	 * set the company code.<br>
	 * @param companyCode Company Code
	 */
	public final void setCompanyCode(final String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * get the company code.<br>
	 * @return company code
	 */
	public final String getCompanyCode() {
		return this.companyCode;
	}

	/**
	 * set the company name.<br>
	 * @param company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * get the company name.<br>
	 *
	 * @return company name
	 */
	public String getCompanyName() {
		return this.companyName;
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
