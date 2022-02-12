package springJr.foodbasket.domain.food;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springJr.foodbasket.domain.food.dto.request.FoodUpdateRequestDto;

@Getter
@NoArgsConstructor
@Entity
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "FOOD_NAME", nullable = false, length = 20)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StoreWay storeWay;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Category category;

	@Column(nullable = false)
	private int quantity;

	@Column(nullable = false)
	private LocalDate saveAt;

	private LocalDate expireAt;

	@Enumerated(EnumType.STRING)
	private FoodStatus foodStatus;

	private LocalDate lastUpdateDate;

	@Builder
	public Food(String name, StoreWay storeWay, Category category, int quantity, LocalDate saveAt,
		LocalDate expireAt, FoodStatus foodStatus, LocalDate lastUpdateDate) {
		this.name = name;
		this.storeWay = storeWay;
		this.category = category;
		this.quantity = quantity;
		this.saveAt = saveAt;
		this.expireAt = expireAt;
		this.foodStatus = foodStatus;
		this.lastUpdateDate = lastUpdateDate;
	}

	public void updateByRequestDto(FoodUpdateRequestDto requestDto) {
		this.name = requestDto.getName();
		this.storeWay = requestDto.getStoreWay();
		this.category = requestDto.getCategory();
		this.quantity = requestDto.getQuantity();
		this.saveAt = requestDto.getSaveAt();
		this.expireAt = requestDto.getExpireAt();
	}
}
