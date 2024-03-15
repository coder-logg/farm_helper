package isbd.repository;


import edu.itmo.isbd.FarmHelper;
import edu.itmo.isbd.model.*;
import edu.itmo.isbd.repository.*;
import edu.itmo.isbd.service.LocationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.beans.BeanUtils.copyProperties;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FarmHelper.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class OrderRepositoryTest {
	@Autowired
	private OrderRepository repository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private static LocationService locationService;
	@Autowired
	private PlantRepository plantRepository;
	private static final String TEST_LOGIN = "test-farmer";
	private static final Farmer farmer = new Farmer(
			new User(TEST_LOGIN, "89188848716", "testadm@mail.com", "qwerty"),
			new Farm(locationService.newLocation("Москва красная площадь"), 100, 1000));
	public static final Customer customer = new Customer("SPAR", "89925556677", "testc@mail.com");
	public static final Plant plant = new Plant("raspberries", 100, 10_000);
	public static Order order = new Order(farmer, customer, OrderProgressStage.STARTED, plant, 5, 6_700_000);

	@Before
	public void prepare(){
		userRepository.save(farmer);
		plantRepository.save(plant);
		customerRepository.save(customer);
	}

	@Test
//	@org.junit.jupiter.api.Order(1)
	public void shouldSaveOrder(){
		System.out.println("aaaa " + order);
		order = repository.save(order);
		Assertions.assertTrue(repository.existsOrderById(order.getId()));
	}

	@Test
//	@org.junit.jupiter.api.Order(2)
	public void shouldDeleteOrder(){
		Assertions.assertTrue(repository.existsOrderById(order.getId()));
		repository.deleteById(order.getId());
		Assertions.assertFalse(repository.existsOrderById(order.getId()));
	}

	// todo check changing variables and persisting its in Order

	@Test
//	@org.junit.jupiter.api.Order(3)
	public void shouldChange(){
		Plant plant1 = new Plant();
		copyProperties(plant, plant1);
		plant1.setCost(1000);
		order.setPlant(plant1);
		Order orderFromDb= repository.save(order);
		Assertions.assertEquals(orderFromDb.getCost(), plant1.getCost());
	}


}
