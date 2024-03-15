package isbd.repository;

import edu.itmo.isbd.FarmHelper;
import edu.itmo.isbd.model.Farm;
import edu.itmo.isbd.model.Farmer;
import edu.itmo.isbd.model.User;
import edu.itmo.isbd.repository.UserRepository;
import edu.itmo.isbd.service.LocationService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FarmHelper.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LocationService locationService;

	@Test
	public void contextTest(){
		Assertions.assertNotNull(userRepository);
	}

	@Test
	public void findUserByLoginTest(){
		User user = userRepository.findUserByLogin("aidar");
		Assertions.assertNotNull(user);
		System.out.println(user);
	}

	@Test
	public void findRandomUserTest(){
		User user = userRepository.findRandomUser();
		Assertions.assertNotNull(user);
		System.out.println(user);
	}

}
