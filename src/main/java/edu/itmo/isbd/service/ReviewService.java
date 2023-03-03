package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Review;
import edu.itmo.isbd.entity.Review;
import edu.itmo.isbd.exception.EntityNotFoundException;
import edu.itmo.isbd.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;

	public List<Review> getAllBySenderLogin(String login) {
		return reviewRepository.findAllBySenderLogin(login);
	}

	public List<Review> getAllByRecipientLogin(String login) {
		return reviewRepository.findAllByRecipientLogin(login);
	}

	public Optional<Review> getReview(int id){
		return reviewRepository.findById(id);
	}

	public Review getReviewOrThrow(int id){
		return reviewRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Review with id = " + id + " wasn't found"));
	}

	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}

	public List<Review> getAllReviews(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Review> pagedResult = reviewRepository.findAll(paging);
		return pagedResult.getContent();
	}
}
