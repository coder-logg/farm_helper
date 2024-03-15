package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.Customer;
import edu.itmo.isbd.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	boolean existsOrderById(int id);
	List<Order> findOrdersByFarmer_Login(String login);
	Page<Order> findAll(Pageable pageable);

	@Query("select new edu.itmo.isbd.model.Customer(c.id, c.name, c.phone, c.mail) from Customer c join Order o on o.customer.id = c.id where o.farmer.login = ?1")
	List<Customer> findCustomerByFarmerLogin(String farmerLogin);
}
