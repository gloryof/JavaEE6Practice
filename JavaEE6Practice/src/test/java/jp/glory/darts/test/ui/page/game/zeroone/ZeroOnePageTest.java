package jp.glory.darts.test.ui.page.game.zeroone;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.faces.model.SelectItem;

import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.domain.game.value.Throwing;
import jp.glory.darts.stub.ui.page.game.zeroone.ZeroOneResultPageStub;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.UserSessionData;
import jp.glory.darts.ui.page.common.ZeroOneSessionData;
import jp.glory.darts.ui.page.game.zeroone.impl.ZeroOnePageImpl;

import org.junit.Before;
import org.junit.Test;

public class ZeroOnePageTest {

	/** テスト対象オブジェクト */
	private ZeroOnePageImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new ZeroOnePageImpl();
		testObj.setResultPage(new ZeroOneResultPageStub());
		testObj.setUserSessionData(new UserSessionData());
		testObj.setZeroOneSessionData(new ZeroOneSessionData());
	}

	@Test
	public void testInit() {

		final long gameId = 0;
		final String userId = "TEST_0001";
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_901;
		testObj.getUserSessionData().setUserId(userId);

		final ZeroOneSessionData sessionData = new ZeroOneSessionData();
		sessionData.setGameInfo(new ZeroOne(gameId, userId, rule));

		testObj.setRoundIndex(1);
		testObj.setTotalRound(0);
		testObj.setInitPoint(1);
		testObj.setRestPoint(1000);

		testObj.setThrowOneNumber(1);
		testObj.setThrowOneCount(1);
		testObj.setThrowTwoNumber(1);
		testObj.setThrowTwoCount(1);
		testObj.setThrowThreeNumber(1);
		testObj.setThrowThreeCount(1);

		testObj.setDisabledPrevButton(false);

		final String forwardKey = testObj.init(rule.getTypeCode());

		assertThat(forwardKey, is(ForwardKey.ZERO_ONE.getValue()));

		assertThat(testObj.getRoundIndex(), is(0));
		assertThat(testObj.getTotalRound(), is(rule.getRound()));
		assertThat(testObj.getInitPoint(), is(rule.getPoint()));
		assertThat(testObj.getRestPoint(), is(rule.getPoint()));

		assertThat(testObj.getThrowOneNumber(), is(0));
		assertThat(testObj.getThrowOneCount(), is(0));
		assertThat(testObj.getThrowTwoNumber(), is(0));
		assertThat(testObj.getThrowTwoCount(), is(0));
		assertThat(testObj.getThrowThreeNumber(), is(0));
		assertThat(testObj.getThrowThreeCount(), is(0));

		assertThat(testObj.isDisabledPrevButton(), is(true));

		final ZeroOne entity = testObj.getZeroOneSessionData().getGameInfo();
		assertThat(entity, is(not(nullValue())));
		assertThat(entity.getUserId(), is(userId));
		assertThat(entity.getRule(), is(rule));
	}

	@Test
	public void testNextRoundCalcPoint() {

		final int beforeIndex = 2;
		testObj.setRoundIndex(beforeIndex);

		final ZeroOneSessionData sessionData = createZeroOneSessionData();

		testObj.setZeroOneSessionData(sessionData);

		testObj.setThrowOneNumber(15);
		testObj.setThrowOneCount(2);
		testObj.setThrowTwoNumber(11);
		testObj.setThrowTwoCount(3);
		testObj.setThrowThreeNumber(8);
		testObj.setThrowThreeCount(1);

		final String forwardKey = testObj.next();

		assertThat(forwardKey, is(ForwardKey.ZERO_ONE.getValue()));

		final ZeroOne entity = testObj.getZeroOneSessionData().getGameInfo();
		final ZeroOneGameRules rule = entity.getRule();

		assertThat(testObj.getRoundIndex(), is(beforeIndex + 1));
		assertThat(testObj.getRestPoint(), is(rule.getPoint() - (5 + 26 + 71)));

		assertThat(testObj.getThrowOneNumber(), is(0));
		assertThat(testObj.getThrowOneCount(), is(0));
		assertThat(testObj.getThrowTwoNumber(), is(0));
		assertThat(testObj.getThrowTwoCount(), is(0));
		assertThat(testObj.getThrowThreeNumber(), is(0));
		assertThat(testObj.getThrowThreeCount(), is(0));

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

		final ZeroOneSessionData sessionData = createZeroOneSessionData();
		final ZeroOneGameRules rule = sessionData.getGameInfo().getRule();
		testObj.setZeroOneSessionData(sessionData);

		testObj.setRoundIndex(0);
		final String firstForwadKey = testObj.next();

		assertThat(firstForwadKey, is(ForwardKey.ZERO_ONE.getValue()));
		assertThat(testObj.isDisabledPrevButton(), is(false));

		testObj.setRoundIndex((rule.getRound() - 2));
		final String secondForwadKey = testObj.next();

		assertThat(secondForwadKey, is(ForwardKey.ZERO_ONE.getValue()));
		assertThat(testObj.isDisabledPrevButton(), is(false));
	}

	@Test
	public void testNextRoundDartsOut() {

		final ZeroOne entity = new ZeroOne(1, "TEST_USER", ZeroOneGameRules.GAME_301);
		final ZeroOneSessionData sessionData = new ZeroOneSessionData();
		sessionData.setGameInfo(entity);
		testObj.setZeroOneSessionData(sessionData);

		final ThrowingNumber[] roundOneThrowing = {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.TWENTY
		};
		final ThrowingCount[] countOne = {
				ThrowingCount.TRIPLE, ThrowingCount.TRIPLE, ThrowingCount.TRIPLE
		};
		entity.setRoundPoint(0, roundOneThrowing, countOne);

		final int beforeIndex = 1;
		testObj.setRoundIndex(beforeIndex);

		testObj.setThrowOneNumber(20);
		testObj.setThrowOneCount(3);
		testObj.setThrowTwoNumber(20);
		testObj.setThrowTwoCount(3);
		testObj.setThrowThreeNumber(1);
		testObj.setThrowThreeCount(1);

		final String secondForwadKey = testObj.next();

		assertThat(secondForwadKey, is(ForwardKey.ZERO_ONE_RESULT.getValue()));
		assertThat(testObj.isDisabledPrevButton(), is(false));

		final ZeroOne afterEntity = testObj.getZeroOneSessionData().getGameInfo();
		assertThat(afterEntity.getEndRoundIndex(), is(1));
	}

	@Test
	public void testPrevRoundPointCalcPoint() {

		final int beforeIndex = 2;
		testObj.setRoundIndex(beforeIndex);

		final ZeroOneSessionData sessionData = createZeroOneSessionData();
		testObj.setZeroOneSessionData(sessionData);

		testObj.setThrowOneNumber(15);
		testObj.setThrowOneCount(2);
		testObj.setThrowTwoNumber(11);
		testObj.setThrowTwoCount(3);
		testObj.setThrowThreeNumber(8);
		testObj.setThrowThreeCount(1);

		testObj.prevRound();

		final ZeroOne entity = testObj.getZeroOneSessionData().getGameInfo();
		final ZeroOneGameRules rule = entity.getRule();

		assertThat(testObj.getRoundIndex(), is(beforeIndex - 1));
		assertThat(testObj.getRestPoint(), is(rule.getPoint() - (5 + 26 + 71)));

		assertThat(testObj.getThrowOneNumber(), is(3));
		assertThat(testObj.getThrowOneCount(), is(1));
		assertThat(testObj.getThrowTwoNumber(), is(4));
		assertThat(testObj.getThrowTwoCount(), is(2));
		assertThat(testObj.getThrowThreeNumber(), is(5));
		assertThat(testObj.getThrowThreeCount(), is(3));

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

		final ZeroOneSessionData sessionData = createZeroOneSessionData();
		final ZeroOneGameRules rule = sessionData.getGameInfo().getRule();
		testObj.setZeroOneSessionData(sessionData);

		testObj.setRoundIndex((rule.getRound() - 1));
		testObj.prevRound();

		assertThat(testObj.isDisabledPrevButton(), is(false));
		testObj.setRoundIndex(1);
		testObj.prevRound();

		assertThat(testObj.isDisabledPrevButton(), is(true));
	}

	@Test
	public void testReset() {

		final int beforeIndex = 1;
		testObj.setRoundIndex(beforeIndex);

		final ZeroOneSessionData sessionData = createZeroOneSessionData();
		testObj.setZeroOneSessionData(sessionData);

		testObj.reset();

		final ZeroOne entity = testObj.getZeroOneSessionData().getGameInfo();
		final ZeroOneGameRules rule = entity.getRule();

		assertThat(testObj.getRoundIndex(), is(0));
		assertThat(testObj.getInitPoint(), is(rule.getPoint()));
		assertThat(testObj.getRestPoint(), is(rule.getPoint()));

		assertThat(testObj.getThrowOneNumber(), is(0));
		assertThat(testObj.getThrowOneCount(), is(0));
		assertThat(testObj.getThrowTwoNumber(), is(0));
		assertThat(testObj.getThrowTwoCount(), is(0));
		assertThat(testObj.getThrowThreeNumber(), is(0));
		assertThat(testObj.getThrowThreeCount(), is(0));

		assertThat(testObj.isDisabledPrevButton(), is(true));

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
	public void testReview() {

		final ThrowingNumber[] numbers = {
				ThrowingNumber.OUT_BORD, ThrowingNumber.ONE, ThrowingNumber.TWO
		};
		final ThrowingCount[] counts = {
				ThrowingCount.OUT, ThrowingCount.SINGLE, ThrowingCount.DOBULE
		};

		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;

		final ZeroOne entity = new ZeroOne(0, "TEST_USER", rule);
		final ZeroOneSessionData sessionData = new ZeroOneSessionData();
		final int viewRoundIndex = 2;

		for (int i = 0; i < viewRoundIndex; i++) {

			entity.setRoundPoint(i, numbers, counts);
		}
		entity.calculatePoint();

		sessionData.setGameInfo(entity);
		testObj.setZeroOneSessionData(sessionData);

		final String forwadKey = testObj.review();

		assertThat(forwadKey, is(ForwardKey.ZERO_ONE.getValue()));

		assertThat(testObj.getRoundIndex(), is(viewRoundIndex));
		assertThat(testObj.getRestPoint(), is(entity.getRestPoint()));

		assertThat(testObj.getThrowOneNumber(), is(0));
		assertThat(testObj.getThrowOneCount(), is(0));
		assertThat(testObj.getThrowTwoNumber(), is(0));
		assertThat(testObj.getThrowTwoCount(), is(0));
		assertThat(testObj.getThrowThreeNumber(), is(0));
		assertThat(testObj.getThrowThreeCount(), is(0));
	}

	@Test
	public void testReviewGameIsEnd() {

		final ThrowingNumber[] numbers = {
				ThrowingNumber.ONE, ThrowingNumber.TWO, ThrowingNumber.THREE
		};
		final ThrowingCount[] counts = {
				ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
		};

		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;

		final ZeroOne entity = new ZeroOne(0, "TEST_USER", rule);
		final ZeroOneSessionData sessionData = new ZeroOneSessionData();
		final int viewRoundIndex = rule.getRound() - 1;

		for (int i = 0; i <= viewRoundIndex; i++) {

			entity.setRoundPoint(i, numbers, counts);
		}
		entity.setEndRoundIndex(viewRoundIndex);
		entity.calculatePoint();

		sessionData.setGameInfo(entity);
		testObj.setZeroOneSessionData(sessionData);

		final String forwadKey = testObj.review();

		assertThat(forwadKey, is(ForwardKey.ZERO_ONE.getValue()));

		assertThat(testObj.getRoundIndex(), is(viewRoundIndex));
		assertThat(testObj.getRestPoint(), is(entity.getRestPoint()));

		assertThat(testObj.getThrowOneNumber(), is(1));
		assertThat(testObj.getThrowOneCount(), is(1));
		assertThat(testObj.getThrowTwoNumber(), is(2));
		assertThat(testObj.getThrowTwoCount(), is(2));
		assertThat(testObj.getThrowThreeNumber(), is(3));
		assertThat(testObj.getThrowThreeCount(), is(3));
	}

	@Test
	public void testReviewDartsOut() {

		final ThrowingNumber[] firstNumbers = {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.TWENTY
		};
		final ThrowingCount[] firstCounts = {
				ThrowingCount.TRIPLE, ThrowingCount.TRIPLE, ThrowingCount.TRIPLE
		};

		final ThrowingNumber[] secondNumbers = {
				ThrowingNumber.TWENTY, ThrowingNumber.TWENTY, ThrowingNumber.ONE
		};
		final ThrowingCount[] secondCounts = {
				ThrowingCount.TRIPLE, ThrowingCount.TRIPLE, ThrowingCount.SINGLE
		};

		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_301;

		final ZeroOne entity = new ZeroOne(0, "TEST_USER", rule);
		final ZeroOneSessionData sessionData = new ZeroOneSessionData();

		entity.setRoundPoint(0, firstNumbers, firstCounts);
		entity.setRoundPoint(1, secondNumbers, secondCounts);
		entity.setEndRoundIndex(1);
		entity.calculatePoint();

		sessionData.setGameInfo(entity);
		testObj.setZeroOneSessionData(sessionData);

		final String forwadKey = testObj.review();

		assertThat(forwadKey, is(ForwardKey.ZERO_ONE.getValue()));

		assertThat(testObj.getRoundIndex(), is(1));
		assertThat(testObj.getRestPoint(), is(0));

		assertThat(testObj.getThrowOneNumber(), is(20));
		assertThat(testObj.getThrowOneCount(), is(3));
		assertThat(testObj.getThrowTwoNumber(), is(20));
		assertThat(testObj.getThrowTwoCount(), is(3));
		assertThat(testObj.getThrowThreeNumber(), is(1));
		assertThat(testObj.getThrowThreeCount(), is(1));
	}

	/**
	 * ゼロワンセッションデータを作成する
	 * 
	 * @return セッションデータ
	 */
	private ZeroOneSessionData createZeroOneSessionData() {

		final String userId = "TEST_0001";
		final ZeroOneSessionData returnData = new ZeroOneSessionData();

		final ZeroOne entity = new ZeroOne(1, userId, ZeroOneGameRules.GAME_701);

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
