package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Car;
import edu.itmo.isbd.entity.Driver;
import edu.itmo.isbd.exception.UserAlreadyRegisteredException;
import edu.itmo.isbd.repository.DriverRepository;
import edu.itmo.isbd.repository.OrderForDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private CarService carService;

	@Autowired
	@Lazy
	PasswordEncoder passwordEncoder;

	public Driver getOrThrow(String login) throws UsernameNotFoundException {
		return driverRepository
				.findDriverByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException("Driver with given username doesn't exist."));
	}

	@Transactional
	public Optional<Driver> findDriver(String login) {
		return driverRepository.findDriverByLogin(login);
	}

	public Driver saveOrThrow(Driver driver){
		if (!checkExists(driver.getLogin())) {
			driver.setPassword(passwordEncoder.encode(driver.getPassword()));
			return driverRepository.save(driver);
		} else throw new UserAlreadyRegisteredException("Driver with username " + driver.getLogin() + " already exists.");
	}

	public boolean checkExists(String login){
		return driverRepository.existsDriverByLogin(login);
	}

	public Car createOrUpdateCar(String login, Car car){
		Driver driver = getOrThrow(login);
		Car currentCar = driver.getCar();
		if (!Objects.isNull(currentCar)) {
			car.setId(currentCar.getId());
			return carService.saveCar(car);
		} else {
			driver.setCar(car);
			return driverRepository.save(driver).getCar();
		}
	}
}
