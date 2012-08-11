package jp.glory.darts.ui.page.game.countup;

/**
 * �J�E���g�A�b�v�y�[�W�C���^�[�t�F�C�X
 * 
 * @author Junki Yamada
 * 
 */
public interface CountUpPage {

	/**
	 * �J�E���g�A�b�v�y�[�W����������<br/>
	 * ����������������A�J�E���g�A�b�v�y�[�W��\������
	 * 
	 * @return ��ʑJ�ڃL�[
	 */
	String init();

	/**
	 * �ĕ\������<br/>
	 * �J�E���g�A�b�v�y�[�W��\������B
	 * 
	 * @return ��ʑJ�ڃL�[
	 */
	String review();

	/**
	 * ���̃��E���h��\������
	 */
	void nextRound();

	/**
	 * �O�̃��E���h��\������
	 */
	void prevRound();

	/**
	 * ���Z�b�g����
	 */
	void reset();

	/**
	 * �Q�[�����I������B<br/>
	 * �ŏI���E���h�̓_���𔽉f���A���ʉ�ʂɑJ�ڂ���B
	 * 
	 * @return ��ʑJ�ڃL�[
	 */
	String endGame();
}
