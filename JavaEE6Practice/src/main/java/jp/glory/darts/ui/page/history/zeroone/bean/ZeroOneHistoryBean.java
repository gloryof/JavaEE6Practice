package jp.glory.darts.ui.page.history.zeroone.bean;

import java.util.Date;

/**
 * カウントアップ履歴Bean
 * 
 * @author Junki Yamada
 * 
 */
public class ZeroOneHistoryBean {

	/** ゲームタイプコード */
	private int gameTypeCode = 0;

	/** ゲームID */
	private long gameId = 0;

	/** 連番 */
	private int seqNo = 0;

	/** プレー日付 */
	private Date playDate = null;

	/** 終了ラウンド インデックス */
	private int endRoundIndex = 0;

	/** スタッツ */
	private double stats = 0;

	/** 残りポイント */
	private int restPoint = 0;

	/**
	 * @return gameTypeCode
	 */
	public int getGameTypeCode() {
		return gameTypeCode;
	}

	/**
	 * @param gameTypeCode セットする gameTypeCode
	 */
	public void setGameTypeCode(final int gameTypeCode) {
		this.gameTypeCode = gameTypeCode;
	}

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
	 * @return endRoundIndex
	 */
	public int getEndRoundIndex() {
		return endRoundIndex;
	}

	/**
	 * @param endRoundIndex セットする endRoundIndex
	 */
	public void setEndRoundIndex(final int endRoundIndex) {
		this.endRoundIndex = endRoundIndex;
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
}
