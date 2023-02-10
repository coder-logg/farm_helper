package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(optional = false)
	@JoinColumn(name = "country_name")
	private Country country;

	@Column(name = "price_per_month")
	private int pricePerMonth;

	@Column(name = "square")
	private int square;

	public Location(int id, Country country, int pricePerMonth, int square) {
		this.id = id;
		this.country = country;
		this.pricePerMonth = pricePerMonth;
		this.square = square;
	}

	public Location() {}
}
