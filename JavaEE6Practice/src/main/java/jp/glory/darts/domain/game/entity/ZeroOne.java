package jp.glory.darts.domain.game.entity;

import java.math.BigDecimal;

import jp.glory.darts.domain.common.Entity;
import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.domain.game.value.Throwing;

/**
 * �[�������G���e�B�e�B
 * 
 * @author Junki Yamada
 * 
 */
public class ZeroOne extends AbstractDartsGame implements Entity<ZeroOne> {

	/** �Q�[�����[�� */
	private final ZeroOneGameRules rule;

	/** �c��|�C���g */
	private int restPoint = 0;

	/** �I�����E���h �C���f�b�N�X */
	private int endRoundIndex = 0;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param gameId �Q�[��ID
	 * @param userId ���[�UID
	 * @param rule ���[��
	 */
	public ZeroOne(final long gameId, final String userId, final ZeroOneGameRules rule) {

		super(gameId, userId, new Round[rule.getRound()]);
		this.rule = rule;

		this.restPoint = rule.getPoint();
	}

	/**
	 * �Q�[�����I���������𔻒f����B
	 * 
	 * @return �I�������ꍇ�Ftrue�A�I�����Ă��Ȃ��ꍇ�Ffalse
	 */
	public boolean isEndGame() {

		if (restPoint == 0) {

			return true;
		}

		for (final Round round : getRounds()) {

			if (!round.isEndThrowing()) {

				return false;
			}
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSame(final ZeroOne other) {

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
	public ZeroOne copyEntity() {

		final ZeroOne entity = new ZeroOne(getGameId(), getUserId(), rule);
		entity.setPlayDate(super.getPlayDate());

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
	public void calculatePoint() {

		int tempRest = rule.getPoint();

		for (final Round round : this.getRounds()) {

			final int roundPoint = round.getPoint();

			if (roundPoint <= tempRest) {

				tempRest -= roundPoint;
			}
		}

		restPoint = tempRest;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getStats() {

		final int totalPoint = rule.getPoint() - restPoint;
		final BigDecimal totalDecimal = new BigDecimal(totalPoint);

		final BigDecimal divideNumber;
		if (0 < endRoundIndex) {

			divideNumber = new BigDecimal((endRoundIndex + 1));
		} else {

			divideNumber = new BigDecimal(getLastThrowingRound());
		}

		final BigDecimal statsFull = totalDecimal.divide(divideNumber, GameConst.POINT_ROUNDING_PLACE,
				BigDecimal.ROUND_HALF_DOWN);

		return statsFull.setScale(GameConst.POINT_ROUNDING_PLACE, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * �Ō�ɓ��������E���h��ԋp����
	 * 
	 * @return ���E���h
	 */
	public int getLastThrowingRound() {

		int roundValue = 0;

		for (final Round round : getRounds()) {

			if (!round.isEndThrowing()) {

				break;
			}

			roundValue++;
		}

		return roundValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {

		initRound();
		calculatePoint();
	}

	public int getRestPoint() {
		return restPoint;
	}

	public void setRestPoint(final int restPoint) {
		this.restPoint = restPoint;
	}

	public int getEndRoundIndex() {
		return endRoundIndex;
	}

	public void setEndRoundIndex(final int endRoundIndex) {
		this.endRoundIndex = endRoundIndex;
	}

	public ZeroOneGameRules getRule() {
		return rule;
	}
}
