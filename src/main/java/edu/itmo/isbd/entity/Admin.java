package edu.itmo.isbd.entity;

import jakarta.persistence.*;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(optional=false, cascade= CascadeType.ALL)
	@JoinColumn(name="admin_inf_login")
	private User adminInfLogin;

	public Admin() {}

	public Admin(int id, User adminInfLogin) {
		this.id = id;
		this.adminInfLogin = adminInfLogin;
	}

	public int getId() {
		return id;
	}

	public Admin setId(int id) {
		this.id = id;
		return this;
	}

	public User getAdminInfLogin() {
		return adminInfLogin;
	}

	public Admin setAdminInfLogin(User adminInfLogin) {
		this.adminInfLogin = adminInfLogin;
		return this;
	}
}

