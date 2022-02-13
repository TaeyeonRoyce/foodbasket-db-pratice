package springJr.foodbasket.web.dto.request;

import java.time.LocalDate;

import lombok.Builder;
import lombok.NoArgsConstructor;
import springJr.foodbasket.domain.food.field.Category;
import springJr.foodbasket.domain.food.field.StoreWay;
import springJr.foodbasket.web.dto.FoodServletDto;

@NoArgsConstructor
public class FoodUpdateRequestDto extends FoodServletDto implements FoodRequestDto {

	@Builder
	public FoodUpdateRequestDto(String name, StoreWay storeWay, Category category, int quantity, LocalDate saveAt,
		LocalDate expireAt) {
		this.name = name;
		this.storeWay = storeWay;
		this.category = category;
		this.quantity = quantity;
		this.saveAt = saveAt;
		this.expireAt = expireAt;
	}
}
