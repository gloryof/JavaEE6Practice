package jp.glory.darts.ui.page.history.countup.bean;

import java.util.Date;

/**
 * �J�E���g�A�b�v����Bean
 * 
 * @author Junki Yamada
 * 
 */
public class CountUpHistoryBean {

	/** �Q�[��ID */
	private long gameId = 0;

	/** �A�� */
	private int seqNo = 0;

	/** �v���[���t */
	private Date playDate = null;

	/** �g�[�^�� */
	private int total = 0;

	/** �X�^�b�c */
	private double stats = 0;

	/**
	 * @return gameId
	 */
	public long getGameId() {
		return gameId;
	}

	/**
	 * @param gameId �Z�b�g���� gameId
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
	 * @param seqNo �Z�b�g���� seqNo
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
	 * @param playDate �Z�b�g���� playDate
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
	 * @param total �Z�b�g���� total
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
	 * @param stats �Z�b�g���� stats
	 */
	public void setStats(final double stats) {
		this.stats = stats;
	}
}
