package jp.glory.darts.test.suite;

import jp.glory.darts.test.ui.page.admin.menu.AdminMenuPageTest;
import jp.glory.darts.test.ui.page.admin.user.EditUserPageTest;
import jp.glory.darts.test.ui.page.admin.user.UserListPageTest;
import jp.glory.darts.test.ui.page.admin.user.common.UserDataConverterTest;
import jp.glory.darts.test.ui.page.common.SessionUserDataTest;
import jp.glory.darts.test.ui.page.game.countup.CountUpPageTest;
import jp.glory.darts.test.ui.page.game.countup.CountUpResultPageTest;
import jp.glory.darts.test.ui.page.game.zeroone.ZeroOnePageTest;
import jp.glory.darts.test.ui.page.game.zeroone.ZeroOneResultPageTest;
import jp.glory.darts.test.ui.page.history.countup.CountUpHistoryDetailPageTest;
import jp.glory.darts.test.ui.page.history.countup.CountUpHistoryListPageTest;
import jp.glory.darts.test.ui.page.history.zeroone.ZeroOneHistoryDetailTest;
import jp.glory.darts.test.ui.page.history.zeroone.ZeroOneHistoryListPageTest;
import jp.glory.darts.test.ui.page.index.IndexPageTest;
import jp.glory.darts.test.ui.page.menu.MainMenuTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		IndexPageTest.class,
		MainMenuTest.class,
		AdminMenuPageTest.class,
		SessionUserDataTest.class,
		UserListPageTest.class,
		UserDataConverterTest.class,
		EditUserPageTest.class,
		CountUpPageTest.class,
		CountUpResultPageTest.class,
		CountUpHistoryListPageTest.class,
		CountUpHistoryDetailPageTest.class,
		ZeroOnePageTest.class,
		ZeroOneResultPageTest.class,
		ZeroOneHistoryListPageTest.class,
		ZeroOneHistoryDetailTest.class
})
public class UiTest {

}
