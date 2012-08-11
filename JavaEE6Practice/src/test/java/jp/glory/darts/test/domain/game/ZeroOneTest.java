package jp.glory.darts.test.domain.game;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;

import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.domain.game.value.Throwing;

import org.junit.Test;

public class ZeroOneTest {

	@Test
	public void testInitialize() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_901;

		final ZeroOne entity = new ZeroOne(gameId, userId, rule);

		assertThat(entity.getGameId(), is(gameId));
		assertThat(entity.getUserId(), is(userId));

		final Round[] roundArray = entity.getRounds();
		assertThat(roundArray, is(not(nullValue())));
		assertThat(roundArray.length, is(rule.getRound()));

		assertThat(entity.getRule(), is(rule));
		assertThat(entity.getEndRoundIndex(), is(0));
		assertThat(entity.getRestPoint(), is(rule.getPoint()));

		assertThat(entity.getPlayDate(), is(nullValue()));
	}

	@Test
	public void testIsSame() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_901;

		final ZeroOne entity = new ZeroOne(gameId, userId, rule);
		final ZeroOne compareEntity = new ZeroOne(gameId, userId, rule);

		assertThat(entity.isSame(compareEntity), is(true));
	}

	@Test
	public void testIsSameDifferent() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_901;

		final ZeroOne entity = new ZeroOne(gameId, userId, rule);
		final ZeroOne compareEntity = new ZeroOne(2, userId, rule);

		assertThat(entity.isSame(compareEntity), is(false));
	}

	@Test
	public void testIsSameNull() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_901;

		final ZeroOne entity = new ZeroOne(gameId, userId, rule);

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
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_901;

		final ZeroOne entity = new ZeroOne(gameId, userId, rule);
		entity.setPlayDate(systemDate);

		for (int i = 0; i < GameConst.COUNT_UP_ROUND_COUNT; i++) {

			entity.setRoundPoint(i, numbers, counts);
		}

		final ZeroOne copyEntity = entity.copyEntity();

		assertThat(copyEntity, is(not(nullValue())));
		assertThat((entity != copyEntity), is(true));
		assertThat(entity.isSame(copyEntity), is(true));
		assertThat(copyEntity.getUserId(), is(entity.getUserId()));
		assertThat(copyEntity.getPlayDate(), is(entity.getPlayDate()));

		for (int i = 0; i < rule.getRound(); i++) {

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
	public void testCalculatePoint() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;

		final ZeroOne entity = new ZeroOne(gameId, userId, rule);

		final ThrowingNumber[] numbers = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.TWENTY
		};
		final ThrowingCount[] counts = new ThrowingCount[] {
				ThrowingCount.TRIPLE, ThrowingCount.TRIPLE, ThrowingCount.TRIPLE
		};

		entity.setRoundPoint(0, numbers, counts);
		entity.calculatePoint();

		assertThat(entity.getRestPoint(), is(121));
	}

	@Test
	public void testCalculatePointBurst() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;

		final ZeroOne entity = new ZeroOne(gameId, userId, rule);

		final ThrowingNumber[] firstNumber = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.TWENTY
		};
		final ThrowingCount[] firstCoount = new ThrowingCount[] {
				ThrowingCount.TRIPLE, ThrowingCount.TRIPLE, ThrowingCount.TRIPLE
		};
		entity.setRoundPoint(0, firstNumber, firstCoount);

		final ThrowingNumber[] secondNumber = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.TWO
		};
		final ThrowingCount[] secondCoount = new ThrowingCount[] {
				ThrowingCount.TRIPLE, ThrowingCount.TRIPLE, ThrowingCount.SINGLE
		};
		entity.setRoundPoint(1, secondNumber, secondCoount);

		entity.calculatePoint();

		assertThat(entity.getRestPoint(), is(121));
	}

	@Test
	public void testGetStats() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;

		final ZeroOne entity = new ZeroOne(gameId, userId, rule);

		final ThrowingNumber[] firstNumber = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.TWENTY
		};
		final ThrowingCount[] firstCoount = new ThrowingCount[] {
				ThrowingCount.DOBULE, ThrowingCount.DOBULE, ThrowingCount.DOBULE
		};
		entity.setRoundPoint(0, firstNumber, firstCoount);
		entity.setRoundPoint(1, firstNumber, firstCoount);

		final ThrowingNumber[] secondNumber = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.ONE, ThrowingNumber.OUT_BORD
		};
		final ThrowingCount[] secondCoount = new ThrowingCount[] {
				ThrowingCount.TRIPLE, ThrowingCount.SINGLE, ThrowingCount.OUT
		};
		entity.setRoundPoint(2, secondNumber, secondCoount);

		entity.calculatePoint();
		entity.setEndRoundIndex(2);

		assertThat(Double.valueOf(entity.getStats()), is(Double.valueOf(100.33)));
	}

	@Test
	public void testGetStatsNotDatrsOut() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;

		final ZeroOne entity = new ZeroOne(gameId, userId, rule);

		final ThrowingNumber[] numbers = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.OUT_BORD, ThrowingNumber.OUT_BORD
		};
		final ThrowingCount[] counts = new ThrowingCount[] {
				ThrowingCount.SINGLE, ThrowingCount.OUT, ThrowingCount.OUT
		};

		for (int i = 0; i < rule.getRound(); i++) {

			entity.setRoundPoint(i, numbers, counts);
		}
		entity.calculatePoint();

		assertThat(Double.valueOf(entity.getStats()), is(Double.valueOf(20)));
	}

	@Test
	public void testGetStatsNotEnd() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;

		final ZeroOne entity = new ZeroOne(gameId, userId, rule);

		final ThrowingNumber[] numbers = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.OUT_BORD, ThrowingNumber.OUT_BORD
		};
		final ThrowingCount[] counts = new ThrowingCount[] {
				ThrowingCount.SINGLE, ThrowingCount.OUT, ThrowingCount.OUT
		};

		entity.setRoundPoint(0, numbers, counts);
		entity.setRoundPoint(1, numbers, counts);

		entity.calculatePoint();

		assertThat(Double.valueOf(entity.getStats()), is(Double.valueOf(20)));
	}

	@Test
	public void testReset() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;
		final ZeroOne entity = new ZeroOne(gameId, userId, rule);

		final ThrowingNumber[] numbers = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.OUT_BORD, ThrowingNumber.OUT_BORD
		};
		final ThrowingCount[] counts = new ThrowingCount[] {
				ThrowingCount.SINGLE, ThrowingCount.OUT, ThrowingCount.OUT
		};

		for (int i = 0; i < rule.getRound(); i++) {

			entity.setRoundPoint(i, numbers, counts);
		}
		entity.calculatePoint();

		entity.reset();

		assertThat(entity.getRestPoint(), is(rule.getPoint()));

		for (final Round round : entity.getRounds()) {

			assertThat(round.isEndThrowing(), is(false));

			for (final Throwing throwing : round.getThrowings()) {

				assertThat(throwing.getNumber(), is(ThrowingNumber.OUT_BORD));
				assertThat(throwing.getCount(), is(ThrowingCount.OUT));
			}

		}
	}

	@Test
	public void testIsEnd() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;
		final ZeroOne entity = new ZeroOne(gameId, userId, rule);

		final ThrowingNumber[] firstRoundNumber = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.TWENTY
		};
		final ThrowingCount[] firstRoundCount = new ThrowingCount[] {
				ThrowingCount.TRIPLE, ThrowingCount.TRIPLE, ThrowingCount.TRIPLE
		};

		entity.setRoundPoint(0, firstRoundNumber, firstRoundCount);
		entity.calculatePoint();

		assertThat(entity.isEndGame(), is(false));

		final ThrowingNumber[] secondRoundNumber = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.ONE
		};
		final ThrowingCount[] secondRoundCount = new ThrowingCount[] {
				ThrowingCount.TRIPLE, ThrowingCount.TRIPLE, ThrowingCount.SINGLE
		};

		entity.setRoundPoint(1, secondRoundNumber, secondRoundCount);
		entity.calculatePoint();

		assertThat(entity.isEndGame(), is(true));
	}

	@Test
	public void testIsEndNotDartsOut() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;
		final ZeroOne entity = new ZeroOne(gameId, userId, rule);

		final ThrowingNumber[] firstRoundNumber = new ThrowingNumber[] {
				ThrowingNumber.OUT_BORD, ThrowingNumber.OUT_BORD, ThrowingNumber.OUT_BORD
		};
		final ThrowingCount[] firstRoundCount = new ThrowingCount[] {
				ThrowingCount.OUT, ThrowingCount.OUT, ThrowingCount.OUT
		};

		for (int i = 0; i < rule.getRound(); i++) {

			entity.setRoundPoint(i, firstRoundNumber, firstRoundCount);
		}

		assertThat(entity.isEndGame(), is(true));
	}

	@Test
	public void testGetLastThrowingRound() {

		final long gameId = 10;
		final String userId = "TEST_USER";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;
		final ZeroOne entity = new ZeroOne(gameId, userId, rule);

		final ThrowingNumber[] firstRoundNumber = new ThrowingNumber[] {
				ThrowingNumber.OUT_BORD, ThrowingNumber.OUT_BORD, ThrowingNumber.OUT_BORD
		};
		final ThrowingCount[] firstRoundCount = new ThrowingCount[] {
				ThrowingCount.OUT, ThrowingCount.OUT, ThrowingCount.OUT
		};

		final int endRound = 8;

		for (int i = 0; i < endRound; i++) {

			entity.setRoundPoint(i, firstRoundNumber, firstRoundCount);
		}

		assertThat(entity.getLastThrowingRound(), is(endRound));
	}
}
