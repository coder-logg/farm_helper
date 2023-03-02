package edu.itmo.isbd.repository;

import edu.itmo.isbd.entity.OrderForDrive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderForDriveRepository extends CrudRepository<OrderForDrive, Integer> {
	List<OrderForDrive> findOrderForDrivesByFarmer_Login(String login);
	List<OrderForDrive> findOrderForDrivesByDriver_Login(String login);
}
