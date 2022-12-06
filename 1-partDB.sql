CREATE TABLE Order_for_drive(
    id PRIMARY KEY,
    farmer_id int NOT NULL, 
    driver_id int NOT NULL,
    cost int CHECK(cost>0), 
    
    FOREIGN KEY(driver_id)
    REFERENCES driver(id),
    FOREIGN KEY(farmer_id)
    REFERENCES farmer(id),
);
CREATE TABLE Country(
    name varchar(30) PRIMARY KEY,
    soil_type varchar(30) NOT NULL,
    sunlight_amount int,
    CONSTRAINT CHK_Country CHECK(sunlight_amount>0 AND (soil_type='почва' OR soil_type='чернозем' OR soil_type='глина'))
);
CREATE INDEX idx_sunlight_amount on Country(sunlight_amount);
CREATE TABLE Location(
    id PRIMARY KEY,
    country_name varchar(30),
    price_per_month int,
    square int CHECK(square>0),
    FOREIGN KEY(country_name)
    REFERENCES country(name),
);
CREATE INDEX idx_price_per_month on Location(price_per_month);
CREATE TABLE Farm(
   id PRIMARY KEY,
   location_id int NOT NULL,
       FOREIGN KEY(location_id)
    REFERENCES Location(id),
);

CREATE TABLE Farmer(
    id PRIMARY KEY,
    farmer_inf_login int NOT NULL,
    balance int CHECK(balance>0), 
    farm_id int,
    FOREIGN KEY(farmer_inf_login)
    REFERENCES user(login),
    FOREIGN KEY(farm_id),
    REFERENCES farm(id),
);
CREATE TABLE Farmer_orders(
   id PRIMARY KEY,
   fermer_id int NOT NULL,
   order_id int,
       FOREIGN KEY(fermer_id)
    REFERENCES fermer(id),
     FOREIGN KEY(order_id)
    REFERENCES order(id),
);
CREATE TABLE Customer(
   id PRIMARY KEY,
   name varchar(10) NOT NULL,
   phone int(10) NOT NULL UNIQUE,
   mail varchar(50),
);
CREATE TABLE Review(
   id PRIMARY KEY,
   sendler_id int NOT NULL,
   message varchar(255) NOT NULL,
   rate int CHECK(rate>0 AND rate<5),
);
CREATE INDEX idx_rate on Review(rate);
CREATE TABLE User(
   login varchar(10) PRIMARY KEY,
   phone int(10) NOT NULL UNIQUE,
   mail varchar(50) NOT NULL UNIQUE,
   password varchar(15) NOT NULL,
);

CREATE TABLE Review_list(
   id PRIMARY KEY,
   rewiew_id int  NOT NULL 
   user_login varchar(10) NOT NULL,
   FOREIGN KEY(rewiew_id),
    REFERENCES Review(id),
     FOREIGN KEY(user_login)
    REFERENCES User(id),
);
CREATE TABLE Arbitration(
   id PRIMARY KEY,
   order_id int NOT NULL,
   driver_id int  NOT NULL,
   fermer_id int NOT NULL,
   FOREIGN KEY(order_id)
    REFERENCES Order(id),
       FOREIGN KEY(driver_id)
    REFERENCES Driver(id),
        FOREIGN KEY(fermer_id)
    REFERENCES fermer(id),
);
