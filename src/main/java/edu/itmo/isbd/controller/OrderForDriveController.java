package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.OrderForDrive;
import edu.itmo.isbd.entity.User;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.service.DriverService;
import edu.itmo.isbd.service.FarmerService;
import edu.itmo.isbd.service.OrderForDriveService;
import edu.itmo.isbd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/")
public class OrderForDriveController {
	@Autowired
	private OrderForDriveService orderForDriveService;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private DriverService driverService;

	@Autowired
	private UserService userService;

	@GetMapping({"/driver/{login}/orders-for-drive/{orderForDriveId}", "/farmer/{login}/orders-for-drive/{orderForDriveId}"})
	public ResponseEntity<OrderForDrive> getOrderForDrive(@PathVariable String login, @PathVariable Integer orderForDriveId, Authentication auth){
		if (((User) auth.getPrincipal()).getLogin().equals(login));
		OrderForDrive orderForDrive = orderForDriveService.getOrderForDrive(orderForDriveId);
		if (orderForDrive.getFarmer().getLogin().equals(login) || orderForDrive.getDriver().getLogin().equals(login))
			return ResponseEntity.ok(orderForDrive);
		else throw new HttpException("You don't have access for requested data.", HttpStatus.FORBIDDEN);
	}

	@PostMapping("/farmer/{login}/addOrderForDrive")
	public ResponseEntity<OrderForDrive> addOrderForDrive(@PathVariable String login,
												  @RequestBody OrderForDrive orderForDrive,
												  HttpServletRequest request) throws URISyntaxException {
//		if (userService.getUserRole(login) == UserService.Role.FARMER) {
			OrderForDrive orderForDriveFromDb = orderForDriveService.addOrderForDrive(orderForDrive);
			return ResponseEntity
					.created(new URI(request.getContextPath() + "/orderForDrive/" + orderForDrive.getId()))
					.body(orderForDriveFromDb);
//		} else throw new HttpException("Farmer with given username doesn't exists.", HttpStatus.FORBIDDEN);
	}

//	@GetMapping({"/farmer/{login}/{orderForDriveId}"})
//	public ResponseEntity<OrderForDrive> getOrderForDrive(@PathVariable String login, @PathVariable Integer orderForDriveId){
//
//	}
}
