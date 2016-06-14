package site.laube.dto;

import site.laube.dto.temporary.CompanyDto;
import site.laube.utility.LaubeUtility;

/**
 * Dtoクラスはフレームワークとデータベースの受け渡し用メッセージクラスです。<br>
 * 申請分類を管理するクラスです。<br>
 * 全ての申請書はいづれかの申請分類に紐づきます。<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public class ApplicationClassificationDto extends CompanyDto {

	/**
	 * シリアルバージョンIDを管理します。<br>
	 */
	private static final long serialVersionUID = 5380706709408812768L;

	/**
	 * 申請分類コードを管理します。<br>
	 */
	private String applicationClassificationCode = null;

	/**
	 * 申請分類名を管理します。<br>
	 */
	private String applicationClassificationName = null;

	/**
	 * 表示序列を管理します。<br>
	 */
	private int sortOrder;

	/**
	 * 管理部門コードを管理します。<br>
	 * 管理部門と同じ部署の社員は全ての社員の申請書を代理申請できるようになります。<br>
	 */
	private String managementUnitCode = null;

	/**
	 * 管理部門名を管理します。<br>
	 */
	private String managementUnitName = null;

	/**
	 * 申請分類コードを設定します。<br>
	 * @param applicationClassificationCode 申請分類コード
	 */
	public final void setApplicationClassificationCode(final String applicationClassificationCode) {
		this.applicationClassificationCode = applicationClassificationCode;
	}

	/**
	 * 申請分類コードを取得します。<br>
	 * @return 申請分類コード
	 */
	public final String getApplicationClassificationCode() {
		return this.applicationClassificationCode;
	}

	/**
	 * 申請分類名を設定します。<br>
	 * @param applicationClassificationName 申請分類名
	 */
	public final void setApplicationClassificationName(final String applicationClassificationName) {
		this.applicationClassificationName = applicationClassificationName;
	}

	/**
	 * 申請分類名を取得します。<br>
	 * @return 申請分類名
	 */
	public final String getApplicationClassificationName() {
		return this.applicationClassificationName;
	}

	/**
	 * 表示序列を設定します。<br>
	 * @param sortOrder 表示序列
	 */
	public final void setSortOrder(final int sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * 表示序列を取得します。<br>
	 * @return 表示序列
	 */
	public final int getSortOrder() {
		return this.sortOrder;
	}

	/**
	 * 管理部門コードを設定します。<br>
	 * @param managementUnitCode 管理部門コード
	 */
	public final void setManagementUnitCode(final String managementUnitCode) {
		this.managementUnitCode = managementUnitCode;
	}

	/**
	 * 管理部門コードを取得します。<br>
	 * @return 管理部門コード
	 */
	public final String getManagementUnitCode() {
		return this.managementUnitCode;
	}

	/**
	 * 管理部門名を設定します。<br>
	 * @param managementUnitName 管理部門名
	 */
	public final void setManagementUnitName(final String managementUnitName) {
		this.managementUnitName = managementUnitName;
	}

	/**
	 * 管理部門名を取得します。<br>
	 * @return 管理部門名
	 */
	public final String getManagementUnitName() {
		return this.managementUnitName;
	}

	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
