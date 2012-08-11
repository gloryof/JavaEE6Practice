package jp.glory.darts.domain.user.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.glory.darts.common.MessageInfo;
import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.common.Entity;
import jp.glory.darts.domain.user.value.Authority;

import org.apache.commons.lang.StringUtils;

/**
 * ���[�U�G���e�B�e�B
 * 
 * @author Junki Yamada
 * 
 */
public class User implements Entity<User> {

	/** ���[�UID */
	private final String userId;

	/** ���[�U�� */
	private String name = null;

	/** �p�X���[�h */
	private String password = null;

	/** �L����� */
	private boolean active = false;

	/** �������X�g */
	private final List<Authority> authorityList = new ArrayList<>();

	/**
	 * �R���X�g���N�^
	 * 
	 * @param userId ���[�UID
	 */
	public User(final String userId) {

		if (StringUtils.isEmpty(userId)) {

			throw new IllegalArgumentException("User entity is required userId");
		}

		this.userId = userId;
	}

	/**
	 * ������ێ����Ă��邩�`�F�b�N����
	 * 
	 * @param type ����
	 * @return ������ێ����Ă���ꍇ�Ftrue�A������ێ����Ă��Ȃ��ꍇ�Ffalse
	 */
	public boolean hasAuthority(final AuthorityType type) {

		if (type == null) {

			return false;
		}

		for (final Authority authority : this.authorityList) {

			if (authority.getAuthorityType().equals(type)) {

				return true;
			}
		}

		return false;
	}

	/**
	 * ������ǉ�����
	 * 
	 * @param type ����
	 */
	public void addAuthority(final AuthorityType type) {

		if (!hasAuthority(type)) {

			this.authorityList.add(new Authority(type));
		}
	}

	/**
	 * ���̓`�F�b�N���s���B<br/>
	 * �`�F�b�N�������ʁA�G���[��񃊃X�g��ԋp����B
	 * 
	 * @return �G���[��񃊃X�g
	 */
	public List<ErrorInfo> validate() {

		final List<ErrorInfo> returnList = new ArrayList<>();

		if (StringUtils.isEmpty(name)) {

			final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_BLANK;
			final String[] messageParam = new String[] {
				"���[�U��"
			};
			returnList.add(new ErrorInfo(messageInfo, messageParam));
		}

		if (StringUtils.isEmpty(password)) {

			final MessageInfo messageInfo = MessageInfo.ERROR_COMMON_BLANK;
			final String[] messageParam = new String[] {
				"�p�X���[�h"
			};
			returnList.add(new ErrorInfo(messageInfo, messageParam));
		}

		if (authorityList.size() < 1) {

			final MessageInfo messageInfo = MessageInfo.ERROR_USER_AUTHORITY;
			returnList.add(new ErrorInfo(messageInfo));
		}

		return returnList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSame(final User other) {

		if (other == null) {

			return false;
		}

		return this.userId.equals(other.getUserId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User copyEntity() {

		final User copyEntity = new User(this.userId);
		copyEntity.setPassword(new String(this.password));
		copyEntity.setName(new String(this.name));

		for (Authority authority : this.authorityList) {

			copyEntity.addAuthority(authority.getAuthorityType());
		}

		return copyEntity;
	}

	public String getUserId() {
		return userId;
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

	public List<Authority> getAuthorityList() {
		return Collections.unmodifiableList(this.authorityList);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}
}
