package isbd.controller;

import edu.itmo.isbd.controller.PlantController;
import edu.itmo.isbd.model.Plant;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(PlantController.class)
public class PlantControllerTest {
	private static Plant plant = new Plant();

	void shouldSavePlant(){

	}
}
