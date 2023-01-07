package edu.itmo.isbd.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "_user")
public class User {
	@Id
	private String login;
	private String phone;
	private String mail;
	private String pass;

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

	public String getPass() {
		return pass;
	}

	public User setPass(String pass) {
		this.pass = pass;
		return this;
	}
}
