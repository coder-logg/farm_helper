package edu.itmo.isbd.dto;

import edu.itmo.isbd.model.Review;
import edu.itmo.isbd.model.User;
import edu.itmo.isbd.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDto {
	private int id;
	private String senderLogin;
	private String recipientLogin;
	private String message;
	private int rate;

	public ReviewDto(Review review) {
		this.id = review.getId();
		this.senderLogin = review.getSenderLogin();
		this.recipientLogin = review.getRecipientLogin();
		this.message = review.getMessage();
		this.rate = review.getRate();
	}

	public Review mapToReview(UserService userService) {
		return new Review(id, (User) userService.loadUserByUsername(senderLogin),
				(User) userService.loadUserByUsername(recipientLogin), message, rate);
	}

	public Review mapToReview() {
		return new Review(id, new User(senderLogin), new User(recipientLogin), message, rate);
	}
}
