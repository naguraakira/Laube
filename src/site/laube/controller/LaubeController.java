package site.laube.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.ApprovalSystemAcceptor;
import site.laube.acceptor.RequestSystemAcceptor;
import site.laube.acceptor.SearchSystemAcceptor;
import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.visitor.ApprovalSystemVisitor;
import site.laube.visitor.RequestSystemVisitor;
import site.laube.visitor.SearchSystemVisitor;

/**
 * It is the adapter class for laube. Application, all of the operations,
 * such as approval is performed by running the method of this class.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public final class LaubeController implements Serializable {

	/**
	 * to manage the log.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(LaubeController.class);

	/**
	 * Make the application-based processing.<br>
	 * @param laubeRequestSystemAcceptor Application business data
	 * @param laubeVisitor Pass the visitor.
	 * The application business can not be passed only visitor class to perform the application business.
	 * If you pass any other visitor class, an exception occurs.
	 * @return Processing result
	 * @throws LaubeException
	 */
	public final ResultDto doSearch(final SearchSystemAcceptor searchSystemAcceptor,SearchSystemVisitor searchSystemVisitor) {

		log.debug("[workflowEngine] " + "doSearch start");

		ResultDto resultDto = null;

		try {
			resultDto = searchSystemAcceptor.accept(searchSystemVisitor);

		}catch(LaubeException e){
			log.error("[workflowEngine] " + e.getMessage());
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.error("[workflowEngine] " + "doSearch end");
			return resultDto;

		}catch(Exception e){
			log.error("[workflowEngine] " + e.getMessage());
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.error("[workflowEngine] " + "doSearch end");
			return resultDto;
		}
		log.debug("[workflowEngine] " + "doSearch end");
		return resultDto;
	}

	/**
	 * Make the application-based processing.<br>
	 * @param laubeRequestSystemAcceptor Application business data
	 * @param laubeVisitor Pass the visitor.
	 * The application business can not be passed only visitor class to perform the application business.
	 * If you pass any other visitor class, an exception occurs.
	 * @return Processing result
	 * @throws LaubeException
	 */
	public final ResultDto doRequest(final RequestSystemAcceptor requestSystemAcceptor,RequestSystemVisitor requestSystemVisitor) {

		log.debug("[workflowEngine] " + "doRequest start");

		ResultDto resultDto = null;

		try {
			resultDto = requestSystemAcceptor.accept(requestSystemVisitor);

		}catch(LaubeException e){
			log.error("[workflowEngine] " + e.getMessage());
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.error("[workflowEngine] " + "doRequest end");
			return resultDto;

		}catch(Exception e){
			log.error("[workflowEngine] " + e.getMessage());
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.error("[workflowEngine] " + "doRequest end");
			return resultDto;
		}
		log.debug("[workflowEngine] " + "doRequest end");
		return resultDto;
	}

	/**
	 * make the process of approval.<br>
	 * @param laubeApprovalAcceptor approval business data
	 * @param laubeVisitor Pass the visitor.
	 * It does not pass only visitor class to approve business to approval business.
	 * If you pass any other visitor class, an exception occurs.
	 * @return Processing result
	 * @throws LaubeException
	 */
	public final ResultDto doApproval(final ApprovalSystemAcceptor approvalAcceptor,ApprovalSystemVisitor approvalSystemVisitor) {

		log.debug("[workflowEngine] " + "doApproval start");

		ResultDto resultDto = null;

		try {
			resultDto = approvalAcceptor.accept(approvalSystemVisitor);

		}catch(LaubeException e){
			log.error("[workflowEngine] " + e.getMessage());
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.error("[workflowEngine] " + "doApproval end");
			return resultDto;

		}catch(Exception e){
			log.error("[workflowEngine] " + e.getMessage());
			resultDto = new ResultDto();
			resultDto.setStatus(false);
			resultDto.setMessageId("E8001");
			log.error("[workflowEngine] " + "doApproval end");
			return resultDto;
		}
		log.debug("[workflowEngine] " + "doApproval end");
		return resultDto;
	}
}
