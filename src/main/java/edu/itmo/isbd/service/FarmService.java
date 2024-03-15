package edu.itmo.isbd.service;

import edu.itmo.isbd.FarmHelper;
import edu.itmo.isbd.Utils;
import edu.itmo.isbd.model.Farm;
import edu.itmo.isbd.model.Location;
import edu.itmo.isbd.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FarmService {
	private final FarmRepository repository;
	private final LocationService locationService;

	public Farm get(int id) {
		return repository.getById(id);
	}

	@Transactional
	public Farm save(Farm farm) {
		farm.setLocation(locationService.saveIfNotExist(farm.getLocation()));
		return repository.save(farm);
	}

	public boolean exist(Integer id) {
		return repository.existsById(id);
	}

	public static Farm newFarm(String address, int pricePerMonth, int square) {
		return new Farm(Utils.context.getBean("locationService",LocationService.class).newLocation(address), pricePerMonth, square);
	}

	public void delete(Farm farm) {
		repository.delete(farm);
	}

	public void delete(int id) {
		repository.deleteById(id);
	}

}
