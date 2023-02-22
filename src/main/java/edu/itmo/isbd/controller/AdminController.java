package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Admin;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.service.AdminService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

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
	public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) throws URISyntaxException {
		return ResponseEntity.created(new URI("/admin/login")).body(adminService.saveAdminOrThrow(admin));
	}
}
