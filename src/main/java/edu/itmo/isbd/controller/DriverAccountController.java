package edu.itmo.isbd.controller;

import edu.itmo.isbd.dto.DeliveryOrderDto;
import edu.itmo.isbd.dto.DriverDto;
import edu.itmo.isbd.dto.RegistrationDto;
import edu.itmo.isbd.model.Car;
import edu.itmo.isbd.model.DeliveryOrderProgressStage;
import edu.itmo.isbd.model.Driver;
import edu.itmo.isbd.service.DriverService;
import edu.itmo.isbd.service.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/driver")
public class DriverAccountController {

	@Autowired
	private DriverService driverService;

	@Autowired
	private DeliveryOrderService deliveryOrderService;

	@GetMapping("/{login}")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<DriverDto> getDriver(@PathVariable @P("login") String login, Principal principal) {
		return ResponseEntity.ok(new DriverDto(driverService.getOrThrow(principal.getName())));
	}

	@PostMapping(value = "/registration", produces = "application/json")
	public ResponseEntity<DriverDto> register(@RequestBody RegistrationDto driverDto) throws URISyntaxException {
		return ResponseEntity
				.created(new URI("/driver/login"))
				.body(new DriverDto(driverService.saveOrThrow(new Driver(driverDto.getLogin(), driverDto.getPhone(), driverDto.getMail(), driverDto.getPassword()))));
	}

	@GetMapping("/{login}/delivery-orders")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<List<DeliveryOrderDto>> getDeliveryOrders(@PathVariable @P("login") String login) {
		return ResponseEntity.ok(driverService.getMyDeliveryOrders(login).stream().map(DeliveryOrderDto::new).collect(Collectors.toList()));
	}

	@GetMapping("/{login}/car")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<Car> getCar(@PathVariable @P("login") String login) {
		return ResponseEntity.ok(driverService.getOrThrow(login).getCar());
	}

	@PutMapping("/{login}/car")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<?> setCarForDriver(@PathVariable @P("login") String login, @RequestBody Car car) throws URISyntaxException {
		return ResponseEntity
				.created(new URI("/cars/" + driverService.createOrUpdateCar(login, car).getId()))
				.build();
	}

	@GetMapping
	public ResponseEntity<List<DriverDto>> getAllDrivers(@RequestParam(defaultValue = "0") Integer pageNo,
														 @RequestParam(defaultValue = "10") Integer pageSize,
														 @RequestParam(defaultValue = "login") String sortBy) {
		return ResponseEntity
				.ok(driverService.getAllDrivers(pageNo, pageSize, sortBy)
				.stream().map(DriverDto::new).collect(Collectors.toList()));
	}

	@PutMapping("/{login}/delivery-orders/{orderId}")
	public ResponseEntity<DeliveryOrderDto> updateDeliveryOrderStatus(@PathVariable String login,
																	  @RequestParam DeliveryOrderProgressStage nextStage,
																	  @PathVariable Integer orderId){
		return ResponseEntity.ok(new DeliveryOrderDto(driverService.updateDeliveryOrderStage(login, orderId, nextStage)));
	}
}
