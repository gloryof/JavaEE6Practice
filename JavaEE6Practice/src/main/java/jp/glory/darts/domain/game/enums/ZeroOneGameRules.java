package jp.glory.darts.domain.game.enums;

/**
 * �[�������̃��[�����`����
 * 
 * @author Junki Yamada
 * 
 */
public enum ZeroOneGameRules {

	GAME_301(1, 301, 10),
	GAME_501(2, 501, 15),
	GAME_701(3, 701, 15),
	GAME_901(4, 901, 20),
	GAME_1101(5, 1101, 20), ;

	/** �^�C�v�R�[�h */
	private final int typeCode;

	/** �|�C���g */
	private final int point;

	/** ���E���h�� */
	private final int round;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param typeCode �^�C�v�R�[�h
	 * @param point �|�C���g
	 * @param round ���E���h��
	 */
	private ZeroOneGameRules(final int typeCode, final int point, final int round) {

		this.typeCode = typeCode;
		this.point = point;
		this.round = round;
	}

	/**
	 * @return typeCode
	 */
	public int getTypeCode() {
		return typeCode;
	}

	/**
	 * @return point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @return round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * �^�C�v�R�[�h���烋�[�����擾����
	 * 
	 * @param typeCode �^�C�v�R�[�h
	 * @return
	 */
	public static ZeroOneGameRules convertGameRule(final int typeCode) {

		for (final ZeroOneGameRules rule : ZeroOneGameRules.values()) {

			if (rule.getTypeCode() == typeCode) {

				return rule;
			}
		}

		return null;
	}
}
