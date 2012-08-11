package jp.glory.darts.common.exception;

import java.util.List;

import jp.glory.darts.common.error.ErrorInfo;

/**
 * 業務例外<br/>
 * 業務上あり得るエラーの場合はこの例外をスローする。
 * 
 * @author Admin
 * 
 */
public class BusinessException extends Exception {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 4064602001528181433L;

	/** エラー情報リスト */
	private final List<ErrorInfo> errorInfoList;

	/**
	 * コンストラクタ
	 * 
	 * @param errorInfoList エラー情報リスト
	 */
	public BusinessException(final List<ErrorInfo> errorInfoList) {

		this.errorInfoList = errorInfoList;
	}

	public List<ErrorInfo> getErrorInfoList() {
		return errorInfoList;
	}
}
