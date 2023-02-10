package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Equipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int cost;
	private String location;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToMany
	@JoinTable(name="required_equipment",
			joinColumns=@JoinColumn(name="equipment_id"),
			inverseJoinColumns=@JoinColumn(name="plant_id"))
	private List<Plant> plants;

	public Equipment() {}

	public Equipment(int id, String name, int cost, String location) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public Equipment setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Equipment setName(String name) {
		this.name = name;
		return this;
	}

	public int getCost() {
		return cost;
	}

	public Equipment setCost(int cost) {
		this.cost = cost;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public Equipment setLocation(String location) {
		this.location = location;
		return this;
	}
}
