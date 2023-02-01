package edu.itmo.isbd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Farmer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "farmer_inf_login")
	private User farmerInfLogin;

	private int balance;

	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "farm_id", referencedColumnName = "id")
	private Farm farm;

	@OneToMany(mappedBy = "farmer", fetch=FetchType.EAGER)
	private Set<OrderForDrive> deliveryOrders;

	@OneToMany(mappedBy = "farmer", fetch = FetchType.EAGER)
	private Set<Order> orders;

	public Farmer() {}

	public Farmer(int id, User farmerInfLogin, int balance, Farm farm) {
		this.id = id;
		this.farmerInfLogin = farmerInfLogin;
		this.balance = balance;
		this.farm = farm;
	}

	public int getId() {
		return id;
	}

	public Farmer setId(int id) {
		this.id = id;
		return this;
	}

	public User getFarmerInfLogin() {
		return farmerInfLogin;
	}

	public Farmer setFarmerInfLogin(User farmerInfLogin) {
		this.farmerInfLogin = farmerInfLogin;
		return this;
	}

	public int getBalance() {
		return balance;
	}

	public Farmer setBalance(int balance) {
		this.balance = balance;
		return this;
	}

	public Farm getFarm() {
		return farm;
	}

	public Farmer setFarm(Farm farm) {
		this.farm = farm;
		return this;
	}
}
