package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.OrderForDrive;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.repository.OrderForDriveRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderForDriveService {

	@Autowired
	private OrderForDriveRepository orderForDriveRepository;

	public OrderForDrive saveOrThrow(OrderForDrive orderForDrive){
		if (ObjectUtils.allNotNull(orderForDrive.getCost(), orderForDrive.getDriver(), orderForDrive.getFarmer()))
			return orderForDriveRepository.save(orderForDrive);
		else throw new HttpException("Incomplete data was given.", HttpStatus.UNPROCESSABLE_ENTITY);
	}

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
