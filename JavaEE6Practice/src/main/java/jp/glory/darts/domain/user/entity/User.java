package jp.glory.darts.domain.user.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.common.Entity;
import jp.glory.darts.domain.user.value.Authority;

import org.apache.commons.lang.StringUtils;

/**
 * ユーザエンティティ
 * 
 * @author Junki Yamada
 * 
 */
public class User implements Entity<User> {

	/** ユーザID */
	private final String userId;

	/** ユーザ名 */
	private String name = null;

	/** パスワード */
	private String password = null;

	/** 有効状態 */
	private boolean active = false;

	/** 権限リスト */
	private final List<Authority> authorityList = new ArrayList<>();

	/**
	 * コンストラクタ
	 * 
	 * @param userId ユーザID
	 */
	public User(final String userId) {

		if (StringUtils.isEmpty(userId)) {

			throw new IllegalArgumentException("User entity is required userId");
		}

		this.userId = userId;
	}

	/**
	 * 権限を保持しているかチェックする
	 * 
	 * @param type 権限
	 * @return 権限を保持している場合：true、権限を保持していない場合：false
	 */
	public boolean hasAuthority(final AuthorityType type) {

		if (type == null) {

			return false;
		}

		for (final Authority authority : this.authorityList) {

			if (authority.getAuthorityType().equals(type)) {

				return true;
			}
		}

		return false;
	}

	/**
	 * 権限を追加する
	 * 
	 * @param type 権限
	 */
	public void addAuthority(final AuthorityType type) {

		if (!hasAuthority(type)) {

			this.authorityList.add(new Authority(type));
		}
	}

	/**
	 * 入力チェックを行う。<br/>
	 * チェックした結果、エラー情報リストを返却する。
	 * 
	 * @return エラー情報リスト
	 */
	public List<ErrorInfo> validate() {

		final List<ErrorInfo> returnList = new ArrayList<>();

		if (StringUtils.isEmpty(name)) {

			final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_BLANK;
			final String[] messageParam = new String[] {
				"ユーザ名"
			};
			returnList.add(new ErrorInfo(messageInfo, messageParam));
		}

		if (StringUtils.isEmpty(password)) {

			final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_BLANK;
			final String[] messageParam = new String[] {
				"パスワード"
			};
			returnList.add(new ErrorInfo(messageInfo, messageParam));
		}

		if (authorityList.size() < 1) {

			final MessageInfo messageInfo = MessageInfo.ERROR_USER_AUTHORITY;
			returnList.add(new ErrorInfo(messageInfo));
		}

		return returnList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSame(final User other) {

		if (other == null) {

			return false;
		}

		return this.userId.equals(other.getUserId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User copyEntity() {

		final User copyEntity = new User(this.userId);
		copyEntity.setPassword(new String(this.password));
		copyEntity.setName(new String(this.name));

		for (Authority authority : this.authorityList) {

			copyEntity.addAuthority(authority.getAuthorityType());
		}

		return copyEntity;
	}

	public String getUserId() {
		return userId;
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

	public List<Authority> getAuthorityList() {
		return Collections.unmodifiableList(this.authorityList);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}
}
