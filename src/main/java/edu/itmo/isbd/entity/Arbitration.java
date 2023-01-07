package edu.itmo.isbd.entity;

import jakarta.persistence.*;

@Entity
public class Arbitration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "admin_id")
	private Admin admin;

	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
}
