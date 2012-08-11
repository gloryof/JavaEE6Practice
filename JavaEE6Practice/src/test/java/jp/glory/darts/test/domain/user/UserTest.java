package jp.glory.darts.test.domain.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.user.entity.User;

import org.junit.Test;

/**
 * ユーザエンティティのテストクラス
 * 
 * @author Junki Yamada
 * 
 */
public class UserTest {

	@Test
	public void testInitialize() {

		final String userId = "TEST0001";
		final User entity = new User(userId);

		assertThat(entity.getUserId(), is(userId));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByEmptyUserId() {

		new User("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNullUserId() {

		new User(null);
	}

	@Test
	public void testIsSameBySameId() {

		final String userId = "TEST0001";
		final User baseUser = new User(new String(userId));
		final User conpareUser = new User(new String(userId));

		assertThat(baseUser.isSame(conpareUser), is(true));
	}

	@Test
	public void testIsSmaeByDifferentId() {

		final User baseUser = new User("TEST0001");
		final User conpareUser = new User("TEST0002");

		assertThat(baseUser.isSame(conpareUser), is(false));
	}

	@Test
	public void testIsSameByNullEntity() {

		final User baseUser = new User("TEST0001");

		assertThat(baseUser.isSame(null), is(false));
	}

	@Test
	public void testCopyEntity() {

		final String userId = "TEST0001";
		final String password = "password";
		final String name = "テストユーザ";

		final User entity = new User(userId);
		entity.setPassword(password);
		entity.setName(name);
		entity.addAuthority(AuthorityType.USER);
		entity.addAuthority(AuthorityType.ADMINISTRATOR);

		final User copyEntity = entity.copyEntity();

		assertThat(copyEntity, is(not(nullValue())));
		assertThat((entity != copyEntity), is(true));
		assertThat(copyEntity.getUserId(), is(userId));
		assertThat(copyEntity.getPassword(), is(password));
		assertThat(copyEntity.getName(), is(name));
		assertThat(copyEntity.getAuthorityList().size(), is(2));
		assertThat(copyEntity.hasAuthority(AuthorityType.USER), is(true));
		assertThat(copyEntity.hasAuthority(AuthorityType.ADMINISTRATOR), is(true));
		assertThat(entity.isSame(copyEntity), is(true));
	}

	@Test
	public void testHasAuthority() {

		final User entity = new User("TEST0001");
		entity.addAuthority(AuthorityType.USER);

		assertThat(entity.hasAuthority(AuthorityType.USER), is(true));
	}

	@Test
	public void testHasAuthorityDifferntValue() {

		final User entity = new User("TEST0001");
		entity.addAuthority(AuthorityType.USER);

		assertThat(entity.hasAuthority(AuthorityType.ADMINISTRATOR), is(false));
	}

	@Test
	public void testHasAuthorityNullValue() {

		final User entity = new User("TEST0001");
		entity.addAuthority(AuthorityType.USER);

		assertThat(entity.hasAuthority(null), is(false));
	}

	@Test
	public void testAddAuthority() {

		final User entity = new User("TEST0001");

		final AuthorityType additinalAuthority = AuthorityType.USER;

		assertThat(entity.hasAuthority(additinalAuthority), is(false));

		entity.addAuthority(additinalAuthority);

		assertThat(entity.getAuthorityList().size(), is(1));
		assertThat(entity.hasAuthority(additinalAuthority), is(true));
	}

	@Test
	public void testValidateNoError() {

		final User entity = new User("TEST0001");
		entity.setName("テストユーザ");
		entity.setPassword("password");
		entity.addAuthority(AuthorityType.USER);

		final List<ErrorInfo> errorInfoList = entity.validate();

		assertThat(errorInfoList, is(not(nullValue())));
		assertThat(errorInfoList.size(), is(0));
	}

	@Test
	public void testValidateNameIsNull() {

		final User entity = new User("TEST0001");
		entity.setName(null);
		entity.setPassword("password");
		entity.addAuthority(AuthorityType.USER);

		final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_BLANK;
		final String[] messageParam = new String[] {
			"ユーザ名"
		};
		final ErrorInfo errorInfo = new ErrorInfo(messageInfo, messageParam);

		final List<ErrorInfo> errorInfoList = entity.validate();

		assertThat(errorInfoList, is(not(nullValue())));
		assertThat(errorInfoList.size(), is(1));
		assertThat(errorInfoList.get(0).getMessageValue(), is(errorInfo.getMessageValue()));
	}

	@Test
	public void testValidateNameIsEmpty() {

		final User entity = new User("TEST0001");
		entity.setName("");
		entity.setPassword("password");
		entity.addAuthority(AuthorityType.USER);

		final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_BLANK;
		final String[] messageParam = new String[] {
			"ユーザ名"
		};
		final ErrorInfo errorInfo = new ErrorInfo(messageInfo, messageParam);

		final List<ErrorInfo> errorInfoList = entity.validate();

		assertThat(errorInfoList, is(not(nullValue())));
		assertThat(errorInfoList.size(), is(1));
		assertThat(errorInfoList.get(0).getMessageValue(), is(errorInfo.getMessageValue()));
	}

	@Test
	public void testValidatePasswordIsNull() {

		final User entity = new User("TEST0001");
		entity.setName("ユーザ名");
		entity.setPassword(null);
		entity.addAuthority(AuthorityType.USER);

		final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_BLANK;
		final String[] messageParam = new String[] {
			"パスワード"
		};
		final ErrorInfo errorInfo = new ErrorInfo(messageInfo, messageParam);

		final List<ErrorInfo> errorInfoList = entity.validate();

		assertThat(errorInfoList, is(not(nullValue())));
		assertThat(errorInfoList.size(), is(1));
		assertThat(errorInfoList.get(0).getMessageValue(), is(errorInfo.getMessageValue()));
	}

	@Test
	public void testValidatePasswordIsEmpty() {

		final User entity = new User("TEST0001");
		entity.setName("ユーザ名");
		entity.setPassword("");
		entity.addAuthority(AuthorityType.USER);

		final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_BLANK;
		final String[] messageParam = new String[] {
			"パスワード"
		};
		final ErrorInfo errorInfo = new ErrorInfo(messageInfo, messageParam);

		final List<ErrorInfo> errorInfoList = entity.validate();

		assertThat(errorInfoList, is(not(nullValue())));
		assertThat(errorInfoList.size(), is(1));
		assertThat(errorInfoList.get(0).getMessageValue(), is(errorInfo.getMessageValue()));
	}

	@Test
	public void testValidateNoAuthority() {

		final User entity = new User("TEST0001");
		entity.setName("テストユーザ");
		entity.setPassword("password");

		final MessageInfo messageInfo = MessageInfo.ERROR_USER_AUTHORITY;
		final ErrorInfo errorInfo = new ErrorInfo(messageInfo, null);

		final List<ErrorInfo> errorInfoList = entity.validate();

		assertThat(errorInfoList, is(not(nullValue())));
		assertThat(errorInfoList.size(), is(1));
		assertThat(errorInfoList.get(0).getMessageValue(), is(errorInfo.getMessageValue()));
	}

	@Test
	public void testValidateAllError() {

		final User entity = new User("TEST0001");

		final List<ErrorInfo> errorInfoList = entity.validate();

		assertThat(errorInfoList, is(not(nullValue())));
		assertThat(errorInfoList.size(), is(3));
	}
}
