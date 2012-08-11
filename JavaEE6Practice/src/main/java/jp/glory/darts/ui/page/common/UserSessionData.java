package jp.glory.darts.ui.page.common;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import jp.glory.darts.domain.common.AuthorityType;

/**
 * ���[�U�Z�b�V�����f�[�^
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "userSessionData")
@SessionScoped
public class UserSessionData implements Serializable {

	/**
	 * �V���A���o�[�W����UID
	 */
	private static final long serialVersionUID = 2919276059824025159L;

	/** ���[�UID */
	private String userId = null;

	/** ���[�U�� */
	private String name = null;

	/** �������X�g */
	private List<AuthorityType> authorityList = null;

	/**
	 * ���[�U������ێ����Ă��邩�𔻒f����
	 * 
	 * @return ���[�U����������ꍇ�Ftrue�A���[�U�������Ȃ��ꍇ�Ffalse
	 */
	public boolean hasUserAuthority() {

		if (authorityList == null) {

			return false;
		}

		return authorityList.contains(AuthorityType.USER);
	}

	/**
	 * �Ǘ��Ҍ�����ێ����Ă��邩�𔻒f����
	 * 
	 * @return �Ǘ��Ҍ���������ꍇ�Ftrue�A�Ǘ��Ҍ������Ȃ��ꍇ�Ffalse
	 */
	public boolean hasAdminAuthority() {

		if (authorityList == null) {

			return false;
		}

		return authorityList.contains(AuthorityType.ADMINISTRATOR);
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

	public List<AuthorityType> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(final List<AuthorityType> authorityList) {
		this.authorityList = authorityList;
	}

}
