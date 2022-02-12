package springJr.foodbasket.domain.food.dto;

import java.time.LocalDate;

import lombok.Getter;
import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.StoreWay;

@Getter
public class FoodServletDto {
	protected String name;
	protected StoreWay storeWay;
	protected Category category;
	protected int quantity;
	protected LocalDate saveAt;
	protected LocalDate expireAt;
}
