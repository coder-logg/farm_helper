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
public class Farmer extends User{
	private int balance;

	@ManyToOne
	@JoinColumn(name = "farm_id", referencedColumnName = "id")
	private Farm farm;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "farmer", fetch=FetchType.LAZY)
	private Set<DeliveryOrder> deliveryOrders;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
	private Set<Order> orders;

	{
		super.ROLE = Role.FARMER;
	}

	public Farmer() {}

	public Farmer(String login) {
		super(login);
	}

	public Farmer(User user){
		copyProperties(user, this);
	}

	@JsonProperty
	public void setDeliveryOrderIds(List<Integer> deliveryOrderIds){
		deliveryOrders = new HashSet<>();
		deliveryOrderIds.forEach(id -> deliveryOrders.add(new DeliveryOrder(id)));
	}

	@Nullable
	@JsonProperty
	public List<Integer> getDeliveryOrderIds(){
		if (deliveryOrders != null)
			return deliveryOrders.stream().map(DeliveryOrder::getId).collect(Collectors.toList());
		return null;
	}

	@JsonProperty
	public void setOrdersIds(List<Integer> ordersIds){
		orders = new HashSet<>();
		ordersIds.forEach(id -> orders.add(new Order(id)));
	}

	@Nullable
	@JsonProperty
	public List<Integer> getOrdersIds(){
		if (orders != null)
			return orders.stream().map(Order::getId).collect(Collectors.toList());
		return null;
	}


//	@Nullable
//	@JsonProperty
//	public ___ get(){
//
//	}
}
