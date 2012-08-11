package jp.glory.darts.ui.page.util;

import jp.glory.darts.common.error.ErrorInfo;

/**
 * JSF���[�e�B���e�B�C���^�[�t�F�C�X
 * 
 * @author Junki Yamada
 * 
 */
public interface JsfUtil {

	/**
	 * �G���[���b�Z�[�W��ǉ�����
	 * 
	 * @param clientId �N���C�A���gID
	 * @param errorInfo �G���[���
	 */
	void addErrorMessage(String clientId, ErrorInfo errorInfo);

	/**
	 * �Z�b�V�������N���A����
	 */
	void clearSession();

	/**
	 * �Z�b�V�����Ƀ��O�C�����t��ݒ肷��
	 */
	void setLoginDateToSession();

}
