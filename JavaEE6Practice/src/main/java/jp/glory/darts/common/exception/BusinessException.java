package jp.glory.darts.common.exception;

import java.util.List;

import jp.glory.darts.common.error.ErrorInfo;

/**
 * �Ɩ���O<br/>
 * �Ɩ��゠�蓾��G���[�̏ꍇ�͂��̗�O���X���[����B
 * 
 * @author Admin
 * 
 */
public class BusinessException extends Exception {

	/**
	 * �V���A���o�[�W����UID
	 */
	private static final long serialVersionUID = 4064602001528181433L;

	/** �G���[��񃊃X�g */
	private final List<ErrorInfo> errorInfoList;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param errorInfoList �G���[��񃊃X�g
	 */
	public BusinessException(final List<ErrorInfo> errorInfoList) {

		this.errorInfoList = errorInfoList;
	}

	public List<ErrorInfo> getErrorInfoList() {
		return errorInfoList;
	}
}
