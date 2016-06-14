package site.laube.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.ApprovalSystemAcceptor;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * The class that manages the approval of processing.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public abstract class ApprovalSystemVisitor implements LaubeVisitor {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApprovalSystemVisitor.class);

	/**
	 * do the approval process.<br>
	 * @param laubeRequestAcceptor acceptor
	 * @return ResultDto
	 * @exception LaubeException
	 */
	public abstract ResultDto visit(final ApprovalSystemAcceptor approvalSystemAcceptor) throws LaubeException;
}
