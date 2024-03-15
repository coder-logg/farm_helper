package edu.itmo.isbd.service;

import edu.itmo.isbd.model.Plant;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService {
	@Autowired
	private PlantRepository plantRepository;

	public Optional<Plant> getPlant(int id){
		return plantRepository.findPlantById(id);
	}

	public Plant getPlantOrThrow(int id){
		return plantRepository
				.findPlantById(id)
				.orElseThrow(() -> new EntityNotFoundException("Plant with id = " + id + " wasn't found"));
	}

	public Plant getPlantOrThrow(String name){
		return plantRepository
				.findPlantByName(name)
				.orElseThrow(() -> new EntityNotFoundException("Plant with name = " + name + " wasn't found"));
	}

	public Plant savePlant(Plant plant) {
		return plantRepository.save(plant);
	}

	public List<Plant> getAllPlants(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Plant> pagedResult = plantRepository.findAll(paging);
		return pagedResult.getContent();
	}
}
