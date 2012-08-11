package jp.glory.darts.stub.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Any;

import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.domain.user.repository.UserRepository;

/**
 * ���[�U���|�W�g���X�^�u
 * 
 * @author Junki Yamada
 * 
 */
@Any
public class UserRepositoryStub implements UserRepository {

	/** �e�X�g���[�UID */
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
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	@Override
	public List<User> findAll() {

		final int LIST_SIZE = 3;

		final List<User> returnList = new ArrayList<>();

		for (int i = 0; i < LIST_SIZE; i++) {

			final User entity = new User("USER_ID_" + i);
			entity.setName("���[�U��" + i);
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
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	/**
	 * �e�X�g���[�U�G���e�B�e�B���쐬����
	 * 
	 * @return ���[�U�G���e�B�e�B
	 */
	private User createTestUser() {

		final User entity = new User(UserRepositoryStub.TEST_USER_ID);

		entity.setName("�e�X�g���[�U");
		entity.setPassword("password");
		entity.setActive(true);

		entity.addAuthority(AuthorityType.USER);
		entity.addAuthority(AuthorityType.ADMINISTRATOR);

		return entity;
	}
}
