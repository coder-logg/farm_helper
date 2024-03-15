package edu.itmo.isbd.service;

import edu.itmo.isbd.dto.CreationDeliveryOrderDto;
import edu.itmo.isbd.dto.DeliveryOrderDto;
import edu.itmo.isbd.model.Arbitration;
import edu.itmo.isbd.model.DeliveryOrder;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.model.Order;
import edu.itmo.isbd.repository.ArbitrationRepository;
import edu.itmo.isbd.repository.DeliveryOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class DeliveryOrderService {

	@Autowired
	private DeliveryOrderRepository deliveryOrderRepository;

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
		deliveryOrder.setFarmer(farmerService.getFarmerOrThrow(deliveryOrder.getFarmer().getLogin()));
		deliveryOrder.setDriver(driverService.getOrThrow(deliveryOrder.getDriver().getLogin()));
		DeliveryOrder deliveryOrderFromDb = deliveryOrderRepository.save(deliveryOrder);
		if (deliveryOrder.getId() == null) {
			Arbitration arbitration = new Arbitration();
			arbitration.setAdmin(adminService.getRandomAdmin());
			arbitration.setFarmer(deliveryOrder.getFarmer());
			arbitration.setDriver(deliveryOrder.getDriver());
			arbitration.setDeliveryOrder(deliveryOrder);
			arbitration.setDeliveryOrder(deliveryOrderFromDb);
		}
		return deliveryOrderFromDb;
	}

	public DeliveryOrder saveOrThrow(DeliveryOrderDto deliveryOrderDto) {
		DeliveryOrder deliveryOrder = new DeliveryOrder();
		BeanUtils.copyProperties(deliveryOrderDto, deliveryOrder);
		deliveryOrder.setFarmer(farmerService.getFarmerOrThrow(deliveryOrderDto.getFarmerLogin()));
		deliveryOrder.setDriver(driverService.getOrThrow(deliveryOrderDto.getDriverLogin()));
		deliveryOrder.setOrder(new Order(deliveryOrderDto.getOrderId()));
		return deliveryOrderRepository.save(deliveryOrder);
	}

	public DeliveryOrder create(CreationDeliveryOrderDto creationDeliveryOrderDto) {
		DeliveryOrderDto deliveryOrderDto = new DeliveryOrderDto();
		BeanUtils.copyProperties(creationDeliveryOrderDto, deliveryOrderDto);
		return saveOrThrow(deliveryOrderDto);
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
