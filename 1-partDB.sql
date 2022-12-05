CREATE TABLE Order_for_drive(
    id PRIMARY KEY,
    driver_id int,
    cost int, 
    
    FOREIGN KEY(driver_id)
    REFERENCES driver(id),
    FOREIGN KEY(farmer_id)
    REFERENCES farmer(id),
);
CREATE TABLE Country(
    name varchar(30) PRIMARY KEY,
    soil_type varchar(30),
    sunlight_amount int,
);
CREATE TABLE Location(
    id PRIMARY KEY,
    country_name varchar(30),
    price_per_month int
    square int
    FOREIGN KEY(country_name)
    REFERENCES country(name),
);
CREATE TABLE Farm(
   id PRIMARY KEY,
   location_id int,
       FOREIGN KEY(location_id)
    REFERENCES Location(id),
);

CREATE TABLE Farmer(
    id PRIMARY KEY,
    farmer_inf_login int,
    balance int, 
    farm_id int,
    FOREIGN KEY(farmer_inf_login)
    REFERENCES user(login),
    FOREIGN KEY(farm_id),
    REFERENCES farm(id),
);
CREATE TABLE Farmer_orders(
   id PRIMARY KEY,
   fermer_id int,
   order_id int,
       FOREIGN KEY(fermer_id)
    REFERENCES fermer(id),
     FOREIGN KEY(order_id)
    REFERENCES order(id),
);
CREATE TABLE Customer(
   id PRIMARY KEY,
   name varchar(10),
   phone int(10),
   mail varchar(50),
);
CREATE TABLE Review(
   id PRIMARY KEY,
   sendler_id int,
   message varchar(255),
   rate int,
);
CREATE TABLE User(
   login varchar(10) PRIMARY KEY,
   phone int(10),
   mail varchar(50),
   password varchar(15)
);

CREATE TABLE Review_list(
   id PRIMARY KEY,
   rewiew_id int
   user_login varchar(10),
   FOREIGN KEY(rewiew_id),
    REFERENCES Review(id),
     FOREIGN KEY(user_login)
    REFERENCES User(id),
);
CREATE TABLE Arbitration(
   id PRIMARY KEY,
   order_id int,
   driver_id int,
   fermer_id int,
   FOREIGN KEY(order_id)
    REFERENCES Order(id),
       FOREIGN KEY(driver_id)
    REFERENCES Driver(id),
        FOREIGN KEY(fermer_id)
    REFERENCES fermer(id),
);
CREATE TABLE Customer(
   id PRIMARY KEY,
   name varchar(10),
   phone int(10),
   mail varchar(50),
);
