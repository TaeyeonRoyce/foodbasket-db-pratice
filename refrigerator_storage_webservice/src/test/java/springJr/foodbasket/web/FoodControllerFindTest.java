package springJr.foodbasket.web;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.StoreWay;
import springJr.foodbasket.repository.FoodRepository;
import springJr.foodbasket.utils.DataCreation;

@DisplayName("선택 조회 테스트")
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FoodControllerFindTest {

	@Autowired
	private FoodRepository foodRepository;

	@BeforeEach
	void setData() {
		foodRepository.save(DataCreation.banana_CHILL_FRUIT());
		foodRepository.save(DataCreation.blueberry_FREEZE_FRUIT());
		foodRepository.save(DataCreation.beef_CHILL_MEAT());
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
}