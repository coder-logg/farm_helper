package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Admin;
import edu.itmo.isbd.entity.Farmer;
import edu.itmo.isbd.exception.UserAlreadyRegisteredException;
import edu.itmo.isbd.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AdminService{
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	@Lazy
	PasswordEncoder passwordEncoder;

	public Admin registration(Admin user){
		return adminRepository.save(user);
	}

	public Admin saveAdminOrThrow(Admin admin){
		if (!checkAdminExists(admin.getLogin())) {
			admin.setPassword(passwordEncoder.encode(admin.getPassword()));
			return adminRepository.save(admin);
		}
		else throw new UserAlreadyRegisteredException("Admin with username " + admin.getLogin() + " already exists.");
	}

	@Transactional
	public Optional<Admin> findAdmin(String login) {
		return adminRepository.findAdminByLogin(login);
	}

	@Transactional
	public boolean checkAdminExists(String login){
		return adminRepository.existsAdminByLogin(login);
	}

	public Admin getAdminOrThrow(String login) throws UsernameNotFoundException {
			return adminRepository
					.findAdminByLogin(login)
					.orElseThrow(() -> new UsernameNotFoundException("Admin with username " + login + " was not found"));
	}
}