package jp.glory.darts.test.ui.page.game.countup;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.stub.application.game.countup.CountUpApplicationStub;
import jp.glory.darts.ui.page.common.CountUpSessionData;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.game.countup.impl.CountUpResultPageImpl;

import org.junit.Before;
import org.junit.Test;

public class CountUpResultPageTest {

	/** テスト対象オブジェクト */
	private CountUpResultPageImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new CountUpResultPageImpl();
		testObj.setApplicaiton(new CountUpApplicationStub());
		testObj.setCountUpSessionData(new CountUpSessionData());
	}

	@Test
	public void testInitNotGameFinished() {

		testObj.setCountUpSessionData(createSessionDataNotFinished());

		final String forwardKey = testObj.init();

		assertThat(forwardKey, is(ForwardKey.COUNT_UP_RESULT.getValue()));
		assertThat(testObj.getTotalPoint(), is(0));
		assertThat(Double.valueOf(testObj.getStats()), is(Double.valueOf(0)));
		assertThat(testObj.isViewExitGameButton(), is(true));
		assertThat(testObj.isViewFinshButton(), is(false));
	}

	@Test
	public void testInitGameFinished() {

		testObj.setCountUpSessionData(createSessionDataFinished());

		final String forwardKey = testObj.init();

		assertThat(forwardKey, is(ForwardKey.COUNT_UP_RESULT.getValue()));
		assertThat(testObj.getTotalPoint(), is(515));
		assertThat(Double.valueOf(testObj.getStats()), is(Double.valueOf(64.37)));
		assertThat(testObj.isViewExitGameButton(), is(false));
		assertThat(testObj.isViewFinshButton(), is(true));
	}

	@Test
	public void testFinish() {

		testObj.setCountUpSessionData(createSessionDataFinished());

		final String fowardKey = testObj.finish();

		assertThat(fowardKey, is(ForwardKey.MAIN_MENU.getValue()));
	}

	/**
	 * ゲームが終了してないカウントアップセッションデータを作成する
	 * 
	 * @return カウントアップセッションデータ
	 */
	private CountUpSessionData createSessionDataNotFinished() {

		final CountUpSessionData sessionData = new CountUpSessionData();

		final CountUp entity = new CountUp(1, "TEST_USER_0001");
		sessionData.setGameInfo(entity);

		return sessionData;
	}

	/**
	 * ゲームが終了したカウントアップセッションデータを作成する
	 * 
	 * @return カウントアップセッションデータ
	 */
	private CountUpSessionData createSessionDataFinished() {

		final CountUpSessionData sessionData = new CountUpSessionData();

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

		final CountUp entity = new CountUp(1, "TEST_USER_0001");
		for (int i = 0; i < GameConst.COUNT_UP_ROUND_COUNT; i++) {

			entity.setRoundPoint(i, number[i], count[i]);
		}
		sessionData.setGameInfo(entity);

		return sessionData;
	}
}
