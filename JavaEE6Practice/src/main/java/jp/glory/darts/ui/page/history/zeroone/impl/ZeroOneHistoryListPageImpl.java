package jp.glory.darts.ui.page.history.zeroone.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.application.game.ZeroOneApplication;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.UserSessionData;
import jp.glory.darts.ui.page.history.util.HistoryUtil;
import jp.glory.darts.ui.page.history.zeroone.ZeroOneHistoryListPage;
import jp.glory.darts.ui.page.history.zeroone.bean.ZeroOneHistoryBean;

/**
 * ゼロワン履歴リストページクラス
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "zeroOneHistoryListPage")
@SessionScoped
public class ZeroOneHistoryListPageImpl implements ZeroOneHistoryListPage, Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -6779168908396808330L;

	/** ゼロワンアプリケーション */
	@Inject
	private ZeroOneApplication application = null;

	/** ユーティリティクラス */
	@Inject
	private HistoryUtil util = null;

	/** ユーザセッションデータ */
	@Inject
	private UserSessionData sessionData = null;

	/** 履歴リスト */
	private List<ZeroOneHistoryBean> historyBeanList = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init(final int gameTypeCode) {

		final List<ZeroOne> entityList = application.findHistoryInfo(sessionData.getUserId(),
				ZeroOneGameRules.convertGameRule(gameTypeCode));

		historyBeanList = new ArrayList<>();

		int index = 0;
		for (final ZeroOne entity : entityList) {

			final ZeroOneHistoryBean hisotryBean = util.convertViewZeroOneBean(entity);
			hisotryBean.setSeqNo((index + 1));

			historyBeanList.add(hisotryBean);

			index++;
		}

		return ForwardKey.HISTORY_ZERO_ONE_LIST.getValue();
	}

	/**
	 * @return application
	 */
	public ZeroOneApplication getApplication() {
		return application;
	}

	/**
	 * @param application セットする application
	 */
	public void setApplication(final ZeroOneApplication application) {
		this.application = application;
	}

	/**
	 * @return util
	 */
	public HistoryUtil getUtil() {
		return util;
	}

	/**
	 * @param util セットする util
	 */
	public void setUtil(final HistoryUtil util) {
		this.util = util;
	}

	/**
	 * @return sessionData
	 */
	public UserSessionData getSessionData() {
		return sessionData;
	}

	/**
	 * @param sessionData セットする sessionData
	 */
	public void setSessionData(final UserSessionData sessionData) {
		this.sessionData = sessionData;
	}

	/**
	 * @return historyBeanList
	 */
	public List<ZeroOneHistoryBean> getHistoryBeanList() {
		return historyBeanList;
	}

	/**
	 * @param historyBeanList セットする historyBeanList
	 */
	public void setHistoryBeanList(final List<ZeroOneHistoryBean> historyBeanList) {
		this.historyBeanList = historyBeanList;
	}

}
