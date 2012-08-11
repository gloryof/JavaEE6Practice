package jp.glory.darts.application.user;

import java.util.List;

import jp.glory.darts.common.exception.BusinessException;
import jp.glory.darts.domain.user.entity.User;

/**
 * ���[�U�A�v���P�[�V�����C���^�[�t�F�C�X
 * 
 * @author Junki Yamada
 * 
 */
public interface UserApplication {

	/**
	 * ���[�U���擾����
	 * 
	 * @param userId ���[�UId
	 * @return ���[�U�G���e�B�e�B
	 */
	User getUser(String userId);

	/**
	 * �S�Ẵ��[�U���擾����
	 * 
	 * @return ���[�U�G���e�B�e�B���X�g
	 */
	List<User> getAllUser();

	/**
	 * ���[�U�����X�V����
	 * 
	 * @param entity ���[�U�G���e�B�e�B
	 * @throws BusinessException �Ɩ���O
	 */
	void updateUser(User entity) throws BusinessException;

	/**
	 * ���[�U����o�^����<br/>
	 * ���[�UID���g�p����Ă��邩�`�F�b�N���s���B
	 * 
	 * @param entity ���[�U�G���e�B�e�B
	 * @throws BusinessException �Ɩ���O
	 */
	void entryUser(User entity) throws BusinessException;

	/**
	 * �L����Ԃ��X�V����<br/>
	 * �p�����[�^�̃��[�UID���X�g�̃��[�U�̗L����Ԃ�ύX����B
	 * 
	 * @param userIdList ���[�UID���X�g
	 * @param activeStatus �L�����
	 */
	void updateActiveStatus(List<String> userIdList, boolean activeStatus);
}
