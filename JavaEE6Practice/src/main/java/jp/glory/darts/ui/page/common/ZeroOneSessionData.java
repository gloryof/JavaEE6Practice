package jp.glory.darts.ui.page.common;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import jp.glory.darts.domain.game.entity.ZeroOne;

/**
 * ゼロワンのセッションデータ
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "zeroOneSessionData")
@SessionScoped
public class ZeroOneSessionData implements Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 5764401541397635788L;

	/** カウントエンティティ */
	private ZeroOne gameInfo = null;

	public ZeroOne getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(final ZeroOne gameInfo) {
		this.gameInfo = gameInfo;
	}

}
