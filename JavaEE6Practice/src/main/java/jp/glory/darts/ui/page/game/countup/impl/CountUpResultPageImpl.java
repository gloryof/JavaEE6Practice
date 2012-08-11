package jp.glory.darts.ui.page.game.countup.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.application.game.CountUpApplication;
import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.ui.page.common.CountUpSessionData;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.game.countup.CountUpResultPage;

/**
 * �J�E���g�A�b�v���ʃy�[�W�N���X
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "countUpResultPage")
@SessionScoped
public class CountUpResultPageImpl implements CountUpResultPage, Serializable {

	/**
	 * �V���A���o�[�W����UID
	 */
	private static final long serialVersionUID = 4652726296599933129L;

	/** �J�E���g�A�b�v�A�v���P�[�V���� */
	@Inject
	private CountUpApplication applicaiton = null;

	/** �J�E���g�A�b�v�Z�b�V�����f�[�^ */
	@Inject
	private CountUpSessionData countUpSessionData = null;

	/** �g�[�^���|�C���g */
	private int totalPoint = 0;

	/** �X�^�b�c */
	private double stats = 0;

	/** �uExit Game�v�{�^���\���t���O */
	private boolean viewExitGameButton = false;

	/** �uFinish�v�{�^���\���t���O */
	private boolean viewFinshButton = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init() {

		final CountUp entity = countUpSessionData.getGameInfo();

		entity.calculatePoint();
		totalPoint = entity.getPoint();

		stats = entity.getStats();

		viewExitGameButton = false;
		viewFinshButton = true;
		for (int i = 0; i < GameConst.COUNT_UP_ROUND_COUNT; i++) {

			final Round round = entity.getRound(i);
			if (!round.isEndThrowing()) {

				viewExitGameButton = true;
				viewFinshButton = false;
				break;
			}
		}

		return ForwardKey.COUNT_UP_RESULT.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String finish() {

		applicaiton.entryResult(countUpSessionData.getGameInfo());

		// TODO �Z�b�V�����j��������

		return ForwardKey.MAIN_MENU.getValue();
	}

	public void setApplicaiton(final CountUpApplication applicaiton) {
		this.applicaiton = applicaiton;
	}

	public CountUpSessionData getCountUpSessionData() {
		return countUpSessionData;
	}

	public void setCountUpSessionData(final CountUpSessionData countUpSessionData) {
		this.countUpSessionData = countUpSessionData;
	}

	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(final int totalPoint) {
		this.totalPoint = totalPoint;
	}

	public double getStats() {
		return stats;
	}

	public void setStats(final double stats) {
		this.stats = stats;
	}

	public boolean isViewExitGameButton() {
		return viewExitGameButton;
	}

	public void setViewExitGameButton(final boolean viewExitGameButton) {
		this.viewExitGameButton = viewExitGameButton;
	}

	public boolean isViewFinshButton() {
		return viewFinshButton;
	}

	public void setViewFinshButton(final boolean viewGameFinshButton) {
		this.viewFinshButton = viewGameFinshButton;
	}

}
