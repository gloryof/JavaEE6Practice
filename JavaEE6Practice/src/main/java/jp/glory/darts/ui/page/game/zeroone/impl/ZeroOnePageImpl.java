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
 * ゼロワンページクラス
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "zeroOnePage")
@SessionScoped
public class ZeroOnePageImpl extends AbstractGamePage implements ZeroOnePage, Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -1481486001115423831L;

	/** ゼロワン結果ページ */
	@Inject
	private ZeroOneResultPage resultPage = null;

	/** ユーザセッションデータ */
	@Inject
	private UserSessionData userSessionData = null;

	/** ゼロワンセッションデータ */
	@Inject
	private ZeroOneSessionData zeroOneSessionData = null;

	/** 合計ラウンド数 */
	private int totalRound = 0;

	/** 残数 */
	private int restPoint = 0;

	/** 初期点数 */
	private int initPoint = 0;

	/** Prevボタン非活性フラグ */
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
	 * 画面の表示情報を設定する
	 * 
	 * @param entity ゼロワンエンティティ
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
	 * @param userSessionData セットする userSessionData
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
	 * @param zeroOneSessionData セットする zeroOneSessionData
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
	 * @param totalRound セットする totalRound
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
	 * @param restPoint セットする restPoint
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
	 * @param initPoint セットする initPoint
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
	 * @param disabledPrevButton セットする disabledPrevButton
	 */
	public void setDisabledPrevButton(final boolean disabledPrevButton) {
		this.disabledPrevButton = disabledPrevButton;
	}

	/**
	 * @param resultPage セットする resultPage
	 */
	public void setResultPage(final ZeroOneResultPage resultPage) {
		this.resultPage = resultPage;
	}

}
