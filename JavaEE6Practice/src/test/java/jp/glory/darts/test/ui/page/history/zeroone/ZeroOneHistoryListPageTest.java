package jp.glory.darts.test.ui.page.history.zeroone;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.List;

import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.stub.application.game.zeroone.ZeroOneApplicationStub;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.UserSessionData;
import jp.glory.darts.ui.page.history.util.HistoryUtil;
import jp.glory.darts.ui.page.history.zeroone.bean.ZeroOneHistoryBean;
import jp.glory.darts.ui.page.history.zeroone.impl.ZeroOneHistoryListPageImpl;

import org.junit.Before;
import org.junit.Test;

public class ZeroOneHistoryListPageTest {

	/** テスト対象オブジェクト */
	private ZeroOneHistoryListPageImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new ZeroOneHistoryListPageImpl();
		testObj.setApplication(new ZeroOneApplicationStub());
		testObj.setUtil(new HistoryUtil());
		testObj.setSessionData(new UserSessionData());
	}

	@Test
	public void testInit() {

		final UserSessionData sessionData = new UserSessionData();
		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_501;
		sessionData.setUserId("USER_ID");
		testObj.setSessionData(sessionData);

		final String forwardKey = testObj.init(rule.getTypeCode());

		assertThat(forwardKey, is(ForwardKey.HISTORY_ZERO_ONE_LIST.getValue()));

		final List<ZeroOneHistoryBean> resultList = testObj.getHistoryBeanList();

		assertThat(resultList, is(not(nullValue())));
		assertThat(resultList.size(), is(3));

		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2012);
		cal.set(Calendar.MONTH, 3);
		cal.set(Calendar.DAY_OF_MONTH, 12);
		cal.set(Calendar.HOUR_OF_DAY, 10);
		cal.set(Calendar.MINUTE, 15);
		cal.set(Calendar.MILLISECOND, 0);

		final int[] restPoint = {
				48, 96, 144
		};

		final double[] stats = {
				30.2, 27, 23.8
		};

		int index = 0;
		for (final ZeroOneHistoryBean historyBean : resultList) {

			cal.set(Calendar.SECOND, index);

			assertThat(Long.valueOf(historyBean.getGameId()), is(Long.valueOf(index)));
			assertThat(historyBean.getGameTypeCode(), is(rule.getTypeCode()));
			assertThat(historyBean.getSeqNo(), is(index + 1));
			assertThat(historyBean.getPlayDate(), is(cal.getTime()));
			assertThat(historyBean.getEndRoundIndex(), is(rule.getRound() - 1));
			assertThat(Double.valueOf(historyBean.getStats()), is(Double.valueOf(stats[index])));
			assertThat(historyBean.getRestPoint(), is(restPoint[index]));

			index++;
		}
	}
}
