package jp.glory.darts.ui.page.admin.user.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import jp.glory.darts.application.user.UserApplication;
import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.common.exception.BusinessException;
import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.domain.user.value.Authority;
import jp.glory.darts.ui.page.admin.user.EditUserPage;
import jp.glory.darts.ui.page.common.ForwardKey;
import jp.glory.darts.ui.page.util.JsfUtil;

@Named(value = "editUserPage")
@SessionScoped
public class EditUserPageImpl implements Serializable, EditUserPage {

	/**
	 * �V���A���o�[�W����UID
	 */
	private static final long serialVersionUID = 2347571450997724735L;

	/** ���[�U�A�v���P�[�V���� */
	@Inject
	private UserApplication userAppli = null;

	/** JSF���[�e�B���e�B */
	@Inject
	private JsfUtil jsfUtil = null;

	/** �\����p���[�h */
	private boolean viewMode = false;

	/** �V�K�o�^�t���O */
	private boolean newEntryData = false;

	/** ���[�UID */
	private String userId = null;

	/** ���[�U�� */
	private String name = null;

	/** �p�X���[�h */
	private String password = null;

	/** ���b�Z�[�W */
	private String infoMessage = null;

	/** �I���ς݌��� */
	private List<String> selectedAuthority = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String initForView(final String userId) {

		reflectEntityValue(userAppli.getUser(userId));

		viewMode = true;
		newEntryData = false;

		return ForwardKey.EDIT_USER.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String initForEntry() {

		clearData();
		viewMode = false;
		newEntryData = true;

		return ForwardKey.EDIT_USER.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String initForUpdate(final String userId) {

		reflectEntityValue(userAppli.getUser(userId));

		viewMode = false;
		newEntryData = false;

		return ForwardKey.EDIT_USER.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String update() {

		final User entity = createEntity();

		String forwardKey = null;

		try {
			if (newEntryData) {

				userAppli.entryUser(entity);

				forwardKey = ForwardKey.USER_LIST.getValue();
			} else {

				userAppli.updateUser(entity);

				forwardKey = ForwardKey.EDIT_USER.getValue();

				viewMode = true;
				newEntryData = false;
				infoMessage = MessageInfo.INFO_UPDATE_COMPLETE.getMessage();
			}
		} catch (BusinessException be) {

			for (final ErrorInfo errorInfo : be.getErrorInfoList()) {

				jsfUtil.addErrorMessage("editUserPage", errorInfo);
			}

			return ForwardKey.EDIT_USER.getValue();
		}

		return forwardKey;
	}

	/**
	 * �����̑I�����X�g���擾����
	 * 
	 * @return �I�����X�g
	 */
	public List<SelectItem> getAuthorityItems() {

		final List<SelectItem> selectItems = new ArrayList<>();

		for (AuthorityType authority : AuthorityType.values()) {

			final SelectItem item = new SelectItem(authority.getCodeValue(), authority.getName());
			selectItems.add(item);
		}

		return selectItems;
	}

	/**
	 * ���̓f�[�^���N���A����
	 */
	private void clearData() {

		userId = null;
		name = null;
		password = null;
		infoMessage = null;
		selectedAuthority = null;
	}

	/**
	 * ���[�U�G���e�B�e�B�̒l����ʂ̏��ɔ��f����
	 * 
	 * @param entity ���[�U�G���e�B�e�B
	 */
	private void reflectEntityValue(final User entity) {

		this.userId = entity.getUserId();
		this.name = entity.getName();
		this.password = entity.getPassword();

		final List<String> authorityList = new ArrayList<>();

		for (final Authority authority : entity.getAuthorityList()) {

			authorityList.add(String.valueOf(authority.getAuthorityType().getCodeValue()));
		}

		this.selectedAuthority = authorityList;
	}

	/**
	 * ���[�U�G���e�B�e�B���쐬����
	 * 
	 * @return ���[�U�G���e�B�e�B
	 */
	private User createEntity() {

		final User entity = new User(userId);

		entity.setName(name);
		entity.setPassword(password);

		for (final AuthorityType type : AuthorityType.values()) {

			if (selectedAuthority.contains(String.valueOf(type.getCodeValue()))) {

				entity.addAuthority(type);
			}
		}

		return entity;
	}

	public void setUserAppli(final UserApplication userAppli) {
		this.userAppli = userAppli;
	}

	public void setJsfUtil(final JsfUtil jsfUtil) {
		this.jsfUtil = jsfUtil;
	}

	public boolean isViewMode() {
		return viewMode;
	}

	public void setViewMode(final boolean viewMode) {
		this.viewMode = viewMode;
	}

	public boolean isNewEntryData() {
		return newEntryData;
	}

	public void setNewEntryData(final boolean newEntryData) {
		this.newEntryData = newEntryData;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(final String infoMessage) {
		this.infoMessage = infoMessage;
	}

	public List<String> getSelectedAuthority() {
		return selectedAuthority;
	}

	public void setSelectedAuthority(final List<String> selectedAuthority) {
		this.selectedAuthority = selectedAuthority;
	}

}
