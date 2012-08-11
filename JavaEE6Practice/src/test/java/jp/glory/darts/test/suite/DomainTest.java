package jp.glory.darts.test.suite;

import jp.glory.darts.test.domain.game.CountUpTest;
import jp.glory.darts.test.domain.game.RoundTest;
import jp.glory.darts.test.domain.game.ThrowingTest;
import jp.glory.darts.test.domain.game.ZeroOneTest;
import jp.glory.darts.test.domain.user.AuthorityTest;
import jp.glory.darts.test.domain.user.UserTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		UserTest.class, AuthorityTest.class, ThrowingTest.class, RoundTest.class, CountUpTest.class, ZeroOneTest.class
})
public class DomainTest {

}
