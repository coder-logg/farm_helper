package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Order;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.repository.OrderRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Nullable
	public Order saveOrder(@NonNull Order order){
		return orderRepository.save(order);
	}

	public boolean deleteOrder(@NonNull Order order){
		if (orderRepository.existsOrderById(order.getId())) {
			orderRepository.delete(order);
			if (orderRepository.existsOrderById(order.getId()))
				throw new HttpException("Something went wrong during delete order from db. Maybe value doesn't exists in db", HttpStatus.INTERNAL_SERVER_ERROR);
			else return true;
		}
		else throw new HttpException("Order: " + order.getId() + "doesn't exists.", HttpStatus.CONFLICT);
	}


}
