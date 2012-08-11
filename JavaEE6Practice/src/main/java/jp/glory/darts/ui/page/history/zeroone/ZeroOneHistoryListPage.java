package jp.glory.darts.ui.page.history.zeroone;

/**
 * ゼロワン履歴リストページインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface ZeroOneHistoryListPage {

	/**
	 * 初期化処理<br/>
	 * カウントアップ履歴リストページを表示する。<br/>
	 * ログインしているユーザのゼロワンの結果履歴一覧を表示する。
	 * 
	 * @param gameTypeCode ゼロワンゲームタイプコード
	 * @return 画面遷移キー
	 */
	String init(final int gameTypeCode);
}
