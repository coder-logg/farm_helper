package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Farm;
import edu.itmo.isbd.entity.Farmer;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FarmerService {
	@Autowired
	private FarmerRepository farmerRepository;

	public Farmer getFarmer(String login){
		if (farmerRepository.existsFarmerByLogin(login))
			return farmerRepository.findFarmerByLogin(login);
		else throw new HttpException("Farmer with given username doesn't exist.", HttpStatus.NOT_FOUND);
	}

	public boolean checkFarmerExists(String login){
		return farmerRepository.existsFarmerByLogin(login);
	}
}
