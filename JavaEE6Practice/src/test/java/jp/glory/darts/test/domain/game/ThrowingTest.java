package jp.glory.darts.test.domain.game;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.value.Throwing;

import org.junit.Test;

public class ThrowingTest {

	@Test
	public void testInitialize() {

		final ThrowingNumber number = ThrowingNumber.TEN;
		final ThrowingCount count = ThrowingCount.SINGLE;

		final Throwing valueObject = new Throwing(number, count);

		assertThat(valueObject.getNumber(), is(number));
		assertThat(valueObject.getCount(), is(count));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInitializeNumberNull() {

		new Throwing(null, ThrowingCount.SINGLE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInitializeCountNull() {

		new Throwing(ThrowingNumber.TEN, null);
	}

	@Test
	public void testIsSame() {

		final ThrowingNumber number = ThrowingNumber.TEN;
		final ThrowingCount count = ThrowingCount.SINGLE;

		final Throwing baseValueObject = new Throwing(number, count);
		final Throwing compareValueObject = new Throwing(number, count);

		assertThat(baseValueObject.isSameValue(compareValueObject), is(true));
	}

	@Test
	public void testIsSameDifferentNumber() {

		final ThrowingNumber number = ThrowingNumber.TEN;
		final ThrowingCount count = ThrowingCount.SINGLE;

		final Throwing baseValueObject = new Throwing(number, count);
		final Throwing compareValueObject = new Throwing(ThrowingNumber.ONE, count);

		assertThat(baseValueObject.isSameValue(compareValueObject), is(false));
	}

	@Test
	public void testIsSameDifferentCount() {

		final ThrowingNumber number = ThrowingNumber.TEN;
		final ThrowingCount count = ThrowingCount.SINGLE;

		final Throwing baseValueObject = new Throwing(number, count);
		final Throwing compareValueObject = new Throwing(number, ThrowingCount.DOBULE);

		assertThat(baseValueObject.isSameValue(compareValueObject), is(false));
	}

	@Test
	public void testIsSameNull() {

		final ThrowingNumber number = ThrowingNumber.TEN;
		final ThrowingCount count = ThrowingCount.SINGLE;

		final Throwing baseValueObject = new Throwing(number, count);

		assertThat(baseValueObject.isSameValue(null), is(false));
	}

	@Test
	public void testCopyValue() {

		final ThrowingNumber number = ThrowingNumber.TEN;
		final ThrowingCount count = ThrowingCount.SINGLE;

		final Throwing valueObject = new Throwing(number, count);

		final Throwing copyValueObject = valueObject.copyValue();

		assertThat(copyValueObject, is(not(nullValue())));
		assertThat((valueObject != copyValueObject), is(true));
		assertThat(copyValueObject.getNumber(), is(number));
		assertThat(copyValueObject.getCount(), is(count));
	}

	@Test
	public void testGetPoint() {

		final ThrowingNumber number = ThrowingNumber.TEN;
		final ThrowingCount count = ThrowingCount.SINGLE;

		final Throwing valueObject = new Throwing(number, count);

		assertThat(valueObject.getPoint(), is(10));
	}

	@Test
	public void testGetPointSingleBull() {

		final ThrowingNumber number = ThrowingNumber.BULL;
		final ThrowingCount count = ThrowingCount.SINGLE;

		final Throwing valueObject = new Throwing(number, count);

		assertThat(valueObject.getPoint(), is(50));
	}

	@Test
	public void testGetPointDoubleBull() {

		final ThrowingNumber number = ThrowingNumber.BULL;
		final ThrowingCount count = ThrowingCount.DOBULE;

		final Throwing valueObject = new Throwing(number, count);

		assertThat(valueObject.getPoint(), is(50));
	}
}
