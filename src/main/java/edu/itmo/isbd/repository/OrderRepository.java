package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	boolean existsOrderById(int id);
	List<Order> findOrdersByFarmer_Login(String login);
	Page<Order> findAll(Pageable pageable);
}
