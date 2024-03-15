package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Data
@SuperBuilder
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "user_id")
public class Farmer extends User {
	private int balance;

	@ManyToOne
	@JoinColumn(name = "farm_id", referencedColumnName = "id")
	private Farm farm;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
	private Set<DeliveryOrder> deliveryOrders = new HashSet<>();

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
	private Set<Order> orders = new HashSet<>();

	{
		super.role = Role.FARMER;
	}

	public Farmer() {
	}

	public Farmer(User user, Farm farm) {
		copyProperties(user, this);
		super.role = Role.FARMER;
		this.farm = farm;
	}

	public Farmer(String login, String phone, String mail, String password, Farm farm) {
		super(login, phone, mail, password);
		this.farm = farm;
	}

	public Farmer(int id,
				  @Size(min = 4, max = 15, message = "Login length incorrect: should be from 4 to 15 symbols") String login,
				  String phone,
				  String mail,
				  @Size(min = 8, max = 15, message = "Password length incorrect: should be from 8 to 15 symbols") String password,
				  Set<Review> myReviews,
				  List<Review> reviewsForMe,
				  Role role,
				  int balance,
				  Farm farm,
				  Set<DeliveryOrder> deliveryOrders,
				  Set<Order> orders) {
		super(id, login, phone, mail, password, myReviews, reviewsForMe, role);
		this.balance = balance;
		this.farm = farm;
		this.deliveryOrders = deliveryOrders;
		this.orders = orders;
	}

	public Farmer(String login, String phone, String mail, String password) {
		super(login, phone, mail, password);
	}
}
