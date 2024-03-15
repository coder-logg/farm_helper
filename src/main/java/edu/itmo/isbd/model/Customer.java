package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String phone;
	@Column(unique = true, nullable = false)
	private String mail;

	public Customer() {}

	public Customer(int id) {
		this.id = id;
	}

	public Customer(int id, String name, String phone, String mail) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.mail = mail;
	}

	public Customer(String name, String phone, String mail) {
		this.name = name;
		this.phone = phone;
		this.mail = mail;
	}
}
