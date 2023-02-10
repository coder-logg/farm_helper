package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonBackReference(value = "myReviews")
	@ManyToOne(optional = false)
	@JoinColumn(name = "sender_id", referencedColumnName = "id")
	private User sender;

	@JsonBackReference(value = "reviewsForMe")
	@ManyToOne(optional = false)
	@JoinColumn(name = "recipient_id", referencedColumnName = "id")
	private User recipient;

	private String message;

	private int rate;

	public Review() {}
}
