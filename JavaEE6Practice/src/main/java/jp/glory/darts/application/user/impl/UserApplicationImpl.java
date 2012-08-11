package jp.glory.darts.application.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jp.glory.darts.application.user.UserApplication;
import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.common.exception.BusinessException;
import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.domain.user.repository.UserRepository;

/**
 * ユーザアプリケーションクラス
 * 
 * @author Junki Yamada
 * 
 */
@Stateless
@LocalBean
public class UserApplicationImpl implements UserApplication {

	/** ユーザリポジトリ */
	@Inject
	private UserRepository repository = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(final String userId) {

		return repository.findByUserId(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> getAllUser() {

		return repository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUser(final User entity) throws BusinessException {

		final List<ErrorInfo> entityErrorList = entity.validate();
		if (0 < entityErrorList.size()) {

			throw new BusinessException(entityErrorList);
		}

		repository.save(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void entryUser(final User entity) throws BusinessException {

		final List<ErrorInfo> entityErrorList = entity.validate();
		if (0 < entityErrorList.size()) {

			throw new BusinessException(entityErrorList);
		}

		if ((repository.findByUserId(entity.getUserId())) != null) {

			final ErrorInfo errorInfo = new ErrorInfo(MessageInfo.ERROR_DUPLICATE_USER_ID);
			final List<ErrorInfo> errorList = new ArrayList<>();
			errorList.add(errorInfo);
			throw new BusinessException(errorList);
		}

		repository.save(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateActiveStatus(final List<String> userIdList, final boolean activeStatus) {

		repository.updateActiveStatus(userIdList, activeStatus);
	}

	public void setRepository(final UserRepository repository) {
		this.repository = repository;
	}

}
