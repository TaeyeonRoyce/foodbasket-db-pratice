package springJr.foodbasket.domain.food.dto;

import static springJr.foodbasket.domain.food.FoodStatus.*;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.FoodStatus;
import springJr.foodbasket.domain.food.StoreWay;

@Getter
@NoArgsConstructor
public class FoodSaveDto {
	private String name;
	private StoreWay storeWay;
	private Category category;
	private int quantity;
	private LocalDate saveAt;
	private LocalDate expireAt;

	private FoodStatus foodStatus;
	private LocalDate lastUpdateDate;

	@Builder
	public FoodSaveDto(String name, StoreWay storeWay, Category category, int quantity, LocalDate saveAt,
		LocalDate expireAt) {
		this.name = name;
		this.storeWay = storeWay;
		this.category = category;
		this.quantity = quantity;
		this.saveAt = saveAt;
		this.expireAt = expireAt;
		this.foodStatus = foodStatusByDate(saveAt, expireAt);
		this.lastUpdateDate = saveAt;
	}
}
