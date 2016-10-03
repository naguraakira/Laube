package site.laube.visitor.search.approval;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.ApprovalSystemAcceptor;
import site.laube.acceptor.approval.ApprovalAcceptor;
import site.laube.controller.LaubeController;
import site.laube.dto.ResultDto;
import site.laube.visitor.ApprovalSystemVisitor;
import site.laube.visitor.approval.ApprovalVisitor;

/**
 * 承認処理
 * @author mikir
 *
 */
public class ApprovalVisitorTest001 {

	/**
	 * to manage the log.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApprovalVisitorTest001.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVisit() {

		LaubeController LaubeController = new LaubeController();
		ApprovalSystemAcceptor approvalSystemAcceptor = new ApprovalAcceptor();
		ApprovalSystemVisitor approvalSystemVisitor = new ApprovalVisitor();

		try {
			approvalSystemAcceptor.setCompanyCode("PS");
			approvalSystemAcceptor.setApplicationNumber(16);
			approvalSystemAcceptor.setApprovalDate("2016/06/08");
			approvalSystemAcceptor.setApprovalCompanyCode("PS");
			approvalSystemAcceptor.setApprovalUnitCode("UNIT001");
			approvalSystemAcceptor.setApprovalUserCode("USER001");
			approvalSystemAcceptor.setComment("承認します");
			approvalSystemAcceptor.setAppendFileList(null);

			ResultDto result = LaubeController.doApproval(approvalSystemAcceptor, approvalSystemVisitor);
			assertTrue(result.isSuccess());
			assertEquals("N0001",result.getMessageId());

		} catch(Exception e){
			log.debug(e.getMessage());
		}
	}
}