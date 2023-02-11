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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
	@ManyToOne(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "car_id", referencedColumnName = "id")
	private Car car;

	private int balance;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
	private List<OrderForDrive> orders;

	{
		super.ROLE = UserService.Role.DRIVER;
	}

	public Driver() {}

	public Driver(User user){
		copyProperties(user, this);
	}

}
