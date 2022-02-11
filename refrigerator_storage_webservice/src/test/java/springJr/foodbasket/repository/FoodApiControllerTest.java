package springJr.foodbasket.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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
import springJr.foodbasket.domain.food.dto.FoodSaveRequestDto;
import springJr.foodbasket.utils.DataCreation;

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
	public void 저장_JSON() {
		//given
		LocalDate today = DataCreation.today;
		String foodName = "바나나";

		FoodSaveRequestDto requestDto = FoodSaveRequestDto.builder()
			.name(foodName)
			.storeWay(StoreWay.CHILL)
			.category(Category.FRUIT)
			.quantity(3)
			.saveAt(today)
			.expireAt(today.plusDays(3))
			.build();

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

}
