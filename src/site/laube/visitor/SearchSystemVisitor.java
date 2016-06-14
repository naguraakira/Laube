package site.laube.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.SearchSystemAcceptor;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;

/**
 * The class that manages the search system processing.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public abstract class SearchSystemVisitor implements LaubeVisitor {

	/**
	 * to manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(SearchSystemVisitor.class);

	/**
	 * it performs a search-based processing.<br>
	 * @param SearchtSystemAcceptor  acceptor
	 * @return ResultDto
	 * @exception LaubeException
	 */
	public abstract ResultDto visit(final SearchSystemAcceptor searchSystemAcceptor) throws LaubeException;
}
