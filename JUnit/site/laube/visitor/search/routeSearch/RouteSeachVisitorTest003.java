package site.laube.visitor.search.routeSearch;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.acceptor.SearchSystemAcceptor;
import site.laube.acceptor.search.RouteSearchAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.controller.LaubeController;
import site.laube.dto.ResultDto;
import site.laube.visitor.SearchSystemVisitor;
import site.laube.visitor.search.RouteSearchVisitor;

/**
 * 初期検索(上司ルート/共通ルートあり)
 * @author mikir
 *
 */
public class RouteSeachVisitorTest003 {

	/**
	 * to manage the log.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RouteSeachVisitorTest003.class);

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
		SearchSystemAcceptor searchSystemAcceptor = new RouteSearchAcceptor();
		SearchSystemVisitor searchSystemVisitor = new RouteSearchVisitor();

		try {
			searchSystemAcceptor.setCompanyCode("PS");
			searchSystemAcceptor.setApplicationFormCode("FORM001");
			searchSystemAcceptor.setApplyCompanyCode("PS");
			searchSystemAcceptor.setApplyUnitCode("UNIT001");
			searchSystemAcceptor.setApplyUserCode("USER001");
			searchSystemAcceptor.setDeputyApplyCompanyCode(null);
			searchSystemAcceptor.setDeputyApplyUnitCode(null);
			searchSystemAcceptor.setDeputyApplyUserCode(null);

			ResultDto result = LaubeController.doSearch(searchSystemAcceptor, searchSystemVisitor);
			assertTrue(result.isSuccess());
			assertEquals("N0001",result.getMessageId());
			Object o = result.getResultData();
			RouteSearchAcceptor routeSearchAcceptor = (RouteSearchAcceptor)o;
			List<ApprovalRouteInformationAcceptor> individualRouts = routeSearchAcceptor.getIndividualRoutes();
			ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor = individualRouts.get(0);
			assertEquals("PS",approvalRouteInformationAcceptor.getApprovalCompanyCode());
			assertEquals("UNIT001",approvalRouteInformationAcceptor.getApprovalUnitCode());
			assertEquals("USER002",approvalRouteInformationAcceptor.getApprovalUserCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalComment());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalCompanyCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUnitCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUserCode());
			assertEquals(1,approvalRouteInformationAcceptor.getFunction());
			assertEquals("P02",approvalRouteInformationAcceptor.getNextPartyCode());
			assertEquals("P01",approvalRouteInformationAcceptor.getPartyCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyCodeConnector());
			assertEquals("T01",approvalRouteInformationAcceptor.getPartyTransitCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyTransitCodeConnector());
			assertEquals(1,approvalRouteInformationAcceptor.getRouteType());

			ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor1 = individualRouts.get(1);
			assertEquals("PS",approvalRouteInformationAcceptor1.getApprovalCompanyCode());
			assertEquals("UNIT001",approvalRouteInformationAcceptor1.getApprovalUnitCode());
			assertEquals("USER003",approvalRouteInformationAcceptor1.getApprovalUserCode());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalComment());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalCompanyCode());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalUnitCode());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalUserCode());
			assertEquals(1,approvalRouteInformationAcceptor1.getFunction());
			assertEquals("E",approvalRouteInformationAcceptor1.getNextPartyCode());
			assertEquals("P02",approvalRouteInformationAcceptor1.getPartyCode());
			assertEquals(0,approvalRouteInformationAcceptor1.getPartyCodeConnector());
			assertEquals("T01",approvalRouteInformationAcceptor1.getPartyTransitCode());
			assertEquals(0,approvalRouteInformationAcceptor1.getPartyTransitCodeConnector());
			assertEquals(1,approvalRouteInformationAcceptor1.getRouteType());

			List<ApprovalRouteInformationAcceptor> commonRouts = routeSearchAcceptor.getCommonRoutes();
			ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor11 = commonRouts.get(0);
			assertEquals("PS",approvalRouteInformationAcceptor11.getApprovalCompanyCode());
			assertEquals("UNIT001",approvalRouteInformationAcceptor11.getApprovalUnitCode());
			assertEquals("USER001",approvalRouteInformationAcceptor11.getApprovalUserCode());
			assertEquals(null,approvalRouteInformationAcceptor11.getDeputyApprovalComment());
			assertEquals(null,approvalRouteInformationAcceptor11.getDeputyApprovalCompanyCode());
			assertEquals(null,approvalRouteInformationAcceptor11.getDeputyApprovalUnitCode());
			assertEquals(null,approvalRouteInformationAcceptor11.getDeputyApprovalUserCode());
			assertEquals(1,approvalRouteInformationAcceptor11.getFunction());
			assertEquals("E",approvalRouteInformationAcceptor11.getNextPartyCode());
			assertEquals("B01",approvalRouteInformationAcceptor11.getPartyCode());
			assertEquals(0,approvalRouteInformationAcceptor11.getPartyCodeConnector());
			assertEquals("B01",approvalRouteInformationAcceptor11.getPartyTransitCode());
			assertEquals(0,approvalRouteInformationAcceptor11.getPartyTransitCodeConnector());

		} catch(Exception e){
			log.debug(e.getMessage());
		}
	}
}