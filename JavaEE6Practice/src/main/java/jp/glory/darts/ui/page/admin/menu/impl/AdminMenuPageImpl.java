package jp.glory.darts.ui.page.admin.menu.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import jp.glory.darts.ui.page.admin.menu.AdminMenuPage;
import jp.glory.darts.ui.page.common.ForwardKey;

/**
 * 管理者ページクラス
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "adminMenuPage")
@RequestScoped
public class AdminMenuPageImpl implements AdminMenuPage {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String initPage() {

		return ForwardKey.ADMIN_MENU.getValue();
	}

}
