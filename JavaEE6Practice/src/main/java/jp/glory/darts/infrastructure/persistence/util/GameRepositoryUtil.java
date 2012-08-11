package jp.glory.darts.infrastructure.persistence.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Singleton;

import jp.glory.darts.domain.game.entity.AbstractDartsGame;
import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.value.Throwing;
import jp.glory.darts.infrastructure.persistence.bean.ThrowingTableBean;

/**
 * �X���[�C���O�G���e�B�e�B�R���o�[�^
 * 
 * @author Junki Yamada
 * 
 */
@Singleton
public class GameRepositoryUtil {

	/**
	 * �X���[�C���O�e�[�u��Bean���X�g���쐬����
	 * 
	 * @param gameId �Q�[��ID
	 * @param rounds ���E���h�z��
	 * @return �X���[�C���O�e�[�u��Bean���X�g
	 */
	public List<ThrowingTableBean> createThrowingList(final long gameId, final Round[] rounds) {

		final List<ThrowingTableBean> returnList = new ArrayList<>();

		for (final Round round : rounds) {

			int index = 0;
			for (final Throwing throwing : round.getThrowings()) {

				returnList.add(convertThrowingTableBeanFromEntity(gameId, round.getRoundIndex(), index, throwing));

				index++;
			}
		}

		return returnList;
	}

	/**
	 * �e���E���h�Ƀ|�C���g��ݒ肷��
	 * 
	 * @param entity �G���e�B�e�B
	 * @param throwingList �X���[�C���O�e�[�u��Bean���X�g
	 */
	public void setRoundPoint(final AbstractDartsGame entity, final List<ThrowingTableBean> throwingList) {

		final Map<Long, List<ThrowingTableBean>> resultMap = new TreeMap<>();
		for (final ThrowingTableBean throwingBean : throwingList) {

			List<ThrowingTableBean> tempList = resultMap.get(throwingBean.getRoundIndex());
			if (tempList == null) {

				tempList = new ArrayList<>();
				resultMap.put(throwingBean.getRoundIndex(), tempList);
			}

			tempList.add(throwingBean);
		}

		int roundIndex = 0;
		for (final Map.Entry<Long, List<ThrowingTableBean>> entry : resultMap.entrySet()) {

			final List<ThrowingTableBean> tempList = entry.getValue();
			final ThrowingNumber[] numbers = new ThrowingNumber[tempList.size()];
			final ThrowingCount[] counts = new ThrowingCount[tempList.size()];

			int throwingIndex = 0;
			for (final ThrowingTableBean throwingBean : tempList) {

				numbers[throwingIndex] = ThrowingNumber.convertThrowingNumber(throwingBean.getNumber());
				counts[throwingIndex] = ThrowingCount.convertThrowingCount(throwingBean.getCount());

				throwingIndex++;
			}

			entity.setRoundPoint(roundIndex, numbers, counts);
			roundIndex++;
		}
	}

	/**
	 * �G���e�B�e�B����X���[�C���O�e�[�u��Bean�ɕϊ�����B
	 * 
	 * @param gameId �Q�[��ID
	 * @param roundIndex ���E���h�C���f�b�N�X
	 * @param throwinIndex �X���[�C���O�C���f�b�N�X
	 * @param value
	 * @return �X���[�C���O�e�[�u��Bean
	 */
	private ThrowingTableBean convertThrowingTableBeanFromEntity(final long gameId, final int roundIndex,
			final int throwinIndex, final Throwing value) {

		final ThrowingTableBean returnBean = new ThrowingTableBean();

		returnBean.setGameId(gameId);
		returnBean.setRoundIndex(roundIndex);
		returnBean.setThrowingIndex(throwinIndex);

		returnBean.setNumber(value.getNumber().getNumber());
		returnBean.setCount(value.getCount().getCount());

		return returnBean;
	}
}
