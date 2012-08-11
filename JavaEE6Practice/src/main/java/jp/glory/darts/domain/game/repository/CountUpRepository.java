package jp.glory.darts.domain.game.repository;

import java.util.List;

import jp.glory.darts.domain.game.entity.CountUp;

/**
 * カウントアップリポジトリインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface CountUpRepository {

	/**
	 * ゲームの結果を保存する
	 * 
	 * @param entity カウントアップエンティティ
	 */
	void save(CountUp entity);

	/**
	 * ゲームIDからカウントアップエンティティを取得する
	 * 
	 * @param gameId ゲームID
	 * @return カウントアップエンティティ
	 */
	CountUp findByGameId(final long gameId);

	/**
	 * スローイング情報なしのカウントアップエンティティリストを取得する<br/>
	 * 
	 * @param userId ユーザID
	 * @return カウントアップエンティティリスト
	 */
	List<CountUp> findNoThrowingByUserId(final String userId);
}
