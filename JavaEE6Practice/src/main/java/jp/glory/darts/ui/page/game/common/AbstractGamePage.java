package jp.glory.darts.ui.page.game.common;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.value.Throwing;

public abstract class AbstractGamePage {

	/** ���E���h�C���f�b�N�X */
	private int roundIndex = 0;

	/** ��꓊�i���o�[ */
	private int throwOneNumber = 0;

	/** ��꓊�{�� */
	private int throwOneCount = 0;

	/** ��񓊃i���o�[ */
	private int throwTwoNumber = 0;

	/** ��񓊔{�� */
	private int throwTwoCount = 0;

	/** ��O���i���o�[ */
	private int throwThreeNumber = 0;

	/** ��O���{�� */
	private int throwThreeCount = 0;

	/**
	 * �i���o�[�̃��X�g���擾����
	 * 
	 * @return �i���o�[���X�g
	 */
	public List<SelectItem> getNumberList() {

		final List<SelectItem> returnList = new ArrayList<>();

		for (final ThrowingNumber number : ThrowingNumber.values()) {

			returnList.add(new SelectItem(number.getNumber(), number.getLabel()));
		}

		return returnList;
	}

	/**
	 * �{���̃��X�g���擾����
	 * 
	 * @return �{�����X�g
	 */
	public List<SelectItem> getCountList() {

		final List<SelectItem> returnList = new ArrayList<>();

		for (final ThrowingCount count : ThrowingCount.values()) {

			returnList.add(new SelectItem(count.getCount(), count.getLabel()));
		}

		return returnList;
	}

	/**
	 * �Q�[���̋��ʍ��ڂ�����������
	 */
	protected void initCommonProperty() {

		roundIndex = 0;

		throwOneNumber = 0;
		throwOneCount = 0;
		throwTwoNumber = 0;
		throwTwoCount = 0;
		throwThreeNumber = 0;
		throwThreeCount = 0;
	}

	/**
	 * ���E���h�̃C���f�b�N�X�����Z����
	 */
	protected void addRoundIndex() {

		roundIndex++;
	}

	/**
	 * ���E���h�̃C���f�b�N�X�����Z����
	 */
	protected void substractRoundIndex() {

		roundIndex--;
	}

	/**
	 * �i���o�[�z����擾����
	 * 
	 * @return �i���o�[�z��
	 */
	protected ThrowingNumber[] getNumberArray() {

		final int[] numberIntValues = {
				throwOneNumber, throwTwoNumber, throwThreeNumber
		};
		final ThrowingNumber[] numberArray = new ThrowingNumber[GameConst.THROW_COUNT];

		int numberIndex = 0;
		for (final int numberIntValue : numberIntValues) {

			numberArray[numberIndex] = ThrowingNumber.convertThrowingNumber(numberIntValue);

			numberIndex++;
		}

		return numberArray;
	}

	/**
	 * �{���z����擾����
	 * 
	 * @return �{���z��
	 */
	protected ThrowingCount[] getCountArray() {

		final int[] countIntValues = {
				throwOneCount, throwTwoCount, throwThreeCount
		};
		final ThrowingCount[] countArray = new ThrowingCount[GameConst.THROW_COUNT];

		int numberIndex = 0;
		for (final int numberIntValue : countIntValues) {

			countArray[numberIndex] = ThrowingCount.convertThrowingCount(numberIntValue);

			numberIndex++;
		}

		return countArray;
	}

	/**
	 * ���ɕ\�����郉�E���h�̏���ݒ肷��
	 * 
	 * @param round ���E���h�G���e�B�e�B
	 */
	protected void setRoundPoint(final Round round) {

		final Throwing firstThrowing = round.getThrowing(0);
		throwOneNumber = firstThrowing.getNumber().getNumber();
		throwOneCount = firstThrowing.getCount().getCount();

		final Throwing secondThrowing = round.getThrowing(1);
		throwTwoNumber = secondThrowing.getNumber().getNumber();
		throwTwoCount = secondThrowing.getCount().getCount();

		final Throwing thridThrowing = round.getThrowing(2);
		throwThreeNumber = thridThrowing.getNumber().getNumber();
		throwThreeCount = thridThrowing.getCount().getCount();
	}

	public int getRoundIndex() {
		return roundIndex;
	}

	public void setRoundIndex(final int roundIndex) {
		this.roundIndex = roundIndex;
	}

	public int getThrowOneNumber() {
		return throwOneNumber;
	}

	public void setThrowOneNumber(final int throwOneNumber) {
		this.throwOneNumber = throwOneNumber;
	}

	public int getThrowOneCount() {
		return throwOneCount;
	}

	public void setThrowOneCount(final int throwOneCount) {
		this.throwOneCount = throwOneCount;
	}

	public int getThrowTwoNumber() {
		return throwTwoNumber;
	}

	public void setThrowTwoNumber(final int throwTwoNumber) {
		this.throwTwoNumber = throwTwoNumber;
	}

	public int getThrowTwoCount() {
		return throwTwoCount;
	}

	public void setThrowTwoCount(final int throwTwoCount) {
		this.throwTwoCount = throwTwoCount;
	}

	public int getThrowThreeNumber() {
		return throwThreeNumber;
	}

	public void setThrowThreeNumber(final int throwThreeNumber) {
		this.throwThreeNumber = throwThreeNumber;
	}

	public int getThrowThreeCount() {
		return throwThreeCount;
	}

	public void setThrowThreeCount(final int throwThreeCount) {
		this.throwThreeCount = throwThreeCount;
	}
}
