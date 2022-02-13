package springJr.foodbasket.utils;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import springJr.foodbasket.domain.food.field.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.field.StoreWay;
import springJr.foodbasket.web.dto.request.FoodSaveRequestDto;
import springJr.foodbasket.web.dto.request.FoodUpdateRequestDto;

@Component
public class DataUtils {

	public final LocalDate today = LocalDate.now();

	public Food banana_CHILL_FRUIT() {
		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name("바나나")
			.storeWay(StoreWay.CHILL)
			.category(Category.FRUIT)
			.quantity(3)
			.saveAt(today)
			.build();

		return requestDto.toEntity();
	}

	public Food beef_CHILL_MEAT() {
		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name("소고기")
			.storeWay(StoreWay.CHILL)
			.category(Category.MEAT)
			.quantity(3)
			.saveAt(today)
			.build();

		return requestDto.toEntity();
	}

	public Food blueberry_FREEZE_FRUIT() {
		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name("블루베리")
			.storeWay(StoreWay.FREEZE)
			.category(Category.FRUIT)
			.quantity(3)
			.saveAt(today)
			.build();

		return requestDto.toEntity();
	}

	public Food milk_CHILL_DAILY() {
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

	public Food yogurt_CHILL_DAILY() {
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

	public Food carrot_CHILL_Vegetable() {
		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name("당근")
			.storeWay(StoreWay.CHILL)
			.category(Category.VEGETABLE)
			.quantity(1)
			.saveAt(today)
			.expireAt(today.plusDays(10))
			.build();

		return requestDto.toEntity();
	}

	public Food chickenWing_2DaysBefore() {
		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name("닭날개")
			.storeWay(StoreWay.CHILL)
			.category(Category.MEAT)
			.quantity(1)
			.saveAt(today.minusDays(2))
			.expireAt(today.plusDays(1))
			.build();

		return requestDto.toEntity();
	}


	public FoodSaveRequestDto toSaveRequestDtoByName(String foodName) {
		LocalDate today = this.today;

		return FoodSaveRequestDto.builder()
			.name(foodName)
			.storeWay(StoreWay.CHILL)
			.category(Category.FRUIT)
			.quantity(3)
			.saveAt(today)
			.expireAt(today.plusDays(3))
			.build();
	}

	public FoodUpdateRequestDto toUpdateRequestDtoByNameCategory(String updateName, Category updateCategory) {
		LocalDate today = this.today;

		return FoodUpdateRequestDto.builder()
			.name(updateName)
			.storeWay(StoreWay.CHILL)
			.category(updateCategory)
			.quantity(3)
			.saveAt(today)
			.expireAt(today.plusDays(3))
			.build();
	}
}
