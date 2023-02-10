package edu.itmo.isbd.repository;

import edu.itmo.isbd.entity.Driver;
import edu.itmo.isbd.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Integer> {
	Driver findDriverByLogin(String login);
	boolean existsDriverByLogin(String login);
//	Driver save(User user);
}
