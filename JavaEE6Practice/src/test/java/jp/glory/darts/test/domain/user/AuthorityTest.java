package jp.glory.darts.test.domain.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.user.value.Authority;

import org.junit.Test;

public class AuthorityTest {

	@Test
	public void testInitialize() {

		final AuthorityType type = AuthorityType.ADMINISTRATOR;

		final Authority valueObject = new Authority(type);

		assertThat(valueObject.getAuthorityType(), is(type));
	}

	@Test
	public void testIsSame() {

		final AuthorityType type = AuthorityType.ADMINISTRATOR;

		final Authority baseObject = new Authority(type);
		final Authority compareObject = new Authority(type);

		assertThat(baseObject.isSameValue(compareObject), is(true));
	}

	@Test
	public void testIsSameDiffrentValue() {

		final Authority baseObject = new Authority(AuthorityType.ADMINISTRATOR);
		final Authority compareObject = new Authority(AuthorityType.USER);

		assertThat(baseObject.isSameValue(compareObject), is(false));
	}

	@Test
	public void testIsSameNullValue() {

		final Authority baseObject = new Authority(AuthorityType.ADMINISTRATOR);

		assertThat(baseObject.isSameValue(null), is(false));
	}

	@Test
	public void testCopyValue() {

		final Authority baseObject = new Authority(AuthorityType.ADMINISTRATOR);

		final Authority copyObject = baseObject.copyValue();

		assertThat(copyObject, is(not(nullValue())));
		assertThat((baseObject != copyObject), is(true));
		assertThat(baseObject.isSameValue(baseObject), is(true));
		assertThat(baseObject.getAuthorityType().equals(copyObject.getAuthorityType()), is(true));
	}
}
