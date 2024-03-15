package edu.itmo.isbd.dto;

import edu.itmo.isbd.model.Equipment;
import edu.itmo.isbd.model.Plant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantDto {
	private int id;
	private String name;
	private int cost;
	private int timeForCompleted;

	public PlantDto(Plant plant) {
		BeanUtils.copyProperties(plant, this);
	}

	public Plant mapToPlant() {
		return new Plant(id, name, cost, timeForCompleted);
	}
}
