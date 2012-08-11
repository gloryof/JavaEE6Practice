package jp.glory.darts.test.ui.page.index;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.stub.application.user.UserApplicationStub;
import jp.glory.darts.stub.ui.page.menu.MainMenuStub;
import jp.glory.darts.stub.ui.page.util.JsfUtilStub;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.common.UserSessionData;
import jp.glory.darts.ui.page.index.impl.IndexPageImpl;

import org.junit.Before;
import org.junit.Test;

public class IndexPageTest {

	/** テストオブジェクト */
	private IndexPageImpl testObj = null;

	private JsfUtilStub jsfUtil = null;

	@Before
	public void beforeTest() {

		testObj = new IndexPageImpl();
		testObj.setApplication(new UserApplicationStub());
		testObj.setSessionData(new UserSessionData());
		testObj.setMainMenuPage(new MainMenuStub());

		jsfUtil = new JsfUtilStub();
		testObj.setJsfUtill(jsfUtil);
	}

	@Test
	public void testLoginOk() {

		testObj.setUserId(UserApplicationStub.TEST_USER_ID);
		testObj.setPassword(UserApplicationStub.TEST_USER_PASSWORD);

		final String forwardKey = testObj.login();

		assertThat(forwardKey, is(ForwardKey.MAIN_MENU.getValue()));

		final UserSessionData sessionData = testObj.getSessionData();
		assertThat(sessionData.getUserId(), is(UserApplicationStub.TEST_USER_ID));
		assertThat(sessionData.getName(), is(UserApplicationStub.TEST_USER_NAME));
		assertThat(sessionData.getAuthorityList().contains(AuthorityType.ADMINISTRATOR), is(true));
	}

	@Test
	public void testLoginPasswordDiffrent() {

		testObj.setUserId(UserApplicationStub.TEST_USER_ID);
		testObj.setPassword("ngPassword");

		final String forwardKey = testObj.login();

		assertThat(forwardKey, is(ForwardKey.INDEX_PAGE.getValue()));

		assertThat(forwardKey, is(ForwardKey.INDEX_PAGE.getValue()));
		assertThat(jsfUtil.getErrorCount(), is(1));
		assertThat(jsfUtil.getByIndex(0).getMessageInfo(), is(MessageInfo.ERROR_LOGIN));
	}

	@Test
	public void testLoginNgNoUser() {

		testObj.setUserId("NODATA");
		testObj.setPassword(UserApplicationStub.TEST_USER_PASSWORD);

		final String forwardKey = testObj.login();

		assertThat(forwardKey, is(ForwardKey.INDEX_PAGE.getValue()));
		assertThat(jsfUtil.getErrorCount(), is(1));
		assertThat(jsfUtil.getByIndex(0).getMessageInfo(), is(MessageInfo.ERROR_LOGIN));
	}
}
