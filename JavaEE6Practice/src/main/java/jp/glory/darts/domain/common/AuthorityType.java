package jp.glory.darts.domain.common;

/**
 * �����^�C�v
 * 
 * @author Junki Yamada
 * 
 */
public enum AuthorityType {

	USER(0, "���[�U"),
	ADMINISTRATOR(1, "�V�X�e���Ǘ���");

	/** �R�[�h�l */
	private final int codeValue;

	/** ������ */
	private final String name;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param codeValue �R�[�h�l
	 */
	private AuthorityType(final int codeValue, final String name) {

		this.codeValue = codeValue;
		this.name = name;
	}

	public int getCodeValue() {
		return codeValue;
	}

	public String getName() {
		return name;
	}

	/**
	 * �����^�C�v�𐶐�����
	 * 
	 * @param codeValue �R�[�h�l
	 * @return �����^�C�v
	 */
	public static AuthorityType createAuthorityType(final int codeValue) {

		for (final AuthorityType type : AuthorityType.values()) {

			if (type.getCodeValue() == codeValue) {

				return type;
			}
		}

		return null;
	}
}
