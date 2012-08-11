package jp.glory.darts.test.ui.page.history.countup;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.List;

import jp.glory.darts.stub.application.game.countup.CountUpApplicationStub;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.UserSessionData;
import jp.glory.darts.ui.page.history.countup.bean.CountUpHistoryBean;
import jp.glory.darts.ui.page.history.countup.impl.CountUpHistoryListPageImpl;
import jp.glory.darts.ui.page.history.util.HistoryUtil;

import org.junit.Before;
import org.junit.Test;

public class CountUpHistoryListPageTest {

	/** テスト対象オブジェクト */
	private CountUpHistoryListPageImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new CountUpHistoryListPageImpl();
		testObj.setApplication(new CountUpApplicationStub());
		testObj.setSessionData(new UserSessionData());
		testObj.setUtil(new HistoryUtil());
	}

	@Test
	public void testInit() {

		final UserSessionData sessionData = new UserSessionData();
		sessionData.setUserId("USER_ID");
		testObj.setSessionData(sessionData);

		final String forwardKey = testObj.init();

		assertThat(forwardKey, is(ForwardKey.HISTORY_COUNTUP_LIST.getValue()));

		final List<CountUpHistoryBean> resultList = testObj.getHistoryBeanList();
		assertThat(resultList, is(not(nullValue())));
		assertThat(resultList.size(), is(3));

		final int[] totalArray = {
				48, 96, 144
		};
		final double[] statsArray = {
				6, 12, 18
		};

		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2012);
		cal.set(Calendar.MONTH, 3);
		cal.set(Calendar.DAY_OF_MONTH, 12);
		cal.set(Calendar.HOUR_OF_DAY, 10);
		cal.set(Calendar.MINUTE, 15);
		cal.set(Calendar.MILLISECOND, 0);

		int index = 0;
		for (final CountUpHistoryBean bean : resultList) {

			cal.set(Calendar.SECOND, index);

			assertThat(Long.valueOf(bean.getGameId()), is(Long.valueOf(index)));
			assertThat(bean.getSeqNo(), is(index + 1));
			assertThat(bean.getPlayDate(), is(cal.getTime()));
			assertThat(bean.getTotal(), is(totalArray[index]));
			assertThat(Double.valueOf(bean.getStats()), is(Double.valueOf(statsArray[index])));

			index++;
		}
	}
}
