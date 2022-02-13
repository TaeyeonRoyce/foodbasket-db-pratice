package springJr.foodbasket.web.dto.request;

import static springJr.foodbasket.domain.food.field.FoodStatus.*;

import java.time.LocalDate;

import lombok.Builder;
import lombok.NoArgsConstructor;
import springJr.foodbasket.domain.food.field.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.field.StoreWay;
import springJr.foodbasket.web.dto.FoodServletDto;

@NoArgsConstructor
public class FoodSaveRequestDto extends FoodServletDto implements FoodRequestDto {

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
