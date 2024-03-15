package edu.itmo.isbd.model;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String name;
	private int cost;
	private int timeForCompleted;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="required_equipment",
			joinColumns=@JoinColumn(name="plant_id"),
			inverseJoinColumns=@JoinColumn(name="equipment_id"))
	private List<Equipment> requiredEquipment = new ArrayList<>();

	public Plant(int id) {
		this.id = id;
	}

	public Plant(String name, int cost, int timeForCompleted) {
		this.name = name;
		this.cost = cost;
		this.timeForCompleted = timeForCompleted;
	}

	public Plant(int id, String name, int cost, int timeForCompleted) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.timeForCompleted = timeForCompleted;
	}

	public Plant(String name) {
		this.name = name;
	}

	public void addRequiredEquipment(Equipment equipment){
		requiredEquipment.add(equipment);
	}

}
