package edu.itmo.isbd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "farmer_id", referencedColumnName = "id", nullable = false)
	private Farmer farmer;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_detail_id", unique = true)
	private OrderDetail detail;

	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id", unique = true)
	private OrderStatus status;

	@Column(nullable = false)
	private int cost;

	public Order(int id, Farmer farmer, Customer customer, OrderDetail detail, OrderStatus status, int cost) {
		this.id = id;
		this.farmer = farmer;
		this.customer = customer;
		this.detail = detail;
		this.status = status;
		this.cost = cost;
	}

	public Order() {}

	public int getId() {
		return id;
	}

	public Order setId(int id) {
		this.id = id;
		return this;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public Order setFarmer(Farmer farmer) {
		this.farmer = farmer;
		return this;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Order setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	public OrderDetail getDetail() {
		return detail;
	}

	public Order setDetail(OrderDetail detail) {
		this.detail = detail;
		return this;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public Order setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

	public int getCost() {
		return cost;
	}

	public Order setCost(int cost) {
		this.cost = cost;
		return this;
	}
}
