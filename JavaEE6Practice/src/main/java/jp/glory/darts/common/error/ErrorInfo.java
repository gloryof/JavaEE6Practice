package jp.glory.darts.common.error;

import java.text.MessageFormat;

import jp.glory.darts.common.MessageInfo;

/**
 * �G���[���
 * 
 * @author Junki Yamada
 * 
 */
public class ErrorInfo {

	/** �G���[���b�Z�[�W��� */
	private final MessageInfo messageInfo;

	/** ���b�Z�[�W�p�����[�^ */
	private final String[] messageParam;

	/**
	 * �R���X�g���N�^<br/>
	 * ���b�Z�[�W�p�����[�^�͋�̔z���ݒ肷��
	 * 
	 * @param messageInfo �G���[���b�Z�[�W���
	 */
	public ErrorInfo(final MessageInfo messageInfo) {

		this.messageInfo = messageInfo;
		this.messageParam = new String[] {};
	}

	/**
	 * �R���X�g���N�^<br/>
	 * 
	 * @param messageInfo �G���[���b�Z�[�W���
	 * @param messageParam ���b�Z�[�W�p�����[�^
	 */
	public ErrorInfo(final MessageInfo messageInfo, final String[] messageParam) {

		this.messageInfo = messageInfo;
		this.messageParam = messageParam;
	}

	/**
	 * �G���[���b�Z�[�W���擾����<br/>
	 * �G���[���b�Z�[���̃��b�Z�[�W���G���[���b�Z�[�W�p�����[�^�ŕϊ�����B<br/>
	 * ���b�Z�[�W��񂪐ݒ肳��Ă��Ȃ��ꍇ�͋󔒂�ԋp����B
	 * 
	 * @return �G���[���b�Z�[�W
	 */
	public String getMessageValue() {

		if (messageInfo == null) {

			return "";
		}

		final MessageFormat format = new MessageFormat(messageInfo.getMessage());

		return format.format(messageParam);
	}

	public MessageInfo getMessageInfo() {
		return messageInfo;
	}

	public String[] getMessageParam() {
		return messageParam;
	}

}
