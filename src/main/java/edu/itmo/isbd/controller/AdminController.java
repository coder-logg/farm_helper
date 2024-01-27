package edu.itmo.isbd.controller;

import edu.itmo.isbd.model.Admin;
import edu.itmo.isbd.model.Arbitration;
import edu.itmo.isbd.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/login")
	public ResponseEntity<?> getAdmin(Principal principal){
		return ResponseEntity.ok(adminService.getAdminOrThrow(principal.getName()));
	}

	@PostMapping(value = "/registration", produces = "application/json")
	public ResponseEntity<Admin> register(@RequestBody Admin admin) throws URISyntaxException {
		return ResponseEntity.created(new URI("/admin/login")).body(adminService.saveAdminOrThrow(admin));
	}

	@GetMapping("/{login}/arbitrations")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<List<Arbitration>> getArbitrations(@PathVariable String login){
		return ResponseEntity.ok(adminService.getAdminOrThrow(login).getArbitrations());
	}

}
