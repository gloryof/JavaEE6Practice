package jp.glory.darts.domain.user.repository;

import java.util.List;

import jp.glory.darts.domain.user.entity.User;

/**
 * ���[�U���|�W�g��
 * 
 * @author Junki Yamada
 * 
 */
public interface UserRepository {

	/**
	 * ���[�UID�Ō�������
	 * 
	 * @param userId ���[�UId
	 * @return ���[�U�G���e�B�e�B
	 */
	User findByUserId(final String userId);

	/**
	 * �S�Ẵ��[�U�G���e�B�e�B��ԋp����
	 * 
	 * @return ���[�U�G���e�B�e�B���X�g
	 */
	List<User> findAll();

	/**
	 * �G���e�B�e�B��ۑ�����
	 * 
	 * @param entity �G���e�B�e�B
	 */
	void save(final User entity);

	/**
	 * �L����Ԃ��X�V����<br/>
	 * �p�����[�^�̃��[�UID���X�g�̑S�Ă̗L����Ԃ�<br/>
	 * �p�����[�^�̗L����ԂōX�V����
	 * 
	 * @param userIdList ���[�UID���X�g
	 * @param activeStatus �L�����
	 */
	void updateActiveStatus(final List<String> userIdList, boolean activeStatus);
}
