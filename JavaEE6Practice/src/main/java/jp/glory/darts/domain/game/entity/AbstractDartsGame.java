package jp.glory.darts.domain.game.entity;

import java.util.Date;

import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;

/**
 * �Q�[���N���X
 * 
 * @author Junki Yamada
 * 
 */
public abstract class AbstractDartsGame {

	/** �Q�[��ID */
	private final long gameId;

	/** �v���C���[���[�UID */
	private final String userId;

	/** ���E���h���X�g */
	private final Round[] rounds;

	/** �Q�[���v���[���� */
	private Date playDate = null;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param gameId �Q�[��ID
	 * @param userId ���[�UId
	 * @param rounds ���E���h���X�g
	 */
	protected AbstractDartsGame(final long gameId, final String userId, final Round[] rounds) {

		this.gameId = gameId;
		this.userId = userId;
		this.rounds = rounds;
		initRound();
	}

	/**
	 * ���E���h�̓��_���Z�b�g����<br/>
	 * ���_��ݒ肵�����E���h�̏I���t���O���I���ɂ���B
	 * 
	 * @param roundIndex ���E���h�̃C���f�b�N�X
	 * @param number �i���o�[�̔z��
	 * @param count �{���̔z��
	 */
	public final void setRoundPoint(final int roundIndex, final ThrowingNumber[] number, final ThrowingCount[] count) {

		final Round round = rounds[roundIndex];
		for (int i = 0; i < GameConst.THROW_COUNT; i++) {

			round.setThrowingPoint(i, number[i], count[i]);
			round.setEndThrowing(true);
		}
	}

	/**
	 * ���E���h���擾����<br/>
	 * 
	 * @param roundIndex ���E���h�C���f�b�N�X
	 * @return ���E���h�G���e�B�e�B
	 */
	public final Round getRound(final int roundIndex) {

		return rounds[roundIndex].copyEntity();
	}

	/**
	 * �X�^�b�c���擾����
	 * 
	 * @return �X�^�b�c
	 */
	abstract public double getStats();

	/**
	 * �Q�[���̏�񃊃Z�b�g����
	 */
	abstract public void reset();

	/**
	 * �Q�[���̃|�C���g���v�Z����
	 */
	abstract public void calculatePoint();

	/**
	 * ���E���h�̏�������������
	 */
	protected final void initRound() {

		for (int i = 0; i < rounds.length; i++) {

			rounds[i] = new Round(i);
		}
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setPlayDate(final Date playDate) {
		this.playDate = playDate;
	}

	public long getGameId() {
		return gameId;
	}

	public String getUserId() {
		return userId;
	}

	public Round[] getRounds() {

		final Round[] returnRounds = new Round[rounds.length];

		int index = 0;
		for (final Round round : rounds) {

			returnRounds[index] = round.copyEntity();

			index++;
		}

		return returnRounds;
	}
}
