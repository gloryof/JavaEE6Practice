package jp.glory.darts.ui.page.util;

import jp.glory.darts.common.error.ErrorInfo;

/**
 * JSFユーティリティインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface JsfUtil {

	/**
	 * エラーメッセージを追加する
	 * 
	 * @param clientId クライアントID
	 * @param errorInfo エラー情報
	 */
	void addErrorMessage(String clientId, ErrorInfo errorInfo);

	/**
	 * セッションをクリアする
	 */
	void clearSession();

	/**
	 * セッションにログイン日付を設定する
	 */
	void setLoginDateToSession();

}
