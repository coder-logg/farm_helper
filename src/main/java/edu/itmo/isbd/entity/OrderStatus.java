package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status")
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class OrderStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String location;

	@Enumerated(EnumType.STRING)
	private Stages progress;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(mappedBy = "status")
	private Order order;

	enum Stages {
		STARTED, CULTIVATION, DELIVERY, FINISHED, ARBITRATION;
	}

	public OrderStatus() {}

	public OrderStatus(int id, String location, Stages progress) {
		this.id = id;
		this.location = location;
		this.progress = progress;
	}
}
