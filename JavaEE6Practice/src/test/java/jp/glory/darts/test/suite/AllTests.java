package jp.glory.darts.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		CommonTest.class, ApplicationTest.class, DomainTest.class, UiTest.class
})
public class AllTests {

}
