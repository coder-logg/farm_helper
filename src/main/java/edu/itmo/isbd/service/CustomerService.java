package edu.itmo.isbd.service;

import edu.itmo.isbd.model.Customer;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Transactional
	public Customer getCustomerOrThrow(int id) {
		return customerRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Not found customer with id = " + id));
	}

	@Transactional
	public Customer getCustomerOrThrow(String name) {
		return customerRepository
				.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException("Not found customer with name = " + name));
	}

	@Transactional
	public Optional<Customer> getCustomer(int id) {
		return customerRepository.findById(id);
	}

	@Transactional
	public List<Customer> getAllCustomers(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Customer> pagedResult = customerRepository.findAll(paging);
		return pagedResult.getContent();
	}
}
