package edu.itmo.isbd.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "order_detail")
@Data
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "plant_id", referencedColumnName = "id")
	private Plant plant;

	private int amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delivery_date")
	private Date deliveryDate;

	@Column(name = "delivery_address")
	private String deliveryAddress;

	@OneToOne(mappedBy = "detail")
	private Order order;

	public OrderDetail() {}

	public OrderDetail(int id, Plant plant, int amount, Date deliveryDate, String deliveryAddress) {
		this.id = id;
		this.plant = plant;
		this.amount = amount;
		this.deliveryDate = deliveryDate;
		this.deliveryAddress = deliveryAddress;
	}

	public int getId() {
		return id;
	}

	public OrderDetail setId(int id) {
		this.id = id;
		return this;
	}

	public Plant getPlant() {
		return plant;
	}

	public OrderDetail setPlant(Plant plant) {
		this.plant = plant;
		return this;
	}

	public int getAmount() {
		return amount;
	}

	public OrderDetail setAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public OrderDetail setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
		return this;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public OrderDetail setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
		return this;
	}
}
