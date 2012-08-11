package jp.glory.darts.ui.page.admin.user.bean;

/**
 * 画面表示用のユーザデータ
 * 
 * @author Junki Yamada
 * 
 */
public class UserDataPageBean {

	/** ユーザID */
	private String userId = null;

	/** ユーザ名 */
	private String name = null;

	/** パスワード */
	private String password = null;

	/** 有効状態 */
	private boolean active = false;

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}
}
