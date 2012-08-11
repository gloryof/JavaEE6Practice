package jp.glory.darts.ui.page.index;

/**
 * インデックスページインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface IndexPage {

	/**
	 * ログイン<br/>
	 * ログインに成功した場合：メインメニューに遷移する<br>
	 * ログインに失敗した場合：ログイン画面に戻る<br>
	 * 
	 * @return 画面遷移キー
	 */
	String login();
}
