package edu.itmo.isbd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "_user")
@Data
public class User {
	@Id
	private String login;
	private String phone;
	private String mail;
	private String password;

	@OneToOne(mappedBy = "adminInfLogin")
	private Admin admin;

	@OneToOne(mappedBy = "farmerInfLogin")
	private Farmer farmer;

	@OneToOne(mappedBy = "driverInf")
	private Driver driver;

	@OneToMany(mappedBy = "sender", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Review> myReviews;

	@OneToMany(mappedBy = "recipient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Review> reviewsForMe;

	public User() {}

	public String getLogin() {
		return login;
	}

	public User setLogin(String login) {
		this.login = login;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public User setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getMail() {
		return mail;
	}

	public User setMail(String mail) {
		this.mail = mail;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String pass) {
		this.password = pass;
		return this;
	}
}
