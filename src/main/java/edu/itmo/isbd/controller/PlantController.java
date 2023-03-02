package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Plant;
import edu.itmo.isbd.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plants")
public class PlantController {
	@Autowired
	private PlantService plantService;

	@GetMapping("/{id}")
	public ResponseEntity<Plant> getPlant(@PathVariable Integer id){
		return ResponseEntity.ok(plantService.getPlantOrThrow(id));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> postPlant(@RequestBody Plant plant) {
		plantService.savePlant(plant);
		return ResponseEntity.ok().build();
	}

//	@GetMapping("/all")
//	@PreAuthorize("hasAnyRole('FARMER', 'ADMIN')")
//	public ResponseEntity<List<Plant>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
//											  @RequestParam(defaultValue = "10") Integer pageSize,
//											  @RequestParam(defaultValue = "name") String sortBy){
//		return ResponseEntity.ok(plantService.getAllPlants(pageNo, pageSize, sortBy));
//	}
}
