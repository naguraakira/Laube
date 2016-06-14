package site.laube.dto;

import site.laube.utility.LaubeUtility;

/**
 * Dto class is the message class for the delivery of the framework and the database.<br>
 * The class that manages the party transit master.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public class PartyTransitDto extends RouteDto {

	/**
	 * to manage the serial version ID. <br>
	 */
	private static final long serialVersionUID = -2693497943935038382L;

	/**
	 * to manage the party transit code.<br>
	 */
	private String partyTransitCode;

	/**
	 * to manage the party transit code connector.<br>
	 */
	private int partyTransitCodeConnector;

	/**
	 * to manage the conditional branching for the item name.<br>
	 */
	private String branchItemName;

	/**
	 * to manage the conditional branching for the item value.<br>
	 */
	private int branchItem;

	/**
	 * to manage the comparison operators.<br>
	 */
	private int comparisonOperator;

	/**
	 * set the party transit code.<br>
	 * @param partyTransitCode party transit code
	 */
	public final void setPartyTransitCode(final String partyTransitCode) {
		this.partyTransitCode = partyTransitCode;
	}

	/**
	 * get the party transit code.<br>
	 *
	 * @return party transit code
	 */
	public final String getPartyTransitCode() {
		return this.partyTransitCode;
	}

	/**
	 * set the party transit code connector.<br>
	 * @param partyTransitCodeConnector party transit code connector
	 */
	public final void setPartyTransitCodeConnector(final int partyTransitCodeConnector) {
		this.partyTransitCodeConnector = partyTransitCodeConnector;
	}

	/**
	 * get the party transit code connector.<br>
	 *
	 * @return party transit code connector
	 */
	public final int getPartyTransitCodeConnector() {
		return this.partyTransitCodeConnector;
	}

	/**
	 * set the conditional branching for the item name.<br>
	 * @param branchItemName branch item name
	 */
	public final void setBranchItemName(final String branchItemName) {
		this.branchItemName = branchItemName;
	}

	/**
	 * get the conditional branching for the item name.<br>
	 * @return branch item name
	 */
	public final String getBranchItemName() {
		return branchItemName;
	}

	/**
	 * set the branch item.<br>
	 * @param branchItem branch item
	 */
	public final void setBranchItem(final int branchItem) {
		this.branchItem = branchItem;
	}

	/**
	 * get the branch item.<br>
	 * @return branch item
	 */
	public final int getBranchItem() {
		return this.branchItem;
	}

	/**
	 * set the comparison operators.<br>
	 * @param comparisonOperator comparison operator
	 */
	public final void setComparisonOperator(final int comparisonOperator) {
		this.comparisonOperator = comparisonOperator;
	}

	/**
	 * get the comparison operators.<br>
	 * @param comparison operator
	 */
	public final int getComparisonOperator() {
		return comparisonOperator;
	}

	/**
	 * all the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
