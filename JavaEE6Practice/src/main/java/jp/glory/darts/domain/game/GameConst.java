package jp.glory.darts.domain.game;

/**
 * ゲームに関する定数
 * 
 * @author Junki Yamada
 * 
 */
public interface GameConst {

	/** スロー数 */
	int THROW_COUNT = 3;

	/** ラウンド数：カウントアップ */
	int COUNT_UP_ROUND_COUNT = 8;

	/** ゲームタイプ：カウントアップ */
	int GAME_TYPE_COUNT_UP = 1;

	/** ポイントの切捨て桁数 */
	int POINT_ROUNDING_PLACE = 2;
}
