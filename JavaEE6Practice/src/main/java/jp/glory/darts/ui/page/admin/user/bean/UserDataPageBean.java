package jp.glory.darts.ui.page.admin.user.bean;

/**
 * ��ʕ\���p�̃��[�U�f�[�^
 * 
 * @author Junki Yamada
 * 
 */
public class UserDataPageBean {

	/** ���[�UID */
	private String userId = null;

	/** ���[�U�� */
	private String name = null;

	/** �p�X���[�h */
	private String password = null;

	/** �L����� */
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
