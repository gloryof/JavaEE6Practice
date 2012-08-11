package jp.glory.darts.infrastructure.persistence.key;

/**
 * スローイングテーブル複合キー
 * 
 * @author Junki Yamada
 * 
 */
public class ThrowingTableKey {

	/** ゲームID */
	private long gameId = 0;

	/** ラウンド */
	private int roundIndex = 0;

	/** スローイングインデックス */
	private int throwingIndex = 0;

	public long getGameId() {
		return gameId;
	}

	public void setGameId(final long gameId) {
		this.gameId = gameId;
	}

	public int getRoundIndex() {
		return roundIndex;
	}

	public void setRoundIndex(final int roundIndex) {
		this.roundIndex = roundIndex;
	}

	public int getThrowingIndex() {
		return throwingIndex;
	}

	public void setThrowingIndex(final int throwingIndex) {
		this.throwingIndex = throwingIndex;
	}

}
