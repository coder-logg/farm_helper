package edu.itmo.isbd.service;

import edu.itmo.isbd.model.Farm;
import edu.itmo.isbd.model.Farmer;
import edu.itmo.isbd.exception.UserAlreadyRegisteredException;
import edu.itmo.isbd.repository.FarmRepository;
import edu.itmo.isbd.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
public class FarmerService {
	@Autowired
	private FarmerRepository farmerRepository;

	@Autowired
	private FarmRepository farmRepository;

	@Autowired
	@Lazy
	PasswordEncoder passwordEncoder;

	public Farmer getFarmerOrThrow(String login) throws UsernameNotFoundException{
		return farmerRepository
				.findFarmerByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException("Farmer with given username doesn't exist"));
	}

	@Transactional
	public Optional<Farmer> findFarmer(String login) {
		return farmerRepository.findFarmerByLogin(login);
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

	public Farm getFarmByFarmerLogin(String farmerLogin) {
		return getFarmerOrThrow(farmerLogin).getFarm();
	}

	public Farm createOrUpdateFarm(String login, Farm farm){
		Farmer farmer = getFarmerOrThrow(login);
		Farm currentFarm = farmer.getFarm();
		if (!Objects.isNull(currentFarm)) {
			farm.setId(currentFarm.getId());
			return farmRepository.save(farm);
		} else {
			farmer.setFarm(farm);
			return farmerRepository.save(farmer).getFarm();
		}
	}


}
