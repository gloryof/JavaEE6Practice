package jp.glory.darts.application.game;

import java.util.List;

import jp.glory.darts.domain.game.entity.CountUp;

/**
 * �J�E���g�A�b�v�A�v���P�[�V�����C���^�[�t�F�C�X
 * 
 * @author Junki Yamada
 * 
 */
public interface CountUpApplication {

	/**
	 * ���ʂ�ۑ�����
	 * 
	 * @param entity �J�E���g�A�b�v�G���e�B�e�B
	 */
	void entryResult(final CountUp entity);

	/**
	 * �Q�[��ID����J�E���g�A�b�v�G���e�B�e�B���擾����
	 * 
	 * @param gameId �Q�[��ID
	 * @return �J�E���g�A�b�v�G���e�B�e�B
	 */
	CountUp findByGameId(final long gameId);

	/**
	 * �X���[�C���O���Ȃ��̃J�E���g�A�b�v�G���e�B�e�B���X�g���擾����<br/>
	 * 
	 * @param userId ���[�UID
	 * @return �J�E���g�A�b�v�G���e�B�e�B���X�g
	 */
	List<CountUp> findNoThrowingByUserId(final String userId);
}
