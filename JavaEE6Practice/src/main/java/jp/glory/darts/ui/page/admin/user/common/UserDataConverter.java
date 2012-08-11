package jp.glory.darts.ui.page.admin.user.common;

import jp.glory.darts.domain.user.entity.User;
import jp.glory.darts.ui.page.admin.user.bean.UserDataPageBean;

/**
 * ユーザのエンティティと画面用Beanの変換を行う。<br/>
 * 
 * @author Junki Yamada
 * 
 */
public final class UserDataConverter {

	/**
	 * コンストラクタ
	 */
	private UserDataConverter() {

	}

	/**
	 * ユーザエンティティから画面用Beanに変換する
	 * 
	 * @param entity エンティティ
	 * @return 画面用Bean
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
	 * 画面用Beanからエンティティに変換する
	 * 
	 * @param dataBean 画面用Bean
	 * @return エンティティ
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
