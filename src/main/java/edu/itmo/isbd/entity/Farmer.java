package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.itmo.isbd.service.UserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

	public Farmer(User user){
		copyProperties(user, this);
	}
}
