package jp.glory.darts.common.error;

import java.text.MessageFormat;

import jp.glory.darts.common.MessageInfo;

/**
 * エラー情報
 * 
 * @author Junki Yamada
 * 
 */
public class ErrorInfo {

	/** エラーメッセージ情報 */
	private final MessageInfo messageInfo;

	/** メッセージパラメータ */
	private final String[] messageParam;

	/**
	 * コンストラクタ<br/>
	 * メッセージパラメータは空の配列を設定する
	 * 
	 * @param messageInfo エラーメッセージ情報
	 */
	public ErrorInfo(final MessageInfo messageInfo) {

		this.messageInfo = messageInfo;
		this.messageParam = new String[] {};
	}

	/**
	 * コンストラクタ<br/>
	 * 
	 * @param messageInfo エラーメッセージ情報
	 * @param messageParam メッセージパラメータ
	 */
	public ErrorInfo(final MessageInfo messageInfo, final String[] messageParam) {

		this.messageInfo = messageInfo;
		this.messageParam = messageParam;
	}

	/**
	 * エラーメッセージを取得する<br/>
	 * エラーメッセー情報のメッセージをエラーメッセージパラメータで変換する。<br/>
	 * メッセージ情報が設定されていない場合は空白を返却する。
	 * 
	 * @return エラーメッセージ
	 */
	public String getMessageValue() {

		if (messageInfo == null) {

			return "";
		}

		final MessageFormat format = new MessageFormat(messageInfo.getMessage());

		return format.format(messageParam);
	}

	public MessageInfo getMessageInfo() {
		return messageInfo;
	}

	public String[] getMessageParam() {
		return messageParam;
	}

}
