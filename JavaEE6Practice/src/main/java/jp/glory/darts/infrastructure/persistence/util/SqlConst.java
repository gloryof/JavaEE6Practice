package jp.glory.darts.infrastructure.persistence.util;

/**
 * SQL�萔
 * 
 * @author Junki Yamada
 * 
 */
public interface SqlConst {

	/** �Q�[��ID�擾SQL */
	String GAME_SEQ_SQL = "SELECT nextval('game_id_seq')";
}
