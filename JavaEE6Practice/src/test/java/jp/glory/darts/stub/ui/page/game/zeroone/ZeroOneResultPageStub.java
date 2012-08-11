package jp.glory.darts.stub.ui.page.game.zeroone;

import javax.enterprise.inject.Any;

import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.game.zeroone.ZeroOneResultPage;

@Any
public class ZeroOneResultPageStub implements ZeroOneResultPage {

	@Override
	public String init() {

		return ForwardKey.ZERO_ONE_RESULT.getValue();
	}

	@Override
	public String finish() {

		return ForwardKey.MAIN_MENU.getValue();
	}

}
