package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.OrderForDrive;
import edu.itmo.isbd.entity.User;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.service.OrderForDriveService;
import edu.itmo.isbd.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders-for-drive")
public class OrderForDriveController {
	@Autowired
	private OrderForDriveService orderForDriveService;

	@PreAuthorize("hasRole('FARMER')")
	@GetMapping({"/{id}"})
	public ResponseEntity<OrderForDrive> getOrderForDrive(@PathVariable Integer id, Authentication auth) {
		OrderForDrive orderForDrive = orderForDriveService.get(id);
		User user = (User) auth.getPrincipal();
		if (user.getLogin().equals(orderForDrive.getDriverLogin())
				|| user.getLogin().equals(orderForDrive.getFarmerLogin())
				|| user.getROLE().equals(UserService.Role.ADMIN))
			return ResponseEntity.ok(orderForDrive);
		else throw new HttpException("You don't have access for requested data.", HttpStatus.FORBIDDEN);
	}

	@PreAuthorize("hasRole('FARMER') and authentication.name == orderForDrive.farmerLogin")
	@PostMapping("/add-order")
	public ResponseEntity<OrderForDrive> addOrderForDrive(@RequestBody OrderForDrive orderForDrive, Authentication auth, Principal principal) throws URISyntaxException {
		if (!ObjectUtils.allNotNull(orderForDrive.getFarmerLogin(), orderForDrive.getOrderId()))
			throw new HttpException("Incomplete data was given. Required fields: farmerLogin, orderId.", HttpStatus.UNPROCESSABLE_ENTITY);
		if (checkAccess(auth, orderForDrive.getFarmerLogin()))
			throw  new HttpException("You don't have access to create given order for drive.", HttpStatus.FORBIDDEN);
		OrderForDrive orderForDriveFromDb = orderForDriveService.saveOrThrow(orderForDrive);
		return ResponseEntity
				.created(new URI("/orders-for-drive/" + orderForDrive.getId()))
				.body(orderForDriveFromDb);
	}

	@DeleteMapping("/drop/{id}")
	public ResponseEntity<String> deleteOrderForDrive(@PathVariable Integer id, Authentication auth){
		OrderForDrive orderForDriveFromDb = orderForDriveService.get(id);
		if (checkAccess(auth, orderForDriveFromDb.getFarmerLogin())) {
			orderForDriveService.removeOrThrow(id);
			return ResponseEntity.ok("Entity deleted");
		} else throw new HttpException("You don't have access to delete order for drive with given id = " + id, HttpStatus.FORBIDDEN);
	}

	private boolean checkAccess(Authentication auth, String farmerLogin){
		List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		return !(roles.contains("ADMIN")
				|| (auth.getName().equals(farmerLogin)
				&& roles.contains("FARMER")));
	}
}
