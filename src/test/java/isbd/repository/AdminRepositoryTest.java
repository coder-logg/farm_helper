package isbd.repository;

import edu.itmo.isbd.FarmHelper;
import edu.itmo.isbd.model.Admin;
import edu.itmo.isbd.model.User;
import edu.itmo.isbd.repository.AdminRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FarmHelper.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class AdminRepositoryTest {
	@Autowired
	private AdminRepository adminRepository;
	private static final String TEST_LOGIN = "test-admin";

	@Test
	public void shouldAdminSave() {
		Admin admin = new Admin(new User(TEST_LOGIN, "89188848716", "testadm@mail.com", "qwerty"));
		Admin adminFromDb = adminRepository.save(admin);
		admin.setId(adminFromDb.getId());
		Assertions.assertTrue(adminRepository.existsAdminByLogin(admin.getLogin()));
		Assertions.assertEquals(admin, adminFromDb);
	}

	@Test
	public void shouldAdminDelete() {
		adminRepository.deleteByLogin(TEST_LOGIN);
		Assertions.assertFalse(adminRepository.existsAdminByLogin(TEST_LOGIN));
	}


}
