package jp.glory.darts.test.application.game;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import jp.glory.darts.application.game.impl.ZeroOneApplicationImpl;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.stub.infrastructure.persistence.repository.ZeroOneRepositoryStub;

import org.junit.Before;
import org.junit.Test;

public class ZeroOneApplicationTest {

	/** テスト対象オブジェクト */
	private ZeroOneApplicationImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new ZeroOneApplicationImpl();
		testObj.setRepository(new ZeroOneRepositoryStub());
	}

	@Test
	public void testEntryResult() {

		testObj.entryResult(new ZeroOne(1, "TEST_USER", ZeroOneGameRules.GAME_301));
	}

	@Test
	public void testFindByGameId() {

		final ZeroOne entity = testObj.findByGameId(1);

		assertThat(entity, is(not(nullValue())));
	}

	@Test
	public void testFindNoThrowingByUserId() {

		final List<ZeroOne> resultList = testObj.findHistoryInfo("TEST_USER", ZeroOneGameRules.GAME_301);

		assertThat(resultList, is(not(nullValue())));
	}
}
