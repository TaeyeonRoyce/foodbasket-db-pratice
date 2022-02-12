package springJr.foodbasket.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.FoodStatus;
import springJr.foodbasket.domain.food.StoreWay;
import springJr.foodbasket.domain.food.dto.request.FoodSaveRequestDto;
import springJr.foodbasket.domain.food.dto.request.FoodUpdateRequestDto;
import springJr.foodbasket.domain.food.dto.response.FoodResponseDto;
import springJr.foodbasket.repository.FoodRepository;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class FoodService {

	private final FoodRepository foodRepository;

	public List<FoodResponseDto> findAll() {
		return toResponseDto(foodRepository.findAll());
	}

	public FoodResponseDto findById(Long id) {
		Food food = foodRepository.findById(id).get();
		return FoodResponseDto.builder()
			.entity(food)
			.build();
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

	public Long addFood(FoodSaveRequestDto requestDto) {
		Food food = requestDto.toEntity();
		Long id = foodRepository.save(food).getId();
		return id;
	}

	public void updateFoodById(Long id, FoodUpdateRequestDto requestDto) {
		Food food = foodRepository.findById(id).orElseThrow(NullPointerException::new);
		food.updateByRequestDto(requestDto);
	}

	public List<FoodResponseDto> toResponseDto(List<Food> foods) {
		return foods.stream()
			.map(food -> new FoodResponseDto(food))
			.collect(Collectors.toList());
	}

}
