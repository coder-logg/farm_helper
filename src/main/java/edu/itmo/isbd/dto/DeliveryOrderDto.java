package edu.itmo.isbd.dto;

import edu.itmo.isbd.model.DeliveryOrder;
import edu.itmo.isbd.model.DeliveryOrderProgressStage;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@NoArgsConstructor
public class DeliveryOrderDto {
	private int id;
	private String driverLogin;
	private String farmerLogin;
	private Date creationDate = new Date();
	private Date closingDate;
	private Integer orderId;
	private DeliveryOrderProgressStage status;
	private int cost;
	private String toAddress;
	private String fromAddress;

	public DeliveryOrderDto(DeliveryOrder deliveryOrder) {
		BeanUtils.copyProperties(deliveryOrder, this);
		this.driverLogin = deliveryOrder.getDriver().getLogin();
		this.farmerLogin = deliveryOrder.getFarmer().getLogin();
		this.orderId = deliveryOrder.getId();
	}
}
