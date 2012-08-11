package jp.glory.darts.test.ui.page.admin.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.stub.application.user.UserApplicationStub;
import jp.glory.darts.stub.ui.page.util.JsfUtilStub;
import jp.glory.darts.ui.page.admin.user.bean.UserDataPageBean;
import jp.glory.darts.ui.page.admin.user.impl.UserListPageImpl;
import jp.glory.darts.ui.page.common.ForwardKey;

import org.junit.Before;
import org.junit.Test;

public class UserListPageTest {

	/** テスト対象 */
	private UserListPageImpl testObj = null;

	/** ユーザアプリケーションスタブ */
	private UserApplicationStub userAppliStub = new UserApplicationStub();

	/** JSFユーティリティスタブ */
	private JsfUtilStub jsfUtilStub = null;

	@Before
	public void beforeTest() {

		testObj = new UserListPageImpl();

		jsfUtilStub = new JsfUtilStub();
		testObj.setApplicatioin(userAppliStub);
		testObj.setJsfUtil(jsfUtilStub);
	}

	@Test
	public void testInit() {

		final Map<String, User> userMap = new HashMap<String, User>();
		for (final User entity : userAppliStub.getAllUser()) {

			userMap.put(entity.getUserId(), entity);
		}

		final String forwardKey = testObj.init();

		assertThat(forwardKey, is(ForwardKey.USER_LIST.getValue()));
		assertThat(testObj.getUserList(), is(not(nullValue())));
		assertThat(testObj.getUserList().size(), is(userMap.size()));

		for (final UserDataPageBean pageBean : testObj.getUserList()) {

			final User entity = userMap.get(pageBean.getUserId());

			assertThat(pageBean.getUserId(), is(entity.getUserId()));
			assertThat(pageBean.getName(), is(entity.getName()));
		}
	}

	@Test
	public void testChangeActive() {

		final Map<String, Boolean> mapValue = new HashMap<>();

		mapValue.put("test1", true);
		mapValue.put("test2", true);
		mapValue.put("test3", false);
		testObj.setCheckedUserIdMap(mapValue);

		testObj.changeActive(true);

		final Map<String, Boolean> resultMap = testObj.getCheckedUserIdMap();
		assertThat(resultMap, is(not(nullValue())));
		assertThat(resultMap.size(), is(0));
	}

	@Test
	public void testChangeActiveNotSelected() {

		testObj.setCheckedUserIdMap(new HashMap<String, Boolean>());
		testObj.changeActive(false);

		assertThat(jsfUtilStub.getErrorCount(), is(1));
		assertThat(jsfUtilStub.getByIndex(0).getMessageInfo(), is(MessageInfo.ERROR_COMMON_NO_CHECK));
	}
}
