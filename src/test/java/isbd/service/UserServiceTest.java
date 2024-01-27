package isbd.service;

import edu.itmo.isbd.FarmHelper;
import edu.itmo.isbd.model.User;
import edu.itmo.isbd.repository.UserRepository;
import edu.itmo.isbd.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(
		locations = "classpath:application.properties")
@SpringBootTest(classes = FarmHelper.class)
public class UserServiceTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	public void getRoleTest(){
		User user = userRepository.findUserByLogin("aidar");
		userService.getUserRole(user);
		Assertions.assertNotNull(user);
		System.out.println(user);
	}

}
