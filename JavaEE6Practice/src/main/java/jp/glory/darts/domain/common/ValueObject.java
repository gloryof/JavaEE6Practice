package jp.glory.darts.domain.common;

/**
 * 値オブジェクトインターフェイス<br>
 * 
 * @author Junki Yamada
 * 
 * @param <T> 値オブジェクト
 */
public interface ValueObject<T extends ValueObject<T>> {

	/**
	 * 同じ値かを判定する。
	 * 
	 * @param other 比較対象値オブジェクト
	 * @return 同一の値の場合：true、異なる値の場合：false
	 */
	boolean isSameValue(T other);

	/***
	 * 同一の値をコピーする
	 * 
	 * @return 値オブジェクト
	 */
	T copyValue();
}
