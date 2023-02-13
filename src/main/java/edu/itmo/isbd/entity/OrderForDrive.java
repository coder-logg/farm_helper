package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
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

	@JsonIgnore
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id", referencedColumnName = "user_id")
	private Driver driver;

	@JsonBackReference
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "farmer_id", referencedColumnName = "user_id")
	private Farmer farmer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "closing_date")
	private Date closingDate;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;

	private int cost;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "orderForDrive")
	private List<Arbitration> arbitrations;

	public OrderForDrive() {}

	public OrderForDrive(int id, Driver driver, Farmer farmer, int cost) {
		this.id = id;
		this.driver = driver;
		this.farmer = farmer;
		this.cost = cost;
	}

	public OrderForDrive(int id) {
		this.id = id;
	}

	@Nullable
	@JsonProperty
	public String getDriverLogin(){
		if (this.driver != null)
			return driver.getLogin();
		return null;
	}

	@JsonProperty
	public void setDriverLogin(String login){
		this.driver = new Driver();
		this.driver.setLogin(login);
	}

	@Nullable
	@JsonProperty
	public String getFarmerLogin(){
		if (this.farmer != null)
			return farmer.getLogin();
		return null;
	}

	@JsonProperty
	public void setFarmerLogin(String login){
		this.farmer = new Farmer();
		this.farmer.setLogin(login);
	}

	@JsonProperty
	public void setOrderId(Integer id){
		this.order = new Order(id);
	}

	@Nullable
	@JsonProperty
	public Integer getOrderId(){
		if (order != null)
			return order.getId();
		return null;
	}

//	@JsonProperty
//	public void set(___){
//
//	}


//	@Nullable
//	@JsonProperty
//	public ___ get(){
//
//	}
}
