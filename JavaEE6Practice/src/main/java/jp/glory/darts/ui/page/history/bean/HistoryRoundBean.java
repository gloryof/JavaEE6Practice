package jp.glory.darts.ui.page.history.bean;

import java.util.List;

/**
 * ���E���h����Bean
 * 
 * @author Junki Yamada
 * 
 */
public class HistoryRoundBean {

	/** �C���f�b�N�X */
	private int index = 0;

	/** �|�C���g */
	private int point = 0;

	/** �X���[�C���O���X�g */
	private List<HistoryThrowingBean> throwiList = null;

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
	 * @return point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point �Z�b�g���� point
	 */
	public void setPoint(final int point) {
		this.point = point;
	}

	/**
	 * @return throwiList
	 */
	public List<HistoryThrowingBean> getThrowiList() {
		return throwiList;
	}

	/**
	 * @param throwiList �Z�b�g���� throwiList
	 */
	public void setThrowiList(final List<HistoryThrowingBean> throwiList) {
		this.throwiList = throwiList;
	}

}
