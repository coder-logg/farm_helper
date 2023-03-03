package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Review;
import edu.itmo.isbd.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
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
	@PreAuthorize("#review.senderLogin == authentication.name")
	public ResponseEntity<?> postReview(@RequestBody @P("review") Review review) throws URISyntaxException {
		return ResponseEntity.created(new URI("/reviews/" + reviewService.saveReview(review).getId())).build();
	}
}
