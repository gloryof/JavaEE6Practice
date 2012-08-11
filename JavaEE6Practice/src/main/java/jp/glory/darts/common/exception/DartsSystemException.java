package jp.glory.darts.common.exception;

/**
 * �V�X�e����O<br/>
 * ���A�s�\�ȃG���[�̏ꍇ�͂��̗�O���X���[����B
 * 
 * @author Admin
 * 
 */
public class DartsSystemException extends RuntimeException {

	/**
	 * �V���A���o�[�W����UID
	 */
	private static final long serialVersionUID = -3791143828005602993L;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param message ���b�Z�[�W
	 */
	public DartsSystemException(final String message) {

		super(message);
	}

	/**
	 * �R���X�g���N�^
	 * 
	 * @param message ���b�Z�[�W
	 * @param cause ������O
	 */
	public DartsSystemException(final String message, final Throwable cause) {

		super(message, cause);
	}
}
