package edu.itmo.isbd.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Arbitration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "admin_id", referencedColumnName = "id")
	private Admin admin;

	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id", nullable = false, referencedColumnName = "id")
	private OrderForDrive order;
}
