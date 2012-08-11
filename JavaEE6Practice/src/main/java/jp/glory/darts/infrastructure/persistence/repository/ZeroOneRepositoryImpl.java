package jp.glory.darts.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import jp.glory.darts.common.DartsConst;
import jp.glory.darts.common.exception.DartsSystemException;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;
import jp.glory.darts.domain.game.repository.ZeroOneRepository;
import jp.glory.darts.infrastructure.persistence.bean.ThrowingTableBean;
import jp.glory.darts.infrastructure.persistence.bean.ZeroOneTableBean;
import jp.glory.darts.infrastructure.persistence.util.GameRepositoryUtil;
import jp.glory.darts.infrastructure.persistence.util.SqlConst;

/**
 * �[���������|�W�g���N���X
 * 
 * @author Junki Yamada
 * 
 */
public class ZeroOneRepositoryImpl implements ZeroOneRepository {

	/** �G���e�B�e�B�}�l�[�W�� */
	@PersistenceContext(unitName = DartsConst.JPA_UNIT_NAME)
	private EntityManager manager = null;

	/** �R���o�[�^ */
	@Inject
	private GameRepositoryUtil util = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(final ZeroOne entity) {

		if (entity == null) {

			throw new DartsSystemException("���[�U�G���e�B�e�B��Null�ł�");
		}

		final ZeroOneTableBean tableBean = convertZeroOneTableBeanFromEntity(entity);

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
	public ZeroOne findByGameId(final long gameId) {

		final ZeroOneTableBean tableBean = manager.find(ZeroOneTableBean.class, gameId);

		final ZeroOne entity = convertZeroOneEntityFromTableBean(tableBean);

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
	public List<ZeroOne> findNoThrowingByUserId(final String userId, final ZeroOneGameRules rule) {

		final List<ZeroOne> returnList = new ArrayList<>();

		final TypedQuery<ZeroOneTableBean> query = manager.createNamedQuery(
				ZeroOneTableBean.QUERY_NAME_FIND_BY_USER_ID, ZeroOneTableBean.class);
		query.setParameter("userId", userId);
		query.setParameter("gameType", rule.getTypeCode());

		final List<ZeroOneTableBean> dbResultList = query.getResultList();
		for (final ZeroOneTableBean tableBean : dbResultList) {

			returnList.add(convertZeroOneEntityFromTableBean(tableBean));
		}

		return returnList;
	}

	/**
	 * �G���e�B�e�B����[�������e�[�u��Bean�ɕϊ�����B
	 * 
	 * @param entity �G���e�B�e�B
	 * @return �[�������e�[�u��Bean
	 */
	private ZeroOneTableBean convertZeroOneTableBeanFromEntity(final ZeroOne entity) {

		final ZeroOneTableBean returnBean = new ZeroOneTableBean();

		returnBean.setGameId(entity.getGameId());
		returnBean.setUserId(entity.getUserId());
		returnBean.setGameType(entity.getRule().getTypeCode());
		returnBean.setRestPoint(entity.getRestPoint());
		returnBean.setEndRound(entity.getEndRoundIndex());
		returnBean.setStats(entity.getStats());

		return returnBean;
	}

	/**
	 * �e�[�u��Bean����[�������G���e�B�e�B�ɕϊ�����B<br/>
	 * �e���E���h�̃|�C���g�̐ݒ�͍s��Ȃ��B
	 * 
	 * @param tableBean �e�[�u��Bean
	 * @return �[�������G���e�B�e�B
	 */
	private ZeroOne convertZeroOneEntityFromTableBean(final ZeroOneTableBean tableBean) {

		final ZeroOne entity = new ZeroOne(tableBean.getGameId(), tableBean.getUserId(),
				ZeroOneGameRules.convertGameRule(tableBean.getGameType()));

		entity.setRestPoint(tableBean.getRestPoint());
		entity.setEndRoundIndex(tableBean.getEndRound());
		entity.setPlayDate(tableBean.getGameTime());

		return entity;
	}
}
