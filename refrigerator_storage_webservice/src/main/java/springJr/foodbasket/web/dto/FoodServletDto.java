package springJr.foodbasket.web.dto;

import java.time.LocalDate;

import lombok.Getter;
import springJr.foodbasket.domain.food.field.Category;
import springJr.foodbasket.domain.food.field.StoreWay;

@Getter
public class FoodServletDto {
	protected String name;
	protected StoreWay storeWay;
	protected Category category;
	protected int quantity;
	protected LocalDate saveAt;
	protected LocalDate expireAt;
}
