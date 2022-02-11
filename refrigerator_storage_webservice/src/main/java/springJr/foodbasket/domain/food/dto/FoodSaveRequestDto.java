package springJr.foodbasket.domain.food.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springJr.foodbasket.domain.food.Category;
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
}
