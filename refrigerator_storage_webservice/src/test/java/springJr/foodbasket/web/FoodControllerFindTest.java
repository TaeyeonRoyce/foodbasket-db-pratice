package springJr.foodbasket.web;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import springJr.foodbasket.domain.FoodService;
import springJr.foodbasket.domain.food.field.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.field.StoreWay;
import springJr.foodbasket.web.dto.request.FoodFilterRequestDto;
import springJr.foodbasket.web.dto.response.FoodResponseDto;
import springJr.foodbasket.repository.FoodRepository;
import springJr.foodbasket.utils.DataUtils;

@DisplayName("선택 조회 테스트")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FoodControllerFindTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private FoodRepository foodRepository;

	@Autowired
	private FoodService foodService;

	@BeforeEach
	void setData() {
		foodRepository.save(DataUtils.banana_CHILL_FRUIT());
		foodRepository.save(DataUtils.blueberry_FREEZE_FRUIT());
		foodRepository.save(DataUtils.beef_CHILL_MEAT());
	}

	@AfterEach
	void clearData() {
		foodRepository.deleteAll();
	}

	@Test
	public void 상태_필터() {
		/**
		 * "바나나", CHILL, FRUIT
		 * "소고기", CHILL, MEAT
		 * "블루베리", FREEZE, FRUIT
		 */

		//given

		//when
		List<Food> allFoods = foodRepository.findAll();

		//then
		assertThat(allFoods.size()).isEqualTo(3);
	}

	@Test
	public void 유형_필터() {
		/**
		 * "바나나", CHILL, FRUIT
		 * "소고기", CHILL, MEAT
		 * "블루베리", FREEZE, FRUIT
		 */

		//given
		Category filterType = Category.FRUIT;
		//when
		List<Food> allFoods = foodRepository.findByCategory(filterType);

		//then
		assertThat(allFoods.size()).isEqualTo(2);
	}

	@Test
	public void 보관방식_필터() {
		/**
		 * "바나나", CHILL, FRUIT
		 * "소고기", CHILL, MEAT
		 * "블루베리", FREEZE, FRUIT
		 */

		//given
		StoreWay filterType = StoreWay.FREEZE;
		//when
		List<Food> allFoods = foodRepository.findByStoreWay(filterType);

		//then
		assertThat(allFoods.size()).isEqualTo(1);
	}

	@Test
	public void 필터_조회() {
		//given
		String url = "http://localhost:" + port + "/foodbasket?category=FRUIT";

		//when
		FoodResponseDto[] foodResponseDto = restTemplate.getForObject(url, FoodResponseDto[].class);
		List<FoodResponseDto> list = Arrays.asList(foodResponseDto);

		//then
		assertThat(list.size()).isEqualTo(2);
	}

	@Test
	public void 필터_조회_테스트() {
	    //given
		FoodFilterRequestDto requestDto = new FoodFilterRequestDto();
		requestDto.setCategory(Category.FRUIT);

		//when
		List<FoodResponseDto> list = foodService.findFoods(requestDto);

		//then
		assertThat(list.size()).isEqualTo(2);
	}


}