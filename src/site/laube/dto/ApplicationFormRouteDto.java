package site.laube.dto;

import site.laube.utility.LaubeUtility;

/**
 * Dtoクラスはフレームワークとデータベースの受け渡し用メッセージクラスです。<br>
 * 申請書別ルートマスタを管理するクラスです。<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public final class ApplicationFormRouteDto extends ApplicationFormDto {

	/**
	 * シリアルバージョンIDを管理します。<br>
	 */
	private static final long serialVersionUID = -8336258002631442544L;

	/**
	 * 部門コードを管理します。<br>
	 */
	private String unitCode;

	/**
	 * 部門名を管理します。<br>
	 */
	private String unitName;

	/**
	 * 個別ルートコードを管理します。<br>
	 */
	private String individualRouteCode;

	/**
	 * 個別ルート名を管理します。<br>
	 */
	private String individualRouteName;

	/**
	 * 共通ルートコードを管理します。<br>
	 */
	private String commonRouteCode;

	/**
	 * 共通ルート名を管理します。<br>
	 */
	private String commonRouteName;

	/**
	 * 部門コードを設定します。
	 * @param unitCode 部門コード
	 */
	public final void setUnitCode(final String unitCode) {
		this.unitCode = unitCode;
	}

	/**
	 * 部門コードを取得します。
	 *
	 * @return 部門コード
	 */
	public final String getUnitCode() {
		return this.unitCode;
	}

	/**
	 * 部門名を設定します。
	 * @param unitName 部門名
	 */
	public final void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 部門名を取得します。
	 *
	 * @return 部門名
	 */
	public final String getUnitName() {
		return this.unitName;
	}

	/**
	 * 個別ルートコードを設定します。
	 * @param individualRouteCode 個別ルートコード
	 */
	public final void setIndividualRouteCode(final String individualRouteCode) {
		this.individualRouteCode = individualRouteCode;
	}

	/**
	 * 個別ルートコードを取得します。
	 *
	 * @return 個別ルートコード
	 */
	public final String getIndividualRouteCode() {
		return this.individualRouteCode;
	}

	/**
	 * 個別ルート名を設定します。
	 * @param individualRouteName 個別ルート名
	 */
	public final void setIndividualRouteName(final String individualRouteName) {
		this.individualRouteName = individualRouteName;
	}

	/**
	 * 個別ルート名を取得します。
	 *
	 * @return 個別ルート名
	 */
	public final String getIndividualRouteName() {
		return this.individualRouteName;
	}

	/**
	 * 共通ルートコードを設定します。
	 * @param commonRouteCode 共通ルートコード
	 */
	public final void setCommonRouteCode(final String commonRouteCode) {
		this.commonRouteCode = commonRouteCode;
	}

	/**
	 * 共通ルートコードを取得します。
	 *
	 * @return 共通ルートコード
	 */
	public final String getCommonRouteCode() {
		return this.commonRouteCode;
	}

	/**
	 * 共通ルート名を設定します。
	 * @param commonRouteName 共通ルート名
	 */
	public void setCommonRouteName(String commonRouteName) {
		this.commonRouteName = commonRouteName;
	}

	/**
	 * 共通ルート名を取得します。
	 *
	 * @return 共通ルート名
	 */
	public String getCommonRouteName() {
		return this.commonRouteName;
	}

	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
