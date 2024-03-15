package isbd.service;

import edu.itmo.isbd.FarmHelper;
import edu.itmo.isbd.model.Farm;
import edu.itmo.isbd.service.FarmService;
import edu.itmo.isbd.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@TestPropertySource(
		locations = "classpath:application.yml")
@SpringBootTest(classes = FarmHelper.class)
@Slf4j
public class FarmServiceTest {
	@Autowired
	private FarmService farmService;
	@Autowired
	private LocationService locationService;

	@Test
	@Rollback
	@Transactional
	public void shouldSaveLocationTest() {
		Farm farm = farmService.save(new Farm(locationService.newLocation("Москва красная площадь"), 100, 1000));
		Assertions.assertNotNull(farm.getLocation());
		Farm farm2 = farmService.save(new Farm(locationService.newLocation("Москва красная площадь"), 300, 3000));
		Assertions.assertNotNull(farm2.getLocation());
		Assertions.assertEquals(farm.getLocation(), farm2.getLocation());
	}

	@Test
	@Rollback
	@Transactional
	public void shouldDeleteTest() {
		Farm farm = farmService.save(new Farm(locationService.newLocation("Москва красная площадь"), 100, 1000));
		farmService.delete(farm);
		Assertions.assertFalse(farmService.exist(farm.getId()));
	}
}
