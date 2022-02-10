package springJr.foodbasket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springJr.foodbasket.domain.food.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
