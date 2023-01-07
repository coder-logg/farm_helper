package edu.itmo.isbd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Equipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int cost;
	private String location;

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
