package springJr.foodbasket.domain.food.dto.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.FoodFilterable;
import springJr.foodbasket.domain.food.FoodStatus;
import springJr.foodbasket.domain.food.StoreWay;

@Getter
@Setter
public class FoodFilterRequestDto {

	private StoreWay storeWay;
	private Category category;
	private FoodStatus foodStatus;

	private FoodFilterable filter;

	public FoodFilterable getFilterWay() {
		checkNull(storeWay);
		checkNull(category);
		checkNull(foodStatus);
		return filter;
	}

	private void checkNull(FoodFilterable filterable) {
		if (filterable != null) {
			this.filter = filterable;
		}
	}
}
