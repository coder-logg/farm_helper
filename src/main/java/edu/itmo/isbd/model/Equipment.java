package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Equipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int cost;

	public Equipment() {}

	public Equipment(int id) {
		this.id = id;
	}

	public Equipment(int id, String name, int cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
	}

	public Equipment(String name) {
		this.name = name;
	}
}
