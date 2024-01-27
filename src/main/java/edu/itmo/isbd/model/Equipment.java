package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.geo.Point;

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
	private Point location;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToMany
	@JoinTable(name="required_equipment",
			joinColumns=@JoinColumn(name="equipment_id"),
			inverseJoinColumns=@JoinColumn(name="plant_id"))
	private List<Plant> plants;

	public Equipment() {}

	public Equipment(int id) {
		this.id = id;
	}

	public Equipment(int id, String name, int cost, Point location) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.location = location;
	}
}
