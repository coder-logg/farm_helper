package edu.itmo.isbd.controller;

import edu.itmo.isbd.model.Equipment;
import edu.itmo.isbd.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;

	@GetMapping("/{id}")
	@Secured({"FARMER", "ADMIN"})
	public ResponseEntity<Equipment> getEquipment(@PathVariable Integer id){
		return ResponseEntity.ok(equipmentService.getEquipmentOrThrow(id));
	}

	@PostMapping
	@Secured("ADMIN")
	public ResponseEntity<URI> postEquipment(@RequestBody Equipment equipment) throws URISyntaxException {
		return ResponseEntity.created(new URI("/equipments/" + equipmentService.saveEquipment(equipment).getId())).build();
	}

	@GetMapping
	@Secured({"FARMER", "ADMIN"})
	public ResponseEntity<List<Equipment>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
											  @RequestParam(defaultValue = "10") Integer pageSize,
											  @RequestParam(defaultValue = "name") String sortBy){
		return ResponseEntity.ok(equipmentService.getAllEquipments(pageNo, pageSize, sortBy));
	}

}
