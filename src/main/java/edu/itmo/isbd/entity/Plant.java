package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Plant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int cost;
	private int timeForCompleted;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="required_equipment",
			joinColumns=@JoinColumn(name="plant_id"),
			inverseJoinColumns=@JoinColumn(name="equipment_id"))
	private List<Equipment> requiredEquipments;

	@Nullable
	@JsonProperty
	public List<Integer> getRequiredEquipmentIds(){
		if (requiredEquipments == null)
			return null;
		return requiredEquipments.stream().map(Equipment::getId).collect(Collectors.toList());
	}

	@JsonProperty
	public void setRequiredEquipmentIds(List<Integer> requiredEquipments){
		this.requiredEquipments = new ArrayList<>();
		requiredEquipments.forEach(x -> {
			Equipment eq = new Equipment(x);
			this.requiredEquipments.add(eq);
		});
	}

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "plant")
	private List<OrderDetail> orderDetails;

	public Plant() {}

}
