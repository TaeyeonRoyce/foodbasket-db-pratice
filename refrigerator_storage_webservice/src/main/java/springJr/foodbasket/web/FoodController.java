package springJr.foodbasket.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springJr.foodbasket.domain.FoodService;
import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.FoodStatus;
import springJr.foodbasket.domain.food.StoreWay;
import springJr.foodbasket.domain.food.dto.request.FoodSaveRequestDto;
import springJr.foodbasket.domain.food.dto.request.FoodUpdateRequestDto;
import springJr.foodbasket.domain.food.dto.response.FoodResponseDto;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/foodbasket")
@RestController
public class FoodController {

	private final FoodService foodService;

	@GetMapping("")
	public List<FoodResponseDto> findAll() {
		log.debug("findAll");
		return foodService.findAll();
	}

	@GetMapping("/category")
	public List<Food> findByCategory(@RequestParam Category category) {
		log.debug("filterType == category : {}", category);
		return foodService.findByCategory(category);
	}

	@GetMapping("/storeway")
	public List<Food> findByStoreWay(@RequestParam StoreWay storeWay) {
		log.debug("filterType == storeWay : {}", storeWay);
		return foodService.findByStoreWay(storeWay);
	}

	@GetMapping("/foodstatus")
	public List<Food> findByFoodStatus(@RequestParam FoodStatus foodStatus) {
		log.debug("filterType == foodStatus : {}", foodStatus);
		return foodService.findByFoodStatus(foodStatus);
	}

	@PostMapping("/newfood")
	public Long addFood(@RequestBody FoodSaveRequestDto requestDto, HttpServletResponse response) throws IOException {
		log.debug("save food : {}", requestDto);
		Long saveFoodId = foodService.addFood(requestDto);
		// response.sendRedirect("http://localhost:3000/foodbasket");
		return saveFoodId;
	}

	@GetMapping("/{foodId}")
	public FoodResponseDto findFood(@PathVariable Long foodId) {
		log.debug("find food, foodId : {}", foodId);
		return foodService.findById(foodId);
	}

	@PostMapping("/edit/{foodId}")
	public Long editFood(@PathVariable Long foodId, @RequestBody FoodUpdateRequestDto requestDto) {
		log.debug("update food, foodId : {}", foodId);
		foodService.updateFoodById(foodId, requestDto);
		return foodId;
	}


}
