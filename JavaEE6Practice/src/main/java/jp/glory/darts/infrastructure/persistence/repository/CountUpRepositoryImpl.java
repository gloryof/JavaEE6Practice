package jp.glory.darts.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import jp.glory.darts.common.DartsConst;
import jp.glory.darts.common.exception.DartsSystemException;
import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.domain.game.repository.CountUpRepository;
import jp.glory.darts.infrastructure.persistence.bean.CountUpTableBean;
import jp.glory.darts.infrastructure.persistence.bean.ThrowingTableBean;
import jp.glory.darts.infrastructure.persistence.util.GameRepositoryUtil;
import jp.glory.darts.infrastructure.persistence.util.SqlConst;

/**
 * カウントアップリポジトリ
 * 
 * @author Junki Yamada
 * 
 */
@Default
public class CountUpRepositoryImpl implements CountUpRepository {

	/** エンティティマネージャ */
	@PersistenceContext(unitName = DartsConst.JPA_UNIT_NAME)
	private EntityManager manager = null;

	/** コンバータ */
	@Inject
	private GameRepositoryUtil util = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(final CountUp entity) {

		if (entity == null) {

			throw new DartsSystemException("ユーザエンティティがNullです");
		}

		final CountUpTableBean tableBean = convertCountUpTableBeanFromEntity(entity);

		final Query seqQuery = manager.createNativeQuery(SqlConst.GAME_SEQ_SQL);
		final Long gameId = (Long) seqQuery.getSingleResult();

		tableBean.setGameId(gameId);

		manager.persist(tableBean);

		final List<ThrowingTableBean> throwingTableList = util.createThrowingList(tableBean.getGameId(),
				entity.getRounds());

		for (final ThrowingTableBean throwingTableBean : throwingTableList) {

			manager.persist(throwingTableBean);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CountUp findByGameId(final long gameId) {

		final CountUpTableBean tableBean = manager.find(CountUpTableBean.class, gameId);

		final CountUp entity = convertCountUpEntityFromTableBean(tableBean);

		final TypedQuery<ThrowingTableBean> throwingResultQuery = manager.createNamedQuery(
				ThrowingTableBean.QUERY_NAME_FIND_BY_GAME_ID, ThrowingTableBean.class);
		throwingResultQuery.setParameter("gameId", gameId);

		final List<ThrowingTableBean> throwingList = throwingResultQuery.getResultList();

		util.setRoundPoint(entity, throwingList);

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CountUp> findNoThrowingByUserId(final String userId) {

		final List<CountUp> returnList = new ArrayList<>();

		final TypedQuery<CountUpTableBean> query = manager.createNamedQuery(
				CountUpTableBean.QUERY_NAME_FIND_BY_USER_ID, CountUpTableBean.class);
		query.setParameter("userId", userId);

		final List<CountUpTableBean> resultList = query.getResultList();
		for (final CountUpTableBean tableBean : resultList) {

			returnList.add(convertCountUpEntityFromTableBean(tableBean));
		}

		return returnList;
	}

	/**
	 * エンティティからカウントアップテーブルBeanに変換する。
	 * 
	 * @param entity エンティティ
	 * @return カウントアップテーブルBean
	 */
	private CountUpTableBean convertCountUpTableBeanFromEntity(final CountUp entity) {

		final CountUpTableBean returnBean = new CountUpTableBean();

		returnBean.setUserId(entity.getUserId());
		returnBean.setTotal(entity.getPoint());
		returnBean.setStats(entity.getStats());

		return returnBean;
	}

	/**
	 * テーブルBeanからカウントアップエンティティに変換する。<br/>
	 * ポイントの設定は行わない。
	 * 
	 * @param tableBean テーブルBean
	 * @return カウントアップエンティティ
	 */
	private CountUp convertCountUpEntityFromTableBean(final CountUpTableBean tableBean) {

		final CountUp entity = new CountUp(tableBean.getGameId(), tableBean.getUserId());

		entity.setPlayDate(tableBean.getGameTime());
		entity.setPoint(tableBean.getTotal());

		return entity;
	}
}
