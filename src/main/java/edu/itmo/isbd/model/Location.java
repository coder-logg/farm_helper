package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "coordinates", unique = true, nullable = false)
	private Point coordinates;

	@Column(unique = true, nullable = false)
	private String address;

	@Column(nullable = false)
	private String country;

	@JsonIgnore
	public void setCoordinates(Double x, Double y) {
		this.coordinates = new Point(x, y);
	}
}
