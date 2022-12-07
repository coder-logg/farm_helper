CREATE TABLE _user(
	login varchar(10) PRIMARY KEY,
	phone varchar(12),
	mail varchar(50),
	password varchar(15)
);

CREATE TABLE Customer(
	id serial PRIMARY KEY,
	name varchar(10),
	phone varchar(12),
	mail varchar(50)
);

CREATE TABLE IF NOT EXISTS car(
	id serial primary key,
	mark varchar(15) NOT NULL,
	number varchar(6) NOT NULL,
	capacity int
);

CREATE TABLE IF NOT EXISTS status(
	id serial primary key,
	location varchar(30) NOT NULL,
	progress varchar(20) check (progress='started' or progress='cultivation' or
								progress='delivery' or progress='finished' or
								progress='arbitration')
);

CREATE TABLE IF NOT EXISTS equipment(
	id serial primary key,
	name varchar(30),
	cost int,
	location varchar(30)
);

CREATE TABLE Review(
	id serial PRIMARY KEY,
	sendler_login varchar(10),
	message varchar(255),
	rate int
);

CREATE TABLE IF NOT EXISTS admin(
	id serial primary key,
	admin_inf_login varchar(10) references _user(login) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS driver(
	id serial primary key,
	driver_inf_login varchar(10) references _user(login) NOT NULL,
	car_id int references car(id) NOT NULL,
	balance int
);

CREATE TABLE IF NOT EXISTS plant(
	id serial primary key,
	name varchar(30) NOT NULL UNIQUE,
	cost int NOT NULL,
	time_for_completed timestamp NOT NULL,
	sunlight int NOT NULL
);

CREATE TABLE IF NOT EXISTS order_detail(
	id serial primary key,
	plant_id int references plant(id) NOT NULL,
	amount int check(amount > 0) NOT NULL,
	delivery_date timestamp NOT NULL,
	delivery_address varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS _order(
	id serial primary key,
	customer_id int references customer(id) NOT NULL,
	order_detail_id int references order_detail(id) NOT NULL,
	status_id int references status(id) NOT NULL,
	cost int check(cost > 0) NOT NULL
);

CREATE TABLE IF NOT EXISTS required_equipment(
	id serial primary key,
	plant_id int references plant(id),
	equipment_id int references equipment(id)
);

CREATE TABLE Country(
	name varchar(30) PRIMARY KEY,
	soil_type varchar(30),
	sunlight_amount int
);

CREATE TABLE Location(
	id serial PRIMARY KEY,
	country_name varchar(30),
	price_per_month int,
	square int,
	FOREIGN KEY(country_name)
	REFERENCES country(name)
);

CREATE TABLE Farm(
	id serial PRIMARY KEY,
	location_id int,
	FOREIGN KEY(location_id)
	REFERENCES Location(id)
);

CREATE TABLE Farmer(
	id serial PRIMARY KEY,
	farmer_inf_login varchar(10),
	balance int,
	farm_id int,
	FOREIGN KEY(farmer_inf_login)
	REFERENCES _user(login),
	FOREIGN KEY(farm_id)
	REFERENCES farm(id)
);

CREATE TABLE Order_for_drive(
	id serial PRIMARY KEY,
	driver_id int,
	farmer_id int,
	cost int,
	FOREIGN KEY(driver_id)
	REFERENCES driver(id),
	FOREIGN KEY(farmer_id)
	REFERENCES farmer(id)
);

CREATE TABLE Farmer_orders(
	id serial PRIMARY KEY,
	farmer_id int,
	order_id int,
	FOREIGN KEY(farmer_id)
	REFERENCES farmer(id),
	FOREIGN KEY(order_id)
	REFERENCES _order(id)
);

CREATE TABLE Review_list(
	id serial PRIMARY KEY,
	review_id int,
	_user_login varchar(10),
	FOREIGN KEY(review_id)
	REFERENCES Review(id),
	FOREIGN KEY(_user_login)
	REFERENCES _user(login)
);

CREATE TABLE Arbitration(
	id serial PRIMARY KEY,
	order_id int,
	driver_id int,
	farmer_id int,
	FOREIGN KEY(order_id)
	REFERENCES _order(id),
	FOREIGN KEY(driver_id)
	REFERENCES Driver(id),
	FOREIGN KEY(farmer_id)
	REFERENCES farmer(id)
);

CREATE TABLE IF NOT EXISTS arbitration_list(
	id serial primary key,
	arbitration_id int references arbitration(id) NOT NULL,
	admin_id int references admin(id) NOT NULL
);
