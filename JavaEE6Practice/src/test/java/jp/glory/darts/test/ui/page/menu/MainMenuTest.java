package jp.glory.darts.test.ui.page.menu;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import jp.glory.darts.stub.ui.page.util.JsfUtilStub;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.menu.impl.MainMenuPageImpl;

import org.junit.Before;
import org.junit.Test;

public class MainMenuTest {

	/** テスト対象オブジェクト */
	private MainMenuPageImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new MainMenuPageImpl();
		testObj.setUtil(new JsfUtilStub());
	}

	@Test
	public void testInit() {

		assertThat(testObj.init(), is(ForwardKey.MAIN_MENU.getValue()));
	}

	@Test
	public void testLogout() {

		assertThat(testObj.logout(), is(ForwardKey.INDEX_PAGE.getValue()));
	}
}
