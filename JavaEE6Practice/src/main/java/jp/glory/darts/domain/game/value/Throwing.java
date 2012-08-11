package jp.glory.darts.domain.game.value;

import jp.glory.darts.domain.common.ValueObject;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;

/**
 * スローイング
 * 
 * @author Junki Yamada
 */
public class Throwing implements ValueObject<Throwing> {

	/** ナンバー */
	private final ThrowingNumber number;

	/** 倍率 */
	private final ThrowingCount count;

	/**
	 * コンストラクタ
	 * 
	 * @param number ナンバー
	 * @param count 倍率
	 */
	public Throwing(final ThrowingNumber number, final ThrowingCount count) {

		if (number == null) {

			throw new IllegalArgumentException("Throwing entity is required number");
		}

		if (count == null) {

			throw new IllegalArgumentException("Throwing entity is required count");
		}

		this.number = number;
		this.count = count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSameValue(final Throwing other) {

		if (other == null) {

			return false;
		}

		if (!number.equals(other.getNumber())) {

			return false;
		}

		if (!count.equals(other.getCount())) {

			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Throwing copyValue() {

		return new Throwing(number, count);
	}

	/**
	 * スローイングのポイントを取得する
	 * 
	 * @return ポイント
	 */
	public int getPoint() {

		if (number.equals(ThrowingNumber.BULL)) {

			return ThrowingNumber.BULL.getNumber() * ThrowingCount.DOBULE.getCount();
		}

		return number.getNumber() * count.getCount();
	}

	public ThrowingNumber getNumber() {
		return number;
	}

	public ThrowingCount getCount() {
		return count;
	}

}
