package springJr.foodbasket.domain;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.FoodStatus;
import springJr.foodbasket.domain.food.StoreWay;
import springJr.foodbasket.domain.food.dto.FoodSaveRequestDto;
import springJr.foodbasket.repository.FoodRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class FoodService {

	private final FoodRepository foodRepository;

	public List<Food> findAll() {
		return foodRepository.findAll();
	}

	public List<Food> findByCategory(Category category) {
		return foodRepository.findByCategory(category);
	}

	public List<Food> findByStoreWay(StoreWay storeWay) {
		return foodRepository.findByStoreWay(storeWay);
	}
	public List<Food> findByFoodStatus(FoodStatus foodStatus) {
		return foodRepository.findByFoodStatus(foodStatus);
	}

	@Transactional
	public Long addFood(FoodSaveRequestDto requestDto) {
		Food saveFood = foodRepository.save(requestDto.toEntity());
		return saveFood.getId();
	}

}
