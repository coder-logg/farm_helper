package edu.itmo.isbd.model;

public enum DeliveryOrderProgressStage {
	CREATED,
	WAITING_ACCEPTANCE_BY_DRIVER,
	DRIVER_REJECTED,
	DRIVER_ACCEPTED,
	ON_DELIVERY,
	DELIVERED,
	ARBITRATION
}
