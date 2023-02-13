package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Driver;
import edu.itmo.isbd.entity.Farmer;
import edu.itmo.isbd.entity.OrderForDrive;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.repository.DriverRepository;
import edu.itmo.isbd.repository.OrderForDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private OrderForDriveRepository orderForDriveRepository;

	public Driver getDriver(String login){
		if (driverRepository.existsDriverByLogin(login))
			return driverRepository.findDriverByLogin(login);
		else throw new HttpException("Driver with given username doesn't exist.", HttpStatus.NOT_FOUND);
	}

	public boolean checkDriverExists(String login){
		return driverRepository.existsDriverByLogin(login);
	}
}
