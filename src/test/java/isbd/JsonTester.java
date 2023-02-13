package isbd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.itmo.isbd.entity.Plant;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.util.Arrays;


public class JsonTester {
	
	@Test
	public void testPlantJsonSerialization() throws JsonProcessingException {
		Plant plant = new Plant();
		plant.setCost(100);
		plant.setId(23);
		plant.setRequiredEquipmentsIds(Arrays.asList(12, 8, 124, 521, 432, 2, 5));
		plant.setName("Яблоко");
		plant.setTimeForCompleted(10000);
		String json = new ObjectMapper().writeValueAsString(plant);
		System.out.println(json);
	}

	@Test
	public void testPlantJsonDeserialization() throws JsonProcessingException {
		Plant plant = new Plant();
		plant.setCost(100);
		plant.setId(23);
		plant.setRequiredEquipmentsIds(Arrays.asList(12, 8, 124, 521, 432, 2, 5));
		plant.setName("Яблоко");
		plant.setTimeForCompleted(10000);
		String json = new ObjectMapper().writeValueAsString(plant);
		System.out.println(json);
		System.out.println(new ObjectMapper().readValue(json, Plant.class));
		System.out.println(plant.getRequiredEquipments());
	}
}
