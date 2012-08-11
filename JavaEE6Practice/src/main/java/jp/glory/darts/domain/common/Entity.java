package jp.glory.darts.domain.common;

/**
 * エンティティインターフェイス
 * 
 * @author Junki Yamada
 * 
 * @param <T> エンティティクラス
 */
public interface Entity<T extends Entity<T>> {

	/**
	 * エンティティが同一のものかをチェックする
	 * 
	 * @param other
	 * @return 同一の場合：true、同一でない場合：false
	 */
	boolean isSame(T other);

	/***
	 * 同一のエンティティをコピーする
	 * 
	 * @return コピーしたエンティティ
	 */
	T copyEntity();
}
