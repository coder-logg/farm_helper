CREATE OR REPLACE FUNCTION recalculate_order_cost(order_id INT) RETURNS void AS $$
DECLARE
	_order_detail record;
	amount_plant INT;
	cost_plant INT;
	cost_equipment_sum INT;
BEGIN
	_order_detail = (SELECT * FROM order_detail WHERE id = order_id);
	amount_plant = _order_detail.amount;
	cost_plant = (SELECT cost FROM plant WHERE id = _order_detail.plant_id);
	cost_equipment_sum = (SELECT sum(cost) FROM Equipment
						  WHERE id = (
							  SELECT equipment_id FROM Required_equipment
							  WHERE plant_id = _order_detail.plant_id));
	UPDATE _order SET cost = amount_plant * cost_plant + cost_equipment_sum where id = order_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION update_order_cost() RETURNS TRIGGER AS $$
declare
	row record;
	_cost int;
begin
	if tg_table_name = 'plant' then
		for row in select * from
				_order join (select id from order_detail where plant_id = old.id) as t on order_detail_id = t.id loop
			UPDATE _order set cost = cost - old.cost + new.cost;
		end loop;
		return NEW;
	elsif tg_table_name = 'equipment' then
		for row in select * from
				order_detail join (select plant_id p from required_equipment where equipment_id = old.id) as rep on order_detail.plant_id = p loop
			UPDATE _order SET cost = cost - old.cost + new.cost where order_detail_id = row.id;
		end loop;
		return new;
	elsif tg_table_name = 'required_equipment' then
		_cost = (select cost from equipment where id = old.plant_id);
		for row in select * from order_detail where plant_id = old.plant_id loop
			UPDATE _order SET cost = cost - old.amount * _cost + new.amount * _cost where order_detail_id = row.id;
		end loop;
		return new;
	elsif tg_table_name = 'order_detail' then
		_cost = (select cost from plant where id = old.plant_id);
		UPDATE _order SET cost = cost - old.amount * _cost + new.amount * _cost where order_detail_id = old.id;
		return new;
	end if;
	return null;
end;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_order_cost
AFTER UPDATE ON plant
FOR EACH ROW
WHEN (old.cost != new.cost)
execute function update_order_cost();

CREATE TRIGGER upd_order_cost
	AFTER UPDATE ON equipment
	FOR EACH ROW
WHEN (old.cost != new.cost)
execute function update_order_cost();

CREATE TRIGGER upd_order_cost_req_eq
	AFTER UPDATE ON required_equipment
	FOR EACH ROW
	WHEN (old.amount != new.amount)
execute function update_order_cost();

CREATE TRIGGER upd_order_cost_ord_det
	AFTER UPDATE ON order_detail
	FOR EACH ROW
	WHEN (old.amount != new.amount)
execute function update_order_cost();

CREATE OR REPLACE FUNCTION update_order_date_if_status_set_finished() RETURNS TRIGGER AS $$
declare
	cost int;
	order_row record;
	order_for_drive_row record;
begin
	if new.progress == 'finished' then
		order_row = (select * from _order where status_id = new.id);
		UPDATE order_detail SET delivery_date = current_timestamp where id = order_row.order_detail_id;
		order_for_drive_row = (SELECT * from order_for_drive where farmer_id = order_row.farmer_id);
		cost = order_for_drive_row.cost;
		UPDATE farmer SET balance = balance - cost where id = order_row.farmer_id;
		UPDATE driver SET balance = balance + cost where id = order_for_drive_row.driver_id;
	end if;
	return new;
end;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_order_date
AFTER UPDATE ON status
FOR EACH ROW
when (old.progress != 'finished' and new.progress = 'finished')
EXECUTE FUNCTION update_order_date_if_status_set_finished();

--- todo remake this function
CREATE OR REPLACE FUNCTION change_order_location() RETURNS TRIGGER AS $$
declare
	row record;
begin
	if new.location_id != old.location_id then
		UPDATE status SET location = (select country_name from location where id = new.location_id)
		where id = row.status_id and (progress = 'cultivation' or progress = 'started');
	end if;
	return new;
end;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_order_location
AFTER UPDATE ON farm
FOR EACH ROW
WHEN (new.location_id != old.location_id)
EXECUTE FUNCTION change_order_location();

CREATE OR REPLACE FUNCTION change_rent_cost() RETURNS TRIGGER AS $$
begin
	if new.square != old.square then
		UPDATE location SET price_per_month = price_per_month * new.square / old.square where id = new.id;
	end if;
	return new;
end;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_rent_cost
AFTER UPDATE ON location
FOR EACH ROW
WHEN (new.square != old.square)
EXECUTE FUNCTION change_rent_cost();

CREATE OR REPLACE FUNCTION update_order_cost_if_required_equipment_added() RETURNS TRIGGER AS $$
declare
	row record;
begin
	for row in select _order.id from
			_order join (select id from order_detail where plant_id = new.id) as t on order_detail_id = t.id loop
		UPDATE _order SET cost = cost + (select cost from equipment where cost = new.cost) WHERE id = row.id;
	end loop;
	return new;
end;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_order_cost_rq
AFTER INSERT ON required_equipment
FOR EACH ROW
EXECUTE FUNCTION update_order_cost_if_required_equipment_added();