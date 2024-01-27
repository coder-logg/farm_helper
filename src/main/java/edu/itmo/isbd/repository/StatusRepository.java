package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//@Repository
public interface StatusRepository extends JpaRepository<OrderStatus, Integer> {
	@Modifying
	@Query(nativeQuery = true, value = "insert into status (location, progress) values (:status.location, :status.progress::progress_stages)")
	void saving(@Param("status") OrderStatus status);
}
