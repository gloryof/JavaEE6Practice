package jp.glory.darts.domain.game.repository;

import java.util.List;

import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;

/**
 * �[���������|�W�g���C���^�[�t�F�C�X
 * 
 * @author Junki Yamada
 * 
 */
public interface ZeroOneRepository {

	/**
	 * �Q�[���̌��ʂ�ۑ�����
	 * 
	 * @param entity �[�������G���e�B�e�B
	 */
	void save(final ZeroOne entity);

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
	 * @param rule �Q�[�����[��
	 * @return �[�������G���e�B�e�B���X�g
	 */
	List<ZeroOne> findNoThrowingByUserId(final String userId, final ZeroOneGameRules rule);
}
