package isbd.repository;

import edu.itmo.isbd.FarmHelper;
import edu.itmo.isbd.entity.Admin;
import edu.itmo.isbd.repository.AdminRepository;
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
public class AdminRepositoryTest {
	@Autowired
	private AdminRepository adminRepository;

	@Test
	public void findAdminTest(){
		Admin admin = adminRepository.findRandomAdmin();
		Assertions.assertNotNull(admin);
		System.out.println(admin);
	}
}
