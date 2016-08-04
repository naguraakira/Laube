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
 * 初期検索(スペシャルルート/共通ルートあり)
 * @author mikir
 *
 */
public class RouteSeachVisitorTest005 {

	/**
	 * to manage the log.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RouteSeachVisitorTest005.class);

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
			searchSystemAcceptor.setApplicationFormCode("F001");
			searchSystemAcceptor.setApplyCompanyCode("PS");
			searchSystemAcceptor.setApplyUnitCode("U001");
			searchSystemAcceptor.setApplyUserCode("90003");
			searchSystemAcceptor.setDeputyApplyCompanyCode(null);
			searchSystemAcceptor.setDeputyApplyUnitCode(null);
			searchSystemAcceptor.setDeputyApplyUserCode(null);

			ResultDto result = LaubeController.doSearch(searchSystemAcceptor, searchSystemVisitor);
			assertTrue(result.isSuccess());
			assertEquals("N0001",result.getMessageId());
			Object o = result.getResultData();
			RouteSearchAcceptor routeSearchAcceptor = (RouteSearchAcceptor)o;
			List<ApprovalRouteInformationAcceptor> individualRouts = routeSearchAcceptor.getIndividualRoutes();

			assertEquals(2,individualRouts.size());




			ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor = individualRouts.get(0);
			assertEquals("PS","AA");
			assertEquals("U001",approvalRouteInformationAcceptor.getApprovalUnitCode());
			assertEquals("80001",approvalRouteInformationAcceptor.getApprovalUserCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalComment());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalCompanyCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUnitCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUserCode());
			assertEquals(1,approvalRouteInformationAcceptor.getFunction());
			assertEquals("S02",approvalRouteInformationAcceptor.getNextPartyCode());
			assertEquals("S01",approvalRouteInformationAcceptor.getPartyCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyCodeConnector());
			assertEquals("S01",approvalRouteInformationAcceptor.getPartyTransitCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyTransitCodeConnector());
			assertEquals(1,approvalRouteInformationAcceptor.getRouteType());

			approvalRouteInformationAcceptor = individualRouts.get(1);
			assertEquals("PS",approvalRouteInformationAcceptor.getApprovalCompanyCode());
			assertEquals("U001",approvalRouteInformationAcceptor.getApprovalUnitCode());
			assertEquals("80001",approvalRouteInformationAcceptor.getApprovalUserCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalComment());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalCompanyCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUnitCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUserCode());
			assertEquals(1,approvalRouteInformationAcceptor.getFunction());
			assertEquals("S01",approvalRouteInformationAcceptor.getNextPartyCode());
			assertEquals("003",approvalRouteInformationAcceptor.getPartyCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyCodeConnector());
			assertEquals("002",approvalRouteInformationAcceptor.getPartyTransitCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyTransitCodeConnector());
			assertEquals(1,approvalRouteInformationAcceptor.getRouteType());






			List<ApprovalRouteInformationAcceptor> commonRouts = routeSearchAcceptor.getCommonRoutes();
			assertEquals(1,commonRouts.size());
			ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor1 = commonRouts.get(0);
			assertEquals("PS",approvalRouteInformationAcceptor1.getApprovalCompanyCode());
			assertEquals("U001",approvalRouteInformationAcceptor1.getApprovalUnitCode());
			assertEquals("90001",approvalRouteInformationAcceptor1.getApprovalUserCode());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalComment());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalCompanyCode());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalUnitCode());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalUserCode());
			assertEquals(1,approvalRouteInformationAcceptor1.getFunction());
			assertEquals("E",approvalRouteInformationAcceptor1.getNextPartyCode());
			assertEquals("B01",approvalRouteInformationAcceptor1.getPartyCode());
			assertEquals(0,approvalRouteInformationAcceptor1.getPartyCodeConnector());
			assertEquals("B01",approvalRouteInformationAcceptor1.getPartyTransitCode());
			assertEquals(0,approvalRouteInformationAcceptor1.getPartyTransitCodeConnector());
			assertEquals(2,approvalRouteInformationAcceptor1.getRouteType());

		} catch(Exception e){
			log.debug(e.getMessage());
		}
	}
}