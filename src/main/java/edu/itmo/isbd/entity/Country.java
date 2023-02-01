package edu.itmo.isbd.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Country {
	@Id
	private String name;

	@Column(name = "soil_type")
	private String soilType;

	@Column(name = "sunlight_amount")
	private int sunlightAmount;

	@OneToOne(mappedBy = "country", cascade = CascadeType.ALL)
	private Location location;

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
