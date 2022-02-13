package springJr.foodbasket.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springJr.foodbasket.domain.food.field.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.field.FoodFilterable;
import springJr.foodbasket.domain.food.field.FoodStatus;
import springJr.foodbasket.domain.food.field.StoreWay;
import springJr.foodbasket.web.dto.request.FoodFilterRequestDto;
import springJr.foodbasket.web.dto.request.FoodSaveRequestDto;
import springJr.foodbasket.web.dto.request.FoodUpdateRequestDto;
import springJr.foodbasket.web.dto.response.FoodResponseDto;
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
		Food food = foodRepository.findById(id).orElseThrow(NullPointerException::new);
		return FoodResponseDto.builder()
			.entity(food)
			.build();
	}

	public List<FoodResponseDto> findFoods(FoodFilterRequestDto requestDto) {
		FoodFilterable filterWay = requestDto.getFilterWay();
		if (filterWay == null) {
			return findAll();
		}
		return toResponseDto(findByFilter(filterWay));
	}

	private List<Food> findByFilter(FoodFilterable filter) {

		if (filter instanceof Category) {
			return foodRepository.findByCategory((Category)filter);
		} else if (filter instanceof StoreWay) {
			return foodRepository.findByStoreWay((StoreWay)filter);
		} else if (filter instanceof FoodStatus) {
			return foodRepository.findByFoodStatus((FoodStatus)filter);
		}
		return null;
	}

	public Long saveFood(FoodSaveRequestDto requestDto) {
		Food food = requestDto.toEntity();
		return foodRepository.save(food).getId();
	}

	public void updateFoodById(Long id, FoodUpdateRequestDto requestDto) {
		Food food = foodRepository.findById(id).orElseThrow(NullPointerException::new);
		food.updateByRequestDto(requestDto);
	}

	public Long deleteFoodById(Long id) {
		foodRepository.deleteById(id);
		return id;
	}

	private List<FoodResponseDto> toResponseDto(List<Food> foods) {
		return foods.stream()
			.map(food -> new FoodResponseDto(food))
			.collect(Collectors.toList());
	}

}