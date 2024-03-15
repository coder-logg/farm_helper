package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mark;
	@Column(name = "license_number")
	private String number;
	private int capacity;

	public Car() {}

	public Car(int id, String mark, String number, int capacity) {
		this.id = id;
		this.mark = mark;
		this.number = number;
		this.capacity = capacity;
	}
}
