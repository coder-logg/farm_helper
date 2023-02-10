package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_for_drive")
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class OrderForDrive {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonBackReference
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id", referencedColumnName = "user_id")
	private Driver driver;

	@JsonBackReference
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "farmer_id", referencedColumnName = "user_id")
	private Farmer farmer;

	private int cost;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "order")
	private List<Arbitration> arbitrations;

	public OrderForDrive() {}

	public OrderForDrive(int id, Driver driver, Farmer farmer, int cost) {
		this.id = id;
		this.driver = driver;
		this.farmer = farmer;
		this.cost = cost;
	}
}
