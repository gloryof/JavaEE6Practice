package jp.glory.darts.infrastructure.persistence.key;

/**
 * �X���[�C���O�e�[�u�������L�[
 * 
 * @author Junki Yamada
 * 
 */
public class ThrowingTableKey {

	/** �Q�[��ID */
	private long gameId = 0;

	/** ���E���h */
	private int roundIndex = 0;

	/** �X���[�C���O�C���f�b�N�X */
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
