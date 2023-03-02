package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Customer;
import edu.itmo.isbd.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;


	@PreAuthorize("hasAnyRole('FARMER', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
		return ResponseEntity.ok(customerService.getCustomerOrThrow(id));
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('FARMER', 'ADMIN')")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		customerService.save(customer);
		return ResponseEntity.ok().build();
	}

}
