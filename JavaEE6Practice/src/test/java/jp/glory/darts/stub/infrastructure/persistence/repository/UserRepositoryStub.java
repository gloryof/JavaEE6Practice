package jp.glory.darts.stub.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Any;

import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.domain.user.repository.UserRepository;

/**
 * ユーザリポジトリスタブ
 * 
 * @author Junki Yamada
 * 
 */
@Any
public class UserRepositoryStub implements UserRepository {

	/** テストユーザID */
	public static final String TEST_USER_ID = "TEST_USER_ID";

	@Override
	public User findByUserId(final String userId) {

		if (UserRepositoryStub.TEST_USER_ID.equals(userId)) {

			return createTestUser();
		}

		return null;
	}

	@Override
	public void save(final User entity) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public List<User> findAll() {

		final int LIST_SIZE = 3;

		final List<User> returnList = new ArrayList<>();

		for (int i = 0; i < LIST_SIZE; i++) {

			final User entity = new User("USER_ID_" + i);
			entity.setName("ユーザ名" + i);
			entity.setPassword("password" + i);
			entity.setActive(true);

			entity.addAuthority(AuthorityType.USER);
			entity.addAuthority(AuthorityType.ADMINISTRATOR);

			returnList.add(entity);
		}

		return returnList;
	}

	@Override
	public void updateActiveStatus(final List<String> userIdList, final boolean activeStatus) {
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * テストユーザエンティティを作成する
	 * 
	 * @return ユーザエンティティ
	 */
	private User createTestUser() {

		final User entity = new User(UserRepositoryStub.TEST_USER_ID);

		entity.setName("テストユーザ");
		entity.setPassword("password");
		entity.setActive(true);

		entity.addAuthority(AuthorityType.USER);
		entity.addAuthority(AuthorityType.ADMINISTRATOR);

		return entity;
	}
}
