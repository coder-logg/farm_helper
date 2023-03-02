package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Order;
import edu.itmo.isbd.entity.OrderStatus;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.repository.OrderRepository;
import edu.itmo.isbd.repository.StatusRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private StatusRepository statusRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Nullable
	public Order saveOrder(@NonNull Order order){
		//insert into status (location, progress) values (:status.location, :status.progress::progress_stages)"
		order.setFarmer(farmerService.getFarmerOrThrow(order.getFarmerLogin()));
		return orderRepository.save(order);
	}

//	@Transactional
//	public void saveStatus(OrderStatus status){
//
//		entityManager.createNativeQuery("insert into status (location, progress) values (:location, :progress)")
//				.setParameter("location", status.getLocation())
//				.setParameter("progress", status.getProgress().name()).executeUpdate();
//	}

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
