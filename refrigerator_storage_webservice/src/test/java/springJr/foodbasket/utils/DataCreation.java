package springJr.foodbasket.utils;

import java.time.LocalDate;

import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.StoreWay;
import springJr.foodbasket.domain.food.dto.FoodSaveRequestDto;

public class DataCreation {

	public static final LocalDate today = LocalDate.now();

	public static Food banana_CHILL_FRUIT() {
		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name("바나나")
			.storeWay(StoreWay.CHILL)
			.category(Category.FRUIT)
			.quantity(3)
			.saveAt(today)
			.build();

		return requestDto.toEntity();
	}

	public static Food beef_CHILL_MEAT() {
		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name("소고기")
			.storeWay(StoreWay.CHILL)
			.category(Category.MEAT)
			.quantity(3)
			.saveAt(today)
			.build();

		return requestDto.toEntity();
	}

	public static Food blueberry_FREEZE_FRUIT() {
		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name("블루베리")
			.storeWay(StoreWay.FREEZE)
			.category(Category.FRUIT)
			.quantity(3)
			.saveAt(today)
			.build();

		return requestDto.toEntity();
	}

	public static Food milk_CHILL_DAILY() {
		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name("우유")
			.storeWay(StoreWay.CHILL)
			.category(Category.DIARY_PRODUCT)
			.quantity(3)
			.saveAt(today)
			.expireAt(today.plusDays(2))
			.build();

		return requestDto.toEntity();
	}

	public static Food yogurt_CHILL_DAILY() {
		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name("요거트")
			.storeWay(StoreWay.CHILL)
			.category(Category.DIARY_PRODUCT)
			.quantity(3)
			.saveAt(today)
			.expireAt(today.minusDays(2))
			.build();

		return requestDto.toEntity();
	}

	public static Food carrot_CHILL_Vegetable() {
		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name("요거트")
			.storeWay(StoreWay.CHILL)
			.category(Category.VEGETABLE)
			.quantity(1)
			.saveAt(today)
			.expireAt(today.plusDays(10))
			.build();

		return requestDto.toEntity();
	}

}
