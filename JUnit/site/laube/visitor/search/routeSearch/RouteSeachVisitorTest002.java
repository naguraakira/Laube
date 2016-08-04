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
 * 初期検索(個別ルート/共通ルートあり)
 * @author mikir
 *
 */
public class RouteSeachVisitorTest002 {

	/**
	 * to manage the log.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RouteSeachVisitorTest002.class);

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
			searchSystemAcceptor.setApplicationFormCode("F002");
			searchSystemAcceptor.setApplyCompanyCode("PS");
			searchSystemAcceptor.setApplyUnitCode("U001");
			searchSystemAcceptor.setApplyUserCode("90001");
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
			assertEquals("U001",approvalRouteInformationAcceptor.getApprovalUnitCode());
			assertEquals("90001",approvalRouteInformationAcceptor.getApprovalUserCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalComment());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalCompanyCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUnitCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUserCode());
			assertEquals(1,approvalRouteInformationAcceptor.getFunction());
			assertEquals("A02",approvalRouteInformationAcceptor.getNextPartyCode());
			assertEquals("A01",approvalRouteInformationAcceptor.getPartyCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyCodeConnector());
			assertEquals("A01",approvalRouteInformationAcceptor.getPartyTransitCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyTransitCodeConnector());
			assertEquals(1,approvalRouteInformationAcceptor.getRouteType());

			ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor1 = individualRouts.get(1);
			assertEquals("PS",approvalRouteInformationAcceptor1.getApprovalCompanyCode());
			assertEquals("U002",approvalRouteInformationAcceptor1.getApprovalUnitCode());
			assertEquals("90001",approvalRouteInformationAcceptor1.getApprovalUserCode());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalComment());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalCompanyCode());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalUnitCode());
			assertEquals(null,approvalRouteInformationAcceptor1.getDeputyApprovalUserCode());
			assertEquals(1,approvalRouteInformationAcceptor1.getFunction());
			assertEquals("A03",approvalRouteInformationAcceptor1.getNextPartyCode());
			assertEquals("A02",approvalRouteInformationAcceptor1.getPartyCode());
			assertEquals(0,approvalRouteInformationAcceptor1.getPartyCodeConnector());
			assertEquals("A02",approvalRouteInformationAcceptor1.getPartyTransitCode());
			assertEquals(0,approvalRouteInformationAcceptor1.getPartyTransitCodeConnector());
			assertEquals(1,approvalRouteInformationAcceptor1.getRouteType());

			ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor2 = individualRouts.get(2);
			assertEquals("PS",approvalRouteInformationAcceptor2.getApprovalCompanyCode());
			assertEquals("U003",approvalRouteInformationAcceptor2.getApprovalUnitCode());
			assertEquals("90001",approvalRouteInformationAcceptor2.getApprovalUserCode());
			assertEquals(null,approvalRouteInformationAcceptor2.getDeputyApprovalComment());
			assertEquals(null,approvalRouteInformationAcceptor2.getDeputyApprovalCompanyCode());
			assertEquals(null,approvalRouteInformationAcceptor2.getDeputyApprovalUnitCode());
			assertEquals(null,approvalRouteInformationAcceptor2.getDeputyApprovalUserCode());
			assertEquals(1,approvalRouteInformationAcceptor2.getFunction());
			assertEquals("E",approvalRouteInformationAcceptor2.getNextPartyCode());
			assertEquals("A03",approvalRouteInformationAcceptor2.getPartyCode());
			assertEquals(0,approvalRouteInformationAcceptor2.getPartyCodeConnector());
			assertEquals("A03",approvalRouteInformationAcceptor2.getPartyTransitCode());
			assertEquals(0,approvalRouteInformationAcceptor2.getPartyTransitCodeConnector());
			assertEquals(1,approvalRouteInformationAcceptor2.getRouteType());


//			List<ApprovalRouteInformationAcceptor> commonRouts = routeSearchAcceptor.getCommonRoutes();
//			ApprovalRouteInformationAcceptor approvalRouteInformationAcceptor3 = commonRouts.get(0);
//			assertEquals("PS",approvalRouteInformationAcceptor3.getApprovalCompanyCode());
//			assertEquals("U001",approvalRouteInformationAcceptor3.getApprovalUnitCode());
//			assertEquals("90001",approvalRouteInformationAcceptor3.getApprovalUserCode());
//			assertEquals(null,approvalRouteInformationAcceptor3.getDeputyApprovalComment());
//			assertEquals(null,approvalRouteInformationAcceptor3.getDeputyApprovalCompanyCode());
//			assertEquals(null,approvalRouteInformationAcceptor3.getDeputyApprovalUnitCode());
//			assertEquals(null,approvalRouteInformationAcceptor3.getDeputyApprovalUserCode());
//			assertEquals(1,approvalRouteInformationAcceptor3.getFunction());
//			assertEquals("E",approvalRouteInformationAcceptor3.getNextPartyCode());
//			assertEquals("B01",approvalRouteInformationAcceptor3.getPartyCode());
//			assertEquals(0,approvalRouteInformationAcceptor3.getPartyCodeConnector());
//			assertEquals("B01",approvalRouteInformationAcceptor3.getPartyTransitCode());
//			assertEquals(0,approvalRouteInformationAcceptor3.getPartyTransitCodeConnector());
//			assertEquals(2,approvalRouteInformationAcceptor3.getRouteType());


		} catch(Exception e){
			log.debug(e.getMessage());
		}
	}
}