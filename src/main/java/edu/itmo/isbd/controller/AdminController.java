package edu.itmo.isbd.controller;

import edu.itmo.isbd.dto.RegistrationDto;
import edu.itmo.isbd.dto.UserDto;
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
	public ResponseEntity<UserDto> getAdmin(Principal principal){
		return ResponseEntity.ok(new UserDto(adminService.getAdminOrThrow(principal.getName())));
	}

	@PostMapping(value = "/registration", produces = "application/json")
	public ResponseEntity<UserDto> register(@RequestBody RegistrationDto adminDto) throws URISyntaxException {
		return ResponseEntity.created(new URI("/admin/login"))
				.body(new UserDto(adminService.saveAdminOrThrow(new Admin(adminDto.getLogin(), adminDto.getPhone(), adminDto.getMail(), adminDto.getPassword()))));
	}

	@GetMapping("/{login}/arbitrations")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<List<Arbitration>> getArbitrations(@PathVariable String login){
		return ResponseEntity.ok(adminService.getAdminOrThrow(login).getArbitrations());
	}

}
