package jp.glory.darts.domain.game.repository;

import java.util.List;

import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;

/**
 * ゼロワンリポジトリインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface ZeroOneRepository {

	/**
	 * ゲームの結果を保存する
	 * 
	 * @param entity ゼロワンエンティティ
	 */
	void save(final ZeroOne entity);

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
	 * @param rule ゲームルール
	 * @return ゼロワンエンティティリスト
	 */
	List<ZeroOne> findNoThrowingByUserId(final String userId, final ZeroOneGameRules rule);
}
