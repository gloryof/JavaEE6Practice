package jp.glory.darts.ui.page.menu.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.menu.MainMenuPage;
import jp.glory.darts.ui.page.util.JsfUtil;

/**
 * ���C�����j���[�y�[�W
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "mainMenuPage")
@RequestScoped
public class MainMenuPageImpl implements MainMenuPage {

	/** JSF���[�e�B���e�B */
	@Inject
	private JsfUtil util = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init() {

		return ForwardKey.MAIN_MENU.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String logout() {

		util.clearSession();

		return ForwardKey.INDEX_PAGE.getValue();
	}

	/**
	 * @param util �Z�b�g���� util
	 */
	public void setUtil(final JsfUtil util) {
		this.util = util;
	}
}
