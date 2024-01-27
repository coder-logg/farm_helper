package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.DeliveryOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Integer> {
	List<DeliveryOrder> findDeliveryOrdersByFarmer_Login(String login);
	List<DeliveryOrder> findDeliveryOrdersByDriver_Login(String login);
}
