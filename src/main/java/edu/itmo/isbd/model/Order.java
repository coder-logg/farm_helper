package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_order")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "farmer_id", referencedColumnName = "user_id")
	private Farmer farmer;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private OrderProgressStage status = OrderProgressStage.TO_DO;

	@ManyToOne(optional = false)
	@JoinColumn(name = "plant_id", referencedColumnName = "id")
	private Plant plant;

	private int amount;
	private int cost;

	{
		status = OrderProgressStage.TO_DO;
	}

	public Order(Farmer farmer, Customer customer, OrderProgressStage status, Plant plant, int amount, int cost) {
		this.farmer = farmer;
		this.customer = customer;
		this.status = status;
		this.plant = plant;
		this.amount = amount;
		this.cost = cost;
	}

	public Order(int id) {
		this.id = id;
	}
}
