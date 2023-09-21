package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.*;
import edu.itmo.isbd.service.UserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
	private Set<OrderForDrive> deliveryOrders;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
	private Set<Order> orders;

	{
		super.ROLE = UserService.Role.FARMER;
	}

	public Farmer() {}

	public Farmer(String login) {
		super(login);
	}

	public Farmer(User user){
		copyProperties(user, this);
	}

	@JsonProperty
	public void setOrderForDriveIds(List<Integer> orderForDriveIds){
		deliveryOrders = new HashSet<>();
		orderForDriveIds.forEach(id -> deliveryOrders.add(new OrderForDrive(id)));
	}

	@Nullable
	@JsonProperty
	public List<Integer> getOrderForDriveIds(){
		if (deliveryOrders != null)
			return deliveryOrders.stream().map(OrderForDrive::getId).collect(Collectors.toList());
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
