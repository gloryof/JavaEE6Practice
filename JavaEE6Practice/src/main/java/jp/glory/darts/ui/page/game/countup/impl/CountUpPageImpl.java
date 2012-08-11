package jp.glory.darts.ui.page.game.countup.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.ui.page.common.CountUpSessionData;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.UserSessionData;
import jp.glory.darts.ui.page.game.common.AbstractGamePage;
import jp.glory.darts.ui.page.game.countup.CountUpPage;
import jp.glory.darts.ui.page.game.countup.CountUpResultPage;

/**
 * �J�E���g�A�b�v�y�[�W�N���X
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "countUpPage")
@SessionScoped
public class CountUpPageImpl extends AbstractGamePage implements CountUpPage, Serializable {

	/**
	 * �V���A���o�[�W����UID
	 */
	private static final long serialVersionUID = -3939137619410288524L;

	/** �J�E���g�A�b�v���ʃy�[�W */
	@Inject
	private CountUpResultPage resultPage = null;

	/** ���[�U�Z�b�V�����f�[�^ */
	@Inject
	private UserSessionData userSessionData = null;

	/** �J�E���g�A�b�v�f�[�^ */
	@Inject
	private CountUpSessionData countUpSessionData = null;

	/** �_�� */
	private int totalPoint = 0;

	/** Prev�{�^���񊈐��t���O */
	private boolean disabledPrevButton = false;

	/** Next�{�^���񊈐��t���O */
	private boolean disabledNextButton = false;

	/** Next�{�^���\���t���O */
	private boolean viewNextButton = false;

	/** End�{�^���\���t���O */
	private boolean viewEndButton = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init() {

		initCommonProperty();
		totalPoint = 0;

		disabledPrevButton = true;
		disabledNextButton = false;
		viewNextButton = true;
		viewEndButton = false;

		countUpSessionData.setGameInfo(new CountUp(0, userSessionData.getUserId()));

		return ForwardKey.COUNT_UP.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void nextRound() {

		final CountUp entity = countUpSessionData.getGameInfo();

		entity.setRoundPoint(getRoundIndex(), getNumberArray(), getCountArray());

		entity.calculatePoint();

		addRoundIndex();
		setViewInfo(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void prevRound() {

		final CountUp entity = countUpSessionData.getGameInfo();

		entity.setRoundPoint(getRoundIndex(), getNumberArray(), getCountArray());

		entity.calculatePoint();

		substractRoundIndex();
		setViewInfo(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {

		setRoundIndex(0);

		final CountUp entity = countUpSessionData.getGameInfo();
		entity.reset();
		setViewInfo(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String endGame() {

		final CountUp entity = countUpSessionData.getGameInfo();

		entity.setRoundPoint(getRoundIndex(), getNumberArray(), getCountArray());

		entity.calculatePoint();
		totalPoint = entity.getPoint();

		return resultPage.init();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String review() {

		return ForwardKey.COUNT_UP.getValue();
	}

	/**
	 * ��ʂ̕\������ݒ肷��
	 * 
	 * @param entity �J�E���g�A�b�v�G���e�B�e�B
	 */
	private void setViewInfo(final CountUp entity) {

		if (getRoundIndex() == 0) {

			setDisabledPrevButton(true);
		} else {

			setDisabledPrevButton(false);
		}

		if ((GameConst.COUNT_UP_ROUND_COUNT - 1) <= getRoundIndex()) {

			setDisabledNextButton(true);
			setViewNextButton(false);
			setViewEndButton(true);
		} else {

			setDisabledNextButton(false);
			setViewNextButton(true);
			setViewEndButton(false);
		}

		totalPoint = entity.getPoint();
		setRoundPoint(entity.getRound(getRoundIndex()));
	}

	public void setResultPage(final CountUpResultPage resultPage) {
		this.resultPage = resultPage;
	}

	public UserSessionData getUserSessionData() {
		return userSessionData;
	}

	public void setUserSessionData(final UserSessionData userSessionData) {
		this.userSessionData = userSessionData;
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

	public boolean isDisabledPrevButton() {
		return disabledPrevButton;
	}

	public void setDisabledPrevButton(final boolean disabledPrevButton) {
		this.disabledPrevButton = disabledPrevButton;
	}

	public boolean isDisabledNextButton() {
		return disabledNextButton;
	}

	public void setDisabledNextButton(final boolean disabledNextButton) {
		this.disabledNextButton = disabledNextButton;
	}

	public boolean isViewNextButton() {
		return viewNextButton;
	}

	public void setViewNextButton(final boolean viewNextButton) {
		this.viewNextButton = viewNextButton;
	}

	public boolean isViewEndButton() {
		return viewEndButton;
	}

	public void setViewEndButton(final boolean viewEndButton) {
		this.viewEndButton = viewEndButton;
	}

}
