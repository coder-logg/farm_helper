package edu.itmo.isbd.entity;

import jakarta.persistence.*;

@Entity
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_inf_login")
	private User driverInf;

	@ManyToOne(optional = false, fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "car_id")
	private Car car;

	private int balance;

	public Driver() {}

	public Driver(int id, User driverInf, Car car, int balance) {
		this.id = id;
		this.driverInf = driverInf;
		this.car = car;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public Driver setId(int id) {
		this.id = id;
		return this;
	}

	public User getDriverInf() {
		return driverInf;
	}

	public Driver setDriverInf(User driverInf) {
		this.driverInf = driverInf;
		return this;
	}

	public Car getCar() {
		return car;
	}

	public Driver setCar(Car car) {
		this.car = car;
		return this;
	}

	public int getBalance() {
		return balance;
	}

	public Driver setBalance(int balance) {
		this.balance = balance;
		return this;
	}
}
