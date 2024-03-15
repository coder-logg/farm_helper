package isbd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.itmo.isbd.model.Equipment;
import edu.itmo.isbd.model.Plant;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JsonTester {
	
	@Test
	public void testPlantJsonSerialization() throws JsonProcessingException {
		Plant plant = new Plant();
		plant.setCost(100);
		plant.setId(23);
		plant.setRequiredEquipment(Stream.of(12, 8, 124, 521, 432, 2, 5).map(Equipment::new).collect(Collectors.toList()));
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
		plant.setRequiredEquipment(Stream.of(12, 8, 124, 521, 432, 2, 5).map(Equipment::new).collect(Collectors.toList()));
		plant.setName("Яблоко");
		plant.setTimeForCompleted(10000);
		String json = new ObjectMapper().writeValueAsString(plant);
		System.out.println(json);
		System.out.println(new ObjectMapper().readValue(json, Plant.class));
		System.out.println(plant.getRequiredEquipment());
	}
}
