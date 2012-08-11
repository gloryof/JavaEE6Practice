package jp.glory.darts.ui.page.common;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import jp.glory.darts.domain.game.entity.ZeroOne;

/**
 * �[�������̃Z�b�V�����f�[�^
 * 
 * @author Junki Yamada
 * 
 */
@Named(value = "zeroOneSessionData")
@SessionScoped
public class ZeroOneSessionData implements Serializable {

	/**
	 * �V���A���o�[�W����UID
	 */
	private static final long serialVersionUID = 5764401541397635788L;

	/** �J�E���g�G���e�B�e�B */
	private ZeroOne gameInfo = null;

	public ZeroOne getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(final ZeroOne gameInfo) {
		this.gameInfo = gameInfo;
	}

}
