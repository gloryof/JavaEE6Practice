package jp.glory.darts.application.user;

import java.util.List;

import jp.glory.darts.common.exception.BusinessException;
import jp.glory.darts.domain.user.entity.User;

/**
 * ユーザアプリケーションインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface UserApplication {

	/**
	 * ユーザを取得する
	 * 
	 * @param userId ユーザId
	 * @return ユーザエンティティ
	 */
	User getUser(String userId);

	/**
	 * 全てのユーザを取得する
	 * 
	 * @return ユーザエンティティリスト
	 */
	List<User> getAllUser();

	/**
	 * ユーザ情報を更新する
	 * 
	 * @param entity ユーザエンティティ
	 * @throws BusinessException 業務例外
	 */
	void updateUser(User entity) throws BusinessException;

	/**
	 * ユーザ情報を登録する<br/>
	 * ユーザIDが使用されているかチェックを行う。
	 * 
	 * @param entity ユーザエンティティ
	 * @throws BusinessException 業務例外
	 */
	void entryUser(User entity) throws BusinessException;

	/**
	 * 有効状態を更新する<br/>
	 * パラメータのユーザIDリストのユーザの有効状態を変更する。
	 * 
	 * @param userIdList ユーザIDリスト
	 * @param activeStatus 有効状態
	 */
	void updateActiveStatus(List<String> userIdList, boolean activeStatus);
}
