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
 * 初期検索(上司ルート/共通ルートなし)
 * @author mikir
 *
 */
public class RouteSeachVisitorTest001 {

	/**
	 * to manage the log.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(RouteSeachVisitorTest001.class);

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
			assertEquals("90002",approvalRouteInformationAcceptor.getApprovalUserCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalComment());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalCompanyCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUnitCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUserCode());
			assertEquals(1,approvalRouteInformationAcceptor.getFunction());
			assertEquals("AP00000002",approvalRouteInformationAcceptor.getNextPartyCode());
			assertEquals("AP00000001",approvalRouteInformationAcceptor.getPartyCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyCodeConnector());
			assertEquals("AT00000001",approvalRouteInformationAcceptor.getPartyTransitCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyTransitCodeConnector());
			assertEquals(1,approvalRouteInformationAcceptor.getRouteType());

			approvalRouteInformationAcceptor = individualRouts.get(1);
			assertEquals("PS",approvalRouteInformationAcceptor.getApprovalCompanyCode());
			assertEquals("U001",approvalRouteInformationAcceptor.getApprovalUnitCode());
			assertEquals("90003",approvalRouteInformationAcceptor.getApprovalUserCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalComment());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalCompanyCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUnitCode());
			assertEquals(null,approvalRouteInformationAcceptor.getDeputyApprovalUserCode());
			assertEquals(1,approvalRouteInformationAcceptor.getFunction());
			assertEquals("E",approvalRouteInformationAcceptor.getNextPartyCode());
			assertEquals("AP00000002",approvalRouteInformationAcceptor.getPartyCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyCodeConnector());
			assertEquals("AT00000002",approvalRouteInformationAcceptor.getPartyTransitCode());
			assertEquals(0,approvalRouteInformationAcceptor.getPartyTransitCodeConnector());
			assertEquals(1,approvalRouteInformationAcceptor.getRouteType());

		} catch(Exception e){
			log.debug(e.getMessage());
		}
	}
}