package edu.itmo.isbd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Country {
	@Id
	private String name;
	@Column(name = "soil_type")
	private String soilType;
	@Column(name = "sunlight_amount")
	private int sunlightAmount;

	public Country() {}

	public Country(String name, String soilType, int sunlightAmount) {
		this.name = name;
		this.soilType = soilType;
		this.sunlightAmount = sunlightAmount;
	}

	public String getName() {
		return name;
	}

	public Country setName(String name) {
		this.name = name;
		return this;
	}

	public String getSoilType() {
		return soilType;
	}

	public Country setSoilType(String soilType) {
		this.soilType = soilType;
		return this;
	}

	public int getSunlightAmount() {
		return sunlightAmount;
	}

	public Country setSunlightAmount(int sunlightAmount) {
		this.sunlightAmount = sunlightAmount;
		return this;
	}
}
