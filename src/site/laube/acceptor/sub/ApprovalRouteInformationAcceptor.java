package site.laube.acceptor.sub;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.utility.LaubeUtility;
import site.laube.utility.SpecifiedValue;
import site.laube.utility.type.RouteType;

/**
 * Approval route information class
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 */
public final class ApprovalRouteInformationAcceptor implements Serializable {

	/**
	 * to manage the log<br>
	 */
	private static Logger log = LoggerFactory.getLogger(ApprovalRouteInformationAcceptor.class);

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 2111275563007100295L;

	/**
	 * to manage the party code.<br>
	 */
	private String partyCode;

	/**
	 * to manage the party Code Connector.<br>
	 */
	private int partyCodeConnector;

	/**
	 * to manage the route Type.<br>
	 */
	private int routeType;

	/**
	 * to manage the approval company code.<br>
	 */
	private String approvalCompanyCode = null;

	/**
	 * to manage the approval unit code.<br>
	 */
	private String approvalUnitCode = null;

	/**
	 * to manage the approval user code.<br>
	 */
	private String approvalUserCode = null;

	/**
	 * to manage the deputy approval company code.<br>
	 */
	private String deputyApprovalCompanyCode = null;

	/**
	 * to manage the deputy approval unit code.<br>
	 */
	private String deputyApprovalUnitCode = null;

	/**
	 * to manage the deputy approval user code.<br>
	 */
	private String deputyApprovalUserCode = null;

	/**
	 * to manage the function.<br>
	 */
	private int function = 0;

	/**
	 * to manage the deputy approval comment.<br>
	 */
	private String deputyApprovalComment = null;

	/**
	 * to manage the next party code.<br>
	 */
	private String nextPartyCode;

	/**
	 * to manage the party transit code.<br>
	 */
	private String partyTansitCode;

	/**
	 * to manage the party transit code.<br>
	 */
	private int partyTansitCodeConnector;

	/**
	 * set the party code.<br>
	 * @param partyCode party code
	 */
	public final void setPartyCode(final String partyCode) {
		this.partyCode = partyCode;
	}

	/**
	 * get the party code.<br>
	 * @return party code
	 */
	public final String getPartyCode() {
		return this.partyCode;
	}

	/**
	 * set the partyCodeConnector.<br>
	 * @param partyCodeConnector party Code Connector
	 */
	public final void setPartyCodeConnector(final int partyCodeConnector) {
		this.partyCodeConnector = partyCodeConnector;
	}

	/**
	 * get the partyCodeConnector.<br>
	 * @return partyCodeConnector
	 */
	public final int getPartyCodeConnector() {
		return this.partyCodeConnector;
	}

	/**
	 * get the route Type.<br>
	 * @return routeType route Type
	 */
	public final int getRouteType() {
		return this.routeType;
	}

	/**
	 * set the route Type.<br>
	 * @param routeType
	 */
	public final void setRouteType(final int routeType) {
		this.routeType = routeType;
	}

	/**
	 * set the route Type.<br>
	 * @param routeType
	 */
	public final void setRouteType(final RouteType routeType) {

		if (routeType == RouteType.IndividualRoute) {
			this.routeType = SpecifiedValue.IndividualRoute;
		}

		if (routeType == RouteType.CommonRoute) {
			this.routeType = SpecifiedValue.CommonRoute;
		}

		if (routeType == RouteType.SpecialRoute) {
			this.routeType = SpecifiedValue.Special;
		}
	}

	/**
	 * get the approval Company Code.<br>
	 * @return approvalCompanyCode approval Company Code
	 */
	public final String getApprovalCompanyCode() {

		if (this.approvalCompanyCode != null){
			this.approvalCompanyCode = this.approvalCompanyCode.trim();
		}
		return this.approvalCompanyCode;
	}

	/**
	 * set the approval Company Code.<br>
	 * @param approvalCompanyCode approval Company Code
	 */
	public final void setApprovalCompanyCode(final String approvalCompanyCode) {

		if ((approvalCompanyCode != null)&&(approvalCompanyCode.trim().length() == 0)){
			this.approvalCompanyCode = null;
		}else{
			this.approvalCompanyCode = approvalCompanyCode;
		}
	}

	/**
	 * get the approval unit Code.<br>
	 * @return approvalUnitCode approval unit Code
	 */
	public final String getApprovalUnitCode() {

		if (this.approvalUnitCode != null){
			this.approvalUnitCode = this.approvalUnitCode.trim();
		}
		return this.approvalUnitCode;
	}

	/**
	 * set the approval unit Code.<br>
	 * @param unitCode approval unit Code
	 */
	public final void setApprovalUnitCode(final String approvalUnitCode) {

		if ((approvalUnitCode != null)&&(approvalUnitCode.trim().length() == 0)){
			this.approvalUnitCode = null;
		}else{
			this.approvalUnitCode = approvalUnitCode;
		}
	}

	/**
	 * get the approval User Code.<br>
	 * @return approvalUserCode approval User Code
	 */
	public final String getApprovalUserCode() {

		if (this.approvalUserCode != null){
			this.approvalUserCode = this.approvalUserCode.trim();
		}
		return this.approvalUserCode;
	}

	/**
	 * set the approval User Code.<br>
	 * @param approvalUserCode approval User Code
	 */
	public final void setApprovalUserCode(final String approvalUserCode) {

		if ((approvalUserCode != null)&&(approvalUserCode.trim().length() == 0)){
			this.approvalUserCode = null;
		}else{
			this.approvalUserCode = approvalUserCode;
		}
	}

	/**
	 * get the deputy approval Company Code.<br>
	 * @return deputy approvalCompanyCode
	 */
	public final String getDeputyApprovalCompanyCode() {

		if (this.deputyApprovalCompanyCode != null){
			this.deputyApprovalCompanyCode = this.deputyApprovalCompanyCode.trim();
		}
		return this.deputyApprovalCompanyCode;
	}

	/**
	 * set the deputy approval Company Code.<br>
	 * @param deputy approval Company Code
	 */
	public final void setDeputyApprovalCompanyCode(final String deputyApprovalCompanyCode) {

		if ((deputyApprovalCompanyCode != null)&&(deputyApprovalCompanyCode.trim().length() == 0)){
			this.deputyApprovalCompanyCode = null;
		}else{
			this.deputyApprovalCompanyCode = deputyApprovalCompanyCode;
		}
	}

	/**
	 * get the deputy approval unit Code.<br>
	 * @return deputy approval unit Code
	 */
	public final String getDeputyApprovalUnitCode() {

		if (this.deputyApprovalUnitCode != null){
			this.deputyApprovalUnitCode = this.deputyApprovalUnitCode.trim();
		}
		return this.deputyApprovalUnitCode;
	}

	/**
	 * set the deputy approval unit Code.<br>
	 * @param deputy approval unit Code
	 */
	public final void setDeputyApprovalUnitCode(final String deputyApprovalUnitCode) {

		if ((deputyApprovalUnitCode != null)&&(deputyApprovalUnitCode.trim().length() == 0)){
			this.deputyApprovalUnitCode = null;
		}else{
			this.deputyApprovalUnitCode = deputyApprovalUnitCode;
		}
	}

	/**
	 * get the deputy approval User Code.<br>
	 * @return deputy Approval User Code
	 */
	public final String getDeputyApprovalUserCode() {

		if (this.deputyApprovalUserCode != null){
			this.deputyApprovalUserCode = this.deputyApprovalUserCode.trim();
		}
		return this.deputyApprovalUserCode;
	}

	/**
	 * set the deputy approval User Code.<br>
	 * @param deputy Approval User Code
	 */
	public final void setDeputyApprovalUserCode(final String deputyApprovalUserCode) {

		if ((deputyApprovalUserCode != null)&&(deputyApprovalUserCode.trim().length() == 0)){
			this.deputyApprovalUserCode = null;
		}else{
			this.deputyApprovalUserCode = deputyApprovalUserCode;
		}
	}

	/**
	 * get the deputy approval Comment.<br>
	 * @return deputy Approval Comment
	 */
	public final String getDeputyApprovalComment() {

		if (this.deputyApprovalComment != null){
			this.deputyApprovalComment = this.deputyApprovalComment.trim();
		}
		return this.deputyApprovalComment;
	}

	/**
	 * set the deputy approval Comment.<br>
	 * @param deputy Approval Comment
	 */
	public final void setDeputyApprovalComment(final String deputyApprovalComment) {

		if ((deputyApprovalComment != null)&&(deputyApprovalComment.trim().length() == 0)){
			this.deputyApprovalComment = null;
		}else{
			this.deputyApprovalComment = deputyApprovalComment;
		}
	}

	/**
	 * get the function.<br>
	 * @return function
	 */
	public final int getFunction() {
		return this.function;
	}

	/**
	 * set the function.<br>
	 * @param function function
	 */
	public final void setFunction(final int function) {
		this.function = function;
	}

	/**
	 * set the next party code.<br>
	 * @param nextPartyCode next party code
	 */
	public final void setNextPartyCode(final String nextPartyCode) {
		this.nextPartyCode = nextPartyCode;
	}

	/**
	 * get the next party code.<br>
	 * @return next party code
	 */
	public final String getNextPartyCode() {
		return this.nextPartyCode;
	}

	/**
	 * set the party transit code.<br>
	 * @param partyTransitCode party transit code
	 */
	public final void setPartyTransitCode(final String partyTransitCode) {
		this.partyTansitCode = partyTransitCode;
	}

	/**
	 * get the party transit code.<br>
	 * @return party transit code
	 */
	public final String getPartyTransitCode() {
		return this.partyTansitCode;
	}

	/**
	 * set the party Transit code connector.<br>
	 * @param partyTransitCodeConnector party Transit code connector
	 */
	public final void setPartyTransitCodeConnector(final int partyTansitCodeConnector) {
		this.partyTansitCodeConnector = partyTansitCodeConnector;
	}

	/**
	 * get the party Transit Code Connector.<br>
	 * @return partyTransitCodeConnector
	 */
	public final int getPartyTransitCodeConnector() {
		return this.partyTansitCodeConnector;
	}

	/**
	 * All the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
