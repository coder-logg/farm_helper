package edu.itmo.isbd.repository;

import edu.itmo.isbd.entity.Farmer;
import edu.itmo.isbd.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmerRepository extends CrudRepository<Farmer, Integer> {
	Optional<Farmer> findFarmerByLogin(String login);
	boolean existsFarmerByLogin(String login);
//	Farmer save(User user);
}
