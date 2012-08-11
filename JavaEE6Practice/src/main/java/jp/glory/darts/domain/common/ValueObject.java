package jp.glory.darts.domain.common;

/**
 * �l�I�u�W�F�N�g�C���^�[�t�F�C�X<br>
 * 
 * @author Junki Yamada
 * 
 * @param <T> �l�I�u�W�F�N�g
 */
public interface ValueObject<T extends ValueObject<T>> {

	/**
	 * �����l���𔻒肷��B
	 * 
	 * @param other ��r�Ώےl�I�u�W�F�N�g
	 * @return ����̒l�̏ꍇ�Ftrue�A�قȂ�l�̏ꍇ�Ffalse
	 */
	boolean isSameValue(T other);

	/***
	 * ����̒l���R�s�[����
	 * 
	 * @return �l�I�u�W�F�N�g
	 */
	T copyValue();
}
