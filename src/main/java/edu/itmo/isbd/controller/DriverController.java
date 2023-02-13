package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Driver;
import edu.itmo.isbd.service.DriverService;
import edu.itmo.isbd.service.OrderForDriveService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@Autowired
	private OrderForDriveService orderForDriveService;

	@GetMapping("/{login}")
	public ResponseEntity<Driver> getFarmer(@PathVariable String login){
		return ResponseEntity.ok(driverService.getDriver(login));
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public void exceptions(ConstraintViolationException exc, final HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), exc.getMessage());
	}
}
