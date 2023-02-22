package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Admin;
import edu.itmo.isbd.entity.Driver;
import edu.itmo.isbd.exception.UserAlreadyRegisteredException;
import edu.itmo.isbd.repository.DriverRepository;
import edu.itmo.isbd.repository.OrderForDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private OrderForDriveRepository orderForDriveRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public Driver getDriverOrThrow(String login) throws UsernameNotFoundException {
		if (checkDriverExists(login))
			return driverRepository.findDriverByLogin(login);
		else throw new UsernameNotFoundException("Driver with given username doesn't exist.");
	}

	public Driver saveDriverOrThrow(Driver driver){
		if (!checkDriverExists(driver.getLogin())) {
			driver.setPassword(passwordEncoder.encode(driver.getPassword()));
			return driverRepository.save(driver);
		}
		else throw new UserAlreadyRegisteredException("Driver with username " + driver.getLogin() + " already exists.");
	}

	public boolean checkDriverExists(String login){
		return driverRepository.existsDriverByLogin(login);
	}
}
