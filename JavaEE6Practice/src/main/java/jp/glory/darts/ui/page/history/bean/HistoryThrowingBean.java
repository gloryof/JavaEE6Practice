package jp.glory.darts.ui.page.history.bean;

import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;

/**
 * スローイング履歴Bean
 * 
 * @author Junki Yamada
 * 
 */
public class HistoryThrowingBean {

	/** インデックス */
	private int index = 0;

	/** ナンバー */
	private ThrowingNumber number = null;

	/** 倍率 */
	private ThrowingCount count = null;

	/**
	 * ポイントを返却する
	 * 
	 * @return ポイント
	 * 
	 */
	public int getPoint() {

		return number.getNumber() * count.getCount();
	}

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
	 * @return number
	 */
	public ThrowingNumber getNumber() {
		return number;
	}

	/**
	 * @param number セットする number
	 */
	public void setNumber(final ThrowingNumber number) {
		this.number = number;
	}

	/**
	 * @return count
	 */
	public ThrowingCount getCount() {
		return count;
	}

	/**
	 * @param count セットする count
	 */
	public void setCount(final ThrowingCount count) {
		this.count = count;
	}
}
