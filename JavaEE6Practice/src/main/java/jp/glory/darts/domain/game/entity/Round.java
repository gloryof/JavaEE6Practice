package jp.glory.darts.domain.game.entity;

import jp.glory.darts.domain.common.Entity;
import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.value.Throwing;

public class Round implements Entity<Round> {

	/** �X���[�C���O */
	private final Throwing[] throwings = new Throwing[GameConst.THROW_COUNT];

	/** ���E���h�C���f�b�N�X */
	private final int roundIndex;

	/** �X���[�C���O�I���t���O */
	private boolean endThrowing = false;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param roundIndex ���E���h�C���f�b�N�X
	 */
	public Round(final int roundIndex) {

		this.roundIndex = roundIndex;

		for (int i = 0; i < throwings.length; i++) {

			throwings[i] = new Throwing(ThrowingNumber.OUT_BORD, ThrowingCount.OUT);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSame(final Round other) {

		if (other == null) {

			return false;
		}

		if (roundIndex != other.getRoundIndex()) {

			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Round copyEntity() {

		final Round entity = new Round(roundIndex);
		entity.setEndThrowing(endThrowing);

		for (int i = 0; i < throwings.length; i++) {

			entity.setThrowingPoint(i, throwings[i].getNumber(), throwings[i].getCount());
		}

		return entity;
	}

	/**
	 * ���E���h�̃|�C���g���擾����
	 * 
	 * @return �|�C���g
	 */
	public int getPoint() {

		int point = 0;
		for (final Throwing throwing : throwings) {

			point += throwing.getPoint();
		}

		return point;
	}

	/**
	 * �X���[�C���O�̏����擾����
	 * 
	 * @param throwNumber
	 * @return �X���[�C���O
	 */
	public Throwing getThrowing(final int throwNumber) {

		return throwings[throwNumber].copyValue();
	}

	/**
	 * �S�ẴX���[�C���O���擾����
	 * 
	 * @return �X���[�C���O�z��
	 */
	public Throwing[] getThrowings() {

		final Throwing[] returnArray = new Throwing[throwings.length];

		int index = 0;
		for (final Throwing throwing : throwings) {

			returnArray[index] = throwing.copyValue();

			index++;
		}

		return returnArray;
	}

	/**
	 * �X���[�C���O�̓_����ݒ肷��
	 * 
	 * @param throwingIndex �X���[�C���O�C���f�b�N�X
	 * @param number �i���o�[
	 * @param count �{��
	 */
	public void setThrowingPoint(final int throwingIndex, final ThrowingNumber number, final ThrowingCount count) {

		throwings[throwingIndex] = new Throwing(number, count);
	}

	public int getRoundIndex() {
		return roundIndex;
	}

	public boolean isEndThrowing() {
		return endThrowing;
	}

	public void setEndThrowing(final boolean endThrowing) {
		this.endThrowing = endThrowing;
	}

}
