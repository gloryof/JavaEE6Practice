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
 * カウントアップテーブルBean
 * 
 * @author Junki Yamada
 * 
 */
@Entity
@Table(name = "countup")
@NamedQueries({
	@NamedQuery(name = CountUpTableBean.QUERY_NAME_FIND_BY_USER_ID, query = "SELECT c FROM CountUpTableBean c WHERE c.userId = :userId ORDER BY c.gameTime DESC")
})
@Cacheable(value = false)
public class CountUpTableBean implements Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 7076643506106529410L;

	/** クエリ名接頭語 */
	private static final String QUERY_NAME_HEADER = "Countup.";

	/** クエリ名：ユーザID検索 */
	public static final String QUERY_NAME_FIND_BY_USER_ID = CountUpTableBean.QUERY_NAME_HEADER + "findByUserId";

	/** ゲームID */
	@Id
	@Column(name = "game_id")
	private long gameId = 0;

	/** ユーザID */
	@Column(name = "user_id")
	private String userId = null;

	/** トータル */
	private int total = 0;

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

	public int getTotal() {
		return total;
	}

	public void setTotal(final int total) {
		this.total = total;
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
