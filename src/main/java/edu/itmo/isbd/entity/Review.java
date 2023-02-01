package edu.itmo.isbd.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "sender_login", referencedColumnName = "login")
	private User sender;

	@ManyToOne(optional = false)
	@JoinColumn(name = "recipient_login", referencedColumnName = "login")
	private User recipient;

	private String message;

	private int rate;

	public Review() {}

	public Review(int id, User sender, String message, int rate) {
		this.id = id;
		this.sender = sender;
		this.message = message;
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public Review setId(int id) {
		this.id = id;
		return this;
	}

	public User getSender() {
		return sender;
	}

	public Review setSender(User sender) {
		this.sender = sender;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Review setMessage(String message) {
		this.message = message;
		return this;
	}

	public int getRate() {
		return rate;
	}

	public Review setRate(int rate) {
		this.rate = rate;
		return this;
	}

	public User getRecipient() {
		return recipient;
	}

	public Review setRecipient(User recipient) {
		this.recipient = recipient;
		return this;
	}
}
