package jp.glory.darts.ui.page.game.countup;

/**
 * カウントアップ結果ページ
 * 
 * @author Junki Yamada
 * 
 */
public interface CountUpResultPage {

	/**
	 * カウントアップ結果ページ初期化処理<br/>
	 * 初期化処理完了後、カウントアップ結果ページを表示する
	 * 
	 * @return 画面遷移キー
	 */
	String init();

	/**
	 * ゲームを終了する。<br/>
	 * 結果を保存し、メニューページに遷移する。
	 * 
	 * @return 画面遷移キー
	 */
	String finish();
}
