package springJr.foodbasket.domain.food;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import springJr.foodbasket.repository.FoodRepository;
import springJr.foodbasket.utils.DataUtils;

@DisplayName("식료품 상태 설정 테스트")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FoodStatusSaveTest {

	@Autowired
	FoodRepository foodRepository;

	@AfterEach
	void cleanUp() {
		foodRepository.deleteAll();
	}


	@Test
	public void 식료품_상태_주의() {
		//given
		Food milk = DataUtils.milk_CHILL_DAILY();
		foodRepository.save(milk);

		//when
		List<Food> allFoods = foodRepository.findAll();

		//then
		Food food = allFoods.get(0);
		assertThat(food.getFoodStatus()).isEqualTo(FoodStatus.CAUTION);
	}

	@Test
	public void 식료품_상태_만료() {
		//given
		Food yogurt = DataUtils.yogurt_CHILL_DAILY();
		foodRepository.save(yogurt);

		//when
		List<Food> allFoods = foodRepository.findAll();

		//then
		Food food = allFoods.get(0);
		assertThat(food.getFoodStatus()).isEqualTo(FoodStatus.EXPIRED);
	}

	@Test
	public void 식료품_상태_신선() {
		//given
		Food carrot = DataUtils.carrot_CHILL_Vegetable();
		foodRepository.save(carrot);

		//when
		List<Food> allFoods = foodRepository.findAll();

		//then
		Food food = allFoods.get(0);
		assertThat(food.getFoodStatus()).isEqualTo(FoodStatus.FRESH);
	}

	@Test
	public void 식료품_상태_모름() {
		//given
		Food banana = DataUtils.banana_CHILL_FRUIT();
		foodRepository.save(banana);

		//when
		List<Food> allFoods = foodRepository.findAll();

		//then
		Food food = allFoods.get(0);
		assertThat(food.getFoodStatus()).isEqualTo(FoodStatus.NONE);
	}

}