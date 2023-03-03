package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Review;
import edu.itmo.isbd.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

	@GetMapping("/{id}")
	public ResponseEntity<Review> getReview(@PathVariable int id) {
		return ResponseEntity.ok(reviewService.getReviewOrThrow(id));
	}

	@PostMapping
	public ResponseEntity<?> postReview(@RequestBody Review review) throws URISyntaxException {
		return ResponseEntity.created(new URI("/reviews/" + reviewService.saveReview(review).getId())).build();
	}
}
