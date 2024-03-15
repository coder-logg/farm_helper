package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(nativeQuery = true, value = "select count(*) from _user")
	Long countAll();
	User findUserByLogin(String login);
	boolean existsUserByLogin(String login);

	void deleteByLogin(String login);
	@Query(nativeQuery = true, value = "select *, 0 as clazz_ from public._user order by random() limit 1")
	User findRandomUser();
}
