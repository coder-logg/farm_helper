package edu.itmo.isbd.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.itmo.isbd.model.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class FarmerDto extends UserDto {
	private int balance;

	private Integer farmId;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Integer> deliveryOrdersIds;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Integer> ordersIds;

	public FarmerDto(Farmer farmer) {
		super(farmer);
		this.deliveryOrdersIds = farmer.getDeliveryOrders().stream().map(DeliveryOrder::getId).collect(Collectors.toSet());
		this.ordersIds = farmer.getOrders().stream().map(Order::getId).collect(Collectors.toSet());
	}

	public Farmer mapToFarmer() {
		return new Farmer(getId(), getLogin(), getPhone(), getMail(), getPassword(),
				getMyReviews().stream().map(ReviewDto::mapToReview).collect(Collectors.toSet()),
				getReviewsForMe().stream().map(ReviewDto::mapToReview).collect(Collectors.toList()),
				getRole(), balance, new Farm(farmId),
				deliveryOrdersIds.stream().map(DeliveryOrder::new).collect(Collectors.toSet()),
				ordersIds.stream().map(Order::new).collect(Collectors.toSet()));
	}
}
