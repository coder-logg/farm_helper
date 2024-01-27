package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
@PrimaryKeyJoinColumn(name = "user_id")
public class Driver extends User {
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "car_id", referencedColumnName = "id")
	private Car car;

	private int balance;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
	private List<DeliveryOrder> orders;

	{
		super.ROLE = Role.DRIVER;
	}

	public Driver() {}

	public Driver(User user){
		copyProperties(user, this);
	}

	@Nullable
	@JsonProperty
	public List<Integer> getDeliveryOrderIds(){
		if (orders != null)
			return orders.stream().map(DeliveryOrder::getId).collect(Collectors.toList());
		return null;
	}

	@JsonProperty
	public void setOrdersIds(List<Integer> ordersIds){
		orders = new ArrayList<>();
		ordersIds.forEach(id -> orders.add(new DeliveryOrder(id)));
	}

}
