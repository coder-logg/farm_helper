package edu.itmo.isbd.repository;

import edu.itmo.isbd.entity.Admin;
import edu.itmo.isbd.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
	Admin findAdminByLogin(String login);

	@Query(nativeQuery = true, value = "select * from public.admin join _user u on u.id = admin.user_id order by random() limit 1")
	Admin findRandomAdmin();

//	Admin save(User User);

	boolean existsAdminByLogin(String login);
}
