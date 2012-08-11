package jp.glory.darts.test.domain.game;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;

import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.value.Throwing;

import org.junit.Test;

public class CountUpTest {

	@Test
	public void testInitialize() {

		final int gameId = 1;
		final String userId = "TEST_00001";
		final Date systemDate = Calendar.getInstance().getTime();
		final CountUp entity = new CountUp(gameId, userId);
		entity.setPlayDate(systemDate);

		assertThat(Long.valueOf(entity.getGameId()), is(Long.valueOf(gameId)));
		assertThat(entity.getUserId(), is(userId));
		assertThat(entity.getPlayDate(), is(systemDate));

		final Round[] rounds = entity.getRounds();
		assertThat(rounds, is(not(nullValue())));
		assertThat(rounds.length, is(GameConst.COUNT_UP_ROUND_COUNT));

		int roundIndex = 0;
		for (final Round round : rounds) {

			assertThat(round, is(not(nullValue())));
			assertThat(round.getRoundIndex(), is(roundIndex));

			roundIndex++;
		}
	}

	@Test
	public void testIsSame() {

		final int gameId = 1;
		final String userId = "TEST_00001";

		final CountUp entity = new CountUp(gameId, userId);
		final CountUp compareEntity = new CountUp(gameId, userId);

		assertThat(entity.isSame(compareEntity), is(true));
	}

	@Test
	public void testIsSameDifferentValue() {

		final int gameId = 1;
		final String userId = "TEST_00001";

		final CountUp entity = new CountUp(gameId, userId);
		final CountUp compareEntity = new CountUp(2, userId);

		assertThat(entity.isSame(compareEntity), is(false));
	}

	@Test
	public void testIsSameNull() {

		final int gameId = 1;
		final String userId = "TEST_00001";

		final CountUp entity = new CountUp(gameId, userId);

		assertThat(entity.isSame(null), is(false));
	}

	@Test
	public void testCopyEntity() {

		final ThrowingNumber[] numbers = new ThrowingNumber[] {
				ThrowingNumber.ONE, ThrowingNumber.TWO, ThrowingNumber.THREE
		};
		final ThrowingCount[] counts = new ThrowingCount[] {
				ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
		};

		final int gameId = 1;
		final String userId = "TEST_00001";
		final Date systemDate = Calendar.getInstance().getTime();
		final CountUp entity = new CountUp(gameId, userId);
		entity.setPlayDate(systemDate);

		for (int i = 0; i < GameConst.COUNT_UP_ROUND_COUNT; i++) {

			entity.setRoundPoint(i, numbers, counts);
		}

		final CountUp copyEntity = entity.copyEntity();
		assertThat(copyEntity, is(not(nullValue())));
		assertThat((entity != copyEntity), is(true));
		assertThat(entity.isSame(copyEntity), is(true));
		assertThat(copyEntity.getUserId(), is(entity.getUserId()));
		assertThat(copyEntity.getPlayDate(), is(entity.getPlayDate()));

		for (int i = 0; i < GameConst.COUNT_UP_ROUND_COUNT; i++) {

			final Round baseRound = entity.getRound(i);
			final Round copyRound = copyEntity.getRound(i);

			assertThat(baseRound.isSame(copyRound), is(true));

			for (int j = 0; j < GameConst.THROW_COUNT; j++) {

				final Throwing baseThrowing = baseRound.getThrowing(j);
				final Throwing copyThrowing = copyRound.getThrowing(j);
				assertThat(baseThrowing.isSameValue(copyThrowing), is(true));
			}
		}
	}

	@Test
	public void testSetRoundPoint() {

		final ThrowingNumber[] numbers = new ThrowingNumber[] {
				ThrowingNumber.OUT_BORD, ThrowingNumber.TWENTY, ThrowingNumber.BULL
		};
		final ThrowingCount[] counts = new ThrowingCount[] {
				ThrowingCount.OUT, ThrowingCount.DOBULE, ThrowingCount.SINGLE
		};

		final int gameId = 1;
		final String userId = "TEST_00001";
		final int round = GameConst.COUNT_UP_ROUND_COUNT - 1;
		final CountUp entity = new CountUp(gameId, userId);

		entity.setRoundPoint(round, numbers, counts);

		final Round roundEntity = entity.getRound(round);

		assertThat(roundEntity, is(not(nullValue())));
		assertThat(roundEntity.isEndThrowing(), is(true));

		for (int i = 0; i < GameConst.THROW_COUNT; i++) {

			final Throwing throwing = roundEntity.getThrowing(i);

			assertThat(throwing.getNumber(), is(numbers[i]));
			assertThat(throwing.getCount(), is(counts[i]));
		}

		assertThat(roundEntity.getPoint(), is(90));
	}

	@Test
	public void testCalculatePoint() {

		final ThrowingNumber[] numbers = new ThrowingNumber[] {
				ThrowingNumber.ONE, ThrowingNumber.TWO, ThrowingNumber.THREE
		};
		final ThrowingCount[] counts = new ThrowingCount[] {
				ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
		};

		final int gameId = 1;
		final String userId = "TEST_00001";
		final CountUp entity = new CountUp(gameId, userId);

		for (int i = 0; i < GameConst.COUNT_UP_ROUND_COUNT; i++) {

			entity.setRoundPoint(i, numbers, counts);
		}
		entity.calculatePoint();

		assertThat(entity.getPoint(), is(112));
	}

	@Test
	public void testReset() {

		final ThrowingNumber[] numbers = new ThrowingNumber[] {
				ThrowingNumber.ONE, ThrowingNumber.TWO, ThrowingNumber.THREE
		};
		final ThrowingCount[] counts = new ThrowingCount[] {
				ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
		};

		final int gameId = 1;
		final String userId = "TEST_00001";
		final CountUp entity = new CountUp(gameId, userId);

		for (int i = 0; i < GameConst.COUNT_UP_ROUND_COUNT; i++) {

			entity.setRoundPoint(i, numbers, counts);
		}

		entity.reset();

		for (final Round round : entity.getRounds()) {

			assertThat(round.isEndThrowing(), is(false));

			for (final Throwing throwing : round.getThrowings()) {

				assertThat(throwing.getNumber(), is(ThrowingNumber.OUT_BORD));
				assertThat(throwing.getCount(), is(ThrowingCount.OUT));
			}
		}
	}

	@Test
	public void testGetStats() {

		final ThrowingNumber[][] number = {
				{
						ThrowingNumber.ONE, ThrowingNumber.TWO, ThrowingNumber.THREE
				}, {
						ThrowingNumber.FOUR, ThrowingNumber.FIVE, ThrowingNumber.SIX
				}, {
						ThrowingNumber.SEVEN, ThrowingNumber.EIGHT, ThrowingNumber.NINE
				}, {
						ThrowingNumber.TEN, ThrowingNumber.ELEVEN, ThrowingNumber.TWELVE
				}, {
						ThrowingNumber.THIRTEEN, ThrowingNumber.FOURTEEN, ThrowingNumber.FIFTEEN
				}, {
						ThrowingNumber.SIXTEEN, ThrowingNumber.SEVENTEEN, ThrowingNumber.EIGHTEEN
				}, {
						ThrowingNumber.NINETEEN, ThrowingNumber.TWENTY, ThrowingNumber.BULL
				}, {
						ThrowingNumber.OUT_BORD, ThrowingNumber.BULL, ThrowingNumber.ONE
				}
		};
		final ThrowingCount[][] count = {
				{
						ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
				}, {
						ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
				}, {
						ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
				}, {
						ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
				}, {
						ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
				}, {
						ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
				}, {
						ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.SINGLE
				}, {
						ThrowingCount.OUT, ThrowingCount.DOBULE, ThrowingCount.DOBULE
				}
		};

		final CountUp entity = new CountUp(0, "TEST_USER_0001");

		for (int i = 0; i < GameConst.COUNT_UP_ROUND_COUNT; i++) {

			entity.setRoundPoint(i, number[i], count[i]);
		}
		entity.calculatePoint();

		assertThat(Double.valueOf(entity.getStats()), is(Double.valueOf(64.37)));
	}
}
