package springJr.foodbasket.controller;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.FoodStatus;
import springJr.foodbasket.domain.food.StoreWay;
import springJr.foodbasket.domain.food.dto.request.FoodRequestDto;
import springJr.foodbasket.domain.food.dto.request.FoodSaveRequestDto;
import springJr.foodbasket.domain.food.dto.response.FoodResponseDto;
import springJr.foodbasket.repository.FoodRepository;
import springJr.foodbasket.utils.DataUtils;

@DisplayName("Rest Api 테스트")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodApiControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private FoodRepository foodRepository;

	@AfterEach
	void deleteData() {
		foodRepository.deleteAll();
	}


	@Test
	public void 저장_Food() {
		//given
		String foodName = "바나나";
		FoodRequestDto requestDto = getSaveRequestDto(foodName);

		String url = "http://localhost:" + port + "/foodbasket/newfood";

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
	public void 수정_Food() {
		//given
		Food beef_chill_meat = DataUtils.beef_CHILL_MEAT();
		Long id = foodRepository.save(beef_chill_meat).getId();

		String updateName = "바나나";
		Category updateCategory = Category.FRUIT;
		FoodRequestDto requestDto = getUpdateRequestDto(updateName, updateCategory);

		String url = "http://localhost:" + port + "/foodbasket/edit/" + id;

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
		Food chickenWing = DataUtils.chickenWing_2DaysBefore();
		Long id = foodRepository.save(chickenWing).getId();
		String url = "http://localhost:" + port + "/foodbasket/" + id;

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
		assertThat(foodResponseDto.getLastUpdateDate()).isEqualTo(DataUtils.today);
	}



	private FoodSaveRequestDto getSaveRequestDto(String foodName) {
		LocalDate today = DataUtils.today;

		return FoodSaveRequestDto.builder()
			.name(foodName)
			.storeWay(StoreWay.CHILL)
			.category(Category.FRUIT)
			.quantity(3)
			.saveAt(today)
			.expireAt(today.plusDays(3))
			.build();
	}

	private FoodSaveRequestDto getUpdateRequestDto(String updateName, Category updateCategory) {
		LocalDate today = DataUtils.today;

		return FoodSaveRequestDto.builder()
			.name(updateName)
			.storeWay(StoreWay.CHILL)
			.category(updateCategory)
			.quantity(3)
			.saveAt(today)
			.expireAt(today.plusDays(3))
			.build();
	}

}
