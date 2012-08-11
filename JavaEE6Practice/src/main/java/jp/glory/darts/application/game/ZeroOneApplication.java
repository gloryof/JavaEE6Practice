package jp.glory.darts.application.game;

import java.util.List;

import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;

/**
 * ゼロワンアプリケーションインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface ZeroOneApplication {

	/**
	 * 結果を保存する
	 * 
	 * @param entity ゼロワンエンティティ
	 */
	void entryResult(final ZeroOne entity);

	/**
	 * ゲームIDからゼロワンエンティティを取得する
	 * 
	 * @param gameId ゲームID
	 * @return ゼロワンエンティティ
	 */
	ZeroOne findByGameId(final long gameId);

	/**
	 * スローイング情報なしのゼロワンエンティティリストを取得する<br/>
	 * 
	 * @param userId ユーザID
	 * @param rule ルール
	 * @return ゼロワンエンティティリスト
	 */
	List<ZeroOne> findHistoryInfo(final String userId, final ZeroOneGameRules rule);

}
