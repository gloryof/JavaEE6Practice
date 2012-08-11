package jp.glory.darts.infrastructure.persistence.util;

/**
 * SQL’è”
 * 
 * @author Junki Yamada
 * 
 */
public interface SqlConst {

	/** ƒQ[ƒ€IDæ“¾SQL */
	String GAME_SEQ_SQL = "SELECT nextval('game_id_seq')";
}
