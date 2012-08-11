package jp.glory.darts.ui.page.admin.user.common;

import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.ui.page.admin.user.bean.UserDataPageBean;

/**
 * ���[�U�̃G���e�B�e�B�Ɖ�ʗpBean�̕ϊ����s���B<br/>
 * 
 * @author Junki Yamada
 * 
 */
public final class UserDataConverter {

	/**
	 * �R���X�g���N�^
	 */
	private UserDataConverter() {

	}

	/**
	 * ���[�U�G���e�B�e�B�����ʗpBean�ɕϊ�����
	 * 
	 * @param entity �G���e�B�e�B
	 * @return ��ʗpBean
	 */
	public static UserDataPageBean convertUserPageDataBean(final User entity) {

		if (entity == null) {

			return new UserDataPageBean();
		}

		final UserDataPageBean returnBean = new UserDataPageBean();

		returnBean.setUserId(entity.getUserId());
		returnBean.setName(entity.getName());
		returnBean.setPassword(entity.getPassword());
		returnBean.setActive(entity.isActive());

		return returnBean;
	}

	/**
	 * ��ʗpBean����G���e�B�e�B�ɕϊ�����
	 * 
	 * @param dataBean ��ʗpBean
	 * @return �G���e�B�e�B
	 */
	public static User convertEntity(final UserDataPageBean dataBean) {

		if (dataBean == null) {

			return null;
		}

		final User entity = new User(dataBean.getUserId());

		entity.setName(dataBean.getName());
		entity.setPassword(dataBean.getPassword());

		return entity;
	}
}
