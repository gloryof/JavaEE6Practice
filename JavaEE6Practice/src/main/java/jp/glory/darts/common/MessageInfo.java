package jp.glory.darts.common;

/**
 * メッセージ情報
 * 
 * @author Junki Yamada
 * 
 */
public enum MessageInfo {

	ERROR_LOGIN("ユーザIDとパスワードの組み合わせが不正です。"),

	ERROR_USER_AUTHORITY("ユーザに権限を付与してください。"),
	ERROR_DUPLICATE_USER_ID("入力したユーザIDはすでに使われています。"),

	ERROR_COMMON_BLANK("{0}を入力してください。"),
	ERROR_COMMON_NO_CHECK("{0}を選択してください。"),

	INFO_UPDATE_COMPLETE("更新が完了しました。"), ;

	/** メッセージ */
	private final String message;

	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 */
	private MessageInfo(final String message) {

		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
