package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
	private int id;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "farmer_id", referencedColumnName = "user_id")
	private Farmer farmer;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	@JsonIgnore
	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_detail_id", unique = true)
	private OrderDetail detail;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id", unique = true)
	private OrderStatus status;

	private int cost;

	{
		detail = new OrderDetail();
	}

	public Order(int id) {
		this.id = id;
	}

	@JsonProperty
	public Integer getDetailId(){
		return detail.getId();
	}

	@JsonProperty
	public String getFarmerLogin(){
		return farmer.getLogin();
	}

	@JsonProperty
	public void setFarmerLogin(String farmerLogin){
		this.farmer = new Farmer(farmerLogin);
	}

	@Nullable
	@JsonProperty
	public Plant getPlant(){
		if (Objects.isNull(detail))
			return null;
		return detail.getPlant();
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public void setPlantId(Integer id){
		detail.setPlant(new Plant(id));
	}

//	@Nullable
//	@JsonProperty
//	public Integer getPlantId(){
//		if (Objects.isNull(detail.getPlant()))
//			return null;
//		return detail.getPlant().getId();
//	}

	@JsonProperty
	public void setPlantAmount(Integer amount){
		detail.setAmount(amount);
	};

	@JsonProperty
	public Date getDeliveryDate() {
		return detail.getDeliveryDate();
	}

	@JsonProperty
	public void setDeliveryDate(Date deliveryDate) {
		detail.setDeliveryDate(deliveryDate);
	}

	@JsonProperty
	public String getDeliveryAddress() {
		return detail.getDeliveryAddress();
	}

	@JsonProperty
	public void setDeliveryAddress(String deliveryAddress) {
		detail.setDeliveryAddress(deliveryAddress);
	}

	@Nullable
	@JsonProperty
	public Integer getCustomerId() {
		if (Objects.isNull(customer))
			return null;
		return customer.getId();
	}

	@Nullable
	@JsonProperty
	public void setCustomerId(Integer id) {
		customer = new Customer(id);
	}

	@JsonProperty
	public void setPlantsAmount(Integer amount){
		detail.setAmount(amount);
	}

	@Nullable
	@JsonProperty
	public Integer getPlantsAmount(){
		return detail.getAmount();
	}
}
