package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
	Optional<Driver> findDriverByLogin(String login);
	boolean existsDriverByLogin(String login);
//	Driver save(User user);
}
