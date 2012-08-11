package jp.glory.darts.ui.page.game.zeroone;

/**
 * ゼロワンページインターフェイス
 * 
 * @author Junki Yamada
 * 
 */
public interface ZeroOnePage {

	/**
	 * ゼロワンページ初期化処理<br/>
	 * 初期化処理完了後、ゼロワンページを表示する
	 * 
	 * @param ruleTypeCode ルールタイプコード
	 * @return 画面遷移キー
	 */
	String init(final int ruleTypeCode);

	/**
	 * 再表示処理<br/>
	 * ゼロワンページを表示する。
	 * 
	 * @return 画面遷移キー
	 */
	String review();

	/**
	 * Nextボタン処理。<br/>
	 * ゲームが終了していない場合は、次のラウンドを表示する。<br/>
	 * ゲームが終了している場合は、結果画面を表示する。
	 * 
	 * @return 画面遷移キー
	 */
	String next();

	/**
	 * 前のラウンドを表示する
	 */
	void prevRound();

	/**
	 * リセットする
	 */
	void reset();
}
