package springJr.foodbasket.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import springJr.foodbasket.repository.FoodRepository;
import springJr.foodbasket.utils.DataCreation;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FoodControllerFindTest {

	@LocalServerPort
	private int port;

	private Logger log = (Logger) LoggerFactory.getLogger(FoodControllerFindTest.class);


	@Autowired
	private TestRestTemplate restTemplate;

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
		// foodRepository.deleteAll();
	}

	@Test
	public void Food_조회() throws Exception {
		/**
		 * "바나나", CHILL, FRUIT
		 * "소고기", CHILL, MEAT
		 * "블루베리", FREEZE, FRUIT
		 */

		//given
		String url = "http://localhost:" + port + "/foodbasket";

		//when
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		log.trace("responseEntity : {}", responseEntity.getStatusCode());
		log.trace("responseEntity : {}", responseEntity.getBody());
		System.out.println("✅");
		System.out.println(responseEntity.getBody());
		System.out.println(responseEntity.getStatusCode());

	}
}