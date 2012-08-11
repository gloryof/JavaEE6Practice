package jp.glory.darts.test.ui.page.admin.user.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.ui.page.admin.user.bean.UserDataPageBean;
import jp.glory.darts.ui.page.admin.user.common.UserDataConverter;

import org.junit.Test;

public class UserDataConverterTest {

	@Test
	public void testConvertUserPageDataBean() {

		final User entity = new User("TEST_USER");
		entity.setName("テストユーザ");
		entity.setPassword("password");

		final UserDataPageBean result = UserDataConverter.convertUserPageDataBean(entity);

		assertThat(result, is(not(nullValue())));
		assertThat(result.getUserId(), is(entity.getUserId()));
		assertThat(result.getName(), is(entity.getName()));
		assertThat(result.getPassword(), is(entity.getPassword()));
	}

	@Test
	public void testConvertUserPageDataBeanNullEntity() {

		final UserDataPageBean result = UserDataConverter.convertUserPageDataBean(null);

		assertThat(result, is(not(nullValue())));
		assertThat(result.getUserId(), is(nullValue()));
		assertThat(result.getName(), is(nullValue()));
		assertThat(result.getPassword(), is(nullValue()));
	}

	@Test
	public void testConvertEntity() {

		final UserDataPageBean pageBean = new UserDataPageBean();

		pageBean.setUserId("TEST_USER");
		pageBean.setName("テストユーザ");
		pageBean.setPassword("password");

		final User result = UserDataConverter.convertEntity(pageBean);

		assertThat(result, is(not(nullValue())));
		assertThat(result.getUserId(), is(pageBean.getUserId()));
		assertThat(result.getName(), is(pageBean.getName()));
		assertThat(result.getPassword(), is(pageBean.getPassword()));
	}

	@Test
	public void testConvertEntityNull() {

		assertThat(UserDataConverter.convertEntity(null), is(nullValue()));
	}
}
