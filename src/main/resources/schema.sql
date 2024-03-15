CREATE TYPE order_progress_stages AS ENUM ('TO_DO', 'STARTED', 'CULTIVATION','DELIVERY','FINISHED');
CREATE TYPE delivery_progress_stages AS ENUM (
    'CREATED',
    'WAITING_ACCEPTANCE_BY_DRIVER',
    'REJECTED',
    'ACCEPTED',
    'ON_DELIVERY',
    'DELIVERED',
    'ARBITRATION'
);

CREATE CAST (character varying AS order_progress_stages) WITH INOUT AS ASSIGNMENT;
CREATE CAST (character varying AS delivery_progress_stages) WITH INOUT AS ASSIGNMENT;

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
	name varchar(50) NOT NULL UNIQUE,
	phone varchar(12) NOT NULL UNIQUE,
	mail varchar(50) UNIQUE
);

CREATE TABLE IF NOT EXISTS car (
	id serial primary key,
	mark varchar(15) NOT NULL,
	license_number varchar(6) NOT NULL UNIQUE,
	capacity int
);

-- CREATE TABLE IF NOT EXISTS status(
-- 	id serial primary key,
-- 	location varchar(30) NOT NULL,
-- 	progress progress_stages default 'STARTED'
-- );

CREATE TABLE IF NOT EXISTS equipment(
	id serial primary key,
	name varchar(40) UNIQUE NOT NULL,
	cost int NOT NULL
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
	user_id serial primary key references _user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS driver(
	user_id serial primary key references _user(id) ON DELETE CASCADE,
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

CREATE TABLE IF NOT EXISTS required_equipment(
	id serial primary key,
	plant_id int references plant(id) ON DELETE CASCADE NOT NULL ,
	equipment_id int references equipment(id) ON DELETE CASCADE NOT NULL,
	amount int default 1 CHECK (amount > 0),
	UNIQUE (plant_id, equipment_id)
);

CREATE TABLE location(
	id serial PRIMARY KEY,
	address text not null unique,
	country varchar(50) not null,
	coordinates point not null unique
-- 	sunlight_amount int
);


CREATE TABLE IF NOT EXISTS farm(
	id serial PRIMARY KEY,
	location_id int references location(id) NOT NULL,
    price_per_month int,
    square int CHECK(square>0) NOT NULL
);

CREATE TABLE IF NOT EXISTS farmer(
	user_id serial PRIMARY KEY references _user(id) ON DELETE CASCADE,
	balance int default 0,
	farm_id int references farm(id)
);

CREATE TABLE IF NOT EXISTS _order(
	id serial primary key,
	farmer_id int references farmer(user_id) NOT NULL,
	customer_id int references customer(id) NOT NULL,
	status order_progress_stages default 'TO_DO',
    plant_id int references plant(id) NOT NULL,
    amount int check(amount > 0) NOT NULL,
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
	status delivery_progress_stages default 'CREATED',
	to_address text NOT NULL,
	from_address text NOT NULL
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

