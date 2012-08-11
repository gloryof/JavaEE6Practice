package jp.glory.darts.test.application.game;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import jp.glory.darts.application.game.impl.CountUpApplicationImpl;
import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.stub.infrastructure.persistence.repository.CountUpRepositoryStub;

import org.junit.Before;
import org.junit.Test;

public class CountUpApplicationTest {

	/** テスト対象オブジェクト */
	private CountUpApplicationImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new CountUpApplicationImpl();
		testObj.setRepository(new CountUpRepositoryStub());
	}

	@Test
	public void testEntryResult() {

		testObj.entryResult(new CountUp(0, "TEST_USER_0000"));
	}

	@Test
	public void testFindByGameId() {

		final CountUp entity = testObj.findByGameId(1);

		assertThat(entity, is(not(nullValue())));
	}

	@Test
	public void testFindNoThrowingByUserId() {

		final List<CountUp> resultList = testObj.findNoThrowingByUserId("TEST_USER");

		assertThat(resultList, is(not(nullValue())));
	}
}
