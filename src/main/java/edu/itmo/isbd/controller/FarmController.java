package edu.itmo.isbd.controller;

import edu.itmo.isbd.model.Farm;
import edu.itmo.isbd.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farms")
@RequiredArgsConstructor
public class FarmController {
	private final FarmService service;

	@GetMapping("/{id}")
	public ResponseEntity<Farm> get(@PathVariable Integer id) {
		return ResponseEntity.ok(service.get(id));
	}

	@PostMapping
	public ResponseEntity<Farm> postFarm(@RequestBody Farm farm) {
		return ResponseEntity.ok(service.save(farm));
	}
}
