package edu.itmo.isbd.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.itmo.isbd.service.UserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;


@Entity
@Data
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
	@OneToMany(mappedBy = "admin")
	private List<Arbitration> arbitrations;

	{
		super.ROLE = UserService.Role.ADMIN;
	}

	public Admin() {}

	public Admin(User user){
		copyProperties(user, this);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.stream(UserService.Role.values())
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
		this.arbitrations = new ArrayList<>();
		arbitrationIds.forEach(x -> {
			Arbitration arbitr = new Arbitration();
			arbitr.setId(x);
			this.arbitrations.add(arbitr);
		});
	}
}

