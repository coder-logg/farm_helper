package isbd.repository;

import edu.itmo.isbd.FarmHelper;
import edu.itmo.isbd.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(
		locations = "classpath:application.properties")
@SpringBootTest(classes = FarmHelper.class)
public class UserRepositoryTest{

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextTest(){
		Assertions.assertNotNull(userRepository);
	}


}
