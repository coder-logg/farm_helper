package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.Customer;
import edu.itmo.isbd.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//	@Query(nativeQuery = true, )
//	Page<Customer> findAllByFarmerLogin(Pageable pageable);

	Set<Customer> findDistinctByOrdersIn(List<Order> orders);
}
