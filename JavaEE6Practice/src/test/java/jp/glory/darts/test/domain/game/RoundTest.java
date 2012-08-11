package jp.glory.darts.test.domain.game;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.value.Throwing;

import org.junit.Test;

public class RoundTest {

	private final ThrowingNumber[] numberParam = new ThrowingNumber[] {
			ThrowingNumber.BULL, ThrowingNumber.TWENTY, ThrowingNumber.TEN
	};

	private final ThrowingCount[] countParam = new ThrowingCount[] {
			ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
	};

	@Test
	public void testInitialize() {

		final int roundIndex = 1;
		final Round entity = new Round(roundIndex);

		assertThat(entity.getRoundIndex(), is(roundIndex));
		assertThat(entity.isEndThrowing(), is(false));

		for (int i = 0; i < 3; i++) {

			final Throwing throwing = entity.getThrowing(i);

			assertThat(throwing, is(not(nullValue())));
			assertThat(throwing.getNumber(), is(ThrowingNumber.OUT_BORD));
			assertThat(throwing.getCount(), is(ThrowingCount.OUT));
		}
	}

	@Test
	public void testIsSame() {

		final int roundIndex = 1;
		final Round entity = new Round(roundIndex);
		final Round compareEntity = new Round(roundIndex);

		assertThat(entity.isSame(compareEntity), is(true));
	}

	@Test
	public void testIsSameDifferentValue() {

		final Round entity = new Round(1);
		final Round compareEntity = new Round(2);

		assertThat(entity.isSame(compareEntity), is(false));
	}

	@Test
	public void testIsSameNull() {

		final Round entity = new Round(1);

		assertThat(entity.isSame(null), is(false));
	}

	@Test
	public void testGetThrowingAndSetThrowingsPoint() {

		final Round entity = new Round(0);
		setThrowingsPointToEntity(entity);

		assertThrowings(entity);
	}

	@Test
	public void testCopyEntity() {

		final Round entity = new Round(1);
		setThrowingsPointToEntity(entity);
		entity.setEndThrowing(true);

		final Round copyEntity = entity.copyEntity();

		assertThat(copyEntity, is(not(nullValue())));
		assertThat((entity != copyEntity), is(true));
		assertThat(entity.isSame(copyEntity), is(true));
		assertThrowings(copyEntity);
		assertThat(copyEntity.isEndThrowing(), is(true));
	}

	@Test
	public void testGetPoint() {

		final Round entity = new Round(1);
		entity.setThrowingPoint(0, ThrowingNumber.BULL, ThrowingCount.DOBULE);
		entity.setThrowingPoint(1, ThrowingNumber.TEN, ThrowingCount.TRIPLE);
		entity.setThrowingPoint(2, ThrowingNumber.OUT_BORD, ThrowingCount.OUT);

		assertThat(entity.getPoint(), is(80));
	}

	@Test
	public void testGetPointInitializeEntity() {

		final Round entity = new Round(1);

		assertThat(entity.getPoint(), is(0));
	}

	@Test
	public void testGetThrowings() {

		final Round entity = new Round(1);

		final ThrowingNumber[] numbers = {
				ThrowingNumber.BULL, ThrowingNumber.TEN, ThrowingNumber.OUT_BORD
		};
		final ThrowingCount[] counts = {
				ThrowingCount.DOBULE, ThrowingCount.TRIPLE, ThrowingCount.OUT
		};

		for (int i = 0; i < GameConst.THROW_COUNT; i++) {

			entity.setThrowingPoint(i, numbers[i], counts[i]);
		}

		final Throwing[] throwings = entity.getThrowings();

		assertThat(throwings, is(not(nullValue())));
		assertThat(throwings.length, is(GameConst.THROW_COUNT));

		int index = 0;
		for (final Throwing throwing : throwings) {

			assertThat(numbers[index], is(throwing.getNumber()));
			assertThat(counts[index], is(throwing.getCount()));

			index++;
		}
	}

	/**
	 * エンティティのスローイングの値を書き換える
	 * 
	 * @param entity エンティティ
	 */
	private void setThrowingsPointToEntity(final Round entity) {

		for (int i = 0; i < numberParam.length; i++) {

			entity.setThrowingPoint(i, numberParam[i], countParam[i]);
		}
	}

	/**
	 * スローイングの検証を行う
	 * 
	 * @param entity ラウンドエンティティ
	 */
	private void assertThrowings(final Round entity) {

		for (int i = 0; i < numberParam.length; i++) {

			assertThat(entity.getThrowing(i).getNumber(), is(numberParam[i]));
			assertThat(entity.getThrowing(i).getCount(), is(countParam[i]));
		}
	}
}
