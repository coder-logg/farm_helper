package edu.itmo.isbd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.*;

import javax.persistence.*;

import java.io.IOException;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class OrderDetail extends JsonSerializer<OrderDetail> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "plant_id", referencedColumnName = "id")
	private Plant plant;

	private int amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delivery_date")
	private Date deliveryDate;

	@Column(name = "delivery_address")
	private String deliveryAddress;

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(mappedBy = "detail")
	private Order order;

	@Override
	public void serialize(OrderDetail orderDetail, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeNumber(id);
		jsonGenerator.writeObject(plant);
		jsonGenerator.writeNumber(amount);
		jsonGenerator.writeObject(deliveryDate);
		jsonGenerator.writeString(deliveryAddress);
		if (order != null)
			jsonGenerator.writeNumber(order.getId());
		else
			jsonGenerator.writeNullField("order");
	}

}
