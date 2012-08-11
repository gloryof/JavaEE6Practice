package jp.glory.darts.stub.ui.page.game.countup;

import javax.enterprise.inject.Any;

import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.game.countup.CountUpResultPage;

/**
 * �J�E���g�A�b�v���ʃX�^�u�y�[�W�N���X
 * 
 * @author Junki Yamada
 * 
 */
@Any
public class CountUpResultStub implements CountUpResultPage {

	/**
	 * �X�^�u�ł͉�ʑJ�ڃL�[�݂̂�ԋp����
	 */
	@Override
	public String init() {

		return ForwardKey.COUNT_UP_RESULT.getValue();
	}

	/**
	 * �X�^�u�ł͉������Ȃ�
	 */
	@Override
	public String finish() {

		return null;
	}

}
