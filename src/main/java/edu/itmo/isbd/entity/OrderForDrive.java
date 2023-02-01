package edu.itmo.isbd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_for_drive")
@Data
public class OrderForDrive {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id", nullable = false, referencedColumnName = "id")
	private Driver driver;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "farmer_id", nullable = false, referencedColumnName = "id")
	private Farmer farmer;

	@Column(nullable = false)
	private int cost;

	@OneToMany(mappedBy = "order")
	private List<Arbitration> arbitrations;

	public OrderForDrive() {}

	public OrderForDrive(int id, Driver driver, Farmer farmer, int cost) {
		this.id = id;
		this.driver = driver;
		this.farmer = farmer;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public OrderForDrive setId(int id) {
		this.id = id;
		return this;
	}

	public Driver getDriver() {
		return driver;
	}

	public OrderForDrive setDriver(Driver driver) {
		this.driver = driver;
		return this;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public OrderForDrive setFarmer(Farmer farmer) {
		this.farmer = farmer;
		return this;
	}

	public int getCost() {
		return cost;
	}

	public OrderForDrive setCost(int cost) {
		this.cost = cost;
		return this;
	}
}
