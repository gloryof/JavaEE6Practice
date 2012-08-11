package jp.glory.darts.stub.ui.page.util;

import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.inject.Any;

import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.ui.page.util.JsfUtil;

@Any
public class JsfUtilStub implements JsfUtil {

	/** エラー情報マップ */
	private final Map<String, ErrorInfo> errorInfoMap;

	/**
	 * コンストラクタ<br>
	 * エラー情報マップを初期化する
	 */
	public JsfUtilStub() {

		this.errorInfoMap = new TreeMap<String, ErrorInfo>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addErrorMessage(final String clientId, final ErrorInfo errorInfo) {

		errorInfoMap.put(clientId, errorInfo);
	}

	/**
	 * スタブでは何もしない
	 */
	@Override
	public void clearSession() {

	}

	/**
	 * エラー情報の件数を返す
	 * 
	 * @return エラー情報件数
	 */
	public int getErrorCount() {

		if (errorInfoMap != null) {

			return errorInfoMap.size();
		}

		return 0;
	}

	/**
	 * エラー情報をインデックスで取得する
	 * 
	 * @param paramIndex インデックス
	 * @return エラー情報
	 */
	public ErrorInfo getByIndex(final int paramIndex) {

		int index = 0;
		for (Map.Entry<String, ErrorInfo> entry : errorInfoMap.entrySet()) {

			if (paramIndex == index) {

				return entry.getValue();
			}

			index++;
		}

		return null;
	}

	/**
	 * スタブでは何もしない
	 */
	@Override
	public void setLoginDateToSession() {

	}
}
