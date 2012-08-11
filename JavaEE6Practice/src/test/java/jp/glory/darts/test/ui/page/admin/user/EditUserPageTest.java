package jp.glory.darts.test.ui.page.admin.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.stub.application.user.UserApplicationStub;
import jp.glory.darts.stub.ui.page.util.JsfUtilStub;
import jp.glory.darts.ui.page.admin.user.impl.EditUserPageImpl;
import jp.glory.darts.ui.page.common.ForwardKey;

import org.junit.Before;
import org.junit.Test;

public class EditUserPageTest {

	/** テスト対象クラス */
	private EditUserPageImpl testObj = null;

	/** JSFユーティリティスタブ */
	private JsfUtilStub jsfUtilStub = new JsfUtilStub();

	@Before
	public void beforeTest() {

		testObj = new EditUserPageImpl();

		testObj.setUserAppli(new UserApplicationStub());
		testObj.setJsfUtil(jsfUtilStub);
	}

	@Test
	public void testInitForView() {

		final String forwadKey = testObj.initForView(UserApplicationStub.TEST_USER_ID);

		assertThat(forwadKey, is(ForwardKey.EDIT_USER.getValue()));
		assertDisplayInfo();
		assertThat(testObj.isViewMode(), is(true));
		assertThat(testObj.isNewEntryData(), is(false));
		assertThat(testObj.getInfoMessage(), is(nullValue()));
	}

	@Test
	public void testInitForEntryValuesCleared() {

		testObj.setInfoMessage("test");
		testObj.setName("test");
		testObj.setPassword("password");
		testObj.setSelectedAuthority(new ArrayList<String>());
		testObj.setUserId("test");

		final String forwadKey = testObj.initForEntry();

		assertThat(forwadKey, is(ForwardKey.EDIT_USER.getValue()));

		assertEmptyDisplayInfo();
		assertThat(testObj.isViewMode(), is(false));
		assertThat(testObj.isNewEntryData(), is(true));
		assertThat(testObj.getInfoMessage(), is(nullValue()));
	}

	@Test
	public void testInitForUpdate() {

		final String forwadKey = testObj.initForUpdate(UserApplicationStub.TEST_USER_ID);

		assertThat(forwadKey, is(ForwardKey.EDIT_USER.getValue()));
		assertDisplayInfo();
		assertThat(testObj.isViewMode(), is(false));
		assertThat(testObj.isNewEntryData(), is(false));
	}

	@Test
	public void testUpdate() {

		testObj.setUserId("UPDATE_USER");
		testObj.setName("既存ユーザ");

		final List<String> selectedAuthorityList = new ArrayList<>();
		selectedAuthorityList.add(String.valueOf(AuthorityType.ADMINISTRATOR.getCodeValue()));
		testObj.setSelectedAuthority(selectedAuthorityList);

		testObj.setNewEntryData(false);

		final String fowardKey = testObj.update();

		assertThat(fowardKey, is(ForwardKey.EDIT_USER.getValue()));
		assertThat(testObj.isViewMode(), is(true));
		assertThat(testObj.isNewEntryData(), is(false));
		assertThat(testObj.getInfoMessage(), is(MessageInfo.INFO_UPDATE_COMPLETE.getMessage()));
	}

	@Test
	public void testUpdateByNewEntry() {

		testObj.setUserId("NEW_USER");
		testObj.setName("新ユーザ");

		final List<String> selectedAuthorityList = new ArrayList<>();
		selectedAuthorityList.add(String.valueOf(AuthorityType.ADMINISTRATOR.getCodeValue()));
		testObj.setSelectedAuthority(selectedAuthorityList);

		testObj.setNewEntryData(true);

		final String fowardKey = testObj.update();

		assertThat(fowardKey, is(ForwardKey.USER_LIST.getValue()));
	}

	@Test
	public void testUpdateError() {

		testObj.setUserId("UPDATE_USER");
		testObj.setName("既存ユーザ");

		testObj.setSelectedAuthority(new ArrayList<String>());

		testObj.setNewEntryData(false);

		final UserApplicationStub appliStub = new UserApplicationStub();
		appliStub.setThrowException(true);
		testObj.setUserAppli(appliStub);

		final String fowardKey = testObj.update();

		assertThat(fowardKey, is(ForwardKey.EDIT_USER.getValue()));
		assertThat(jsfUtilStub.getErrorCount(), is(1));
		assertThat(jsfUtilStub.getByIndex(0).getMessageInfo(), is(MessageInfo.ERROR_USER_AUTHORITY));
	}

	@Test
	public void testUpdateErrorByNewEntry() {

		testObj.setUserId("NEW_USER");
		testObj.setName("新ユーザ");

		testObj.setSelectedAuthority(new ArrayList<String>());

		testObj.setNewEntryData(true);

		final UserApplicationStub appliStub = new UserApplicationStub();
		appliStub.setThrowException(true);
		testObj.setUserAppli(appliStub);

		final String fowardKey = testObj.update();

		assertThat(fowardKey, is(ForwardKey.EDIT_USER.getValue()));
		assertThat(jsfUtilStub.getErrorCount(), is(1));
		assertThat(jsfUtilStub.getByIndex(0).getMessageInfo(), is(MessageInfo.ERROR_USER_AUTHORITY));
	}

	@Test
	public void testGetAuthorityItems() {

		final List<SelectItem> selectItems = testObj.getAuthorityItems();
		final AuthorityType[] authoritValues = AuthorityType.values();

		assertThat(selectItems, is(not(nullValue())));
		assertThat(selectItems.size(), is(authoritValues.length));

		for (int i = 0; i < authoritValues.length; i++) {

			final AuthorityType type = authoritValues[i];
			final SelectItem item = selectItems.get(i);

			assertThat((Integer) item.getValue(), is(Integer.valueOf(type.getCodeValue())));
			assertThat(item.getLabel(), is(type.getName()));
		}
	}

	/**
	 * 画面に表示されるデータを検証する
	 */
	private void assertDisplayInfo() {

		assertThat(testObj.getUserId(), is(UserApplicationStub.TEST_USER_ID));
		assertThat(testObj.getName(), is(UserApplicationStub.TEST_USER_NAME));
		assertThat(testObj.getPassword(), is(UserApplicationStub.TEST_USER_PASSWORD));

		final List<String> selectedAuthorityList = testObj.getSelectedAuthority();
		assertThat(selectedAuthorityList.size(), is(1));
		assertThat(selectedAuthorityList.get(0), is(String.valueOf(AuthorityType.ADMINISTRATOR.getCodeValue())));
	}

	/**
	 * 全ての項目が空白であるかを検証する
	 */
	private void assertEmptyDisplayInfo() {

		assertThat(testObj.getUserId(), is(nullValue()));
		assertThat(testObj.getName(), is(nullValue()));
		assertThat(testObj.getPassword(), is(nullValue()));
		assertThat(testObj.getSelectedAuthority(), is(nullValue()));
	}

}
