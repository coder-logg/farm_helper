package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Farmer;
import edu.itmo.isbd.exception.UserAlreadyRegisteredException;
import edu.itmo.isbd.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FarmerService {
	@Autowired
	private FarmerRepository farmerRepository;

	@Autowired
	@Lazy
	PasswordEncoder passwordEncoder;

	public Farmer getFarmerOrThrow(String login) throws UsernameNotFoundException{
		return farmerRepository
				.findFarmerByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException("Farmer with given username doesn't exist"));
	}

	public Farmer saveFarmerOrThrow(Farmer farmer){
		if (!checkFarmerExists(farmer.getLogin())) {
			farmer.setPassword(passwordEncoder.encode(farmer.getPassword()));
			return farmerRepository.save(farmer);
		} else throw new UserAlreadyRegisteredException("Farmer with username " + farmer.getLogin() + " already exists.");
	}

	public boolean checkFarmerExists(String login){
		return farmerRepository.existsFarmerByLogin(login);
	}
}
