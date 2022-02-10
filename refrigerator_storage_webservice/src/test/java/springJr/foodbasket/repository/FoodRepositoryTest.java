package springJr.foodbasket.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
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
class FoodRepositoryTest {

	@Autowired
	FoodRepository foodRepository;

	@AfterEach
	void cleanUp() {
		foodRepository.deleteAll();
	}

	@Test
	public void saveFoodTest() {
		//given
		String name = "바나나";
		StoreWay storeWay = StoreWay.CHILL;
		Category category = Category.FRUIT;
		int quantity = 3;
		LocalDateTime expiredAt = LocalDateTime.of(2022, 2, 20, 00, 00);

		foodRepository.save(Food.builder()
			.name(name)
			.storeWay(storeWay)
			.category(category)
			.quantity(quantity)
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

}