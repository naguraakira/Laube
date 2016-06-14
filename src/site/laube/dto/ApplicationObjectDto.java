package site.laube.dto;

import site.laube.dto.temporary.CompanyDto;
import site.laube.utility.LaubeUtility;

/**
 * Dtoクラスはフレームワークとデータベースの受け渡し用メッセージクラスです。<br>
 * 申請オブジェクトを管理するクラスです。<br>
 *
 * @version    1.0.0
 * @since      Class deprecated in Release 1.0.0
 * @author     Ryuta Miki of Pocket Soft Co.,Ltd
 */
public class ApplicationObjectDto extends CompanyDto {

	/**
	 * シリアルバージョンIDを管理します。<br>
	 */
	private static final long serialVersionUID = -3758242472539403511L;

	/**
	 * 申請番号を管理します。<br>
	 */
	private int applicationNumber;

	/**
	 * 再申請前の申請番号を管理します。<br>
	 */
	private int reapplicationNumber;

	/**
	 * 申請分類コードを管理します。<br>
	 */
	private String applicationClassificationCode;

	/**
	 * 申請分類名を管理します。<br>
	 */
	private String applicationClassificationName;

	/**
	 * 申請書コードを管理します。<br>
	 */
	private String applicationFormCode;

	/**
	 * 申請書名を管理します。<br>
	 */
	private String applicationFormName;

	/**
	 * 申請者の会社コードを管理します。<br>
	 */
	private String applyCompanyCode;

	/**
	 * 申請者の会社名を管理します。<br>
	 */
	private String applyCompanyName;

	/**
	 * 申請者の部門コードを管理します。<br>
	 */
	private String applyUnitCode;

	/**
	 * 申請者の部門名を管理します。<br>
	 */
	private String applyUnitName;

	/**
	 * 申請者の社員番号を管理します。<br>
	 */
	private String applyUserCode;

	/**
	 * 申請者の社員名を管理します。<br>
	 */
	private String applyUserName;

	/**
	 * 代理申請者の会社コードを管理します。<br>
	 */
	private String deputyApplyCompanyCode;

	/**
	 * 代理申請者の会社名を管理します。<br>
	 */
	private String deputyApplyCompanyName;

	/**
	 * 代理申請者の部門コードを管理します。<br>
	 */
	private String deputyApplyUnitCode;

	/**
	 * 代理申請者の部門名を管理します。<br>
	 */
	private String deputyApplyUnitName;

	/**
	 * 代理申請者の社員番号を管理します。<br>
	 */
	private String deputyApplyUserCode;

	/**
	 * 代理申請者の社員名を管理します。<br>
	 */
	private String deputyApplyUserName;

	/**
	 * 申請日を管理します。<br>
	 */
	private String applyDate;

	/**
	 * 申請書の状態を管理します。<br>
	 */
	private int applicationStatus;

	/**
	 * 申請書の状態名を管理します。<br>
	 */
	private String applicationStatusName;

	/**
	 * 申請番号を設定します。<br>
	 * @param applicationNumber 申請番号
	 */
	public final void setApplicationNumber(final int applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * 申請番号を取得します。<br>
	 *
	 * @return 申請番号
	 */
	public final int getApplicationNumber() {
		return this.applicationNumber;
	}

	/**
	 * 再申請前の申請番号を設定します。<br>
	 * @param reApplicationNumber 再申請前の申請番号
	 */
	public final void setReapplicationNumber(final int reapplicationNumber) {
		this.reapplicationNumber = reapplicationNumber;
	}

	/**
	 * 再申請前の申請番号を取得します。<br>
	 *
	 * @return 再申請前の申請番号
	 */
	public final int getReapplicationNumber() {
		return this.reapplicationNumber;
	}

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
	 * 申請書コードを設定します。<br>
	 * @param applicationFormCode 申請書コード
	 */
	public final void setApplicationFormCode(final String applicationFormCode) {
		this.applicationFormCode = applicationFormCode;
	}

	/**
	 * 申請書コードを取得します。<br>
	 *
	 * @return 申請書コード
	 */
	public final String getApplicationFormCode() {
		return this.applicationFormCode;
	}

	/**
	 * 申請書名を設定します。<br>
	 * @param applicationFormName 申請書名
	 */
	public final void setApplicationFormName(final String applicationFormName) {
		this.applicationFormName = applicationFormName;
	}

	/**
	 * 申請書名を取得します。<br>
	 *
	 * @return 申請書名
	 */
	public final String getApplicationFormName() {
		return this.applicationFormName;
	}

	/**
	 * 申請者の会社コードを設定します。<br>
	 * @param applyCompanyCode 申請者の会社コード
	 */
	public final void setApplyCompanyCode(final String applyCompanyCode) {
		this.applyCompanyCode = applyCompanyCode;
	}

	/**
	 * 申請者の会社コードを取得します。<br>
	 * @return 申請者の会社コード
	 */
	public final String getApplyCompanyCode() {
		return this.applyCompanyCode;
	}

	/**
	 * 申請者の会社名を設定します。<br>
	 * @param applyCompanyName 申請者の会社名
	 */
	public final void setApplyCompanyName(final String applyCompanyName) {
		this.applyCompanyName = applyCompanyName;
	}

	/**
	 * 申請者の会社名を取得します。<br>
	 * @return 申請者の会社名
	 */
	public final String getApplyCompanyName() {
		return this.applyCompanyName;
	}

	/**
	 * 申請者の部門コードを設定します。<br>
	 * @param applyUnitCode 申請者の部門コード
	 */
	public final void setApplyUnitCode(final String applyUnitCode) {
		this.applyUnitCode = applyUnitCode;
	}

	/**
	 * 申請者の部門コードを取得します。<br>
	 * @return 申請者の部門コード
	 */
	public final String getApplyUnitCode() {
		return this.applyUnitCode;
	}

	/**
	 * 申請者の部門名を設定します。<br>
	 * @param appUnitName 申請者の部門名
	 */
	public final void setApplyUnitName(final String applyUnitName) {
		this.applyUnitName = applyUnitName;
	}

	/**
	 * 申請者の部門名を取得します。<br>
	 * @return 申請者の部門名
	 */
	public final String getApplyUnitName() {
		return this.applyUnitName;
	}

	/**
	 * 申請者の社員番号を設定します。<br>
	 * @param appUserCode 申請者の社員番号
	 */
	public final void setApplyUserCode(final String applyUserCode) {
		this.applyUserCode = applyUserCode;
	}

	/**
	 * 申請者の社員番号を取得します。<br>
	 * @return 申請者の社員番号
	 */
	public final String getApplyUserCode() {
		return this.applyUserCode;
	}

	/**
	 * 申請者の社員名を設定します。<br>
	 * @param appUserName 申請者の社員名
	 */
	public final void setApplyUserName(final String applyUserName) {
		this.applyUserName = applyUserName;
	}

	/**
	 * 申請者の社員名を取得します。<br>
	 * @return 申請者の社員名
	 */
	public final String getApplyUserName() {
		return this.applyUserName;
	}

	/**
	 * 代理申請者の会社コードを設定します。<br>
	 * @param deputyApplyCompanyCode 代理申請者の会社コード
	 */
	public final void setDeputyApplyCompanyCode(final String deputyApplyCompanyCode) {
		this.deputyApplyCompanyCode = deputyApplyCompanyCode;
	}

	/**
	 * 代理申請者の会社コードを取得します。<br>
	 * @return 代理申請者の会社コード
	 */
	public final String getDeputyApplyCompanyCode() {
		return this.deputyApplyCompanyCode;
	}

	/**
	 * 代理申請者の会社名を設定します。<br>
	 * @param deputyApplyCompanyName 代理申請者の会社名
	 */
	public final void setDeputyApplyCompanyName(final String deputyApplyCompanyName) {
		this.deputyApplyCompanyName = deputyApplyCompanyName;
	}

	/**
	 * 代理申請者の会社名を取得します。<br>
	 * @return 代理申請者の会社名
	 */
	public final String getDeputyApplyCompanyName() {
		return this.deputyApplyCompanyName;
	}

	/**
	 * 代理申請者の部門コードを設定します。<br>
	 * @param deputyApplyUnitCode 代理申請者の部門コード
	 */
	public final void setDeputyApplyUnitCode(final String deputyApplyUnitCode) {
		this.deputyApplyUnitCode = deputyApplyUnitCode;
	}

	/**
	 * 代理申請者の部門コードを取得します。<br>
	 * @return 代理申請者の部門コード
	 */
	public final String getDeputyApplyUnitCode() {
		return this.deputyApplyUnitCode;
	}

	/**
	 * 代理申請者の部門名を設定します。<br>
	 * @param deputyAppUnitName 代理申請者の部門名
	 */
	public final void setDeputyApplyUnitName(final String deputyApplyUnitName) {
		this.deputyApplyUnitName = deputyApplyUnitName;
	}

	/**
	 * 代理申請者の部門名を取得します。<br>
	 * @return 代理申請者の部門名
	 */
	public final String getDeputyApplyUnitName() {
		return this.deputyApplyUnitName;
	}

	/**
	 * 代理申請者の社員番号を設定します。<br>
	 * @param deputyAppUserCode 代理申請者の社員番号
	 */
	public final void setDeputyApplyUserCode(final String deputyApplyUserCode) {
		this.deputyApplyUserCode = deputyApplyUserCode;
	}

	/**
	 * 代理申請者の社員番号を取得します。<br>
	 * @return 代理申請者の社員番号
	 */
	public final String getDeputyApplyUserCode() {
		return this.deputyApplyUserCode;
	}

	/**
	 * 代理申請者の社員名を設定します。<br>
	 * @return 代理申請者の社員名
	 */
	public final String getDeputyApplyUserName() {
		return this.deputyApplyUserName;
	}

	/**
	 * 代理申請者の社員名を取得します。<br>
	 * @param deputyAppUserName 代理申請者の社員名
	 */
	public final void setDeputyApplyUserName(final String deputyApplyUserName) {
		this.deputyApplyUserName = deputyApplyUserName;
	}

	/**
	 * 申請日を設定します。<br>
	 * @param applyDate 申請日
	 */
	public final void setApplyDate(final String applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * 申請日を取得します。<br>
	 *
	 * @return 申請日
	 */
	public final String getApplyDate() {

		if (this.applyDate != null){
			this.applyDate = this.applyDate.trim().replace('-', '/');
		}
		return this.applyDate;
	}

	/**
	 * 申請書の状態を設定します。<br>
	 * @param applicationStatus 申請書の状態
	 */
	public final void setApplicationStatus(final int applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	/**
	 * 申請書の状態を取得します。<br>
	 *
	 * @return 申請書の状態
	 */
	public final int getApplicationStatus() {
		return this.applicationStatus;
	}

	/**
	 * 申請書の状態名を設定します。<br>
	 * @param applicationStatusName 申請書の状態名
	 */
	public void setApplicationStatusName(String applicationStatusName) {
		this.applicationStatusName = applicationStatusName;
	}

	/**
	 * 申請書の状態名を取得します。<br>
	 * @return applicationStatusName
	 */
	public String getApplicationStatusName() {
		return this.applicationStatusName;
	}

	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return LaubeUtility.reflectionToString(this);
	}
}
