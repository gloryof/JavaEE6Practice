package jp.glory.darts.domain.game.entity;

import jp.glory.darts.domain.common.Entity;
import jp.glory.darts.domain.game.GameConst;
import jp.glory.darts.domain.game.enums.ThrowingCount;
import jp.glory.darts.domain.game.enums.ThrowingNumber;
import jp.glory.darts.domain.game.value.Throwing;

public class Round implements Entity<Round> {

	/** スローイング */
	private final Throwing[] throwings = new Throwing[GameConst.THROW_COUNT];

	/** ラウンドインデックス */
	private final int roundIndex;

	/** スローイング終了フラグ */
	private boolean endThrowing = false;

	/**
	 * コンストラクタ
	 * 
	 * @param roundIndex ラウンドインデックス
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
	 * ラウンドのポイントを取得する
	 * 
	 * @return ポイント
	 */
	public int getPoint() {

		int point = 0;
		for (final Throwing throwing : throwings) {

			point += throwing.getPoint();
		}

		return point;
	}

	/**
	 * スローイングの情報を取得する
	 * 
	 * @param throwNumber
	 * @return スローイング
	 */
	public Throwing getThrowing(final int throwNumber) {

		return throwings[throwNumber].copyValue();
	}

	/**
	 * 全てのスローイングを取得する
	 * 
	 * @return スローイング配列
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
	 * スローイングの点数を設定する
	 * 
	 * @param throwingIndex スローイングインデックス
	 * @param number ナンバー
	 * @param count 倍率
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
