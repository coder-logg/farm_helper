package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

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
	private ProgressStages progress;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(mappedBy = "status")
	private Order order;

	public enum ProgressStages {
		STARTED, CULTIVATION, DELIVERY, FINISHED, ARBITRATION;
	}

	public void setProgress(ProgressStages progress) {
		this.progress = progress;
	}

	public OrderStatus() {}

	public OrderStatus(String location, ProgressStages progress) {
		this.location = location;
		this.progress = progress;
	}

	public OrderStatus(int id, String location, ProgressStages progress) {
		this.id = id;
		this.location = location;
		this.progress = progress;
	}
}
