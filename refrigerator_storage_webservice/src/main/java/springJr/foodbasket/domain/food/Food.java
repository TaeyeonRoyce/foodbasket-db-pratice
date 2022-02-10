package springJr.foodbasket.domain.food;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

	@CreatedDate
	private LocalDateTime saveAt;

	private LocalDateTime expireAt;

	@Enumerated(EnumType.STRING)
	private FoodStatus foodStatus;

	@Builder
	public Food(String name, StoreWay storeWay, Category category, int quantity, LocalDateTime expireAt) {
		this.name = name;
		this.storeWay = storeWay;
		this.category = category;
		this.quantity = quantity;
		this.expireAt = expireAt;
	}

	@Builder
	public Food(String name, StoreWay storeWay, Category category, int quantity, LocalDateTime saveAt,
		LocalDateTime expireAt) {
		this.name = name;
		this.storeWay = storeWay;
		this.category = category;
		this.quantity = quantity;
		this.saveAt = saveAt;
		this.expireAt = expireAt;
	}
}
