package jp.glory.darts.stub.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Any;

import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.domain.game.repository.CountUpRepository;

@Any
public class CountUpRepositoryStub implements CountUpRepository {

	/**
	 * ƒXƒ^ƒu‚Å‚Í‰½‚à‚µ‚È‚¢
	 */
	@Override
	public void save(final CountUp entity) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CountUp findByGameId(final long gameId) {

		return new CountUp(gameId, "TEST_USER_ID");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CountUp> findNoThrowingByUserId(final String userId) {

		final List<CountUp> returnList = new ArrayList<>();

		for (int i = 0; i < 3; i++) {

			returnList.add(new CountUp(i, userId));
		}

		return returnList;
	}

}
