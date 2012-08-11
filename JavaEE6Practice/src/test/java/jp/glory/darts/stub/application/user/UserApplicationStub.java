package jp.glory.darts.stub.application.user;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Any;

import jp.glory.darts.application.user.UserApplication;
import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.common.exception.BusinessException;
import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.user.entity.User;

/**
 * ユーザアプリケーションスタブ
 * 
 * @author Junki Yamada
 * 
 */
@Any
public class UserApplicationStub implements UserApplication {

	/** テストユーザID */
	public static final String TEST_USER_ID = "TEST_USER";

	/** テストユーザ名 */
	public static final String TEST_USER_NAME = "テストユーザ";

	/** テストユーザパスワード */
	public static final String TEST_USER_PASSWORD = "password";

	/** 例外スローフラグ */
	private boolean throwException = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(final String userId) {

		if (UserApplicationStub.TEST_USER_ID.equals(userId)) {

			return createTestUser1();
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> getAllUser() {

		final List<User> userList = new ArrayList<>();

		userList.add(createTestUser1());
		userList.add(createTestUser2());
		userList.add(createTestUser3());

		return userList;
	}

	/**
	 * 例外スローフラグがtrueの場合は例外をスローする。<br/>
	 * falseの場合は何もしない。
	 */
	@Override
	public void updateUser(final User entity) throws BusinessException {

		if (throwException) {

			final List<ErrorInfo> errorInfoList = new ArrayList<>();
			final ErrorInfo errorInfo = new ErrorInfo(MessageInfo.ERROR_USER_AUTHORITY);
			errorInfoList.add(errorInfo);
			throw new BusinessException(errorInfoList);
		}
	}

	/**
	 * 例外スローフラグがtrueの場合は例外をスローする。<br/>
	 * falseの場合は何もしない。
	 */
	@Override
	public void entryUser(final User entity) throws BusinessException {

		if (throwException) {

			final List<ErrorInfo> errorInfoList = new ArrayList<>();
			final ErrorInfo errorInfo = new ErrorInfo(MessageInfo.ERROR_USER_AUTHORITY);
			errorInfoList.add(errorInfo);
			throw new BusinessException(errorInfoList);
		}
	}

	/**
	 * スタブでは何もしない
	 */
	@Override
	public void updateActiveStatus(final List<String> userIdList, final boolean activeStatus) {

	}

	/**
	 * テストユーザ（パターン1）を作成する
	 * 
	 * @return テストユーザ
	 */
	private User createTestUser1() {

		final User entity = new User(UserApplicationStub.TEST_USER_ID);
		entity.setPassword(UserApplicationStub.TEST_USER_PASSWORD);
		entity.setName(UserApplicationStub.TEST_USER_NAME);

		entity.addAuthority(AuthorityType.ADMINISTRATOR);
		entity.setActive(true);

		return entity;
	}

	/**
	 * テストユーザ（パターン2）を作成する
	 * 
	 * @return テストユーザ
	 */
	private User createTestUser2() {

		final String patternString = "2";
		final User entity = new User(UserApplicationStub.TEST_USER_ID + patternString);
		entity.setPassword(UserApplicationStub.TEST_USER_PASSWORD + patternString);
		entity.setName("テスト ユーザ" + patternString);

		entity.addAuthority(AuthorityType.ADMINISTRATOR);
		entity.setActive(true);

		return entity;
	}

	/**
	 * テストユーザ（パターン3）を作成する
	 * 
	 * @return テストユーザ
	 */
	private User createTestUser3() {

		final String patternString = "3";
		final User entity = new User(UserApplicationStub.TEST_USER_ID + patternString);
		entity.setPassword(UserApplicationStub.TEST_USER_PASSWORD + patternString);
		entity.setName("テスト ユーザ" + patternString);

		entity.addAuthority(AuthorityType.ADMINISTRATOR);
		entity.setActive(true);

		return entity;
	}

	public void setThrowException(final boolean throwException) {
		this.throwException = throwException;
	}

	public boolean isThrowException() {
		return throwException;
	}

}
