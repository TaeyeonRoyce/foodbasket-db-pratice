package springJr.foodbasket.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.field.Category;
import springJr.foodbasket.domain.food.field.StoreWay;
import springJr.foodbasket.repository.FoodRepository;
import springJr.foodbasket.utils.DataUtils;
import springJr.foodbasket.web.dto.request.FoodFilterRequestDto;
import springJr.foodbasket.web.dto.request.FoodRequestDto;
import springJr.foodbasket.web.dto.request.FoodSaveRequestDto;
import springJr.foodbasket.web.dto.request.FoodUpdateRequestDto;
import springJr.foodbasket.web.dto.response.FoodResponseDto;

@DisplayName("FoodService_테스트")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FoodServiceTest {

	@Autowired
	private DataUtils dataUtils;

	@Autowired
	private FoodService foodService;

	@Autowired
	private FoodRepository foodRepository;

	@AfterEach
	void deleteData() {
		foodRepository.deleteAll();
	}

	@Test
	public void Id_조회() {
	    //given
		Food food = dataUtils.banana_CHILL_FRUIT();
		Long id = foodRepository.save(food).getId();

		//when
		FoodResponseDto responseDto = foodService.findById(id);

		//then
		assertThat(responseDto.getName()).isEqualTo("바나나");
		assertThat(responseDto.getStoreWay()).isEqualTo(StoreWay.CHILL);
		assertThat(responseDto.getCategory()).isEqualTo(Category.FRUIT);
	}

	@Test
	public void Id_조회_예외() {

		foodRepository.save(dataUtils.banana_CHILL_FRUIT());

		Long nullId = 100L;

		assertThatThrownBy(() ->
			foodService.findById(nullId))
			.isInstanceOf(NullPointerException.class);
	}

	@Test
	public void 식료품_필터_조회() {
		//given
		foodRepository.save(dataUtils.banana_CHILL_FRUIT());
		foodRepository.save(dataUtils.beef_CHILL_MEAT());
		foodRepository.save(dataUtils.blueberry_FREEZE_FRUIT());

		FoodFilterRequestDto categoryFilterDto = new FoodFilterRequestDto();
		categoryFilterDto.setFilter(Category.FRUIT);

		FoodFilterRequestDto storeWayFilterDto = new FoodFilterRequestDto();
		storeWayFilterDto.setFilter(StoreWay.FREEZE);

		//when
		List<FoodResponseDto> fruits = foodService.findFoods(categoryFilterDto);
		List<FoodResponseDto> freezer = foodService.findFoods(storeWayFilterDto);

		//then
		assertThat(fruits.size()).isEqualTo(2);
		assertThat(freezer.size()).isEqualTo(1);
	}

	@Test
	public void 식료품_저장() {
		//given
		String foodName = "체리";
		FoodSaveRequestDto requestDto = dataUtils.toSaveRequestDtoByName(foodName);

		//when
		Long foodId = foodService.saveFood(requestDto);

		//then
		Optional<Food> findFood = foodRepository.findById(foodId);
		assertThat(findFood.get().getName()).isEqualTo(foodName);
	}

	@Test
	public void 식료품_수정() {
		//given
		Long id = foodRepository.save(dataUtils.blueberry_FREEZE_FRUIT()).getId();

		String updateName = "체리";
		Category updateCategory = Category.ETC;

		FoodUpdateRequestDto requestDto =
			dataUtils.toUpdateRequestDtoByNameCategory(updateName, updateCategory);

		//when
		foodService.updateFoodById(id, requestDto);

		//then
		Food findFood = foodRepository.findById(id).get();
		assertThat(findFood.getName()).isEqualTo(updateName);
		assertThat(findFood.getCategory()).isEqualTo(updateCategory);
	}

	@Test
	public void 식료품_삭제() {
		//given
		Long id = foodRepository.save(dataUtils.blueberry_FREEZE_FRUIT()).getId();

		//when
		foodService.deleteFoodById(id);

		//then
		List<Food> all = foodRepository.findAll();
		assertThat(all).isEmpty();
	}

}