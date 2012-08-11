package jp.glory.darts.domain.game.enums;

/**
 * �i���o�[
 * 
 * @author Junki Yamada
 * 
 */
public enum ThrowingNumber {

	OUT_BORD(0, "-"),
	ONE(1, "1"),
	TWO(2, "2"),
	THREE(3, "3"),
	FOUR(4, "4"),
	FIVE(5, "5"),
	SIX(6, "6"),
	SEVEN(7, "7"),
	EIGHT(8, "8"),
	NINE(9, "9"),
	TEN(10, "10"),
	ELEVEN(11, "11"),
	TWELVE(12, "12"),
	THIRTEEN(13, "13"),
	FOURTEEN(14, "14"),
	FIFTEEN(15, "15"),
	SIXTEEN(16, "16"),
	SEVENTEEN(17, "17"),
	EIGHTEEN(18, "18"),
	NINETEEN(19, "19"),
	TWENTY(20, "20"),
	BULL(25, "BULL"), ;

	/** �i���o�[ */
	private final int number;

	/** ���x�� */
	private final String label;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param number �i���o�[
	 * @param label ���x��
	 */
	private ThrowingNumber(final int number, final String label) {

		this.number = number;
		this.label = label;
	}

	public int getNumber() {
		return number;
	}

	public String getLabel() {
		return label;
	}

	/**
	 * ���l����i���o�[�ɕϊ�����
	 * 
	 * @param paramNumber ���l
	 * @return �i���o�[
	 */
	public static ThrowingNumber convertThrowingNumber(final int paramNumber) {

		for (final ThrowingNumber throwing : ThrowingNumber.values()) {

			if (throwing.getNumber() == paramNumber) {

				return throwing;
			}
		}

		return null;
	}
}
