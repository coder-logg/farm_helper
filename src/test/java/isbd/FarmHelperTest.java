package isbd;

import edu.itmo.isbd.FarmHelper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(
		locations = "classpath:application.yml")
@SpringBootTest(classes = FarmHelper.class)
public class FarmHelperTest {

}
