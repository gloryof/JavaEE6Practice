package jp.glory.darts.domain.game.enums;

/**
 * �X���[�C���O�̔{��
 * 
 * @author Junki Yamada
 * 
 */
public enum ThrowingCount {

	OUT(0, "-"),
	SINGLE(1, "S"),
	DOBULE(2, "D"),
	TRIPLE(3, "T");

	/** �{�� */
	private final int count;

	/** ���x�� */
	private final String label;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param count �{��
	 * @param label ���x��
	 */
	private ThrowingCount(final int count, final String label) {

		this.count = count;
		this.label = label;
	}

	public int getCount() {
		return count;
	}

	public String getLabel() {
		return label;
	}

	/**
	 * ���l����{���ɕϊ�����
	 * 
	 * @param countNumber ���l
	 * @return �{��
	 */
	public static ThrowingCount convertThrowingCount(final int countNumber) {

		for (final ThrowingCount throwing : ThrowingCount.values()) {

			if (throwing.getCount() == countNumber) {

				return throwing;
			}
		}

		return null;
	}
}
