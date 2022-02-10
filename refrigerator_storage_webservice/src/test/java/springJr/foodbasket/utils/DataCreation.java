package springJr.foodbasket.utils;

import java.time.LocalDate;

import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.StoreWay;

public class DataCreation {

	public static final LocalDate today = LocalDate.now();

	public static Food banana_CHILL_FRUIT() {
		return Food.builder()
				.name("바나나")
				.storeWay(StoreWay.CHILL)
				.category(Category.FRUIT)
				.quantity(3)
				.saveAt(today)
				.build();
	}

	public static Food beef_CHILL_MEAT() {
		return Food.builder()
			.name("소고기")
			.storeWay(StoreWay.CHILL)
			.category(Category.MEAT)
			.quantity(3)
			.saveAt(today)
			.build();
	}

	public static Food blueberry_FREEZE_FRUIT() {
		return Food.builder()
			.name("블루베리")
			.storeWay(StoreWay.FREEZE)
			.category(Category.FRUIT)
			.quantity(3)
			.saveAt(today)
			.build();
	}

}
