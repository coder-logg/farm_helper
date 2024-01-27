package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	List<Review> findReviewsBySender_Login(String login);

	List<Review> findReviewsByRecipient_Login(String login);

}
