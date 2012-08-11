package jp.glory.darts.domain.game.enums;

/**
 * スローイングの倍率
 * 
 * @author Junki Yamada
 * 
 */
public enum ThrowingCount {

	OUT(0, "-"),
	SINGLE(1, "S"),
	DOBULE(2, "D"),
	TRIPLE(3, "T");

	/** 倍率 */
	private final int count;

	/** ラベル */
	private final String label;

	/**
	 * コンストラクタ
	 * 
	 * @param count 倍率
	 * @param label ラベル
	 */
	private ThrowingCount(final int count, final String label) {

		this.count = count;
		this.label = label;
	}

	public int getCount() {
		return count;
	}

	public String getLabel() {
		return label;
	}

	/**
	 * 数値から倍率に変換する
	 * 
	 * @param countNumber 数値
	 * @return 倍率
	 */
	public static ThrowingCount convertThrowingCount(final int countNumber) {

		for (final ThrowingCount throwing : ThrowingCount.values()) {

			if (throwing.getCount() == countNumber) {

				return throwing;
			}
		}

		return null;
	}
}
