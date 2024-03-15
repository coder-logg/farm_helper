package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;


@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User  {
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "admin", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Arbitration> arbitrations;

	{
		super.role = Role.ADMIN;
	}

	public Admin() {}

	public Admin(User user){
		copyProperties(user, this);
		super.role = Role.ADMIN;
	}
	public Admin(String login, String phone, String mail, String password){
		super(login, phone, mail, password);
		super.role = Role.ADMIN;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.stream(Role.values())
				.map(x -> new SimpleGrantedAuthority("ROLE_" + x.name()))
				.collect(Collectors.toList());
	}

	@Nullable
	@JsonProperty
	public List<Integer> getArbitrationIds(){
		if (arbitrations == null)
			return null;
		return arbitrations.stream().map(Arbitration::getId).collect(Collectors.toList());
	}

	@JsonProperty
	public void setArbitrationIds(List<Integer> arbitrationIds){
		if (Objects.isNull(arbitrationIds))
			return;
		this.arbitrations = new ArrayList<>();
		arbitrationIds.forEach(x -> {
			Arbitration arbitr = new Arbitration();
			arbitr.setId(x);
			this.arbitrations.add(arbitr);
		});
	}
}

