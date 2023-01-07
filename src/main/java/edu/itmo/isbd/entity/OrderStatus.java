package edu.itmo.isbd.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class OrderStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String location;

	@Enumerated(EnumType.ORDINAL)
	private Stages progress;

	static enum Stages {
		STARTED, CULTIVATION, DELIVERY, FINISHED, ARBITRATION;
	}

	public OrderStatus() {}

	public OrderStatus(int id, String location, Stages progress) {
		this.id = id;
		this.location = location;
		this.progress = progress;
	}

	public int getId() {
		return id;
	}

	public OrderStatus setId(int id) {
		this.id = id;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public OrderStatus setLocation(String location) {
		this.location = location;
		return this;
	}

	public Stages getProgress() {
		return progress;
	}

	public OrderStatus setProgress(Stages progress) {
		this.progress = progress;
		return this;
	}
}
