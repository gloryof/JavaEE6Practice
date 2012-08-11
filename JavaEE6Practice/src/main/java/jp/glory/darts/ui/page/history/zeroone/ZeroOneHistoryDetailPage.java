package jp.glory.darts.ui.page.history.zeroone;

/**
 * ゼロワン履歴詳細ページインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface ZeroOneHistoryDetailPage {

	/**
	 * 初期表示処理<br/>
	 * ゲームIDに紐付くゼロワンの詳細を表示する。
	 * 
	 * @param gameId ゲームID
	 * @return 画面遷移キー
	 */
	String init(final long gameId);
}
