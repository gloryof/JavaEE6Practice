package jp.glory.darts.application.game.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jp.glory.darts.application.game.ZeroOneApplication;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.domain.game.repository.ZeroOneRepository;

/**
 * ゼロワンアプリケーションクラス
 * 
 * @author Junki Yamada
 * 
 */
@Stateless
@LocalBean
public class ZeroOneApplicationImpl implements ZeroOneApplication {

	/** ゼロワンリポジトリ */
	@Inject
	private ZeroOneRepository repository = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void entryResult(final ZeroOne entity) {

		repository.save(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ZeroOne findByGameId(final long gameId) {

		return repository.findByGameId(gameId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ZeroOne> findHistoryInfo(final String userId, final ZeroOneGameRules rule) {

		return repository.findNoThrowingByUserId(userId, rule);
	}

	public void setRepository(final ZeroOneRepository repository) {
		this.repository = repository;
	}

}
