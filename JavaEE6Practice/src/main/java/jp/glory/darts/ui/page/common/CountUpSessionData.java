package jp.glory.darts.ui.page.common;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import jp.glory.darts.domain.game.entity.CountUp;

/**
 * カウントアップのセッションデータ
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "countUpSessionData")
@SessionScoped
public class CountUpSessionData implements Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 8989991562218918348L;

	/** カウントエンティティ */
	private CountUp gameInfo = null;

	public CountUp getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(final CountUp gameInfo) {
		this.gameInfo = gameInfo;
	}
}
