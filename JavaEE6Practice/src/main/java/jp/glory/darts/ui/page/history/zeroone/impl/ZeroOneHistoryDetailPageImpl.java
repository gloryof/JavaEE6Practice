package jp.glory.darts.ui.page.history.zeroone.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.application.game.ZeroOneApplication;
import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.history.bean.HistoryRoundBean;
import jp.glory.darts.ui.page.history.util.HistoryUtil;
import jp.glory.darts.ui.page.history.zeroone.ZeroOneHistoryDetailPage;
import jp.glory.darts.ui.page.history.zeroone.bean.ZeroOneHistoryBean;

/**
 * ゼロワン履歴詳細ページクラス
 * 
 * @author Junki Yamada
 */
@Named(value = "zeroOneHistoryDetailPage")
@SessionScoped
public class ZeroOneHistoryDetailPageImpl implements ZeroOneHistoryDetailPage, Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -3649672339727870609L;

	/** ゼロワンアプリケーション */
	@Inject
	private ZeroOneApplication application = null;

	/** 履歴ユーティリティクラス */
	@Inject
	private HistoryUtil util = null;

	/** ゼロワンBean */
	private ZeroOneHistoryBean zeroOneBean = null;

	/** ラウンドリスト */
	private List<HistoryRoundBean> roundList = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init(final long gameId) {

		final ZeroOne entity = application.findByGameId(gameId);

		zeroOneBean = util.convertViewZeroOneBean(entity);

		roundList = new ArrayList<>();
		for (final Round roundEntity : entity.getRounds()) {

			roundList.add(util.convertViewRoundBean(roundEntity));
		}

		return ForwardKey.HISTORY_ZERO_ONE_DETAIL.getValue();
	}

	/**
	 * @return zeroOneBean
	 */
	public ZeroOneHistoryBean getZeroOneBean() {
		return zeroOneBean;
	}

	/**
	 * @param zeroOneBean セットする zeroOneBean
	 */
	public void setZeroOneBean(final ZeroOneHistoryBean zeroOneBean) {
		this.zeroOneBean = zeroOneBean;
	}

	/**
	 * @return roundList
	 */
	public List<HistoryRoundBean> getRoundList() {
		return roundList;
	}

	/**
	 * @param roundList セットする roundList
	 */
	public void setRoundList(final List<HistoryRoundBean> roundList) {
		this.roundList = roundList;
	}

	/**
	 * @param application セットする application
	 */
	public void setApplication(final ZeroOneApplication application) {
		this.application = application;
	}

	/**
	 * @param util セットする util
	 */
	public void setUtil(final HistoryUtil util) {
		this.util = util;
	}

}
