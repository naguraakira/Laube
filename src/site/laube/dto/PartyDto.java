package site.laube.dto;

import site.laube.utility.LaubeUtility;

/**
 * Dto class is the message class for the delivery of the framework and the database.<br>
 * The class that manages the party master.<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public class PartyDto extends PartyTransitDto {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = -2693497943935038382L;

	/**
	 * to manage the party code.<br>
	 */
	private String partyCode;

	/**
	 * to manage the partyCodeConnector.<br>
	 */
	private int partyCodeConnector;

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
	 * All the items in the class will be returned.<br>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
