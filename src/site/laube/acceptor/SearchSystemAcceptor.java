package site.laube.acceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.laube.dto.ResultDto;
import site.laube.exception.LaubeException;
import site.laube.utility.LaubeUtility;
import site.laube.visitor.SearchSystemVisitor;

/**
 * SearchSystemAcceptor class is passing the message class for the operator and the framework.<br>
 * All of the Search System Acceptorr is add an item to this class.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public abstract class SearchSystemAcceptor implements LaubeAcceptor {

	/**
	 * To manage the log object.<br>
	 */
	private static Logger log = LoggerFactory.getLogger(SearchSystemAcceptor.class);

	/**
	 * to manage the state of the company code<br>
	 */
	private String companyCode;

	/**
	 * to manage the application code of request.<br>
	 */
	private String applicationFormCode;

	/**
	 * to manage the apply company code of request.<br>
	 */
	private String applyCompanyCode;

	/**
	 * to manage the department code of request.<br>
	 */
	private String applyUnitCode;

	/**
	 * to manage the employee number of request.<br>
	 */
	private String applyUserCode;

	/**
	 * to manage the deputy apply company code of request.<br>
	 */
	private String deputyApplyCompanyCode;

	/**
	 * to manage the Department code of the proxy applicant of request.<br>
	 */
	private String deputyApplyUnitCode;

	/**
	 * to manage the employee number of the proxy applicant of request.<br>
	 */
	private String deputyApplyUserCode;

	/**
	 * set the company code.<br>
	 * @param companyCode company code
	 */
	public final void setCompanyCode(final String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * get the company code.<br>
	 *
	 * @return company code.
	 */
	public final  String getCompanyCode() {
		return this.companyCode;
	}

	/**
	 * set the application code of request.<br>
	 * @param applicationFormCode application code of request
	 */
	public final void setApplicationFormCode(final String applicationFormCode) {
		this.applicationFormCode = applicationFormCode;
	}

	/**
	 * get the application code of request.<br>
	 *
	 * @return application code of request
	 */
	public final String getApplicationFormCode() {
		return this.applicationFormCode;
	}

	/**
	 * set the apply company code of request.<br>
	 * @param applyCompanyCode apply company code of request
	 */
	public final void setApplyCompanyCode(final String applyCompanyCode) {
		this.applyCompanyCode = applyCompanyCode;
	}

	/**
	 * get the apply company code of request.<br>
	 * @return apply company code of request
	 */
	public final String getApplyCompanyCode() {
		return this.applyCompanyCode;
	}

	/**
	 * set the department code of request.<br>
	 * @param unitCode department code of request
	 */
	public final void setApplyUnitCode(final String applyUnitCode) {
		this.applyUnitCode = applyUnitCode;
	}

	/**
	 * get the department code of request.<br>
	 * @return department code of request
	 */
	public final String getApplyUnitCode() {
		return this.applyUnitCode;
	}

	/**
	 * set the employee number of request.<br>
	 * @param userCode employee number of request
	 */
	public final void setApplyUserCode(final String applyUserCode) {
		this.applyUserCode = applyUserCode;
	}

	/**
	 * get the employee number of request.<br>
	 * @return employee number of request
	 */
	public final String getApplyUserCode() {
		return this.applyUserCode;
	}

	/**
	 * set the deputy apply company code of request.<br>
	 * @param deputyApplyCompanyCode deputy apply company code of request
	 */
	public final void setDeputyApplyCompanyCode(final String deputyApplyCompanyCode) {
		this.deputyApplyCompanyCode = deputyApplyCompanyCode;
	}

	/**
	 * get the deputy apply company code of request.<br>
	 * @return deputy apply company code of request
	 */
	public final String getDeputyApplyCompanyCode() {
		return this.deputyApplyCompanyCode;
	}

	/**
	 * set the Department code of the proxy applicant of request.<br>
	 * @param deputyAppUnitCode Department code of the proxy applicant of request
	 */
	public final void setDeputyApplyUnitCode(final String deputyApplyUnitCode) {
		this.deputyApplyUnitCode = deputyApplyUnitCode;
	}

	/**
	 * get the Department code of the proxy applicant of request.<br>
	 * @return Department code of the proxy applicant of request
	 */
	public final String getDeputyApplyUnitCode() {
		return this.deputyApplyUnitCode;
	}

	/**
	 * set the employee number of the proxy applicant of request.<br>
	 * @param deputyAppUserCode employee number of the proxy applicant of request
	 */
	public final void setDeputyApplyUserCode(final String deputyApplyUserCode) {
		this.deputyApplyUserCode = deputyApplyUserCode;
	}

	/**
	 * get the employee number of the proxy applicant of request.<br>
	 * @return employee number of the proxy applicant of request
	 */
	public final String getDeputyApplyUserCode() {
		return this.deputyApplyUserCode;
	}

	/**
	 * @param laubeRequestSystemVisitor  Visitor class of the request system
	 * @exception LaubeException return the exception
	 */
	public final ResultDto accept(final SearchSystemVisitor searchSystemVisitor) throws LaubeException{

		ResultDto resultDto = searchSystemVisitor.visit(this);
		log.debug("[workflowEngine]" + resultDto.toString());
		return resultDto;
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
