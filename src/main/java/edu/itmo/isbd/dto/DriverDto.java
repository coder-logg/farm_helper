package edu.itmo.isbd.dto;

import edu.itmo.isbd.model.Car;
import edu.itmo.isbd.model.DeliveryOrder;
import edu.itmo.isbd.model.Driver;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DriverDto extends UserDto {
	private Car car;
	private int balance;
	private List<Integer> deliveryOrdersIds = new ArrayList<>();

	public DriverDto(Driver driver) {
		super(driver);
		this.deliveryOrdersIds = driver.getOrders().stream().map(DeliveryOrder::getId).collect(Collectors.toList());
	}

	public Driver mapToDriver() {
		return new Driver(getId(), getLogin(), getPhone(), getMail(), getPassword(),
				getMyReviews().stream().map(ReviewDto::mapToReview).collect(Collectors.toSet()),
				getReviewsForMe().stream().map(ReviewDto::mapToReview).collect(Collectors.toList()),
				getRole(), car, balance,
				deliveryOrdersIds.stream().map(DeliveryOrder::new).collect(Collectors.toList()));
	}
}
