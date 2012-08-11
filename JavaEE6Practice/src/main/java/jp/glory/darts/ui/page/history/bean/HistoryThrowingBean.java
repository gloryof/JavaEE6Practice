package jp.glory.darts.ui.page.history.bean;

import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;

/**
 * �X���[�C���O����Bean
 * 
 * @author Junki Yamada
 * 
 */
public class HistoryThrowingBean {

	/** �C���f�b�N�X */
	private int index = 0;

	/** �i���o�[ */
	private ThrowingNumber number = null;

	/** �{�� */
	private ThrowingCount count = null;

	/**
	 * �|�C���g��ԋp����
	 * 
	 * @return �|�C���g
	 * 
	 */
	public int getPoint() {

		return number.getNumber() * count.getCount();
	}

	/**
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index �Z�b�g���� index
	 */
	public void setIndex(final int index) {
		this.index = index;
	}

	/**
	 * @return number
	 */
	public ThrowingNumber getNumber() {
		return number;
	}

	/**
	 * @param number �Z�b�g���� number
	 */
	public void setNumber(final ThrowingNumber number) {
		this.number = number;
	}

	/**
	 * @return count
	 */
	public ThrowingCount getCount() {
		return count;
	}

	/**
	 * @param count �Z�b�g���� count
	 */
	public void setCount(final ThrowingCount count) {
		this.count = count;
	}
}
