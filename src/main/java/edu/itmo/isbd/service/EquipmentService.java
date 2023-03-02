package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Equipment;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {
	@Autowired
	private EquipmentRepository equipmentRepository;

	public Optional<Equipment> getEquipment(int id){
		return equipmentRepository.findById(id);
	}

	public Equipment getEquipmentOrThrow(int id){
		return equipmentRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Equipment with id = " + id + " wasn't found"));
	}

	public Equipment saveEquipment(Equipment equipment) {
		return equipmentRepository.save(equipment);
	}

	public List<Equipment> getAllEquipments(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Equipment> pagedResult = equipmentRepository.findAll(paging);
		return pagedResult.getContent();
	}
}
