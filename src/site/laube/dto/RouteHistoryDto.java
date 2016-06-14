package site.laube.dto;

import site.laube.utility.LaubeUtility;

/**
 * Dtoクラスはフレームワークとデータベースの受け渡し用メッセージクラスです。<br>
 * 承認ルート履歴を管理するクラスです。<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public final class RouteHistoryDto extends ApplicationObjectDto {

	/**
	 * to manage the log.<br>
	 */
	private static final long serialVersionUID = -4751153596644033770L;

	/**
	 * to manage the history number.<br>
	 */
	private int number;

	/**
	 * set the history number.<br>
	 * @param number history number
	 */
	public final void setNumber(final int number) {
		this.number = number;
	}

	/**
	 * get the history number.<br>
	 * @return history number
	 */
	public final int getNumber() {
		return this.number;
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
