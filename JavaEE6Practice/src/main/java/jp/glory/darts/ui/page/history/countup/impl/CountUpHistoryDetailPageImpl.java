package jp.glory.darts.ui.page.history.countup.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.application.game.CountUpApplication;
import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.history.bean.HistoryRoundBean;
import jp.glory.darts.ui.page.history.countup.CountUpHistoryDetailPage;
import jp.glory.darts.ui.page.history.countup.bean.CountUpHistoryBean;
import jp.glory.darts.ui.page.history.util.HistoryUtil;

/**
 * �J�E���g�A�b�v�ڍ׃y�[�W�N���X
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "countUpHistoryDetailPage")
@RequestScoped
public class CountUpHistoryDetailPageImpl implements CountUpHistoryDetailPage {

	/** �J�E���g�A�b�v�A�v���P�[�V���� */
	@Inject
	private CountUpApplication applicatioin = null;

	/** �������[�e�B���e�B�N���X */
	@Inject
	private HistoryUtil util = null;

	/** �J�E���g�A�b�v�f�[�^Bean */
	private CountUpHistoryBean countUpBean = null;

	/** ���E���h���X�g */
	private List<HistoryRoundBean> roundList = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init(final long gameId) {

		final CountUp entity = applicatioin.findByGameId(gameId);

		countUpBean = util.convertViewCountUpBean(entity);

		roundList = new ArrayList<>();
		for (final Round round : entity.getRounds()) {

			roundList.add(util.convertViewRoundBean(round));
		}

		return ForwardKey.HISTORY_COUNTUP_DETAIL.getValue();
	}

	public CountUpHistoryBean getCountUpBean() {
		return countUpBean;
	}

	public void setCountUpBean(final CountUpHistoryBean countUpBean) {
		this.countUpBean = countUpBean;
	}

	public List<HistoryRoundBean> getRoundList() {
		return roundList;
	}

	public void setRoundList(final List<HistoryRoundBean> roundList) {
		this.roundList = roundList;
	}

	public void setApplicatioin(final CountUpApplication applicatioin) {
		this.applicatioin = applicatioin;
	}

	public void setUtil(final HistoryUtil util) {
		this.util = util;
	}

}
