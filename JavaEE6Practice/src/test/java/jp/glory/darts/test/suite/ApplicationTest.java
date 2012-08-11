package jp.glory.darts.test.suite;

import jp.glory.darts.test.application.game.CountUpApplicationTest;
import jp.glory.darts.test.application.game.ZeroOneApplicationTest;
import jp.glory.darts.test.application.user.UserApplicationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		UserApplicationTest.class, CountUpApplicationTest.class, ZeroOneApplicationTest.class
})
public class ApplicationTest {

}
