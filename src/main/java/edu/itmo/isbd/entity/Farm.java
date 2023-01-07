package edu.itmo.isbd.entity;

import jakarta.persistence.*;

@Entity
public class Farm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne(optional = false)
	@JoinColumn(name = "location_id")
	private Location location;

	public Farm() {}

	public Farm(int id, Location location) {
		this.id = id;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public Farm setId(int id) {
		this.id = id;
		return this;
	}

	public Location getLocation() {
		return location;
	}

	public Farm setLocation(Location location) {
		this.location = location;
		return this;
	}
}
