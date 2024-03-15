package edu.itmo.isbd.controller;

import edu.itmo.isbd.Utils;
import edu.itmo.isbd.dto.CreationDeliveryOrderDto;
import edu.itmo.isbd.dto.DeliveryOrderDto;
import edu.itmo.isbd.model.DeliveryOrder;
import edu.itmo.isbd.model.Role;
import edu.itmo.isbd.model.User;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.service.DeliveryOrderService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/delivery-orders")
public class DeliveryOrderController {
	@Autowired
	private DeliveryOrderService deliveryOrderService;

	@GetMapping("/{id}")
	public ResponseEntity<DeliveryOrderDto> getDeliveryOrder(@PathVariable Integer id, Authentication auth) {
		DeliveryOrder deliveryOrder = deliveryOrderService.get(id);
		User user = (User) auth.getPrincipal();
		if (user.getLogin().equals(deliveryOrder.getDriver().getLogin())
				|| user.getLogin().equals(deliveryOrder.getFarmer().getLogin())
				|| user.getRole().equals(Role.ADMIN))
			return ResponseEntity.ok(new DeliveryOrderDto(deliveryOrder));
		else throw new HttpException("You don't have access for requested data.", HttpStatus.FORBIDDEN);
	}

	@PostMapping
	@PreAuthorize("(hasRole('FARMER') and authentication.name == #deliveryOrder.farmerLogin) or hasRole('ADMIN')")
	public ResponseEntity<DeliveryOrderDto> addDeliveryOrder(@RequestBody @P("deliveryOrder") CreationDeliveryOrderDto creationDeliveryOrderDto) throws URISyntaxException {
		if (!ObjectUtils.allNotNull(creationDeliveryOrderDto.getFarmerLogin(), creationDeliveryOrderDto.getOrderId()))
			throw new HttpException("Incomplete data was given. Required fields: farmerLogin, orderId.", HttpStatus.UNPROCESSABLE_ENTITY);
		return ResponseEntity
				.created(new URI("/delivery-orders/" + creationDeliveryOrderDto.getId()))
				.body(new DeliveryOrderDto(deliveryOrderService.create(creationDeliveryOrderDto)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDeliveryOrder(@PathVariable Integer id, Authentication auth){
		DeliveryOrder deliveryOrderFromDb = deliveryOrderService.get(id);
		List<String> roles = Utils.getRoles(auth);
		if (!(roles.contains("ROLE_ADMIN")
				|| (auth.getName().equals(deliveryOrderFromDb.getFarmer().getLogin()) && roles.contains("ROLE_FARMER"))))
			throw new HttpException("You don't have access to delete order for drive with id = " + id, HttpStatus.FORBIDDEN);
		deliveryOrderService.removeOrThrow(id);
		return ResponseEntity.ok("Entity deleted");
	}
}
