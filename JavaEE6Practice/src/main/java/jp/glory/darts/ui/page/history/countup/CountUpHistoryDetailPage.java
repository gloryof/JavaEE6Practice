package jp.glory.darts.ui.page.history.countup;

/**
 * カウントアップ履歴詳細ページインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface CountUpHistoryDetailPage {

	/**
	 * 初期表示処理<br/>
	 * ゲームIDに紐付くカウントアップの詳細を表示する。
	 * 
	 * @param gameId ゲームID
	 * @return 画面遷移キー
	 */
	String init(final long gameId);
}
