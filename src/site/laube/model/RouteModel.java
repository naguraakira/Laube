package site.laube.model;

import site.laube.exception.LaubeException;
import site.laube.modelinterface.RouteModelInterface;
import site.laube.utility.LaubeLogger;
import site.laube.utility.LaubeLoggerFactory;
import site.laube.utility.type.RouteType;

/*
 * Copyright (c) 2016, Ryuta Miki All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public abstract class RouteModel extends LaubeModel {

	/**
	 * to manage the log object.<br>
	 */
	private static LaubeLogger log = LaubeLoggerFactory.getLogger(RouteModel.class);

	/**
	 * it returns the root model class.<br>
	 * @param routeType route type
	 * @return route master
	 * @throws LaubeException please properly handle because it is impossible to continue exception.
	 */
	@SuppressWarnings("nls")
	public static RouteModelInterface factory(final RouteType routeType) throws LaubeException {

		log.traceStart("factory", routeType);

		switch(routeType) {

		case IndividualRoute:
			log.traceEnd("factory");
			return new IndividualRouteModel();

		case CommonRoute:
			log.traceEnd("factory");
			return new CommonRouteModel();

		case SpecialRoute:
			log.traceEnd("factory");
			return new SpecialRouteModel();

		default:
			throw new LaubeException("factory", "[workflowEngine] route type is incorrect. Please check it.[routeType] " + routeType);
		}
	}
}
