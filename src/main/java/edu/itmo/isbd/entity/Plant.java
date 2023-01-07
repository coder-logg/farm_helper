package edu.itmo.isbd.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Plant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int cost;
	private int timeForCompleted;

	@ManyToMany
	@JoinTable(name="required_equipment",
			joinColumns=@JoinColumn(name="plant_id"),
			inverseJoinColumns=@JoinColumn(name="equipment_id"))
	private List<Equipment> requiredEquipments;

	public Plant(int id, String name, int cost, int timeForCompleted, List<Equipment> requiredEquipments) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.timeForCompleted = timeForCompleted;
		this.requiredEquipments = requiredEquipments;
	}

	public Plant(int id, String name, int cost, int timeForCompleted) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.timeForCompleted = timeForCompleted;
	}

	public Plant() {}

	public int getId() {
		return id;
	}

	public Plant setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Plant setName(String name) {
		this.name = name;
		return this;
	}

	public int getCost() {
		return cost;
	}

	public Plant setCost(int cost) {
		this.cost = cost;
		return this;
	}

	public int getTimeForCompleted() {
		return timeForCompleted;
	}

	public Plant setTimeForCompleted(int timeForCompleted) {
		this.timeForCompleted = timeForCompleted;
		return this;
	}

	public List<Equipment> getRequiredEquipments() {
		return requiredEquipments;
	}

	public Plant setRequiredEquipments(List<Equipment> requiredEquipments) {
		this.requiredEquipments = requiredEquipments;
		return this;
	}
}
