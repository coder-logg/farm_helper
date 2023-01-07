package edu.itmo.isbd.entity;

import jakarta.persistence.*;

@Entity
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne(optional = false)
	@JoinColumn(name = "country_name")
	private Country country;
	@Column(name = "price_per_month")
	private int pricePerMonth;
	private int square;

	public Location(int id, Country country, int pricePerMonth, int square) {
		this.id = id;
		this.country = country;
		this.pricePerMonth = pricePerMonth;
		this.square = square;
	}

	public Location() {}

	public int getId() {
		return id;
	}

	public Location setId(int id) {
		this.id = id;
		return this;
	}

	public Country getCountry() {
		return country;
	}

	public Location setCountry(Country country) {
		this.country = country;
		return this;
	}

	public int getPricePerMonth() {
		return pricePerMonth;
	}

	public Location setPricePerMonth(int pricePerMonth) {
		this.pricePerMonth = pricePerMonth;
		return this;
	}

	public int getSquare() {
		return square;
	}

	public Location setSquare(int square) {
		this.square = square;
		return this;
	}
}
