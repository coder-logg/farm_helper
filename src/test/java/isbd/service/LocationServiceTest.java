package isbd.service;

import edu.itmo.isbd.FarmHelper;
import edu.itmo.isbd.model.Location;
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
@TestPropertySource(
		locations = "classpath:application.yml")
@SpringBootTest(classes = FarmHelper.class)
public class LocationServiceTest {
	@Autowired
	private LocationService locationService;

	@Test
	public void shouldReturnLocation(){
		Assertions.assertNotNull(locationService.newLocation("Москва красная площадь"));
	}
}
