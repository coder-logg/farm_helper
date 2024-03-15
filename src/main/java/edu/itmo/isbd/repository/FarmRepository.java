package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Integer> {
	void deleteById(int id);
}