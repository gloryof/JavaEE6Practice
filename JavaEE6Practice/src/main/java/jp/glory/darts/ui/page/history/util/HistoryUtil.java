package jp.glory.darts.ui.page.history.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.domain.game.entity.Round;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.value.Throwing;
import jp.glory.darts.ui.page.history.bean.HistoryRoundBean;
import jp.glory.darts.ui.page.history.bean.HistoryThrowingBean;
import jp.glory.darts.ui.page.history.countup.bean.CountUpHistoryBean;
import jp.glory.darts.ui.page.history.zeroone.bean.ZeroOneHistoryBean;

/**
 * カウントアップ履歴ユーティリティクラス
 * 
 * @author Junki Yamada
 * 
 */
@Singleton
public class HistoryUtil implements Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -3102831241818501884L;

	/**
	 * カウントアップエンティティから画面用のBeanに変換する。
	 * 
	 * @param entity エンティティ
	 * @return 画面Bean
	 */
	public CountUpHistoryBean convertViewCountUpBean(final CountUp entity) {

		final CountUpHistoryBean returnBean = new CountUpHistoryBean();

		returnBean.setGameId(entity.getGameId());
		returnBean.setTotal(entity.getPoint());
		returnBean.setStats(entity.getStats());
		returnBean.setPlayDate(entity.getPlayDate());

		return returnBean;
	}

	/**
	 * ゼロワンエンティティから画面用のBeanに変換する。
	 * 
	 * @param entity エンティティ
	 * @return 画面Bean
	 */
	public ZeroOneHistoryBean convertViewZeroOneBean(final ZeroOne entity) {

		final ZeroOneHistoryBean returnBean = new ZeroOneHistoryBean();

		returnBean.setGameTypeCode(entity.getRule().getTypeCode());
		returnBean.setGameId(entity.getGameId());
		returnBean.setPlayDate(entity.getPlayDate());
		returnBean.setEndRoundIndex(entity.getEndRoundIndex());
		returnBean.setStats(entity.getStats());
		returnBean.setRestPoint(entity.getRestPoint());

		return returnBean;
	}

	/**
	 * ラウンドエンティティから画面用のBeanに変換する。
	 * 
	 * @param entity エンティティ
	 * @return 画面用Bean
	 */
	public HistoryRoundBean convertViewRoundBean(final Round entity) {

		final HistoryRoundBean returnBean = new HistoryRoundBean();

		returnBean.setIndex(entity.getRoundIndex());
		returnBean.setPoint(entity.getPoint());

		final List<HistoryThrowingBean> throwingList = new ArrayList<>();
		int index = 0;
		for (final Throwing value : entity.getThrowings()) {

			throwingList.add(convertViewThrowingBean(index, value));
			index++;
		}
		returnBean.setThrowiList(throwingList);

		return returnBean;
	}

	/**
	 * スローイング値オブジェクトから画面用Beanに変換する。
	 * 
	 * @param throwingIndex スローイングインデックス
	 * @param value 値オブジェクト
	 * @return 画面用Bean
	 */
	private HistoryThrowingBean convertViewThrowingBean(final int throwingIndex, final Throwing value) {

		final HistoryThrowingBean returnBean = new HistoryThrowingBean();

		returnBean.setIndex(throwingIndex);
		returnBean.setNumber(value.getNumber());
		returnBean.setCount(value.getCount());

		return returnBean;
	}
}
