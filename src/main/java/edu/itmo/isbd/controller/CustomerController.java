package edu.itmo.isbd.controller;

import edu.itmo.isbd.model.Customer;
import edu.itmo.isbd.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/customers")
@PreAuthorize("hasAnyRole('FARMER', 'ADMIN')")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
		return ResponseEntity.ok(customerService.getCustomerOrThrow(id));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) throws URISyntaxException {
		return ResponseEntity.created(new URI("/customers/" + customerService.save(customer).getId())).build();
	}

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(defaultValue = "0") Integer pageNo,
														  @RequestParam(defaultValue = "10") Integer pageSize,
														  @RequestParam(defaultValue = "name") String sortBy) {
		return ResponseEntity.ok(customerService.getAllCustomers(pageNo, pageSize, sortBy));
	}

}
