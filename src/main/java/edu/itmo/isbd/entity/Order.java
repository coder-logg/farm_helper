package edu.itmo.isbd.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "farmer_id")
	private Farmer farmer;

	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToOne(optional = false)
	@JoinColumn(name = "order_detail_id", unique = true)
	private OrderDetail detail;

	@OneToOne(optional = false)
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
