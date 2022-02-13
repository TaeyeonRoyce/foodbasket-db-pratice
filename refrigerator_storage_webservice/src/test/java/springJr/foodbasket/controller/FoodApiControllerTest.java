package springJr.foodbasket.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.field.Category;
import springJr.foodbasket.domain.food.field.FoodStatus;
import springJr.foodbasket.utils.DataUtils;
import springJr.foodbasket.web.dto.request.FoodRequestDto;
import springJr.foodbasket.web.dto.request.FoodUpdateRequestDto;
import springJr.foodbasket.web.dto.response.FoodResponseDto;

@DisplayName("Rest_API_테스트")
public class FoodApiControllerTest extends WebTest {

	@Autowired
	private DataUtils dataUtils;

	@Test
	public void 저장하기() {
		//given
		String foodName = "바나나";
		FoodRequestDto requestDto = dataUtils.toSaveRequestDtoByName(foodName);

		String url = baseUrl + port + "/foodbasket/newfood";

		//when
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

		//then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isGreaterThan(0L);

		List<Food> allFoods = foodRepository.findAll();
		assertThat(allFoods.get(0).getName()).isEqualTo(foodName);
		assertThat(allFoods.get(0).getFoodStatus()).isEqualTo(FoodStatus.CAUTION);
	}

	@Test
	public void 수정하기() {
		//given
		Food beef_chill_meat = dataUtils.beef_CHILL_MEAT();
		Long id = foodRepository.save(beef_chill_meat).getId();

		String updateName = "바나나";
		Category updateCategory = Category.FRUIT;
		FoodUpdateRequestDto requestDto =
			dataUtils.toUpdateRequestDtoByNameCategory(updateName, updateCategory);

		String url = baseUrl + port + "/foodbasket/edit/" + id;

		//when
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

		//then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isGreaterThan(0L);

		List<Food> allFoods = foodRepository.findAll();
		assertThat(allFoods.get(0).getName()).isEqualTo(updateName);
		assertThat(allFoods.get(0).getCategory()).isEqualTo(updateCategory);
	}

	@Test
	public void 날짜_반영_업데이트() {
		//given
		/**
		 * chickenWing
		 * FoodStatus = CAUTION
		 * LastUpdateDate = today - 2
		 * ExpireAt = today + 1
		 * (diffDays = 3, before dayUpdate)
		 */
		Food chickenWing = dataUtils.chickenWing_2DaysBefore();
		Long id = foodRepository.save(chickenWing).getId();
		String url = baseUrl + port + "/foodbasket/" + id;

		//when
		/**
		 * chickenWing
		 * FoodStatus = WARN
		 * LastUpdateDate = today
		 * ExpireAt = today + 1
		 * (diffDays = 1, after dayUpdate)
		 */
		FoodResponseDto foodResponseDto = restTemplate.getForObject(url, FoodResponseDto.class);

		//then
		assertThat(foodResponseDto.getFoodStatus()).isEqualTo(FoodStatus.WARN);
		assertThat(foodResponseDto.getLastUpdateDate()).isEqualTo(dataUtils.today);
	}

	@Test
	public void 삭제하기() {
		//given
		Food beef_chill_meat = dataUtils.beef_CHILL_MEAT();
		Long id = foodRepository.save(beef_chill_meat).getId();

		String url = baseUrl + port + "/foodbasket/" + id;

		//when
		restTemplate.delete(url);

		//then
		List<Food> allFoods = foodRepository.findAll();
		assertThat(allFoods).isEmpty();
	}

	@Test
	public void 필터_조회() {
		//given
		foodRepository.save(dataUtils.banana_CHILL_FRUIT());
		foodRepository.save(dataUtils.blueberry_FREEZE_FRUIT());
		foodRepository.save(dataUtils.beef_CHILL_MEAT());

		String url = "http://localhost:" + port + "/foodbasket?category=FRUIT";

		//when
		FoodResponseDto[] foodResponseDto = restTemplate.getForObject(url, FoodResponseDto[].class);
		List<FoodResponseDto> list = Arrays.asList(foodResponseDto);

		//then
		assertThat(list.size()).isEqualTo(2);
	}

}
