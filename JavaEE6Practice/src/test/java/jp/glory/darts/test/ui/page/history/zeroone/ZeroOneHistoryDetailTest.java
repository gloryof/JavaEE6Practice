package jp.glory.darts.test.ui.page.history.zeroone;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.stub.application.game.zeroone.ZeroOneApplicationStub;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.history.bean.HistoryRoundBean;
import jp.glory.darts.ui.page.history.bean.HistoryThrowingBean;
import jp.glory.darts.ui.page.history.util.HistoryUtil;
import jp.glory.darts.ui.page.history.zeroone.bean.ZeroOneHistoryBean;
import jp.glory.darts.ui.page.history.zeroone.impl.ZeroOneHistoryDetailPageImpl;

import org.junit.Before;
import org.junit.Test;

public class ZeroOneHistoryDetailTest {

	/** テスト対象オブジェクト */
	private ZeroOneHistoryDetailPageImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new ZeroOneHistoryDetailPageImpl();
		testObj.setApplication(new ZeroOneApplicationStub());
		testObj.setUtil(new HistoryUtil());
	}

	@Test
	public void testInit() {

		final ZeroOneGameRules expectGameRule = ZeroOneGameRules.GAME_501;
		final long gameId = 10;

		final String forwardKey = testObj.init(gameId);

		assertThat(forwardKey, is(ForwardKey.HISTORY_ZERO_ONE_DETAIL.getValue()));

		final ZeroOneHistoryBean zeroOneBean = testObj.getZeroOneBean();

		assertThat(zeroOneBean, is(not(nullValue())));
		assertThat(zeroOneBean.getGameTypeCode(), is(expectGameRule.getTypeCode()));
		assertThat(zeroOneBean.getGameId(), is(gameId));
		assertThat(zeroOneBean.getPlayDate(), is(ZeroOneApplicationStub.getDefaultStubData()));
		assertThat(zeroOneBean.getEndRoundIndex(), is(expectGameRule.getRound() - 1));
		assertThat(Double.valueOf(zeroOneBean.getStats()), is(Double.valueOf(14)));
		assertThat(zeroOneBean.getRestPoint(), is(291));

		final List<HistoryRoundBean> roundBeanList = testObj.getRoundList();

		assertThat(roundBeanList, is(not(nullValue())));
		assertThat(roundBeanList.size(), is(expectGameRule.getRound()));

		int index = 0;
		for (final HistoryRoundBean roundBean : roundBeanList) {

			assertThat(roundBean.getIndex(), is(index));
			assertThat(roundBean.getPoint(), is(14));

			final ThrowingNumber[] numbers = {
					ThrowingNumber.ONE, ThrowingNumber.TWO, ThrowingNumber.THREE
			};

			final ThrowingCount[] counts = {
					ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
			};

			int throwingIndex = 0;
			for (final HistoryThrowingBean throwing : roundBean.getThrowiList()) {

				assertThat(throwing.getIndex(), is(throwingIndex));

				final ThrowingNumber number = numbers[throwingIndex];
				final ThrowingCount count = counts[throwingIndex];
				assertThat(throwing.getNumber(), is(number));
				assertThat(throwing.getCount(), is(count));

				assertThat(throwing.getPoint(), is(number.getNumber() * count.getCount()));

				throwingIndex++;
			}
			index++;
		}
	}
}
