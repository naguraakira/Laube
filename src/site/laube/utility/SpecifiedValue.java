package site.laube.utility;
import java.io.Serializable;

/*
 * Copyright (c) 2016, Ryuta Miki All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public final class SpecifiedValue implements Serializable {

	/**
	 * to manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = 2824047883202335433L;

	// screen mode
	public static final int ApplyMode                          = 1;   // 申請モード
	public static final int ApprovalMode                       = 2;   // 承認モード
	public static final int ConfirmationMode                   = 3;   // 確認モード
	public static final int SeeMode                            = 4;   // 参照モード

	// route flag
	public static final int IndividualRouteFlag                = 1;   // 個別ルートあり
	public static final int BossRouteFlag                      = 2;   // 上司ルート利用
	public static final int NoIndividualRouteFlag              = 3;   // 個別ルートなし

	// route type
	public static final int None                               = 0;   // 初期値
	public static final int IndividualRoute                    = 1;   // 個別
	public static final int CommonRoute                        = 2;   // 共通
	public static final int Special                            = 3;   // 特別

	// party code
	public static final String END                             = "E"; // 終了パーティコード

	// connector
	public static final int Unspecified                        = 0;   // 指定なし
	public static final int LogicalSum                         = 1;   // 論理和
	public static final int LogicalProduct                     = 2;   // 論理積

	// approval function
	public static final int InitialState                       = 0;   // 初期状態
	public static final int Examination                        = 1;   // 承認
	public static final int FunctionConfirmation               = 2;   // 確認

	// comparison operator
	public static final int Smaller                            = 1;   // より小さい
	public static final int Same                               = 2;   // 同じ
	public static final int Unequal                            = 3;   // 同じでない
	public static final int TheFollowing                       = 4;   // 以下
	public static final int OrMore                             = 5;   // 以上
	public static final int LargerThan                         = 6;   // より大きい

	// application form status
	public static final int Draft                              = 0;   // 下書き
	public static final int UnderExamination                   = 1;   // 審査中
	public static final int Remand                             = 2;   // 差戻し
	public static final int Pullback                           = 3;   // 引戻し
	public static final int Withdrawal                         = 4;   // 取り下げ
	public static final int Denial                             = 5;   // 否認済
	public static final int Approved                           = 6;   // 承認済

	// apploval user status
	public static final int Authorizer_Untreated               = 0;   // 未着
	public static final int Arrival                            = 1;   // 到着
	public static final int Hold                               = 2;   // 保留
	public static final int Authorizer_AnticipatingApproval    = 3;   // 先取り承認(順番を無視して先に承認)
	public static final int Authorizer_HikeApproval            = 4;   // 引上げ承認(上司が承認すると未承認の下位は自動的に承認)
	public static final int Authorizer_AutomaticApproval       = 5;   // 自動承認
	public static final int Authorizer_Denial                  = 6;   // 否認済
	public static final int Authorizer_Approval                = 7;   // 承認済
	public static final int Authorizer_Confirmation            = 8;   // 確認済

	/**
	 * to manage the encryption password.<br>
	 */
	public static final String CipherPassword = "PocketSoft Co.,Ltd";

	/**
	 * date format<br>
	 */
	public static final String[] DateFormat =  new String[]{"yyyy-MM-dd", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss"};
}
