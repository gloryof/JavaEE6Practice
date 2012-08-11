package jp.glory.darts.test.ui.page.game.zeroone;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.stub.application.game.zeroone.ZeroOneApplicationStub;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.ZeroOneSessionData;
import jp.glory.darts.ui.page.game.zeroone.impl.ZeroOneResultPageImpl;

import org.junit.Before;
import org.junit.Test;

public class ZeroOneResultPageTest {

	/** テスト対象オブジェクト */
	private ZeroOneResultPageImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new ZeroOneResultPageImpl();
		testObj.setApplication(new ZeroOneApplicationStub());
		testObj.setSessionData(new ZeroOneSessionData());
	}

	@Test
	public void testInitDartsOut() {

		testObj.getSessionData().setGameInfo(createEntityDartsOut());

		final String forwardKey = testObj.init();

		assertThat(forwardKey, is(ForwardKey.ZERO_ONE_RESULT.getValue()));
		assertThat(testObj.getDartsOutRound(), is(2));
		assertThat(Double.valueOf(testObj.getStats()), is(Double.valueOf(150.5)));
		assertThat(testObj.getRestPoint(), is(0));

		assertThat(testObj.isViewFinshButton(), is(true));
		assertThat(testObj.isViewExitGameButton(), is(false));
	}

	@Test
	public void testInitNotDartsOut() {

		testObj.getSessionData().setGameInfo(createEntityNotDartsOut());
		final ZeroOneGameRules rule = testObj.getSessionData().getGameInfo().getRule();

		final String forwardKey = testObj.init();

		assertThat(forwardKey, is(ForwardKey.ZERO_ONE_RESULT.getValue()));
		assertThat(testObj.getDartsOutRound(), is(rule.getRound()));
		assertThat(Double.valueOf(testObj.getStats()), is(Double.valueOf(6)));
		assertThat(testObj.getRestPoint(), is(241));

		assertThat(testObj.isViewFinshButton(), is(true));
		assertThat(testObj.isViewExitGameButton(), is(false));
	}

	@Test
	public void testInitNotFinished() {

		testObj.getSessionData().setGameInfo(createEntityNotFinished());

		final String forwardKey = testObj.init();

		assertThat(forwardKey, is(ForwardKey.ZERO_ONE_RESULT.getValue()));
		assertThat(testObj.getDartsOutRound(), is(0));
		assertThat(Double.valueOf(testObj.getStats()), is(Double.valueOf(52.5)));
		assertThat(testObj.getRestPoint(), is(196));

		assertThat(testObj.isViewFinshButton(), is(false));
		assertThat(testObj.isViewExitGameButton(), is(true));
	}

	@Test
	public void testFinish() {

		testObj.getSessionData().setGameInfo(createEntityDartsOut());

		final String fowardKey = testObj.finish();

		assertThat(fowardKey, is(ForwardKey.MAIN_MENU.getValue()));
	}

	/**
	 * ダーツアウトしたゼロワンエンティティを返却する
	 * 
	 * @return ゼロワンエンティティ
	 */
	private ZeroOne createEntityDartsOut() {

		final ZeroOne entity = new ZeroOne(1, "TEST_USER", ZeroOneGameRules.GAME_301);

		final ThrowingNumber[] firstNumber = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.TWENTY
		};
		final ThrowingCount[] firstCoount = new ThrowingCount[] {
				ThrowingCount.TRIPLE, ThrowingCount.TRIPLE, ThrowingCount.TRIPLE
		};
		entity.setRoundPoint(0, firstNumber, firstCoount);

		final ThrowingNumber[] secondNumber = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.ONE
		};
		final ThrowingCount[] secondCoount = new ThrowingCount[] {
				ThrowingCount.TRIPLE, ThrowingCount.TRIPLE, ThrowingCount.SINGLE
		};
		entity.setRoundPoint(1, secondNumber, secondCoount);

		entity.calculatePoint();

		return entity;
	}

	/**
	 * ダーツアウトしていないゼロワンエンティティを返却する。
	 * 
	 * @return ゼロワンエンティティ
	 */
	private ZeroOne createEntityNotDartsOut() {

		final ZeroOne entity = new ZeroOne(1, "TEST_USER", ZeroOneGameRules.GAME_301);

		final ThrowingNumber[] numbers = new ThrowingNumber[] {
				ThrowingNumber.ONE, ThrowingNumber.TWO, ThrowingNumber.THREE
		};
		final ThrowingCount[] counts = new ThrowingCount[] {
				ThrowingCount.SINGLE, ThrowingCount.SINGLE, ThrowingCount.SINGLE
		};

		for (int i = 0; i < entity.getRule().getRound(); i++) {

			entity.setRoundPoint(i, numbers, counts);
		}

		entity.calculatePoint();

		return entity;
	}

	/**
	 * ゲーム終了していないゼロワンエンティティを返却する。
	 * 
	 * @return ゼロワンエンティティ
	 */
	private ZeroOne createEntityNotFinished() {

		final ZeroOne entity = new ZeroOne(1, "TEST_USER", ZeroOneGameRules.GAME_301);

		final ThrowingNumber[] firstNumber = new ThrowingNumber[] {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.TWENTY
		};
		final ThrowingCount[] firstCoount = new ThrowingCount[] {
				ThrowingCount.SINGLE, ThrowingCount.SINGLE, ThrowingCount.SINGLE
		};
		entity.setRoundPoint(0, firstNumber, firstCoount);

		final ThrowingNumber[] secondNumber = new ThrowingNumber[] {
				ThrowingNumber.FIFTEEN, ThrowingNumber.FIFTEEN, ThrowingNumber.FIFTEEN
		};
		final ThrowingCount[] secondCoount = new ThrowingCount[] {
				ThrowingCount.SINGLE, ThrowingCount.SINGLE, ThrowingCount.SINGLE
		};
		entity.setRoundPoint(1, secondNumber, secondCoount);

		entity.calculatePoint();

		return entity;
	}
}
