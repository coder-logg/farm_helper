package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Farm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "location_id")
	private Location location;

	@Column(name = "price_per_month")
	private int pricePerMonth;

	@Column(name = "square")
	private int square;

	public Farm(Location location, int pricePerMonth, int square) {
		this.location = location;
		this.pricePerMonth = pricePerMonth;
		this.square = square;
	}

	public Farm(int id) {
		this.id = id;
	}
}
