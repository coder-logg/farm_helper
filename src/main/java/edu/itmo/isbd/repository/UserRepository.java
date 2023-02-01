package edu.itmo.isbd.repository;

import edu.itmo.isbd.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	User findByLoginIs(String login);
}
