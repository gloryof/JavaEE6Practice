package jp.glory.darts.ui.page.admin.user;

/**
 * ユーザ一覧ページインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface UserListPage {

	/**
	 * 画面の初期表示処理<br/>
	 * ユーザのリストを取得し、ページ表示用のデータとして保持する。<br/>
	 * 処理終了後、ユーザ一覧ページの画面船キーを返却する。
	 * 
	 * @return 画面遷移キー
	 */
	String init();

	/**
	 * 有効状態を更新する
	 * 
	 * @param activeStatus 有効状態
	 */
	void changeActive(boolean activeStatus);
}
