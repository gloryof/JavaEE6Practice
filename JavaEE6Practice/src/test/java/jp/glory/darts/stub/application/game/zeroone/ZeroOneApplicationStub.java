package jp.glory.darts.stub.application.game.zeroone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Any;

import jp.glory.darts.application.game.ZeroOneApplication;
import jp.glory.darts.domain.game.entity.ZeroOne;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.enums.ZeroOneGameRules;

@Any
public class ZeroOneApplicationStub implements ZeroOneApplication {

	/**
	 * スタブでは何もしない
	 */
	@Override
	public void entryResult(final ZeroOne entity) {

	}

	@Override
	public ZeroOne findByGameId(final long gameId) {

		final ZeroOneGameRules rule = ZeroOneGameRules.GAME_501;
		final ZeroOne entity = new ZeroOne(gameId, "TEST_USER", rule);

		final ThrowingNumber[] numbers = {
				ThrowingNumber.ONE, ThrowingNumber.TWO, ThrowingNumber.THREE
		};

		final ThrowingCount[] counts = {
				ThrowingCount.SINGLE, ThrowingCount.DOBULE, ThrowingCount.TRIPLE
		};

		entity.setPlayDate(ZeroOneApplicationStub.getDefaultStubData());
		entity.setEndRoundIndex(rule.getRound() - 1);

		for (int i = 0; i < rule.getRound(); i++) {

			entity.setRoundPoint(i, numbers, counts);
		}
		entity.calculatePoint();

		return entity;
	}

	@Override
	public List<ZeroOne> findHistoryInfo(final String userId, final ZeroOneGameRules rule) {

		final List<ZeroOne> returnList = new ArrayList<>();

		final int[] restPoint = {
				48, 96, 144
		};

		for (int i = 0; i < 3; i++) {

			final ZeroOne entity = new ZeroOne(i, userId, rule);
			entity.setRestPoint(restPoint[i]);
			entity.setEndRoundIndex(rule.getRound() - 1);
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
	 * ゼロワンスタブデータのデフォルト日付を取得する
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
