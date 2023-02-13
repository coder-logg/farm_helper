package edu.itmo.isbd.repository;

import edu.itmo.isbd.entity.OrderForDrive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderForDriveRepository extends CrudRepository<OrderForDrive, Integer> {

}
