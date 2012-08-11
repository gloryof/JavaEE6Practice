package jp.glory.darts.common;

/**
 * ���b�Z�[�W���
 * 
 * @author Junki Yamada
 * 
 */
public enum MessageInfo {

	ERROR_LOGIN("���[�UID�ƃp�X���[�h�̑g�ݍ��킹���s���ł��B"),

	ERROR_USER_AUTHORITY("���[�U�Ɍ�����t�^���Ă��������B"),
	ERROR_DUPLICATE_USER_ID("���͂������[�UID�͂��łɎg���Ă��܂��B"),

	ERROR_COMMON_BLANK("{0}����͂��Ă��������B"),
	ERROR_COMMON_NO_CHECK("{0}��I�����Ă��������B"),

	INFO_UPDATE_COMPLETE("�X�V���������܂����B"), ;

	/** ���b�Z�[�W */
	private final String message;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param message ���b�Z�[�W
	 */
	private MessageInfo(final String message) {

		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
