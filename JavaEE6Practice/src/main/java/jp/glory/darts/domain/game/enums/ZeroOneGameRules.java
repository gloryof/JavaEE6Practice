package jp.glory.darts.domain.game.enums;

/**
 * ゼロワンのルールを定義する
 * 
 * @author Junki Yamada
 * 
 */
public enum ZeroOneGameRules {

	GAME_301(1, 301, 10),
	GAME_501(2, 501, 15),
	GAME_701(3, 701, 15),
	GAME_901(4, 901, 20),
	GAME_1101(5, 1101, 20), ;

	/** タイプコード */
	private final int typeCode;

	/** ポイント */
	private final int point;

	/** ラウンド数 */
	private final int round;

	/**
	 * コンストラクタ
	 * 
	 * @param typeCode タイプコード
	 * @param point ポイント
	 * @param round ラウンド数
	 */
	private ZeroOneGameRules(final int typeCode, final int point, final int round) {

		this.typeCode = typeCode;
		this.point = point;
		this.round = round;
	}

	/**
	 * @return typeCode
	 */
	public int getTypeCode() {
		return typeCode;
	}

	/**
	 * @return point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @return round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * タイプコードからルールを取得する
	 * 
	 * @param typeCode タイプコード
	 * @return
	 */
	public static ZeroOneGameRules convertGameRule(final int typeCode) {

		for (final ZeroOneGameRules rule : ZeroOneGameRules.values()) {

			if (rule.getTypeCode() == typeCode) {

				return rule;
			}
		}

		return null;
	}
}
