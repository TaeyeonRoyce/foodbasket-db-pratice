package springJr.foodbasket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springJr.foodbasket.domain.food.Category;
import springJr.foodbasket.domain.food.Food;
import springJr.foodbasket.domain.food.FoodStatus;
import springJr.foodbasket.domain.food.StoreWay;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

	@Query("SELECT f FROM Food f WHERE f.category = :category")
	List<Food> findByCategory(@Param("category") Category category);

	@Query("SELECT f FROM Food f WHERE f.storeWay = :storeWay")
	List<Food> findByStoreWay(@Param("storeWay") StoreWay storeWay);

	@Query("SELECT f FROM Food f WHERE f.foodStatus = :foodStatus")
	List<Food> findByFoodStatus(@Param("foodStatus") FoodStatus foodStatus);
}
