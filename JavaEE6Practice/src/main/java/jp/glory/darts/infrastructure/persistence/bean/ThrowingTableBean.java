package jp.glory.darts.infrastructure.persistence.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import jp.glory.darts.infrastructure.persistence.key.ThrowingTableKey;

/**
 * スローイングテーブルBean
 * 
 * @author Junki Yamada
 * 
 */
@Entity
@Table(name = "throwing")
@IdClass(value = ThrowingTableKey.class)
@NamedQueries({
	@NamedQuery(name = ThrowingTableBean.QUERY_NAME_FIND_BY_GAME_ID, query = "SELECT t FROM ThrowingTableBean t WHERE t.gameId = :gameId")
})
@Cacheable(value = false)
public class ThrowingTableBean implements Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -1403006210723825138L;

	/** クエリ名接頭語 */
	private static final String QUERY_NAME_HEADER = "Throwing.";

	/** クエリ名：ゲームID検索 */
	public static final String QUERY_NAME_FIND_BY_GAME_ID = ThrowingTableBean.QUERY_NAME_HEADER + "findByGameId";

	/** ゲームID */
	@Id
	@Column(name = "game_id")
	private long gameId = 0;

	/** ラウンドインデックス */
	@Id
	@Column(name = "round_index")
	private int roundIndex = 0;

	/** スローイングインデックス */
	@Id
	@Column(name = "throwing_index")
	private int throwingIndex = 0;

	/** ナンバー */
	private int number = 0;

	/** 倍率 */
	private int count = 0;

	public long getGameId() {
		return gameId;
	}

	public void setGameId(final long gameId) {
		this.gameId = gameId;
	}

	public long getRoundIndex() {
		return roundIndex;
	}

	public void setRoundIndex(final int round) {
		this.roundIndex = round;
	}

	public int getThrowingIndex() {
		return throwingIndex;
	}

	public void setThrowingIndex(final int index) {
		this.throwingIndex = index;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}

	public int getCount() {
		return count;
	}

	public void setCount(final int count) {
		this.count = count;
	}
}
