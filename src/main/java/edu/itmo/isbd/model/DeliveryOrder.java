package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "delivery_order")
@Data
public class DeliveryOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id", referencedColumnName = "user_id")
	private Driver driver;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "farmer_id", referencedColumnName = "user_id")
	private Farmer farmer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", columnDefinition = "timestamp NOT NULL default current_timestamp")
	private Date creationDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "closing_date", columnDefinition = "timestamp default NULL")
	private Date closingDate;

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;

	@Column
	@Enumerated(EnumType.STRING)
	private DeliveryOrderProgressStage status;

	private int cost;

	@Column(name = "to_address")
	private String toAddress;

	@Column(name = "from_address")
	private String fromAddress;

	public DeliveryOrder() {}

	public DeliveryOrder(int id, Driver driver, Farmer farmer, int cost) {
		this.id = id;
		this.driver = driver;
		this.farmer = farmer;
		this.cost = cost;
	}

	public DeliveryOrder(int id) {
		this.id = id;
	}
}
