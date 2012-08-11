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
 * �C���f�b�N�X�y�[�W
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "indexPage")
@RequestScoped
public class IndexPageImpl implements IndexPage {

	/** ���[�U�A�v���P�[�V���� */
	@Inject
	private UserApplication application = null;

	/** JSF���[�e�B���e�B */
	@Inject
	private JsfUtil jsfUtill = null;

	/** �Z�b�V�������[�U�f�[�^ */
	@Inject
	private UserSessionData sessionData = null;

	/** ���C�����j���[�y�[�W */
	@Inject
	@Named("mainMenuPage")
	private MainMenuPage mainMenuPage = null;

	/** ���[�UID */
	private String userId = null;

	/** �p�X���[�h */
	private String password = null;

	/**
	 * ���O�C��<br/>
	 * ���O�C���ɐ��������ꍇ�F���C�����j���[�ɑJ�ڂ���<br>
	 * ���O�C���Ɏ��s�����ꍇ�F���O�C����ʂɖ߂�<br>
	 * 
	 * @return ��ʑJ�ڃL�[
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
	 * ���O�C�������𔻒f����
	 * 
	 * @param user ���[�U�G���e�B�e�B
	 * @return ���O�C�������̏ꍇ�Ftrue�A���O�C�����s�̏ꍇ�Ffalse
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
	 * �Z�b�V�����f�[�^���쐬����
	 * 
	 * @param user ���[�U�G���e�B�e�B
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
