package edu.itmo.isbd.repository;

import edu.itmo.isbd.entity.Driver;
import edu.itmo.isbd.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Integer> {
	Optional<Driver> findDriverByLogin(String login);
	boolean existsDriverByLogin(String login);
//	Driver save(User user);
}
