package jp.glory.darts.test.application.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import jp.glory.darts.application.user.impl.UserApplicationImpl;
import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.common.exception.BusinessException;
import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.stub.infrastructure.persistence.repository.UserRepositoryStub;

import org.junit.Before;
import org.junit.Test;

public class UserApplicationTest {

	/** テスト対象クラス */
	private UserApplicationImpl testObj = null;

	@Before
	public void beforeTest() {

		testObj = new UserApplicationImpl();

		testObj.setRepository(new UserRepositoryStub());
	}

	@Test
	public void testGetUser() {

		final String userId = UserRepositoryStub.TEST_USER_ID;
		final User entity = testObj.getUser(userId);

		assertThat(entity, is(not(nullValue())));
		assertThat(entity.getUserId(), is(userId));
	}

	@Test
	public void testGetUserNotExistUserId() {

		assertThat(testObj.getUser("NoDataUserId"), is(nullValue()));
	}

	@Test
	public void testGetAllUser() {

		final List<User> resultList = testObj.getAllUser();

		assertThat(resultList, is(not(nullValue())));
		assertThat(resultList.size(), is(3));

		int count = 0;
		for (final User entity : resultList) {

			assertThat(entity.getUserId(), is("USER_ID_" + count));
			assertThat(entity.getName(), is("ユーザ名" + count));
			assertThat(entity.getPassword(), is("password" + count));
			assertThat(entity.isActive(), is(true));

			assertThat(entity.getAuthorityList().size(), is(2));

			assertThat(entity.hasAuthority(AuthorityType.USER), is(true));
			assertThat(entity.hasAuthority(AuthorityType.ADMINISTRATOR), is(true));

			count++;
		}
	}

	@Test
	public void testUpdateUser() {

		final User entity = new User("TEST0001");
		entity.setName("ユーザ名");
		entity.setPassword("password");
		entity.setActive(true);
		entity.addAuthority(AuthorityType.USER);

		try {

			testObj.updateUser(entity);
		} catch (BusinessException e) {

			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testUpdateUserError() {

		final User entity = new User("TEST0001");
		entity.setName("ユーザ名");
		entity.setPassword("password");
		entity.setActive(true);

		try {

			testObj.updateUser(entity);

			fail();
		} catch (BusinessException e) {

			final List<ErrorInfo> errorList = e.getErrorInfoList();
			assertThat(errorList.size(), is(1));
			assertThat(errorList.get(0).getMessageInfo(), is(MessageInfo.ERROR_USER_AUTHORITY));
		} catch (Exception e) {

			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testEntryUser() {

		final User entity = new User("TEST0001");
		entity.setName("ユーザ名");
		entity.setPassword("password");
		entity.setActive(true);
		entity.addAuthority(AuthorityType.USER);

		try {

			testObj.entryUser(entity);
		} catch (BusinessException e) {

			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testEntryUserError() {

		final User entity = new User("TEST0001");
		entity.setName("ユーザ名");
		entity.setPassword("password");
		entity.setActive(true);

		try {

			testObj.entryUser(entity);

			fail();
		} catch (BusinessException e) {

			final List<ErrorInfo> errorList = e.getErrorInfoList();

			assertThat(errorList.size(), is(1));
			assertThat(errorList.get(0).getMessageInfo(), is(MessageInfo.ERROR_USER_AUTHORITY));
		} catch (Exception e) {

			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testEntryDuplicateUserId() {

		final User entity = new User(UserRepositoryStub.TEST_USER_ID);
		entity.setName("ユーザ名");
		entity.setPassword("password");
		entity.addAuthority(AuthorityType.USER);
		entity.setActive(true);

		try {

			testObj.entryUser(entity);

			fail();
		} catch (BusinessException e) {

			final List<ErrorInfo> errorList = e.getErrorInfoList();

			assertThat(errorList.size(), is(1));
			assertThat(errorList.get(0).getMessageInfo(), is(MessageInfo.ERROR_DUPLICATE_USER_ID));
		} catch (Exception e) {

			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testUpdateActiveStatus() {

		try {

			testObj.updateActiveStatus(new ArrayList<String>(), true);
		} catch (Exception e) {

			e.printStackTrace();
			fail();
		}
	}
}
