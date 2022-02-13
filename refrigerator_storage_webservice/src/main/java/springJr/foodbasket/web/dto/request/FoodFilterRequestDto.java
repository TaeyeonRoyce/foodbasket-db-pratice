package springJr.foodbasket.web.dto.request;

import lombok.Getter;
import lombok.Setter;
import springJr.foodbasket.domain.food.field.Category;
import springJr.foodbasket.domain.food.field.FoodFilterable;
import springJr.foodbasket.domain.food.field.FoodStatus;
import springJr.foodbasket.domain.food.field.StoreWay;

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
