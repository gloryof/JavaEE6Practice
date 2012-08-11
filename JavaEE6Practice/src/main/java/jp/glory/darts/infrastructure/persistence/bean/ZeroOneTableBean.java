package jp.glory.darts.infrastructure.persistence.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * �[�������e�[�u��Bean
 * 
 * @author Junki Yamada
 * 
 */
@Entity
@Table(name = "zero_one")
@NamedQueries({
	@NamedQuery(name = ZeroOneTableBean.QUERY_NAME_FIND_BY_USER_ID, query = "SELECT z FROM ZeroOneTableBean z WHERE z.userId = :userId AND z.gameType = :gameType ORDER BY z.gameTime DESC")
})
@Cacheable(value = false)
public class ZeroOneTableBean implements Serializable {

	/**
	 * �V���A���o�[�W����UID
	 */
	private static final long serialVersionUID = 2396783950565900459L;

	/** �N�G�����̐ړ��� */
	private static final String QUERY_NAME_HEADER = "ZeroOne.";

	/** �N�G�����F���[�UID���� */
	public static final String QUERY_NAME_FIND_BY_USER_ID = ZeroOneTableBean.QUERY_NAME_HEADER + "findByUserId";

	/** �Q�[��ID */
	@Id
	@Column(name = "game_id")
	private long gameId = 0;

	/** ���[�UID */
	@Column(name = "user_id")
	private String userId = null;

	/** �Q�[���^�C�v */
	@Column(name = "game_type")
	private int gameType = 0;

	/** �c��_�� */
	@Column(name = "rest_point")
	private int restPoint = 0;

	/** �I�����E���h */
	@Column(name = "end_round")
	private int endRound = 0;

	/** �X�^�b�c */
	private double stats = 0;

	/** �Q�[������ */
	@Column(name = "game_time", insertable = false)
	private Timestamp gameTime = null;

	public long getGameId() {
		return gameId;
	}

	public void setGameId(final long gameId) {
		this.gameId = gameId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public int getGameType() {
		return gameType;
	}

	public void setGameType(final int gameType) {
		this.gameType = gameType;
	}

	public int getRestPoint() {
		return restPoint;
	}

	public void setRestPoint(final int restPoint) {
		this.restPoint = restPoint;
	}

	public int getEndRound() {
		return endRound;
	}

	public void setEndRound(final int endRound) {
		this.endRound = endRound;
	}

	public double getStats() {
		return stats;
	}

	public void setStats(final double stats) {
		this.stats = stats;
	}

	public Timestamp getGameTime() {
		return gameTime;
	}

	public void setGameTime(final Timestamp gameTime) {
		this.gameTime = gameTime;
	}
}
