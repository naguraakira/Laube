package site.laube.visitor.request.apply;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.request.ApplyAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.controller.LaubeController;
import site.laube.dto.ResultDto;
import site.laube.utility.SpecifiedValue;
import site.laube.visitor.request.ApplyVisitor;

/**
 * 通常申請(ループとAND分岐)
 * @author mikir
 *
 */
public class ApplyVisitorTest011 {

	/**
	 * to manage the log.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApplyVisitorTest010.class);

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
	public void testDoApply() {

		LaubeController LaubeController = new LaubeController();
		ApplyAcceptor requestSystemAcceptor = new ApplyAcceptor();
		ApplyVisitor requestSystemVisitor = new ApplyVisitor();

		try {
			requestSystemAcceptor.setCompanyCode("PS");
			requestSystemAcceptor.setApplicationFormCode("F001");
			requestSystemAcceptor.setApplyDate("2016/02/10");
			requestSystemAcceptor.setApplyCompanyCode("PS");
			requestSystemAcceptor.setApplyUnitCode("U001");
			requestSystemAcceptor.setApplyUserCode("90001");

			List<ApprovalRouteInformationAcceptor> individualRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();
			List<ApprovalRouteInformationAcceptor> commonRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();
			ApprovalRouteInformationAcceptor route = null;

			// 個別ルート　第一承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("1");
			route.setPartyCodeConnector(SpecifiedValue.Unspecified);
			route.setRouteType(SpecifiedValue.IndividualRoute);
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U001");
			route.setApprovalUserCode("USER01");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(SpecifiedValue.Examination);
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("2");
			route.setPartyTransitCode("T2");
			route.setPartyTransitCodeConnector(SpecifiedValue.Unspecified);
			individualRoutes.add(route);

			// 個別ルート　第一承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("1");
			route.setPartyCodeConnector(SpecifiedValue.Unspecified);
			route.setRouteType(SpecifiedValue.IndividualRoute);
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U001");
			route.setApprovalUserCode("USER01");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(SpecifiedValue.Examination);
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("3");
			route.setPartyTransitCode("T2");
			route.setPartyTransitCodeConnector(SpecifiedValue.Unspecified);
			individualRoutes.add(route);

			// 個別ルート　第二承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("2");
			route.setPartyCodeConnector(SpecifiedValue.Unspecified);
			route.setRouteType(SpecifiedValue.IndividualRoute);
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U001");
			route.setApprovalUserCode("USER02");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(SpecifiedValue.Examination);
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("5");
			route.setPartyTransitCode("T4");
			route.setPartyTransitCodeConnector(SpecifiedValue.LogicalProduct);
			individualRoutes.add(route);

			// 個別ルート　第三承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("3");
			route.setPartyCodeConnector(SpecifiedValue.Unspecified);
			route.setRouteType(SpecifiedValue.IndividualRoute);
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U001");
			route.setApprovalUserCode("USER03");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(SpecifiedValue.Examination);
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("4");
			route.setPartyTransitCode("T3");
			route.setPartyTransitCodeConnector(SpecifiedValue.Unspecified);
			individualRoutes.add(route);

			// 個別ルート　第四承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("4");
			route.setPartyCodeConnector(SpecifiedValue.Unspecified);
			route.setRouteType(SpecifiedValue.IndividualRoute);
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U001");
			route.setApprovalUserCode("USER04");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(SpecifiedValue.Examination);
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("3");
			route.setPartyTransitCode("T4");
			route.setPartyTransitCodeConnector(SpecifiedValue.LogicalProduct);
			individualRoutes.add(route);

			// 共通ルート　第一承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("5");
			route.setPartyCodeConnector(SpecifiedValue.Unspecified);
			route.setRouteType(SpecifiedValue.CommonRoute);
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U001");
			route.setApprovalUserCode("USER05");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(SpecifiedValue.Examination);
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode(SpecifiedValue.END);
			route.setPartyTransitCode("T5");
			route.setPartyTransitCodeConnector(SpecifiedValue.Unspecified);
			commonRoutes.add(route);

			requestSystemAcceptor.setIndividualRoutes(individualRoutes);
			requestSystemAcceptor.setCommonRoutes(commonRoutes);
			ResultDto result = LaubeController.doRequest(requestSystemAcceptor, requestSystemVisitor);
			assertTrue(!result.isSuccess());
			assertTrue(result.getMessageId().equals("E0009"));

		} catch(Exception e){
			log.debug(e.getMessage());
		}
	}

}