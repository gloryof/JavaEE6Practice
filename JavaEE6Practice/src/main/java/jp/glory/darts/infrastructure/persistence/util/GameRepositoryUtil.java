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
 * スローイングエンティティコンバータ
 * 
 * @author Junki Yamada
 * 
 */
@Singleton
public class GameRepositoryUtil {

	/**
	 * スローイングテーブルBeanリストを作成する
	 * 
	 * @param gameId ゲームID
	 * @param rounds ラウンド配列
	 * @return スローイングテーブルBeanリスト
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
	 * 各ラウンドにポイントを設定する
	 * 
	 * @param entity エンティティ
	 * @param throwingList スローイングテーブルBeanリスト
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
	 * エンティティからスローイングテーブルBeanに変換する。
	 * 
	 * @param gameId ゲームID
	 * @param roundIndex ラウンドインデックス
	 * @param throwinIndex スローイングインデックス
	 * @param value
	 * @return スローイングテーブルBean
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
