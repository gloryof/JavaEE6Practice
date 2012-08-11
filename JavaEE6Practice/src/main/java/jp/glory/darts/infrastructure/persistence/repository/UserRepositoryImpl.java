package jp.glory.darts.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import jp.glory.darts.common.DartsConst;
import jp.glory.darts.common.exception.DartsSystemException;
import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.domain.user.repository.UserRepository;
import jp.glory.darts.infrastructure.persistence.bean.AuthorityTableBean;
import jp.glory.darts.infrastructure.persistence.bean.UserDataTableBean;

@Default
public class UserRepositoryImpl implements UserRepository {

	/** エンティティマネージャ */
	@PersistenceContext(unitName = DartsConst.JPA_UNIT_NAME)
	private EntityManager manager = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findByUserId(final String userId) {

		final TypedQuery<UserDataTableBean> query = manager
				.createNamedQuery(UserDataTableBean.QUERY_NAME_FIND_BY_USER_ID, UserDataTableBean.class);
		query.setParameter("userId", userId);
		final List<UserDataTableBean> resultList = query.getResultList();

		if (resultList.size() < 1) {

			return null;
		}

		return convertEntity(resultList.get(0));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(final User entity) {

		if (entity == null) {

			throw new DartsSystemException("ユーザエンティティがNullです");
		}

		saveUserData(entity);

		saveUserAuthorityList(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> findAll() {

		final TypedQuery<UserDataTableBean> query = manager.createNamedQuery(UserDataTableBean.QUERY_NAME_FIND_ALL_USER, UserDataTableBean.class);
		final List<UserDataTableBean> resultList = query.getResultList();

		final List<User> entityList = new ArrayList<>();

		for (final UserDataTableBean data : resultList) {

			entityList.add(convertEntity(data));
		}

		return entityList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateActiveStatus(final List<String> userIdList, final boolean activeStatus) {

		final Query query = manager.createNamedQuery(UserDataTableBean.QUERY_NAME_UPDATE_ACTIVE);
		query.setParameter("activateValue", activeStatus);
		query.setParameter("userIdList", userIdList);

		query.executeUpdate();
	}

	/**
	 * テーブルデータをエンティティに変換する
	 * 
	 * @param tableData テーブルデータ
	 * @return エンティティ
	 */
	private User convertEntity(final UserDataTableBean tableData) {

		if (tableData == null) {

			return null;
		}

		final User entity = new User(tableData.getUserId());

		entity.setName(tableData.getName());
		entity.setPassword(tableData.getPassword());
		entity.setActive(tableData.isActive());

		for (final AuthorityTableBean authorityData : getAuthorityListByUserId(tableData.getUserId())) {

			if (authorityData.isActivate()) {

				entity.addAuthority(AuthorityType.createAuthorityType(authorityData.getAuthorityType()));
			}
		}

		return entity;
	}

	/**
	 * UserDataテーブルにデータを保存する
	 * 
	 * @param entity ユーザエンティティ
	 */
	private void saveUserData(final User entity) {

		UserDataTableBean user = manager.find(UserDataTableBean.class, entity.getUserId());

		if (user == null) {

			user = new UserDataTableBean();
			user.setUserId(entity.getUserId());
		}

		user.setName(entity.getName());
		user.setPassword(entity.getPassword());
		user.setActive(entity.isActive());

		manager.merge(user);

	}

	/**
	 * ユーザIDにひもづく権限情報リストを取得する
	 * 
	 * @param userId
	 * @return 権限情報リスト
	 */
	private List<AuthorityTableBean> getAuthorityListByUserId(final String userId) {

		final TypedQuery<AuthorityTableBean> query = manager.createNamedQuery(AuthorityTableBean.QUERY_NAME_FIND_BY_USER_ID,
				AuthorityTableBean.class);
		query.setParameter("userId", userId);

		return query.getResultList();
	}

	/**
	 * ユーザの権限を全て更新する
	 * 
	 * @param entity ユーザエンティティ
	 */
	private void saveUserAuthorityList(final User entity) {

		for (final AuthorityType type : AuthorityType.values()) {

			saveAuthorityData(entity.getUserId(), type, entity.hasAuthority(type));
		}

	}

	/**
	 * 権限テーブルにデータを保存する
	 * 
	 * @param userId ユーザID
	 * @param type 権限
	 * @param hasAuthority 権限有無
	 */
	private void saveAuthorityData(final String userId, final AuthorityType type, final boolean hasAuthority) {

		final TypedQuery<AuthorityTableBean> query = manager.createNamedQuery(
				AuthorityTableBean.QUERY_NAME_FIND_BY_USER_AUTHORITY, AuthorityTableBean.class);
		query.setParameter("userId", userId);
		query.setParameter("authorityType", type.getCodeValue());
		query.setMaxResults(1);

		AuthorityTableBean data = null;

		final List<AuthorityTableBean> dataList = query.getResultList();

		if (0 < dataList.size()) {

			data = dataList.get(0);
		} else {
			data = new AuthorityTableBean();
			data.setUserId(userId);
			data.setAuthorityType(type.getCodeValue());
		}

		data.setActivate(hasAuthority);

		manager.merge(data);
	}

	public void setManager(final EntityManager manager) {
		this.manager = manager;
	}
}
