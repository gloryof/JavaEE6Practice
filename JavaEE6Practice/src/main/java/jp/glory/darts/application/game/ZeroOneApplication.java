package jp.glory.darts.application.game;

import java.util.List;

import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;

/**
 * �[�������A�v���P�[�V�����C���^�[�t�F�C�X
 * 
 * @author Junki Yamada
 * 
 */
public interface ZeroOneApplication {

	/**
	 * ���ʂ�ۑ�����
	 * 
	 * @param entity �[�������G���e�B�e�B
	 */
	void entryResult(final ZeroOne entity);

	/**
	 * �Q�[��ID����[�������G���e�B�e�B���擾����
	 * 
	 * @param gameId �Q�[��ID
	 * @return �[�������G���e�B�e�B
	 */
	ZeroOne findByGameId(final long gameId);

	/**
	 * �X���[�C���O���Ȃ��̃[�������G���e�B�e�B���X�g���擾����<br/>
	 * 
	 * @param userId ���[�UID
	 * @param rule ���[��
	 * @return �[�������G���e�B�e�B���X�g
	 */
	List<ZeroOne> findHistoryInfo(final String userId, final ZeroOneGameRules rule);

}
