package jp.glory.darts.ui.page.admin.user;

/**
 * ユーザ編集画面ページインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface EditUserPage {

	/**
	 * 参照表示用の画面表示処理を行う。<br/>
	 * ユーザ編集画面に遷移する
	 * 
	 * @param userId ユーザID
	 * @return 画面遷移キー
	 */
	String initForView(String userId);

	/**
	 * 登録用の画面表示処理を行う。<br/>
	 * ユーザ編集画面に遷移する
	 * 
	 * @return 画面遷移キー
	 */
	String initForEntry();

	/**
	 * 更新用の画面表示処理を行う。<br/>
	 * ユーザ編集画面に遷移する
	 * 
	 * @param userId ユーザID
	 * @return 画面遷移キー
	 */
	String initForUpdate(String userId);

	/**
	 * 入力した内容で更新をする<br/>
	 * 
	 * @return 画面遷移キー
	 */
	String update();
}
