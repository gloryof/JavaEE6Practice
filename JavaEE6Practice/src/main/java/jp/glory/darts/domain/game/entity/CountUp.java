package jp.glory.darts.domain.game.entity;

import java.math.BigDecimal;
import java.util.Date;

import jp.glory.darts.domain.common.Entity;
import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.value.Throwing;

/**
 * カウントアップ
 * 
 * @author Junki Yamada
 * 
 */
public class CountUp extends AbstractDartsGame implements Entity<CountUp> {

	/** ポイント */
	private int point = 0;

	/**
	 * コンストラクタ
	 * 
	 * @param gameId ゲームID
	 * @param userId プレイヤーユーザID
	 */
	public CountUp(final long gameId, final String userId) {

		super(gameId, userId, new Round[GameConst.COUNT_UP_ROUND_COUNT]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void calculatePoint() {

		int total = 0;
		for (final Round round : super.getRounds()) {

			total += round.getPoint();
		}

		point = total;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSame(final CountUp other) {

		if (other == null) {

			return false;
		}

		if (super.getGameId() != other.getGameId()) {

			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CountUp copyEntity() {

		final CountUp entity = new CountUp(super.getGameId(), super.getUserId());
		entity.setPlayDate((Date) super.getPlayDate().clone());

		int index = 0;
		for (final Round round : super.getRounds()) {

			final Throwing[] throwings = round.getThrowings();
			final ThrowingNumber[] numbers = new ThrowingNumber[throwings.length];
			final ThrowingCount[] counts = new ThrowingCount[throwings.length];

			int throwingIndex = 0;
			for (final Throwing throwing : throwings) {

				numbers[throwingIndex] = throwing.getNumber();
				counts[throwingIndex] = throwing.getCount();

				throwingIndex++;
			}

			entity.setRoundPoint(index, numbers, counts);
			index++;
		}

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {

		initRound();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getStats() {

		final int total = getPoint();

		final BigDecimal totalDecimal = new BigDecimal(total);
		final BigDecimal divideNumber = new BigDecimal(GameConst.COUNT_UP_ROUND_COUNT);
		final BigDecimal statsFull = totalDecimal.divide(divideNumber, GameConst.POINT_ROUNDING_PLACE,
				BigDecimal.ROUND_HALF_DOWN);

		return statsFull.setScale(GameConst.POINT_ROUNDING_PLACE, BigDecimal.ROUND_DOWN).doubleValue();
	}

	public void setPoint(final int point) {
		this.point = point;
	}

	public int getPoint() {

		return point;
	}
}
