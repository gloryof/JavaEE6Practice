package jp.glory.darts.ui.page.common;

/**
 * 画面遷移キー
 * 
 * @author Junki Yamada
 * 
 */
public enum ForwardKey {

	INDEX_PAGE("/index.xhtml"),

	MAIN_MENU("/page/content/mainMenu.xhtml"),

	COUNT_UP("/page/content/game/countup/countUp.xhtml"),
	COUNT_UP_RESULT("/page/content/game/countup/countUpResult.xhtml"),

	ZERO_ONE("/page/content/game/zeroone/zeroOne.xhtml<"),
	ZERO_ONE_RESULT("/page/content/game/zeroone/zeroOneResult.xhtml"),

	HISTORY_COUNTUP_LIST("/page/content/hisotry/countup/countUpHistoryList.xhtml"),
	HISTORY_COUNTUP_DETAIL("/page/content/hisotry/countup/countUpHistoryDetail.xhtml"),
	HISTORY_ZERO_ONE_LIST("/page/content/hisotry/zeroone/zeroOneHistoryList.xhtml"),
	HISTORY_ZERO_ONE_DETAIL("/page/content/hisotry/zeroone/zeroOneHistoryDetail.xhtml"),

	ADMIN_MENU("/page/content/admin/adminMenu.xhtml"),
	USER_LIST("/page/content/admin/userList.xhtml"),
	EDIT_USER("/page/content/admin/editUser.xhtml");

	/** 遷移キー */
	private final String value;

	/**
	 * コンストラクタ
	 * 
	 * @param value 遷移キー
	 */
	private ForwardKey(final String value) {

		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
