package edu.itmo.isbd.dto;

import edu.itmo.isbd.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class OrderDto {
	private Integer id;
	private String farmerLogin;
	private Customer customer;
	private OrderProgressStage status;
	private String plantName;
	private int amount;
	private int cost;

	public OrderDto(Order order) {
		BeanUtils.copyProperties(order, this);
		plantName = order.getPlant().getName();
		farmerLogin = order.getFarmer().getLogin();
	}

}
