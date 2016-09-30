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
import site.laube.utility.type.ApprovalFunction;
import site.laube.utility.type.Connector;
import site.laube.utility.type.RouteType;
import site.laube.visitor.request.ApplyVisitor;

/**
 * 通常申請(unut_code別)
 * @author mikir
 *
 */
public class ApplyVisitorTest019 {

	/**
	 * to manage the log.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApplyVisitorTest019.class);

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
			requestSystemAcceptor.setApplicationFormCode("FORM001");
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
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U001");
			route.setApprovalUserCode("USER001");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("2");
			route.setPartyTransitCode("T2");
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			individualRoutes.add(route);

			// 個別ルート　第一承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("1");
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U002");
			route.setApprovalUserCode("USER10");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("3");
			route.setPartyTransitCode("T3");
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			individualRoutes.add(route);

			// 個別ルート　第一承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("1");
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U003");
			route.setApprovalUserCode("USER20");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("4");
			route.setPartyTransitCode("T4");
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			individualRoutes.add(route);

			// 個別ルート　第二承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("2");
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U001");
			route.setApprovalUserCode("USER002");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("5");
			route.setPartyTransitCode("T5");
			route.setPartyTransitCodeConnector(Connector.LogicalSum.toInt());
			individualRoutes.add(route);

			// 個別ルート　第三承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("3");
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U002");
			route.setApprovalUserCode("USER11");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("5");
			route.setPartyTransitCode("T5");
			route.setPartyTransitCodeConnector(Connector.LogicalSum.toInt());
			individualRoutes.add(route);

			// 個別ルート　第四承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("4");
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U003");
			route.setApprovalUserCode("USER21");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("5");
			route.setPartyTransitCode("T6");
			route.setPartyTransitCodeConnector(Connector.LogicalProduct.toInt());
			individualRoutes.add(route);

			// 共通ルート　第一承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("5");
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.CommonRoute.toInt());
			route.setApprovalCompanyCode("PS");
			route.setApprovalUnitCode("U001");
			route.setApprovalUserCode("USER005");
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode(SpecifiedValue.END);
			route.setPartyTransitCode("T6");
			route.setPartyTransitCodeConnector(Connector.LogicalProduct.toInt());
			commonRoutes.add(route);

			requestSystemAcceptor.setIndividualRoutes(individualRoutes);
			requestSystemAcceptor.setCommonRoutes(commonRoutes);
			ResultDto result = LaubeController.doRequest(requestSystemAcceptor, requestSystemVisitor);
			assertTrue(result.isSuccess());

		} catch(Exception e){
			log.debug(e.getMessage());
		}
	}

}