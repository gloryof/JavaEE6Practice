package jp.glory.darts.domain.common;

/**
 * 権限タイプ
 * 
 * @author Junki Yamada
 * 
 */
public enum AuthorityType {

	USER(0, "ユーザ"),
	ADMINISTRATOR(1, "システム管理者");

	/** コード値 */
	private final int codeValue;

	/** 権限名 */
	private final String name;

	/**
	 * コンストラクタ
	 * 
	 * @param codeValue コード値
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
	 * 権限タイプを生成する
	 * 
	 * @param codeValue コード値
	 * @return 権限タイプ
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
