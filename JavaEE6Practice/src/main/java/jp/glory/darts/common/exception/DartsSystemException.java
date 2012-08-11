package jp.glory.darts.common.exception;

/**
 * システム例外<br/>
 * 復帰不可能なエラーの場合はこの例外をスローする。
 * 
 * @author Admin
 * 
 */
public class DartsSystemException extends RuntimeException {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -3791143828005602993L;

	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 */
	public DartsSystemException(final String message) {

		super(message);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 * @param cause 原因例外
	 */
	public DartsSystemException(final String message, final Throwable cause) {

		super(message, cause);
	}
}
