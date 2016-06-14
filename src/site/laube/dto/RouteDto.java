package site.laube.dto;

import site.laube.dto.temporary.CompanyDto;
import site.laube.utility.LaubeUtility;

/**
 * Dto class is the message class for the delivery of the framework and the database.<br>
 * The class that manages the route master.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public class RouteDto extends CompanyDto {

	/**
	 * to manage the log.<br>
	 */
	private static final long serialVersionUID = 8811788410134049514L;

	/**
	 * to manage the route code.<br>
	 */
	private String routeCode;

	/**
	 * to manage the route name.<br>
	 */
	private String routeName;

	/**
	 * to manage the route type.<br>
	 */
	private int routeType;

	/**
	 * set the route code.<br>
	 * @param routeCode route code
	 */
	public final void setRouteCode(final String routeCode) {
		this.routeCode = routeCode;
	}

	/**
	 * get the route code.<br>
	 * @return route code
	 */
	public final String getRouteCode() {
		return this.routeCode;
	}

	/**
	 * set the route name.<br>
	 * @param routeName route name
	 */
	public final void setRouteName(final String routeName) {
		this.routeName = routeName;
	}

	/**
	 * get the route name.<br>
	 * @return route name
	 */
	public final String getRouteName() {
		return this.routeName;
	}

	/**
	 * set the route type.<br>
	 * @param routeType route type
	 */
	public void setRouteType(int routeType) {
		this.routeType = routeType;
	}

	/**
	 * get the route type.<br>
	 * @return route type
	 */
	public int getRouteType() {
		return routeType;
	}

	/**
	 * all the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
