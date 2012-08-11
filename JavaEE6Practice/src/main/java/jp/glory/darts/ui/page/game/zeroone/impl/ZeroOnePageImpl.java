package jp.glory.darts.ui.page.game.zeroone.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.UserSessionData;
import jp.glory.darts.ui.page.common.ZeroOneSessionData;
import jp.glory.darts.ui.page.game.common.AbstractGamePage;
import jp.glory.darts.ui.page.game.zeroone.ZeroOnePage;
import jp.glory.darts.ui.page.game.zeroone.ZeroOneResultPage;

/**
 * �[�������y�[�W�N���X
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "zeroOnePage")
@SessionScoped
public class ZeroOnePageImpl extends AbstractGamePage implements ZeroOnePage, Serializable {

	/**
	 * �V���A���o�[�W����UID
	 */
	private static final long serialVersionUID = -1481486001115423831L;

	/** �[���������ʃy�[�W */
	@Inject
	private ZeroOneResultPage resultPage = null;

	/** ���[�U�Z�b�V�����f�[�^ */
	@Inject
	private UserSessionData userSessionData = null;

	/** �[�������Z�b�V�����f�[�^ */
	@Inject
	private ZeroOneSessionData zeroOneSessionData = null;

	/** ���v���E���h�� */
	private int totalRound = 0;

	/** �c�� */
	private int restPoint = 0;

	/** �����_�� */
	private int initPoint = 0;

	/** Prev�{�^���񊈐��t���O */
	private boolean disabledPrevButton = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init(final int ruleTypeCode) {

		final ZeroOneGameRules rule = ZeroOneGameRules.convertGameRule(ruleTypeCode);

		initCommonProperty();

		totalRound = rule.getRound();
		restPoint = rule.getPoint();
		initPoint = rule.getPoint();

		disabledPrevButton = true;

		zeroOneSessionData.setGameInfo(new ZeroOne(0, userSessionData.getUserId(), rule));

		return ForwardKey.ZERO_ONE.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String review() {

		final ZeroOne entity = zeroOneSessionData.getGameInfo();
		int viewIndex = 0;

		if (entity.isEndGame()) {

			viewIndex = entity.getEndRoundIndex();
		} else {

			for (final Round round : entity.getRounds()) {

				if (!round.isEndThrowing()) {

					break;
				}
				viewIndex++;
			}
		}

		setRoundIndex(viewIndex);
		setViewInfo(entity);

		return ForwardKey.ZERO_ONE.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String next() {

		final ZeroOne entity = zeroOneSessionData.getGameInfo();

		entity.setRoundPoint(getRoundIndex(), getNumberArray(), getCountArray());

		entity.calculatePoint();

		if (entity.isEndGame()) {

			entity.setEndRoundIndex(getRoundIndex());
			return resultPage.init();
		} else {

			addRoundIndex();
			setViewInfo(entity);

			return ForwardKey.ZERO_ONE.getValue();
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void prevRound() {

		final ZeroOne entity = zeroOneSessionData.getGameInfo();

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

		final ZeroOne entity = zeroOneSessionData.getGameInfo();
		entity.reset();

		final ZeroOneGameRules rule = entity.getRule();
		restPoint = rule.getPoint();
		initPoint = rule.getPoint();

		setViewInfo(entity);
	}

	/**
	 * ��ʂ̕\������ݒ肷��
	 * 
	 * @param entity �[�������G���e�B�e�B
	 */
	private void setViewInfo(final ZeroOne entity) {

		if (getRoundIndex() == 0) {

			setDisabledPrevButton(true);
		} else {

			setDisabledPrevButton(false);
		}

		restPoint = entity.getRestPoint();
		setRoundPoint(entity.getRound(getRoundIndex()));
	}

	/**
	 * @return userSessionData
	 */
	public UserSessionData getUserSessionData() {
		return userSessionData;
	}

	/**
	 * @param userSessionData �Z�b�g���� userSessionData
	 */
	public void setUserSessionData(final UserSessionData userSessionData) {
		this.userSessionData = userSessionData;
	}

	/**
	 * @return zeroOneSessionData
	 */
	public ZeroOneSessionData getZeroOneSessionData() {
		return zeroOneSessionData;
	}

	/**
	 * @param zeroOneSessionData �Z�b�g���� zeroOneSessionData
	 */
	public void setZeroOneSessionData(final ZeroOneSessionData zeroOneSessionData) {
		this.zeroOneSessionData = zeroOneSessionData;
	}

	/**
	 * @return totalRound
	 */
	public int getTotalRound() {
		return totalRound;
	}

	/**
	 * @param totalRound �Z�b�g���� totalRound
	 */
	public void setTotalRound(final int totalRound) {
		this.totalRound = totalRound;
	}

	/**
	 * @return restPoint
	 */
	public int getRestPoint() {
		return restPoint;
	}

	/**
	 * @param restPoint �Z�b�g���� restPoint
	 */
	public void setRestPoint(final int restPoint) {
		this.restPoint = restPoint;
	}

	/**
	 * @return initPoint
	 */
	public int getInitPoint() {
		return initPoint;
	}

	/**
	 * @param initPoint �Z�b�g���� initPoint
	 */
	public void setInitPoint(final int initPoint) {
		this.initPoint = initPoint;
	}

	/**
	 * @return disabledPrevButton
	 */
	public boolean isDisabledPrevButton() {
		return disabledPrevButton;
	}

	/**
	 * @param disabledPrevButton �Z�b�g���� disabledPrevButton
	 */
	public void setDisabledPrevButton(final boolean disabledPrevButton) {
		this.disabledPrevButton = disabledPrevButton;
	}

	/**
	 * @param resultPage �Z�b�g���� resultPage
	 */
	public void setResultPage(final ZeroOneResultPage resultPage) {
		this.resultPage = resultPage;
	}

}
