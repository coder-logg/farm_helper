package isbd.service;


import edu.itmo.isbd.FarmHelper;
import edu.itmo.isbd.service.OrderService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(
		locations = "classpath:application.properties")
@SpringBootTest(classes = FarmHelper.class)
public class OrderRepositoryTest {
	@Autowired
	private OrderService orderService;

//	@Test
//	public void statusTest(){
//		orderService.saveStatus(new OrderStatus("Красноярск", OrderStatus.ProgressStages.CULTIVATION));
//	}
}
