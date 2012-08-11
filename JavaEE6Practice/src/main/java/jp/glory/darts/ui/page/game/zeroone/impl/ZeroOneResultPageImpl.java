package jp.glory.darts.ui.page.game.zeroone.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.application.game.ZeroOneApplication;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.ZeroOneSessionData;
import jp.glory.darts.ui.page.game.zeroone.ZeroOneResultPage;

/**
 * ゼロワン結果ページクラス
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "zeroOneResultPage")
@SessionScoped
public class ZeroOneResultPageImpl implements ZeroOneResultPage, Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 232832714478895958L;

	/** ゼロワンセッションデータ */
	@Inject
	private ZeroOneSessionData sessionData = null;

	/** ゼロワンアプリケーション */
	@Inject
	private ZeroOneApplication application = null;

	/** 終了ラウンド */
	private int dartsOutRound = 0;

	/** スタッツ */
	private double stats = 0;

	/** 残り点数 */
	private int restPoint = 0;

	/** 「Exit Game」ボタン表示フラグ */
	private boolean viewExitGameButton = false;

	/** 「Finish」ボタン表示フラグ */
	private boolean viewFinshButton = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init() {

		final ZeroOne entity = sessionData.getGameInfo();

		restPoint = entity.getRestPoint();

		stats = entity.getStats();
		if (entity.isEndGame()) {

			viewExitGameButton = false;
			viewFinshButton = true;
			dartsOutRound = entity.getLastThrowingRound();
		} else {

			viewExitGameButton = true;
			viewFinshButton = false;
			dartsOutRound = 0;
		}

		return ForwardKey.ZERO_ONE_RESULT.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String finish() {

		application.entryResult(sessionData.getGameInfo());

		// TODO セッション破棄したい

		return ForwardKey.MAIN_MENU.getValue();
	}

	public ZeroOneSessionData getSessionData() {
		return sessionData;
	}

	public void setSessionData(final ZeroOneSessionData sessionData) {
		this.sessionData = sessionData;
	}

	public int getDartsOutRound() {
		return dartsOutRound;
	}

	public void setDartsOutRound(final int dartsOutRound) {
		this.dartsOutRound = dartsOutRound;
	}

	public double getStats() {
		return stats;
	}

	public void setStats(final double stats) {
		this.stats = stats;
	}

	public int getRestPoint() {
		return restPoint;
	}

	public void setRestPoint(final int restPoint) {
		this.restPoint = restPoint;
	}

	public void setApplication(final ZeroOneApplication application) {
		this.application = application;
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

	public void setViewFinshButton(final boolean viewFinshButton) {
		this.viewFinshButton = viewFinshButton;
	}

}
