package edu.itmo.isbd.repository;

import edu.itmo.isbd.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
