package jp.glory.darts.domain.game.entity;

import java.util.Date;

import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;

/**
 * ゲームクラス
 * 
 * @author Junki Yamada
 * 
 */
public abstract class AbstractDartsGame {

	/** ゲームID */
	private final long gameId;

	/** プレイヤーユーザID */
	private final String userId;

	/** ラウンドリスト */
	private final Round[] rounds;

	/** ゲームプレー日時 */
	private Date playDate = null;

	/**
	 * コンストラクタ
	 * 
	 * @param gameId ゲームID
	 * @param userId ユーザId
	 * @param rounds ラウンドリスト
	 */
	protected AbstractDartsGame(final long gameId, final String userId, final Round[] rounds) {

		this.gameId = gameId;
		this.userId = userId;
		this.rounds = rounds;
		initRound();
	}

	/**
	 * ラウンドの得点をセットする<br/>
	 * 得点を設定したラウンドの終了フラグをオンにする。
	 * 
	 * @param roundIndex ラウンドのインデックス
	 * @param number ナンバーの配列
	 * @param count 倍率の配列
	 */
	public final void setRoundPoint(final int roundIndex, final ThrowingNumber[] number, final ThrowingCount[] count) {

		final Round round = rounds[roundIndex];
		for (int i = 0; i < GameConst.THROW_COUNT; i++) {

			round.setThrowingPoint(i, number[i], count[i]);
			round.setEndThrowing(true);
		}
	}

	/**
	 * ラウンドを取得する<br/>
	 * 
	 * @param roundIndex ラウンドインデックス
	 * @return ラウンドエンティティ
	 */
	public final Round getRound(final int roundIndex) {

		return rounds[roundIndex].copyEntity();
	}

	/**
	 * スタッツを取得する
	 * 
	 * @return スタッツ
	 */
	abstract public double getStats();

	/**
	 * ゲームの情報リセットする
	 */
	abstract public void reset();

	/**
	 * ゲームのポイントを計算する
	 */
	abstract public void calculatePoint();

	/**
	 * ラウンドの情報を初期化する
	 */
	protected final void initRound() {

		for (int i = 0; i < rounds.length; i++) {

			rounds[i] = new Round(i);
		}
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setPlayDate(final Date playDate) {
		this.playDate = playDate;
	}

	public long getGameId() {
		return gameId;
	}

	public String getUserId() {
		return userId;
	}

	public Round[] getRounds() {

		final Round[] returnRounds = new Round[rounds.length];

		int index = 0;
		for (final Round round : rounds) {

			returnRounds[index] = round.copyEntity();

			index++;
		}

		return returnRounds;
	}
}
