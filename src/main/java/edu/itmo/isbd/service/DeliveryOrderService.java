package edu.itmo.isbd.service;

import edu.itmo.isbd.model.Arbitration;
import edu.itmo.isbd.model.DeliveryOrder;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.repository.ArbitrationRepository;
import edu.itmo.isbd.repository.DeliveryOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class DeliveryOrderService {

	@Autowired
	private DeliveryOrderRepository deliveryOrderRepository;

	@Autowired
	private ArbitrationRepository arbitrationRepository;

	@Autowired
	private DriverService driverService;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private AdminService adminService;

	@Transactional
	public DeliveryOrder saveOrThrow(DeliveryOrder deliveryOrder){
		if (!ObjectUtils.allNotNull(deliveryOrder.getCost(), deliveryOrder.getDriver(), deliveryOrder.getFarmer()))
			throw new HttpException("Incomplete data was given.", HttpStatus.UNPROCESSABLE_ENTITY);
		if (deliveryOrder.getId() == 0)
			log.info("deliveryOrder.id == 0");
		deliveryOrder.setFarmer(farmerService.getFarmerOrThrow(deliveryOrder.getFarmerLogin()));
		deliveryOrder.setDriver(driverService.getOrThrow(deliveryOrder.getDriverLogin()));
		DeliveryOrder deliveryOrderFromDb = deliveryOrderRepository.save(deliveryOrder);
		if (deliveryOrder.getId() == 0) {
			Arbitration arbitration = new Arbitration();
			arbitration.setAdmin(adminService.getRandomAdmin());
			arbitration.setFarmer(deliveryOrder.getFarmer());
			arbitration.setDriver(deliveryOrder.getDriver());
			arbitration.setDeliveryOrder(deliveryOrder);
			arbitration.setDeliveryOrder(deliveryOrderFromDb);
			deliveryOrderFromDb.setArbitrations(Arrays.asList(arbitrationRepository.save(arbitration)));
			log.info("arbitrations = {}", deliveryOrder.getArbitrations());
		}
		return deliveryOrderFromDb;

	}

	@Transactional
	public void removeOrThrow(int id) {
		if (exists(id))
			deliveryOrderRepository.deleteById(id);
		else throw new EntityNotFoundException("Order for drive with id=" + id + " wasn't found");
	}

	public DeliveryOrder get(int deliveryOrderId) throws EntityNotFoundException {
		return deliveryOrderRepository
				.findById(deliveryOrderId)
				.orElseThrow(() -> new EntityNotFoundException("Order for drive with id=" + deliveryOrderId + " wasn't found"));
	}

	public List<DeliveryOrder> getAllByFarmerLogin(String login) {
		return deliveryOrderRepository.findDeliveryOrdersByFarmer_Login(login);
	}

	public List<DeliveryOrder> getAllByDriverLogin(String login) {
		return deliveryOrderRepository.findDeliveryOrdersByDriver_Login(login);
	}

	public boolean exists(int id){
		return deliveryOrderRepository.existsById(id);
	}
}
