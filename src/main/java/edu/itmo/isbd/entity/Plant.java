package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Plant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int cost;
	private int timeForCompleted;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="required_equipment",
			joinColumns=@JoinColumn(name="plant_id"),
			inverseJoinColumns=@JoinColumn(name="equipment_id"))
	private List<Equipment> requiredEquipments;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "plant")
	private List<OrderDetail> orderDetails;

	public Plant() {}

}
