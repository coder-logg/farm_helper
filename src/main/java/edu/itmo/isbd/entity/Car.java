package edu.itmo.isbd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mark;
	private String number;
	private int capacity;

	public Car() {}

	public Car(int id, String mark, String number, int capacity) {
		this.id = id;
		this.mark = mark;
		this.number = number;
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}

	public Car setId(int id) {
		this.id = id;
		return this;
	}

	public String getMark() {
		return mark;
	}

	public Car setMark(String mark) {
		this.mark = mark;
		return this;
	}

	public String getNumber() {
		return number;
	}

	public Car setNumber(String number) {
		this.number = number;
		return this;
	}

	public int getCapacity() {
		return capacity;
	}

	public Car setCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}
}
