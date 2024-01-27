package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.Arbitration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArbitrationRepository extends JpaRepository<Arbitration, Integer> {
	List<Arbitration> findAllByFarmerLogin(String login);
	List<Arbitration> findAllByAdminLogin(String login);
}
