package jp.glory.darts.infrastructure.persistence.util;

/**
 * SQL定数
 * 
 * @author Junki Yamada
 * 
 */
public interface SqlConst {

	/** ゲームID取得SQL */
	String GAME_SEQ_SQL = "SELECT nextval('game_id_seq')";
}
