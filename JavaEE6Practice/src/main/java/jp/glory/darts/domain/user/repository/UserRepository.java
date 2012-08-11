package jp.glory.darts.domain.user.repository;

import java.util.List;

import jp.glory.darts.domain.user.entity.User;

/**
 * ユーザリポジトリ
 * 
 * @author Junki Yamada
 * 
 */
public interface UserRepository {

	/**
	 * ユーザIDで検索する
	 * 
	 * @param userId ユーザId
	 * @return ユーザエンティティ
	 */
	User findByUserId(final String userId);

	/**
	 * 全てのユーザエンティティを返却する
	 * 
	 * @return ユーザエンティティリスト
	 */
	List<User> findAll();

	/**
	 * エンティティを保存する
	 * 
	 * @param entity エンティティ
	 */
	void save(final User entity);

	/**
	 * 有効状態を更新する<br/>
	 * パラメータのユーザIDリストの全ての有効状態を<br/>
	 * パラメータの有効状態で更新する
	 * 
	 * @param userIdList ユーザIDリスト
	 * @param activeStatus 有効状態
	 */
	void updateActiveStatus(final List<String> userIdList, boolean activeStatus);
}
