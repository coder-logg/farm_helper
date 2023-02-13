package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.*;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.exception.UserAlreadyRegisteredException;
import edu.itmo.isbd.repository.AdminRepository;
import edu.itmo.isbd.repository.DriverRepository;
import edu.itmo.isbd.repository.FarmerRepository;
import edu.itmo.isbd.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@DependsOn("userRepository")
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private FarmerRepository farmerRepository;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ApplicationContext context;

	public User registration(User user) throws UserAlreadyRegisteredException {
		if (userRepository.existsUserByLogin(user.getLogin()))
			throw new UserAlreadyRegisteredException("User with login '" + user.getLogin() +"' already exists.");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		switch (user.getROLE()){
			case ADMIN:
				Admin admin = new Admin(user);
				return adminRepository.save(admin);
			case DRIVER:
				Driver driver = new Driver(user);
				return driverRepository.save(driver);
			case FARMER:
				Farmer farmer = new Farmer(user);
				return farmerRepository.save(farmer);
		}
		throw new IllegalStateException("Error in UserService.registration(): Unknown user role!");
	}

	@AllArgsConstructor
	@Getter
	public enum Role {
		FARMER(FarmerRepository.class, Farmer.class),
		DRIVER(DriverRepository.class, Driver.class),
		ADMIN(AdminRepository.class, Admin.class);
		private final Class<? extends CrudRepository<?, ?>> repository;
		private final Class<? extends User> entity;
		public Class<? extends CrudRepository<?, ?>> getRepository() {
			return repository;
		}
	}

	public Role getUserRole(User user){
		return getUserRole(user.getLogin());
	}
	
	public Role getUserRole(String login) throws UsernameNotFoundException{
		if (userRepository.existsUserByLogin(login)) {
			DriverRepository driverRepository = context.getBean(DriverRepository.class);
			AdminRepository adminRepository = context.getBean(AdminRepository.class);
			FarmerRepository farmerRepository = context.getBean(FarmerRepository.class);
			if (driverRepository.existsDriverByLogin(login))
				return Role.DRIVER;
			else if (farmerRepository.existsFarmerByLogin(login))
				return Role.FARMER;
			else if (adminRepository.existsAdminByLogin(login))
				return Role.ADMIN;
		}
		throw new UsernameNotFoundException("User with username: " + login + " doesn't exist.");
	}

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
		User userFromDb = userRepository.findUserByLogin(username);
		if (userFromDb == null)
			throw new UsernameNotFoundException("Unknown user: " + username);
		return userFromDb;
	}
}
