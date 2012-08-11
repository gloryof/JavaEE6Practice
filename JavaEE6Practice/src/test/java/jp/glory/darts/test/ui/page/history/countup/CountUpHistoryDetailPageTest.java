package jp.glory.darts.test.ui.page.history.countup;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.stub.application.game.countup.CountUpApplicationStub;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.history.bean.HistoryRoundBean;
import jp.glory.darts.ui.page.history.bean.HistoryThrowingBean;
import jp.glory.darts.ui.page.history.countup.bean.CountUpHistoryBean;
import jp.glory.darts.ui.page.history.countup.impl.CountUpHistoryDetailPageImpl;
import jp.glory.darts.ui.page.history.util.HistoryUtil;

import org.junit.Before;
import org.junit.Test;

public class CountUpHistoryDetailPageTest {

	/** テスト対象オブジェクト */
	private CountUpHistoryDetailPageImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new CountUpHistoryDetailPageImpl();
		testObj.setApplicatioin(new CountUpApplicationStub());
		testObj.setUtil(new HistoryUtil());
	}

	@Test
	public void testInit() {

		final long gameId = 10;
		final String forwadKey = testObj.init(gameId);

		assertThat(forwadKey, is(ForwardKey.HISTORY_COUNTUP_DETAIL.getValue()));

		final CountUpHistoryBean countUpBean = testObj.getCountUpBean();
		assertThat(countUpBean, is(not(nullValue())));
		assertThat(countUpBean.getGameId(), is(gameId));
		assertThat(countUpBean.getTotal(), is(112));
		assertThat(Double.valueOf(countUpBean.getStats()), is(Double.valueOf(14)));
		assertThat(countUpBean.getPlayDate(), is(CountUpApplicationStub.getDefaultStubData()));

		final List<HistoryRoundBean> roundList = testObj.getRoundList();
		assertThat(roundList, is(not(nullValue())));
		assertThat(roundList.size(), is(GameConst.COUNT_UP_ROUND_COUNT));

		int index = 0;
		for (final HistoryRoundBean round : roundList) {

			assertThat(round, is(not(nullValue())));

			assertThat(round.getIndex(), is(index));
			assertThat(round.getPoint(), is(14));

			final List<HistoryThrowingBean> throwingList = round.getThrowiList();

			assertThat(throwingList, is(not(nullValue())));
			assertThat(throwingList.size(), is(GameConst.THROW_COUNT));

			final ThrowingNumber[] numbers = {
					ThrowingNumber.ONE, ThrowingNumber.TWO, ThrowingNumber.THREE
			};

			final ThrowingCount[] counts = {
					ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
			};

			int throwingIndex = 0;
			for (final HistoryThrowingBean throwing : throwingList) {

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
