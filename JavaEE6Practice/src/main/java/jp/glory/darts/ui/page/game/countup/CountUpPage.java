package jp.glory.darts.ui.page.game.countup;

/**
 * カウントアップページインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface CountUpPage {

	/**
	 * カウントアップページ初期化処理<br/>
	 * 初期化処理完了後、カウントアップページを表示する
	 * 
	 * @return 画面遷移キー
	 */
	String init();

	/**
	 * 再表示処理<br/>
	 * カウントアップページを表示する。
	 * 
	 * @return 画面遷移キー
	 */
	String review();

	/**
	 * 次のラウンドを表示する
	 */
	void nextRound();

	/**
	 * 前のラウンドを表示する
	 */
	void prevRound();

	/**
	 * リセットする
	 */
	void reset();

	/**
	 * ゲームを終了する。<br/>
	 * 最終ラウンドの点数を反映し、結果画面に遷移する。
	 * 
	 * @return 画面遷移キー
	 */
	String endGame();
}
