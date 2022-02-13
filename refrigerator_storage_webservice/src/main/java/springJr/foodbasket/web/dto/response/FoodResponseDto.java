package springJr.foodbasket.web.dto.response;

import static springJr.foodbasket.domain.food.field.FoodStatus.*;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.field.FoodStatus;
import springJr.foodbasket.web.dto.FoodServletDto;

@Getter
@NoArgsConstructor
public class FoodResponseDto extends FoodServletDto {
	private FoodStatus foodStatus;
	private LocalDate lastUpdateDate;

	@Builder
	public FoodResponseDto(Food entity) {
		this.name = entity.getName();
		this.storeWay = entity.getStoreWay();
		this.category = entity.getCategory();
		this.quantity = entity.getQuantity();
		this.saveAt = entity.getSaveAt();
		this.expireAt = entity.getExpireAt();

		updateFoodStatus(entity);
	}

	private void updateFoodStatus(Food entity) {
		LocalDate today = LocalDate.now();

		if (entity.getLastUpdateDate()
				.isEqual(today)) {
			this.foodStatus = entity.getFoodStatus();
			this.lastUpdateDate = today;
			return;
		}
		FoodStatus updatedFoodStatus = foodStatusByDate(today, this.expireAt);
		this.foodStatus = updatedFoodStatus;
		this.lastUpdateDate = today;

		entity.dailyUpdate(updatedFoodStatus, today);
	}
}
