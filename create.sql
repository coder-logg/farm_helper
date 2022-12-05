CREATE TABLE IF NOT EXIST _admin(
	id serial primary key,
	admin_inf_login int references users NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXIST arbitrartion_list(
	id serial primary key,
	arbitration_id int references arbitration(id) NOT NULL,
	admin_id int references _admin(id) NOT NULL
)

CREATE TABLE IF NOT EXIST driver(
	id serial primary key,
	driver_inf_login varchar(10) references users(login) NOT NULL,
	car_id int references car(id) NOT NULL,
	balance int
);

CREATE TABLE IF NOT EXIST car(
	id serial primary key,
    mark varchcar(15) NOT NULL,
    number varchar(6) NOT NULL,
    capacity int

);

CREATE TABLE IF NOT EXIST status(
	id serial primary key,
	location varchar(30) NOT NULL
);


CREATE TABLE IF NOT EXIST order_detail(
    id serial primary key,
    plant_id int references plant(id) NOT NULL,
    amount int check(amount > 0) NOT NULL,
    delivery_date timestamp NOT NULL,
    delivery_address varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXIST order(
	id serial primary key,
    customer_id int references customer(id) NOT NULL,
    order_detail_id int references order_detail_id(id) NOT NULL,
    status_id int references status(id) NOT NULL,
    cost int check(cost > 1) NOT NULL
);

CREATE TABLE IF NOT EXIST plant(
	id serial primary key,
    name  varchar(30) NOT NULL UNIQUE,
    сost int NOT NULL,
    time_for_completed timestamp NOT NULL,
    sunlight int NOT NULL
);

CREATE TABLE IF NOT EXIST equipment(
	id serial primary key,
    name varchar(30),
    cost int,
    location varchar(30)
);

CREATE TABLE IF NOT EXIST required_requipment(
	id serial primary key,
    plant_id int references plant(id),
    equipment_id int references equipment(id)
);





