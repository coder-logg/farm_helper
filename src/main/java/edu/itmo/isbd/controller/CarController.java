package edu.itmo.isbd.controller;

import edu.itmo.isbd.model.Car;
import edu.itmo.isbd.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
	@Autowired
	private CarService carService;

	@GetMapping("/{id}")
	public ResponseEntity<Car> getCar(@PathVariable Integer id){
		return ResponseEntity.ok(carService.getCarOrThrow(id));
	}

	@GetMapping("/{id}/{field}")
	public ResponseEntity<?> getCar(@PathVariable Integer id, @PathVariable String field){
		try {
			return ResponseEntity.ok(Car.class.getDeclaredField(field).get(carService.getCarOrThrow(id)));
		} catch (NoSuchFieldException e) {
			return ResponseEntity.badRequest().body("Car hasn't field '" + field + "'");
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
	public ResponseEntity<?> postCar(@RequestBody Car car, Authentication auth) throws URISyntaxException {
		return ResponseEntity.created(new URI("/cars/" + carService.saveCar(car).getId())).build();
	}

	@GetMapping
	public ResponseEntity<List<Car>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
											  @RequestParam(defaultValue = "10") Integer pageSize,
											  @RequestParam(defaultValue = "name") String sortBy){
		return ResponseEntity.ok(carService.getAllCars(pageNo, pageSize, sortBy));
	}
}
