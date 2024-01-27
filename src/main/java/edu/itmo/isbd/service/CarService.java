package edu.itmo.isbd.service;

import edu.itmo.isbd.model.Car;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;

	public Car getCarOrThrow(int id) {
		return carRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Car with id = " + id + "wasn't found"));
	}

	public Optional<Car> getCar(int id){
		return carRepository.findById(id);
	}

	public Car saveCar(Car car) {
		return carRepository.save(car);
	}

	public List<Car> getAllCars(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Car> pagedResult = carRepository.findAll(paging);
		return pagedResult.getContent();
	}

}
