package jp.glory.darts.ui.page.history.zeroone.bean;

import java.util.Date;

/**
 * �J�E���g�A�b�v����Bean
 * 
 * @author Junki Yamada
 * 
 */
public class ZeroOneHistoryBean {

	/** �Q�[���^�C�v�R�[�h */
	private int gameTypeCode = 0;

	/** �Q�[��ID */
	private long gameId = 0;

	/** �A�� */
	private int seqNo = 0;

	/** �v���[���t */
	private Date playDate = null;

	/** �I�����E���h �C���f�b�N�X */
	private int endRoundIndex = 0;

	/** �X�^�b�c */
	private double stats = 0;

	/** �c��|�C���g */
	private int restPoint = 0;

	/**
	 * @return gameTypeCode
	 */
	public int getGameTypeCode() {
		return gameTypeCode;
	}

	/**
	 * @param gameTypeCode �Z�b�g���� gameTypeCode
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
	 * @return endRoundIndex
	 */
	public int getEndRoundIndex() {
		return endRoundIndex;
	}

	/**
	 * @param endRoundIndex �Z�b�g���� endRoundIndex
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
	 * @param stats �Z�b�g���� stats
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
	 * @param restPoint �Z�b�g���� restPoint
	 */
	public void setRestPoint(final int restPoint) {
		this.restPoint = restPoint;
	}
}
