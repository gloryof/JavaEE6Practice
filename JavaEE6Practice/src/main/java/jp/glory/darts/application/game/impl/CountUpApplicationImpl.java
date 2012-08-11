package jp.glory.darts.application.game.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jp.glory.darts.application.game.CountUpApplication;
import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.domain.game.repository.CountUpRepository;

/**
 * カウントアップアプリケーションクラス
 * 
 * @author Junki Yamada
 * 
 */
@Stateless
@LocalBean
public class CountUpApplicationImpl implements CountUpApplication {

	/** カウントアップリポジトリ */
	@Inject
	private CountUpRepository repository = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void entryResult(final CountUp entity) {

		repository.save(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CountUp findByGameId(final long gameId) {

		return repository.findByGameId(gameId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CountUp> findNoThrowingByUserId(final String userId) {

		return repository.findNoThrowingByUserId(userId);
	}

	public void setRepository(final CountUpRepository repository) {
		this.repository = repository;
	}

}
