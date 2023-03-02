package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Customer;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderService orderService;

	@Transactional
	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	@Transactional
	public Customer getCustomerOrThrow(int id) {
		return customerRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Not found customer with id = " + id));
	}

	@Transactional
	public Optional<Customer> getCustomer(int id) {
		return customerRepository.findById(id);
	}

	@Transactional
	public Set<Customer> getAllCustomersByFarmerLogin(String login) {
		return customerRepository.findDistinctByOrdersIn(orderService.getOrdersByFarmerLogin(login));
	}
}
