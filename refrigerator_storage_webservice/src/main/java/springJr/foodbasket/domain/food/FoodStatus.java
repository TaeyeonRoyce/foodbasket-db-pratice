package springJr.foodbasket.domain.food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum FoodStatus {
	NONE,
	FRESH,
	CAUTION,
	WARN,
	EXPIRED;

	public static FoodStatus foodStatusByDate(LocalDate today, LocalDate expireAt) {
		if (expireAt == null) {
			return FoodStatus.NONE;
		}
		long betweenDays = ChronoUnit.DAYS.between(today, expireAt);
		switch ((int) betweenDays) {
			case 0:
			case 1:
				return WARN;
			case 2:
			case 3:
				return CAUTION;
			default:
				if (betweenDays < 0) {
					return EXPIRED;
				}
				return FRESH;
		}
	}
}
