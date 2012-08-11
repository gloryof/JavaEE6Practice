package jp.glory.darts.stub.ui.page.menu;

import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.menu.MainMenuPage;

/**
 * メインメニュースタブ
 * 
 * @author Junki Yamada
 * 
 */
public class MainMenuStub implements MainMenuPage {

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

		return ForwardKey.INDEX_PAGE.getValue();
	}

}
