-- CREATE TYPE progress_stages AS ENUM ('STARTED', 'CULTIVATION','DELIVERY','FINISHED','ARBITRATION');

CREATE TABLE IF NOT EXISTS _user(
	id serial primary key,
	login varchar(10) NOT NULL UNIQUE,
	phone varchar(12) NOT NULL UNIQUE,
	mail varchar(50) NOT NULL UNIQUE,
	password varchar(100) NOT NULL
);

CREATE INDEX user_login ON _user(login);

CREATE TABLE IF NOT EXISTS customer(
	id serial PRIMARY KEY,
	name varchar(50),
	phone varchar(12) NOT NULL UNIQUE,
	mail varchar(50) UNIQUE
);

CREATE TABLE IF NOT EXISTS car (
	id serial primary key,
	mark varchar(15) NOT NULL,
	license_number varchar(6) NOT NULL UNIQUE,
	capacity int
);

CREATE TABLE IF NOT EXISTS status(
	id serial primary key,
	location varchar(30) NOT NULL,
	progress varchar(14) default 'STARTED' check
	    ( progress = 'STARTED'
	          or progress = 'CULTIVATION'
	          or progress = 'DELIVERY'
	          or progress = 'FINISHED'
	          or progress = 'ARBITRATION')
);

CREATE TABLE IF NOT EXISTS equipment(
	id serial primary key,
	name varchar(40) UNIQUE NOT NULL,
	cost int NOT NULL,
	location point
);

CREATE INDEX equipment_cost ON equipment(cost);

CREATE TABLE IF NOT EXISTS review(
	id serial PRIMARY KEY,
	sender_id int references _user(id) NOT NULL,
	recipient_id int references _user(id) NOT NULL,
	message varchar(255) NOT NULL check (char_length(message) > 0),
	rate int CHECK(rate>=0 AND rate<=5),
	UNIQUE (sender_id, recipient_id), CHECK (sender_id != review.recipient_id)
);

CREATE INDEX idx_rate on review(rate);

CREATE TABLE IF NOT EXISTS admin(
	user_id serial primary key references _user(id)
);

CREATE TABLE IF NOT EXISTS driver(
	user_id serial primary key references _user(id),
	car_id int references car(id),
	balance int
);

CREATE TABLE IF NOT EXISTS plant(
	id serial primary key,
	name varchar(30) NOT NULL UNIQUE,
	cost int NOT NULL,
	time_for_completed int NOT NULL
);

CREATE INDEX plant_cost ON plant(cost);

CREATE TABLE IF NOT EXISTS order_detail(
	id serial primary key,
	plant_id int references plant(id) NOT NULL,
	amount int check(amount > 0) NOT NULL,
	delivery_date timestamp NOT NULL,
	delivery_address varchar(30) NOT NULL
);

CREATE INDEX delivery_date ON order_detail(delivery_date);

CREATE TABLE IF NOT EXISTS required_equipment(
	id serial primary key,
	plant_id int references plant(id) NOT NULL,
	equipment_id int references equipment(id) NOT NULL,
	amount int default 1 CHECK (amount > 0),
	UNIQUE (plant_id, equipment_id)
);

-- CREATE TABLE Country(
-- 	name varchar(30) PRIMARY KEY,
-- 	soil_type varchar(30) NOT NULL,
-- 	sunlight_amount int,
-- 	CONSTRAINT CHK_Country CHECK(sunlight_amount>0 AND (soil_type='почва' OR soil_type='чернозем' OR soil_type='глина'))
-- );

-- CREATE INDEX idx_sunlight_amount on Country(sunlight_amount);

CREATE TABLE location(
	id serial PRIMARY KEY,
	name text not null unique,
	coordinates point not null unique,
	sunlight_amount int
);

CREATE INDEX idx_price_per_month on location(name);

CREATE TABLE IF NOT EXISTS farm(
	id serial PRIMARY KEY,
	location_id int NOT NULL,
    price_per_month int NOT NULL,
    square int CHECK(square>0) NOT NULL,
	FOREIGN KEY(location_id)
	REFERENCES location(id)
);

CREATE TABLE IF NOT EXISTS farmer(
	user_id serial PRIMARY KEY references _user(id),
	balance int default 0,
	farm_id int,
	FOREIGN KEY(farm_id)
	REFERENCES farm(id)
);

CREATE TABLE IF NOT EXISTS _order(
	id serial primary key,
	farmer_id int references farmer(user_id) NOT NULL,
	customer_id int references customer(id) NOT NULL,
	order_detail_id int references order_detail(id) NOT NULL UNIQUE,
	status_id int references status(id) NOT NULL UNIQUE,
	cost int check(cost >= 0) NOT NULL
);

CREATE INDEX order_cost ON _order(cost);

CREATE TABLE delivery_order(
	id serial PRIMARY KEY,
	farmer_id int NOT NULL,
	driver_id int default NULL,
	order_id int references _order(id) NOT NULL,
	creation_date timestamp NOT NULL default current_timestamp,
	closing_date timestamp default NULL,
	cost int default 0 CHECK(cost >= 0) NOT NULL,
	FOREIGN KEY(driver_id)
	REFERENCES driver(user_id),
	FOREIGN KEY(farmer_id)
	REFERENCES farmer(user_id)
);

CREATE TABLE arbitration(
	id serial PRIMARY KEY,
	admin_id int references admin(user_id) NOT NULL,
	delivery_order_id int NOT NULL,
	driver_id int NOT NULL,
	farmer_id int NOT NULL,
	FOREIGN KEY(delivery_order_id)
	REFERENCES delivery_order(id),
	FOREIGN KEY(driver_id)
	REFERENCES driver(user_id),
	FOREIGN KEY(farmer_id)
	REFERENCES farmer(user_id)
);

