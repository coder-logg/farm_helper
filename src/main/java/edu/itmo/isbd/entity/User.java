package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.*;
import edu.itmo.isbd.service.UserService;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "_user")
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "login")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String login;

	@Column(nullable = false, unique = true)
	private String phone;

	@Column(nullable = false, unique = true)
	private String mail;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	private Set<Review> myReviews;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
	private List<Review> reviewsForMe;

	@Transient
	protected UserService.Role ROLE;

	public User() {}

	public User(int id) {
		this.id = id;
	}

	public User(String login) {
		this.login = login;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + ROLE.name()));
	}

	@JsonIgnore
	@Override
	public String getUsername(){
		return getLogin();
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}
