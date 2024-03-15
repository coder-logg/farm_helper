package isbd.service;

import edu.itmo.isbd.FarmHelper;
import edu.itmo.isbd.model.Admin;
import edu.itmo.isbd.model.Farmer;
import edu.itmo.isbd.model.Role;
import edu.itmo.isbd.model.User;
import edu.itmo.isbd.repository.UserRepository;
import edu.itmo.isbd.service.FarmService;
import edu.itmo.isbd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.slf4j.SLF4JLogger;
import org.apache.logging.slf4j.SLF4JProvider;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@TestPropertySource(
		locations = "classpath:application.yml")
@SpringBootTest(classes = FarmHelper.class)
@ContextConfiguration(classes = FarmHelper.class)
@Slf4j
public class UserServiceTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	@Rollback
	@Transactional
	public void shouldRegisterAdmin(){
		Admin admin = new Admin(new User("test-admin", "89188848716", "testadm@mail.com", "qwerty"));
		userService.registration(admin);
		Assertions.assertEquals(userService.getUserRole(admin), Role.ADMIN);
	}

	@Test
	@Rollback
	@Transactional
	public void shouldRegisterFarmer(){
		Farmer farmer = Farmer.builder()
				.login("test-admin")
				.phone("89188848716")
				.mail("testadm@mail.com")
				.password("qwerty")
				.farm(FarmService.newFarm("дмитров деревня целеево 61", 100000, 1000))
				.build();
		User fromDb = userService.registration(farmer);
		farmer.setId(fromDb.getId());
		farmer.setPassword(fromDb.getPassword());
		Assertions.assertEquals(userService.getUserRole(farmer), Role.FARMER);
		Assertions.assertEquals(fromDb, farmer);

	}

}
