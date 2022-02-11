package springJr.foodbasket.domain.food.dto;

import static springJr.foodbasket.domain.food.FoodStatus.*;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.FoodStatus;
import springJr.foodbasket.domain.food.StoreWay;

@Getter
@NoArgsConstructor
public class FoodSaveRequestDto {
	protected String name;
	protected StoreWay storeWay;
	protected Category category;
	protected int quantity;
	protected LocalDate saveAt;
	protected LocalDate expireAt;

	@Builder
	public FoodSaveRequestDto(String name, StoreWay storeWay, Category category, int quantity, LocalDate saveAt,
		LocalDate expireAt) {
		this.name = name;
		this.storeWay = storeWay;
		this.category = category;
		this.quantity = quantity;
		this.saveAt = saveAt;
		this.expireAt = expireAt;
	}

	public Food toEntity() {
		return Food.builder()
			.name(name)
			.storeWay(storeWay)
			.category(category)
			.quantity(quantity)
			.saveAt(saveAt)
			.expireAt(expireAt)
			.foodStatus(foodStatusByDate(this.saveAt, this.expireAt))
			.lastUpdateDate(saveAt)
			.build();
	}
}
