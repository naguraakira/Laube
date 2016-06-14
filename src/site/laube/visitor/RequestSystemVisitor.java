package site.laube.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.RequestSystemAcceptor;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * The class that manages the application-based processing.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public abstract class RequestSystemVisitor implements LaubeVisitor {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RequestSystemVisitor.class);

	/**
	 * make the application-based processing.<br>
	 * @param RequestSystemAcceptor acceptor
	 * @return ResultDto
	 * @exception LaubeException
	 */
	public abstract ResultDto visit(final RequestSystemAcceptor requestSystemAcceptor) throws LaubeException;
}
