package edu.itmo.isbd.repository;

import edu.itmo.isbd.entity.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {
	Optional<Plant> findPlantByName(String name);
	Optional<Plant> findPlantById(Integer id);
	Page<Plant> findAll(Pageable pageable);
}
