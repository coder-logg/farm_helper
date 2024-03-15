package edu.itmo.isbd.controller;

import edu.itmo.isbd.dto.PlantDto;
import edu.itmo.isbd.model.Equipment;
import edu.itmo.isbd.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plants")
public class PlantController {
	@Autowired
	private PlantService plantService;

	@GetMapping("/{id}")
	public ResponseEntity<PlantDto> getPlant(@PathVariable Integer id) {
		return ResponseEntity.ok(new PlantDto(plantService.getPlantOrThrow(id)));
	}

	@GetMapping("/{id}/required-equipment")
	public ResponseEntity<List<Equipment>> getRequiredEquipment(@PathVariable Integer id) {
		return ResponseEntity.ok(plantService.getPlantOrThrow(id).getRequiredEquipment());
	}

	@PostMapping
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> postPlant(@RequestBody PlantDto plant) throws URISyntaxException {
		return ResponseEntity.created(new URI("/plants/" + plantService.savePlant(plant.mapToPlant()).getId())).build();
	}

	@GetMapping
	@Secured({"ROLE_FARMER", "ROLE_ADMIN"})
	public ResponseEntity<List<PlantDto>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
												 @RequestParam(defaultValue = "10") Integer pageSize,
												 @RequestParam(defaultValue = "name") String sortBy) {
		return ResponseEntity.ok(plantService.getAllPlants(pageNo, pageSize, sortBy)
				.stream().map(PlantDto::new).collect(Collectors.toList()));
	}
}
