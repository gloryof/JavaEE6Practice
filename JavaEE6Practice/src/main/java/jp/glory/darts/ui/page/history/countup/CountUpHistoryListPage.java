package jp.glory.darts.ui.page.history.countup;

/**
 * カウントアップ履歴リストページインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface CountUpHistoryListPage {

	/**
	 * 初期化処理<br/>
	 * カウントアップ履歴リストページを表示する。<br/>
	 * ログインしているユーザのカウントアップの結果履歴一覧を表示する。
	 * 
	 * @return 画面遷移キー
	 */
	String init();
}
