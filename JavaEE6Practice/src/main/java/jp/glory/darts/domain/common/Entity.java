package jp.glory.darts.domain.common;

/**
 * �G���e�B�e�B�C���^�[�t�F�C�X
 * 
 * @author Junki Yamada
 * 
 * @param <T> �G���e�B�e�B�N���X
 */
public interface Entity<T extends Entity<T>> {

	/**
	 * �G���e�B�e�B������̂��̂����`�F�b�N����
	 * 
	 * @param other
	 * @return ����̏ꍇ�Ftrue�A����łȂ��ꍇ�Ffalse
	 */
	boolean isSame(T other);

	/***
	 * ����̃G���e�B�e�B���R�s�[����
	 * 
	 * @return �R�s�[�����G���e�B�e�B
	 */
	T copyEntity();
}
