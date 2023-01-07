package edu.itmo.isbd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String phone;
	private String mail;

	public Customer() {}

	public Customer(int id, String name, String phone, String mail) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.mail = mail;
	}

	public int getId() {
		return id;
	}

	public Customer setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Customer setName(String name) {
		this.name = name;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Customer setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getMail() {
		return mail;
	}

	public Customer setMail(String mail) {
		this.mail = mail;
		return this;
	}
}
