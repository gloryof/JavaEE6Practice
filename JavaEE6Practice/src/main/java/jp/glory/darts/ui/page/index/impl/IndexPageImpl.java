package jp.glory.darts.ui.page.index.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.application.user.UserApplication;
import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.domain.user.value.Authority;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.UserSessionData;
import jp.glory.darts.ui.page.index.IndexPage;
import jp.glory.darts.ui.page.menu.MainMenuPage;
import jp.glory.darts.ui.page.util.JsfUtil;

/**
 * インデックスページ
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "indexPage")
@RequestScoped
public class IndexPageImpl implements IndexPage {

	/** ユーザアプリケーション */
	@Inject
	private UserApplication application = null;

	/** JSFユーティリティ */
	@Inject
	private JsfUtil jsfUtill = null;

	/** セッションユーザデータ */
	@Inject
	private UserSessionData sessionData = null;

	/** メインメニューページ */
	@Inject
	@Named("mainMenuPage")
	private MainMenuPage mainMenuPage = null;

	/** ユーザID */
	private String userId = null;

	/** パスワード */
	private String password = null;

	/**
	 * ログイン<br/>
	 * ログインに成功した場合：メインメニューに遷移する<br>
	 * ログインに失敗した場合：ログイン画面に戻る<br>
	 * 
	 * @return 画面遷移キー
	 */
	@Override
	public String login() {

		final User user = application.getUser(userId);

		if (!isLoginComplete(user)) {

			jsfUtill.addErrorMessage("login", new ErrorInfo(MessageInfo.ERROR_LOGIN));

			return ForwardKey.INDEX_PAGE.getValue();
		}

		createSessioinData(user);
		jsfUtill.setLoginDateToSession();

		return mainMenuPage.init();
	}

	/**
	 * ログイン完了を判断する
	 * 
	 * @param user ユーザエンティティ
	 * @return ログイン成功の場合：true、ログイン失敗の場合：false
	 */
	private boolean isLoginComplete(final User user) {

		if (user == null) {

			return false;
		}

		if (!user.getPassword().equals(password)) {

			return false;
		}

		return true;
	}

	public UserSessionData getSessionData() {
		return sessionData;
	}

	public void setSessionData(final UserSessionData sessionData) {
		this.sessionData = sessionData;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setApplication(final UserApplication application) {
		this.application = application;
	}

	public void setJsfUtill(final JsfUtil jsfUtill) {
		this.jsfUtill = jsfUtill;
	}

	public void setMainMenuPage(final MainMenuPage mainMenuPage) {
		this.mainMenuPage = mainMenuPage;
	}

	/**
	 * セッションデータを作成する
	 * 
	 * @param user ユーザエンティティ
	 */
	private void createSessioinData(final User user) {

		sessionData.setUserId(user.getUserId());
		sessionData.setName(user.getName());

		final List<AuthorityType> authorityList = new ArrayList<>();
		for (final Authority authority : user.getAuthorityList()) {

			authorityList.add(authority.getAuthorityType());
		}
		sessionData.setAuthorityList(authorityList);
	}

}
