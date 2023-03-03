package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Arbitration;
import edu.itmo.isbd.entity.OrderForDrive;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.repository.AdminRepository;
import edu.itmo.isbd.repository.ArbitrationRepository;
import edu.itmo.isbd.repository.OrderForDriveRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class OrderForDriveService {

	@Autowired
	private OrderForDriveRepository orderForDriveRepository;

	@Autowired
	private ArbitrationRepository arbitrationRepository;

	@Autowired
	private DriverService driverService;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private AdminService adminService;

	@Transactional
	public OrderForDrive saveOrThrow(OrderForDrive orderForDrive){
		if (!ObjectUtils.allNotNull(orderForDrive.getCost(), orderForDrive.getDriver(), orderForDrive.getFarmer()))
			throw new HttpException("Incomplete data was given.", HttpStatus.UNPROCESSABLE_ENTITY);
		if (orderForDrive.getId() == 0)
			log.info("orderForDrive.id == 0");
		orderForDrive.setFarmer(farmerService.getFarmerOrThrow(orderForDrive.getFarmerLogin()));
		orderForDrive.setDriver(driverService.getOrThrow(orderForDrive.getDriverLogin()));
		OrderForDrive orderForDriveFromDb = orderForDriveRepository.save(orderForDrive);
		if (orderForDrive.getId() == 0) {
			Arbitration arbitration = new Arbitration();
			arbitration.setAdmin(adminService.getRandomAdmin());
			arbitration.setFarmer(orderForDrive.getFarmer());
			arbitration.setDriver(orderForDrive.getDriver());
			arbitration.setOrderForDrive(orderForDrive);
			arbitration.setOrderForDrive(orderForDriveFromDb);
			orderForDriveFromDb.setArbitrations(Arrays.asList(arbitrationRepository.save(arbitration)));
			log.info("arbitrations = {}", orderForDrive.getArbitrations());
		}
		return orderForDriveFromDb;

	}

	@Transactional
	public void removeOrThrow(int id) {
		if (exists(id))
			orderForDriveRepository.deleteById(id);
		else throw new EntityNotFoundException("Order for drive with id=" + id + " wasn't found");
	}

	public OrderForDrive get(int orderForDriveId) throws EntityNotFoundException {
		return orderForDriveRepository
				.findById(orderForDriveId)
				.orElseThrow(() -> new EntityNotFoundException("Order for drive with id=" + orderForDriveId + " wasn't found"));
	}

	public List<OrderForDrive> getAllByFarmerLogin(String login) {
		return orderForDriveRepository.findOrderForDrivesByFarmer_Login(login);
	}

	public List<OrderForDrive> getAllByDriverLogin(String login) {
		return orderForDriveRepository.findOrderForDrivesByDriver_Login(login);
	}

	public boolean exists(int id){
		return orderForDriveRepository.existsById(id);
	}
}
