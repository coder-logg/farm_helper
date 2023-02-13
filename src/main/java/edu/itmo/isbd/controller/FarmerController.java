package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Farmer;
import edu.itmo.isbd.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/farmer")
public class FarmerController {

	@Autowired
	private FarmerService farmerService;

	@GetMapping("/{login}")
	public ResponseEntity<Farmer> getFarmer(@PathVariable String login){
		return ResponseEntity.ok(farmerService.getFarmer(login));
	}
}
