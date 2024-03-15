package edu.itmo.isbd.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import edu.itmo.isbd.dto.OrderDto;
import edu.itmo.isbd.exception.EntitySaveException;
import edu.itmo.isbd.model.Customer;
import edu.itmo.isbd.model.Order;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.model.Plant;
import edu.itmo.isbd.repository.OrderRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private PlantService plantService;

	@Nullable
	public Order saveOrder(@NonNull Order order){
		order.setFarmer(farmerService.getFarmerOrThrow(order.getFarmer().getLogin()));
		return orderRepository.save(order);
	}

	@Transactional
	public Order saveOrder(@NonNull OrderDto orderDto){
		Order order = Order.builder()
				.id(orderDto.getId())
				.farmer(farmerService.getFarmerOrThrow(orderDto.getFarmerLogin()))
				.amount(orderDto.getAmount())
				.cost(orderDto.getCost())
				.plant(plantService.getPlantOrThrow(orderDto.getPlantName()))
				.build();
		if (orderDto.getCustomer().getId() != 0)
			order.setCustomer(customerService.getCustomerOrThrow(orderDto.getCustomer().getId()));
		else if (order.getCustomer().getName() != null)
			order.setCustomer(customerService.getCustomerOrThrow(orderDto.getCustomer().getName()));
		if (orderDto.getStatus() != null)
			order.setStatus(orderDto.getStatus());
		log.info("saving new order: {}", order);
		return orderRepository.save(order);
	}

	@Nullable
	@Transactional
	public Order updateOrder(@NonNull OrderDto orderDto){
		if (orderDto.getId() == null)
			throw new EntitySaveException("Order has to have non null 'id'", HttpStatus.UNPROCESSABLE_ENTITY);
		Order orderFromDb = get(orderDto.getId());
		orderFromDb.setAmount(orderDto.getAmount());
		orderFromDb.setCost(orderDto.getCost());
		orderFromDb.setPlant(plantService.getPlantOrThrow(orderDto.getPlantName()));
		orderFromDb.setCustomer(orderDto.getCustomer());
		return orderRepository.save(orderFromDb);
	}

	public List<Order> getAll(Integer pageNo, Integer pageSize, String sortBy) {
		return orderRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy))).getContent();
	}

	//todo test
	public List<Customer> getCustomersByFarmerLogin(String login) {
		return orderRepository.findCustomerByFarmerLogin(login);
	}

	public boolean deleteOrder(@NonNull Order order) throws HttpException {
		if (orderRepository.existsOrderById(order.getId())) {
			orderRepository.delete(order);
			if (orderRepository.existsOrderById(order.getId()))
				throw new HttpException("Something went wrong during delete order from db.", HttpStatus.INTERNAL_SERVER_ERROR);
			else return true;
		} else throw new EntityNotFoundException("Order: " + order.getId() + "doesn't exists.");
	}

	public Order get(int id) throws EntityNotFoundException {
		return orderRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Order with given id = " + id + "doesn't exists"));
	}

	public List<Order> getOrdersByFarmerLogin(String login) {
		return orderRepository.findOrdersByFarmer_Login(login);
	}

}
