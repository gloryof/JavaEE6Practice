package jp.glory.darts.stub.application.game.countup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Any;

import jp.glory.darts.application.game.CountUpApplication;
import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.entity.CountUp;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;

/**
 * カウントアップアプリケーションスタブ
 * 
 * @author Junki Yamada
 * 
 */
@Any
public class CountUpApplicationStub implements CountUpApplication {

	/**
	 * スタブでは何もしない
	 */
	@Override
	public void entryResult(final CountUp entity) {

	}

	/**
	 * テストデータのエンティティを返却する
	 */
	@Override
	public CountUp findByGameId(final long gameId) {

		final CountUp entity = new CountUp(gameId, "TEST_UER");

		final ThrowingNumber[] numbers = {
				ThrowingNumber.ONE, ThrowingNumber.TWO, ThrowingNumber.THREE
		};

		final ThrowingCount[] counts = {
				ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
		};

		for (int i = 0; i < GameConst.COUNT_UP_ROUND_COUNT; i++) {

			entity.setRoundPoint(i, numbers, counts);
		}

		entity.calculatePoint();
		entity.setPlayDate(getDefaultStubData());

		return entity;
	}

	/**
	 * 空のリストを返却する
	 */
	@Override
	public List<CountUp> findNoThrowingByUserId(final String userId) {

		final List<CountUp> returnList = new ArrayList<>();

		final int[] totalArray = {
				48, 96, 144
		};

		for (int i = 0; i < 3; i++) {

			final CountUp entity = new CountUp(i, userId);
			entity.setPoint(totalArray[i]);

			final Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 2012);
			cal.set(Calendar.MONTH, 3);
			cal.set(Calendar.DAY_OF_MONTH, 12);
			cal.set(Calendar.HOUR_OF_DAY, 10);
			cal.set(Calendar.MINUTE, 15);
			cal.set(Calendar.SECOND, i);
			cal.set(Calendar.MILLISECOND, 0);

			entity.setPlayDate(cal.getTime());

			returnList.add(entity);
		}

		return returnList;
	}

	/**
	 * カウントアップスタブデータのデフォルト日付を取得する
	 * 
	 * @return
	 */
	public static Date getDefaultStubData() {

		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2012);
		cal.set(Calendar.MONTH, 3);
		cal.set(Calendar.DAY_OF_MONTH, 12);
		cal.set(Calendar.HOUR_OF_DAY, 10);
		cal.set(Calendar.MINUTE, 15);
		cal.set(Calendar.SECOND, 12);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}
}
