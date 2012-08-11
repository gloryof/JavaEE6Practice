package jp.glory.darts.stub.ui.page.util;

import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.inject.Any;

import jp.glory.darts.common.error.ErrorInfo;
import jp.glory.darts.ui.page.util.JsfUtil;

@Any
public class JsfUtilStub implements JsfUtil {

	/** �G���[���}�b�v */
	private final Map<String, ErrorInfo> errorInfoMap;

	/**
	 * �R���X�g���N�^<br>
	 * �G���[���}�b�v������������
	 */
	public JsfUtilStub() {

		this.errorInfoMap = new TreeMap<String, ErrorInfo>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addErrorMessage(final String clientId, final ErrorInfo errorInfo) {

		errorInfoMap.put(clientId, errorInfo);
	}

	/**
	 * �X�^�u�ł͉������Ȃ�
	 */
	@Override
	public void clearSession() {

	}

	/**
	 * �G���[���̌�����Ԃ�
	 * 
	 * @return �G���[��񌏐�
	 */
	public int getErrorCount() {

		if (errorInfoMap != null) {

			return errorInfoMap.size();
		}

		return 0;
	}

	/**
	 * �G���[�����C���f�b�N�X�Ŏ擾����
	 * 
	 * @param paramIndex �C���f�b�N�X
	 * @return �G���[���
	 */
	public ErrorInfo getByIndex(final int paramIndex) {

		int index = 0;
		for (Map.Entry<String, ErrorInfo> entry : errorInfoMap.entrySet()) {

			if (paramIndex == index) {

				return entry.getValue();
			}

			index++;
		}

		return null;
	}

	/**
	 * �X�^�u�ł͉������Ȃ�
	 */
	@Override
	public void setLoginDateToSession() {

	}
}
