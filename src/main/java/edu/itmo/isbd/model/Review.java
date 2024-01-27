package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.lang.Nullable;

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

	public Review() {}

	@Nullable
	@JsonProperty
	public String getRecipientLogin(){
		if (recipient != null)
			return recipient.getLogin();
		return null;
	}

	@JsonProperty
	public void setRecipientLogin(String login){
		recipient = new User(login);
	}

	@Nullable
	@JsonProperty
	public String getSenderLogin(){
		if (sender != null)
			return sender.getLogin();
		return null;
	}

	@JsonProperty
	public void setSenderLogin(String login){
		sender = new User(login);
	}
}
