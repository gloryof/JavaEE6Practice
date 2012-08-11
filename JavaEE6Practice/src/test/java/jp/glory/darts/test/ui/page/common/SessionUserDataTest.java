package jp.glory.darts.test.ui.page.common;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.ui.page.common.UserSessionData;

import org.junit.Test;

public class SessionUserDataTest {

	@Test
	public void testHasUserAuthority() {

		final UserSessionData sessionData = new UserSessionData();

		final List<AuthorityType> authorityList = new ArrayList<>();
		authorityList.add(AuthorityType.USER);
		sessionData.setAuthorityList(authorityList);

		assertThat(sessionData.hasUserAuthority(), is(true));
	}

	@Test
	public void testHasUserAuthorityNoAuthority() {

		final UserSessionData sessionData = new UserSessionData();

		final List<AuthorityType> authorityList = new ArrayList<>();
		sessionData.setAuthorityList(authorityList);

		assertThat(sessionData.hasUserAuthority(), is(false));
	}

	@Test
	public void testHasUserAuthorityNullList() {

		final UserSessionData sessionData = new UserSessionData();

		assertThat(sessionData.hasUserAuthority(), is(false));
	}

	@Test
	public void testHasAdminAuthority() {

		final UserSessionData sessionData = new UserSessionData();

		final List<AuthorityType> authorityList = new ArrayList<>();
		authorityList.add(AuthorityType.ADMINISTRATOR);
		sessionData.setAuthorityList(authorityList);

		assertThat(sessionData.hasAdminAuthority(), is(true));
	}

	@Test
	public void testHasAdminAuthorityNoAuthority() {

		final UserSessionData sessionData = new UserSessionData();

		final List<AuthorityType> authorityList = new ArrayList<>();
		sessionData.setAuthorityList(authorityList);

		assertThat(sessionData.hasAdminAuthority(), is(false));
	}

	@Test
	public void testHasAdminAuthorityNullList() {

		final UserSessionData sessionData = new UserSessionData();

		assertThat(sessionData.hasAdminAuthority(), is(false));
	}

}
