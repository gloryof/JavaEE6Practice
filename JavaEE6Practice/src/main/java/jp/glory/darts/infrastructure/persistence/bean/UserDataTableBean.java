package jp.glory.darts.infrastructure.persistence.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * ユーザテーブルBean
 * 
 * @author Junki Yamada
 * 
 */
@Entity
@Table(name = "user_data")
@NamedQueries({
		@NamedQuery(name = UserDataTableBean.QUERY_NAME_FIND_BY_USER_ID, query = "SELECT u FROM UserDataTableBean u WHERE u.userId = :userId AND u.active = true"),
		@NamedQuery(name = UserDataTableBean.QUERY_NAME_FIND_ALL_USER, query = "SELECT u FROM UserDataTableBean u"),
		@NamedQuery(name = UserDataTableBean.QUERY_NAME_UPDATE_ACTIVE, query = "UPDATE UserDataTableBean u SET u.active = :activateValue WHERE u.userId IN :userIdList ")
})
@Cacheable(value = false)
public class UserDataTableBean implements Serializable {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 6566856619500847870L;

	/** クエリ名の接頭語 */
	private static final String QUERY_NAME_HEADER = "UserData.";

	/** クエリ名：ユーザID検索 */
	public static final String QUERY_NAME_FIND_BY_USER_ID = UserDataTableBean.QUERY_NAME_HEADER + "findByUserId";

	/** クエリ名：全ユーザ検索 */
	public static final String QUERY_NAME_FIND_ALL_USER = UserDataTableBean.QUERY_NAME_HEADER + "findAllUserData";

	/** クエリ名：有効状態更新 */
	public static final String QUERY_NAME_UPDATE_ACTIVE = UserDataTableBean.QUERY_NAME_HEADER + "updateActivateValue";

	/** ユーザID */
	@Id
	@Column(name = "user_id")
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

	public void setActive(final boolean activate) {
		this.active = activate;
	}
}
