package edu.itmo.isbd.repository;

import edu.itmo.isbd.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	List<Review> findAllBySenderLogin(String login);

	List<Review> findAllByRecipientLogin(String login);

}
