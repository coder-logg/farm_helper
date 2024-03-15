package edu.itmo.isbd.dto;

import edu.itmo.isbd.model.DeliveryOrderProgressStage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CreationDeliveryOrderDto {
	private int id;
	private String farmerLogin;
	private Date creationDate = new Date();
	private Date closingDate;
	private Integer orderId;
	private int cost;
	private String toAddress;
	private String fromAddress;
}
