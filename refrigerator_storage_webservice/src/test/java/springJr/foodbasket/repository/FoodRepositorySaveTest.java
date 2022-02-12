package springJr.foodbasket.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.utils.DataUtils;

@DisplayName("식료품 저장 테스트")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FoodRepositorySaveTest {

	@Autowired
	FoodRepository foodRepository;

	@AfterEach
	void cleanUp() {
		foodRepository.deleteAll();
	}

	@Test
	public void 식료품_저장_유통기한() {
		//given
		Food banana = DataUtils.banana_CHILL_FRUIT();
		foodRepository.save(banana);

		//when
		List<Food> allFoods = foodRepository.findAll();

		//then
		Food food = allFoods.get(0);
		assertThat(food.getName()).isEqualTo(banana.getName());
		assertThat(food.getExpireAt()).isEqualTo(banana.getExpireAt());
	}

	@Test
	public void 식료품_저장_유통기한X() {
		//given
		Food beef = DataUtils.beef_CHILL_MEAT();
		foodRepository.save(beef);

		//when
		List<Food> allFoods = foodRepository.findAll();

		//then
		Food food = allFoods.get(0);
		assertThat(food.getName()).isEqualTo("소고기");
		assertThat(food.getSaveAt()).isEqualTo(beef.getSaveAt());
		assertThat(food.getExpireAt()).isNull();
	}

}