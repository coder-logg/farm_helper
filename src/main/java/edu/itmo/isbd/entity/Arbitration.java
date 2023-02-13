package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Arbitration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id", referencedColumnName = "user_id")
	private Admin admin;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_for_drive_id", referencedColumnName = "id")
	private OrderForDrive orderForDrive;

	@ManyToOne
	@JoinColumn(name = "farmer_id", referencedColumnName = "user_id")
	private Farmer farmer;

	@ManyToOne
	@JoinColumn(name = "driver_id", referencedColumnName = "user_id")
	private Driver driver;

	@JsonProperty
	public int getAdminId(){
		return admin.getId();
	}

	@JsonProperty
	public void setAdminId(Integer id){
		admin = new Admin();
		admin.setId(id);
	}
}
