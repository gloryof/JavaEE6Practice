package jp.glory.darts.ui.page.menu;

/**
 * メインメニューページインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface MainMenuPage {

	/**
	 * 初期化処理<br>
	 * メインメニューに遷移する
	 * 
	 * @return 画面遷移キー
	 */
	String init();

	/**
	 * ログアウト処理<br/>
	 * ログイン画面に遷移する。
	 * 
	 * @return 画面遷移キー
	 */
	String logout();
}
