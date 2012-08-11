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
 * ゼロワンテーブルBean
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
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 2396783950565900459L;

	/** クエリ名の接頭語 */
	private static final String QUERY_NAME_HEADER = "ZeroOne.";

	/** クエリ名：ユーザID検索 */
	public static final String QUERY_NAME_FIND_BY_USER_ID = ZeroOneTableBean.QUERY_NAME_HEADER + "findByUserId";

	/** ゲームID */
	@Id
	@Column(name = "game_id")
	private long gameId = 0;

	/** ユーザID */
	@Column(name = "user_id")
	private String userId = null;

	/** ゲームタイプ */
	@Column(name = "game_type")
	private int gameType = 0;

	/** 残り点数 */
	@Column(name = "rest_point")
	private int restPoint = 0;

	/** 終了ラウンド */
	@Column(name = "end_round")
	private int endRound = 0;

	/** スタッツ */
	private double stats = 0;

	/** ゲーム時間 */
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
