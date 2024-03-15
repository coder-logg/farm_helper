package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
	boolean existsByAddress(String address);
	Location getById(Integer id);
	Location getByAddress(String address);
}
