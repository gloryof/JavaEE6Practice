package jp.glory.darts.domain.user.value;

import jp.glory.darts.domain.common.AuthorityType;
import jp.glory.darts.domain.common.ValueObject;

/**
 * �����l�I�u�W�F�N�g
 * 
 * @author Junki Yamada
 * 
 */
public class Authority implements ValueObject<Authority> {

	/** �����^�C�v */
	private final AuthorityType authorityType;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param authorityType
	 */
	public Authority(final AuthorityType authorityType) {

		this.authorityType = authorityType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSameValue(final Authority other) {

		if (other == null) {

			return false;
		}

		return authorityType.equals(other.getAuthorityType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Authority copyValue() {

		final Authority copyValue = new Authority(authorityType);

		return copyValue;
	}

	public AuthorityType getAuthorityType() {
		return authorityType;
	}
}
