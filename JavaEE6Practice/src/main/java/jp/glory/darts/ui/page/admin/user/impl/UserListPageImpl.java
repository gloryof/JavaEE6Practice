package jp.glory.darts.ui.page.admin.user.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.application.user.UserApplication;
import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.ui.page.admin.user.UserListPage;
import jp.glory.darts.ui.page.admin.user.bean.UserDataPageBean;
import jp.glory.darts.ui.page.admin.user.common.UserDataConverter;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.util.JsfUtil;

/**
 * ���[�U�ꗗ�y�[�W�N���X
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "userListPage")
@SessionScoped
public class UserListPageImpl implements Serializable, UserListPage {

	/**
	 * �V���A���o�[�W����UID
	 */
	private static final long serialVersionUID = -2134134547271407468L;

	/** ���[�U�ꗗ�f�[�^���X�g */
	private List<UserDataPageBean> userList = null;

	/** �`�F�b�N�ς݃��[�U�}�b�v */
	private Map<String, Boolean> checkedUserIdMap = new HashMap<>();

	/** ���[�U�A�v���P�[�V���� */
	@Inject
	private UserApplication applicatioin = null;

	/** JSF���[�e�B���e�B */
	@Inject
	private JsfUtil jsfUtil = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init() {

		userList = new ArrayList<>();

		for (final User entity : applicatioin.getAllUser()) {

			userList.add(UserDataConverter.convertUserPageDataBean(entity));
		}

		return ForwardKey.USER_LIST.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeActive(final boolean activeStatus) {

		if (checkedUserIdMap.isEmpty()) {

			final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_NO_CHECK;
			final String[] messageParam = new String[] {
				"�X�V�Ώۃ��[�U"
			};
			final ErrorInfo errorInfo = new ErrorInfo(messageInfo, messageParam);
			jsfUtil.addErrorMessage("userListPage", errorInfo);
		}

		final List<String> checkedUserIdList = new ArrayList<>();
		for (final Map.Entry<String, Boolean> entry : checkedUserIdMap.entrySet()) {

			if (entry.getValue()) {

				checkedUserIdList.add(entry.getKey());
			}
		}

		applicatioin.updateActiveStatus(checkedUserIdList, activeStatus);

		checkedUserIdMap.clear();

		init();
	}

	public List<UserDataPageBean> getUserList() {
		return userList;
	}

	public void setUserList(final List<UserDataPageBean> userList) {
		this.userList = userList;
	}

	public void setApplicatioin(final UserApplication applicatioin) {
		this.applicatioin = applicatioin;
	}

	public Map<String, Boolean> getCheckedUserIdMap() {
		return checkedUserIdMap;
	}

	public void setCheckedUserIdMap(final Map<String, Boolean> checkedUserIdMap) {
		this.checkedUserIdMap = checkedUserIdMap;
	}

	public void setJsfUtil(final JsfUtil jsfUtil) {
		this.jsfUtil = jsfUtil;
	}

}
