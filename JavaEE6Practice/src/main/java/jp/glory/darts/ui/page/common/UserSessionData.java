package jp.glory.darts.ui.page.common;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import jp.glory.darts.domain.common.AuthorityType;

/**
 * ユーザセッションデータ
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "userSessionData")
@SessionScoped
public class UserSessionData implements Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 2919276059824025159L;

	/** ユーザID */
	private String userId = null;

	/** ユーザ名 */
	private String name = null;

	/** 権限リスト */
	private List<AuthorityType> authorityList = null;

	/**
	 * ユーザ権限を保持しているかを判断する
	 * 
	 * @return ユーザ権限がある場合：true、ユーザ権限がない場合：false
	 */
	public boolean hasUserAuthority() {

		if (authorityList == null) {

			return false;
		}

		return authorityList.contains(AuthorityType.USER);
	}

	/**
	 * 管理者権限を保持しているかを判断する
	 * 
	 * @return 管理者権限がある場合：true、管理者権限がない場合：false
	 */
	public boolean hasAdminAuthority() {

		if (authorityList == null) {

			return false;
		}

		return authorityList.contains(AuthorityType.ADMINISTRATOR);
	}

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

	public List<AuthorityType> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(final List<AuthorityType> authorityList) {
		this.authorityList = authorityList;
	}

}
