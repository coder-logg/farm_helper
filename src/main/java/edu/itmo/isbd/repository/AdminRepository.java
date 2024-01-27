package edu.itmo.isbd.repository;

import edu.itmo.isbd.model.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
	Optional<Admin> findAdminByLogin(String login);

	@Query(nativeQuery = true, value = "select id, login, phone, mail, password from admin a join  _user u on u.id = a.user_id order by random() limit 1")
	Admin findRandomAdmin();

//	Admin save(User User);

	boolean existsAdminByLogin(String login);
}
