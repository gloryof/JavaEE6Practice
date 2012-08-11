package jp.glory.darts.test.ui.page.admin.menu;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import jp.glory.darts.ui.page.admin.menu.impl.AdminMenuPageImpl;
import jp.glory.darts.ui.page.common.ForwardKey;

import org.junit.Before;
import org.junit.Test;

public class AdminMenuPageTest {

	/** テストオブジェクト */
	private AdminMenuPageImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new AdminMenuPageImpl();
	}

	@Test
	public void testInit() {

		assertThat(testObj.initPage(), is(ForwardKey.ADMIN_MENU.getValue()));
	}

}
