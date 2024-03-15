package edu.itmo.isbd.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.itmo.isbd.model.Role;
import edu.itmo.isbd.model.User;
import edu.itmo.isbd.service.UserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "login")
public class UserDto {
	private int id;
	private String login;
	private String phone;
	private String mail;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<ReviewDto> myReviews;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<ReviewDto> reviewsForMe;
	private Role role;

	public User mapToUser() {
		return new User(id, login, phone, mail, password,
				myReviews.stream().map(ReviewDto::mapToReview).collect(Collectors.toSet()),
				reviewsForMe.stream().map(ReviewDto::mapToReview).collect(Collectors.toList()),
				role);
	}

	public User mapToUser(UserService userService) {
		return new User(id, login, phone, mail, password,
				myReviews.stream().map(a -> a.mapToReview(userService)).collect(Collectors.toSet()),
				reviewsForMe.stream().map(a -> a.mapToReview(userService)).collect(Collectors.toList()),
				role);
	}

	public UserDto(User user) {
		BeanUtils.copyProperties(user, this);
		if (user.getMyReviews() != null)
			this.myReviews = user.getMyReviews().stream().map(ReviewDto::new).collect(Collectors.toSet());
		if (user.getReviewsForMe() != null)
			this.reviewsForMe = user.getReviewsForMe().stream().map(ReviewDto::new).collect(Collectors.toList());
	}
}
