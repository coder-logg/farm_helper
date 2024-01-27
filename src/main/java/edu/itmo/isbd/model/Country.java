package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "name")
public class Country {
	@Id
	private String name;

	@Column(name = "soil_type")
	private String soilType;

	@Column(name = "sunlight_amount")
	private int sunlightAmount;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(mappedBy = "country", cascade = CascadeType.ALL)
	private Location location;

	public Country() {}

	public Country(String name, String soilType, int sunlightAmount) {
		this.name = name;
		this.soilType = soilType;
		this.sunlightAmount = sunlightAmount;
	}
}
