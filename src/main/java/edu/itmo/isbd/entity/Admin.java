package edu.itmo.isbd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin {
	@Id
	private int id;
	private String adminInfLogin;

	public Admin() {
	}

	public int getId() {
		return id;
	}

	public Admin setId(int id) {
		this.id = id;
		return this;
	}

	public String getAdminInfLogin() {
		return adminInfLogin;
	}

	public Admin setAdminInfLogin(String adminInfLogin) {
		this.adminInfLogin = adminInfLogin;
		return this;
	}
}

