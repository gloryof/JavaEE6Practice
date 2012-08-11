package jp.glory.darts.stub.ui.page.game.countup;

import javax.enterprise.inject.Any;

import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.game.countup.CountUpResultPage;

/**
 * カウントアップ結果スタブページクラス
 * 
 * @author Junki Yamada
 * 
 */
@Any
public class CountUpResultStub implements CountUpResultPage {

	/**
	 * スタブでは画面遷移キーのみを返却する
	 */
	@Override
	public String init() {

		return ForwardKey.COUNT_UP_RESULT.getValue();
	}

	/**
	 * スタブでは何もしない
	 */
	@Override
	public String finish() {

		return null;
	}

}
