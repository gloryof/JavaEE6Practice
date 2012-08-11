package jp.glory.darts.application.game;

import java.util.List;

import jp.glory.darts.domain.game.entity.CountUp;

/**
 * カウントアップアプリケーションインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface CountUpApplication {

	/**
	 * 結果を保存する
	 * 
	 * @param entity カウントアップエンティティ
	 */
	void entryResult(final CountUp entity);

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
