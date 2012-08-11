package jp.glory.darts.ui.page.game.zeroone;

/**
 * �[�������y�[�W�C���^�[�t�F�C�X
 * 
 * @author Junki Yamada
 * 
 */
public interface ZeroOnePage {

	/**
	 * �[�������y�[�W����������<br/>
	 * ����������������A�[�������y�[�W��\������
	 * 
	 * @param ruleTypeCode ���[���^�C�v�R�[�h
	 * @return ��ʑJ�ڃL�[
	 */
	String init(final int ruleTypeCode);

	/**
	 * �ĕ\������<br/>
	 * �[�������y�[�W��\������B
	 * 
	 * @return ��ʑJ�ڃL�[
	 */
	String review();

	/**
	 * Next�{�^�������B<br/>
	 * �Q�[�����I�����Ă��Ȃ��ꍇ�́A���̃��E���h��\������B<br/>
	 * �Q�[�����I�����Ă���ꍇ�́A���ʉ�ʂ�\������B
	 * 
	 * @return ��ʑJ�ڃL�[
	 */
	String next();

	/**
	 * �O�̃��E���h��\������
	 */
	void prevRound();

	/**
	 * ���Z�b�g����
	 */
	void reset();
}
