package site.laube.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.exception.LaubeException;
import site.laube.modelinterface.RouteModelInterface;
import site.laube.utility.type.RouteType;

/**
 * Model class is a class for manipulating the database.<br>
 * the class that manages the route master Model.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public abstract class RouteModel extends LaubeModel {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RouteModel.class);

	/**
	 * it returns the root model class.<br>
	 * @param routeType route type
	 * @return route master
	 * @throws LaubeException
	 */
	public static RouteModelInterface factory(RouteType routeType) throws LaubeException {

		switch(routeType) {

		case IndividualRoute:
			return new IndividualRouteModel();

		case CommonRoute:
			return new CommonRouteModel();

		case SpecialRoute:
			return new SpecialRouteModel();

		default:
			log.error("[workflowEngine] " + "route type is incorrect. Please check it.[routeType] " + routeType);
			throw new LaubeException("[workflowEngine] route type is incorrect. Please check it.[routeType] " + routeType);
		}
	}
}
