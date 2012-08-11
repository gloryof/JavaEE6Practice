package jp.glory.darts.stub.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Any;

import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.domain.game.repository.ZeroOneRepository;

/**
 * ゼロワンリポジトリスタブ
 * 
 * @author Junki Yamada
 * 
 */
@Any
public class ZeroOneRepositoryStub implements ZeroOneRepository {

	/**
	 * スタブでは何もしない
	 */
	@Override
	public void save(final ZeroOne entity) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ZeroOne findByGameId(final long gameId) {

		return new ZeroOne(gameId, "TEST_USER", ZeroOneGameRules.GAME_301);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ZeroOne> findNoThrowingByUserId(final String userId, final ZeroOneGameRules rule) {

		final List<ZeroOne> returnList = new ArrayList<>();

		for (int i = 0; i < 3; i++) {

			returnList.add(new ZeroOne(i, userId, rule));
		}

		return returnList;
	}
}
