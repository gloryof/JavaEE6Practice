package jp.glory.darts.ui.page.history.countup.bean;

import java.util.Date;

/**
 * カウントアップ履歴Bean
 * 
 * @author Junki Yamada
 * 
 */
public class CountUpHistoryBean {

	/** ゲームID */
	private long gameId = 0;

	/** 連番 */
	private int seqNo = 0;

	/** プレー日付 */
	private Date playDate = null;

	/** トータル */
	private int total = 0;

	/** スタッツ */
	private double stats = 0;

	/**
	 * @return gameId
	 */
	public long getGameId() {
		return gameId;
	}

	/**
	 * @param gameId セットする gameId
	 */
	public void setGameId(final long gameId) {
		this.gameId = gameId;
	}

	/**
	 * @return seqNo
	 */
	public int getSeqNo() {
		return seqNo;
	}

	/**
	 * @param seqNo セットする seqNo
	 */
	public void setSeqNo(final int seqNo) {
		this.seqNo = seqNo;
	}

	/**
	 * @return playDate
	 */
	public Date getPlayDate() {
		return playDate;
	}

	/**
	 * @param playDate セットする playDate
	 */
	public void setPlayDate(final Date playDate) {
		this.playDate = playDate;
	}

	/**
	 * @return total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total セットする total
	 */
	public void setTotal(final int total) {
		this.total = total;
	}

	/**
	 * @return stats
	 */
	public double getStats() {
		return stats;
	}

	/**
	 * @param stats セットする stats
	 */
	public void setStats(final double stats) {
		this.stats = stats;
	}
}
