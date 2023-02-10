package edu.itmo.isbd.repository;

import edu.itmo.isbd.entity.Farmer;
import edu.itmo.isbd.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends CrudRepository<Farmer, Integer> {
	Farmer findFarmerByLogin(String login);
	boolean existsFarmerByLogin(String login);
//	Farmer save(User user);
}
