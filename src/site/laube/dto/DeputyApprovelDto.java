package site.laube.dto;

import site.laube.dto.temporary.UserDto;
import site.laube.utility.LaubeUtility;

/**
 * Dtoクラスはフレームワークとデータベースの受け渡し用メッセージクラスです。<br>
 * 代理承認者を管理するクラスです。<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public final class DeputyApprovelDto extends UserDto {

	/**
	 * シリアルバージョンIDを管理します。<br>
	 */
	private static final long serialVersionUID = -5313076077453748225L;

	/**
	 * 代理承認者の会社コードを管理します。<br>
	 */
	private String deputyApproverlCompanyCode;

	/**
	 * 代理承認者の部門コードを管理します。<br>
	 */
	private String deputyApproverlUnitCode;

	/**
	 * 代理承認者の社員番号を管理します。<br>
	 */
	private String deputyApproverlUserCode;

	/**
	 * 代理承認者の社員名を管理します。<br>
	 */
	private String deputyApproverlUserName;

	/**
	 * 代理承認の理由を管理します。<br>
	 */
	private String deputyContents;

	/**
	 * 代理承認者の会社コードを設定します。<br>
	 * @param deputyApproverlCompanyCode 代理承認者の会社コード
	 */
	public final void setDeputyApproverlCompanyCode(final String deputyApproverlCompanyCode) {
		this.deputyApproverlCompanyCode = deputyApproverlCompanyCode;
	}

	/**
	 * 代理承認者の会社コードを取得します。<br>
	 *
	 * @return 代理承認者の会社コード
	 */
	public final String getDeputyApproverlCompanyCode() {
		return this.deputyApproverlCompanyCode;
	}

	/**
	 * 代理承認者の部門コードを設定します。<br>
	 * @param deputyApproverlUnitCode 代理承認者の部門コード
	 */
	public final void setDeputyApproverlUnitCode(final String deputyApproverlUnitCode) {
		this.deputyApproverlUnitCode = deputyApproverlUnitCode;
	}

	/**
	 * 代理承認者の部門コードを取得します。<br>
	 *
	 * @return 代理承認者の部門コード
	 */
	public final String getDeputyApproverlUnitCode() {
		return this.deputyApproverlUnitCode;
	}

	/**
	 * 代理承認者の社員番号を設定します。<br>
	 * @param deputyApproverlUserCode 代理承認者の社員番号
	 */
	public final void setDeputyApproverlUserCode(final String deputyApproverlUserCode) {
		this.deputyApproverlUserCode = deputyApproverlUserCode;
	}

	/**
	 * 代理承認者の社員番号を取得します。<br>
	 *
	 * @return 代理承認者の社員番号
	 */
	public final String getDeputyApproverlUserCode() {
		return this.deputyApproverlUserCode;
	}

	/**
	 * 代理承認者の社員名を設定します。<br>
	 * @param deputyApproverlUserName 代理承認者の社員名
	 */
	public final void setDeputyApproverlUserName(final String deputyApproverlUserName) {
		this.deputyApproverlUserName = deputyApproverlUserName;
	}

	/**
	 * 代理承認者の社員名をを取得します。<br>
	 *
	 * @return 代理承認者の社員名
	 */
	public final String getDeputyApproverlUserName() {
		return this.deputyApproverlUserName;
	}

	/**
	 * 代理承認の理由を設定します。<br>
	 * @param deputyContents 代理承認の理由
	 */
	public final void setDeputyContents(final String deputyContents) {
		this.deputyContents = deputyContents;
	}

	/**
	 * 代理承認者の社員名を取得します。<br>
	 *
	 * @return 代理承認者の社員名
	 */
	public final String getDeputyContents() {
		return this.deputyContents;
	}

	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
