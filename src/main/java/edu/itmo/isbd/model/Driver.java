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
	private List<DeliveryOrder> orders = new ArrayList<>();

	{
		super.role = Role.DRIVER;
	}

	public Driver() {}

	public Driver(User user){
		copyProperties(user, this);
	}

	public Driver(int id, String login, String phone, String mail, String password, Set<Review> myReviews,
				  List<Review> reviewsForMe, Role role, Car car, int balance, List<DeliveryOrder> orders) {
		super(id, login, phone, mail, password, myReviews, reviewsForMe, role);
		this.car = car;
		this.balance = balance;
		this.orders = orders;
	}

	public Driver(String login, String phone, String mail, String password) {
		super(login, phone, mail, password);
	}
}
