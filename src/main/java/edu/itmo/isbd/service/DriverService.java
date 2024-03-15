package edu.itmo.isbd.service;

import edu.itmo.isbd.dto.DriverDto;
import edu.itmo.isbd.model.Car;
import edu.itmo.isbd.model.DeliveryOrder;
import edu.itmo.isbd.model.DeliveryOrderProgressStage;
import edu.itmo.isbd.model.Driver;
import edu.itmo.isbd.exception.UserAlreadyRegisteredException;
import edu.itmo.isbd.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private CarService carService;

	@Autowired
	private DeliveryOrderService deliveryOrderService;

	@Autowired
	@Lazy
	PasswordEncoder passwordEncoder;

	public Driver getOrThrow(String login) throws UsernameNotFoundException {
		return driverRepository
				.findDriverByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException("Driver with given username doesn't exist."));
	}

	@Transactional
	public Optional<Driver> findDriver(String login) {
		return driverRepository.findDriverByLogin(login);
	}

	@Transactional
	public Driver saveOrThrow(Driver driver){
		if (!checkExists(driver.getLogin())) {
			driver.setPassword(passwordEncoder.encode(driver.getPassword()));
			return driverRepository.save(driver);
		} else throw new UserAlreadyRegisteredException("Driver with username " + driver.getLogin() + " already exists.");
	}

	public Driver saveOrThrow(DriverDto driverDto){
		if (!checkExists(driverDto.getLogin())) {
			driverDto.setPassword(passwordEncoder.encode(driverDto.getPassword()));
			return driverRepository.save(driverDto.mapToDriver());
		} else throw new UserAlreadyRegisteredException("Driver with username " + driverDto.getLogin() + " already exists.");
	}

	public boolean checkExists(String login){
		return driverRepository.existsDriverByLogin(login);
	}

	public Car createOrUpdateCar(String login, Car car){
		Driver driver = getOrThrow(login);
		Car currentCar = driver.getCar();
		if (!Objects.isNull(currentCar)) {
			car.setId(currentCar.getId());
			return carService.saveCar(car);
		} else {
			driver.setCar(car);
			return driverRepository.save(driver).getCar();
		}
	}

	public List<Driver> getAllDrivers(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Driver> pagedResult = driverRepository.findAll(paging);
		return pagedResult.getContent();
	}

	public List<DeliveryOrder> getMyDeliveryOrders(String login) {
		return deliveryOrderService.getAllByDriverLogin(login);
	}

	public DeliveryOrder updateDeliveryOrderStage(String login, Integer deliveryOrderId, DeliveryOrderProgressStage stage) {
		DeliveryOrder deliveryOrderFromDb = deliveryOrderService.get(deliveryOrderId);
		deliveryOrderFromDb.setStatus(stage);
		return deliveryOrderService.saveOrThrow(deliveryOrderFromDb);
	}
}
