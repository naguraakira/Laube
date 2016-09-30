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

import site.laube.acceptor.RequestSystemAcceptor;
import site.laube.acceptor.request.ApplyAcceptor;
import site.laube.acceptor.request.DraftAcceptor;
import site.laube.acceptor.sub.ApprovalRouteInformationAcceptor;
import site.laube.controller.LaubeController;
import site.laube.dto.ResultDto;
import site.laube.utility.SpecifiedValue;
import site.laube.utility.type.ApprovalFunction;
import site.laube.utility.type.Connector;
import site.laube.utility.type.RouteType;
import site.laube.visitor.RequestSystemVisitor;
import site.laube.visitor.request.ApplyVisitor;
import site.laube.visitor.request.DraftVisitor;

/**
 * 下書き申請→通常申請
 * @author mikir
 *
 */
public class ApplyVisitorTest004 {

	/**
	 * to manage the log.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApplyVisitorTest004.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// no code
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// no code
	}

	@Before
	public void setUp() throws Exception {
		// no code
	}

	@After
	public void tearDown() throws Exception {
		// no code
	}

	@Test
	public void testDoApply() {

		LaubeController LaubeController = new LaubeController();
		RequestSystemAcceptor requestSystemAcceptor = new DraftAcceptor();
		RequestSystemVisitor requestSystemVisitor = new DraftVisitor();

		try {
			requestSystemAcceptor.setCompanyCode("PS"); //$NON-NLS-1$
			requestSystemAcceptor.setApplicationFormCode("FORM001"); //$NON-NLS-1$
			requestSystemAcceptor.setApplyDate("2016/02/10"); //$NON-NLS-1$
			requestSystemAcceptor.setApplyCompanyCode("PS"); //$NON-NLS-1$
			requestSystemAcceptor.setApplyUnitCode("UNIT001"); //$NON-NLS-1$
			requestSystemAcceptor.setApplyUserCode("USER001"); //$NON-NLS-1$

			List<ApprovalRouteInformationAcceptor> individualRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();
			List<ApprovalRouteInformationAcceptor> commonRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();
			ApprovalRouteInformationAcceptor route = null;

			// 個別ルート　第一承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("1"); //$NON-NLS-1$
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS"); //$NON-NLS-1$
			route.setApprovalUnitCode("UNIT001"); //$NON-NLS-1$
			route.setApprovalUserCode("USER0001"); //$NON-NLS-1$
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("2"); //$NON-NLS-1$
			route.setPartyTransitCode("T2"); //$NON-NLS-1$
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			individualRoutes.add(route);

			// 個別ルート　第二承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("2"); //$NON-NLS-1$
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS"); //$NON-NLS-1$
			route.setApprovalUnitCode("UNIT001"); //$NON-NLS-1$
			route.setApprovalUserCode("USER0002"); //$NON-NLS-1$
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("3"); //$NON-NLS-1$
			route.setPartyTransitCode("T3"); //$NON-NLS-1$
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			individualRoutes.add(route);

			// 個別ルート　第三承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("3"); //$NON-NLS-1$
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS"); //$NON-NLS-1$
			route.setApprovalUnitCode("UNIT001"); //$NON-NLS-1$
			route.setApprovalUserCode("USER0003"); //$NON-NLS-1$
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("4"); //$NON-NLS-1$
			route.setPartyTransitCode("T4"); //$NON-NLS-1$
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			individualRoutes.add(route);

			// 共通ルート　第一承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("4"); //$NON-NLS-1$
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.CommonRoute.toInt());
			route.setApprovalCompanyCode("PS"); //$NON-NLS-1$
			route.setApprovalUnitCode("UNIT001"); //$NON-NLS-1$
			route.setApprovalUserCode("USER0004"); //$NON-NLS-1$
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("5"); //$NON-NLS-1$
			route.setPartyTransitCode("T5"); //$NON-NLS-1$
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			commonRoutes.add(route);

			// 共通ルート　第二承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("5"); //$NON-NLS-1$
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.CommonRoute.toInt());
			route.setApprovalCompanyCode("PS"); //$NON-NLS-1$
			route.setApprovalUnitCode("UNIT001"); //$NON-NLS-1$
			route.setApprovalUserCode("USER0005"); //$NON-NLS-1$
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode(SpecifiedValue.END);
			route.setPartyTransitCode("T6"); //$NON-NLS-1$
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			commonRoutes.add(route);

			requestSystemAcceptor.setIndividualRoutes(individualRoutes);
			requestSystemAcceptor.setCommonRoutes(commonRoutes);
			ResultDto result = LaubeController.doRequest(requestSystemAcceptor, requestSystemVisitor);
			log.debug("-----------");
			assertTrue(result.isSuccess());

			Object o = result.getResultData();
			int applicationNumber = (int)o;
			// 下書きしたものを申請します。
			requestSystemAcceptor = new ApplyAcceptor();
			requestSystemVisitor = new ApplyVisitor();
			requestSystemAcceptor.setApplicationNumber(applicationNumber);
			requestSystemAcceptor.setCompanyCode("PS"); //$NON-NLS-1$
			requestSystemAcceptor.setApplicationFormCode("FORM001"); //$NON-NLS-1$
			requestSystemAcceptor.setApplyDate("2016/02/10"); //$NON-NLS-1$
			requestSystemAcceptor.setApplyCompanyCode("PS"); //$NON-NLS-1$
			requestSystemAcceptor.setApplyUnitCode("UNIT001"); //$NON-NLS-1$
			requestSystemAcceptor.setApplyUserCode("USER001"); //$NON-NLS-1$

			individualRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();
			commonRoutes = new ArrayList<ApprovalRouteInformationAcceptor>();
			route = null;

			// 個別ルート　第一承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("1"); //$NON-NLS-1$
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS"); //$NON-NLS-1$
			route.setApprovalUnitCode("UNIT001"); //$NON-NLS-1$
			route.setApprovalUserCode("USER001"); //$NON-NLS-1$
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("2"); //$NON-NLS-1$
			route.setPartyTransitCode("T2"); //$NON-NLS-1$
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			individualRoutes.add(route);

			// 個別ルート　第二承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("2"); //$NON-NLS-1$
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS"); //$NON-NLS-1$
			route.setApprovalUnitCode("UNIT001"); //$NON-NLS-1$
			route.setApprovalUserCode("USER002"); //$NON-NLS-1$
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("3"); //$NON-NLS-1$
			route.setPartyTransitCode("T3"); //$NON-NLS-1$
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			individualRoutes.add(route);

			// 個別ルート　第三承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("3"); //$NON-NLS-1$
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.IndividualRoute.toInt());
			route.setApprovalCompanyCode("PS"); //$NON-NLS-1$
			route.setApprovalUnitCode("UNIT001"); //$NON-NLS-1$
			route.setApprovalUserCode("USER003"); //$NON-NLS-1$
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("4"); //$NON-NLS-1$
			route.setPartyTransitCode("T4"); //$NON-NLS-1$
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			individualRoutes.add(route);

			// 共通ルート　第一承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("4"); //$NON-NLS-1$
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.CommonRoute.toInt());
			route.setApprovalCompanyCode("PS"); //$NON-NLS-1$
			route.setApprovalUnitCode("UNIT001"); //$NON-NLS-1$
			route.setApprovalUserCode("USER004"); //$NON-NLS-1$
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode("5"); //$NON-NLS-1$
			route.setPartyTransitCode("T5"); //$NON-NLS-1$
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			commonRoutes.add(route);

			// 共通ルート　第二承認者
			route = new ApprovalRouteInformationAcceptor();
			route.setPartyCode("5"); //$NON-NLS-1$
			route.setPartyCodeConnector(Connector.Unspecified.toInt());
			route.setRouteType(RouteType.CommonRoute.toInt());
			route.setApprovalCompanyCode("PS"); //$NON-NLS-1$
			route.setApprovalUnitCode("UNIT001"); //$NON-NLS-1$
			route.setApprovalUserCode("USER005"); //$NON-NLS-1$
			route.setDeputyApprovalCompanyCode(null);
			route.setDeputyApprovalUnitCode(null);
			route.setDeputyApprovalUserCode(null);
			route.setFunction(ApprovalFunction.Examination.toInt());
			route.setDeputyApprovalComment(null);
			route.setNextPartyCode(SpecifiedValue.END);
			route.setPartyTransitCode("T6"); //$NON-NLS-1$
			route.setPartyTransitCodeConnector(Connector.Unspecified.toInt());
			commonRoutes.add(route);

			requestSystemAcceptor.setIndividualRoutes(individualRoutes);
			requestSystemAcceptor.setCommonRoutes(commonRoutes);
			result = LaubeController.doRequest(requestSystemAcceptor, requestSystemVisitor);
			assertTrue(result.isSuccess());

		} catch(Exception e){
			log.debug(e.getMessage());
		}
	}

}