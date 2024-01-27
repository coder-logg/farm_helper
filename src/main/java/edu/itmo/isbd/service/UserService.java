package edu.itmo.isbd.service;

import edu.itmo.isbd.model.*;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.exception.UserAlreadyRegisteredException;
import edu.itmo.isbd.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@DependsOn("userRepository")
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminService adminService;

	@Autowired
	private DriverService driverService;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	@Deprecated
	public User registration(User user) throws UserAlreadyRegisteredException {
		if (userRepository.existsUserByLogin(user.getLogin()))
			throw new UserAlreadyRegisteredException("User with login '" + user.getLogin() +"' already exists.");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		switch (user.getROLE()){
			case ADMIN:
				return adminService.saveAdminOrThrow(new Admin(user));
			case DRIVER:
				return driverService.saveOrThrow(new Driver(user));
			case FARMER:
				return farmerService.saveFarmerOrThrow(new Farmer(user));
		}
		throw new IllegalStateException("Error in UserService.registration(): Unknown user role!");
	}

	public Role getUserRole(User user){
		return getUserRole(user.getLogin());
	}

	public Role getUserRole(String login) throws UsernameNotFoundException{
		if (userRepository.existsUserByLogin(login)) {
			if (driverService.checkExists(login))
				return Role.DRIVER;
			else if (farmerService.checkFarmerExists(login))
				return Role.FARMER;
			else if (adminService.checkAdminExists(login))
				return Role.ADMIN;
		}
		throw new UsernameNotFoundException("User '" + login + "' doesn't exist.");
	}

	public Optional<User> getUser(String login, Role role){
		switch (role){
			case ADMIN:
				return Optional.of(adminService.getAdminOrThrow(login));
			case FARMER:
				return Optional.of(farmerService.getFarmerOrThrow(login));
			case DRIVER:
				return Optional.of(driverService.getOrThrow(login));
		}
		return Optional.empty();
	}

	public Optional<? extends User> getUser(String login) {
		Optional<? extends User> res = adminService.findAdmin(login);
		if (res.isPresent())
			return res;
		res = farmerService.findFarmer(login);
		if (res.isPresent())
			return res;
		res = driverService.findDriver(login);
		return res;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	public void deleteUser(String login){
		if (userRepository.existsUserByLogin(login))
			userRepository.deleteByLogin(login);
		else throw new HttpException("User with given username doesn't exists.", HttpStatus.NOT_FOUND);
	}

	public User getRandomUser(){
		return userRepository.findRandomUser();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getUser(username).orElseThrow(() -> new UsernameNotFoundException("Unknown user: " + username));
	}


}
