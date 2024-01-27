package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
	Car findCarsByCapacityAndNumberAndMark(Integer capacity, String number, String mark);
}
