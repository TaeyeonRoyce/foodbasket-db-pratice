package springJr.foodbasket.domain.food.dto;

import static springJr.foodbasket.domain.food.FoodStatus.*;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springJr.foodbasket.domain.food.FoodStatus;

@Getter
@NoArgsConstructor
public class FoodSaveDto extends FoodSaveRequestDto {

	private FoodStatus foodStatus;
	private LocalDate lastUpdateDate;


	public FoodSaveDto(FoodSaveRequestDto requestDto) {
		this.name = requestDto.getName();
		this.storeWay = requestDto.getStoreWay();
		this.category = requestDto.getCategory();
		this.quantity = requestDto.getQuantity();
		this.saveAt = requestDto.getSaveAt();
		this.expireAt = requestDto.getExpireAt();
		this.foodStatus = foodStatusByDate(saveAt, expireAt);
		this.lastUpdateDate = saveAt;
	}
}
