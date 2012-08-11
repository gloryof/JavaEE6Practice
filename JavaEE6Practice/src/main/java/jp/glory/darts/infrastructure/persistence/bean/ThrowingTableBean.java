package jp.glory.darts.infrastructure.persistence.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import jp.glory.darts.infrastructure.persistence.key.ThrowingTableKey;

/**
 * �X���[�C���O�e�[�u��Bean
 * 
 * @author Junki Yamada
 * 
 */
@Entity
@Table(name = "throwing")
@IdClass(value = ThrowingTableKey.class)
@NamedQueries({
	@NamedQuery(name = ThrowingTableBean.QUERY_NAME_FIND_BY_GAME_ID, query = "SELECT t FROM ThrowingTableBean t WHERE t.gameId = :gameId")
})
@Cacheable(value = false)
public class ThrowingTableBean implements Serializable {

	/**
	 * �V���A���o�[�W����UID
	 */
	private static final long serialVersionUID = -1403006210723825138L;

	/** �N�G�����ړ��� */
	private static final String QUERY_NAME_HEADER = "Throwing.";

	/** �N�G�����F�Q�[��ID���� */
	public static final String QUERY_NAME_FIND_BY_GAME_ID = ThrowingTableBean.QUERY_NAME_HEADER + "findByGameId";

	/** �Q�[��ID */
	@Id
	@Column(name = "game_id")
	private long gameId = 0;

	/** ���E���h�C���f�b�N�X */
	@Id
	@Column(name = "round_index")
	private int roundIndex = 0;

	/** �X���[�C���O�C���f�b�N�X */
	@Id
	@Column(name = "throwing_index")
	private int throwingIndex = 0;

	/** �i���o�[ */
	private int number = 0;

	/** �{�� */
	private int count = 0;

	public long getGameId() {
		return gameId;
	}

	public void setGameId(final long gameId) {
		this.gameId = gameId;
	}

	public long getRoundIndex() {
		return roundIndex;
	}

	public void setRoundIndex(final int round) {
		this.roundIndex = round;
	}

	public int getThrowingIndex() {
		return throwingIndex;
	}

	public void setThrowingIndex(final int index) {
		this.throwingIndex = index;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}

	public int getCount() {
		return count;
	}

	public void setCount(final int count) {
		this.count = count;
	}
}
