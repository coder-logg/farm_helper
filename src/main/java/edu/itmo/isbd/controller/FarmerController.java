package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Admin;
import edu.itmo.isbd.entity.Farmer;
import edu.itmo.isbd.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

@Controller
@RequestMapping("/farmer")
public class FarmerController {
	@Autowired
	private FarmerService farmerService;

	@GetMapping("/login")
	public ResponseEntity<Farmer> getFarmer(Principal principal){
		return ResponseEntity.ok(farmerService.getFarmerOrThrow(principal.getName()));
	}

	@PostMapping(value = "/registration", produces = "application/json")
	public ResponseEntity<Farmer> registerAdmin(@RequestBody Farmer farmer) throws URISyntaxException {
		return ResponseEntity.created(new URI("/farmer/login")).body(farmerService.saveFarmerOrThrow(farmer));
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public void UsernameNotFoundExceptionHandler(UsernameNotFoundException exc, final HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value(), exc.getMessage());
	}
}
