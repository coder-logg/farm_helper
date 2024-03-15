package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "sender_id", referencedColumnName = "id")
	private User sender;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "recipient_id", referencedColumnName = "id")
	private User recipient;

	private String message;

	private int rate;

	@Nullable
	@JsonProperty
	public String getRecipientLogin(){
		if (recipient != null)
			return recipient.getLogin();
		return null;
	}

	@Nullable
	@JsonProperty
	public String getSenderLogin(){
		if (sender != null)
			return sender.getLogin();
		return null;
	}
}
