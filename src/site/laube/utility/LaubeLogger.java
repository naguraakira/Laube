package site.laube.utility;
import org.slf4j.Logger;

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

public class LaubeLogger{

	private Logger logger = null;

	public LaubeLogger(Logger logger) {
		this.logger = logger;
	}

	@SuppressWarnings("nls")
	private final String crownName = "[workflowEngine] ";
	@SuppressWarnings("nls")
	private final String argument  = "[argument]";
	@SuppressWarnings("nls")
	private final String space     = " ";
	@SuppressWarnings("nls")
	private final String start     = "start";
	@SuppressWarnings("nls")
	private final String end       = "end";
	@SuppressWarnings("nls")
	private final String unknown   = "unknown";

	/**
	 * trace start log<br>
	 * @param traceName trace name
	 */
	public final void traceStart(final String traceName){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.debug(this.crownName + this.unknown + this.space + this.start);
		}else{
			this.logger.debug(this.crownName + traceName + this.space + this.start);
		}
	}

	/**
	 * trace start log<br>
	 * @param traceName trace name
	 * @param o1 argument1
	 */
	public final void traceStart(final String traceName, final Object o1){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.trace(this.crownName + this.unknown + this.space + this.start);
		}else{
			this.logger.trace(this.crownName + traceName + this.space + this.start);
		}
		this.logger.debug(this.crownName + this.argument);
		this.logger.debug(this.crownName + o1);
	}

	/**
	 * trace start log<br>
	 * @param traceName trace name
	 * @param o1 argument1
	 * @param o2 argument2
	 */
	public final void traceStart(final String traceName, final Object o1, final Object o2){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.trace(this.crownName + this.unknown + this.space + this.start);
		}else{
			this.logger.trace(this.crownName + traceName + this.space + this.start);
		}
		this.logger.debug(this.crownName + this.argument);
		this.logger.debug(this.crownName + o1);
		this.logger.debug(this.crownName + o2);
	}

	/**
	 * trace start log<br>
	 * @param traceName trace name
	 * @param o1 argument1
	 * @param o2 argument2
	 * @param o3 argument3
	 */
	public final void traceStart(final String traceName, final Object o1, final Object o2, final Object o3){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.trace(this.crownName + this.unknown + this.space + this.start);
		}else{
			this.logger.trace(this.crownName + traceName + this.space + this.start);
		}
		this.logger.debug(this.crownName + this.argument);
		this.logger.debug(this.crownName + o1);
		this.logger.debug(this.crownName + o2);
		this.logger.debug(this.crownName + o3);
	}

	/**
	 * trace start log<br>
	 * @param traceName trace name
	 * @param o1 argument1
	 * @param o2 argument2
	 * @param o3 argument3
	 * @param o4 argument4
	 */
	public final void traceStart(final String traceName, final Object o1, final Object o2, final Object o3, final Object o4){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.trace(this.crownName + this.unknown + this.space + this.start);
		}else{
			this.logger.trace(this.crownName + traceName + this.space + this.start);
		}
		this.logger.debug(this.crownName + this.argument);
		this.logger.debug(this.crownName + o1);
		this.logger.debug(this.crownName + o2);
		this.logger.debug(this.crownName + o3);
		this.logger.debug(this.crownName + o4);
	}

	/**
	 * trace start log<br>
	 * @param traceName trace name
	 * @param o1 argument1
	 * @param o2 argument2
	 * @param o3 argument3
	 * @param o4 argument4
	 * @param o5 argument5
	 */
	public final void traceStart(final String traceName, final Object o1, final Object o2, final Object o3, final Object o4, final Object o5){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.trace(this.crownName + this.unknown + this.space + this.start);
		}else{
			this.logger.trace(this.crownName + traceName + this.space + this.start);
		}
		this.logger.debug(this.crownName + this.argument);
		this.logger.debug(this.crownName + o1);
		this.logger.debug(this.crownName + o2);
		this.logger.debug(this.crownName + o3);
		this.logger.debug(this.crownName + o4);
		this.logger.debug(this.crownName + o5);
	}

	/**
	 * trace start log<br>
	 * @param traceName trace name
	 * @param o1 argument1
	 * @param o2 argument2
	 * @param o3 argument3
	 * @param o4 argument4
	 * @param o5 argument5
	 * @param o6 argument6
	 */
	public final void traceStart(final String traceName, final Object o1, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.trace(this.crownName + this.unknown + this.space + this.start);
		}else{
			this.logger.trace(this.crownName + traceName + this.space + this.start);
		}
		this.logger.debug(this.crownName + this.argument);
		this.logger.debug(this.crownName + o1);
		this.logger.debug(this.crownName + o2);
		this.logger.debug(this.crownName + o3);
		this.logger.debug(this.crownName + o4);
		this.logger.debug(this.crownName + o5);
		this.logger.debug(this.crownName + o6);
	}

	/**
	 * trace end log<br>
	 * @param traceName trace name
	 */
	public final void traceEnd(final String traceName){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.trace(this.crownName + this.unknown + this.space + this.end);
		}else{
			this.logger.trace(this.crownName + traceName + this.space + this.end);
		}
	}

	/**
	 * trace end log<br>
	 * @param traceName trace name
	 * @param o1 return value
	 */
	public final void traceEnd(final String traceName, final Object o1){

		this.logger.debug(this.crownName + o1);

		if (LaubeUtility.isBlank(traceName)){
			this.logger.trace(this.crownName + this.unknown + this.space + this.end);
		}else{
			this.logger.trace(this.crownName + traceName + this.space + this.end);
		}
	}

	/**
	 * message log<br>
	 * @param traceName trace name
	 * @param contents contents
	 */
	public final void message(final String traceName, final String contents){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.debug(this.crownName + this.unknown + this.space + contents);
		}else{
			this.logger.debug(this.crownName + traceName + this.space  + contents);
		}

	}

	/**
	 * information log<br>
	 * @param traceName trace name
	 * @param contents contents
	 */
	public final void information(final String traceName, final String contents){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.info(this.crownName + this.unknown + this.space + contents);
		}else{
			this.logger.info(this.crownName + traceName + this.space  + contents);
		}
	}

	/**
	 * crush log<br>
	 * @param traceName trace name
	 * @param contents contents
	 */
	public final void crush(final String traceName, final String contents){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.error(this.crownName + this.unknown + this.space + contents);
		}else{
			this.logger.error(this.crownName + traceName + this.space  + contents);
		}
	}

	/**
	 * crush log<br>
	 * @param traceName trace name
	 * @param messageId message id
	 * @param message message
	 */
	public final void crush(final String traceName, final String messageId, final String message){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.error(this.crownName + this.unknown + this.space  + messageId + this.space + message);
		}else{
			this.logger.error(this.crownName + traceName + this.space  + messageId + this.space + message);
		}
	}

	/**
	 * crush log<br>
	 * @param traceName trace name
	 * @param contents contents
	 */
	public final void crush(final String traceName, final Exception e){

		if (LaubeUtility.isBlank(traceName)){
			this.logger.error(this.crownName + this.unknown + this.space  + e);
		}else{
			this.logger.error(this.crownName + traceName + this.space  + e);
		}
	}
}
