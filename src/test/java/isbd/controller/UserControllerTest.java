package isbd.controller;

import edu.itmo.isbd.controller.PlantController;
import edu.itmo.isbd.controller.UserController;
import edu.itmo.isbd.dto.DriverDto;
import edu.itmo.isbd.model.Driver;
import edu.itmo.isbd.model.User;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RunWith(SpringRunner.class)
@WebMvcTest(PlantController.class)
public class UserControllerTest {
	private Driver driver = new Driver(new User("test-driver", "89127848716", "testd@mail.com", "131264"));

	public void shouldRegisterNewDriver(HttpServletRequest request, HttpServletResponse response, UserController controller) throws ServletException, IOException {
		controller.registration(request, response, new DriverDto(driver));
	}
}
