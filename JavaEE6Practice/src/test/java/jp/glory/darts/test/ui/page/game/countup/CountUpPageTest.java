package jp.glory.darts.test.ui.page.game.countup;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.faces.model.SelectItem;

import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.value.Throwing;
import jp.glory.darts.stub.ui.page.game.countup.CountUpResultStub;
import jp.glory.darts.ui.page.common.CountUpSessionData;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.UserSessionData;
import jp.glory.darts.ui.page.game.countup.impl.CountUpPageImpl;

import org.junit.Before;
import org.junit.Test;

public class CountUpPageTest {

	private CountUpPageImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new CountUpPageImpl();
		testObj.setCountUpSessionData(new CountUpSessionData());
		testObj.setUserSessionData(new UserSessionData());
		testObj.setResultPage(new CountUpResultStub());
	}

	@Test
	public void testInit() {

		final String userId = "TEST_0001";
		testObj.getUserSessionData().setUserId(userId);

		testObj.setRoundIndex(1);
		testObj.setThrowOneNumber(1);
		testObj.setThrowOneCount(1);
		testObj.setThrowTwoNumber(1);
		testObj.setThrowTwoCount(1);
		testObj.setThrowThreeNumber(1);
		testObj.setThrowThreeCount(1);
		testObj.setTotalPoint(1);

		testObj.setDisabledPrevButton(false);
		testObj.setDisabledNextButton(true);

		testObj.setViewNextButton(false);
		testObj.setViewEndButton(true);

		final String forwardKey = testObj.init();

		assertThat(forwardKey, is(ForwardKey.COUNT_UP.getValue()));

		assertThat(testObj.getRoundIndex(), is(0));

		assertThat(testObj.getThrowOneNumber(), is(0));
		assertThat(testObj.getThrowOneCount(), is(0));
		assertThat(testObj.getThrowTwoNumber(), is(0));
		assertThat(testObj.getThrowTwoCount(), is(0));
		assertThat(testObj.getThrowThreeNumber(), is(0));
		assertThat(testObj.getThrowThreeCount(), is(0));

		assertThat(testObj.getTotalPoint(), is(0));

		assertThat(testObj.isDisabledPrevButton(), is(true));
		assertThat(testObj.isDisabledNextButton(), is(false));
		assertThat(testObj.isViewNextButton(), is(true));
		assertThat(testObj.isViewEndButton(), is(false));

		final CountUp entity = testObj.getCountUpSessionData().getGameInfo();
		assertThat(entity, is(not(nullValue())));
		assertThat(entity.getUserId(), is(userId));
	}

	@Test
	public void testNextRoundCalcPoint() {

		final int beforeIndex = 2;
		testObj.setRoundIndex(beforeIndex);

		testObj.setCountUpSessionData(createCountUpSessionData());

		testObj.setThrowOneNumber(15);
		testObj.setThrowOneCount(2);
		testObj.setThrowTwoNumber(11);
		testObj.setThrowTwoCount(3);
		testObj.setThrowThreeNumber(8);
		testObj.setThrowThreeCount(1);

		testObj.nextRound();

		assertThat(testObj.getRoundIndex(), is((beforeIndex + 1)));

		assertThat(testObj.getThrowOneNumber(), is(0));
		assertThat(testObj.getThrowOneCount(), is(0));
		assertThat(testObj.getThrowTwoNumber(), is(0));
		assertThat(testObj.getThrowTwoCount(), is(0));
		assertThat(testObj.getThrowThreeNumber(), is(0));
		assertThat(testObj.getThrowThreeCount(), is(0));

		assertThat(testObj.getTotalPoint(), is(5 + 26 + 71));

		final CountUpSessionData countUpData = testObj.getCountUpSessionData();
		final CountUp entity = countUpData.getGameInfo();

		final Round round = entity.getRound(beforeIndex);
		assertThat(round.isEndThrowing(), is(true));

		final Throwing firstThrowing = round.getThrowing(0);
		assertThat(firstThrowing.getNumber(), is(ThrowingNumber.FIFTEEN));
		assertThat(firstThrowing.getCount(), is(ThrowingCount.DOBULE));

		final Throwing secondThrowing = round.getThrowing(1);
		assertThat(secondThrowing.getNumber(), is(ThrowingNumber.ELEVEN));
		assertThat(secondThrowing.getCount(), is(ThrowingCount.TRIPLE));

		final Throwing thridThrowing = round.getThrowing(2);
		assertThat(thridThrowing.getNumber(), is(ThrowingNumber.EIGHT));
		assertThat(thridThrowing.getCount(), is(ThrowingCount.SINGLE));
	}

	@Test
	public void testNextRoundDisabledButton() {

		testObj.setCountUpSessionData(createCountUpSessionData());

		testObj.setRoundIndex(0);
		testObj.nextRound();

		assertThat(testObj.isDisabledPrevButton(), is(false));
		assertThat(testObj.isDisabledNextButton(), is(false));
		assertThat(testObj.isViewNextButton(), is(true));
		assertThat(testObj.isViewEndButton(), is(false));

		testObj.setRoundIndex((GameConst.COUNT_UP_ROUND_COUNT - 2));
		testObj.nextRound();

		assertThat(testObj.isDisabledPrevButton(), is(false));
		assertThat(testObj.isDisabledNextButton(), is(true));
		assertThat(testObj.isViewNextButton(), is(false));
		assertThat(testObj.isViewEndButton(), is(true));
	}

	@Test
	public void testPrevRoundPointCalcPoint() {

		final int beforeIndex = 2;
		testObj.setRoundIndex(beforeIndex);

		testObj.setCountUpSessionData(createCountUpSessionData());
		testObj.setThrowOneNumber(15);
		testObj.setThrowOneCount(2);
		testObj.setThrowTwoNumber(11);
		testObj.setThrowTwoCount(3);
		testObj.setThrowThreeNumber(8);
		testObj.setThrowThreeCount(1);

		testObj.prevRound();

		assertThat(testObj.getRoundIndex(), is(beforeIndex - 1));

		assertThat(testObj.getThrowOneNumber(), is(3));
		assertThat(testObj.getThrowOneCount(), is(1));
		assertThat(testObj.getThrowTwoNumber(), is(4));
		assertThat(testObj.getThrowTwoCount(), is(2));
		assertThat(testObj.getThrowThreeNumber(), is(5));
		assertThat(testObj.getThrowThreeCount(), is(3));

		assertThat(testObj.getTotalPoint(), is(5 + 26 + 71));

		final CountUpSessionData countUpData = testObj.getCountUpSessionData();
		final CountUp entity = countUpData.getGameInfo();

		final Round round = entity.getRound(beforeIndex);
		assertThat(round.isEndThrowing(), is(true));

		final Throwing firstThrowing = round.getThrowing(0);
		assertThat(firstThrowing.getNumber(), is(ThrowingNumber.FIFTEEN));
		assertThat(firstThrowing.getCount(), is(ThrowingCount.DOBULE));

		final Throwing secondThrowing = round.getThrowing(1);
		assertThat(secondThrowing.getNumber(), is(ThrowingNumber.ELEVEN));
		assertThat(secondThrowing.getCount(), is(ThrowingCount.TRIPLE));

		final Throwing thridThrowing = round.getThrowing(2);
		assertThat(thridThrowing.getNumber(), is(ThrowingNumber.EIGHT));
		assertThat(thridThrowing.getCount(), is(ThrowingCount.SINGLE));
	}

	@Test
	public void testPrevRoundDisabledButton() {

		testObj.setCountUpSessionData(createCountUpSessionData());

		testObj.setRoundIndex((GameConst.COUNT_UP_ROUND_COUNT - 1));
		testObj.prevRound();

		assertThat(testObj.isDisabledPrevButton(), is(false));
		assertThat(testObj.isDisabledNextButton(), is(false));
		assertThat(testObj.isViewNextButton(), is(true));
		assertThat(testObj.isViewEndButton(), is(false));

		testObj.setRoundIndex(1);
		testObj.prevRound();

		assertThat(testObj.isDisabledPrevButton(), is(true));
		assertThat(testObj.isDisabledNextButton(), is(false));
		assertThat(testObj.isViewNextButton(), is(true));
		assertThat(testObj.isViewEndButton(), is(false));
	}

	@Test
	public void testReset() {

		final int beforeIndex = 1;
		testObj.setRoundIndex(beforeIndex);

		testObj.setCountUpSessionData(createCountUpSessionData());

		testObj.reset();

		assertThat(testObj.getRoundIndex(), is(0));

		assertThat(testObj.getThrowOneNumber(), is(0));
		assertThat(testObj.getThrowOneCount(), is(0));
		assertThat(testObj.getThrowTwoNumber(), is(0));
		assertThat(testObj.getThrowTwoCount(), is(0));
		assertThat(testObj.getThrowThreeNumber(), is(0));
		assertThat(testObj.getThrowThreeCount(), is(0));

		assertThat(testObj.getTotalPoint(), is(0));
		assertThat(testObj.isDisabledPrevButton(), is(true));
		assertThat(testObj.isDisabledNextButton(), is(false));

		final CountUpSessionData countUpData = testObj.getCountUpSessionData();
		final CountUp entity = countUpData.getGameInfo();

		for (int i = 0; i < GameConst.COUNT_UP_ROUND_COUNT; i++) {

			final Round round = entity.getRound(i);
			assertThat(round.isEndThrowing(), is(false));

			for (final Throwing throwing : round.getThrowings()) {

				assertThat(throwing.getNumber(), is(ThrowingNumber.OUT_BORD));
				assertThat(throwing.getCount(), is(ThrowingCount.OUT));
			}
		}
	}

	@Test
	public void testGetNumberList() {

		final List<SelectItem> selectList = testObj.getNumberList();
		final ThrowingNumber[] numberArray = ThrowingNumber.values();

		assertThat(selectList, is(not(nullValue())));
		assertThat(selectList.size(), is(numberArray.length));

		for (int i = 0; i < numberArray.length; i++) {

			final SelectItem item = selectList.get(i);
			final ThrowingNumber number = numberArray[i];

			assertThat(number.getNumber(), is(item.getValue()));
			assertThat(number.getLabel(), is(item.getLabel()));
		}
	}

	@Test
	public void testGetCountList() {

		final List<SelectItem> selectList = testObj.getCountList();
		final ThrowingCount[] countArray = ThrowingCount.values();

		assertThat(selectList, is(not(nullValue())));
		assertThat(selectList.size(), is(countArray.length));

		for (int i = 0; i < countArray.length; i++) {

			final SelectItem item = selectList.get(i);
			final ThrowingCount count = countArray[i];

			assertThat(count.getCount(), is(item.getValue()));
			assertThat(count.getLabel(), is(item.getLabel()));
		}
	}

	@Test
	public void testEndGame() {

		testObj.getCountUpSessionData().setGameInfo(new CountUp(1, "TEST_0001"));

		final int beforeIndex = GameConst.COUNT_UP_ROUND_COUNT - 1;

		testObj.setRoundIndex(beforeIndex);

		testObj.setThrowOneNumber(15);
		testObj.setThrowOneCount(2);
		testObj.setThrowTwoNumber(11);
		testObj.setThrowTwoCount(3);
		testObj.setThrowThreeNumber(8);
		testObj.setThrowThreeCount(1);

		final String forwardKey = testObj.endGame();

		assertThat(forwardKey, is(ForwardKey.COUNT_UP_RESULT.getValue()));

		assertThat(testObj.getTotalPoint(), is(30 + 33 + 8));

		final CountUpSessionData sessionData = testObj.getCountUpSessionData();
		final CountUp entity = sessionData.getGameInfo();

		final Round round = entity.getRound(beforeIndex);

		assertThat(round.isEndThrowing(), is(true));

		final Throwing firstThrowing = round.getThrowing(0);
		assertThat(firstThrowing.getNumber(), is(ThrowingNumber.FIFTEEN));
		assertThat(firstThrowing.getCount(), is(ThrowingCount.DOBULE));

		final Throwing secondThrowing = round.getThrowing(1);
		assertThat(secondThrowing.getNumber(), is(ThrowingNumber.ELEVEN));
		assertThat(secondThrowing.getCount(), is(ThrowingCount.TRIPLE));

		final Throwing thridThrowing = round.getThrowing(2);
		assertThat(thridThrowing.getNumber(), is(ThrowingNumber.EIGHT));
		assertThat(thridThrowing.getCount(), is(ThrowingCount.SINGLE));
	}

	@Test
	public void testReview() {

		assertThat(testObj.review(), is(ForwardKey.COUNT_UP.getValue()));
	}

	/**
	 * カウントアップセッションデータを作成する
	 * 
	 * @return セッションデータ
	 */
	private CountUpSessionData createCountUpSessionData() {

		final String userId = "TEST_0001";
		final CountUpSessionData returnData = new CountUpSessionData();

		final CountUp entity = new CountUp(1, userId);

		final ThrowingNumber[] roundOneThrowing = {
				ThrowingNumber.OUT_BORD, ThrowingNumber.ONE, ThrowingNumber.TWO
		};
		final ThrowingCount[] countOne = {
				ThrowingCount.OUT, ThrowingCount.SINGLE, ThrowingCount.DOBULE
		};

		final ThrowingNumber[] roundTwoThrowing = {
				ThrowingNumber.THREE, ThrowingNumber.FOUR, ThrowingNumber.FIVE
		};
		final ThrowingCount[] countTwo = {
				ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
		};

		entity.setRoundPoint(0, roundOneThrowing, countOne);
		entity.setRoundPoint(1, roundTwoThrowing, countTwo);

		returnData.setGameInfo(entity);

		return returnData;
	}
}
