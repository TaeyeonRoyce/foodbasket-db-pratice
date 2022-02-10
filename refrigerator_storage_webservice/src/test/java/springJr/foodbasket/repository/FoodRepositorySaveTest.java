package springJr.foodbasket.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.StoreWay;

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
		String name = "바나나";
		StoreWay storeWay = StoreWay.CHILL;
		Category category = Category.FRUIT;
		int quantity = 3;
		LocalDate today = LocalDate.now();
		LocalDate expiredAt = LocalDate.of(2022, 2, 20);

		foodRepository.save(Food.builder()
			.name(name)
			.storeWay(storeWay)
			.category(category)
			.quantity(quantity)
			.saveAt(today)
			.expireAt(expiredAt)
			.build()
		);

		//when
		List<Food> allFoods = foodRepository.findAll();

		//then
		Food food = allFoods.get(0);
		assertThat(food.getName()).isEqualTo("바나나");
		assertThat(food.getExpireAt()).isEqualTo(expiredAt);
	}

	@Test
	public void 식료품_저장_유통기한X() {
		//given
		String name = "소고기";
		StoreWay storeWay = StoreWay.CHILL;
		Category category = Category.MEAT;
		int quantity = 1;
		LocalDate today = LocalDate.now();

		foodRepository.save(Food.builder()
			.name(name)
			.storeWay(storeWay)
			.category(category)
			.quantity(quantity)
			.saveAt(today)
			.build()
		);

		//when
		List<Food> allFoods = foodRepository.findAll();

		//then
		Food food = allFoods.get(0);
		assertThat(food.getName()).isEqualTo("소고기");
		assertThat(food.getSaveAt()).isEqualTo(today);
		assertThat(food.getExpireAt()).isNull();
	}

}