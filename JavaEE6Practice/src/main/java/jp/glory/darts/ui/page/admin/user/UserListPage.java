package jp.glory.darts.ui.page.admin.user;

/**
 * ���[�U�ꗗ�y�[�W�C���^�[�t�F�C�X
 * 
 * @author Junki Yamada
 * 
 */
public interface UserListPage {

	/**
	 * ��ʂ̏����\������<br/>
	 * ���[�U�̃��X�g���擾���A�y�[�W�\���p�̃f�[�^�Ƃ��ĕێ�����B<br/>
	 * �����I����A���[�U�ꗗ�y�[�W�̉�ʑD�L�[��ԋp����B
	 * 
	 * @return ��ʑJ�ڃL�[
	 */
	String init();

	/**
	 * �L����Ԃ��X�V����
	 * 
	 * @param activeStatus �L�����
	 */
	void changeActive(boolean activeStatus);
}
