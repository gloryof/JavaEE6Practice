package jp.glory.darts.ui.page.history.bean;

import java.util.List;

/**
 * ラウンド履歴Bean
 * 
 * @author Junki Yamada
 * 
 */
public class HistoryRoundBean {

	/** インデックス */
	private int index = 0;

	/** ポイント */
	private int point = 0;

	/** スローイングリスト */
	private List<HistoryThrowingBean> throwiList = null;

	/**
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index セットする index
	 */
	public void setIndex(final int index) {
		this.index = index;
	}

	/**
	 * @return point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point セットする point
	 */
	public void setPoint(final int point) {
		this.point = point;
	}

	/**
	 * @return throwiList
	 */
	public List<HistoryThrowingBean> getThrowiList() {
		return throwiList;
	}

	/**
	 * @param throwiList セットする throwiList
	 */
	public void setThrowiList(final List<HistoryThrowingBean> throwiList) {
		this.throwiList = throwiList;
	}

}
