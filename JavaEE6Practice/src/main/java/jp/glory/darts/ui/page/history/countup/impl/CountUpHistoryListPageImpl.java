package jp.glory.darts.ui.page.history.countup.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.application.game.CountUpApplication;
import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.UserSessionData;
import jp.glory.darts.ui.page.history.countup.CountUpHistoryListPage;
import jp.glory.darts.ui.page.history.countup.bean.CountUpHistoryBean;
import jp.glory.darts.ui.page.history.util.HistoryUtil;

/**
 * カウントアップ履歴リストページクラス
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "countUpHistoryListPage")
@SessionScoped
public class CountUpHistoryListPageImpl implements CountUpHistoryListPage, Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -30991725710232268L;

	/** カウントアップアプリケーション */
	@Inject
	private CountUpApplication application = null;

	/** ユーティリティクラス */
	@Inject
	private HistoryUtil util = null;

	/** ユーザセッションデータ */
	@Inject
	private UserSessionData sessionData = null;

	/** 履歴Beanリスト */
	private List<CountUpHistoryBean> historyBeanList = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init() {

		final List<CountUp> resultList = application.findNoThrowingByUserId(sessionData.getUserId());

		final List<CountUpHistoryBean> viewList = new ArrayList<>();
		int seqNo = 0;
		for (final CountUp entity : resultList) {

			final CountUpHistoryBean listBean = util.convertViewCountUpBean(entity);
			listBean.setSeqNo((seqNo + 1));

			viewList.add(listBean);
			seqNo++;
		}

		historyBeanList = viewList;

		return ForwardKey.HISTORY_COUNTUP_LIST.getValue();
	}

	public void setApplication(final CountUpApplication application) {
		this.application = application;
	}

	public void setUtil(final HistoryUtil util) {
		this.util = util;
	}

	public UserSessionData getSessionData() {
		return sessionData;
	}

	public void setSessionData(final UserSessionData sessionData) {
		this.sessionData = sessionData;
	}

	public List<CountUpHistoryBean> getHistoryBeanList() {
		return historyBeanList;
	}

	public void setHistoryBeanList(final List<CountUpHistoryBean> historyBeanList) {
		this.historyBeanList = historyBeanList;
	}

}
