package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.User;
import edu.itmo.isbd.exception.UserAlreadyRegisteredException;
import edu.itmo.isbd.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
	private UserRepository userRepository;

	public User registration(User user) throws UserAlreadyRegisteredException {
		if (userRepository.findByLoginIs(user.getLogin()) != null)
			throw new UserAlreadyRegisteredException("User with login '" + user.getLogin() +"' already exists: ");
		return userRepository.save(user);
	}

}
