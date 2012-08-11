package jp.glory.darts.ui.page.admin.user;

/**
 * ���[�U�ҏW��ʃy�[�W�C���^�[�t�F�C�X
 * 
 * @author Junki Yamada
 * 
 */
public interface EditUserPage {

	/**
	 * �Q�ƕ\���p�̉�ʕ\���������s���B<br/>
	 * ���[�U�ҏW��ʂɑJ�ڂ���
	 * 
	 * @param userId ���[�UID
	 * @return ��ʑJ�ڃL�[
	 */
	String initForView(String userId);

	/**
	 * �o�^�p�̉�ʕ\���������s���B<br/>
	 * ���[�U�ҏW��ʂɑJ�ڂ���
	 * 
	 * @return ��ʑJ�ڃL�[
	 */
	String initForEntry();

	/**
	 * �X�V�p�̉�ʕ\���������s���B<br/>
	 * ���[�U�ҏW��ʂɑJ�ڂ���
	 * 
	 * @param userId ���[�UID
	 * @return ��ʑJ�ڃL�[
	 */
	String initForUpdate(String userId);

	/**
	 * ���͂������e�ōX�V������<br/>
	 * 
	 * @return ��ʑJ�ڃL�[
	 */
	String update();
}
